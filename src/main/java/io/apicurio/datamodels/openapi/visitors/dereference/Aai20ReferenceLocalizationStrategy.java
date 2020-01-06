package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;

import java.util.Map;

public class Aai20ReferenceLocalizationStrategy implements ReferenceLocalizationStrategy {


    @Override
    public String attachAsDefinition(Document model, String name, Node component) {
        return null;
    }


    @Override
    public Map<String, Node> getExistingLocalComponents(Document model) {
        return null;
    }
}
