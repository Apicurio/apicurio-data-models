package io.apicurio.datamodels.deref;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Extensible;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.union.AnyUnionValueImpl;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Channel;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Components;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Document;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30MultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Operation;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30OperationReply;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30OperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Schema;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Server;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Tag;
import io.apicurio.datamodels.models.union.MultiFormatSchemaSchemaUnion;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;
import io.apicurio.datamodels.util.RegexUtil;

import java.util.List;

/**
 * Node importer for AsyncAPI 3.0 documents. Handles dereferencing and importing external
 * references into the AsyncAPI 3.0 components section.
 */
public class AsyncApi3NodeImporter extends ReferencedNodeImporter {

    public AsyncApi3NodeImporter(Document doc, Node nodeWithUnresolvedRef, String ref, boolean shouldInline) {
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
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedChannelBinding"), getComponentNames(components.getChannelBindings()));
            components.addChannelBinding(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitChannel(AsyncApi30Channel node) {
        String componentType = "channels";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedChannel"), getComponentNames(components.getChannels()));
            components.addChannel(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitCorrelationID(AsyncApiCorrelationID node) {
        String componentType = "correlationIds";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedCorrelationID"), getComponentNames(components.getCorrelationIds()));
            components.addCorrelationId(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitExternalDocumentation(ExternalDocumentation node) {
        String componentType = "externalDocs";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedExternalDocs"), getComponentNames(components.getExternalDocs()));
            components.addExternalDoc(name, (AsyncApi30ExternalDocumentation) node);
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
            AsyncApi30Components components = ensureAsyncApi30Components();
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
            AsyncApi30Components components = ensureAsyncApi30Components();
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
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedMessageTrait"), getComponentNames(components.getMessageTraits()));
            components.addMessageTrait(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitMultiFormatSchema(AsyncApi30MultiFormatSchema node) {
        String componentType = "schemas";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedSchema"), getComponentNames(components.getSchemas()));
            // AsyncAPI 3.0 schemas are a union type, so we manually add to the map
            if (components.getSchemas() == null) {
                components.setSchemas(new java.util.LinkedHashMap<>());
            }
            components.getSchemas().put(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitOperation(Operation node) {
        String componentType = "operations";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedOperation"), getComponentNames(components.getOperations()));
            components.addOperation(name, (AsyncApi30Operation) node);
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
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedOperationBinding"), getComponentNames(components.getOperationBindings()));
            components.addOperationBinding(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitOperationReply(AsyncApi30OperationReply node) {
        String componentType = "replies";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedReply"), getComponentNames(components.getReplies()));
            components.addReply(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitOperationReplyAddress(AsyncApi30OperationReplyAddress node) {
        String componentType = "replyAddresses";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedReplyAddress"), getComponentNames(components.getReplyAddresses()));
            components.addReplyAddress(name, node);
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
            AsyncApi30Components components = ensureAsyncApi30Components();
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
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedParameter"), getComponentNames(components.getParameters()));
            components.addParameter(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitSchema(Schema node) {
        // For AsyncAPI 3.0, we need to detect if this is a non-JSON schema and wrap it in a MultiFormatSchema
        String componentType = "schemas";
        if (shouldInline()) {
            // Check if this is a non-JSON schema that needs wrapping
            if (isAvroSchema(node)) {
                AsyncApi30MultiFormatSchema multiFormatSchema = wrapInMultiFormatSchema(node, "avro");
                inlineComponent(componentType, multiFormatSchema);
            } else if (isProtobufSchema(node)) {
                AsyncApi30MultiFormatSchema multiFormatSchema = wrapInMultiFormatSchema(node, "protobuf");
                inlineComponent(componentType, multiFormatSchema);
            } else {
                inlineComponent(componentType, node);
            }
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();

            // Determine the best name hint for this schema
            String nameHint;
            if (isAvroSchema(node)) {
                // For Avro schemas, prefer the name from the schema itself
                String avroName = extractAvroSchemaName(node);
                if (avroName != null) {
                    nameHint = avroName;
                } else {
                    // Fall back to extracting from the reference URL
                    nameHint = getNameHintFromRef("ImportedSchema");
                }
            } else if (isProtobufSchema(node)) {
                // For Protobuf schemas, prefer the name from the schema itself
                String protobufName = extractProtobufSchemaName(node);
                if (protobufName != null) {
                    nameHint = protobufName;
                } else {
                    // Fall back to extracting from the reference URL
                    nameHint = getNameHintFromRef("ImportedSchema");
                }
            } else {
                // For JSON schemas, extract from the reference URL
                nameHint = getNameHintFromRef("ImportedSchema");
            }

            String name = generateNodeName(nameHint, getComponentNames(components.getSchemas()));

            // AsyncAPI 3.0 schemas are a union type, so we manually add to the map
            if (components.getSchemas() == null) {
                components.setSchemas(new java.util.LinkedHashMap<>());
            }

            // Check if this is a non-JSON schema that needs wrapping
            if (isAvroSchema(node)) {
                AsyncApi30MultiFormatSchema multiFormatSchema = wrapInMultiFormatSchema(node, "avro");
                components.getSchemas().put(name, multiFormatSchema);
                multiFormatSchema.attach(components);
                setPathToImportedNode(multiFormatSchema, componentType, name);
            } else if (isProtobufSchema(node)) {
                AsyncApi30MultiFormatSchema multiFormatSchema = wrapInMultiFormatSchema(node, "protobuf");
                components.getSchemas().put(name, multiFormatSchema);
                multiFormatSchema.attach(components);
                setPathToImportedNode(multiFormatSchema, componentType, name);
            } else {
                // Cast to MultiFormatSchemaSchemaUnion (AsyncApi30Schema implements this)
                components.getSchemas().put(name, (MultiFormatSchemaSchemaUnion) node);
                node.attach(components);
                setPathToImportedNode(node, componentType, name);
            }
        }
    }

    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        String componentType = "securitySchemes";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedSecurityScheme"), getComponentNames(components.getSecuritySchemes()));
            components.addSecurityScheme(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitServer(Server node) {
        String componentType = "servers";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedServer"), getComponentNames(components.getServers()));
            components.addServer(name, (AsyncApi30Server) node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitServerBindings(AsyncApiServerBindings node) {
        String componentType = "serverBindings";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedServerBinding"), getComponentNames(components.getServerBindings()));
            components.addServerBinding(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitServerVariable(ServerVariable node) {
        String componentType = "serverVariables";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedServerVariable"), getComponentNames(components.getServerVariables()));
            components.addServerVariable(name, (AsyncApi30ServerVariable) node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitTag(Tag node) {
        String componentType = "tags";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            AsyncApi30Components components = ensureAsyncApi30Components();
            String name = generateNodeName(getNameHintFromRef("ImportedTag"), getComponentNames(components.getTags()));
            components.addTag(name, (AsyncApi30Tag) node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void importJson(JsonNode jsonNode, String mediaType) {
        // Handle JSON content that isn't an OpenAPI/AsyncAPI document
        // This is used for Avro JSON schemas or other JSON-based formats
        String componentType = "schemas";
        AsyncApi30Components components = ensureAsyncApi30Components();

        // Determine name hint from reference URL
        String nameHint = getNameHintFromRef("ImportedSchema");

        // Try to extract a better name from the JSON if it's an Avro schema
        if (mediaType != null && mediaType.contains("avro")) {
            String avroName = extractAvroNameFromJson(jsonNode);
            if (avroName != null) {
                nameHint = avroName;
            }
        }

        String name = generateNodeName(nameHint, getComponentNames(components.getSchemas()));

        // Create a MultiFormatSchema to wrap the JSON content
        AsyncApi30MultiFormatSchema multiFormatSchema = wrapJsonInMultiFormatSchema(jsonNode, mediaType);

        // Add to components
        if (components.getSchemas() == null) {
            components.setSchemas(new java.util.LinkedHashMap<>());
        }
        components.getSchemas().put(name, multiFormatSchema);
        multiFormatSchema.attach(components);
        setPathToImportedNode(multiFormatSchema, componentType, name);
    }

    @Override
    public void importText(String textContent, String mediaType) {
        // Handle text content (Protobuf, GraphQL, Avro text, etc.)
        String componentType = "schemas";
        AsyncApi30Components components = ensureAsyncApi30Components();

        // Determine name hint - try to extract from content based on media type
        String nameHint = getNameHintFromRef("ImportedSchema");

        if (mediaType != null && mediaType.contains("protobuf")) {
            String protobufName = extractProtobufNameFromText(textContent);
            if (protobufName != null) {
                nameHint = protobufName;
            }
        }

        String name = generateNodeName(nameHint, getComponentNames(components.getSchemas()));

        // Create a MultiFormatSchema to wrap the text content
        AsyncApi30MultiFormatSchema multiFormatSchema = wrapTextInMultiFormatSchema(textContent, mediaType);

        // Add to components
        if (components.getSchemas() == null) {
            components.setSchemas(new java.util.LinkedHashMap<>());
        }
        components.getSchemas().put(name, multiFormatSchema);
        multiFormatSchema.attach(components);
        setPathToImportedNode(multiFormatSchema, componentType, name);
    }

    /**
     * Ensures that the AsyncAPI 3.0 document has a components section, creating it if necessary.
     *
     * @return the AsyncApi30Components object
     */
    private AsyncApi30Components ensureAsyncApi30Components() {
        AsyncApi30Document doc = (AsyncApi30Document) getDoc();
        if (doc.getComponents() == null) {
            doc.setComponents(doc.createComponents());
        }
        return (AsyncApi30Components) doc.getComponents();
    }

    /**
     * Detects if a schema is an Apache Avro schema by looking for the "type": "record" pattern.
     *
     * @param schemaNode the schema node to check
     * @return true if this appears to be an Avro schema
     */
    private boolean isAvroSchema(Schema schemaNode) {
        try {
            ObjectNode json = Library.writeNode(schemaNode);
            String type = JsonUtil.getStringProperty(json, "type");

            // Avro schemas have "type": "record", "enum", "fixed", etc.
            // JSON Schema has "type": "object", "array", "string", etc.
            if (type != null) {
                return "record".equals(type) || "enum".equals(type) || "fixed".equals(type);
            }

            return false;
        } catch (Exception e) {
            // If we can't determine, assume it's JSON Schema
            return false;
        }
    }

    /**
     * Extracts the name from an Avro schema. Avro schemas have a "name" field that identifies
     * the type. Optionally, they may also have a "namespace" field for fully qualified names.
     *
     * @param schemaNode the Avro schema node
     * @return the name from the schema, or null if not found
     */
    private String extractAvroSchemaName(Schema schemaNode) {
        try {
            ObjectNode json = Library.writeNode(schemaNode);
            String name = JsonUtil.getStringProperty(json, "name");

            if (!NodeUtil.isNullOrUndefined(name)) {
                return name;
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Detects if a schema is a Protocol Buffers schema by looking for protobuf content in the "x-text-content" field.
     *
     * @param schemaNode the schema node to check
     * @return true if this appears to be a Protobuf schema
     */
    private boolean isProtobufSchema(Schema schemaNode) {
        try {
            // Protobuf schemas have an "x-text-content" property with the .proto file text
            ObjectNode json = Library.writeNode(schemaNode);
            String protoContent = JsonUtil.getStringProperty(json, "x-text-content");
            return !NodeUtil.isNullOrUndefined(protoContent);
        } catch (Exception e) {
            // If we can't determine, assume it's JSON Schema
            return false;
        }
    }

    /**
     * Extracts the name from a Protobuf schema. Parses the .proto text content to find
     * the message name.
     *
     * @param schemaNode the Protobuf schema node
     * @return the name from the first message definition, or null if not found
     */
    private String extractProtobufSchemaName(Schema schemaNode) {
        try {
            ObjectNode json = Library.writeNode(schemaNode);
            String protoContent = JsonUtil.getStringProperty(json, "x-text-content");

            if (!NodeUtil.isNullOrUndefined(protoContent)) {
                // Simple regex to extract message name from "message MessageName {"
                List<String[]> matches = RegexUtil.findMatches(protoContent, "message\\s+([A-Za-z0-9_]+)\\s*\\{");
                LoggerUtil.debug(">>>> --------------");
                LoggerUtil.debug(">>>> MATCHES: ", matches);
                LoggerUtil.debug(">>>> --------------");
                if (!matches.isEmpty()) {
                    return matches.get(0)[1];
                }
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Extracts the raw Protobuf content from a schema node.
     *
     * @param schemaNode the Protobuf schema node
     * @return the .proto file content as a string, or null if not found
     */
    private String extractProtobufContent(Schema schemaNode) {
        try {
            ObjectNode json = Library.writeNode(schemaNode);
            String protoContent = JsonUtil.getStringProperty(json, "x-text-content");
            if (!NodeUtil.isNullOrUndefined(protoContent)) {
                return protoContent;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Wraps a schema (Avro, Protobuf, etc.) in an AsyncAPI 3.0 Multi-Format Schema Object.
     *
     * @param schemaNode the schema to wrap
     * @param schemaType the type of schema ("avro", "protobuf", etc.)
     * @return a MultiFormatSchema containing the wrapped schema
     */
    private AsyncApi30MultiFormatSchema wrapInMultiFormatSchema(Schema schemaNode, String schemaType) {
        AsyncApi30Components components = ensureAsyncApi30Components();
        AsyncApi30MultiFormatSchema multiFormatSchema = components.createMultiFormatSchema();

        // Set the schema format based on the type
        switch (schemaType) {
            case "avro":
                multiFormatSchema.setSchemaFormat("application/vnd.apache.avro+json;version=1.12.0");
                break;
            case "protobuf":
                multiFormatSchema.setSchemaFormat("application/vnd.google.protobuf;version=3");
                break;
            default:
                multiFormatSchema.setSchemaFormat("application/schema+json");
                break;
        }

        // For Protobuf, extract the raw .proto content and set it as a TextNode
        // For other formats, convert the schema node to JSON
        if ("protobuf".equals(schemaType)) {
            String protobufContent = extractProtobufContent(schemaNode);
            if (protobufContent != null) {
                // The schema property in MultiFormatSchema is an AnySchemaUnion, which can be
                // either a Schema object or any JsonNode. For Protobuf, we use a TextNode
                // wrapped in an AnyUnionValueImpl.
                JsonNode protobufTextNode = JsonUtil.textNode(protobufContent);
                AnyUnionValueImpl schemaValue = new AnyUnionValueImpl(protobufTextNode);

                // Set it as the schema
                multiFormatSchema.setSchema(schemaValue);
            } else {
                // Fallback to normal schema handling
                ObjectNode schemaJson = Library.writeNode(schemaNode);
                AsyncApi30Schema schema = components.createSchema();
                Library.readNode(schemaJson, schema);
                multiFormatSchema.setSchema(schema);
            }
        } else {
            // Convert the schema node to JSON and set it as the schema property
            ObjectNode schemaJson = Library.writeNode(schemaNode);
            // Set the schema by creating an AsyncApi30Schema and populating it from the JSON
            AsyncApi30Schema schema = components.createSchema();
            Library.readNode(schemaJson, schema);
            multiFormatSchema.setSchema(schema);
        }

        return multiFormatSchema;
    }

    /**
     * Extracts the name from an Avro schema in JSON format.
     *
     * @param jsonNode the Avro schema JSON
     * @return the name from the schema, or null if not found
     */
    private String extractAvroNameFromJson(JsonNode jsonNode) {
        try {
            if (JsonUtil.isObject(jsonNode)) {
                String name = JsonUtil.getStringProperty((ObjectNode) jsonNode, "name");
                if (!NodeUtil.isNullOrUndefined(name)) {
                    return name;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Extracts the name from a Protobuf schema text.
     *
     * @param textContent the .proto file content
     * @return the name from the first message definition, or null if not found
     */
    private String extractProtobufNameFromText(String textContent) {
        try {
            if (!NodeUtil.isNullOrUndefined(textContent)) {
                // Simple regex to extract message name from "message MessageName {"
                List<String[]> matches = RegexUtil.findMatches(textContent, "message\\s+([A-Za-z0-9_]+)\\s*\\{");
                if (!matches.isEmpty()) {
                    return matches.get(0)[1];
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Wraps JSON content in an AsyncAPI 3.0 Multi-Format Schema Object.
     *
     * @param jsonNode the JSON content to wrap
     * @param mediaType the media type of the content
     * @return a MultiFormatSchema containing the wrapped JSON
     */
    private AsyncApi30MultiFormatSchema wrapJsonInMultiFormatSchema(JsonNode jsonNode, String mediaType) {
        AsyncApi30Components components = ensureAsyncApi30Components();
        AsyncApi30MultiFormatSchema multiFormatSchema = components.createMultiFormatSchema();

        // Set the schema format based on media type
        if (mediaType != null && !mediaType.isEmpty()) {
            multiFormatSchema.setSchemaFormat(mediaType);
        } else {
            multiFormatSchema.setSchemaFormat("application/schema+json");
        }

        // Wrap the JsonNode in an AnyUnionValueImpl
        AnyUnionValueImpl schemaValue = new AnyUnionValueImpl(jsonNode);
        multiFormatSchema.setSchema(schemaValue);

        return multiFormatSchema;
    }

    /**
     * Wraps text content in an AsyncAPI 3.0 Multi-Format Schema Object.
     *
     * @param textContent the text content to wrap
     * @param mediaType the media type of the content
     * @return a MultiFormatSchema containing the wrapped text
     */
    private AsyncApi30MultiFormatSchema wrapTextInMultiFormatSchema(String textContent, String mediaType) {
        AsyncApi30Components components = ensureAsyncApi30Components();
        AsyncApi30MultiFormatSchema multiFormatSchema = components.createMultiFormatSchema();

        // Set the schema format based on media type
        if (mediaType != null && !mediaType.isEmpty()) {
            multiFormatSchema.setSchemaFormat(mediaType);
        } else {
            multiFormatSchema.setSchemaFormat("text/plain");
        }

        // Wrap the text in a TextNode and then in an AnyUnionValueImpl
        JsonNode textNode = JsonUtil.textNode(textContent);
        AnyUnionValueImpl schemaValue = new AnyUnionValueImpl(textNode);
        multiFormatSchema.setSchema(schemaValue);

        return multiFormatSchema;
    }

}
