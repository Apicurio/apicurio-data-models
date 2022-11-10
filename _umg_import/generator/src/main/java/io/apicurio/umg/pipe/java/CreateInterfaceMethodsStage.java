package io.apicurio.umg.pipe.java;

import java.util.List;
import java.util.Map;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyType;

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
    }

    private void createEntityInterfaceMethods(EntityModel entity) {
        entity.getProperties().values().forEach(property -> {
            createPropertyMethods(entity, property);
        });
    }

    private void createPropertyMethods(EntityModel entity, PropertyModel property) {
        if (property.getName().equals("*")) {
            Logger.warn("Failed to create methods (not yet implemented) for property '" + property.getName() + "' of entity: " + entity.fullyQualifiedName());
        } else if (property.getName().startsWith("/")) {
            Logger.warn("Failed to create methods (not yet implemented) for property '" + property.getName() + "' of entity: " + entity.fullyQualifiedName());
        } else if (isPrimitive(property) || isPrimitiveList(property) || isPrimitiveMap(property)) {
            createGetter(entity, property);
            createSetter(entity, property);
        } else if (isEntity(property)) {
            createGetter(entity, property);
            createSetter(entity, property);
            createFactoryMethod(entity, property);
        } else if (isEntityList(property) || isEntityMap(property)) {
            createFactoryMethod(entity, property);
            createGetter(entity, property);
            createAddMethod(entity, property);
            createClearMethod(entity, property);
            createRemoveMethod(entity, property);
        } else {
            Logger.warn("Failed to create methods (not yet implemented) for property '" + property.getName() + "' of entity: " + entity.fullyQualifiedName());
        }
    }

    private void createGetter(EntityModel entity, PropertyModel property) {
        JavaInterfaceSource entityInterface = getState().getJavaIndex().lookupInterface(getEntityInterfaceFQN(entity));
        MethodSource<JavaInterfaceSource> method = entityInterface.addMethod().setName(getterMethodName(property)).setPublic();
        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            entityInterface.addImport(List.class);
            entityInterface.addImport(listType);
            method.setReturnType("List<" + listType.getSimpleName() + ">");
        } else if (isPrimitiveMap(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            entityInterface.addImport(Map.class);
            entityInterface.addImport(listType);
            method.setReturnType("Map<String, " + listType.getSimpleName() + ">");
        } else if (isPrimitive(property)) {
            Class<?> returnType = primitiveTypeToClass(property.getType());
            entityInterface.addImport(returnType);
            method.setReturnType(returnType.getSimpleName());
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(entity.getNamespace(), property);
            if (entityType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                entityInterface.addImport(entityType);
                method.setReturnType(entityType.getName());
            }
        }
    }

    private void createSetter(EntityModel entity, PropertyModel property) {
        JavaInterfaceSource entityInterface = getState().getJavaIndex().lookupInterface(getEntityInterfaceFQN(entity));
        MethodSource<JavaInterfaceSource> method = entityInterface.addMethod().setName(setterMethodName(property)).setReturnTypeVoid().setPublic();
        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            entityInterface.addImport(List.class);
            entityInterface.addImport(listType);
            method.addParameter("List<" + listType.getSimpleName() + ">", "value");
        } else if (isPrimitiveMap(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            entityInterface.addImport(Map.class);
            entityInterface.addImport(listType);
            method.addParameter("Map<String, " + listType.getSimpleName() + ">", "value");
        } else if (isPrimitive(property)) {
            Class<?> paramType = primitiveTypeToClass(property.getType());
            entityInterface.addImport(paramType);
            method.addParameter(paramType.getSimpleName(), "value");
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(entity.getNamespace(), property);
            if (entityType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                entityInterface.addImport(entityType);
                method.addParameter(entityType.getName(), "value");
            }
        }
    }

    /**
     * Creates a factory method for the entity type associated with the given
     * property.  This method will only be called for entity properties, either
     * simple entity properties or collection entity properties (list/map).
     * @param entity
     * @param property
     */
    private void createFactoryMethod(EntityModel entity, PropertyModel property) {
        JavaInterfaceSource entityInterface = getState().getJavaIndex().lookupInterface(getEntityInterfaceFQN(entity));

        PropertyType type = property.getType();
        if (type.isMap() || type.isList()) {
            type = type.getNested().iterator().next();
        }
        String methodName = createMethodName(type.getSimpleType());
        // The name of the "create" method is based on the type, so it's possible to have
        // duplicates.  Let's not do that.
        if (!hasNamedMethod(entityInterface, methodName)) {
            JavaInterfaceSource entityType = resolveEntityType(entity.getNamespace(), type);
            if (entityType == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + entity.getNamespace() + "::" + type);
                return;
            }
            entityInterface.addMethod().setPublic().setName(methodName).setReturnType(entityType);
        }
    }

    /**
     * Creates an "add" method for the given property.  The type of the property must be a
     * list of entities.  The add method would accept a single entity and add it to the list.
     * @param entity
     * @param property
     */
    private void createAddMethod(EntityModel entity, PropertyModel property) {
        JavaInterfaceSource entityInterface = getState().getJavaIndex().lookupInterface(getEntityInterfaceFQN(entity));

        PropertyType type = property.getType().getNested().iterator().next();
        String methodName = addMethodName(singularize(property.getName()));
        JavaInterfaceSource entityType = resolveEntityType(entity.getNamespace(), type);
        if (entityType == null) {
            Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + entity.getNamespace() + "::" + type);
            return;
        }

        entityInterface.addImport(entityType);

        MethodSource<JavaInterfaceSource> method = entityInterface.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
        if (property.getType().isMap()) {
            method.addParameter("String", "name");
        }
        method.addParameter(entityType.getName(), "value");
    }

    /**
     * Creates a "clear" method for the given property.  The type of the property must be a
     * list of entities.  The clear method will remove all items from the list.
     * @param entity
     * @param property
     */
    private void createClearMethod(EntityModel entity, PropertyModel property) {
        JavaInterfaceSource entityInterface = getState().getJavaIndex().lookupInterface(getEntityInterfaceFQN(entity));

        String methodName = clearMethodName(property.getName());

        entityInterface.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
    }

    /**
     * Creates a "remove" method for the given property.  The type of the property must be a
     * list of entities.  The remove method will remove one item from the list.
     * @param entity
     * @param property
     */
    private void createRemoveMethod(EntityModel entity, PropertyModel property) {
        JavaInterfaceSource entityInterface = getState().getJavaIndex().lookupInterface(getEntityInterfaceFQN(entity));

        String methodName = removeMethodName(singularize(property.getName()));
        MethodSource<JavaInterfaceSource> method = entityInterface.addMethod().setPublic().setName(methodName).setReturnTypeVoid();

        if (property.getType().isList()) {
            PropertyType type = property.getType().getNested().iterator().next();
            JavaInterfaceSource entityType = resolveEntityType(entity.getNamespace(), type);
            if (entityType == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + entity.getNamespace() + "::" + type);
                return;
            }
            entityInterface.addImport(entityType);
            method.addParameter(entityType.getName(), "value");
        } else {
            method.addParameter("String", "name");
        }
    }

}
