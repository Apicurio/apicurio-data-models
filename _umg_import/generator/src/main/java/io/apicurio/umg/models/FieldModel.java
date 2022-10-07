package io.apicurio.umg.models;

import org.jboss.forge.roaster.model.Type;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
