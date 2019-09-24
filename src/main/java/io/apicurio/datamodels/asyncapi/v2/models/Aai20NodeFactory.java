package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiChannelBindings;
import io.apicurio.datamodels.asyncapi.models.AaiChannelBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.asyncapi.models.AaiContact;
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiExternalDocumentation;
import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiInfo;
import io.apicurio.datamodels.asyncapi.models.AaiLicense;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindings;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOAuthFlows;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindings;
import io.apicurio.datamodels.asyncapi.models.AaiOperationBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityRequirement;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindings;
import io.apicurio.datamodels.asyncapi.models.AaiServerBindingsDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiServerVariable;
import io.apicurio.datamodels.asyncapi.models.AaiTag;
import io.apicurio.datamodels.asyncapi.models.IAaiNodeFactory;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko<jsenko@redhat.com>
 */
public class Aai20NodeFactory implements IAaiNodeFactory {

    @Override
    public AaiChannelItem createChannelItem(Node parent, String key) {
        return new Aai20ChannelItem(parent, key);
    }

    @Override
    public AaiComponents createComponents(Node parent) {
        return new Aai20Components(parent);
    }

    @Override
    public AaiContact createContact(Node parent) {
        return new Aai20Contact(parent);
    }

    @Override
    public AaiCorrelationId createCorrelationId(Node parent, String key) {
        return new Aai20CorrelationId(parent, key);
    }

    @Override
    public AaiDocument createDocument() {
        return new Aai20Document();
    }

    @Override
    public AaiExternalDocumentation createExternalDocumentation(Node parent) {
        return new Aai20ExternalDocumentation(parent);
    }

    @Override
    public AaiHeaderItem createHeaderItem(Node parent) {
        return new Aai20HeaderItem(parent);
    }

    @Override
    public AaiInfo createInfo(Node parent) {
        return new Aai20Info(parent);
    }

    @Override
    public AaiLicense createLicense(Node parent) {
        return new Aai20License(parent);
    }

    @Override
    public AaiMessage createMessage(Node parent, String key) {
        return new Aai20Message(parent, key);
    }

    @Override
    public AaiMessageTrait createMessageTrait(Node parent, String key) {
        return new Aai20MessageTrait(parent, key);
    }
    
    @Override
    public AaiMessageTraitDefinition createMessageTraitDefinition(Node parent, String key) {
        return new Aai20MessageTraitDefinition(parent, key);
    }

    @Override
    public AaiOAuthFlows createOAuthFlows(Node parent) {
        return new Aai20OAuthFlows(parent);
    }

    @Override
    public AaiOperation createOperation(Node parent, String key) {
        return new Aai20Operation(parent, key);
    }

    @Override
    public AaiOperationTrait createOperationTrait(Node parent, String key) {
        return new Aai20OperationTrait(parent, key);
    }
    
    @Override
    public AaiOperationTraitDefinition createOperationTraitDefinition(Node parent, String key) {
        return new Aai20OperationTraitDefinition(parent, key);
    }

    @Override
    public AaiParameter createParameter(Node parent, String key) {
        return new Aai20Parameter(parent, key);
    }

    @Override
    public AaiSecurityRequirement createSecurityRequirement(Node parent) {
        return new Aai20SecurityRequirement(parent);
    }

    @Override
    public AaiSecurityScheme createSecurityScheme(Node parent, String key) {
        return new Aai20SecurityScheme(parent, key);
    }

    @Override
    public AaiServer createServer(Node parent, String key) {
        return new Aai20Server(parent, key);
    }

    @Override
    public AaiServerVariable createServerVariable(Node parent, String key) {
        return new Aai20ServerVariable(parent, key);
    }

    @Override
    public AaiTag createTag(Node parent) {
        return new Aai20Tag(parent);
    }

    @Override
    public AaiServerBindings createServerBindings(Node parent) {
        return new Aai20ServerBindings(parent);
    }

    @Override
    public AaiServerBindingsDefinition createServerBindingsDefinition(Node parent, String key) {
        return new Aai20ServerBindingsDefinition(parent, key);
    }

    @Override
    public AaiOperationBindings createOperationBindings(Node parent) {
        return new Aai20OperationBindings(parent);
    }

    @Override
    public AaiOperationBindingsDefinition createOperationBindingsDefinition(Node parent, String key) {
        return new Aai20OperationBindingsDefinition(parent, key);
    }

    @Override
    public AaiMessageBindings createMessageBindings(Node parent) {
        return new Aai20MessageBindings(parent);
    }

    @Override
    public AaiMessageBindingsDefinition createMessageBindingsDefinition(Node parent, String key) {
        return new Aai20MessageBindingsDefinition(parent, key);
    }

    @Override
    public AaiChannelBindings createChannelBindings(Node parent) {
        return new Aai20ChannelBindings(parent);
    }

    @Override
    public AaiChannelBindingsDefinition createChannelBindingsDefinition(Node parent, String key) {
        return new Aai20ChannelBindingsDefinition(parent, key);
    }
}
