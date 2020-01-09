package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
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
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30ParameterDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Response;
import io.apicurio.datamodels.openapi.v3.models.Oas30ResponseDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;
import io.apicurio.datamodels.openapi.v3.models.Oas30SchemaDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityScheme;

import java.util.LinkedHashMap;
import java.util.Map;

public class Oas30ReferenceLocalizationStrategy extends AbstractReferenceLocalizationStrategy implements ReferenceLocalizationStrategy {

    // throw
    // when attaching, we actually create a copy of the "component" node, so we should return it
    @Override
    public Pair attachAsDefinition(Document model0, String name, Node component) {
        Oas30Document model = (Oas30Document) model0; // TODO explicit check
//        component._ownerDocument = model0;
        // TODO reduce code duplication?
        if (component instanceof Oas30Schema) {
            if (model.components.getSchemaDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30SchemaDefinition definition = wrap(component, new Oas30SchemaDefinition(name), model);
            definition.attachToParent(model.components); // TODO this should be done by vvv
            model.components.addSchemaDefinition(definition.getName(), definition);
            //return definition;
            return new Pair(PREFIX + "schemas/" + name, definition);
        }
        if (component instanceof Oas30Response) {
            if (model.components.getResponseDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30ResponseDefinition definition = wrap(component, new Oas30ResponseDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addResponseDefinition(definition.getName(), definition);
            //return definition;
            return new Pair(PREFIX + "responses/" + name, definition);
        }
        if (component instanceof Oas30Parameter) {
            if (model.components.getParameterDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30ParameterDefinition definition = wrap(component, new Oas30ParameterDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addParameterDefinition(definition.getName(), definition);
            //return definition;
            return new Pair(PREFIX + "parameters/" + name, definition);
        }
        if (component instanceof Oas30Example) {
            if (model.components.getExampleDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30ExampleDefinition definition = wrap(component, new Oas30ExampleDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addExampleDefinition(definition.getName(), definition);
            //return definition;
            return new Pair(PREFIX + "examples/" + name, definition);
        }
        if (component instanceof Oas30RequestBody) {
            if (model.components.getRequestBodyDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30RequestBodyDefinition definition = wrap(component, new Oas30RequestBodyDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addRequestBodyDefinition(definition.getName(), definition);
            //return definition;
            return new Pair(PREFIX + "requestBodies/" + name, definition);
        }
        if (component instanceof Oas30Header) {
            if (model.components.getHeaderDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30HeaderDefinition definition = wrap(component, new Oas30HeaderDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addHeaderDefinition(definition.getName(), definition);
            //return definition;
            return new Pair(PREFIX + "headers/" + name, definition);
        }
        if (component instanceof ModernSecurityScheme) {
            if (model.components.getSecurityScheme(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30SecurityScheme definition = wrap(component, new Oas30SecurityScheme(name), model);
            definition.attachToParent(model.components);
            model.components.addSecurityScheme(definition.getName(), definition);
            //return definition;
            return new Pair(PREFIX + "securitySchemes/" + name, definition);
        }
        if (component instanceof Oas30Link) {
            if (model.components.getLinkDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30LinkDefinition definition = wrap(component, new Oas30LinkDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addLinkDefinition(definition.getName(), definition);
            //return definition;
            return new Pair(PREFIX + "links/" + name, definition);
        }
        if (component instanceof Oas30Callback) {
            if (model.components.getCallbackDefinition(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30CallbackDefinition definition = wrap(component, new Oas30CallbackDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addCallbackDefinition(definition.getName(), definition);
            //return definition;
            return new Pair(PREFIX + "callbacks/" + name, definition);
        }
        return null;
    }


    @Override
    public Map<String, io.apicurio.datamodels.core.models.Node> getExistingLocalComponents(Document model0) {
        Oas30Document model = (Oas30Document) model0; // TODO explicit check
        // We could use a local resolver here theoretically, but the reverse approach should be easier and faster
        Map<String, io.apicurio.datamodels.core.models.Node> res = new LinkedHashMap<>();
        if(model.components != null) {
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


}
