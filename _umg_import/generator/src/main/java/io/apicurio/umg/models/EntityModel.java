package io.apicurio.umg.models;

import io.apicurio.umg.beans.beans.Entity;
import io.apicurio.umg.beans.beans.Specification;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntityModel {

    private Entity entity;

    private Specification spec;

    private ClassModel classModel;

}
