package io.apicurio.datamodels.deref;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelItem;
import io.apicurio.datamodels.models.asyncapi.AsyncApiComponents;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiDocument;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;

public class AsyncApi2NodeImporter extends ReferencedNodeImporter {

    public AsyncApi2NodeImporter(Document doc, Node nodeWithUnresolvedRef, String ref, boolean shouldInline) {
        super(doc, nodeWithUnresolvedRef, ref, shouldInline);
    }

    @Override
    protected void setPathToImportedNode(Node importedNode, String type, String name) {
        setPathToImportedNode(importedNode, "#/components/" + type + "/" + name);
    }

    @Override
    public void visitChannelBindings(AsyncApiChannelBindings node) {
        String componentType = "channelBindings";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedChannelBinding"), getComponentNames(components.getChannelBindings()));
            components.addChannelBinding(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitChannelItem(AsyncApiChannelItem node) {
        // Note: there is no place in #/components to store channel items, so they must be inlined.
        ObjectNode json = Library.writeNode(node);
        Library.readNode(json, getNodeWithUnresolvedRef());
        setPathToImportedNode(getNodeWithUnresolvedRef(), null);
    }

    @Override
    public void visitCorrelationID(AsyncApiCorrelationID node) {
        String componentType = "correlationIds";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedCorrelationID"), getComponentNames(components.getCorrelationIds()));
            components.addCorrelationId(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitMessage(AsyncApiMessage node) {
        String componentType = "messages";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedMessage"), getComponentNames(components.getMessages()));
            components.addMessage(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitMessageBindings(AsyncApiMessageBindings node) {
        String componentType = "messageBindings";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedMessageBinding"), getComponentNames(components.getMessageBindings()));
            components.addMessageBinding(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitMessageTrait(AsyncApiMessageTrait node) {
        String componentType = "messageTraits";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedMessageTrait"), getComponentNames(components.getMessageTraits()));
            components.addMessageTrait(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitOperationBindings(AsyncApiOperationBindings node) {
        String componentType = "operationBindings";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedOperationBinding"), getComponentNames(components.getOperationBindings()));
            components.addOperationBinding(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitOperationTrait(AsyncApiOperationTrait node) {
        String componentType = "operationTraits";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedOperationTrait"), getComponentNames(components.getOperationTraits()));
            components.addOperationTrait(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitParameter(Parameter node) {
        String componentType = "parameters";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedParameter"), getComponentNames(components.getParameters()));
            components.addParameter(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitSchema(Schema node) {
        String componentType = "schemas";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedSchema"), getComponentNames(components.getSchemas()));
            components.addSchema(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        String componentType = "securitySchemes";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedSecurityScheme"), getComponentNames(components.getSecuritySchemes()));
            components.addSecurityScheme(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitServer(Server node) {
        // Note: there is no place in #/components to store servers, so they must be inlined.
        ObjectNode json = Library.writeNode(node);
        Library.readNode(json, getNodeWithUnresolvedRef());
        setPathToImportedNode(getNodeWithUnresolvedRef(), null);
    }

    @Override
    public void visitServerBindings(AsyncApiServerBindings node) {
        String componentType = "serverBindings";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApiComponents components = ensureAsyncApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedServerBinding"), getComponentNames(components.getServerBindings()));
            components.addServerBinding(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitServerVariable(ServerVariable node) {
        // Note: there is no place in #/components to store server variables, so they must be inlined.
        ObjectNode json = Library.writeNode(node);
        Library.readNode(json, getNodeWithUnresolvedRef());
        setPathToImportedNode(getNodeWithUnresolvedRef(), null);
    }

    private AsyncApiComponents ensureAsyncApiComponents() {
        AsyncApiDocument doc = (AsyncApiDocument) getDoc();
        if (doc.getComponents() == null) {
            doc.setComponents(doc.createComponents());
        }
        return doc.getComponents();
    }

}
