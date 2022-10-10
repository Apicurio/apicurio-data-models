package io.apicurio.umg.models.concept;

import io.apicurio.umg.beans.Specification;
import io.apicurio.umg.beans.Trait;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TraitId {

    private String specificationId;
    private String specificationVersion;
    private String traitName;

    public static TraitId create(Specification spec, Trait trait) {
        return TraitId.builder().specificationId(spec.getSpecification())
                .specificationVersion(spec.getVersion())
                .traitName(trait.getName())
                .build();
    }
}
