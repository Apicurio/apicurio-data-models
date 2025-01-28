package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.ModernSecurityScheme;
import io.apicurio.datamodels.openapi.v3.models.Oas30Callback;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;
import io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Link;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30ParameterDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30PathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Response;
import io.apicurio.datamodels.openapi.v3.models.Oas30ResponseDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;
import io.apicurio.datamodels.openapi.v3.models.Oas30SchemaDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityScheme;

import java.util.LinkedHashMap;
import java.util.Map;

public class Oas30IReferenceManipulationStrategy extends AbstractReferenceLocalizationStrategy implements IReferenceManipulationStrategy {


    @Override
    public ReferenceAndNode attachAsComponent(Document document, String name, Node component) {
        // TODO visitor?
        if (!(document instanceof Oas30Document)) // TODO get documenttype
            throw new IllegalArgumentException("Oas30Document expected.");
        Oas30Document model = (Oas30Document) document;

        if (model.components == null) {
            model.components = model.createComponents();
        }
        
        if (component instanceof Oas30Schema) {
            if (model.components.getSchemaDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30SchemaDefinition definition = wrap(component, new Oas30SchemaDefinition(name), model);
            definition.attachToParent(model.components); // TODO this should be done by vvv
            model.components.addSchemaDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "schemas/" + name, definition);
        }
        if (component instanceof Oas30Response) {
            if (model.components.getResponseDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30ResponseDefinition definition = wrap(component, new Oas30ResponseDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addResponseDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "responses/" + name, definition);
        }
        if (component instanceof Oas30Parameter) {
            if (model.components.getParameterDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30ParameterDefinition definition = wrap(component, new Oas30ParameterDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addParameterDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "parameters/" + name, definition);
        }
        if (component instanceof Oas30Example) {
            if (model.components.getExampleDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30ExampleDefinition definition = wrap(component, new Oas30ExampleDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addExampleDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "examples/" + name, definition);
        }
        if (component instanceof Oas30RequestBody) {
            if (model.components.getRequestBodyDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30RequestBodyDefinition definition = wrap(component, new Oas30RequestBodyDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addRequestBodyDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "requestBodies/" + name, definition);
        }
        if (component instanceof Oas30Header) {
            if (model.components.getHeaderDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30HeaderDefinition definition = wrap(component, new Oas30HeaderDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addHeaderDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "headers/" + name, definition);
        }
        if (component instanceof ModernSecurityScheme) {
            if (model.components.getSecurityScheme(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30SecurityScheme definition = wrap(component, new Oas30SecurityScheme(name), model);
            definition.attachToParent(model.components);
            model.components.addSecurityScheme(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "securitySchemes/" + name, definition);
        }
        if (component instanceof Oas30Link) {
            if (model.components.getLinkDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30LinkDefinition definition = wrap(component, new Oas30LinkDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addLinkDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "links/" + name, definition);
        }
        if (component instanceof Oas30Callback) {
            if (model.components.getCallbackDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30CallbackDefinition definition = wrap(component, new Oas30CallbackDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addCallbackDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "callbacks/" + name, definition);
        }
        return null;
    }


    @Override
    public Map<String, io.apicurio.datamodels.core.models.Node> getExistingLocalComponents(Document document) {
        if (document.getDocumentType() != DocumentType.openapi3) {
            throw new IllegalArgumentException("Oas30Document expected.");
        }
        Oas30Document model = (Oas30Document) document;
        // We could use a local resolver here theoretically, but the reverse approach should be easier and faster
        Map<String, io.apicurio.datamodels.core.models.Node> res = new LinkedHashMap<>();
        if (model.components != null) {
            transform(model.components.schemas, name -> PREFIX + "schemas/" + name, res);
            transform(model.components.responses, name -> PREFIX + "responses/" + name, res);
            transform(model.components.parameters, name -> PREFIX + "parameters/" + name, res);
            transform(model.components.examples, name -> PREFIX + "examples/" + name, res);
            transform(model.components.requestBodies, name -> PREFIX + "requestBodies/" + name, res);
            transform(model.components.headers, name -> PREFIX + "headers/" + name, res);
            transform(model.components.securitySchemes, name -> PREFIX + "securitySchemes/" + name, res);
            transform(model.components.links, name -> PREFIX + "links/" + name, res);
            transform(model.components.callbacks, name -> PREFIX + "callbacks/" + name, res);
        }
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
        if (document.getDocumentType() != DocumentType.openapi3) {
            throw new IllegalArgumentException("Oas30Document expected.");
        }
        Oas30Document model = (Oas30Document) document;
        IDefinition removed = model.components.schemas.remove(name);
        if (removed != null) return true;
        removed = model.components.responses.remove(name);
        if (removed != null) return true;
        removed = model.components.parameters.remove(name);
        if (removed != null) return true;
        removed = model.components.examples.remove(name);
        if (removed != null) return true;
        removed = model.components.requestBodies.remove(name);
        if (removed != null) return true;
        removed = model.components.headers.remove(name);
        if (removed != null) return true;
        removed = model.components.securitySchemes.remove(name);
        if (removed != null) return true;
        removed = model.components.links.remove(name);
        if (removed != null) return true;
        return model.components.callbacks.remove(name) != null;
    }


    @Override
    public boolean mergeNode(Node from, Node to) {
        if (to instanceof Oas30PathItem && from instanceof Oas30PathItem) {
            mergePathItem((Oas30PathItem) from, (Oas30PathItem) to);
            return true;
        }
        return false;
    }
    
    private static void mergePathItem(Oas30PathItem from, Oas30PathItem to) {
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
        if (to.summary == null && from.summary != null) {
            to.summary = from.summary;
        }
        if (to.description == null && from.description != null) {
            to.description = from.description;
        }
        if (to.trace == null && from.trace != null) {
            to.trace = cloneNode(from.trace, () -> new Oas30Operation("trace"));
        }
        if (to.servers == null && from.servers != null) {
            to.servers = from.servers;
        }
        to.setReference(null);
    }
}
