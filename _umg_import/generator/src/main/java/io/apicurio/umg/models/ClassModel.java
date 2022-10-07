package io.apicurio.umg.models;

import static lombok.AccessLevel.NONE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

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
public class ClassModel {

    // Multiple parents are only valid for interfaces
    // TODO: Add a separate model for interfaces?
    @Builder.Default
    private Set<ClassModel> parents = new HashSet<>();

    @Getter(NONE)
    @Setter(NONE)
    private PackageModel _package;

    private String name;

    @Builder.Default
    private Map<String, FieldModel> fields = new HashMap<>();

    @Getter(NONE)
    @Setter(NONE)
    private boolean _abstract;

    private boolean core;

    private boolean _interface;

    @Builder.Default
    private Set<ClassModel> _implements = new HashSet<>();

    private JavaClassSource classSource;

    private JavaInterfaceSource interfaceSource;

    public PackageModel getPackage() {
        return _package;
    }

    public void setPackage(PackageModel _package) {
        this._package = _package;
    }

    public boolean isAbstract() {
        return _abstract;
    }

    public void setAbstract(boolean _abstract) {
        this._abstract = _abstract;
    }

    public String getFullyQualifiedName() {
        return getPackage().getName() + "." + getName();
    }

    public void addField(FieldModel fieldModel) {
        this.getFields().put(fieldModel.getName(), fieldModel);
    }
}
