package io.apicurio.umg.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jboss.forge.roaster.model.Type;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FieldModel {

    private String name;

    private String type;

    private Type javaType;
}
