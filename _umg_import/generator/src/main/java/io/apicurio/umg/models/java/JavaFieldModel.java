package io.apicurio.umg.models.java;

import org.jboss.forge.roaster.model.Type;

import io.apicurio.umg.models.concept.PropertyModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class JavaFieldModel {

    // Field name
    @EqualsAndHashCode.Include
    private String name;

    //private boolean resolved;

    private PropertyModel concept;

    // EITHER:
    // Raw Java type string, e.g. boolean, int, (java.lang.)Object
    private String primitiveType;

    // OR:
    // Entity in the model, including external entity such as com.fasterxml.jackson.databind.JsonNode
    private JavaEntityModel entityType;

    @Builder.Default
    private Flavor flavor = Flavor.NONE; // TODO Maybe just use the concept model?

    private Type<?> typeSource;

    public enum Flavor {
        NONE,
        //SET, TODO?
        STRING_MAP,
        LIST
    }
}
