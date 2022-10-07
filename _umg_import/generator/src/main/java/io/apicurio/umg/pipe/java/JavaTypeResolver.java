package io.apicurio.umg.pipe.java;

import static java.util.Map.entry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import io.apicurio.umg.index.ClassIndex;
import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.PackageModel;

public class JavaTypeResolver {

    private Map<String, ClassModel> registeredTypes = new HashMap<>();

    private static Map<String, String> BASE_TYPE_MAP = Map.ofEntries(
            entry("string", String.class.getName()),
            entry("boolean", Boolean.class.getName()),
            entry("number", Number.class.getName()),
            entry("integer", Integer.class.getName()),
            entry("object", Object.class.getName())
    );

    /**
     * Resolves a type defined in a specfile to a Roaster {@link org.jboss.forge.roaster.model.Type}.
     */
    public Type<?> resolveType(String type, ClassIndex index, PackageModel packageModel) {

        boolean isList = false;
        boolean isMap = false;

        if (type.startsWith("[")) {
            isList = true;
            type = type.substring(1, type.length() - 1);
        }
        if (type.startsWith("{")) {
            isMap = true;
            type = type.substring(1, type.length() - 1);
        }

        String baseType = null;
        baseType = BASE_TYPE_MAP.get(type);
        if (baseType == null) {
            var t = registeredTypes.get(type);
            if (t != null) {
                baseType = t.getFullyQualifiedName();
            }
        }

        if (baseType == null) {
            ClassModel t;
            if (type.contains(".")) {
                t = index.lookupClass(type);
            } else {
                t = index.lookupClass(packageModel.getName() + "." + type);
            }
            if (t != null) {
                baseType = t.getFullyQualifiedName();
            }
        }

        if (baseType == null) {
            throw new RuntimeException("Unknown type: " + type);
        }

        if (isList) {
            baseType = "java.util.List<" + baseType + ">";
        }
        if (isMap) {
            baseType = "java.util.Map<String, " + baseType + ">";
        }

        String stub = "public class Stub { public " + baseType + " method() {} }";
        JavaClassSource temp = (JavaClassSource) Roaster.parse(stub);
        List<MethodSource<JavaClassSource>> methods = temp.getMethods();
        return methods.get(0).getReturnType();
    }

    public void registerType(String type, ClassModel model) {
        this.registeredTypes.put(type, model);
    }
}
