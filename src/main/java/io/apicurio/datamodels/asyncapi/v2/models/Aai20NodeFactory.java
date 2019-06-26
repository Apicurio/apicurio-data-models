package io.apicurio.datamodels.asyncapi.v2.models;

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
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTraitItems;
import io.apicurio.datamodels.asyncapi.models.AaiNodeFactory;
import io.apicurio.datamodels.asyncapi.models.AaiOAuthFlows;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitExtendedItem;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitItems;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.models.AaiProtocolInfo;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityRequirement;
import io.apicurio.datamodels.asyncapi.models.AaiSecurityScheme;
import io.apicurio.datamodels.asyncapi.models.AaiServer;
import io.apicurio.datamodels.asyncapi.models.AaiServerVariable;
import io.apicurio.datamodels.asyncapi.models.AaiTag;
import io.apicurio.datamodels.asyncapi.models.AaiTraitItem;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author Jakub Senko<jsenko@redhat.com>
 */
public class Aai20NodeFactory implements AaiNodeFactory {

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
    public AaiHeaderItem createHeaderItem(Node parent, String key) {
        return new Aai20HeaderItem(parent, key);
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
    public AaiMessageTraitExtendedItem createMessageTraitExtendedItem(Node parent) {
        return new Aai20MessageTraitExtendedItem(parent);
    }

    @Override
    public AaiMessageTraitItems createMessageTraitItems(Node parent) {
        return new Aai20MessageTraitItems(parent);
    }

    @Override
    public AaiMessageTrait createMessageTrait(Node parent, String key) {
        return new Aai20MessageTrait(parent, key);
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
    public AaiOperationTraitExtendedItem createOperationTraitExtendedItem(Node parent, String key) {
        return new Aai20OperationTraitExtendedItem(parent, key);
    }

    @Override
    public AaiOperationTraitItems createOperationTraitItems(Node parent) {
        return new Aai20OperationTraitItems(parent);
    }

    @Override
    public AaiOperationTrait createOperationTrait(Node parent, String key) {
        return new Aai20OperationTrait(parent, key);
    }

    @Override
    public AaiParameter createParameter(Node parent, String key) {
        return new Aai20Parameter(parent, key);
    }

    @Override
    public AaiProtocolInfo createProtocolInfo(Node parent, String key) {
        return new Aai20ProtocolInfo(parent, key);
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
    public AaiServer createServer(Node parent) {
        return new Aai20Server(parent);
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
    public AaiTraitItem createTraitItem(Node parent, String key) {
        return new Aai20TraitItem(parent, key);
    }
}
