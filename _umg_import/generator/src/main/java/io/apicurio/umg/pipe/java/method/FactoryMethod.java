package io.apicurio.umg.pipe.java.method;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.java.JavaEntityModel;
import io.apicurio.umg.models.java.JavaFieldModel;
import io.apicurio.umg.models.java.method.JavaClassMethod;
import io.apicurio.umg.models.java.method.JavaInterfaceMethod;
import io.apicurio.umg.pipe.GeneratorState;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import java.util.ArrayList;
import java.util.List;

import static io.apicurio.umg.pipe.java.Util.sanitizeFieldName;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class FactoryMethod implements JavaInterfaceMethod, JavaClassMethod {

    private JavaFieldModel targetField;

    private JavaEntityModel javaEntity;

    private GeneratorState state;

    private boolean prepared;

    private List<JavaEntityModel> implJavaEntities;

    @Override
    @EqualsAndHashCode.Include
    @ToString.Include
    public String getName() {
        return "create" + StringUtils.capitalize(getEntityOrTraitModelName(targetField.getEntityType()));
    }

    private String getEntityOrTraitModelName(JavaEntityModel fieldEntity) {
        String fieldEntityOriginalName; // i.e. without the prefix
        if (fieldEntity.getEntityModel() != null) {
            fieldEntityOriginalName = fieldEntity.getEntityModel().getName();
        } else if (fieldEntity.getTraitModel() != null) {
            fieldEntityOriginalName = fieldEntity.getTraitModel().getName();
        } else {
            throw new RuntimeException("TODO");
        }
        return fieldEntityOriginalName;
    }

    @Override
    @ToString.Include
    public Flavor getFlavor() {
        return Flavor.FACTORY;
    }

    @Override
    public void apply(JavaInterfaceSource source) {
        prepare();

        // We need to know if we can provide an implementation
        if (implJavaEntities.size() != 1) {
            Logger.warn("Expected 1 Java entity in implementation search for factory method '%s' " +
                    "in Java interface. Skipping factory method.", getName());
        } else {
            source.addMethod()
                    .setReturnType(targetField.getEntityType().getSource().getQualifiedName())
                    .setName(getName());
        }
    }

    @Override
    public void apply(JavaClassSource source) {
        prepare();
        String fieldName = sanitizeFieldName(targetField.getName());

        if (implJavaEntities.size() != 1) {
            Logger.warn("Expected 1 Java entity in implementation search for factory method '%s' " +
                            "for field '%s' in Java entity '%s'. Got %s. Skipping factory method.",
                    getName(), targetField, javaEntity, implJavaEntities);
        } else {

            source.addMethod()
                    .setPublic()
                    .setReturnType(targetField.getEntityType().getSource().getQualifiedName())
                    .setName(getName())
                    .setBody(BodyBuilder.create()
                            .c("fieldName", fieldName)
                            .c("fieldType", targetField.getEntityType().getSource().getQualifiedName())
                            .c("implClassName", implJavaEntities.get(0).fullyQualifiedName())
                            .a("${fieldType} _new = new ${implClassName}();")
                            .a("return _new;")
                            .toString()
                    );
        }
    }

    private void prepare() {
        if (prepared) {
            return;
        }
        prepared = true;
        // Find the actual implementation class
        // Look for the entities with the same name
        var fieldEntity = targetField.getEntityType();

        implJavaEntities = new ArrayList<>();

        String fieldEntityOriginalName = getEntityOrTraitModelName(fieldEntity);

        getState().getJavaIndex().getAllJavaEntitiesWithoutCopy().forEach(je -> {
            je.ifClass(_class -> {
                String jeOriginalName = getEntityOrTraitModelName(je);
                if (fieldEntityOriginalName.equals(jeOriginalName) && je.get_package().equals(javaEntity.get_package())) {
                    implJavaEntities.add(je);
                }
            });
        });
    }
}
