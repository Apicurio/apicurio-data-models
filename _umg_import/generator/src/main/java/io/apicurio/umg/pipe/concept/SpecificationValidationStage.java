package io.apicurio.umg.pipe.concept;

import java.util.Set;

import io.apicurio.umg.beans.Entity;
import io.apicurio.umg.beans.Trait;
import io.apicurio.umg.pipe.AbstractStage;

/**
 * Validate the input specifications.
 * @author eric.wittmann@gmail.com
 */
public class SpecificationValidationStage extends AbstractStage {

    @Override
    protected void doProcess() {
        getState().getSpecIndex().getAllSpecifications().forEach(spec -> {
            require(spec.getName(), "Specification missing required property 'name'.");
            require(spec.getNamespace(), "Specification %s missing required property '%s'.", spec.getName(), "namespace");
            require(spec.getPrefix(), "Specification %s missing required property '%s'.", spec.getName(), "prefix");
            require(spec.getVersions(), "Specification %s missing required property '%s'.", spec.getName(), "versions");
        });
        getState().getSpecIndex().getAllSpecificationVersions().forEach(specVer -> {
            require(specVer.getName(), "Specification version missing required property 'name'.");
            require(specVer.getNamespace(), "Specification version '%s' missing required property 'namespace'.", specVer.getName());
            require(specVer.getPrefix(), "Specification version '%s' missing required property 'prefix'.", specVer.getName());
            require(specVer.getVersion(), "Specification version '%s' missing required property 'version'.", specVer.getName());

            Set<Trait> traits = specVer.getTraits();
            traits.forEach(trait -> {
                if (trait.getProperties() == null || trait.getProperties().isEmpty()) {
                    fail("Trait '%s' in specification version '%s' must have at least one property.", trait.getName(), specVer.getName());
                } else {
                    // TODO validate properties
                }
            });
            Set<Entity> entities = specVer.getEntities();
            entities.forEach(entity -> {
                if (entity.getProperties() == null || entity.getProperties().isEmpty()) {
                    fail("Entity '%s' in specification version '%s' must have at least one property.", entity.getName(), specVer.getName());
                } else {
                    // TODO validate properties
                }
            });
            long numRootEntities = entities.stream().filter(entity -> entity.getRoot() != null && entity.getRoot()).count();
            if (numRootEntities != 1) {
                fail("Specification version '%s' must have one and only one root entity.", specVer.getName());
            }
        });
    }

    private void require(Object propertyValue, String message, Object ... args) {
        if (propertyValue == null) {
            fail(message, args);
        }
    }

    private void fail(String message, Object ... args) {
        String formattedMsg = String.format(message, args);
        throw new RuntimeException(formattedMsg);
    }

}
