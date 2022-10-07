package io.apicurio.umg.models;

import io.apicurio.umg.beans.beans.Specification;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TraitModelId {

    private String specificationId;
    private String specificationVersion;
    private String traitName;

    public static TraitModelId create(Specification spec, TraitModel trait) {
        return TraitModelId.builder().specificationId(spec.getSpecification())
                .specificationVersion(spec.getVersion())
                .traitName(trait.getName())
                .build();
    }
}
