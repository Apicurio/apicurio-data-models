package io.apicurio.umg.pipe.java;

import static io.apicurio.umg.pipe.java.Util.sanitizeFieldName;

import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.util.Types;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.pipe.AbstractStage;

public class JavaFieldStage extends AbstractStage {
    @Override
    protected void doProcess() {
        var resolver = getState().getJavaTypeResolver();

        getState().getClassIndex().findClasses("").forEach(model -> {
            if (!model.isCore()) {

                Logger.info("Generating model for entity '%s'", model.getName());


                // Add fields with getters/setters
                model.getFields().values().forEach(fieldModel -> {



                    String fieldName = sanitizeFieldName(fieldModel.getName());
                    if (!"*".equals(fieldName)) {

                        Type fieldType = resolver.resolveType(fieldModel.getType(), getState().getClassIndex(), model.getPackage());
                        fieldModel.setJavaType(fieldType);
                        if(!model.is_interface()) {
                            var modelClass = model.getClassSource();
                            String resolvedType = Types.toResolvedType(fieldType.getQualifiedNameWithGenerics(), modelClass.getOrigin());
                            modelClass.addField()
                                    .setName(fieldName)
                                    .setPrivate()
                                    .setType(resolvedType);

                        }
                    }
                });

            }
        });
    }


}
