package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;

/**
 * Interface defining a factory for creating AsyncAPI model nodes.
 * Useful for e.g. DataModelReader.
 * Common parameters:
 *  - Node parent - parent node in the target model tree. You have to
 *      attach the node to the parent node separately. (TODO?)
 *      May be null.
 *  - String key - if the node is attached to the parent as a member of a map field,
 *      The child node should be aware of the key it has been referenced by.
 *      This information is necessary for serialization and deserialization.
 *      May be null, e.g. in case the node is reference directly via a field.
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public interface IAaiNodeFactory {

    AaiChannelItem createChannelItem(Node parent, String key);
    AaiComponents createComponents(Node parent);
    AaiContact createContact(Node parent);
    AaiCorrelationId createCorrelationId(Node parent, String key);
    AaiDocument createDocument();
    AaiExternalDocumentation createExternalDocumentation(Node parent);
    AaiHeaderItem createHeaderItem(Node parent, String key);
    AaiInfo createInfo(Node parent);
    AaiLicense createLicense(Node parent);
    AaiMessage createMessage(Node parent, String key);
    AaiMessageTraitExtendedItem createMessageTraitExtendedItem(Node parent);
    AaiMessageTraitItems createMessageTraitItems(Node parent);
    AaiMessageTrait createMessageTrait(Node parent, String key);
    AaiOAuthFlows createOAuthFlows(Node parent);
    AaiOperation createOperation(Node parent, String key);
    AaiOperationTraitExtendedItem createOperationTraitExtendedItem(Node parent, String key);
    AaiOperationTraitItems createOperationTraitItems(Node parent);
    AaiOperationTrait createOperationTrait(Node parent, String key);
    AaiParameter createParameter(Node parent, String key);
    AaiProtocolInfo createProtocolInfo(Node parent, String key);
    AaiSecurityRequirement createSecurityRequirement(Node parent);
    AaiSecurityScheme createSecurityScheme(Node parent, String key);
    AaiServer createServer(Node parent);
    AaiServerVariable createServerVariable(Node parent, String key);
    AaiTag createTag(Node parent);
    AaiUnknownTrait createUnknownTrait(Node parent, String key);
}
