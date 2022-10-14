package io.apicurio.umg.models.java;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class JavaPackageModel {

    /**
     * Full name
     */
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    private JavaPackageModel parent;

    private final Map<String, JavaPackageModel> children = new HashMap<>();

    private final Map<String, JavaEntityModel> types = new HashMap<>();

    public void addClass(JavaEntityModel type) {
        types.put(type.getName(), type);
    }
}
