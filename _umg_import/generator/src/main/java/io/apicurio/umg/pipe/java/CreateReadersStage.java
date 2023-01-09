package io.apicurio.umg.pipe.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.umg.beans.SpecificationVersion;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.NamespaceModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyModelWithOrigin;
import io.apicurio.umg.models.concept.PropertyType;
import io.apicurio.umg.pipe.java.method.BodyBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Creates the i/o reader classes.  There is a bespoke reader for each specification
 * version.
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateReadersStage extends AbstractJavaStage {

    @Override
    protected void doProcess() {
        getState().getSpecIndex().getAllSpecificationVersions().forEach(specVersion -> {
            createReader(specVersion);
        });
    }

    /**
     * Creates a reader for the given spec version.
     * @param specVersion
     */
    private void createReader(SpecificationVersion specVersion) {
        String readerPackageName = getReaderPackageName(specVersion);
        String readerClassName = getReaderClassName(specVersion);

        // Create java source code for the reader
        JavaClassSource readerClassSource = Roaster.create(JavaClassSource.class)
                .setPackage(readerPackageName)
                .setName(readerClassName)
                .setPublic();
        readerClassSource.addImport(getState().getConfig().getRootNamespace() + ".util." + "JsonUtil");
        readerClassSource.addImport(getState().getConfig().getRootNamespace() + ".util." + "ReaderUtil");

        // Implements the ModelReader interface
        JavaInterfaceSource modelReaderInterfaceSource = getState().getJavaIndex().lookupInterface(getModelReaderInterfaceFQN());
        readerClassSource.addImport(modelReaderInterfaceSource);
        readerClassSource.addInterface(modelReaderInterfaceSource);

        // Create the readXYZ methods - one for each entity
        specVersion.getEntities().forEach(entity -> {
            EntityModel entityModel = getState().getConceptIndex().lookupEntity(specVersion.getNamespace() + "." + entity.getName());
            if (entityModel == null) {
                warn("Entity model not found for entity: " + entity);
            } else {
                createReadMethodFor(specVersion, readerClassSource, entityModel);

                // There should be a single root entity in the spec.
                if (entityModel.isRoot()) {
                    createReadRootMethod(specVersion, readerClassSource, entityModel);
                }
            }
        });

        getState().getJavaIndex().index(readerClassSource);
    }

    /**
     * Creates a "readRoot(json)" method for this reader.
     * @param specVersion
     * @param readerClassSource
     * @param entityModel
     */
    private void createReadRootMethod(SpecificationVersion specVersion, JavaClassSource readerClassSource, EntityModel entityModel) {
        JavaInterfaceSource rootNodeInterfaceSource = getState().getJavaIndex().lookupInterface(getRootNodeInterfaceFQN());
        readerClassSource.addImport(rootNodeInterfaceSource);
        readerClassSource.addImport(ObjectNode.class);

        MethodSource<JavaClassSource> readRootMethodSource = readerClassSource.addMethod()
                .setName("readRoot")
                .setReturnType(rootNodeInterfaceSource.getName())
                .setPublic();
        readRootMethodSource.addParameter("ObjectNode", "json");
        readRootMethodSource.addAnnotation(Override.class);

        String readMethodName = readMethodName(entityModel);
        JavaInterfaceSource entitySource = lookupJavaEntity(entityModel);
        JavaClassSource entityImplSource = lookupJavaEntityImpl(entityModel);

        readerClassSource.addImport(entitySource);
        readerClassSource.addImport(entityImplSource);

        BodyBuilder body = new BodyBuilder();
        body.addContext("readMethodName", readMethodName);
        body.addContext("rootEntityType", entitySource.getName());
        body.addContext("rootEntityImplType", entityImplSource.getName());

        body.append("${rootEntityType} rootModel = new ${rootEntityImplType}();");
        body.append("this.${readMethodName}(json, rootModel);");
        body.append("return rootModel;");
        readRootMethodSource.setBody(body.toString());
    }

    /**
     * Creates a single "readXyz" method for the given entity.
     *
     * @param specVersion
     * @param readerClassSource
     * @param entityModel
     */
    private void createReadMethodFor(SpecificationVersion specVersion, JavaClassSource readerClassSource, EntityModel entityModel) {
        String entityFQN = getJavaEntityInterfaceFQN(entityModel);
        String readMethodName = readMethodName(entityModel);

        JavaInterfaceSource javaEntity = getState().getJavaIndex().lookupInterface(entityFQN);
        if (javaEntity == null) {
            warn("Java interface for entity not found: " + entityFQN);
        }

        readerClassSource.addImport(ObjectNode.class);
        readerClassSource.addImport(javaEntity);
        MethodSource<JavaClassSource> methodSource = readerClassSource.addMethod()
                .setName(readMethodName)
                .setReturnTypeVoid()
                .setPublic();
        methodSource.addParameter(ObjectNode.class.getSimpleName(), "json");
        methodSource.addParameter(javaEntity.getName(), "node");

        // Now create the body content for the reader.
        BodyBuilder body = new BodyBuilder();
        // Read each property of the entity
        Collection<PropertyModelWithOrigin> allProperties = getState().getConceptIndex().getAllEntityProperties(entityModel);
        allProperties.forEach(property -> {
            createReadPropertyCode(body, property, entityModel, javaEntity, readerClassSource);
        });
        // Read "extra" properties (whatever is left over)
        createReadExtraPropertiesCode(body);

        methodSource.setBody(body.toString());
    }

    /**
     * Generates the right java code for reading a single property of an entity.
     *
     * @param body
     * @param property
     * @param javaEntityModel
     * @param readerClassSource
     */
    private void createReadPropertyCode(BodyBuilder body, PropertyModelWithOrigin property, EntityModel entityModel,
            JavaInterfaceSource javaEntity, JavaClassSource readerClassSource) {
        CreateReadPropertySnippet crp = new CreateReadPropertySnippet(property, entityModel, javaEntity, readerClassSource);
        body.clearContext();
        crp.writeTo(body);
    }

    /**
     * Creates code that will read any extra/remaining properties on a JSON object.
     *
     * @param body
     */
    private void createReadExtraPropertiesCode(BodyBuilder body) {
        body.append("ReaderUtil.readExtraProperties(json, node);");
    }

    @Data
    @AllArgsConstructor
    private class CreateReadPropertySnippet {
        PropertyModelWithOrigin propertyWithOrigin;
        EntityModel entityModel;
        JavaInterfaceSource javaEntity;
        JavaClassSource readerClassSource;

        /**
         * Generates code to read a property from a JSON node into the data model.
         *
         * @param body
         */
        public void writeTo(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            if ("*".equals(property.getName())) {
                handleStarProperty(body);
            } else if (property.getName().startsWith("/")) {
                handleRegexProperty(body);
            } else if (property.getType().isEntityType()) {
                handleEntityProperty(body);
            } else if (property.getType().isPrimitiveType()) {
                handlePrimitiveTypeProperty(body);
            } else if (property.getType().isList()) {
                handleListProperty(body);
            } else if (property.getType().isMap()) {
                handleMapProperty(body);
            } else if (property.getType().isUnion()) {
                handleUnionProperty(body);
            } else {
                warn("Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
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
                JavaInterfaceSource propertyTypeJavaEntity = getState().getJavaIndex().lookupInterface(getJavaEntityInterfaceFQN(propertyTypeEntity));
                if (propertyTypeJavaEntity == null) {
                    warn("STAR Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in JAVA index: " + property.getType());
                    return;
                }
                readerClassSource.addImport(propertyTypeJavaEntity);
                readerClassSource.addImport(List.class);

                body.addContext("entityJavaType", propertyTypeJavaEntity.getName());
                body.addContext("createMethodName", createMethodName(propertyTypeEntity));
                body.addContext("readMethodName", readMethodName(propertyTypeEntity));
                body.addContext("addMethodName", "addItem");

                body.append("{");
                body.append("    List<String> propertyNames = JsonUtil.keys(json);");
                body.append("    propertyNames.forEach(name -> {");
                body.append("        ObjectNode object = JsonUtil.consumeObjectProperty(json, name);");
                body.append("        if (object != null) {");
                body.append("            ${entityJavaType} model = (${entityJavaType}) node.${createMethodName}();");
                body.append("            this.${readMethodName}(object, model);");
                body.append("            node.${addMethodName}(name, model);");
                body.append("        }");
                body.append("    });");
                body.append("}");
            } else if (isPrimitive(property) || isPrimitiveList(property) || isPrimitiveMap(property)) {
                readerClassSource.addImport(List.class);
                if (property.getType().isMap()) {
                    readerClassSource.addImport(Map.class);
                }

                body.addContext("valueType", determineValueType(property.getType()));
                body.addContext("consumePropertyMethodName", determineConsumePropertyVariant(property.getType()));

                body.append("{");
                body.append("    List<String> propertyNames = JsonUtil.keys(json);");
                body.append("    propertyNames.forEach(name -> {");
                body.append("        ${valueType} value = JsonUtil.${consumePropertyMethodName}(json, name);");
                body.append("        node.addItem(name, value);");
                body.append("    });");
                body.append("}");
            } else {
                warn("STAR Entity property '" + property.getName() + "' not read (unhandled) for entity: " + entityModel.fullyQualifiedName());
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
                JavaInterfaceSource propertyTypeJavaEntity = getState().getJavaIndex().lookupInterface(getJavaEntityInterfaceFQN(propertyTypeEntity));
                if (propertyTypeJavaEntity == null) {
                    warn("REGEX Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in JAVA index: " + property.getType());
                    return;
                }
                readerClassSource.addImport(propertyTypeJavaEntity);
                readerClassSource.addImport(List.class);

                body.addContext("propertyRegex", encodeRegex(extractRegex(property.getName())));
                body.addContext("entityJavaType", propertyTypeJavaEntity.getName());
                body.addContext("createMethodName", createMethodName(propertyTypeEntity));
                body.addContext("readMethodName", readMethodName(propertyTypeEntity));
                body.addContext("addMethodName", addMethodName(singularize(property.getCollection())));

                body.append("{");
                body.append("    List<String> propertyNames = JsonUtil.matchingKeys(\"${propertyRegex}\", json);");
                body.append("    propertyNames.forEach(name -> {");
                body.append("        ObjectNode object = JsonUtil.consumeObjectProperty(json, name);");
                body.append("        if (object != null) {");
                body.append("            ${entityJavaType} model = (${entityJavaType}) node.${createMethodName}();");
                body.append("            this.${readMethodName}(object, model);");
                body.append("            node.${addMethodName}(name, model);");
                body.append("        }");
                body.append("    });");
                body.append("}");
            } else if (isPrimitive(property) || isPrimitiveList(property) || isPrimitiveMap(property)) {
                readerClassSource.addImport(List.class);
                if (property.getType().isMap()) {
                    readerClassSource.addImport(Map.class);
                }

                body.addContext("propertyRegex", encodeRegex(extractRegex(property.getName())));
                body.addContext("valueType", determineValueType(property.getType()));
                body.addContext("consumeProperty", determineConsumePropertyVariant(property.getType()));
                body.addContext("addMethodName", addMethodName(singularize(property.getCollection())));

                body.append("{");
                body.append("    List<String> propertyNames = JsonUtil.matchingKeys(\"${propertyRegex}\", json);");
                body.append("    propertyNames.forEach(name -> {");
                body.append("        ${valueType} value = JsonUtil.${consumeProperty}(json, name);");
                body.append("        node.${addMethodName}(name, value);");
                body.append("    });");
                body.append("}");
            } else {
                warn("REGEX Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
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
            readerClassSource.addImport(propertyTypeJavaEntity);

            body.addContext("propertyName", property.getName());
            body.addContext("setterMethodName", setterMethodName(property));
            body.addContext("createMethodName", createMethodName(propertyTypeEntity));
            body.addContext("getterMethodName", getterMethodName(property));
            body.addContext("readMethodName", readMethodName(propertyTypeEntity));
            body.addContext("propertyEntityType", propertyTypeJavaEntity.getName());

            body.append("{");
            body.append("    ObjectNode object = JsonUtil.consumeObjectProperty(json, \"${propertyName}\");");
            body.append("    if (object != null) {");
            body.append("        node.${setterMethodName}(node.${createMethodName}());");
            body.append("        ${readMethodName}(object, (${propertyEntityType}) node.${getterMethodName}());");
            body.append("    }");
            body.append("}");
        }

        private void handlePrimitiveTypeProperty(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            body.addContext("valueType", determineValueType(property.getType()));
            body.addContext("consumeProperty", determineConsumePropertyVariant(property.getType()));
            body.addContext("propertyName", property.getName());
            body.addContext("setterMethodName", setterMethodName(property));

            body.append("{");
            body.append("    ${valueType} value = JsonUtil.${consumeProperty}(json, \"${propertyName}\");");
            body.append("    node.${setterMethodName}(value);");
            body.append("}");
        }

        private void handleListProperty(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            body.addContext("propertyName", property.getName());
            body.addContext("setterMethodName", setterMethodName(property));

            PropertyType listValuePropertyType = property.getType().getNested().iterator().next();
            if (listValuePropertyType.isPrimitiveType()) {
                body.addContext("consumeMethodName", determineConsumePropertyVariant(property.getType()));
                body.addContext("propertyValueJavaType", determineValueType(property.getType()));
                readerClassSource.addImport(List.class);

                body.append("{");
                body.append("    ${propertyValueJavaType} value = JsonUtil.${consumeMethodName}(json, \"${propertyName}\");");
                body.append("    node.${setterMethodName}(value);");
                body.append("}");
            } else if (listValuePropertyType.isEntityType()) {
                String entityTypeName = listValuePropertyType.getSimpleType();
                String fqEntityName = entityModel.getNamespace().fullName() + "." + entityTypeName;
                EntityModel entityTypeModel = getState().getConceptIndex().lookupEntity(fqEntityName);
                if (entityTypeModel == null) {
                    warn("LIST Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in index: " + property.getType());
                    return;
                }
                JavaInterfaceSource entityTypeJavaModel = getState().getJavaIndex().lookupInterface(getJavaEntityInterfaceFQN(entityTypeModel));
                if (entityTypeJavaModel == null) {
                    warn("LIST Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in JAVA index: " + property.getType());
                    return;
                }
                readerClassSource.addImport(entityTypeJavaModel);
                readerClassSource.addImport(List.class);

                body.addContext("listValueJavaType", entityTypeJavaModel.getName());
                body.addContext("createMethodName", createMethodName(entityTypeModel));
                body.addContext("readMethodName", readMethodName(entityTypeModel));
                body.addContext("addMethodName", addMethodName(singularize(property.getName())));

                body.append("{");
                body.append("    List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, \"${propertyName}\");");
                body.append("    if (objects != null) {");
                body.append("        objects.forEach(object -> {");
                body.append("            ${listValueJavaType} model = (${listValueJavaType}) node.${createMethodName}();");
                body.append("            this.${readMethodName}(object, model);");
                body.append("            node.${addMethodName}(model);");
                body.append("        });");
                body.append("    }");
                body.append("}");
            } else {
                warn("LIST Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                warn("       property type: " + property.getType());
            }
        }

        private void handleMapProperty(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            body.addContext("propertyName", property.getName());
            body.addContext("setterMethodName", setterMethodName(property));

            PropertyType mapValuePropertyType = property.getType().getNested().iterator().next();
            if (mapValuePropertyType.isPrimitiveType()) {
                body.addContext("consumeMethodName", determineConsumePropertyVariant(property.getType()));
                body.addContext("propertyValueJavaType", determineValueType(property.getType()));
                readerClassSource.addImport(Map.class);

                body.append("{");
                body.append("    ${propertyValueJavaType} value = JsonUtil.${consumeMethodName}(json, \"${propertyName}\");");
                body.append("    node.${setterMethodName}(value);");
                body.append("}");
            } else if (mapValuePropertyType.isEntityType()) {
                String entityTypeName = mapValuePropertyType.getSimpleType();
                String fqEntityName = entityModel.getNamespace().fullName() + "." + entityTypeName;
                EntityModel entityTypeModel = getState().getConceptIndex().lookupEntity(fqEntityName);
                if (entityTypeModel == null) {
                    warn("MAP Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in index: " + property.getType());
                    return;
                }
                JavaInterfaceSource entityTypeJavaModel = getState().getJavaIndex().lookupInterface(getJavaEntityInterfaceFQN(entityTypeModel));
                if (entityTypeJavaModel == null) {
                    warn("MAP Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type is entity but not found in JAVA index: " + property.getType());
                    return;
                }
                readerClassSource.addImport(entityTypeJavaModel);

                body.addContext("mapValueJavaType", entityTypeJavaModel.getName());
                body.addContext("createMethodName", "create" + entityTypeName);
                body.addContext("readMethodName", "read" + entityTypeName);
                body.addContext("addMethodName", addMethodName(singularize(property.getName())));

                body.append("{");
                body.append("    ObjectNode object = JsonUtil.consumeObjectProperty(json, \"${propertyName}\");");
                body.append("    JsonUtil.keys(object).forEach(name -> {");
                body.append("        ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);");
                body.append("        if (mapValue != null) {");
                body.append("            ${mapValueJavaType} model = (${mapValueJavaType}) node.${createMethodName}();");
                body.append("            this.${readMethodName}(mapValue, model);");
                body.append("            node.${addMethodName}(name, model);");
                body.append("        }");
                body.append("    });");
                body.append("}");
            } else {
                warn("MAP Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                warn("       property type: " + property.getType());
            }
        }

        // TODO handle entity maps!
        private void handleUnionProperty(BodyBuilder body) {
            PropertyModel property = propertyWithOrigin.getProperty();
            NamespaceModel nsContext = propertyWithOrigin.getOrigin().getNamespace();
            UnionPropertyType ut = new UnionPropertyType(property.getType());

            readerClassSource.addImport(JsonNode.class);

            body.addContext("unionJavaType", ut.toJavaTypeString());
            body.addContext("propertyName", property.getName());
            body.addContext("getterMethodName", getterMethodName(property));
            body.addContext("setterMethodName", setterMethodName(property));

            body.append("{");
            body.append("    JsonNode value = JsonUtil.consumeAnyProperty(json, \"${propertyName}\");");
            body.append("    if (value != null) {");

            boolean first = true;
            for (PropertyType nestedType : ut.getNestedTypes()) {
                if (!first) {
                    body.append(" else ");
                }
                first = false;
                JavaType jt = new JavaType(nestedType, nsContext);
                if (jt.isPrimitive()) {
                    String javaTypeName = jt.toJavaTypeString();
                    String isMethodName = "is" + javaTypeName;
                    String toMethodName = "to" + javaTypeName;
                    String unionValueInterfaceName = javaTypeName + "UnionValue";
                    String unionValueClassName = unionValueInterfaceName + "Impl";
                    JavaInterfaceSource unionValueInterface = getState().getJavaIndex().lookupInterface(getUnionTypeFQN(unionValueInterfaceName));
                    JavaClassSource unionValueClass = getState().getJavaIndex().lookupClass(getUnionTypeFQN(unionValueClassName));

                    body.addContext("javaTypeName", javaTypeName);
                    body.addContext("isMethodName", isMethodName);
                    body.addContext("toMethodName", toMethodName);
                    body.addContext("unionValueInterfaceName", unionValueInterfaceName);
                    body.addContext("unionValueClassName", unionValueClassName);

                    body.append("if (JsonUtil.${isMethodName}(value)) {");
                    body.append("    ${javaTypeName} pValue = JsonUtil.${toMethodName}(value);");
                    body.append("    ${unionValueInterfaceName} unionValue = new ${unionValueClassName}(pValue);");
                    body.append("    node.${setterMethodName}(unionValue);");
                    body.append("}");

                    readerClassSource.addImport(unionValueInterface);
                    readerClassSource.addImport(unionValueClass);
                } else if (jt.isPrimitiveList()) {
                    String nestedJavaTypeName = getTypeName(nestedType.getNested().iterator().next());
                    String unionValueName = getTypeName(nestedType);
                    String toMethodName = "to" + nestedJavaTypeName;
                    String unionValueInterfaceName = unionValueName + "UnionValue";
                    String unionValueClassName = unionValueInterfaceName + "Impl";
                    JavaInterfaceSource unionValueInterface = getState().getJavaIndex().lookupInterface(getUnionTypeFQN(unionValueInterfaceName));
                    JavaClassSource unionValueClass = getState().getJavaIndex().lookupClass(getUnionTypeFQN(unionValueClassName));

                    if (unionValueInterface == null || unionValueClassName == null) {
                        warn("Missing primitive list Union Value interface or class: " + unionValueName);
                        return;
                    }

                    body.addContext("toMethodName", toMethodName);
                    body.addContext("javaTypeName", nestedJavaTypeName);
                    body.addContext("unionValueInterfaceName", unionValueInterfaceName);
                    body.addContext("unionValueClassName", unionValueClassName);

                    body.append("if (JsonUtil.isArray(value)) {");
                    body.append("    List<JsonNode> array = JsonUtil.toList(value);");
                    body.append("    List<${javaTypeName}> items = new ArrayList<>();");
                    body.append("    array.forEach(item -> {");
                    body.append("        ${javaTypeName} pValue = JsonUtil.${toMethodName}(item);");
                    body.append("        items.add(pValue);");
                    body.append("    });");
                    body.append("    ${unionValueInterfaceName} unionValue = new ${unionValueClassName}(items);");
                    body.append("    node.${setterMethodName}(unionValue);");
                    body.append("}");

                    readerClassSource.addImport(unionValueInterface);
                    readerClassSource.addImport(unionValueClass);
                    readerClassSource.addImport(JsonNode.class);
                    readerClassSource.addImport(List.class);
                    readerClassSource.addImport(ArrayList.class);
                } else if (jt.isEntity()) {
                    NamespaceModel nestedTypeEntityNS = entityModel.getNamespace();
                    String nestedTypeEntityName = nestedTypeEntityNS.fullName() + "." + nestedType.getSimpleType();
                    EntityModel nestedTypeEntity = getState().getConceptIndex().lookupEntity(nestedTypeEntityName);
                    if (nestedTypeEntity == null) {
                        warn("Property union type with entity sub-type not found for property: '" + property.getName() + "' of entity: " + entityModel.fullyQualifiedName());
                        warn("       nested union type: " + nestedType);
                        return;
                    }
                    JavaInterfaceSource entityJavaSource = resolveJavaEntityType(nestedTypeEntityNS, nestedType);
                    if (entityJavaSource == null) {
                        warn("Property union type with entity sub-type not found (in java index) for property: '" + property.getName() + "' of entity: " + entityModel.fullyQualifiedName());
                        warn("       nested union type: " + nestedType);
                        return;
                    }
                    readerClassSource.addImport(entityJavaSource);

                    body.addContext("setterMethodName", setterMethodName(property));
                    body.addContext("createMethodName", createMethodName(nestedTypeEntity));
                    body.addContext("getterMethodName", getterMethodName(property));
                    body.addContext("readMethodName", readMethodName(nestedTypeEntity));
                    body.addContext("propertyEntityType", entityJavaSource.getName());

                    body.append("if (JsonUtil.isObject(value)) {");
                    body.append("    ObjectNode object = JsonUtil.toObject(value);");
                    body.append("    node.${setterMethodName}(node.${createMethodName}());");
                    body.append("    ${readMethodName}(object, (${propertyEntityType}) node.${getterMethodName}());");
                    body.append("}");
                } else if (jt.isEntityList()) {
                    String unionValueName = getTypeName(nestedType);
                    String unionValueInterfaceName = unionValueName + "UnionValue";
                    String unionValueClassName = unionValueInterfaceName + "Impl";
                    JavaInterfaceSource unionValueInterface = getState().getJavaIndex().lookupInterface(getUnionTypeFQN(unionValueInterfaceName));
                    JavaClassSource unionValueClass = getState().getJavaIndex().lookupClass(getUnionTypeFQN(unionValueClassName));
                    if (unionValueInterface == null || unionValueClassName == null) {
                        warn("Missing entity list Union Value interface or class (this should have been generated!): " + unionValueName);
                        return;
                    }

                    PropertyType listItemType = nestedType.getNested().iterator().next();
                    String listItemEntityName = nsContext.fullName() + "." + listItemType.getSimpleType();
                    EntityModel listItemEntity = getState().getConceptIndex().lookupEntity(listItemEntityName);
                    if (listItemEntity == null) {
                        warn("Property union type with entity sub-type not found for property: '" + property.getName() + "' of entity: " + entityModel.fullyQualifiedName());
                        warn("       nested union type: " + nestedType);
                        return;
                    }
                    JavaInterfaceSource listItemEntitySource = getState().getJavaIndex().lookupInterface(getJavaEntityInterfaceFQN(listItemEntity));
                    if (listItemEntitySource == null) {
                        warn("Property union type with entity sub-type not found (in java index) for property: '" + property.getName() + "' of entity: " + entityModel.fullyQualifiedName());
                        warn("       nested union type: " + listItemType);
                        return;
                    }

                    readerClassSource.addImport(listItemEntitySource);
                    readerClassSource.addImport(unionValueInterface);
                    readerClassSource.addImport(unionValueClass);
                    readerClassSource.addImport(JsonNode.class);
                    readerClassSource.addImport(List.class);
                    readerClassSource.addImport(ArrayList.class);

                    body.addContext("unionValueInterfaceName", unionValueInterfaceName);
                    body.addContext("unionValueClassName", unionValueClassName);
                    body.addContext("listValueJavaType", listItemEntitySource.getName());
                    body.addContext("setterMethodName", setterMethodName(property));
                    body.addContext("createMethodName", createMethodName(listItemEntity));
                    body.addContext("getterMethodName", getterMethodName(property));
                    body.addContext("readMethodName", readMethodName(listItemEntity));

                    body.append("if (JsonUtil.isArray(value)) {");
                    body.append("    List<JsonNode> array = JsonUtil.toList(value);");
                    body.append("    List<${listValueJavaType}> models = new ArrayList<>();");
                    body.append("    array.forEach(item -> {");
                    body.append("        ObjectNode object = JsonUtil.toObject(item);");
                    body.append("        ${listValueJavaType} model = node.${createMethodName}();");
                    body.append("        this.${readMethodName}(object, model);");
                    body.append("        models.add(model);");
                    body.append("    });");
                    body.append("    @SuppressWarnings({ \"unchecked\", \"rawtypes\" })");
                    body.append("    ${unionValueInterfaceName} unionValue = new ${unionValueClassName}((List) models);");
                    body.append("    node.${setterMethodName}(unionValue);");
                    body.append("}");
                } else {
                    // TODO implement handling for entity maps
                    warn("UNION Entity property '" + property.getName() + "' not read (unsupported union subtype) for entity: " + entityModel.fullyQualifiedName());
                    warn("       property type: " + property.getType());
                    body.append("if (Boolean.TRUE) {}");
                }
            }
            body.append("        else {");
            body.append("            node.addExtraProperty(\"${propertyName}\", value);");
            body.append("        }");
            body.append("    }");
            body.append("}");
        }

        /**
         * Figure out which variant of "consumeProperty" from "JsonUtil" we should use for
         * this property.  The property might be a primitive type, or a list/map of primitive
         * types, or an Entity type, or a list/map of Entity types.
         *
         * @param type
         */
        private String determineConsumePropertyVariant(PropertyType type) {
            if (type.isEntityType()) {
                return "consumeObjectProperty";
            }

            if (type.isPrimitiveType()) {
                Class<?> _class = primitiveTypeToClass(type);
                if (ObjectNode.class.equals(_class)) {
                    readerClassSource.addImport(_class);
                    return "consumeObjectProperty";
                } else if (JsonNode.class.equals(_class)) {
                    readerClassSource.addImport(_class);
                    return "consumeAnyProperty";
                } else {
                    return "consume" + _class.getSimpleName() + "Property";
                }
            }

            if (type.isList()) {
                PropertyType listType = type.getNested().iterator().next();
                if (listType.isPrimitiveType()) {
                    Class<?> _class = primitiveTypeToClass(listType);
                    if (ObjectNode.class.equals(_class)) {
                        readerClassSource.addImport(_class);
                        return "consumeObjectArrayProperty";
                    } else if (JsonNode.class.equals(_class)) {
                        readerClassSource.addImport(_class);
                        return "consumeAnyArrayProperty";
                    } else {
                        return "consume" + _class.getSimpleName() + "ArrayProperty";
                    }
                }
            }

            if (type.isMap()) {
                PropertyType mapType = type.getNested().iterator().next();
                if (mapType.isPrimitiveType()) {
                    Class<?> _class = primitiveTypeToClass(mapType);
                    if (ObjectNode.class.equals(_class)) {
                        readerClassSource.addImport(_class);
                        return "consumeObjectMapProperty";
                    } else if (JsonNode.class.equals(_class)) {
                        readerClassSource.addImport(_class);
                        return "consumeAnyMapProperty";
                    } else {
                        return "consume" + _class.getSimpleName() + "MapProperty";
                    }
                }
            }

            PropertyModel property = propertyWithOrigin.getProperty();
            warn("Unable to determine value type for: " + property);
            return "consumeProperty";
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
                    readerClassSource.addImport(_class);
                    return _class.getSimpleName();
                }
            }

            if (type.isList()) {
                PropertyType listType = type.getNested().iterator().next();
                if (listType.isPrimitiveType()) {
                    Class<?> _class = primitiveTypeToClass(listType);
                    if (_class != null) {
                        readerClassSource.addImport(_class);
                        return "List<" + _class.getSimpleName() + ">";
                    }
                }
            }

            if (type.isMap()) {
                PropertyType mapType = type.getNested().iterator().next();
                if (mapType.isPrimitiveType()) {
                    Class<?> _class = primitiveTypeToClass(mapType);
                    if (_class != null) {
                        readerClassSource.addImport(_class);
                        return "Map<String, " + _class.getSimpleName() + ">";
                    }
                }
            }

            PropertyModel property = propertyWithOrigin.getProperty();
            warn("Unable to determine value type for: " + property);
            return "Object";
        }

        private String encodeRegex(String regex) {
            return regex.replace("\\", "\\\\");
        }
    }
}
