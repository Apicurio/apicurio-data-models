package io.apicurio.umg.models.java.method;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public interface JavaClassMethod extends JavaEntityMethod {

    void apply(JavaClassSource source);
}
