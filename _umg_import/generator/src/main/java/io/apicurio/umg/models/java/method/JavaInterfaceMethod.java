package io.apicurio.umg.models.java.method;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

public interface JavaInterfaceMethod extends JavaEntityMethod {

    void apply(JavaInterfaceSource source);
}
