package io.apicurio.umg.pipe.java;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.models.concept.EntityModel;

/**
 * Creates the java interfaces for all entities.
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateEntityInterfacesStage extends AbstractJavaStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findEntities("").forEach(entity -> {
            createEntityInterface(entity);
        });
    }

    private void createEntityInterface(EntityModel entity) {
        String _package = getJavaEntityInterfacePackage(entity);
        String name = getJavaEntityInterfaceName(entity);

        JavaInterfaceSource entityInterface = Roaster.create(JavaInterfaceSource.class)
                .setPackage(_package)
                .setName(name)
                .setPublic();

        getState().getJavaIndex().index(entityInterface);
    }
}