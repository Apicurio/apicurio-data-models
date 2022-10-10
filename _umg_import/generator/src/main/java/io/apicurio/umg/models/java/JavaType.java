package io.apicurio.umg.models.java;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.HashMap;
import java.util.Map;

@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public abstract class JavaType {

    @EqualsAndHashCode.Include
    @ToString.Include
    private JavaPackageModel _package;

    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    private final Map<String, JavaFieldModel> fields = new HashMap<>();

    private boolean external;

    public String fullyQualifiedName() {
        return _package.getName() + "." + name;
    }

    public void addField(JavaFieldModel field) {
        fields.put(field.getName(), field);
    }

    public abstract JavaSource getJavaSource();
}
