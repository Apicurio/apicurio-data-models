package io.apicurio.umg.pipe.java;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodHolderSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyType;

/**
 * Base class for the stages that create methods for entity interfaces and impl classes both.  The
 * logic for these two stages is shared.
 * @author eric.wittmann@gmail.com
 */
public abstract class AbstractCreateMethodsStage extends AbstractJavaStage {

    protected void createPropertyMethods(JavaSource<?> javaEntity, PropertyModel property) {
        if (property.getName().equals("*")) {
            if (isEntity(property) || isPrimitive(property)) {
                createMappedNodeMethods(javaEntity, property);
                if (isEntity(property)) {
                    createFactoryMethod(javaEntity, property);
                }
            } else {
                error("STAR property type not handled: " + javaEntity.getCanonicalName() + "::" + property);
                return;
            }
        } else if (property.getName().startsWith("/") && (isEntity(property) || isPrimitive(property))) {
            if (property.getCollection() == null) {
                error("Regex property defined without a collection name: " + javaEntity.getCanonicalName() + "::" + property);
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
            warn("Failed to create methods (not yet implemented) for property '" + property.getName() + "' of entity: " + javaEntity.getQualifiedName());
        }
    }

    /**
     * When an entity has a "*" property, that means the entity is a wrapper around a map
     * of values of a particular type.  In this case, the entity needs to extend/implement
     * the "MappedNode" interface.
     * @param javaEntity
     * @param property
     */
    protected abstract void createMappedNodeMethods(JavaSource<?> javaEntity, PropertyModel property);

    /**
     * Creates a standard java getter method for the given property.
     * @param javaEntity
     * @param property
     */
    protected void createGetter(JavaSource<?> javaEntity, PropertyModel property) {
        MethodSource<?> method = ((MethodHolderSource<?>) javaEntity).addMethod().setName(getterMethodName(property)).setPublic();
        addAnnotations(method);

        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(List.class);
            javaEntity.addImport(listType);
            method.setReturnType("List<" + listType.getSimpleName() + ">");
        } else if (isPrimitiveMap(property)) {
            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(Map.class);
            javaEntity.addImport(mapType);
            method.setReturnType("Map<String, " + mapType.getSimpleName() + ">");
        } else if (isPrimitive(property)) {
            Class<?> returnType = primitiveTypeToClass(property.getType());
            javaEntity.addImport(returnType);
            method.setReturnType(returnType.getSimpleName());
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveJavaEntityType(javaEntity.getPackage(), property);
            if (entityType == null) {
                warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntity.addImport(entityType);
                method.setReturnType(entityType.getName());
            }
        } else if (isEntityList(property)) {
            JavaInterfaceSource listType = resolveJavaEntityType(javaEntity.getPackage(), property.getType().getNested().iterator().next());
            if (listType == null) {
                warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntity.addImport(List.class);
                javaEntity.addImport(listType);
                method.setReturnType("List<" + listType.getName() + ">");
            }
        } else if (isEntityMap(property)) {
            JavaInterfaceSource mapType = resolveJavaEntityType(javaEntity.getPackage(), property.getType().getNested().iterator().next());
            if (mapType == null) {
                warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntity.addImport(Map.class);
                javaEntity.addImport(mapType);
                method.setReturnType("Map<String, " + mapType.getName() + ">");
            }
        } else {
            warn("Return type not supported for getter method: " + method.getName() + " for type: " + property.getType());
        }

        createGetterBody(property, method);
    }
    protected void createGetterBody(PropertyModel property, MethodSource<?> method) {
    }

    /**
     * Creates a standard java setter method for the given property.
     * @param javaEntity
     * @param property
     */
    protected void createSetter(JavaSource<?> javaEntity, PropertyModel property) {
        MethodSource<?> method = ((MethodHolderSource<?>) javaEntity).addMethod().setName(setterMethodName(property)).setReturnTypeVoid().setPublic();
        addAnnotations(method);

        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(List.class);
            javaEntity.addImport(listType);
            method.addParameter("List<" + listType.getSimpleName() + ">", "value");
        } else if (isPrimitiveMap(property)) {
            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(Map.class);
            javaEntity.addImport(mapType);
            method.addParameter("Map<String, " + mapType.getSimpleName() + ">", "value");
        } else if (isPrimitive(property)) {
            Class<?> paramType = primitiveTypeToClass(property.getType());
            javaEntity.addImport(paramType);
            method.addParameter(paramType.getSimpleName(), "value");
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveJavaEntityType(javaEntity.getPackage(), property);
            if (entityType == null) {
                warn("Java interface for entity type not found: " + property.getType());
            } else {
                javaEntity.addImport(entityType);
                method.addParameter(entityType.getName(), "value");
            }
        } else {
            warn("[AbstractCreateMethodsStage] Parameter type not supported for setter method: " + method.getName() + " for type: " + property.getType());
        }

        createSetterBody(property, method);
    }
    protected void createSetterBody(PropertyModel property, MethodSource<?> method) {
    }

    /**
     * Creates a factory method for the entity type associated with the given
     * property.  This method will only be called for entity properties, either
     * simple entity properties or collection entity properties (list/map).
     * @param javaEntity
     * @param property
     */
    protected void createFactoryMethod(JavaSource<?> javaEntity, PropertyModel property) {
        String _package = javaEntity.getPackage();
        PropertyType type = property.getType();
        if (type.isMap() || type.isList()) {
            type = type.getNested().iterator().next();
        }
        String entityName = type.getSimpleType();
        String methodName = createMethodName(entityName);
        // The name of the "create" method is based on the type, so it's possible to have
        // duplicates.  Let's not do that.
        if (!hasNamedMethod(((MethodHolderSource<?>) javaEntity), methodName)) {
            JavaInterfaceSource entityType = resolveJavaEntityType(_package, type);
            if (entityType == null) {
                error("Could not resolve entity type: " + _package + "::" + type);
                return;
            }

            MethodSource<?> method = ((MethodHolderSource<?>) javaEntity).addMethod().setPublic().setName(methodName).setReturnType(entityType);
            addAnnotations(method);

            createFactoryMethodBody(javaEntity, entityName, method);
        }
    }
    protected void createFactoryMethodBody(JavaSource<?> javaEntity, String entityName, MethodSource<?> method) {
    }

    /**
     * Creates an "add" method for the given property.  The type of the property must be a
     * list of entities.  The add method would accept a single entity and add it to the list.
     * @param javaEntity
     * @param property
     */
    protected void createAddMethod(JavaSource<?> javaEntity, PropertyModel property) {
        String _package = javaEntity.getPackage();
        PropertyType type = property.getType().getNested().iterator().next();
        String methodName = addMethodName(singularize(property.getName()));
        MethodSource<?> method;

        if (type.isEntityType()) {
            JavaInterfaceSource entityType = resolveJavaEntityType(_package, type);
            if (entityType == null) {
                error("Could not resolve entity type: " + _package + "::" + type);
                return;
            }

            javaEntity.addImport(entityType);

            method = ((MethodHolderSource<?>) javaEntity).addMethod().setPublic().setName(methodName).setReturnTypeVoid();
            addAnnotations(method);
            if (property.getType().isMap()) {
                method.addParameter("String", "name");
            }
            method.addParameter(entityType.getName(), "value");
        } else if (type.isPrimitiveType()) {
            Class<?> primitiveType = primitiveTypeToClass(type);
            javaEntity.addImport(primitiveType);

            method = ((MethodHolderSource<?>) javaEntity).addMethod().setPublic().setName(methodName).setReturnTypeVoid();
            addAnnotations(method);
            if (property.getType().isMap()) {
                method.addParameter("String", "name");
            }
            method.addParameter(primitiveType.getSimpleName(), "value");
        } else {
            warn("Type not supported for 'add' method: " + methodName + " with type: " + property.getType());
            return;
        }

        createAddMethodBody(javaEntity, property, method);
    }
    protected void createAddMethodBody(JavaSource<?> javaEntity, PropertyModel property, MethodSource<?> method) {
    }

    /**
     * Creates a "clear" method for the given property.  The type of the property must be a
     * list of entities.  The clear method will remove all items from the list.
     * @param javaEntity
     * @param property
     */
    protected void createClearMethod(JavaSource<?> javaEntity, PropertyModel property) {
        String methodName = clearMethodName(property.getName());

        MethodSource<?> method = ((MethodHolderSource<?>) javaEntity).addMethod().setPublic().setName(methodName).setReturnTypeVoid();
        addAnnotations(method);

        createClearMethodBody(property, method);
    }
    protected void createClearMethodBody(PropertyModel property, MethodSource<?> method) {
    }

    /**
     * Creates a "remove" method for the given property.  The type of the property must be a
     * list of entities.  The remove method will remove one item from the list.
     * @param entity
     * @param property
     */
    protected void createRemoveMethod(JavaSource<?> javaEntity, PropertyModel property) {
        String methodName = removeMethodName(singularize(property.getName()));
        MethodSource<?> method = ((MethodHolderSource<?>) javaEntity).addMethod().setPublic().setName(methodName).setReturnTypeVoid();
        addAnnotations(method);

        if (property.getType().isList()) {
            PropertyType type = property.getType().getNested().iterator().next();
            JavaInterfaceSource entityType = resolveJavaEntityType(javaEntity.getPackage(), type);
            if (entityType == null) {
                error("Could not resolve entity type: " + javaEntity.getPackage() + "::" + type);
                return;
            }
            javaEntity.addImport(entityType);
            method.addParameter(entityType.getName(), "value");
        } else {
            method.addParameter("String", "name");
        }

        createRemoveMethodBody(property, method);
    }
    protected void createRemoveMethodBody(PropertyModel property, MethodSource<?> method) {
    }

    /**
     * Gives subclasses an opportunity to add annotations to the created method.
     * @param method
     */
    protected void addAnnotations(MethodSource<?> method) {
    }

}
