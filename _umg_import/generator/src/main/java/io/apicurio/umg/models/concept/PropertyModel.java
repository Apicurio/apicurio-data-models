package io.apicurio.umg.models.concept;

import java.util.List;

import io.apicurio.umg.beans.UnionRule;
import lombok.Builder;
import lombok.Data;

/**
 * Models a single property in an entity or trait.
 */
@Builder
@Data
public class PropertyModel {

    private String name;

    private String collection;

    private String discriminator;

    private String rawType;

    private List<UnionRule> unionRules;

    private PropertyType type;

    public UnionRule getRuleFor(String rawUnionSubtype) {
        if (unionRules != null) {
            return unionRules.stream().filter(rule -> rule.getUnionType().equals(rawUnionSubtype)).findFirst().orElse(null);
        }
        return null;
    }
}
