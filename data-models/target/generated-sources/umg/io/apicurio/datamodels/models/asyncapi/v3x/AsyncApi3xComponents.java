package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiComponents;
import io.apicurio.datamodels.models.union.MultiFormatSchemaSchemaUnion;
import java.util.Map;

public interface AsyncApi3xComponents extends AsyncApiComponents {

	public AsyncApi3xMultiFormatSchema createMultiFormatSchema();

	public AsyncApi3xSchema createSchema();

	public Map<String, MultiFormatSchemaSchemaUnion> getSchemas();

	public void addSchema(String name, MultiFormatSchemaSchemaUnion value);

	public void clearSchemas();

	public void removeSchema(String name);

	public void insertSchema(String name, MultiFormatSchemaSchemaUnion value, int atIndex);

	public AsyncApi3xOperationReply createOperationReply();

	public Map<String, AsyncApi3xOperationReply> getReplies();

	public void addReply(String name, AsyncApi3xOperationReply value);

	public void clearReplies();

	public void removeReply(String name);

	public void insertReply(String name, AsyncApi3xOperationReply value, int atIndex);

	public AsyncApi3xServer createServer();

	public Map<String, AsyncApi3xServer> getServers();

	public void addServer(String name, AsyncApi3xServer value);

	public void clearServers();

	public void removeServer(String name);

	public void insertServer(String name, AsyncApi3xServer value, int atIndex);

	public AsyncApi3xChannel createChannel();

	public Map<String, AsyncApi3xChannel> getChannels();

	public void addChannel(String name, AsyncApi3xChannel value);

	public void clearChannels();

	public void removeChannel(String name);

	public void insertChannel(String name, AsyncApi3xChannel value, int atIndex);

	public AsyncApi3xOperationReplyAddress createOperationReplyAddress();

	public Map<String, AsyncApi3xOperationReplyAddress> getReplyAddresses();

	public void addReplyAddress(String name, AsyncApi3xOperationReplyAddress value);

	public void clearReplyAddresses();

	public void removeReplyAddress(String name);

	public void insertReplyAddress(String name, AsyncApi3xOperationReplyAddress value, int atIndex);

	public AsyncApi3xTag createTag();

	public Map<String, AsyncApi3xTag> getTags();

	public void addTag(String name, AsyncApi3xTag value);

	public void clearTags();

	public void removeTag(String name);

	public void insertTag(String name, AsyncApi3xTag value, int atIndex);

	public AsyncApi3xServerVariable createServerVariable();

	public Map<String, AsyncApi3xServerVariable> getServerVariables();

	public void addServerVariable(String name, AsyncApi3xServerVariable value);

	public void clearServerVariables();

	public void removeServerVariable(String name);

	public void insertServerVariable(String name, AsyncApi3xServerVariable value, int atIndex);

	public AsyncApi3xExternalDocumentation createExternalDocumentation();

	public Map<String, AsyncApi3xExternalDocumentation> getExternalDocs();

	public void addExternalDoc(String name, AsyncApi3xExternalDocumentation value);

	public void clearExternalDocs();

	public void removeExternalDoc(String name);

	public void insertExternalDoc(String name, AsyncApi3xExternalDocumentation value, int atIndex);

	public AsyncApi3xOperation createOperation();

	public Map<String, AsyncApi3xOperation> getOperations();

	public void addOperation(String name, AsyncApi3xOperation value);

	public void clearOperations();

	public void removeOperation(String name);

	public void insertOperation(String name, AsyncApi3xOperation value, int atIndex);
}