package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20Response;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20Schema;
import io.apicurio.datamodels.openapi.v2.models.Oas20SchemaDefinition;

import java.util.LinkedHashMap;
import java.util.Map;

public class Oas20IReferenceManipulationStrategy extends AbstractReferenceLocalizationStrategy implements IReferenceManipulationStrategy {

    protected String PREFIX = "#/";

    @Override
    public ReferenceAndNode attachAsDefinition(Document document, String name, Node component) {
        if (!(document instanceof Oas20Document))
            throw new IllegalArgumentException("Oas20Document expected.");
        Oas20Document model = (Oas20Document) document;
        if (component instanceof Oas20Schema) {
            if (model.definitions == null)
                model.definitions = model.createDefinitions();
            if (model.definitions.getDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas20SchemaDefinition definition = wrap(component, new Oas20SchemaDefinition(name), model);
            definition.attachToParent(model.definitions);
            model.definitions.addDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "definitions/" + name, definition);
        }
        if (component instanceof Oas20Parameter) {
            if (model.parameters == null)
                model.parameters = model.createParameterDefinitions();
            if (model.parameters.getParameter(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas20ParameterDefinition definition = wrap(component, new Oas20ParameterDefinition(name), model);
            definition.attachToParent(model.parameters);
            model.parameters.addParameter(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "parameters/" + name, definition);
        }
        if (component instanceof Oas20Response) {
            if (model.responses == null)
                model.responses = model.createResponseDefinitions();
            if (model.responses.getResponse(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas20ResponseDefinition definition = wrap(component, new Oas20ResponseDefinition(name), model);
            definition.attachToParent(model.responses);
            model.responses.addResponse(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "responses/" + name, definition);
        }
        return null;
    }


    @Override
    public Map<String, Node> getExistingLocalComponents(Document document) {
        if (!(document instanceof Oas20Document))
            throw new IllegalArgumentException("Oas20Document expected.");
        Oas20Document model = (Oas20Document) document;
        // We could use a local resolver here theoretically, but the reverse approach should be easier and faster
        Map<String, io.apicurio.datamodels.core.models.Node> res = new LinkedHashMap<>();

        if (model.definitions != null)
            transform(model.definitions.items, name -> PREFIX + "definitions/" + name, res);
        if (model.parameters != null)
            transform(model.parameters.items, name -> PREFIX + "parameters/" + name, res);
        if (model.responses != null)
            transform(model.responses.items, name -> PREFIX + "responses/" + name, res);
        return res;
    }
}
