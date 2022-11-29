package io.apicurio.umg.pipe.java;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.umg.beans.SpecificationVersion;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyModelWithOrigin;
import io.apicurio.umg.models.concept.PropertyType;
import io.apicurio.umg.pipe.java.method.BodyBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Creates the i/o writer classes.  There is a bespoke writer for each specification
 * version.
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateWritersStage extends AbstractJavaStage {

    @Override
    protected void doProcess() {
        getState().getSpecIndex().getAllSpecificationVersions().forEach(specVersion -> {
            createWriter(specVersion);
        });
    }

    /**
     * Creates a writer for the given spec version.
     * @param specVersion
     */
    private void createWriter(SpecificationVersion specVersion) {
        String writerPackageName = getWriterPackageName(specVersion);
        String writerClassName = getWriterClassName(specVersion);

        // Create java source code for the writer
        JavaClassSource writerClassSource = Roaster.create(JavaClassSource.class)
                .setPackage(writerPackageName)
                .setName(writerClassName)
                .setPublic();
        writerClassSource.addImport(getState().getConfig().getRootNamespace() + ".util." + "JsonUtil");
        writerClassSource.addImport(getState().getConfig().getRootNamespace() + ".util." + "WriterUtil");

        // Create the writeXYZ methods - one for each entity
        specVersion.getEntities().forEach(entity -> {
            EntityModel entityModel = getState().getConceptIndex().lookupEntity(specVersion.getNamespace() + "." + entity.getName());
            if (entityModel == null) {
                warn("Entity model not found for entity: " + entity);
            } else {
                createWriteMethodFor(specVersion, writerClassSource, entityModel);
            }
        });

        getState().getJavaIndex().index(writerClassSource);
    }

    /**
     * Creates a single "writeXyx" method for the given entity.
     *
     * @param specVersion
     * @param writerClassSource
     * @param entityModel
     */
    private void createWriteMethodFor(SpecificationVersion specVersion, JavaClassSource writerClassSource, EntityModel entityModel) {
        String writeMethodName = writeMethodName(entityModel);

        JavaInterfaceSource javaEntityModel = getState().getJavaIndex().lookupInterface(getJavaEntityInterfaceFQN(entityModel));
        if (javaEntityModel == null) {
            warn("Java entity not found for: " + entityModel.fullyQualifiedName());
            return;
        }

        writerClassSource.addImport(javaEntityModel.getQualifiedName());
        writerClassSource.addImport(ObjectNode.class);
        MethodSource<JavaClassSource> methodSource = writerClassSource.addMethod()
                .setName(writeMethodName)
                .setReturnTypeVoid()
                .setPublic();
        methodSource.addParameter(javaEntityModel.getName(), "node");
        methodSource.addParameter(ObjectNode.class.getSimpleName(), "json");

        // Now create the body content for the writer.
        BodyBuilder body = new BodyBuilder();
        body.append("if (node == null) {");
        body.append("    return;");
        body.append("}");

        // Write each property of the entity
        Collection<PropertyModelWithOrigin> allProperties = getState().getConceptIndex().getAllEntityProperties(entityModel);
        allProperties.forEach(property -> {
            createWritePropertyCode(body, property, entityModel, javaEntityModel, writerClassSource);
        });
        // Write "extra" properties
        createWriteExtraPropertiesCode(body);

        methodSource.setBody(body.toString());
    }

    /**
     * Generates the right java code for writing a single property of an entity.
     *
     * @param body
     * @param property
     * @param javaEntity
     * @param javaEntity
     * @param writerClassSource
     */
    private void createWritePropertyCode(BodyBuilder body, PropertyModelWithOrigin propertyWithOrigin, EntityModel entityModel,
            JavaInterfaceSource javaEntity, JavaClassSource writerClassSource) {
        CreateWriteProperty crp = new CreateWriteProperty(propertyWithOrigin, entityModel, javaEntity, writerClassSource);
        body.clearContext();
        crp.writeTo(body);
    }

    /**
     * Generates code that will write the extra properties from the model to the JSON output.
     *
     * @param body
     */
    private void createWriteExtraPropertiesCode(BodyBuilder body) {
        body.append("WriterUtil.writeExtraProperties(node, json);");
    }

    private static String writeMethodName(EntityModel entityModel) {
        return writeMethodName(entityModel.getName());
    }

    private static String writeMethodName(String entityName) {
        return "write" + StringUtils.capitalize(entityName);
    }

    @Data
    @AllArgsConstructor
    private class CreateWriteProperty {
        PropertyModelWithOrigin propertyWithOrigin;
        EntityModel entityModel;
        JavaInterfaceSource javaEntityModel;
        JavaClassSource writerClassSource;

        /**
         * Generates code to write a property from a JSON node into the data model.
         *
         * @param body
         */
        public void writeTo(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            if (isStarProperty(property)) {
                handleStarProperty(body);
            } else if (isRegexProperty(property)) {
                handleRegexProperty(body);
            } else if (isEntity(property)) {
                handleEntityProperty(body);
            } else if (isPrimitive(property)) {
                handlePrimitiveTypeProperty(body);
            } else if (property.getType().isList()) {
                handleListProperty(body);
            } else if (property.getType().isMap()) {
                handleMapProperty(body);
            } else if (property.getType().isUnion()) {
                handleUnionProperty(body);
            } else {
                warn("Entity property '" + property.getName() + "' not written (unsupported) for entity: " + entityModel.fullyQualifiedName());
                warn("       property type: " + property.getType());
            }
        }

        private void handleStarProperty(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            if (isEntity(property)) {
                String entityTypeName = entityModel.getNamespace().fullName() + "." + property.getType().getSimpleType();
                EntityModel propertyTypeEntity = getState().getConceptIndex().lookupEntity(entityTypeName);
                if (propertyTypeEntity == null) {
                    warn("STAR Property entity type not found for property: '" + property.getName() + "' of entity: " + entityModel.fullyQualifiedName());
                    warn("       property type: " + property.getType());
                    return;
                }
                JavaInterfaceSource entityTypeJavaModel = getState().getJavaIndex().lookupInterface(getJavaEntityInterfaceFQN(propertyTypeEntity));
                if (entityTypeJavaModel == null) {
                    warn("STAR Entity property '" + property.getName() + "' not written (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in JAVA index: " + property.getType());
                    return;
                }

                writerClassSource.addImport(List.class);
                writerClassSource.addImport(entityTypeJavaModel);

                body.addContext("writeMethodName", writeMethodName(propertyTypeEntity));
                body.addContext("entityJavaType", entityTypeJavaModel.getName());

                body.append("{");
                body.append("    List<String> propertyNames = node.getItemNames();");
                body.append("    propertyNames.forEach(propertyName -> {");
                body.append("        ObjectNode object = JsonUtil.objectNode();");
                body.append("        this.${writeMethodName}((${entityJavaType}) node.getItem(propertyName), object);");
                body.append("        JsonUtil.setObjectProperty(json, propertyName, object);");
                body.append("    });");
                body.append("}");
            } else if (isPrimitive(property) || isPrimitiveList(property) || isPrimitiveMap(property)) {
                writerClassSource.addImport(List.class);

                body.addContext("valueType", determineValueType(property.getType()));
                body.addContext("setPropertyMethodName", determineSetPropertyVariant(property.getType()));

                body.append("{");
                body.append("    List<String> propertyNames = node.getItemNames();");
                body.append("    propertyNames.forEach(propertyName -> {");
                body.append("        ${valueType} value = node.getItem(propertyName);");
                body.append("        JsonUtil.${setPropertyMethodName}(json, propertyName, value);");
                body.append("    });");
                body.append("}");
            } else {
                warn("STAR Entity property '" + property.getName() + "' not written (unhandled) for entity: " + entityModel.fullyQualifiedName());
                warn("       property type: " + property.getType());
            }
        }

        private void handleRegexProperty(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            if (isEntity(property)) {
                String entityTypeName = entityModel.getNamespace().fullName() + "." + property.getType().getSimpleType();
                EntityModel propertyTypeEntity = getState().getConceptIndex().lookupEntity(entityTypeName);
                if (propertyTypeEntity == null) {
                    warn("REGEX Property entity type not found for property: '" + property.getName() + "' of entity: " + entityModel.fullyQualifiedName());
                    warn("       property type: " + property.getType());
                    return;
                }
                JavaInterfaceSource entityTypeJavaModel = getState().getJavaIndex().lookupInterface(getJavaEntityInterfaceFQN(propertyTypeEntity));
                if (entityTypeJavaModel == null) {
                    warn("REGEX Entity property '" + property.getName() + "' not written (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in JAVA index: " + property.getType());
                    return;
                }
                JavaInterfaceSource commonEntityTypeJavaModel = resolveCommonJavaEntity(propertyTypeEntity);

                writerClassSource.addImport(Map.class);
                writerClassSource.addImport(entityTypeJavaModel);

                body.addContext("mapValueJavaType", entityTypeJavaModel.getName());
                body.addContext("getterMethodName", getterMethodName(property));
                body.addContext("writeMethodName", writeMethodName(propertyTypeEntity));
                body.addContext("mapValueCommonJavaType", commonEntityTypeJavaModel.getName());

                body.append("{");
                body.append("    Map<String, ? extends ${mapValueCommonJavaType}> models = node.${getterMethodName}();");
                body.append("    if (models != null) {");
                body.append("        models.keySet().forEach(propertyName -> {");
                body.append("            ObjectNode object = JsonUtil.objectNode();");
                body.append("            this.${writeMethodName}((${mapValueJavaType}) models.get(propertyName), object);");
                body.append("            JsonUtil.setObjectProperty(json, propertyName, object);");
                body.append("        });");
                body.append("    }");
                body.append("}");
            } else if (isPrimitive(property) || isPrimitiveList(property) || isPrimitiveMap(property)) {
                writerClassSource.addImport(List.class);

                body.addContext("valueType", determineValueType(property.getType()));
                body.addContext("getterMethodName", getterMethodName(property));
                body.addContext("setPropertyMethodName", determineSetPropertyVariant(property.getType()));

                body.append("{");
                body.append("    Map<String, ${valueType}> values = node.${getterMethodName}();");
                body.append("    if (values != null) {");
                body.append("        values.keySet().forEach(propertyName -> {");
                body.append("            ${valueType} value = values.get(propertyName);");
                body.append("            JsonUtil.${setPropertyMethodName}(json, propertyName, value);");
                body.append("        });");
                body.append("    }");
                body.append("}");
            } else {
                warn("REGEX Entity property '" + property.getName() + "' not written (unhandled) for entity: " + entityModel.fullyQualifiedName());
                warn("       property type: " + property.getType());
            }
        }

        private void handleEntityProperty(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            String propertyTypeEntityName = entityModel.getNamespace().fullName() + "." + property.getType().getSimpleType();
            EntityModel propertyTypeEntity = getState().getConceptIndex().lookupEntity(propertyTypeEntityName);
            if (propertyTypeEntity == null) {
                warn("Property entity type not found for property: '" + property.getName() + "' of entity: " + entityModel.fullyQualifiedName());
                warn("       property type: " + property.getType());
                return;
            }
            JavaInterfaceSource propertyTypeJavaEntity = resolveJavaEntityType(entityModel.getNamespace(), property);
            writerClassSource.addImport(propertyTypeJavaEntity);

            body.addContext("propertyName", property.getName());
            body.addContext("getterMethodName", getterMethodName(property));
            body.addContext("writeMethodName", writeMethodName(propertyTypeEntity));
            body.addContext("propertyTypeJavaEntity", propertyTypeJavaEntity.getName());

            body.append("{");
            body.append("    if (node.${getterMethodName}() != null) {");
            body.append("        ObjectNode object = JsonUtil.objectNode();");
            body.append("        this.${writeMethodName}((${propertyTypeJavaEntity}) node.${getterMethodName}(), object);");
            body.append("        JsonUtil.setObjectProperty(json, \"${propertyName}\", object);");
            body.append("    }");
            body.append("}");
        }

        private void handlePrimitiveTypeProperty(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            body.addContext("setPropertyMethodName", determineSetPropertyVariant(property.getType()));
            body.addContext("propertyName", property.getName());
            body.addContext("getterMethodName", getterMethodName(property));

            body.append("JsonUtil.${setPropertyMethodName}(json, \"${propertyName}\", node.${getterMethodName}());");
        }

        private void handleListProperty(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            body.addContext("propertyName", property.getName());
            body.addContext("getterMethodName", getterMethodName(property));

            PropertyType listValuePropertyType = property.getType().getNested().iterator().next();
            if (listValuePropertyType.isPrimitiveType()) {
                body.addContext("setPropertyMethodName", determineSetPropertyVariant(property.getType()));

                body.append("JsonUtil.${setPropertyMethodName}(json, \"${propertyName}\", node.${getterMethodName}());");
            } else if (listValuePropertyType.isEntityType()) {
                String entityTypeName = listValuePropertyType.getSimpleType();
                String fqEntityName = entityModel.getNamespace().fullName() + "." + entityTypeName;
                EntityModel entityTypeModel = getState().getConceptIndex().lookupEntity(fqEntityName);
                if (entityTypeModel == null) {
                    warn("LIST Entity property '" + property.getName() + "' not written (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in index: " + property.getType());
                    return;
                }
                JavaInterfaceSource entityTypeJavaModel = resolveJavaEntity(entityTypeModel);
                if (entityTypeJavaModel == null) {
                    warn("LIST Entity property '" + property.getName() + "' not written (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in JAVA index: " + property.getType());
                    return;
                }
                JavaInterfaceSource commonEntityTypeJavaModel = resolveCommonJavaEntity(entityTypeModel);

                writerClassSource.addImport(entityTypeJavaModel);
                writerClassSource.addImport(commonEntityTypeJavaModel);
                writerClassSource.addImport(List.class);
                writerClassSource.addImport(ArrayNode.class);

                body.addContext("propertyName", property.getName());
                body.addContext("listValueJavaType", entityTypeJavaModel.getName());
                body.addContext("writeMethodName", writeMethodName(entityTypeModel));
                body.addContext("listValueCommonJavaType", commonEntityTypeJavaModel.getName());

                body.append("{");
                body.append("    List<? extends ${listValueCommonJavaType}> models = node.${getterMethodName}();");
                body.append("    if (models != null) {");
                body.append("        ArrayNode array = JsonUtil.arrayNode();");
                body.append("        models.forEach(model -> {");
                body.append("            ObjectNode object = JsonUtil.objectNode();");
                body.append("            this.${writeMethodName}((${listValueJavaType}) model, object);");
                body.append("            array.add(object);");
                body.append("        });");
                body.append("        JsonUtil.setAnyProperty(json, \"${propertyName}\", array);");
                body.append("    }");
                body.append("}");
            } else {
                warn("LIST Entity property '" + property.getName() + "' not written (unsupported) for entity: " + entityModel.fullyQualifiedName());
                warn("       property type: " + property.getType());
            }
        }

        private void handleMapProperty(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            body.addContext("propertyName", property.getName());
            body.addContext("getterMethodName", getterMethodName(property));

            PropertyType mapValuePropertyType = property.getType().getNested().iterator().next();
            if (mapValuePropertyType.isPrimitiveType()) {
                body.addContext("setPropertyMethodName", determineSetPropertyVariant(property.getType()));
                writerClassSource.addImport(List.class);

                body.append("JsonUtil.${setPropertyMethodName}(json, \"${propertyName}\", node.${getterMethodName}());");
            } else if (mapValuePropertyType.isEntityType()) {
                String entityTypeName = mapValuePropertyType.getSimpleType();
                String fqEntityName = entityModel.getNamespace().fullName() + "." + entityTypeName;
                EntityModel entityTypeModel = getState().getConceptIndex().lookupEntity(fqEntityName);
                if (entityTypeModel == null) {
                    warn("MAP Entity property '" + property.getName() + "' not written (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in index: " + property.getType());
                    return;
                }
                JavaInterfaceSource entityTypeJavaModel = getState().getJavaIndex().lookupInterface(getJavaEntityInterfaceFQN(entityTypeModel));
                if (entityTypeJavaModel == null) {
                    warn("MAP Entity property '" + property.getName() + "' not written (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in JAVA index: " + property.getType());
                    return;
                }
                JavaInterfaceSource commonEntityTypeJavaModel = resolveCommonJavaEntity(entityTypeModel);

                writerClassSource.addImport(Map.class);
                writerClassSource.addImport(entityTypeJavaModel);
                writerClassSource.addImport(commonEntityTypeJavaModel);

                body.addContext("mapValueJavaType", entityTypeJavaModel.getName());
                body.addContext("writeMethodName", "write" + entityTypeName);
                body.addContext("mapValueCommonJavaType", commonEntityTypeJavaModel.getName());

                body.append("{");
                body.append("    Map<String, ? extends ${mapValueCommonJavaType}> models = node.${getterMethodName}();");
                body.append("    if (models != null) {");
                body.append("        ObjectNode object = JsonUtil.objectNode();");
                body.append("        models.keySet().forEach(jsonName -> {");
                body.append("            ObjectNode jsonValue = JsonUtil.objectNode();");
                body.append("            this.${writeMethodName}((${mapValueJavaType}) models.get(jsonName), jsonValue);");
                body.append("            JsonUtil.setObjectProperty(object, jsonName, jsonValue);");
                body.append("        });");
                body.append("        JsonUtil.setObjectProperty(json, \"${propertyName}\", object);");
                body.append("    }");
                body.append("}");
            } else {
                warn("MAP Entity property '" + property.getName() + "' not written (unsupported) for entity: " + entityModel.fullyQualifiedName());
                warn("       property type: " + property.getType());
            }
        }

        private void handleUnionProperty(BodyBuilder body) {
            // TODO Auto-generated method stub
            PropertyModel property = propertyWithOrigin.getProperty();
            warn("UNION Entity property '" + property.getName() + "' not written (unsupported) for entity: " + entityModel.fullyQualifiedName());
            warn("       property type: " + property.getType());
        }

        /**
         * Figure out which variant of "writeProperty" from "JsonUtil" we should use for
         * this property.  The property might be a primitive type, or a list/map of primitive
         * types.
         *
         * @param type
         */
        private String determineSetPropertyVariant(PropertyType type) {
            if (type.isPrimitiveType()) {
                Class<?> _class = primitiveTypeToClass(type);
                if (ObjectNode.class.equals(_class)) {
                    writerClassSource.addImport(_class);
                    return "setObjectProperty";
                } else if (JsonNode.class.equals(_class)) {
                    writerClassSource.addImport(_class);
                    return "setAnyProperty";
                } else {
                    return "set" + _class.getSimpleName() + "Property";
                }
            }

            if (type.isList()) {
                PropertyType listType = type.getNested().iterator().next();
                if (listType.isPrimitiveType()) {
                    Class<?> _class = primitiveTypeToClass(listType);
                    if (ObjectNode.class.equals(_class)) {
                        writerClassSource.addImport(_class);
                        return "setObjectArrayProperty";
                    } else if (JsonNode.class.equals(_class)) {
                        writerClassSource.addImport(_class);
                        return "setAnyArrayProperty";
                    } else {
                        return "set" + _class.getSimpleName() + "ArrayProperty";
                    }
                }
            }

            if (type.isMap()) {
                PropertyType mapType = type.getNested().iterator().next();
                if (mapType.isPrimitiveType()) {
                    Class<?> _class = primitiveTypeToClass(mapType);
                    if (ObjectNode.class.equals(_class)) {
                        writerClassSource.addImport(_class);
                        return "setObjectMapProperty";
                    } else if (JsonNode.class.equals(_class)) {
                        writerClassSource.addImport(_class);
                        return "setAnyMapProperty";
                    } else {
                        return "set" + _class.getSimpleName() + "MapProperty";
                    }
                }
            }

            PropertyModel property = propertyWithOrigin.getProperty();
            warn("Unable to determine value type for: " + property);
            return "setProperty";
        }

        /**
         * Determines the Java data type of the given property.
         *
         * @param type
         */
        private String determineValueType(PropertyType type) {
            if (type.isPrimitiveType()) {
                Class<?> _class = primitiveTypeToClass(type);
                if (_class != null) {
                    writerClassSource.addImport(_class);
                    return _class.getSimpleName();
                }
            }

            if (type.isList()) {
                PropertyType listType = type.getNested().iterator().next();
                if (listType.isPrimitiveType()) {
                    Class<?> _class = primitiveTypeToClass(listType);
                    if (_class != null) {
                        writerClassSource.addImport(_class);
                        return "List<" + _class.getSimpleName() + ">";
                    }
                }
            }

            if (type.isMap()) {
                PropertyType mapType = type.getNested().iterator().next();
                if (mapType.isPrimitiveType()) {
                    Class<?> _class = primitiveTypeToClass(mapType);
                    if (_class != null) {
                        writerClassSource.addImport(_class);
                        return "Map<String, " + _class.getSimpleName() + ">";
                    }
                }
            }

            PropertyModel property = propertyWithOrigin.getProperty();
            warn("Unable to determine value type for: " + property);
            return "Object";
        }
    }
}
