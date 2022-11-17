package io.apicurio.umg.pipe;

import org.modeshape.common.text.Inflector;

import io.apicurio.umg.models.concept.PropertyModel;
import lombok.Getter;

/**
 * Base class for all pipeline stages.
 */
public abstract class AbstractStage implements Stage {

    private static final Inflector inflector = new Inflector();

    @Getter
    private GeneratorState state;

    @Override
    public void process(GeneratorState state) {
        this.state = state;
        this.doProcess();
    }

    /**
     * Perform the logic of the stage.
     */
    protected abstract void doProcess();

    protected boolean isStarProperty(PropertyModel property) {
        return "*".equals(property.getName());
    }

    protected boolean isRegexProperty(PropertyModel property) {
        return property.getName().startsWith("/");
    }

    protected boolean isEntityList(PropertyModel property) {
        return property.getType().isList() && property.getType().getNested().iterator().next().isEntityType();
    }

    protected boolean isEntityMap(PropertyModel property) {
        return property.getType().isMap() && property.getType().getNested().iterator().next().isEntityType();
    }

    protected boolean isEntity(PropertyModel property) {
        return property.getType().isEntityType();
    }

    protected boolean isPrimitive(PropertyModel property) {
        return property.getType().isPrimitiveType();
    }

    protected boolean isPrimitiveList(PropertyModel property) {
        return property.getType().isList() && property.getType().getNested().iterator().next().isPrimitiveType();
    }

    protected boolean isPrimitiveMap(PropertyModel property) {
        return property.getType().isMap() && property.getType().getNested().iterator().next().isPrimitiveType();
    }

    protected String singularize(String name) {
        return inflector.singularize(name);
    }

    protected String extractRegex(String propertyName) {
        return propertyName.substring(1, propertyName.length() - 2);
    }

}
