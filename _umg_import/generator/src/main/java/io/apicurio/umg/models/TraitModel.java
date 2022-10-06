package io.apicurio.umg.models;

import io.apicurio.umg.beans.beans.Specification;
import io.apicurio.umg.beans.beans.Trait;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TraitModel {
	
    private Trait trait;

    private Specification spec;

    private ClassModel classModel;


}
