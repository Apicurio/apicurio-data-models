package io.apicurio.umg.pipe;

import io.apicurio.umg.logging.Logger;

public class DebugStage extends AbstractStage {

    @Override
    protected void doProcess() {
        Logger.debug("--- DEBUG ---");
        getState().getModelIndex().findNamespaces("io.apicurio").forEach(namespace -> {
            Logger.debug("Package: " + namespace.fullName());
            namespace.getEntities().values().forEach(entity -> {
                Logger.debug("    Entity: " + entity.getName());
                entity.getProperties().values().forEach(field -> {
                    Logger.debug("        Property: " + field.getName() + " (" + field.getType() + ")");
                });
            });
        });
        Logger.debug("---");
    }


}
