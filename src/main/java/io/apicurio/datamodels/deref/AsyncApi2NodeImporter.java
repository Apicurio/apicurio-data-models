package io.apicurio.datamodels.deref;

import io.apicurio.datamodels.models.Document;
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

    public AsyncApi2NodeImporter(Document doc, String ref) {
        super(doc, ref);
    }

    private void setPathToImportedNode(String type, String name) {
        setPathToImportedNode("#/components/" + type + "/" + name);
    }

    @Override
    public void visitChannelBindings(AsyncApiChannelBindings node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedChannelBinding"), getComponentNames(components.getChannelBindings()));
        components.addChannelBinding(name, node);
        node.attach(components);
        setPathToImportedNode("channelBindings", name);
    }

    @Override
    public void visitChannelItem(AsyncApiChannelItem node) {
        // TODO support importing ChannelItem - needs to be inlined?
    }

    @Override
    public void visitCorrelationID(AsyncApiCorrelationID node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedCorrelationID"), getComponentNames(components.getCorrelationIds()));
        components.addCorrelationId(name, node);
        node.attach(components);
        setPathToImportedNode("correlationIds", name);
    }

    @Override
    public void visitMessage(AsyncApiMessage node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedMessage"), getComponentNames(components.getMessages()));
        components.addMessage(name, node);
        node.attach(components);
        setPathToImportedNode("messages", name);
    }

    @Override
    public void visitMessageBindings(AsyncApiMessageBindings node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedMessageBinding"), getComponentNames(components.getMessageBindings()));
        components.addMessageBinding(name, node);
        node.attach(components);
        setPathToImportedNode("messageBindings", name);
    }

    @Override
    public void visitMessageTrait(AsyncApiMessageTrait node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedMessageTrait"), getComponentNames(components.getMessageTraits()));
        components.addMessageTrait(name, node);
        node.attach(components);
        setPathToImportedNode("messageTraits", name);
    }

    @Override
    public void visitOperationBindings(AsyncApiOperationBindings node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedOperationBinding"), getComponentNames(components.getOperationBindings()));
        components.addOperationBinding(name, node);
        node.attach(components);
        setPathToImportedNode("operationBindings", name);
    }

    @Override
    public void visitOperationTrait(AsyncApiOperationTrait node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedOperationTrait"), getComponentNames(components.getOperationTraits()));
        components.addOperationTrait(name, node);
        node.attach(components);
        setPathToImportedNode("operationTraits", name);
    }

    @Override
    public void visitParameter(Parameter node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedParameter"), getComponentNames(components.getParameters()));
        components.addParameter(name, node);
        node.attach(components);
        setPathToImportedNode("parameters", name);
    }

    @Override
    public void visitSchema(Schema node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedSchema"), getComponentNames(components.getSchemas()));
        components.addSchema(name, node);
        node.attach(components);
        setPathToImportedNode("schemas", name);
    }

    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedSecurityScheme"), getComponentNames(components.getSecuritySchemes()));
        components.addSecurityScheme(name, node);
        node.attach(components);
        setPathToImportedNode("securitySchemes", name);
    }

    @Override
    public void visitServer(Server node) {
        // TODO support importing Server - needs to be inlined??
    }

    @Override
    public void visitServerBindings(AsyncApiServerBindings node) {
        AsyncApiComponents components = ensureAsyncApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedServerBinding"), getComponentNames(components.getServerBindings()));
        components.addServerBinding(name, node);
        node.attach(components);
        setPathToImportedNode("serverBindings", name);
    }

    @Override
    public void visitServerVariable(ServerVariable node) {
        // TODO support importing ServerVariable - needs to be inlined?
    }

    private AsyncApiComponents ensureAsyncApiComponents() {
        AsyncApiDocument doc = (AsyncApiDocument) getDoc();
        if (doc.getComponents() == null) {
            doc.setComponents(doc.createComponents());
        }
        return doc.getComponents();
    }

}
