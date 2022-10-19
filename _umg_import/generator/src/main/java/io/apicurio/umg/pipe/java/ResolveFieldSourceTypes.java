package io.apicurio.umg.pipe.java;

import io.apicurio.umg.models.java.JavaFieldModel;
import io.apicurio.umg.pipe.AbstractStage;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import java.util.List;

/**
 * Go over field types and resolve them to Roaster source types
 */
public class ResolveFieldSourceTypes extends AbstractStage {

    @Override
    protected void doProcess() {

        getState().getJavaIndex().getAllJavaEntitiesWithCopy().forEach(je -> {
            je.getFields().forEach(f -> {
                String baseType = null;
                if (f.getPrimitiveType() != null) {
                    baseType = f.getPrimitiveType();
                }
                if (f.getEntityType() != null) {
                    baseType = f.getEntityType().fullyQualifiedName();
                }
                if (f.getFlavor() == JavaFieldModel.Flavor.LIST) {
                    baseType = "java.util.List<" + baseType + ">";
                }
                if (f.getFlavor() == JavaFieldModel.Flavor.STRING_MAP) {
                    baseType = "java.util.Map<String, " + baseType + ">";
                }
                String stub = "public class Stub { public " + baseType + " method() {} }";
                JavaClassSource temp = (JavaClassSource) Roaster.parse(stub);
                List<MethodSource<JavaClassSource>> methods = temp.getMethods();
                f.setTypeSource(methods.get(0).getReturnType());
            });
        });
    }
}
