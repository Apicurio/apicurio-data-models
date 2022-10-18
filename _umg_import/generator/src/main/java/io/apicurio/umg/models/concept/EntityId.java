package io.apicurio.umg.models.concept;

import io.apicurio.umg.beans.Entity;
import io.apicurio.umg.beans.SpecificationVersion;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntityId {

    private SpecificationVersionId specVersionId;
    private String entityName;

    public static EntityId create(SpecificationVersion specVersion, Entity entity) {
        return EntityId.builder().specVersionId(SpecificationVersionId.create(specVersion))
                .entityName(entity.getName())
                .build();
    }
}
