package io.apicurio.datamodels.openapi.visitors.dereference;

import java.util.LinkedHashMap;
import java.util.Map;

import io.apicurio.datamodels.asyncapi.models.AaiChannelBindings;
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindings;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindings;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindings;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20ChannelBindingsDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Components;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20CorrelationId;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Message;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20MessageBindingsDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20MessageTraitDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20OperationBindingsDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20OperationTraitDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Parameter;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SecurityScheme;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20ServerBindingsDefinition;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;

public class Aai20IReferenceManipulationStrategy extends AbstractReferenceLocalizationStrategy implements IReferenceManipulationStrategy {


    @Override
    public ReferenceAndNode attachAsDefinition(Document document, String name, Node component) {
        if(!(document instanceof Aai20Document))
            throw new IllegalArgumentException("Aai20Document expected.");
        
        Aai20Document model = (Aai20Document) document;
        if (model.components == null)
            model.components = model.createComponents();
        
        // TODO reduce code repetition?
        if (component instanceof Aai20Message) {
            if (model.components.messages != null && model.components.messages.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20Message definition = wrap(component, new Aai20Message(name), model);
            definition.attachToParent(model.components); // TODO this should be done by vvv
            model.components.addMessage(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "messages/" + name, definition);
        }
        if (component instanceof AaiSecurityScheme) {
            if (model.components.securitySchemes != null && model.components.securitySchemes.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20SecurityScheme definition = wrap(component, new Aai20SecurityScheme(name), model);
            definition.attachToParent(model.components);
            model.components.addSecurityScheme(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "securitySchemes/" + name, definition);
        }
        if (component instanceof AaiParameter) {
            if (model.components.parameters != null && model.components.parameters.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20Parameter definition = wrap(component, new Aai20Parameter(name), model);
            definition.attachToParent(model.components);
            model.components.addParameter(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "parameters/" + name, definition);
        }
        if (component instanceof AaiCorrelationId) {
            if (model.components.correlationIds != null && model.components.correlationIds.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20CorrelationId definition = wrap(component, new Aai20CorrelationId(name), model);
            definition.attachToParent(model.components);
            model.components.addCorrelationId(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "correlationIds/" + name, definition);
        }
        // ---
        if (component instanceof AaiOperationTrait) {
            if (model.components.operationTraits != null && model.components.operationTraits.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20OperationTraitDefinition definition = wrap(component, new Aai20OperationTraitDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addOperationTraitDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "operationTraits/" + name, definition);
        }
        if (component instanceof AaiMessageTrait) {
            if (model.components.messageTraits != null && model.components.messageTraits.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20MessageTraitDefinition definition = wrap(component, new Aai20MessageTraitDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addMessageTraitDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "messageTraits/" + name, definition);
        }
        if (component instanceof AaiServerBindings) {
            if (model.components.serverBindings != null && model.components.serverBindings.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20ServerBindingsDefinition definition = wrap(component, new Aai20ServerBindingsDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addServerBindingDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "serverBindings/" + name, definition);
        }
        if (component instanceof AaiChannelBindings) {
            if (model.components.channelBindings != null && model.components.channelBindings.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20ChannelBindingsDefinition definition = wrap(component, new Aai20ChannelBindingsDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addChannelBindingDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "channelBindings/" + name, definition);
        }
        if (component instanceof AaiOperationBindings) {
            if (model.components.operationBindings != null && model.components.operationBindings.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20OperationBindingsDefinition definition = wrap(component, new Aai20OperationBindingsDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addOperationBindingDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "operationBindings/" + name, definition);
        }
        if (component instanceof AaiMessageBindings) {
            if (model.components.messageBindings != null && model.components.messageBindings.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20MessageBindingsDefinition definition = wrap(component, new Aai20MessageBindingsDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.addMessageBindingDefinition(definition.getName(), definition);
            return new ReferenceAndNode(PREFIX + "messageBindings/" + name, definition);
        }
        return null;
    }


    @Override
    public Map<String, Node> getExistingLocalComponents(Document document) {
        if(!(document instanceof Aai20Document))
            throw new IllegalArgumentException("Aai20Document expected.");
        Aai20Document model = (Aai20Document) document;
        // We could use a local resolver here theoretically, but the reverse approach should be easier and faster
        Map<String, io.apicurio.datamodels.core.models.Node> res = new LinkedHashMap<>();

        if (model.components != null) {
            transform(model.components.messages, name -> PREFIX + "messages/" + name, res);
            transform(model.components.securitySchemes, name -> PREFIX + "securitySchemes/" + name, res);
            transform(model.components.parameters, name -> PREFIX + "parameters/" + name, res);
            transform(model.components.correlationIds, name -> PREFIX + "correlationIds/" + name, res);
            transform(model.components.operationTraits, name -> PREFIX + "operationTraits/" + name, res);
            transform(model.components.messageTraits, name -> PREFIX + "messageTraits/" + name, res);
            transform(model.components.serverBindings, name -> PREFIX + "serverBindings/" + name, res);
            transform(model.components.channelBindings, name -> PREFIX + "channelBindings/" + name, res);
            transform(model.components.operationBindings, name -> PREFIX + "operationBindings/" + name, res);
            transform(model.components.messageBindings, name -> PREFIX + "messageBindings/" + name, res);
            // schemas are not Nodes on AaiComponents, they have to be treated separately
            /*
            for (String name : components.schemas.keySet()) {
                res.put(PREFIX + "schemas/" + name, null);
            }
            */
            // However, we mahe them Nodes on Aai20Components, so now we can cat document and components,
            // retrieve tham adn treat them as regular nodes we're going to transform.
            Aai20Components components = (Aai20Components) ((Aai20Document) document).components;
            transform(components.schemas, name -> PREFIX + "schemas/" + name, res);

        }
        return res;
    }
}
