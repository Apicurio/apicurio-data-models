package io.apicurio.umg.pipe.java;

import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.umg.beans.SpecificationVersion;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaPackageModel;
import io.apicurio.umg.pipe.AbstractStage;

/**
 * Creates the i/o reader classes.  There is a bespoke reader for each specification
 * version.
 * @author eric.wittmann@gmail.com
 */
public class CreateReadersStage extends AbstractStage {

    @Override
    protected void doProcess() {
        if (Boolean.TRUE)
            return;
        getState().getSpecIndex().getAllSpecificationVersions().forEach(specVersion -> {
            String readerPackageName = specVersion.getNamespace() + ".io";
            String readerClassName = specVersion.getPrefix() + "ModelReader";
            JavaPackageModel readerPackage = getState().getJavaIndex().lookupAndIndexPackage(() -> {
                JavaPackageModel parentPackage = getState().getJavaIndex().lookupPackage(specVersion.getNamespace());
                JavaPackageModel packageModel = JavaPackageModel.builder()
                        .name(readerPackageName)
                        .parent(parentPackage)
                        .build();
                return packageModel;
            });
            JavaClassModel readerClass = JavaClassModel.builder()
                    ._package(readerPackage)
                    ._abstract(false)
                    .name(readerClassName)
                    .build();

            JavaClassSource readerClassSource = Roaster.create(JavaClassSource.class)
                    .setPackage(readerClass.get_package().getName())
                    .setName(readerClass.getName())
                    .setAbstract(readerClass.is_abstract())
                    .setPublic();

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
            createReadMethodFor(specVersion, readerClassSource, entityModel);
        });
    }

    /**
     * Creates a single "readXyx" method for the given entity.
     * @param specVersion
     * @param readerClassSource
     * @param entityModel
     */
    private void createReadMethodFor(SpecificationVersion specVersion, JavaClassSource readerClassSource, EntityModel entityModel) {
        String entityInterfaceName = specVersion.getPrefix() + entityModel.getName();
        String entityImplClassName = specVersion.getPrefix() + entityModel.getName() + "Impl";
        String readMethodName = "read" + entityModel.getName();

        MethodSource<JavaClassSource> methodSource = readerClassSource.addMethod()
                .setName(readMethodName)
                .setReturnType(entityModel.getNamespace().fullName() + "." + entityInterfaceName)
                .setPublic();
        methodSource.addParameter(ObjectNode.class, "node");

        StringBuilder body = new StringBuilder();
        body.append(entityImplClassName).append(" model = new ").append(entityImplClassName).append("();\n");
        entityModel.getProperties().values().forEach(property -> {
            createReadPropertyCode(body, property);
        });
        body.append("return model;\n");

        methodSource.setBody(body.toString());
    }

    /**
     * Generates the right java code for reading a single property of an entity.
     * @param body
     * @param property
     */
    private void createReadPropertyCode(StringBuilder body, PropertyModel property) {
        if (property.getType().isSimple() && !property.getType().isPrimitiveType()) {
            body.append("model.SETTER_NAME(this.readENTITY_NAME(node.get(\"PROPERTY_NAME\")));\n"
                    .replace("SETTER_NAME", fieldSetter(property))
                    .replace("ENTITY_NAME", property.getType().getSimpleType())
                    .replace("PROPERTY_NAME", property.getName()));
        }
    }

    private static String fieldSetter(PropertyModel property) {
        return "set" + StringUtils.capitalize(property.getName());
    }

}
