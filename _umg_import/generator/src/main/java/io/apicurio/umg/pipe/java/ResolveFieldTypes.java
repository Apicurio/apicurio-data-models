package io.apicurio.umg.pipe.java;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.java.JavaEntityModel;
import io.apicurio.umg.models.java.JavaFieldModel;
import io.apicurio.umg.pipe.AbstractStage;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Map.entry;

/**
 * Go over field types and resolve them (not the sources yet)
 */
public class ResolveFieldTypes extends AbstractStage {

    private static Map<String, String> SIMPLE_TYPE_MAP = Map.ofEntries(
            entry("string", String.class.getName()),
            entry("boolean", Boolean.class.getName()),
            entry("number", Number.class.getName()),
            entry("integer", Integer.class.getName()),
            entry("object", Object.class.getName())
    );

    @Override
    protected void doProcess() {

        getState().getJavaIndex().getAllJavaEntitiesWithCopy().forEach(je -> {
            je.getFields().forEach(f -> {
                var propertyType = f.getConcept().getType();

                // First decide base type
                if (propertyType.isSimple()) {
                    var baseType = SIMPLE_TYPE_MAP.get(propertyType.getName());
                    if (baseType == null) {
                        Logger.error("Unknown simple type {}. Using Object.", propertyType.getName());
                        baseType = "Object";
                    }
                    f.setRawType(baseType);
                } else {
                    // Does the type contain package?
                    String packageString;
                    String classString;
                    if (propertyType.getName().contains(".")) {
                        var sep = propertyType.getName().lastIndexOf(".");
                        packageString = propertyType.getName().substring(0, sep);
                        classString = propertyType.getName().substring(sep + 1);
                    } else {
                        packageString = je.get_package().getName(); // Use the package where entity is located
                        classString = propertyType.getName();
                    }
                    // Look for class with the given name
                    var classes = getState().getJavaIndex().getAllJavaEntitiesWithCopy().stream()
                            .filter(e -> e.getName().equals(classString))
                            .collect(Collectors.toList());
                    if (classes.size() == 0) {
                        Logger.error("Could not find class for property %s. Using Object.", f.getConcept());
                        f.setRawType("Object");
                    } else if (classes.size() == 1) {
                        f.setEntityType(classes.get(0));
                    } else {
                        // Try to match based on the package (if exists)
                        Optional<JavaEntityModel> type = Optional.of(packageString)
                                .map(p -> getState().getJavaIndex().getPackages().get(p))
                                .map(p -> {
                                    var filtered = classes.stream().filter(c -> c.get_package().getName().equals(p.getName()))
                                            .collect(Collectors.toList());
                                    return filtered.size() == 1 ? filtered.get(0) : null;
                                });
                        if (type.isPresent()) {
                            f.setEntityType(type.get());
                        } else {
                            Logger.error("Could not find class for property %s. Using Object. Possible candidates were %s", f.getConcept(), classes);
                            f.setRawType("Object");
                        }
                    }
                }
                // We have a base type, set the flavor (TODO improve naming)
                if (propertyType.isList()) {
                    f.setFlavor(JavaFieldModel.Flavor.LIST);
                }
                if (propertyType.isMap()) {
                    f.setFlavor(JavaFieldModel.Flavor.STRING_MAP);
                }
            });
        });
    }
}
