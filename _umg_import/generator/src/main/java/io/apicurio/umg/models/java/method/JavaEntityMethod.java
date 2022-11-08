package io.apicurio.umg.models.java.method;

public interface JavaEntityMethod {

    String getName();

    Flavor getFlavor();

    enum Flavor {
        GETTER,
        SETTER,
        FACTORY,
        VISITOR
    }
}
