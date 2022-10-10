package io.apicurio.umg.pipe;

import java.util.Arrays;
import java.util.List;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.NamespaceModel;

public class CreateNamespaceModelsStage extends AbstractStage {

    @Override
    protected void doProcess() {
        Logger.info("-- Creating Namespace Models --");
        getState().getSpecifications().forEach(spec -> {
            String ns = spec.getNamespace();
            this.makeNamespaces(ns);
        });
    }

    /**
     * Creates the namespace (if it does not yet exist) and all parent namespaces
     * (if necessary).
     */
    private NamespaceModel makeNamespaces(String namespaceName) {
        List<String> nsComponents = Arrays.asList(namespaceName.split("\\."));
        String currentNs = null;
        NamespaceModel lastModel = null;
        for (String nsComponent : nsComponents) {
            currentNs = (currentNs == null) ? nsComponent : currentNs + "." + nsComponent;
            final NamespaceModel parentModel = lastModel;
            NamespaceModel nsModel = getState().getModelIndex().lookupNamespace(currentNs, (ns) -> {
                NamespaceModel rval = NamespaceModel.builder().name(nsComponent).parent(parentModel).build();
                if (parentModel != null) {
                    parentModel.getChildren().put(nsComponent, rval);
                }
                Logger.info("Created namespace model: %s", rval.fullName());
                getState().getModelIndex().index(rval);
                return rval;
            });
            lastModel = nsModel;
        }
        return lastModel;
    }

}
