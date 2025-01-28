package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20PathItem;
import io.apicurio.datamodels.openapi.v2.models.Oas20Response;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20Schema;
import io.apicurio.datamodels.openapi.v2.models.Oas20SchemaDefinition;

import java.util.LinkedHashMap;
import java.util.Map;

public class Oas20IReferenceManipulationStrategy extends AbstractReferenceLocalizationStrategy implements IReferenceManipulationStrategy {

    protected String PREFIX = "#/";

    @Override
    public ReferenceAndNode attachAsComponent(Document document, String name, Node component) {
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

    @Override
    public String getComponentName(Document document, Node component) {
        if (component instanceof IDefinition)
            return ((IDefinition) component).getName();
        return null;
    }

    @Override
    public boolean removeComponent(Document document, String name) {
        if (document.getDocumentType() != DocumentType.openapi2) {
            throw new IllegalArgumentException("Oas20Document expected.");
        }
        Oas20Document model = (Oas20Document) document;
        IDefinition removed = model.definitions.items.remove(name);
        if (removed != null) return true;
        removed = model.parameters.items.remove(name);
        if (removed != null) return true;
        return model.responses.items.remove(name) != null;
    }

    @Override
    public boolean mergeNode(Node from, Node to) {
        if (to instanceof Oas20PathItem && from instanceof Oas20PathItem) {
            mergePathItem((Oas20PathItem) from, (Oas20PathItem) to);
            return true;
        }
        return false;
    }

    private static void mergePathItem(Oas20PathItem from, Oas20PathItem to) {
        if (to.get == null && from.get != null) {
            to.get = cloneNode(from.get, () -> to.createOperation("get"));
        }
        if (to.put == null && from.put != null) {
            to.put = cloneNode(from.put, () -> to.createOperation("put"));
        }
        if (to.post == null && from.post != null) {
            to.post = cloneNode(from.post, () -> to.createOperation("post"));
        }
        if (to.delete == null && from.delete != null) {
            to.delete = cloneNode(from.delete, () -> to.createOperation("delete"));
        }
        if (to.options == null && from.options != null) {
            to.options = cloneNode(from.options, () -> to.createOperation("options"));
        }
        if (to.head == null && from.head != null) {
            to.head = cloneNode(from.head, () -> to.createOperation("head"));
        }
        if (to.patch == null && from.patch != null) {
            to.patch = cloneNode(from.patch, () -> to.createOperation("patch"));
        }
        if (to.parameters == null && from.parameters != null) {
            to.parameters = cloneNodes(from.parameters, to::createParameter);
        }
        to.setReference(null);
    }
}
