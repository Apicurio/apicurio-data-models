package io.apicurio.umg.models.java;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class JavaInterfaceModel extends JavaType {

    private final Set<JavaInterfaceModel> parents = new HashSet<>();

    private JavaInterfaceSource interfaceSource;

    @Override
    public JavaSource getJavaSource() {
        return interfaceSource;
    }
}
