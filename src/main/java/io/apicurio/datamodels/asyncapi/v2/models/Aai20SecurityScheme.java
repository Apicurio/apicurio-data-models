package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;


public class Aai20SecurityScheme extends AaiSecurityScheme {


    public Aai20SecurityScheme(Node parent) {
        super(parent);
    }

    public Aai20SecurityScheme(Node parent, String name) {
        super(parent, name);
    }
}
