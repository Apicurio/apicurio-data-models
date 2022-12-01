package io.apicurio.umg.pipe.java;

import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodHolderSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import io.apicurio.umg.beans.SpecificationVersion;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.NamespaceModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyType;
import io.apicurio.umg.models.concept.TraitModel;
import io.apicurio.umg.models.concept.VisitorModel;
import io.apicurio.umg.pipe.AbstractStage;

public abstract class AbstractJavaStage extends AbstractStage {

    protected String getReaderClassName(SpecificationVersion specVersion) {
        return specVersion.getPrefix() + "ModelReader";
    }

    protected String getReaderPackageName(SpecificationVersion specVersion) {
        return specVersion.getNamespace() + ".io";
    }

    protected String getWriterClassName(SpecificationVersion specVersion) {
        return specVersion.getPrefix() + "ModelWriter";
    }

    protected String getWriterPackageName(SpecificationVersion specVersion) {
        return specVersion.getNamespace() + ".io";
    }

    protected String getTraverserClassName(SpecificationVersion specVersion) {
        return specVersion.getPrefix() + "Traverser";
    }

    protected String getTraverserPackageName(SpecificationVersion specVersion) {
        return specVersion.getNamespace() + ".visitors";
    }

    /**
     * Determines the package to use for the interface generated for the given visitor.
     * @param visitor
     */
    protected String getVisitorInterfacePackageName(VisitorModel visitor) {
        String packageName = visitor.getNamespace().fullName();
        String visitorPackageName = packageName + ".visitors";
        return visitorPackageName;
    }

    /**
     * Determines the prefix to use for the interface name for the given visitor.
     * @param visitor
     */
    protected String getVisitorInterfacePrefix(VisitorModel visitor) {
        return (visitor.getParent() == null) ? "" : getState().getSpecIndex().prefixForNS(visitor.getNamespace().fullName());
    }

    /**
     * Determines the interface name for the given visitor.
     * @param visitor
     */
    protected String getVisitorInterfaceName(VisitorModel visitor) {
        String visitorPrefix = getVisitorInterfacePrefix(visitor);
        String visitorInterfaceName = visitorPrefix + "Visitor";
        return visitorInterfaceName;
    }

    /**
     * Determines the fully qualified name of the Java interface for a given visitor.
     * @param visitor
     */
    protected String getVisitorInterfaceFullName(VisitorModel visitor) {
        String packageName = visitor.getNamespace().fullName();
        String visitorPackageName = packageName + ".visitors";
        String visitorPrefix = getVisitorInterfacePrefix(visitor);
        String visitorInterfaceName = visitorPrefix + "Visitor";
        return visitorPackageName + "." + visitorInterfaceName;
    }

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

    protected String getJavaEntityInterfaceFQN(EntityModel entity) {
        return getJavaEntityInterfacePackage(entity) + "." + getJavaEntityInterfaceName(entity);
    }

    protected String getJavaTraitInterfaceFQN(TraitModel trait) {
        return getJavaTraitInterfacePackage(trait) + "." + getJavaTraitInterfaceName(trait);
    }

    protected String getJavaEntityClassFQN(EntityModel entity) {
        return getJavaEntityClassPackage(entity) + "." + getJavaEntityClassName(entity);
    }

    protected String getJavaEntityInterfaceName(EntityModel entity) {
        String prefix = getState().getSpecIndex().getNsToPrefix().get(entity.getNamespace().fullName());
        return (prefix == null ? "" : prefix) + entity.getName();
    }

    protected String getJavaTraitInterfaceName(TraitModel trait) {
        String prefix = getState().getSpecIndex().getNsToPrefix().get(trait.getNamespace().fullName());
        return (prefix == null ? "" : prefix) + trait.getName();
    }

    protected String getJavaEntityClassName(EntityModel entity) {
        String prefix = getState().getSpecIndex().getNsToPrefix().get(entity.getNamespace().fullName());
        return (prefix == null ? "" : prefix) + entity.getName() + "Impl";
    }

    protected String getJavaEntityInterfacePackage(EntityModel entity) {
        return getPackage(entity.getNamespace());
    }

    protected String getJavaTraitInterfacePackage(TraitModel trait) {
        return getPackage(trait.getNamespace());
    }

    protected String getJavaEntityClassPackage(EntityModel entity) {
        return getPackage(entity.getNamespace());
    }

    protected String getPackage(NamespaceModel namespace) {
        return namespace.fullName();
    }

    protected String getNodeEntityInterfaceFQN() {
        return getState().getConfig().getRootNamespace() + ".Node";
    }

    protected String getRootNodeInterfaceFQN() {
        return getState().getConfig().getRootNamespace() + ".RootNode";
    }

    protected String getMappedNodeInterfaceFQN() {
        return getState().getConfig().getRootNamespace() + ".MappedNode";
    }

    protected String getNodeEntityClassFQN() {
        return getState().getConfig().getRootNamespace() + ".NodeImpl";
    }

    protected String getRootNodeEntityClassFQN() {
        return getState().getConfig().getRootNamespace() + ".RootNodeImpl";
    }

    protected String getModelTypeEnumFQN() {
        return getState().getConfig().getRootNamespace() + ".ModelType";
    }

    protected String getModelReaderInterfaceFQN() {
        return getState().getConfig().getRootNamespace() + ".io.ModelReader";
    }

    protected String getModelWriterInterfaceFQN() {
        return getState().getConfig().getRootNamespace() + ".io.ModelWriter";
    }

    protected String getRootVisitorInterfaceFQN() {
        return getState().getConfig().getRootNamespace() + ".visitors.Visitor";
    }

    protected String getAbstractTraverserFQN() {
        return getState().getConfig().getRootNamespace() + ".visitors.AbstractTraverser";
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

    protected String readMethodName(EntityModel entityModel) {
        return readMethodName(entityModel.getName());
    }

    protected String readMethodName(String entityName) {
        return "read" + StringUtils.capitalize(entityName);
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

    protected JavaInterfaceSource resolveJavaEntityType(NamespaceModel namespace, PropertyModel property) {
        return resolveJavaEntityType(namespace.fullName(), property.getType());
    }

    protected JavaInterfaceSource resolveJavaEntityType(String namespace, PropertyModel property) {
        return resolveJavaEntityType(namespace, property.getType());
    }

    protected JavaInterfaceSource resolveJavaEntityType(String namespace, PropertyType type) {
        return resolveJavaEntity(namespace, type.getSimpleType());
    }

    protected JavaInterfaceSource resolveJavaEntity(EntityModel entityModel) {
        return resolveJavaEntity(entityModel.getNamespace().fullName(), entityModel.getName());
    }

    protected JavaInterfaceSource resolveJavaEntity(String namespace, String entityName) {
        String _package = namespace;
        String prefix = getPrefix(namespace);
        String fqn = _package + "." + prefix + entityName;
        return lookupJavaEntity(fqn);
    }

    protected JavaInterfaceSource resolveCommonJavaEntity(EntityModel entityModel) {
        return resolveCommonJavaEntity(entityModel.getNamespace().fullName(), entityModel.getName());
    }

    protected JavaInterfaceSource resolveCommonJavaEntity(String namespace, String entityName) {
        EntityModel commonEntity = getState().getConceptIndex().lookupCommonEntity(namespace, entityName);
        return lookupJavaEntity(commonEntity);
    }

    protected boolean hasNamedMethod(MethodHolderSource<?> entityInterface, String methodName) {
        for (MethodSource<?> method : entityInterface.getMethods()) {
            if (method.getName().equals(methodName)) {
                return true;
            }
        }
        return false;
    }

    protected JavaInterfaceSource lookupJavaEntity(EntityModel entity) {
        return lookupJavaEntity(getJavaEntityInterfaceFQN(entity));
    }

    protected JavaInterfaceSource lookupJavaEntity(String fullyQualifiedName) {
        return getState().getJavaIndex().lookupInterface(fullyQualifiedName);
    }

    protected JavaInterfaceSource lookupJavaTrait(TraitModel trait) {
        return getState().getJavaIndex().lookupInterface(getJavaTraitInterfaceFQN(trait));
    }

    protected JavaClassSource lookupJavaEntityImpl(EntityModel entity) {
        return lookupJavaEntityImpl(getJavaEntityClassFQN(entity));
    }

    protected JavaClassSource lookupJavaEntityImpl(String fullyQualifiedName) {
        return getState().getJavaIndex().lookupClass(fullyQualifiedName);
    }

    protected JavaInterfaceSource lookupJavaVisitor(VisitorModel visitor) {
        String interfaceFQN = getVisitorInterfaceFullName(visitor);
        JavaInterfaceSource _interface = getState().getJavaIndex().lookupInterface(interfaceFQN);
        if (_interface == null) {
            warn("Visitor interface not found: " + interfaceFQN);
        }
        return _interface;
    }

    protected String prefixToModelType(String prefix) {
        return prefix.toUpperCase();
    }

}
