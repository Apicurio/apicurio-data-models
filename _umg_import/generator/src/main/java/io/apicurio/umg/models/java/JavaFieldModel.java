package io.apicurio.umg.models.java;

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
public class JavaFieldModel {

    private String name;

    private String type;

    private Type javaType;
}
