package io.apicurio.umg.pipe.java;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyType;

/**
 * Creates methods on all entity implementation classes.  This follows the same algorithm
 * as {@link CreateInterfaceMethodsStage} except it rolls up all of the methods from the
 * entire entity and trait hierarchies so that the Impl class can implement them.
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateImplMethodsStage extends AbstractJavaStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findEntities("").stream().filter(entity -> entity.isLeaf()).forEach(entity -> {
            createEntityImplMethods(entity);
        });
    }

    private void createEntityImplMethods(EntityModel entity) {
        Collection<PropertyModel> allProperties = getState().getConceptIndex().getAllEntityProperties(entity);
        allProperties.forEach(property -> {
            createPropertyMethods(lookupEntityImpl(entity), property);
        });
    }

    private void createPropertyMethods(JavaClassSource javaEntity, PropertyModel property) {
        if (property.getName().equals("*")) {
            if (isEntity(property) || isPrimitive(property)) {
                createMappedNodeMethods(javaEntity, property);
                if (isEntity(property)) {
                    createFactoryMethod(javaEntity, property);
                }
            } else {
                Logger.error("[CreateInterfaceMethodsStage] STAR property type not handled: " + javaEntity.getCanonicalName() + "::" + property);
                return;
            }
        } else if (property.getName().startsWith("/") && (isEntity(property) || isPrimitive(property))) {
            if (property.getCollection() == null) {
                Logger.error("[CreateInterfaceMethodsStage] Regex property defined without a collection name: " + javaEntity.getCanonicalName() + "::" + property);
                return;
            }
            PropertyType collectionPropertyType = PropertyType.builder()
                    .nested(Collections.singleton(property.getType()))
                    .map(true)
                    .build();
            PropertyModel collectionProperty = PropertyModel.builder().name(property.getCollection()).type(collectionPropertyType).build();

            if (isEntity(property)) {
                createFactoryMethod(javaEntity, collectionProperty);
            }
            createGetter(javaEntity, collectionProperty);
            createAddMethod(javaEntity, collectionProperty);
            createClearMethod(javaEntity, collectionProperty);
            createRemoveMethod(javaEntity, collectionProperty);
        } else if (isPrimitive(property) || isPrimitiveList(property) || isPrimitiveMap(property)) {
            createGetter(javaEntity, property);
            createSetter(javaEntity, property);
        } else if (isEntity(property)) {
            createGetter(javaEntity, property);
            createSetter(javaEntity, property);
            createFactoryMethod(javaEntity, property);
        } else if (isEntityList(property) || isEntityMap(property)) {
            createFactoryMethod(javaEntity, property);
            createGetter(javaEntity, property);
            createAddMethod(javaEntity, property);
            createClearMethod(javaEntity, property);
            createRemoveMethod(javaEntity, property);
        } else {
            Logger.warn("[CreateImplMethodsStage] Failed to create methods (not yet implemented) for property '" + property.getName() + "' of entity: " + javaEntity.getQualifiedName());
        }
    }

    /**
     * When an entity has a "*" property, that means the entity is a wrapper around a map
     * of values of a particular type.  In this case, the entity interface needs to extend
     * the "MappedNode" interface.
     * @param javaEntity
     * @param property
     */
    private void createMappedNodeMethods(JavaClassSource javaEntity, PropertyModel property) {
        //        String mappedNodeFQN = getMappedNodeInterfaceFQN();
        //        JavaInterfaceSource mappedNodeInterface = getState().getJavaIndex().lookupInterface(mappedNodeFQN);
        //
        //        javaEntity.addImport(mappedNodeInterface);
        //        String mappedNodeInterfaceWithType;
        //
        //        if (isPrimitiveList(property)) {
        //            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
        //            javaEntity.addImport(List.class);
        //            javaEntity.addImport(listType);
        //            String mappedType = "List<" + listType.getSimpleName() + ">";
        //            mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + mappedType + ">";
        //        } else if (isPrimitiveMap(property)) {
        //            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
        //            javaEntity.addImport(Map.class);
        //            javaEntity.addImport(mapType);
        //            String mappedType = "Map<String, " + mapType.getSimpleName() + ">";
        //            mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + mappedType + ">";
        //        } else if (isPrimitive(property)) {
        //            Class<?> primType = primitiveTypeToClass(property.getType());
        //            javaEntity.addImport(primType);
        //            mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + primType.getSimpleName() + ">";
        //        } else if (isEntity(property)) {
        //            JavaInterfaceSource entityType = resolveEntityType(javaEntity.getPackage(), property);
        //            if (entityType == null) {
        //                Logger.error("Java interface for entity type not found: " + property.getType());
        //                return;
        //            } else {
        //                javaEntity.addImport(entityType);
        //                mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + entityType.getName() + ">";
        //            }
        //        } else {
        //            Logger.error("Unhandled STAR property from entity: " + javaEntity.getCanonicalName());
        //            return;
        //        }
        //
        //        javaEntity.addInterface(mappedNodeInterfaceWithType);
    }

    /**
     * Creates a standard java getter method for the given property.
     * @param javaEntity
     * @param property
     */
    private void createGetter(JavaClassSource javaEntity, PropertyModel property) {
        MethodSource<JavaClassSource> method = javaEntity.addMethod().setName(getterMethodName(property)).setPublic();
        method.addAnnotation(Override.class);
        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(List.class);
            javaEntity.addImport(listType);
            method.setReturnType("List<" + listType.getSimpleName() + ">");
            method.setBody("return ");
        } else if (isPrimitiveMap(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(Map.class);
            javaEntity.addImport(listType);
            method.setReturnType("Map<String, " + listType.getSimpleName() + ">");
        } else if (isPrimitive(property)) {
            Class<?> returnType = primitiveTypeToClass(property.getType());
            javaEntity.addImport(returnType);
            method.setReturnType(returnType.getSimpleName());
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(javaEntity.getPackage(), property);
            if (entityType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntity.addImport(entityType);
                method.setReturnType(entityType.getName());
            }
        } else if (isEntityList(property)) {
            JavaInterfaceSource listType = resolveEntityType(javaEntity.getPackage(), property.getType().getNested().iterator().next());
            if (listType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntity.addImport(List.class);
                javaEntity.addImport(listType);
                method.setReturnType("List<" + listType.getName() + ">");
            }
        } else if (isEntityMap(property)) {
            JavaInterfaceSource mapType = resolveEntityType(javaEntity.getPackage(), property.getType().getNested().iterator().next());
            if (mapType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntity.addImport(Map.class);
                javaEntity.addImport(mapType);
                method.setReturnType("Map<String, " + mapType.getName() + ">");
            }
        } else {
            Logger.warn("[CreateInterfaceMethodsStage] Return type not supported for getter method: " + method.getName() + " for type: " + property.getType());
        }
    }

    /**
     * Creates a standard java setter method for the given property.
     * @param javaEntity
     * @param property
     */
    private void createSetter(JavaClassSource javaEntity, PropertyModel property) {
        MethodSource<JavaClassSource> method = javaEntity.addMethod().setName(setterMethodName(property)).setReturnTypeVoid().setPublic();
        method.addAnnotation(Override.class);
        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(List.class);
            javaEntity.addImport(listType);
            method.addParameter("List<" + listType.getSimpleName() + ">", "value");
        } else if (isPrimitiveMap(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(Map.class);
            javaEntity.addImport(listType);
            method.addParameter("Map<String, " + listType.getSimpleName() + ">", "value");
        } else if (isPrimitive(property)) {
            Class<?> paramType = primitiveTypeToClass(property.getType());
            javaEntity.addImport(paramType);
            method.addParameter(paramType.getSimpleName(), "value");
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(javaEntity.getPackage(), property);
            if (entityType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntity.addImport(entityType);
                method.addParameter(entityType.getName(), "value");
            }
        } else {
            Logger.warn("[CreateInterfaceMethodsStage] Parameter type not supported for setter method: " + method.getName() + " for type: " + property.getType());
        }
    }

    /**
     * Creates a factory method for the entity type associated with the given
     * property.  This method will only be called for entity properties, either
     * simple entity properties or collection entity properties (list/map).
     * @param javaEntity
     * @param property
     */
    private void createFactoryMethod(JavaClassSource javaEntity, PropertyModel property) {
        String _package = javaEntity.getPackage();
        PropertyType type = property.getType();
        if (type.isMap() || type.isList()) {
            type = type.getNested().iterator().next();
        }
        String methodName = createMethodName(type.getSimpleType());
        // The name of the "create" method is based on the type, so it's possible to have
        // duplicates.  Let's not do that.
        if (!hasNamedMethod(javaEntity, methodName)) {
            JavaInterfaceSource entityType = resolveEntityType(_package, type);
            if (entityType == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + _package + "::" + type);
                return;
            }
            MethodSource<JavaClassSource> method = javaEntity.addMethod().setPublic().setName(methodName).setReturnType(entityType);
            method.addAnnotation(Override.class);
        }
    }

    /**
     * Creates an "add" method for the given property.  The type of the property must be a
     * list of entities.  The add method would accept a single entity and add it to the list.
     * @param javaEntity
     * @param property
     */
    private void createAddMethod(JavaClassSource javaEntity, PropertyModel property) {
        String _package = javaEntity.getPackage();
        PropertyType type = property.getType().getNested().iterator().next();
        String methodName = addMethodName(singularize(property.getName()));
        if (type.isEntityType()) {
            JavaInterfaceSource entityType = resolveEntityType(_package, type);
            if (entityType == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + _package + "::" + type);
                return;
            }

            javaEntity.addImport(entityType);

            MethodSource<JavaClassSource> method = javaEntity.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
            method.addAnnotation(Override.class);
            if (property.getType().isMap()) {
                method.addParameter("String", "name");
            }
            method.addParameter(entityType.getName(), "value");
        } else if (type.isPrimitiveType()) {
            Class<?> primitiveType = primitiveTypeToClass(type);
            javaEntity.addImport(primitiveType);

            MethodSource<JavaClassSource> method = javaEntity.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
            method.addAnnotation(Override.class);
            if (property.getType().isMap()) {
                method.addParameter("String", "name");
            }
            method.addParameter(primitiveType.getSimpleName(), "value");
        } else {
            Logger.warn("[CreateInterfaceMethodsStage] Type not supported for 'add' method: " + methodName + " with type: " + property.getType());
        }
    }

    /**
     * Creates a "clear" method for the given property.  The type of the property must be a
     * list of entities.  The clear method will remove all items from the list.
     * @param javaEntity
     * @param property
     */
    private void createClearMethod(JavaClassSource javaEntity, PropertyModel property) {
        String methodName = clearMethodName(property.getName());

        MethodSource<JavaClassSource> method = javaEntity.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
        method.addAnnotation(Override.class);
    }

    /**
     * Creates a "remove" method for the given property.  The type of the property must be a
     * list of entities.  The remove method will remove one item from the list.
     * @param entity
     * @param property
     */
    private void createRemoveMethod(JavaClassSource javaEntity, PropertyModel property) {
        String methodName = removeMethodName(singularize(property.getName()));
        MethodSource<JavaClassSource> method = javaEntity.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
        method.addAnnotation(Override.class);

        if (property.getType().isList()) {
            PropertyType type = property.getType().getNested().iterator().next();
            JavaInterfaceSource entityType = resolveEntityType(javaEntity.getPackage(), type);
            if (entityType == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + javaEntity.getPackage() + "::" + type);
                return;
            }
            javaEntity.addImport(entityType);
            method.addParameter(entityType.getName(), "value");
        } else {
            method.addParameter("String", "name");
        }
    }

}
