package io.apicurio.umg.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class PackageModel {

    @EqualsAndHashCode.Include
    private String name;

    private PackageModel parent;

    @Builder.Default
    @ToString.Exclude
    private Map<String, PackageModel> children = new HashMap<>();

    @Builder.Default
    @ToString.Exclude
    private Map<String, ClassModel> classes = new HashMap<>();

    @ToString.Include
    private Set<String> classesKeys() {
        return classes.keySet();
    }

    @ToString.Include
    private Set<String> childrenKeys() {
        return children.keySet();
    }

    public void addClass(ClassModel classModel) {
        this.getClasses().put(classModel.getName(), classModel);
    }

    public boolean containsClass(String className) {
        if (getClasses().containsKey(className)) {
            return true;
        }
        for (PackageModel childPackage : getChildren().values()) {
            if (childPackage.containsClass(className)) {
                return true;
            }
        }
        return false;
    }
}
