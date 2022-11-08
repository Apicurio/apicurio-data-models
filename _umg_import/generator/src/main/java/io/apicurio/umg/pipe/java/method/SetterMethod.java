package io.apicurio.umg.pipe.java.method;

import io.apicurio.umg.models.java.JavaFieldModel;
import io.apicurio.umg.models.java.method.JavaClassMethod;
import io.apicurio.umg.models.java.method.JavaInterfaceMethod;
import io.apicurio.umg.pipe.java.Util;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import static io.apicurio.umg.pipe.java.Util.sanitizeFieldName;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class SetterMethod implements JavaInterfaceMethod, JavaClassMethod {

    private JavaFieldModel target;

    @Override
    @EqualsAndHashCode.Include
    @ToString.Include
    public String getName() {
        return Util.fieldSetter(target);
    }

    @Override
    @ToString.Include
    public Flavor getFlavor() {
        return Flavor.SETTER;
    }

    @Override
    public void apply(JavaInterfaceSource source) {
        String fieldName = sanitizeFieldName(target.getName());
        Type<?> fieldType = target.getTypeSource();
        source.addMethod()
                .setReturnTypeVoid()
                .setName(getName())
                .addParameter(fieldType.getQualifiedNameWithGenerics(), fieldName);
    }

    @Override
    public void apply(JavaClassSource source) {
        String fieldName = sanitizeFieldName(target.getName());
        Type<?> fieldType = target.getTypeSource();
        source.addMethod()
                .setPublic()
                .setReturnTypeVoid()
                .setName(getName())
                .setBody(BodyBuilder.create()
                        .c("fieldName", fieldName)
                        .a("this.${fieldName} = ${fieldName};")
                        .toString()
                )
                .addParameter(fieldType.getQualifiedNameWithGenerics(), fieldName);
    }
}
