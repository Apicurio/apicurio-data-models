package io.apicurio.umg.pipe.java;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.pipe.AbstractStage;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.util.Types;

import static io.apicurio.umg.pipe.java.Util.sanitizeFieldName;

public class JavaFieldStage extends AbstractStage {
    @Override
    protected void doProcess() {

        var resolver = getState().getJavaTypeResolver();

        getState().getJavaIndex().getTypes().values().forEach(t -> {
            if (!t.isExternal()) {

                Logger.info("Generating model for type '%s'", t.getName());


                // Add fields with getters/setters
                t.getFields().values().forEach(fieldModel -> {


                    String fieldName = sanitizeFieldName(fieldModel.getName());
                    if (!"*".equals(fieldName)) {

                        Type fieldType = resolver.resolveType(fieldModel.getType(), getState().getJavaIndex(), t.get_package());
                        fieldModel.setJavaType(fieldType);
                        if (t instanceof JavaClassModel) {
                            var _class = (JavaClassModel) t;
                            var modelClass = _class.getClassSource();
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
