package io.apicurio.umg.pipe.java;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.models.concept.NamespaceModel;
import io.apicurio.umg.models.concept.PropertyModelWithOrigin;

/**
 * Creates the union type interface.  For example, if the union type is "boolean|[string]" then
 * a new Java interface named "BooleanStringListUnion" will be created to represent a property
 * that can be either a boolean or a string list.
 *
 * Also for example, if there is a union type property that is "number|Widget", then a new Java
 * interface named "NumberWidgetUnion" will be created to represent that property (the value of
 * which can be either a number or a Widget entity).
 */
public class CreateUnionTypesStage extends AbstractUnionTypeJavaStage {

    @Override
    protected void doProcess(PropertyModelWithOrigin property) {
        createUnionType(property);
    }

    /**
     * @param property
     */
    private void createUnionType(PropertyModelWithOrigin property) {
        debug("Creating union type for: " + property.getProperty().getName());
        UnionPropertyType unionType = new UnionPropertyType(property.getProperty().getType());

        String name = unionType.getName();
        String _package = getUnionTypesPackageName();

        // Create the main union type interface
        JavaInterfaceSource unionTypeInterface = Roaster.create(JavaInterfaceSource.class)
                .setPackage(_package)
                .setName(name)
                .setPublic();

        // It must extend the "Union" interface
        String unionFQN = getUnionInterfaceFQN();
        JavaInterfaceSource unionValueSource = getState().getJavaIndex().lookupInterface(unionFQN);
        unionTypeInterface.addImport(unionValueSource);
        unionTypeInterface.addInterface(unionValueSource);

        // Now create the union methods.
        createUnionMethods(unionType, unionTypeInterface, property.getOrigin().getNamespace());

        getState().getJavaIndex().index(unionTypeInterface);
    }

    private void createUnionMethods(UnionPropertyType unionType, JavaInterfaceSource unionTypeInterface, NamespaceModel nsContext) {
        unionType.getNestedTypes().forEach(nestedType -> {
            String typeName = getTypeName(nestedType);
            String isMethodName = "is" + typeName;
            String asMethodName = "as" + typeName;

            JavaType jt = new JavaType(nestedType, nsContext.fullName());

            String asMethodReturnType = jt.toJavaTypeString();

            unionTypeInterface.addMethod().setName(isMethodName).setReturnType(boolean.class).setPublic();
            unionTypeInterface.addMethod().setName(asMethodName).setReturnType(asMethodReturnType).setPublic();
            jt.addImportsTo(unionTypeInterface);
        });
    }
}
