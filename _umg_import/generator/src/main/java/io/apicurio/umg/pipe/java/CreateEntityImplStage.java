package io.apicurio.umg.pipe.java;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.models.concept.EntityModel;

/**
 * Creates the java implementation classes for all leaf entities.
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateEntityImplStage extends AbstractJavaStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findEntities("").stream().filter(entity -> entity.isLeaf()).forEach(entity -> {
            createEntityImpl(entity);
        });
    }

    private void createEntityImpl(EntityModel entity) {
        String _package = getEntityClassPackage(entity);
        String name = getEntityClassName(entity);

        JavaClassSource entityClass = Roaster.create(JavaClassSource.class)
                .setPackage(_package)
                .setName(name)
                .setPublic();

        JavaInterfaceSource entityInterface = getState().getJavaIndex().lookupInterface(getEntityInterfaceFQN(entity));
        addImportTo(entityInterface, entityClass);
        entityClass.addInterface(entityInterface);

        // All impl classes extend NodeImpl
        JavaClassSource nodeImpl = getState().getJavaIndex().lookupClass(getNodeEntityClassFQN());
        entityClass.extendSuperType(nodeImpl);

        getState().getJavaIndex().index(entityClass);
    }
}
