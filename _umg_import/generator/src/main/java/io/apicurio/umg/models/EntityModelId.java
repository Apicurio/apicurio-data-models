package io.apicurio.umg.models;

import io.apicurio.umg.beans.beans.Specification;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntityModelId {

    private String specificationId;
    private String specificationVersion;
    private String entityName;

    public static EntityModelId create(Specification spec, EntityModel entity) {
        return EntityModelId.builder().specificationId(spec.getSpecification())
                .specificationVersion(spec.getVersion())
                .entityName(entity.getName())
                .build();
    }
}
