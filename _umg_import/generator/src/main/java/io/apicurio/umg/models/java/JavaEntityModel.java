package io.apicurio.umg.models.java;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.jboss.forge.roaster.model.source.JavaSource;

import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.TraitModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public abstract class JavaEntityModel {

    @EqualsAndHashCode.Include
    @ToString.Include
    private JavaPackageModel _package;

    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    private final Set<JavaFieldModel> fields = new HashSet<>();

    private boolean external;

    private EntityModel entityModel;
    private TraitModel traitModel;

    public String fullyQualifiedName() {
        return _package.getName() + "." + name;
    }

    public abstract JavaSource<?> getJavaSource();

    public abstract boolean isInterface();

    public boolean isClass() {
        return !isInterface();
    }

    public abstract void ifInterface(Consumer<JavaInterfaceModel> f);

    public abstract void ifClass(Consumer<JavaClassModel> f);
}
