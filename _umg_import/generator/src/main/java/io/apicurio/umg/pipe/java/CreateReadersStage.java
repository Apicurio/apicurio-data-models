package io.apicurio.umg.pipe.java;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Method;
import org.jboss.forge.roaster.model.MethodHolder;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.umg.beans.SpecificationVersion;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyType;
import io.apicurio.umg.models.concept.TraitModel;
import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaEntityModel;
import io.apicurio.umg.models.java.JavaPackageModel;
import io.apicurio.umg.pipe.AbstractStage;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Creates the i/o reader classes.  There is a bespoke reader for each specification
 * version.
 * @author eric.wittmann@gmail.com
 */
public class CreateReadersStage extends AbstractStage {

    @Override
    protected void doProcess() {
        getState().getSpecIndex().getAllSpecificationVersions().forEach(specVersion -> {
            String readerPackageName = specVersion.getNamespace() + ".io";
            String readerClassName = specVersion.getPrefix() + "ModelReader";
            // Create the package for the reader
            JavaPackageModel readerPackage = getState().getJavaIndex().lookupAndIndexPackage(() -> {
                JavaPackageModel parentPackage = getState().getJavaIndex().lookupPackage(specVersion.getNamespace());
                JavaPackageModel packageModel = JavaPackageModel.builder()
                        .name(readerPackageName)
                        .parent(parentPackage)
                        .build();
                return packageModel;
            });

            // Create the reader class model
            JavaClassModel readerClass = JavaClassModel.builder()
                    ._package(readerPackage)
                    ._abstract(false)
                    .name(readerClassName)
                    .build();

            // Create java source code for the reader
            JavaClassSource readerClassSource = Roaster.create(JavaClassSource.class)
                    .setPackage(readerClass.get_package().getName())
                    .setName(readerClass.getName())
                    .setAbstract(readerClass.is_abstract())
                    .setPublic();
            readerClassSource.addImport(getState().getConfig().getRootNamespace() + ".util." + "JsonUtil");

            // Create the readXYZ methods - one for each entity
            createReadMethods(specVersion, readerClassSource);

            readerClass.setClassSource(readerClassSource);
            getState().getJavaIndex().addClass(readerClass);
        });
    }

    /**
     * Creates a "read" method for each entity in the spec version.
     * @param specVersion
     * @param readerClassSource
     */
    private void createReadMethods(SpecificationVersion specVersion, JavaClassSource readerClassSource) {
        specVersion.getEntities().forEach(entity -> {
            EntityModel entityModel = getState().getConceptIndex().lookupEntity(specVersion.getNamespace() + "." + entity.getName());
            if (entityModel == null) {
                Logger.warn("[CreateReadersStage] Entity model not found for entity: " + entity);
            } else {
                createReadMethodFor(specVersion, readerClassSource, entityModel);
            }
        });
    }

    /**
     * Creates a single "readXyx" method for the given entity.
     * @param specVersion
     * @param readerClassSource
     * @param entityModel
     */
    private void createReadMethodFor(SpecificationVersion specVersion, JavaClassSource readerClassSource, EntityModel entityModel) {
        String readMethodName = readMethodName(entityModel);

        JavaEntityModel javaEntityModel = getState().getJavaIndex().lookupType(entityModel);
        if (javaEntityModel == null) {
            Logger.warn("[CreateReadersStage] Java entity not found for: " + entityModel.fullyQualifiedName());
            return;
        }

        MethodSource<JavaClassSource> methodSource = readerClassSource.addMethod()
                .setName(readMethodName)
                .setReturnTypeVoid()
                .setPublic();
        methodSource.addParameter(ObjectNode.class, "json");
        methodSource.addParameter(javaEntityModel.getJavaSource().getQualifiedName(), "node");

        // Now create the body content for the reader.
        BodyBuilder body = new BodyBuilder();
        // Read each property of the entity
        Collection<PropertyModel> allProperties = getAllPropertiesFor(entityModel);
        allProperties.forEach(property -> {
            createReadPropertyCode(body, property, entityModel, javaEntityModel, readerClassSource);
        });
        // Read "extra" properties (whatever is left over)
        // TODO read extra properties

        methodSource.setBody(body.toString());
    }

    /**
     * Generates the right java code for reading a single property of an entity.
     * @param body
     * @param property
     * @param javaEntityModel
     * @param javaEntityModel
     * @param readerClassSource
     */
    private void createReadPropertyCode(BodyBuilder body, PropertyModel property, EntityModel entityModel,
            JavaEntityModel javaEntityModel, JavaClassSource readerClassSource) {
        CreateReadProperty crp = new CreateReadProperty(property, entityModel, javaEntityModel, readerClassSource);
        crp.writeTo(body);
    }

    /**
     * Gets a list of all properties for the given entity.  This includes any inherited properties.
     * @param entityModel
     */
    private Collection<PropertyModel> getAllPropertiesFor(EntityModel entityModel) {
        EntityModel model = entityModel;
        Set<PropertyModel> models = new HashSet<>();
        while (model != null) {
            models.addAll(model.getProperties().values());
            model = model.getParent();
        }
        return models;
    }

    private static String readMethodName(EntityModel entityModel) {
        return "read" + entityModel.getName();
    }

    private static String createMethodName(EntityModel entityModel) {
        return "create" + entityModel.getName();
    }

    @Data
    @AllArgsConstructor
    private class CreateReadProperty {
        PropertyModel property;
        EntityModel entityModel;
        JavaEntityModel javaEntityModel;
        JavaClassSource readerClassSource;

        /**
         * Generates code to read a property from a JSON node into the data model.
         * @param body
         */
        public void writeTo(BodyBuilder body) {
            if (property.getType().isSimple() && !"*".equals(property.getName()) && !property.getName().startsWith("/")) {
                handleSimpleType(body);
            } else if (property.getType().isSimple() && "*".equals(property.getName())) {
                /* Handle mapped properties */
                handleStarProperties(body);
            } else if (property.getType().isSimple() && property.getName().startsWith("/")) {
                /* Handle regex properties */
                handleRegexProperties(body);
            } else if (property.getType().isList()) {
                /* Handle List property */
                handleListProperty(body);
            } else if (property.getType().isMap()) {
                /* Handle map property */
                handleMapProperty(body);
            } else if (property.getType().isUnion()) {
                /* Handle union property */
                handleUnionProperty(body);
            } else {
                Logger.warn("[CreateReadersStage] Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                Logger.warn("[CreateReadersStage]        property type: " + property.getType());
            }
        }

        private void handleSimpleType(BodyBuilder body) {
            body.addContext("valueType", determineValueType());
            body.addContext("consumeProperty", determineConsumePropertyVariant());
            body.addContext("propertyName", property.getName());
            body.addContext("setterName", Util.fieldSetter(property));
            body.addContext("getterName", Util.fieldGetter(property));

            body.append("{");
            body.append("    ${valueType} value = JsonUtil.${consumeProperty}(json, \"${propertyName}\");");
            if (property.getType().isPrimitiveType()) {
                body.append("    node.${setterName}(value);");
            } else {
                String propertyTypeEntityName = entityModel.getNamespace().fullName() + "." + property.getType().getSimpleType();
                EntityModel propertyTypeEntity = getState().getConceptIndex().lookupEntity(propertyTypeEntityName);
                if (propertyTypeEntity == null) {
                    Logger.warn("[CreateReadersStage] Property entity type not found for property: '" + property.getName() + "' of entity: " + entityModel.fullyQualifiedName());
                } else {
                    body.addContext("createMethodName", createMethodName(propertyTypeEntity));
                    body.addContext("readMethodName", readMethodName(propertyTypeEntity));

                    body.append("    if (value != null) {");
                    body.append("        node.${setterName}(node.${createMethodName}());");
                    body.append("        ${readMethodName}(value, node.${getterName}());");
                    body.append("    }");
                }
            }
            body.append("}");
        }

        private void handleStarProperties(BodyBuilder body) {
            // TODO Auto-generated method stub
            Logger.warn("[CreateReadersStage] '*' Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
            Logger.warn("[CreateReadersStage]        property type: " + property.getType());
        }

        private void handleRegexProperties(BodyBuilder body) {
            // TODO Auto-generated method stub
            Logger.warn("[CreateReadersStage] REGEX Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
            Logger.warn("[CreateReadersStage]        property type: " + property.getType());
        }

        private void handleListProperty(BodyBuilder body) {
            // TODO Auto-generated method stub
            Logger.warn("[CreateReadersStage] LIST Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
            Logger.warn("[CreateReadersStage]        property type: " + property.getType());
        }

        private void handleMapProperty(BodyBuilder body) {
            body.addContext("propertyName", property.getName());
            body.addContext("setterName", Util.fieldSetter(property));

            PropertyType mapValuePropertyType = property.getType().getNested().iterator().next();
            if (mapValuePropertyType.getSimpleType().equals("string")) {
                body.append("{");
                body.append("    Map<String, String> value = JsonUtil.consumeStringMapProperty(json, \"${propertyName}\");");
                body.append("    node.${setterName}(value);");
                body.append("}");
            } else if (mapValuePropertyType.getSimpleType().equals("any")) {
                body.append("{");
                body.append("    Map<String, JsonNode> value = JsonUtil.consumeAnyMapProperty(json, \"${propertyName}\");");
                body.append("    node.${setterName}(value);");
                body.append("}");
            } else if (mapValuePropertyType.isEntityType()) {
                String entityTypeName = mapValuePropertyType.getSimpleType();
                String fqEntityName = entityModel.getNamespace().fullName() + "." + entityTypeName;
                EntityModel entityTypeModel = getState().getConceptIndex().lookupEntity(fqEntityName);
                if (entityTypeModel == null) {
                    Logger.warn("[CreateReadersStage] MAP Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    Logger.warn("[CreateReadersStage]        property type is entity but not found in index: " + property.getType());
                    return;
                }
                JavaEntityModel entityTypeJavaModel = getState().getJavaIndex().lookupType(entityTypeModel);
                if (entityTypeJavaModel == null) {
                    Logger.warn("[CreateReadersStage] MAP Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                    Logger.warn("[CreateReadersStage]        property type is entity but not found in JAVA index: " + property.getType());
                    return;
                }
                readerClassSource.addImport(entityTypeJavaModel.fullyQualifiedName());

                body.addContext("objectName", "object");
                body.addContext("mapValueJavaType", entityTypeJavaModel.getName());
                body.addContext("createMethodName", "create" + entityTypeName);
                body.addContext("readMethodName", "read" + entityTypeName);
                body.addContext("addMethodName", "add" + entityTypeName);

                body.append("{");
                body.append("    ObjectNode ${objectName} = JsonUtil.consumeJsonProperty(json, \"${propertyName}\");");
                body.append("    JsonUtil.keys(${objectName}).forEach(name -> {");
                body.append("        ObjectNode mapValue = JsonUtil.consumeJsonProperty(${objectName}, name);");
                body.append("        ${mapValueJavaType} model = node.${createMethodName}(name);");
                body.append("        this.${readMethodName}(mapValue, model);");
                body.append("        node.${addMethodName}(name, model);");
                body.append("    });");
                body.append("}");
            } else {
                Logger.warn("[CreateReadersStage] MAP Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
                Logger.warn("[CreateReadersStage]        property type: " + property.getType());
            }
        }

        private void handleUnionProperty(BodyBuilder body) {
            // TODO Auto-generated method stub
            Logger.warn("[CreateReadersStage] UNION Entity property '" + property.getName() + "' not read (unsupported) for entity: " + entityModel.fullyQualifiedName());
            Logger.warn("[CreateReadersStage]        property type: " + property.getType());

        }

        /**
         * Figure out which variant of "consumeProperty" from "JsonUtil" we should use for
         * this property.  The property might be a primitive type, or a list/map of primitive
         * types, or an Entity type, or a list/map of Entity types.
         */
        private String determineConsumePropertyVariant() {
            if (property.getType().isSimple()) {
                if (property.getType().isPrimitiveType()) {
                    Method<?, ?> method = findGetterMethod();
                    if (method == null) {
                        Logger.warn("[CreateReadersStage] Getter not found for property: " + property + " for: " + javaEntityModel);
                        return null;
                    }
                    Type<?> returnType = method.getReturnType();
                    if (returnType.toString().equals("ObjectNode")) {
                        return "consumeJsonProperty";
                    } else {
                        return "consume" + returnType.toString() + "Property";
                    }
                } else {
                    return "consumeJsonProperty";
                }
            }
            Logger.warn("[CreateReadersStage] Unable to determine value type for: " + property);
            return "consumeProperty";
        }

        /**
         * Determines the Java data type of the given property.
         */
        private String determineValueType() {
            if (property.getType().isSimple()) {
                if (property.getType().isPrimitiveType()) {
                    Method<?, ?> method = findGetterMethod();
                    if (method == null) {
                        Logger.warn("[CreateReadersStage] Getter not found for property: " + property + " for: " + javaEntityModel);
                        return "Object";
                    }
                    Type<?> returnType = method.getReturnType();
                    return returnType.toString();
                } else {
                    return "ObjectNode";
                }
            }
            Logger.warn("[CreateReadersStage] Unable to determine value type for: " + property);
            return "Object";
        }

        /**
         * Finds the getter for the given property.  Searches up the java class/interface hierarchy for the
         * getter method.
         * @param property
         * @param javaEntityModel
         */
        private Method<?, ?> findGetterMethod() {
            String getterName = Util.fieldGetter(property);
            Collection<EntityModel> entities = new HashSet<>();
            Collection<TraitModel> traits = new HashSet<>();

            EntityModel model = entityModel;
            while (model != null) {
                entities.add(model);
                for (TraitModel trait : model.getTraits()) {
                    TraitModel t = trait;
                    while (t != null) {
                        traits.add(t);
                        t = t.getParent();
                    }
                }
                model = model.getParent();
            }

            // Check for getter in all entities
            for (EntityModel entity : entities) {
                JavaEntityModel javaModel = getState().getJavaIndex().lookupType(entity);
                MethodHolder<?> source = (MethodHolder<?>) javaModel.getJavaSource();
                Method<?, ?> method = source.getMethod(getterName);
                if (method != null) {
                    return method;
                }
            }

            // Check for getter in all traits
            for (TraitModel trait : traits) {
                JavaEntityModel javaModel = getState().getJavaIndex().lookupType(trait);
                MethodHolder<?> source = (MethodHolder<?>) javaModel.getJavaSource();
                Method<?, ?> method = source.getMethod(getterName);
                if (method != null) {
                    return method;
                }
            }

            return null;
        }
    }
}
