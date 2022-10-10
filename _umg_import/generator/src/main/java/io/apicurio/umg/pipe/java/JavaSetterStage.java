package io.apicurio.umg.pipe.java;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaFieldModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.pipe.AbstractStage;
import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.util.Types;

import static io.apicurio.umg.pipe.java.Util.sanitizeFieldName;

public class JavaSetterStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            if (!t.isExternal()) {

                Logger.info("Generating model for type '%s'", t.getName());


                // Add fields with getters/setters
                t.getFields().values().forEach(fieldModel -> {

                    String fieldName = sanitizeFieldName(fieldModel.getName());
                    if (!"*".equals(fieldName)) {

                        Type fieldType = fieldModel.getJavaType();

                        // Add a setter for the field.
                        if (t instanceof JavaInterfaceModel) {
                            var _interface = (JavaInterfaceModel) t;
                            var modelClass = _interface.getInterfaceSource();
                            String resolvedType = Types.toResolvedType(fieldType.getQualifiedNameWithGenerics(), modelClass.getOrigin());
                            _interface.getInterfaceSource()
                                    .addMethod()
                                    .setName(fieldSetter(fieldModel))
                                    .setReturnTypeVoid()
                                    .addParameter(resolvedType, fieldName);
                            // TODO What happens if I use set body?

                        } else {
                            var _class = (JavaClassModel) t;
                            var modelClass = _class.getClassSource();
                            String resolvedType = Types.toResolvedType(fieldType.getQualifiedNameWithGenerics(), modelClass.getOrigin());
                            modelClass.addMethod()
                                    .setName(fieldSetter(fieldModel))
                                    .setReturnTypeVoid()
                                    .setPublic()
                                    .setBody("this." + fieldName + " = " + fieldName + ";")
                                    .addParameter(resolvedType, fieldName);
                        }
                    }
                });

            }
        });
    }

    private static String fieldSetter(JavaFieldModel fieldModel) {
        return "set" + StringUtils.capitalize(fieldModel.getName());
    }
}
