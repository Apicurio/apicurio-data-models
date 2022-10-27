package io.apicurio.umg.pipe.java;

import static io.apicurio.umg.pipe.java.Util.sanitizeFieldName;

import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.util.Types;

import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaFieldModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.pipe.AbstractStage;

public class JavaGetterStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            if (!t.isExternal()) {

                // Add fields with getters/setters
                t.getFields().forEach(fieldModel -> {

                    String fieldName = sanitizeFieldName(fieldModel.getName());

                    Type<?> fieldType = fieldModel.getTypeSource();

                    // Add a getter for the field.
                    if (t instanceof JavaInterfaceModel) {
                        var _interface = (JavaInterfaceModel) t;
                        var modelClass = _interface.getInterfaceSource();
                        String resolvedType = Types.toResolvedType(fieldType.getQualifiedNameWithGenerics(), modelClass.getOrigin());
                        _interface.getInterfaceSource()
                        .addMethod()
                        .setName(fieldGetter(fieldModel))
                        .setReturnType(resolvedType);

                    } else {
                        var _class = (JavaClassModel) t;
                        var modelClass = _class.getClassSource();
                        String resolvedType = Types.toResolvedType(fieldType.getQualifiedNameWithGenerics(), modelClass.getOrigin());
                        modelClass.addMethod()
                        .setName(fieldGetter(fieldModel))
                        .setReturnType(resolvedType)
                        .setPublic()
                        .setBody("return " + fieldName + ";");
                    }

                });

            }
        });
    }

    private static String fieldGetter(JavaFieldModel fieldModel) {
        boolean isBool = fieldModel.getPrimitiveType() != null && fieldModel.getPrimitiveType().endsWith("Boolean");
        return (isBool ? "is" : "get") + StringUtils.capitalize(fieldModel.getName());
    }
}
