package io.apicurio.umg.pipe;

import java.util.stream.Collectors;

import io.apicurio.umg.logging.Logger;

public class DebugStage extends AbstractStage {

    @Override
    protected void doProcess() {
        Logger.debug("--- DEBUG ---");
        getState().getConceptIndex().findNamespaces("io.apicurio").forEach(namespace -> {
            Logger.debug("==========================");
            Logger.debug("Namespace: " + namespace.fullName());
            Logger.debug("==========================");
            namespace.getTraits().values().forEach(trait -> {
                Logger.debug("    Trait: " + trait.getName());
                trait.getProperties().values().forEach(field -> {
                    Logger.debug("        Property: " + field.getName() + " (" + field.getType() + ")");
                });
            });
            namespace.getEntities().values().forEach(entity -> {
                Logger.debug("    Entity: " + entity.getName());
                entity.getTraits().forEach(trait -> {
                    Logger.debug("        Trait: " + trait.getName());
                });
                entity.getProperties().values().forEach(field -> {
                    Logger.debug("        Property: " + field.getName() + " (" + field.getType() + ")");
                });
            });
            if (namespace.getVisitor() != null) {
                Logger.debug("    Visitor (%d entities): " + namespace.getVisitor().getEntities().stream().map(entity -> entity.getName()).collect(Collectors.toList()),
                        namespace.getVisitor().getEntities().size());
            }
        });
        Logger.debug("---");
    }


}
