package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;

import java.util.Map;

public interface ReferenceLocalizationStrategy {


    /**
     * Add the node to the document (wrapped) as a definition.
     * Return the definition that was added or null on failure.
     *
     * @param model
     * @param name
     * @param component
     * @return
     * @throws java.lang.IllegalArgumentException if there is a naming conflict. // TODO some better way?
     */
    /*IDefinition*/String attachAsDefinition(Document model, String name, Node component);


    /**
     * Get a collection of local components, mapped by their local reference string
     *
     * @param model
     * @return
     */
    Map<String, Node> getExistingLocalComponents(Document model);
}
