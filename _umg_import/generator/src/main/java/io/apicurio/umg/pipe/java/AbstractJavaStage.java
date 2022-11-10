package io.apicurio.umg.pipe.java;

import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodHolderSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.modeshape.common.text.Inflector;

import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.NamespaceModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyType;
import io.apicurio.umg.models.concept.TraitModel;
import io.apicurio.umg.pipe.AbstractStage;

public abstract class AbstractJavaStage extends AbstractStage {

    private static final Inflector inflector = new Inflector();

    protected String getFieldName(PropertyModel property) {
        if (property.getName().equals("*")) {
            return "_items";
        }
        if (property.getName().startsWith("/")) {
            return sanitizeFieldName(property.getCollection());
        }
        return sanitizeFieldName(property.getName());
    }

    protected String sanitizeFieldName(String name) {
        if (name == null) {
            return null;
        }
        return Util.JAVA_KEYWORD_MAP.getOrDefault(name, name);
    }

    protected String getPrefix(NamespaceModel namespace) {
        return getPrefix(namespace.fullName());
    }

    protected String getPrefix(String namespace) {
        String prefix = getState().getSpecIndex().getNsToPrefix().get(namespace);
        return prefix == null ? "" : prefix;
    }

    protected String getEntityInterfaceFQN(EntityModel entity) {
        return getEntityInterfacePackage(entity) + "." + getEntityInterfaceName(entity);
    }

    protected String getTraitInterfaceFQN(TraitModel trait) {
        return getTraitInterfacePackage(trait) + "." + getTraitInterfaceName(trait);
    }

    protected String getEntityClassFQN(EntityModel entity) {
        return getEntityClassPackage(entity) + "." + getEntityClassName(entity);
    }

    protected String getEntityInterfaceName(EntityModel entity) {
        String prefix = getState().getSpecIndex().getNsToPrefix().get(entity.getNamespace().fullName());
        return (prefix == null ? "" : prefix) + entity.getName();
    }

    protected String getTraitInterfaceName(TraitModel trait) {
        String prefix = getState().getSpecIndex().getNsToPrefix().get(trait.getNamespace().fullName());
        return (prefix == null ? "" : prefix) + trait.getName();
    }

    protected String getEntityClassName(EntityModel entity) {
        String prefix = getState().getSpecIndex().getNsToPrefix().get(entity.getNamespace().fullName());
        return (prefix == null ? "" : prefix) + entity.getName() + "Impl";
    }

    protected String getEntityInterfacePackage(EntityModel entity) {
        return getPackage(entity.getNamespace());
    }

    protected String getTraitInterfacePackage(TraitModel trait) {
        return getPackage(trait.getNamespace());
    }

    protected String getEntityClassPackage(EntityModel entity) {
        return getPackage(entity.getNamespace());
    }

    protected String getPackage(NamespaceModel namespace) {
        return namespace.fullName();
    }

    protected String getNodeEntityInterfaceFQN() {
        return getState().getConfig().getRootNamespace() + ".Node";
    }

    protected String getMappedNodeInterfaceFQN() {
        return getState().getConfig().getRootNamespace() + ".MappedNode";
    }

    protected String getNodeEntityClassFQN() {
        return getState().getConfig().getRootNamespace() + ".NodeImpl";
    }

    protected String createMethodName(EntityModel entityModel) {
        return createMethodName(entityModel.getName());
    }

    protected String createMethodName(PropertyModel propertyModel) {
        return createMethodName(propertyModel.getName());
    }

    protected String createMethodName(String entityName) {
        return "create" + StringUtils.capitalize(entityName);
    }

    protected String addMethodName(EntityModel entityModel) {
        return addMethodName(entityModel.getName());
    }

    protected String addMethodName(PropertyModel propertyModel) {
        return addMethodName(propertyModel.getName());
    }

    protected String addMethodName(String name) {
        return "add" + StringUtils.capitalize(name);
    }

    protected String clearMethodName(EntityModel entityModel) {
        return clearMethodName(entityModel.getName());
    }

    protected String clearMethodName(PropertyModel propertyModel) {
        return clearMethodName(propertyModel.getName());
    }

    protected String clearMethodName(String name) {
        return "clear" + StringUtils.capitalize(name);
    }

    protected String removeMethodName(EntityModel entityModel) {
        return removeMethodName(entityModel.getName());
    }

    protected String removeMethodName(PropertyModel propertyModel) {
        return removeMethodName(propertyModel.getName());
    }

    protected String removeMethodName(String name) {
        return "remove" + StringUtils.capitalize(name);
    }

    protected String getterMethodName(PropertyModel propertyModel) {
        String name = propertyModel.getName();
        if (name.startsWith("/")) {
            name = propertyModel.getCollection();
        }
        return getterMethodName(name, propertyModel.getType());
    }

    protected String getterMethodName(String propertyName, PropertyType type) {
        boolean isBool = type.isPrimitiveType() && type.getSimpleType().equals("boolean");
        return (isBool ? "is" : "get") + StringUtils.capitalize(propertyName);
    }

    protected String setterMethodName(PropertyModel propertyModel) {
        return "set" + StringUtils.capitalize(propertyModel.getName());
    }

    protected Class<?> primitiveTypeToClass(PropertyType type) {
        if (!type.isPrimitiveType()) {
            throw new UnsupportedOperationException("Property type not primitive: " + type);
        }
        Class<?> rval = Util.PRIMITIVE_TYPE_MAP.get(type.getSimpleType());
        if (rval == null) {
            throw new UnsupportedOperationException("Primitive-to-class mapping not found for: " + type.getSimpleType());
        }
        return rval;
    }

    protected JavaInterfaceSource resolveEntityType(NamespaceModel namespace, PropertyModel property) {
        return resolveEntityType(namespace.fullName(), property.getType());
    }

    protected JavaInterfaceSource resolveEntityType(String namespace, PropertyModel property) {
        return resolveEntityType(namespace, property.getType());
    }

    protected JavaInterfaceSource resolveEntityType(String namespace, PropertyType type) {
        String _package = namespace;
        String prefix = getPrefix(namespace);
        String fqn = _package + "." + prefix + type.getSimpleType();
        return getState().getJavaIndex().lookupInterface(fqn);
    }

    protected boolean isEntityList(PropertyModel property) {
        return property.getType().isList() && property.getType().getNested().iterator().next().isEntityType();
    }

    protected boolean isEntityMap(PropertyModel property) {
        return property.getType().isMap() && property.getType().getNested().iterator().next().isEntityType();
    }

    protected boolean isEntity(PropertyModel property) {
        return property.getType().isEntityType();
    }

    protected boolean isPrimitive(PropertyModel property) {
        return property.getType().isPrimitiveType();
    }

    protected boolean isPrimitiveList(PropertyModel property) {
        return property.getType().isList() && property.getType().getNested().iterator().next().isPrimitiveType();
    }

    protected boolean isPrimitiveMap(PropertyModel property) {
        return property.getType().isMap() && property.getType().getNested().iterator().next().isPrimitiveType();
    }

    protected String singularize(String name) {
        return inflector.singularize(name);
    }

    protected boolean hasNamedMethod(MethodHolderSource<?> entityInterface, String methodName) {
        for (MethodSource<?> method : entityInterface.getMethods()) {
            if (method.getName().equals(methodName)) {
                return true;
            }
        }
        return false;
    }

    protected JavaInterfaceSource lookupEntity(EntityModel entity) {
        return getState().getJavaIndex().lookupInterface(getEntityInterfaceFQN(entity));
    }

    protected JavaInterfaceSource lookupTrait(TraitModel trait) {
        return getState().getJavaIndex().lookupInterface(getTraitInterfaceFQN(trait));
    }

    protected JavaClassSource lookupEntityImpl(EntityModel entity) {
        return getState().getJavaIndex().lookupClass(getEntityClassFQN(entity));
    }

}
