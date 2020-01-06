package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.Library;
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
import java.util.Map.Entry;
import java.util.function.Function;

public class Oas30ReferenceLocalizationStrategy implements ReferenceLocalizationStrategy {

    private static final String PREFIX = "#/components/";

    /**
     * Warning: This method assumes that the definition and the represented object can be read from the same JSON,
     * i.e. effectively contain the same data.
     */
    private Node wrap(Node source, Node target) {
        return Library.readNode(Library.writeNode(source), target);
    }

    // throw
    @Override
    public String attachAsDefinition(Document model0, String name, Node component) {
        Oas30Document model = (Oas30Document) model0; // TODO explicit check
        // TODO reduce code duplication?
        if (component instanceof Oas30Schema) {
            if (model.components.getSchemaDefinition(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30SchemaDefinition definition = (Oas30SchemaDefinition) wrap(component, new Oas30SchemaDefinition(name));
            definition.attachToParent(model.components); // TODO this should be done by vvv
            model.components.addSchemaDefinition(definition.getName(), definition);
            //return definition;
            return PREFIX + "schemas/" + name;
        }
        if (component instanceof Oas30Response) {
            if (model.components.getResponseDefinition(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30ResponseDefinition definition = (Oas30ResponseDefinition) wrap(component, new Oas30SchemaDefinition(name));
            definition.attachToParent(model.components);
            model.components.addResponseDefinition(definition.getName(), definition);
            //return definition;
            return PREFIX + "responses/" + name;
        }
        if (component instanceof Oas30Parameter) {
            if (model.components.getParameterDefinition(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30ParameterDefinition definition = (Oas30ParameterDefinition) wrap(component, new Oas30SchemaDefinition(name));
            definition.attachToParent(model.components);
            model.components.addParameterDefinition(definition.getName(), definition);
            //return definition;
            return PREFIX + "parameters/" + name;
        }
        if (component instanceof Oas30Example) {
            if (model.components.getExampleDefinition(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30ExampleDefinition definition = (Oas30ExampleDefinition) wrap(component, new Oas30SchemaDefinition(name));
            definition.attachToParent(model.components);
            model.components.addExampleDefinition(definition.getName(), definition);
            //return definition;
            return PREFIX + "examples/" + name;
        }
        if (component instanceof Oas30RequestBody) {
            if (model.components.getRequestBodyDefinition(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30RequestBodyDefinition definition = (Oas30RequestBodyDefinition) wrap(component, new Oas30SchemaDefinition(name));
            definition.attachToParent(model.components);
            model.components.addRequestBodyDefinition(definition.getName(), definition);
            //return definition;
            return PREFIX + "requestBodies/" + name;
        }
        if (component instanceof Oas30Header) {
            if (model.components.getHeaderDefinition(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30HeaderDefinition definition = (Oas30HeaderDefinition) wrap(component, new Oas30SchemaDefinition(name));
            definition.attachToParent(model.components);
            model.components.addHeaderDefinition(definition.getName(), definition);
            //return definition;
            return PREFIX + "headers/" + name;
        }
        if (component instanceof ModernSecurityScheme) {
            if (model.components.getSecurityScheme(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30SecurityScheme definition = (Oas30SecurityScheme) wrap(component, new Oas30SecurityScheme(name));
            definition.attachToParent(model.components);
            model.components.addSecurityScheme(definition.getName(), definition);
            //return definition;
            return PREFIX + "securitySchemes/" + name;
        }
        if (component instanceof Oas30Link) {
            if (model.components.getLinkDefinition(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30LinkDefinition definition = (Oas30LinkDefinition) wrap(component, new Oas30SchemaDefinition(name));
            definition.attachToParent(model.components);
            model.components.addLinkDefinition(definition.getName(), definition);
            //return definition;
            return PREFIX + "links/" + name;
        }
        if (component instanceof Oas30Callback) {
            if (model.components.getCallbackDefinition(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Oas30CallbackDefinition definition = (Oas30CallbackDefinition) wrap(component, new Oas30SchemaDefinition(name));
            definition.attachToParent(model.components);
            model.components.addCallbackDefinition(definition.getName(), definition);
            //return definition;
            return PREFIX + "callbacks/" + name;
        }
        return null;
    }


    private void transform(Map<String, ? extends io.apicurio.datamodels.core.models.Node> source, Function<String, String> transformName, Map<String, io.apicurio.datamodels.core.models.Node> result) {
        for (Entry<String, ?> e : source.entrySet()) {
            result.put(transformName.apply(e.getKey()), (io.apicurio.datamodels.core.models.Node) e.getValue());
        }
    }


    @Override
    public Map<String, io.apicurio.datamodels.core.models.Node> getExistingLocalComponents(Document model0) {
        Oas30Document model = (Oas30Document) model0; // TODO explicit check
        // We could use a local resolver here theoretically, but the reverse approach should be easier and faster
        Map<String, io.apicurio.datamodels.core.models.Node> res = new LinkedHashMap<>();
        transform(model.components.schemas, name -> PREFIX + "schemas/" + name, res);
        transform(model.components.responses, name -> PREFIX + "responses/" + name, res);
        transform(model.components.parameters, name -> PREFIX + "parameters/" + name, res);
        transform(model.components.examples, name -> PREFIX + "examples/" + name, res);
        transform(model.components.requestBodies, name -> PREFIX + "requestBodies/" + name, res);
        transform(model.components.headers, name -> PREFIX + "headers/" + name, res);
        transform(model.components.securitySchemes, name -> PREFIX + "securitySchemes/" + name, res);
        transform(model.components.links, name -> PREFIX + "links/" + name, res);
        transform(model.components.callbacks, name -> PREFIX + "callbacks/" + name, res);
        return res;
    }
}
