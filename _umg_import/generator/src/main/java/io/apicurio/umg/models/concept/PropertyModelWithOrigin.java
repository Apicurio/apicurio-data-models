package io.apicurio.umg.models.concept;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PropertyModelWithOrigin {

    @Include
    private PropertyModel property;
    private EntityOrTraitModel origin;

}
