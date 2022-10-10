package io.apicurio.umg.models;

import java.util.Collection;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VisitorModel {

    @Include
    private NamespaceModel namespace;
    private VisitorModel parent;
    private final Collection<VisitorModel> children = new HashSet<>();
    private final Collection<EntityModel> entities = new HashSet<>();

    @Override
    public String toString() {
        return "Visitor <" + namespace.fullName() + ">";
    }
}
