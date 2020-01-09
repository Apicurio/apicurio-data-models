package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.asyncapi.models.AaiChannelBindings;
import io.apicurio.datamodels.asyncapi.models.AaiChannelBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindings;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindings;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindings;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20ChannelBindingsDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Message;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20MessageBindingsDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20MessageTraitDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20OperationBindingsDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20OperationTraitDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20ServerBindingsDefinition;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;

import java.util.LinkedHashMap;
import java.util.Map;

public class Aai20ReferenceLocalizationStrategy extends AbstractReferenceLocalizationStrategy implements ReferenceLocalizationStrategy {


    @Override
    public Pair attachAsDefinition(Document model0, String name, Node component) {
        Aai20Document model = (Aai20Document) model0; // TODO explicit check
//        component._ownerDocument = model0;
        // TODO reduce code duplication?
        if (component instanceof Aai20Message) {
            if (model.components.messages.get(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            Aai20Message definition = (Aai20Message) component;
            definition.attachToParent(model.components); // TODO this should be done by vvv
            model.components.messages.put(definition.getName(), definition);
            return new Pair(PREFIX + "messages/" + name, definition);
        }
        if (component instanceof AaiSecurityScheme) {
            if (model.components.messages.get(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            AaiSecurityScheme definition = (AaiSecurityScheme) component;
            definition.attachToParent(model.components); // TODO this should be done by vvv
            model.components.securitySchemes.put(definition.getName(), definition);
            return new Pair(PREFIX + "securitySchemes/" + name, definition);
        }
        if (component instanceof AaiParameter) {
            if (model.components.parameters.get(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            AaiParameter definition = (AaiParameter) component;
            definition.attachToParent(model.components); // TODO this should be done by vvv
            model.components.parameters.put(definition.getName(), definition);
            return new Pair(PREFIX + "parameters/" + name, definition);
        }
        if (component instanceof AaiCorrelationId) {
            if (model.components.messages.get(name) == null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            AaiCorrelationId definition = (AaiCorrelationId) component;
            definition.attachToParent(model.components); // TODO this should be done by vvv
            model.components.correlationIds.put(definition.getName(), definition);
            return new Pair(PREFIX + "correlationIds/" + name, definition);
        }
        // ---
        if (component instanceof AaiOperationTrait) {
            if (model.components.operationTraits.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            AaiOperationTraitDefinition definition = wrap(component, new Aai20OperationTraitDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.operationTraits.put(definition.getName(), definition);
            return new Pair(PREFIX + "operationTraits/" + name, definition);
        }
        if (component instanceof AaiMessageTrait) {
            if (model.components.messageTraits.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            AaiMessageTraitDefinition definition = wrap(component, new Aai20MessageTraitDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.messageTraits.put(definition.getName(), definition);
            return new Pair(PREFIX + "messageTraits/" + name, definition);
        }
        if (component instanceof AaiServerBindings) {
            if (model.components.serverBindings.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            AaiServerBindingsDefinition definition = wrap(component, new Aai20ServerBindingsDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.serverBindings.put(definition.getName(), definition);
            return new Pair(PREFIX + "serverBindings/" + name, definition);
        }
        if (component instanceof AaiChannelBindings) {
            if (model.components.channelBindings.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            AaiChannelBindingsDefinition definition = wrap(component, new Aai20ChannelBindingsDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.channelBindings.put(definition.getName(), definition);
            return new Pair(PREFIX + "channelBindings/" + name, definition);
        }
        if (component instanceof AaiOperationBindings) {
            if (model.components.operationBindings.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            AaiOperationBindingsDefinition definition = wrap(component, new Aai20OperationBindingsDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.operationBindings.put(definition.getName(), definition);
            return new Pair(PREFIX + "operationBindings/" + name, definition);
        }
        if (component instanceof AaiMessageBindings) {
            if (model.components.messageBindings.get(name) != null)
                throw new IllegalArgumentException("Definition with that name already exists: " + name);

            AaiMessageBindingsDefinition definition = wrap(component, new Aai20MessageBindingsDefinition(name), model);
            definition.attachToParent(model.components);
            model.components.messageBindings.put(definition.getName(), definition);
            return new Pair(PREFIX + "messageBindings/" + name, definition);
        }
        return null;
    }


    @Override
    public Map<String, Node> getExistingLocalComponents(Document model0) {
        Aai20Document model = (Aai20Document) model0; // TODO explicit check
        // We could use a local resolver here theoretically, but the reverse approach should be easier and faster
        Map<String, io.apicurio.datamodels.core.models.Node> res = new LinkedHashMap<>();

        if(model.components != null) {
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

            // schemas are not Nodes, they have to be treated separately
            //transform(model.components.schemas, name -> PREFIX + "schemas/" + name, res);
            for (String name : model.components.schemas.keySet()) {
                res.put(PREFIX + "schemas/" + name, null);
            }
        }
        return res;
    }
}
