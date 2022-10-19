package io.apicurio.umg.pipe.java;

import io.apicurio.umg.pipe.AbstractStage;

/**
 * Transform nested types that are of format:
 * {(not-simple)}
 * [(not-simple)]
 * (any)|(any)|...
 * <p>
 * where:
 * (not-simple) is a type that is not a primitive type or an entity
 * (any) is both (not-simple) and (simple)
 * <p>
 * by introducing synthetic entities that (recursively) represent the nested type
 *
 * @author Jakub Senko <m@jsenko.net>
 */
public class TransformComplexTypes extends AbstractStage {

    @Override
    protected void doProcess() {

        getState().getJavaIndex().getAllJavaEntitiesWithCopy().forEach(je -> {
            je.getFields().forEach(f -> {

            });
        });
    }
}
