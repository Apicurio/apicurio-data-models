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
public class GetterMethod implements JavaInterfaceMethod, JavaClassMethod {

    private JavaFieldModel target;

    @Override
    @EqualsAndHashCode.Include
    @ToString.Include
    public String getName() {
        return Util.fieldGetter(target);
    }

    @Override
    @ToString.Include
    public Flavor getFlavor() {
        return Flavor.GETTER;
    }

    @Override
    public void apply(JavaInterfaceSource source) {
        Type<?> fieldType = target.getTypeSource();
        source.addMethod()
                .setReturnType(fieldType.getQualifiedNameWithGenerics())
                .setName(getName());
    }

    @Override
    public void apply(JavaClassSource source) {
        String fieldName = sanitizeFieldName(target.getName());
        Type<?> fieldType = target.getTypeSource();
        source.addMethod()
                .setPublic()
                .setReturnType(fieldType.getQualifiedNameWithGenerics())
                .setName(getName())
                .setBody("return " + fieldName + ";");
    }
}
