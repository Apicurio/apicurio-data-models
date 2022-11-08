package io.apicurio.umg.models.java;

import io.apicurio.umg.models.java.method.JavaClassMethod;
import io.apicurio.umg.models.java.method.JavaEntityMethod;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

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

    private final Set<JavaClassMethod> classMethods = new HashSet<>();

    @Override
    public Set<JavaEntityMethod> getMethods() {
        return (Set<JavaEntityMethod>) (Set<? extends JavaEntityMethod>) classMethods;
    }

    @Override
    public JavaSource<?> getSource() {
        return classSource;
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public void ifInterface(Consumer<JavaInterfaceModel> f) {
        // NOOP
    }

    @Override
    public void ifClass(Consumer<JavaClassModel> f) {
        f.accept(this);
    }
}
