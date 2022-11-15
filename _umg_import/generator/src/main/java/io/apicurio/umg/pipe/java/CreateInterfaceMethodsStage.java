package io.apicurio.umg.pipe.java;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyType;
import io.apicurio.umg.models.concept.TraitModel;

/**
 * Adds methods to all entity interfaces.  This works by finding all the properties for the
 * entity and then deciding what methods should exist on the entity interface based on the
 * name and type of the property.
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateInterfaceMethodsStage extends AbstractJavaStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findEntities("").forEach(entity -> {
            createEntityInterfaceMethods(entity);
        });
        getState().getConceptIndex().findTraits("").forEach(trait -> {
            createTraitInterfaceMethods(trait);
        });
    }

    private void createEntityInterfaceMethods(EntityModel entity) {
        entity.getProperties().values().forEach(property -> {
            createPropertyMethods(lookupEntity(entity), property);
        });
    }

    private void createTraitInterfaceMethods(TraitModel trait) {
        trait.getProperties().values().forEach(property -> {
            createPropertyMethods(lookupTrait(trait), property);
        });
    }

    private void createPropertyMethods(JavaInterfaceSource javaEntityOrTrait, PropertyModel property) {
        if (property.getName().equals("*")) {
            if (isEntity(property) || isPrimitive(property)) {
                addMappedNodeInterface(javaEntityOrTrait, property);
                if (isEntity(property)) {
                    createFactoryMethod(javaEntityOrTrait, property);
                }
            } else {
                Logger.error("[CreateInterfaceMethodsStage] STAR property type not handled: " + javaEntityOrTrait.getCanonicalName() + "::" + property);
                return;
            }
        } else if (property.getName().startsWith("/") && (isEntity(property) || isPrimitive(property))) {
            if (property.getCollection() == null) {
                Logger.error("[CreateInterfaceMethodsStage] Regex property defined without a collection name: " + javaEntityOrTrait.getCanonicalName() + "::" + property);
                return;
            }
            PropertyType collectionPropertyType = PropertyType.builder()
                    .nested(Collections.singleton(property.getType()))
                    .map(true)
                    .build();
            PropertyModel collectionProperty = PropertyModel.builder().name(property.getCollection()).type(collectionPropertyType).build();

            if (isEntity(property)) {
                createFactoryMethod(javaEntityOrTrait, collectionProperty);
            }
            createGetter(javaEntityOrTrait, collectionProperty);
            createAddMethod(javaEntityOrTrait, collectionProperty);
            createClearMethod(javaEntityOrTrait, collectionProperty);
            createRemoveMethod(javaEntityOrTrait, collectionProperty);
        } else if (isPrimitive(property) || isPrimitiveList(property) || isPrimitiveMap(property)) {
            createGetter(javaEntityOrTrait, property);
            createSetter(javaEntityOrTrait, property);
        } else if (isEntity(property)) {
            createGetter(javaEntityOrTrait, property);
            createSetter(javaEntityOrTrait, property);
            createFactoryMethod(javaEntityOrTrait, property);
        } else if (isEntityList(property) || isEntityMap(property)) {
            createFactoryMethod(javaEntityOrTrait, property);
            createGetter(javaEntityOrTrait, property);
            createAddMethod(javaEntityOrTrait, property);
            createClearMethod(javaEntityOrTrait, property);
            createRemoveMethod(javaEntityOrTrait, property);
        } else {
            Logger.warn("[CreateInterfaceMethodsStage] Failed to create methods (not yet implemented) for property '" + property.getName() + "' of entity: " + javaEntityOrTrait.getQualifiedName());
        }
    }

    /**
     * When an entity has a "*" property, that means the entity is a wrapper around a map
     * of values of a particular type.  In this case, the entity interface needs to extend
     * the "MappedNode" interface.
     * @param javaEntityOrTrait
     * @param property
     */
    private void addMappedNodeInterface(JavaInterfaceSource javaEntityOrTrait, PropertyModel property) {
        String mappedNodeFQN = getMappedNodeInterfaceFQN();
        JavaInterfaceSource mappedNodeInterface = getState().getJavaIndex().lookupInterface(mappedNodeFQN);

        javaEntityOrTrait.addImport(mappedNodeInterface);
        String mappedNodeInterfaceWithType;

        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntityOrTrait.addImport(List.class);
            javaEntityOrTrait.addImport(listType);
            String mappedType = "List<" + listType.getSimpleName() + ">";
            mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + mappedType + ">";
        } else if (isPrimitiveMap(property)) {
            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntityOrTrait.addImport(Map.class);
            javaEntityOrTrait.addImport(mapType);
            String mappedType = "Map<String, " + mapType.getSimpleName() + ">";
            mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + mappedType + ">";
        } else if (isPrimitive(property)) {
            Class<?> primType = primitiveTypeToClass(property.getType());
            javaEntityOrTrait.addImport(primType);
            mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + primType.getSimpleName() + ">";
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(javaEntityOrTrait.getPackage(), property);
            if (entityType == null) {
                Logger.error("Java interface for entity type not found: " + property.getType());
                return;
            } else {
                javaEntityOrTrait.addImport(entityType);
                mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + entityType.getName() + ">";
            }
        } else {
            Logger.error("Unhandled STAR property from entity: " + javaEntityOrTrait.getCanonicalName());
            return;
        }

        javaEntityOrTrait.addInterface(mappedNodeInterfaceWithType);
    }

    /**
     * Creates a standard java getter method for the given property.
     * @param javaEntityOrTrait
     * @param property
     */
    private void createGetter(JavaInterfaceSource javaEntityOrTrait, PropertyModel property) {
        MethodSource<JavaInterfaceSource> method = javaEntityOrTrait.addMethod().setName(getterMethodName(property)).setPublic();
        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntityOrTrait.addImport(List.class);
            javaEntityOrTrait.addImport(listType);
            method.setReturnType("List<" + listType.getSimpleName() + ">");
        } else if (isPrimitiveMap(property)) {
            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntityOrTrait.addImport(Map.class);
            javaEntityOrTrait.addImport(mapType);
            method.setReturnType("Map<String, " + mapType.getSimpleName() + ">");
        } else if (isPrimitive(property)) {
            Class<?> returnType = primitiveTypeToClass(property.getType());
            javaEntityOrTrait.addImport(returnType);
            method.setReturnType(returnType.getSimpleName());
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(javaEntityOrTrait.getPackage(), property);
            if (entityType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntityOrTrait.addImport(entityType);
                method.setReturnType(entityType.getName());
            }
        } else if (isEntityList(property)) {
            JavaInterfaceSource listType = resolveEntityType(javaEntityOrTrait.getPackage(), property.getType().getNested().iterator().next());
            if (listType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntityOrTrait.addImport(List.class);
                javaEntityOrTrait.addImport(listType);
                method.setReturnType("List<" + listType.getName() + ">");
            }
        } else if (isEntityMap(property)) {
            JavaInterfaceSource mapType = resolveEntityType(javaEntityOrTrait.getPackage(), property.getType().getNested().iterator().next());
            if (mapType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntityOrTrait.addImport(Map.class);
                javaEntityOrTrait.addImport(mapType);
                method.setReturnType("Map<String, " + mapType.getName() + ">");
            }
        } else {
            Logger.warn("[CreateInterfaceMethodsStage] Return type not supported for getter method: " + method.getName() + " for type: " + property.getType());
        }
    }

    /**
     * Creates a standard java setter method for the given property.
     * @param javaEntityOrTrait
     * @param property
     */
    private void createSetter(JavaInterfaceSource javaEntityOrTrait, PropertyModel property) {
        MethodSource<JavaInterfaceSource> method = javaEntityOrTrait.addMethod().setName(setterMethodName(property)).setReturnTypeVoid().setPublic();
        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntityOrTrait.addImport(List.class);
            javaEntityOrTrait.addImport(listType);
            method.addParameter("List<" + listType.getSimpleName() + ">", "value");
        } else if (isPrimitiveMap(property)) {
            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntityOrTrait.addImport(Map.class);
            javaEntityOrTrait.addImport(mapType);
            method.addParameter("Map<String, " + mapType.getSimpleName() + ">", "value");
        } else if (isPrimitive(property)) {
            Class<?> paramType = primitiveTypeToClass(property.getType());
            javaEntityOrTrait.addImport(paramType);
            method.addParameter(paramType.getSimpleName(), "value");
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(javaEntityOrTrait.getPackage(), property);
            if (entityType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntityOrTrait.addImport(entityType);
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
     * @param javaEntityOrTrait
     * @param property
     */
    private void createFactoryMethod(JavaInterfaceSource javaEntityOrTrait, PropertyModel property) {
        String _package = javaEntityOrTrait.getPackage();
        PropertyType type = property.getType();
        if (type.isMap() || type.isList()) {
            type = type.getNested().iterator().next();
        }
        String methodName = createMethodName(type.getSimpleType());
        // The name of the "create" method is based on the type, so it's possible to have
        // duplicates.  Let's not do that.
        if (!hasNamedMethod(javaEntityOrTrait, methodName)) {
            JavaInterfaceSource entityType = resolveEntityType(_package, type);
            if (entityType == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + _package + "::" + type);
                return;
            }
            javaEntityOrTrait.addMethod().setPublic().setName(methodName).setReturnType(entityType);
        }
    }

    /**
     * Creates an "add" method for the given property.  The type of the property must be a
     * list of entities.  The add method would accept a single entity and add it to the list.
     * @param javaEntityOrTrait
     * @param property
     */
    private void createAddMethod(JavaInterfaceSource javaEntityOrTrait, PropertyModel property) {
        String _package = javaEntityOrTrait.getPackage();
        PropertyType type = property.getType().getNested().iterator().next();
        String methodName = addMethodName(singularize(property.getName()));
        if (type.isEntityType()) {
            JavaInterfaceSource entityType = resolveEntityType(_package, type);
            if (entityType == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + _package + "::" + type);
                return;
            }

            javaEntityOrTrait.addImport(entityType);

            MethodSource<JavaInterfaceSource> method = javaEntityOrTrait.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
            if (property.getType().isMap()) {
                method.addParameter("String", "name");
            }
            method.addParameter(entityType.getName(), "value");
        } else if (type.isPrimitiveType()) {
            Class<?> primitiveType = primitiveTypeToClass(type);
            javaEntityOrTrait.addImport(primitiveType);

            MethodSource<JavaInterfaceSource> method = javaEntityOrTrait.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
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
     * @param javaEntityOrTrait
     * @param property
     */
    private void createClearMethod(JavaInterfaceSource javaEntityOrTrait, PropertyModel property) {
        String methodName = clearMethodName(property.getName());

        javaEntityOrTrait.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
    }

    /**
     * Creates a "remove" method for the given property.  The type of the property must be a
     * list of entities.  The remove method will remove one item from the list.
     * @param entity
     * @param property
     */
    private void createRemoveMethod(JavaInterfaceSource javaEntityOrTrait, PropertyModel property) {
        String methodName = removeMethodName(singularize(property.getName()));
        MethodSource<JavaInterfaceSource> method = javaEntityOrTrait.addMethod().setPublic().setName(methodName).setReturnTypeVoid();

        if (property.getType().isList()) {
            PropertyType type = property.getType().getNested().iterator().next();
            JavaInterfaceSource entityType = resolveEntityType(javaEntityOrTrait.getPackage(), type);
            if (entityType == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + javaEntityOrTrait.getPackage() + "::" + type);
                return;
            }
            javaEntityOrTrait.addImport(entityType);
            method.addParameter(entityType.getName(), "value");
        } else {
            method.addParameter("String", "name");
        }
    }

}
