package io.apicurio.umg.models.java;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaSource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class JavaClassModel extends JavaEntityModel {

    private JavaClassModel _extends;

    private boolean _abstract;

    private final Set<JavaInterfaceModel> _implements = new HashSet<>();

    private JavaClassSource classSource;

    @Override
    public JavaSource<?> getJavaSource() {
        return classSource;
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public void ifInterface(Consumer<JavaInterfaceModel> f) {
    }

    @Override
    public void ifClass(Consumer<JavaClassModel> f) {
        f.accept(this);
    }
}
