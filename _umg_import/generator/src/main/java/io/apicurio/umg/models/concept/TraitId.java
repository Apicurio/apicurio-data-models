package io.apicurio.umg.models.concept;

import io.apicurio.umg.beans.SpecificationVersion;
import io.apicurio.umg.beans.Trait;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TraitId {

    private SpecificationVersionId specVersionId;
    private String traitName;

    public static TraitId create(SpecificationVersion specVersion, Trait trait) {
        return TraitId.builder().specVersionId(SpecificationVersionId.create(specVersion))
                .traitName(trait.getName())
                .build();
    }
}
