package io.apicurio.umg.models;

import io.apicurio.umg.beans.beans.Entity;
import io.apicurio.umg.beans.beans.Specification;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntityId {

    private String specificationId;
    private String specificationVersion;
    private String entityName;

    public static EntityId create(Specification spec, Entity entity) {
        return EntityId.builder().specificationId(spec.getSpecification())
                .specificationVersion(spec.getVersion())
                .entityName(entity.getName())
                .build();
    }
}
