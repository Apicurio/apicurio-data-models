package io.apicurio.umg.pipe.java;

import java.util.ArrayList;
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
import io.apicurio.umg.pipe.java.method.BodyBuilder;

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
        JavaClassSource javaEntity = lookupEntityImpl(entity);

        Collection<PropertyModel> allProperties = getState().getConceptIndex().getAllEntityProperties(entity);
        allProperties.forEach(property -> {
            createPropertyMethods(javaEntity, property);
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
        String mappedNodeType;
        addImportTo(List.class, javaEntity);
        addImportTo(ArrayList.class, javaEntity);

        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            addImportTo(listType, javaEntity);
            mappedNodeType = "List<" + listType.getSimpleName() + ">";
        } else if (isPrimitiveMap(property)) {
            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            addImportTo(Map.class, javaEntity);
            addImportTo(mapType, javaEntity);
            mappedNodeType = "Map<String, " + mapType.getSimpleName() + ">";
        } else if (isPrimitive(property)) {
            Class<?> primType = primitiveTypeToClass(property.getType());
            addImportTo(primType, javaEntity);
            mappedNodeType = primType.getSimpleName();
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(javaEntity.getPackage(), property);
            if (entityType == null) {
                Logger.error("[CreateImplMethodsStage] Java interface for entity type not found: " + property.getType());
                return;
            } else {
                addImportTo(entityType, javaEntity);
                mappedNodeType = entityType.getName();
            }
        } else {
            Logger.error("Unhandled STAR property from entity: " + javaEntity.getCanonicalName());
            return;
        }

        // T getItem(String name)
        {
            MethodSource<JavaClassSource> method = javaEntity.addMethod().setName("getItem").setPublic();
            method.addAnnotation(Override.class);
            method.addParameter("String", "name");
            method.setReturnType(mappedNodeType);
            BodyBuilder body = new BodyBuilder();
            body.append("return this._items.get(name);");
            method.setBody(body.toString());
        }

        // List<T> getItems()
        {
            MethodSource<JavaClassSource> method = javaEntity.addMethod().setName("getItems").setPublic();
            method.addAnnotation(Override.class);
            method.setReturnType("List<" + mappedNodeType + ">");
            BodyBuilder body = new BodyBuilder();
            body.addContext("itemType", mappedNodeType);
            body.append("List<${itemType}> rval = new ArrayList<>();");
            body.append("rval.addAll(this._items.values());");
            body.append("return rval;");
            method.setBody(body.toString());
        }

        // List<String> getItemNames()
        {
            MethodSource<JavaClassSource> method = javaEntity.addMethod().setName("getItemNames").setPublic();
            method.addAnnotation(Override.class);
            method.setReturnType("List<String>");
            BodyBuilder body = new BodyBuilder();
            body.addContext("itemType", mappedNodeType);
            body.append("List<String> rval = new ArrayList<>();");
            body.append("rval.addAll(this._items.keySet());");
            body.append("return rval;");
            method.setBody(body.toString());
        }

        // void addItem(String name, T item)
        {
            MethodSource<JavaClassSource> method = javaEntity.addMethod().setName("addItem").setPublic().setReturnTypeVoid();
            method.addAnnotation(Override.class);
            method.addParameter("String", "name");
            method.addParameter(mappedNodeType, "item");
            BodyBuilder body = new BodyBuilder();
            body.append("this._items.put(name, item);");
            method.setBody(body.toString());
        }

        // T removeItem(String name)
        {
            MethodSource<JavaClassSource> method = javaEntity.addMethod().setName("removeItem").setPublic();
            method.addAnnotation(Override.class);
            method.addParameter("String", "name");
            method.setReturnType(mappedNodeType);
            BodyBuilder body = new BodyBuilder();
            body.append("return this._items.remove(name);");
            method.setBody(body.toString());
        }
    }

    /**
     * Creates a standard java getter method for the given property.
     * @param javaEntity
     * @param property
     */
    private void createGetter(JavaClassSource javaEntity, PropertyModel property) {
        MethodSource<JavaClassSource> method = javaEntity.addMethod().setName(getterMethodName(property)).setPublic();
        method.addAnnotation(Override.class);

        String fieldName = getFieldName(property);

        BodyBuilder body = new BodyBuilder();
        body.addContext("fieldName", fieldName);
        body.append("return ${fieldName};");

        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            addImportTo(List.class, javaEntity);
            addImportTo(listType, javaEntity);
            method.setReturnType("List<" + listType.getSimpleName() + ">");
        } else if (isPrimitiveMap(property)) {
            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            addImportTo(Map.class, javaEntity);
            addImportTo(mapType, javaEntity);
            method.setReturnType("Map<String, " + mapType.getSimpleName() + ">");
        } else if (isPrimitive(property)) {
            Class<?> returnType = primitiveTypeToClass(property.getType());
            addImportTo(returnType, javaEntity);
            method.setReturnType(returnType.getSimpleName());
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(javaEntity.getPackage(), property);
            if (entityType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                addImportTo(entityType, javaEntity);
                method.setReturnType(entityType.getName());
            }
        } else if (isEntityList(property)) {
            JavaInterfaceSource listType = resolveEntityType(javaEntity.getPackage(), property.getType().getNested().iterator().next());
            if (listType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                addImportTo(List.class, javaEntity);
                addImportTo(listType, javaEntity);
                method.setReturnType("List<" + listType.getName() + ">");
            }
        } else if (isEntityMap(property)) {
            JavaInterfaceSource mapType = resolveEntityType(javaEntity.getPackage(), property.getType().getNested().iterator().next());
            if (mapType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                addImportTo(Map.class, javaEntity);
                addImportTo(mapType, javaEntity);
                method.setReturnType("Map<String, " + mapType.getName() + ">");
            }
        } else {
            Logger.warn("[CreateInterfaceMethodsStage] Return type not supported for getter method: " + method.getName() + " for type: " + property.getType());
        }

        method.setBody(body.toString());
    }

    /**
     * Creates a standard java setter method for the given property.
     * @param javaEntity
     * @param property
     */
    private void createSetter(JavaClassSource javaEntity, PropertyModel property) {
        MethodSource<JavaClassSource> method = javaEntity.addMethod().setName(setterMethodName(property)).setReturnTypeVoid().setPublic();
        method.addAnnotation(Override.class);

        String fieldName = getFieldName(property);

        BodyBuilder body = new BodyBuilder();
        body.addContext("fieldName", fieldName);
        body.append("this.${fieldName} = value;");

        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            addImportTo(List.class, javaEntity);
            addImportTo(listType, javaEntity);
            method.addParameter("List<" + listType.getSimpleName() + ">", "value");
        } else if (isPrimitiveMap(property)) {
            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            addImportTo(Map.class, javaEntity);
            addImportTo(mapType, javaEntity);
            method.addParameter("Map<String, " + mapType.getSimpleName() + ">", "value");
        } else if (isPrimitive(property)) {
            Class<?> paramType = primitiveTypeToClass(property.getType());
            addImportTo(paramType, javaEntity);
            method.addParameter(paramType.getSimpleName(), "value");
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(javaEntity.getPackage(), property);
            if (entityType == null) {
                Logger.warn("Java interface for entity type not found: " + property.getType());
            } else {
                addImportTo(entityType, javaEntity);
                method.addParameter(entityType.getName(), "value");
            }
        } else {
            Logger.warn("[CreateInterfaceMethodsStage] Parameter type not supported for setter method: " + method.getName() + " for type: " + property.getType());
        }

        method.setBody(body.toString());
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

            JavaClassSource entityImpl = lookupEntityImpl(entityType.getQualifiedName() + "Impl");
            if (entityImpl == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type (impl): " + _package + "::" + type);
                return;
            }
            addImportTo(entityImpl, javaEntity);

            MethodSource<JavaClassSource> method = javaEntity.addMethod().setPublic().setName(methodName).setReturnType(entityType);
            method.addAnnotation(Override.class);

            BodyBuilder body = new BodyBuilder();
            body.addContext("implClass", entityImpl.getName());
            body.append("${implClass} node = new ${implClass}();");
            body.append("node.setParent(this);");
            body.append("node.setRoot(this.root());");
            body.append("return node;");
            method.setBody(body.toString());
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
        MethodSource<JavaClassSource> method;

        BodyBuilder body = new BodyBuilder();
        body.addContext("fieldName", getFieldName(property));

        if (type.isEntityType()) {
            JavaInterfaceSource entityType = resolveEntityType(_package, type);
            if (entityType == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + _package + "::" + type);
                return;
            }

            addImportTo(entityType, javaEntity);

            method = javaEntity.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
            method.addAnnotation(Override.class);
            if (property.getType().isMap()) {
                method.addParameter("String", "name");
                body.append("this.${fieldName}.put(name, value);");
            } else {
                body.append("this.${fieldName}.add(value);");
            }
            method.addParameter(entityType.getName(), "value");
        } else if (type.isPrimitiveType()) {
            Class<?> primitiveType = primitiveTypeToClass(type);
            addImportTo(primitiveType, javaEntity);

            method = javaEntity.addMethod().setPublic().setName(methodName).setReturnTypeVoid();
            method.addAnnotation(Override.class);
            if (property.getType().isMap()) {
                method.addParameter("String", "name");
                body.append("this.${fieldName}.put(name, value);");
            } else {
                body.append("this.${fieldName}.add(value);");
            }
            method.addParameter(primitiveType.getSimpleName(), "value");
        } else {
            Logger.warn("[CreateInterfaceMethodsStage] Type not supported for 'add' method: " + methodName + " with type: " + property.getType());
            return;
        }

        method.setBody(body.toString());
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

        String fieldName = getFieldName(property);

        BodyBuilder body = new BodyBuilder();
        body.addContext("fieldName", fieldName);
        body.append("this.${fieldName}.clear();");

        method.setBody(body.toString());
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

        String fieldName = getFieldName(property);
        BodyBuilder body = new BodyBuilder();
        body.addContext("fieldName", fieldName);

        if (property.getType().isList()) {
            PropertyType type = property.getType().getNested().iterator().next();
            JavaInterfaceSource entityType = resolveEntityType(javaEntity.getPackage(), type);
            if (entityType == null) {
                Logger.error("[CreateEntityInterfaceMethodsStage] Could not resolve entity type: " + javaEntity.getPackage() + "::" + type);
                return;
            }
            addImportTo(entityType, javaEntity);
            method.addParameter(entityType.getName(), "value");

            body.append("${fieldName}.remove(value);");
        } else {
            method.addParameter("String", "name");

            body.append("${fieldName}.remove(name);");
        }
        method.setBody(body.toString());
    }

}
