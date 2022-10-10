package io.apicurio.umg.models.java;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class JavaClassModel extends JavaType {

    private JavaClassModel parent;

    private boolean _abstract;

    private final Set<JavaType> _implements = new HashSet<>();

    private JavaClassSource classSource;

    @Override
    public JavaSource getJavaSource() {
        return classSource;
    }
}
