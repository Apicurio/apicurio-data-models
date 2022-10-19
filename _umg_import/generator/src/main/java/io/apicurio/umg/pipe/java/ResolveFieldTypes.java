package io.apicurio.umg.pipe.java;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.java.JavaEntityModel;
import io.apicurio.umg.models.java.JavaFieldModel;
import io.apicurio.umg.models.java.JavaPackageModel;
import io.apicurio.umg.pipe.AbstractStage;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Map.entry;

/**
 * Go over field types and resolve them (not the sources yet)
 */
public class ResolveFieldTypes extends AbstractStage {

    // TODO Move this map into some shared place?
    public static Map<String, String> PRIMITIVE_TYPE_MAP = Map.ofEntries(
            entry("string", String.class.getName()),
            entry("boolean", Boolean.class.getName()),
            entry("number", Number.class.getName()),
            entry("integer", Integer.class.getName()),
            entry("object", ObjectNode.class.getCanonicalName()),
            entry("any", JsonNode.class.getCanonicalName())
    );

    @Override
    protected void doProcess() {

        getState().getJavaIndex().getAllJavaEntitiesWithCopy().forEach(je -> {
            je.getFields().forEach(f -> {

                var propertyType = f.getConcept().getType();

                // This stage can only process:
                // - a simple type
                // - a list of simple types
                // - a map of simple tpe
                // Previous stage must preprocess more complex types (TODO TransformComplexTypes)

                var stop = false;
                var nested = List.copyOf(propertyType.getNested());
                String simpleType = null;

                if (propertyType.isSimple()) {
                    // ok
                    f.setFlavor(JavaFieldModel.Flavor.NONE);
                    simpleType = propertyType.getSimpleType();

                } else if (propertyType.isList() && nested.get(0).isSimple()) {
                    // ok
                    f.setFlavor(JavaFieldModel.Flavor.LIST);
                    simpleType = nested.get(0).getSimpleType();

                } else if (propertyType.isMap() && nested.get(0).isSimple()) {
                    // ok
                    f.setFlavor(JavaFieldModel.Flavor.STRING_MAP);
                    simpleType = nested.get(0).getSimpleType();

                } else {
                    // Not supported!
                    Logger.warn("Could not process complex type '%s' in field %s", f.getConcept().getRawType(), f);
                    f.setFlavor(JavaFieldModel.Flavor.NONE);
                    f.setPrimitiveType("Object");
                    stop = true;
                }

                if (!stop) {
                    // Is this a primitive type?
                    var baseType = PRIMITIVE_TYPE_MAP.get(simpleType);
                    if (baseType != null) {
                        // yes, primitive type
                        f.setPrimitiveType(baseType);
                    } else {
                        // no, look for entity
                        // Does the type contain package?
                        String packageString = null;
                        String classString;
                        if (simpleType.contains(".")) {
                            var sep = simpleType.lastIndexOf(".");
                            packageString = simpleType.substring(0, sep);
                            classString = simpleType.substring(sep + 1);
                        } else {
                            classString = simpleType;
                            // Use the highest-level package that contains the entity
                            var _package = je.get_package();
                            JavaPackageModel highestPackage = null;
                            while (_package != null) {
                                var target = _package.getTypes().get(classString);
                                if (target != null) {
                                    highestPackage = _package;
                                }
                                _package = _package.getParent();
                            }
                            if (highestPackage != null) {
                                packageString = highestPackage.getName();
                            }
                        }
                        // Look for class with the given name
                        var classes = getState().getJavaIndex().getAllJavaEntitiesWithCopy().stream()
                                .filter(e -> e.getName().equals(classString))
                                .collect(Collectors.toList());
                        if (classes.size() == 0) {
                            Logger.warn("Could not find class '%s' for property %s. Using Object.", simpleType, f.getConcept());
                            f.setPrimitiveType("Object");
                        } else if (classes.size() == 1) {
                            f.setEntityType(classes.get(0));
                        } else {
                            // Try to match based on the package (if exists)
                            Optional<JavaEntityModel> type = Optional.ofNullable(packageString)
                                    .map(p -> getState().getJavaIndex().getPackages().get(p))
                                    .map(p -> {
                                        var filtered = classes.stream().filter(c -> c.get_package().getName().equals(p.getName()))
                                                .collect(Collectors.toList());
                                        return filtered.size() == 1 ? filtered.get(0) : null;
                                    });
                            if (type.isPresent()) {
                                f.setEntityType(type.get());
                            } else {
                                Logger.warn("Could not find class '%s' for property %s. Using Object. Possible candidates were %s", simpleType, f.getConcept(), classes);
                                f.setPrimitiveType("Object");
                            }
                        }
                    }
                }
            });
        });
    }
}
