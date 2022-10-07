package io.apicurio.umg.pipe.java;

import static io.apicurio.umg.pipe.java.Util.sanitizeFieldName;

import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.util.Types;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.FieldModel;
import io.apicurio.umg.pipe.AbstractStage;

public class JavaSetterStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getIndex().findClasses("").forEach(model -> {
            if (!model.isCore()) {

                Logger.info("Generating model for entity '%s'", model.getName());


                // Add fields with getters/setters
                model.getFields().values().forEach(fieldModel -> {

                    String fieldName = sanitizeFieldName(fieldModel.getName());
                    if (!"*".equals(fieldName)) {

                        Type fieldType = fieldModel.getJavaType();

                        // Add a setter for the field.
                        if(model.is_interface()) {
                            var modelClass = model.getInterfaceSource();
                            String resolvedType = Types.toResolvedType(fieldType.getQualifiedNameWithGenerics(), modelClass.getOrigin());
                            model.getInterfaceSource()
                                    .addMethod()
                                    .setName(fieldSetter(fieldModel))
                                    .setReturnTypeVoid()
                                    .addParameter(resolvedType, fieldName);
                                    // TODO What happens if I use set body?

                        } else {
                            var modelClass = model.getClassSource();
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

    private static String fieldSetter(FieldModel fieldModel) {
        return "set" + StringUtils.capitalize(fieldModel.getName());
    }
}
