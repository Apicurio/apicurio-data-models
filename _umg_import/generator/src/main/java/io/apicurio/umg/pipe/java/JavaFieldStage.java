package io.apicurio.umg.pipe.java;

import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.pipe.AbstractStage;
import org.jboss.forge.roaster.model.util.Types;

import static io.apicurio.umg.pipe.java.Util.sanitizeFieldName;

public class JavaFieldStage extends AbstractStage {
    @Override
    protected void doProcess() {

        getState().getJavaIndex().getTypes().values().forEach(t -> {
            if (!t.isExternal()) {

                // Add fields with getters/setters
                t.getFields().forEach(fieldModel -> {

                    String fieldName = sanitizeFieldName(fieldModel.getName());

                    if (t instanceof JavaClassModel) {
                        var _class = (JavaClassModel) t;
                        var modelClass = _class.getClassSource();
                        String resolvedType = Types.toResolvedType(fieldModel.getTypeSource().getQualifiedNameWithGenerics(), modelClass.getOrigin());
                        modelClass.addField()
                                .setName(fieldName)
                                .setPrivate()
                                .setType(resolvedType);

                    }
                });

            }
        });
    }


}
