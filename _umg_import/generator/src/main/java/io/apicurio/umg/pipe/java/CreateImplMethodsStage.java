package io.apicurio.umg.pipe.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodHolderSource;
import org.jboss.forge.roaster.model.source.MethodSource;

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
public class CreateImplMethodsStage extends AbstractCreateMethodsStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findEntities("").stream().filter(entity -> entity.isLeaf()).forEach(entity -> {
            createEntityImplMethods(entity);
        });
    }

    private void createEntityImplMethods(EntityModel entity) {
        JavaClassSource javaEntity = lookupJavaEntityImpl(entity);

        Collection<PropertyModel> allProperties = getState().getConceptIndex().getAllEntityProperties(entity);
        allProperties.forEach(property -> {
            createPropertyMethods(javaEntity, property);
        });
    }

    /**
     * When an entity has a "*" property, that means the entity is a wrapper around a map
     * of values of a particular type.  In this case, the entity interface needs to extend
     * the "MappedNode" interface.
     * @param javaEntity
     * @param property
     */
    @Override
    protected void createMappedNodeMethods(JavaSource<?> javaEntity, PropertyModel property) {
        String mappedNodeType;
        javaEntity.addImport(List.class);
        javaEntity.addImport(ArrayList.class);

        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(listType);
            mappedNodeType = "List<" + listType.getSimpleName() + ">";
        } else if (isPrimitiveMap(property)) {
            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(Map.class);
            javaEntity.addImport(mapType);
            mappedNodeType = "Map<String, " + mapType.getSimpleName() + ">";
        } else if (isPrimitive(property)) {
            Class<?> primType = primitiveTypeToClass(property.getType());
            javaEntity.addImport(primType);
            mappedNodeType = primType.getSimpleName();
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveJavaEntityType(javaEntity.getPackage(), property);
            if (entityType == null) {
                error("Java interface for entity type not found: " + property.getType());
                return;
            } else {
                javaEntity.addImport(entityType);
                mappedNodeType = entityType.getName();
            }
        } else {
            error("Unhandled STAR property from entity: " + javaEntity.getCanonicalName());
            return;
        }

        // T getItem(String name)
        {
            MethodSource<?> method = ((MethodHolderSource<?>) javaEntity).addMethod().setName("getItem").setPublic();
            method.addAnnotation(Override.class);
            method.addParameter("String", "name");
            method.setReturnType(mappedNodeType);
            BodyBuilder body = new BodyBuilder();
            body.append("return this._items.get(name);");
            method.setBody(body.toString());
        }

        // List<T> getItems()
        {
            MethodSource<?> method = ((MethodHolderSource<?>) javaEntity).addMethod().setName("getItems").setPublic();
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
            MethodSource<?> method = ((MethodHolderSource<?>) javaEntity).addMethod().setName("getItemNames").setPublic();
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
            MethodSource<?> method = ((MethodHolderSource<?>) javaEntity).addMethod().setName("addItem").setPublic().setReturnTypeVoid();
            method.addAnnotation(Override.class);
            method.addParameter("String", "name");
            method.addParameter(mappedNodeType, "item");
            BodyBuilder body = new BodyBuilder();
            body.append("this._items.put(name, item);");
            method.setBody(body.toString());
        }

        // T removeItem(String name)
        {
            MethodSource<?> method = ((MethodHolderSource<?>) javaEntity).addMethod().setName("removeItem").setPublic();
            method.addAnnotation(Override.class);
            method.addParameter("String", "name");
            method.setReturnType(mappedNodeType);
            BodyBuilder body = new BodyBuilder();
            body.append("return this._items.remove(name);");
            method.setBody(body.toString());
        }
    }

    @Override
    protected void createGetterBody(PropertyModel property, MethodSource<?> method) {
        String fieldName = getFieldName(property);
        BodyBuilder body = new BodyBuilder();
        body.addContext("fieldName", fieldName);
        body.append("return ${fieldName};");
        method.setBody(body.toString());
    }

    @Override
    protected void createSetterBody(PropertyModel property, MethodSource<?> method) {
        String fieldName = getFieldName(property);
        BodyBuilder body = new BodyBuilder();
        body.addContext("fieldName", fieldName);
        body.append("this.${fieldName} = value;");
        method.setBody(body.toString());
    }

    @Override
    protected void createFactoryMethodBody(JavaSource<?> javaEntity, String entityName, MethodSource<?> method) {
        String entityFQN = javaEntity.getPackage() + "." + entityName;
        EntityModel entityModel = getState().getConceptIndex().lookupEntity(entityFQN);
        String implFQN = getJavaEntityClassFQN(entityModel);

        JavaClassSource entityImpl = lookupJavaEntityImpl(implFQN);
        if (entityImpl == null) {
            error("Could not resolve entity type (impl): " + implFQN);
            return;
        }
        javaEntity.addImport(entityImpl);

        BodyBuilder body = new BodyBuilder();
        body.addContext("implClass", entityImpl.getName());
        body.append("${implClass} node = new ${implClass}();");
        body.append("node.setParent(this);");
        body.append("node.setRoot(this.root());");
        body.append("return node;");
        method.setBody(body.toString());
    }

    @Override
    protected void createAddMethodBody(JavaSource<?> javaEntity, PropertyModel property, MethodSource<?> method) {
        PropertyType type = property.getType().getNested().iterator().next();

        BodyBuilder body = new BodyBuilder();
        body.addContext("fieldName", getFieldName(property));

        if (type.isEntityType() || type.isPrimitiveType()) {
            if (property.getType().isMap()) {
                javaEntity.addImport(LinkedHashMap.class);
                body.append("if (this.${fieldName} == null) {");
                body.append("    this.${fieldName} = new LinkedHashMap<>();");
                body.append("}");
                body.append("this.${fieldName}.put(name, value);");
            } else {
                javaEntity.addImport(ArrayList.class);
                body.append("if (this.${fieldName} == null) {");
                body.append("    this.${fieldName} = new ArrayList<>();");
                body.append("}");
                body.append("this.${fieldName}.add(value);");
            }
        }

        method.setBody(body.toString());
    }

    @Override
    protected void createClearMethodBody(PropertyModel property, MethodSource<?> method) {
        String fieldName = getFieldName(property);
        BodyBuilder body = new BodyBuilder();
        body.addContext("fieldName", fieldName);
        body.append("if (this.${fieldName} != null) {");
        body.append("    this.${fieldName}.clear();");
        body.append("}");
        method.setBody(body.toString());
    }

    @Override
    protected void createRemoveMethodBody(PropertyModel property, MethodSource<?> method) {
        String fieldName = getFieldName(property);
        BodyBuilder body = new BodyBuilder();
        body.addContext("fieldName", fieldName);

        body.append("if (this.${fieldName} != null) {");
        if (property.getType().isList()) {
            body.append("    this.${fieldName}.remove(value);");
        } else {
            body.append("    this.${fieldName}.remove(name);");
        }
        body.append("}");

        method.setBody(body.toString());
    }

    @Override
    protected void addAnnotations(MethodSource<?> method) {
        method.addAnnotation(Override.class);
    }

    /**
     * Override the entity type resolution to do the extra work of finding the right entity.
     * This is needed here because Impl classes implement methods for *all* properties, not
     * just the ones in their same namespace.  So we need to search UP the entity hierarchy
     * to find the right one.
     *
     * @see io.apicurio.umg.pipe.java.AbstractJavaStage#resolveJavaEntity(java.lang.String, java.lang.String)
     */
    @Override
    protected JavaInterfaceSource resolveJavaEntity(String namespace, String entityName) {
        return resolveCommonJavaEntity(namespace, entityName);
    }

}
