package io.apicurio.umg.pipe.java;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.pipe.GenState;
import io.apicurio.umg.pipe.Stage;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.util.Types;

import java.util.Map;

import static io.apicurio.umg.pipe.java.Util.sanitizeFieldName;

public class JavaFieldStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;
        var resolver = state.getJavaTypeResolver();

        state.getIndex().findClasses("").forEach(model -> {
            if (!model.isCore()) {

                Logger.info("Generating model for entity '%s'", model.getName());


                // Add fields with getters/setters
                model.getFields().values().forEach(fieldModel -> {



                    String fieldName = sanitizeFieldName(fieldModel.getName());
                    if (!"*".equals(fieldName)) {

                        Type fieldType = resolver.resolveType(fieldModel.getType(), state.getIndex(), model.getPackage());
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
