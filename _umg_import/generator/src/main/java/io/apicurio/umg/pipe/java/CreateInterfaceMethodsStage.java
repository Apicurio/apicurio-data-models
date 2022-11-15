package io.apicurio.umg.pipe.java;

import java.util.List;
import java.util.Map;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.JavaSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.TraitModel;

/**
 * Adds methods to all entity interfaces.  This works by finding all the properties for the
 * entity and then deciding what methods should exist on the entity interface based on the
 * name and type of the property.
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateInterfaceMethodsStage extends AbstractCreateMethodsStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findEntities("").forEach(entity -> {
            createEntityInterfaceMethods(entity);
        });
        getState().getConceptIndex().findTraits("").forEach(trait -> {
            createTraitInterfaceMethods(trait);
        });
    }

    private void createEntityInterfaceMethods(EntityModel entity) {
        entity.getProperties().values().forEach(property -> {
            createPropertyMethods(lookupEntity(entity), property);
        });
    }

    private void createTraitInterfaceMethods(TraitModel trait) {
        trait.getProperties().values().forEach(property -> {
            createPropertyMethods(lookupTrait(trait), property);
        });
    }

    /**
     * When an entity has a "*" property, that means the entity is a wrapper around a map
     * of values of a particular type.  In this case, the entity interface needs to extend
     * the "MappedNode" interface.
     *
     * @param javaEntity
     * @param property
     */
    @Override
    protected void createMappedNodeMethods(JavaSource<?> javaEntity, PropertyModel property) {
        String mappedNodeFQN = getMappedNodeInterfaceFQN();
        JavaInterfaceSource mappedNodeInterface = getState().getJavaIndex().lookupInterface(mappedNodeFQN);

        javaEntity.addImport(mappedNodeInterface);
        String mappedNodeInterfaceWithType;

        if (isPrimitiveList(property)) {
            Class<?> listType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(List.class);
            javaEntity.addImport(listType);
            String mappedType = "List<" + listType.getSimpleName() + ">";
            mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + mappedType + ">";
        } else if (isPrimitiveMap(property)) {
            Class<?> mapType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntity.addImport(Map.class);
            javaEntity.addImport(mapType);
            String mappedType = "Map<String, " + mapType.getSimpleName() + ">";
            mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + mappedType + ">";
        } else if (isPrimitive(property)) {
            Class<?> primType = primitiveTypeToClass(property.getType());
            javaEntity.addImport(primType);
            mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + primType.getSimpleName() + ">";
        } else if (isEntity(property)) {
            JavaInterfaceSource entityType = resolveEntityType(javaEntity.getPackage(), property);
            if (entityType == null) {
                Logger.error("Java interface for entity type not found: " + property.getType());
                return;
            } else {
                javaEntity.addImport(entityType);
                mappedNodeInterfaceWithType = mappedNodeInterface.getName() + "<" + entityType.getName() + ">";
            }
        } else {
            Logger.error("Unhandled STAR property from entity: " + javaEntity.getCanonicalName());
            return;
        }

        ((JavaInterfaceSource) javaEntity).addInterface(mappedNodeInterfaceWithType);
    }

}
