package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Components;
import java.util.Map;

public interface AsyncApiComponents extends Components {

	public AsyncApiCorrelationID createCorrelationID();

	public Map<String, AsyncApiCorrelationID> getCorrelationIds();

	public void addCorrelationId(String name, AsyncApiCorrelationID value);

	public void clearCorrelationIds();

	public void removeCorrelationId(String name);

	public void insertCorrelationId(String name, AsyncApiCorrelationID value, int atIndex);

	public AsyncApiChannelBindings createChannelBindings();

	public Map<String, AsyncApiChannelBindings> getChannelBindings();

	public void addChannelBinding(String name, AsyncApiChannelBindings value);

	public void clearChannelBindings();

	public void removeChannelBinding(String name);

	public void insertChannelBinding(String name, AsyncApiChannelBindings value, int atIndex);

	public AsyncApiParameter createParameter();

	public Map<String, AsyncApiParameter> getParameters();

	public void addParameter(String name, AsyncApiParameter value);

	public void clearParameters();

	public void removeParameter(String name);

	public void insertParameter(String name, AsyncApiParameter value, int atIndex);

	public AsyncApiSecurityScheme createSecurityScheme();

	public Map<String, AsyncApiSecurityScheme> getSecuritySchemes();

	public void addSecurityScheme(String name, AsyncApiSecurityScheme value);

	public void clearSecuritySchemes();

	public void removeSecurityScheme(String name);

	public void insertSecurityScheme(String name, AsyncApiSecurityScheme value, int atIndex);

	public AsyncApiMessageTrait createMessageTrait();

	public Map<String, AsyncApiMessageTrait> getMessageTraits();

	public void addMessageTrait(String name, AsyncApiMessageTrait value);

	public void clearMessageTraits();

	public void removeMessageTrait(String name);

	public void insertMessageTrait(String name, AsyncApiMessageTrait value, int atIndex);

	public AsyncApiServerBindings createServerBindings();

	public Map<String, AsyncApiServerBindings> getServerBindings();

	public void addServerBinding(String name, AsyncApiServerBindings value);

	public void clearServerBindings();

	public void removeServerBinding(String name);

	public void insertServerBinding(String name, AsyncApiServerBindings value, int atIndex);

	public AsyncApiOperationTrait createOperationTrait();

	public Map<String, AsyncApiOperationTrait> getOperationTraits();

	public void addOperationTrait(String name, AsyncApiOperationTrait value);

	public void clearOperationTraits();

	public void removeOperationTrait(String name);

	public void insertOperationTrait(String name, AsyncApiOperationTrait value, int atIndex);

	public AsyncApiMessageBindings createMessageBindings();

	public Map<String, AsyncApiMessageBindings> getMessageBindings();

	public void addMessageBinding(String name, AsyncApiMessageBindings value);

	public void clearMessageBindings();

	public void removeMessageBinding(String name);

	public void insertMessageBinding(String name, AsyncApiMessageBindings value, int atIndex);

	public AsyncApiOperationBindings createOperationBindings();

	public Map<String, AsyncApiOperationBindings> getOperationBindings();

	public void addOperationBinding(String name, AsyncApiOperationBindings value);

	public void clearOperationBindings();

	public void removeOperationBinding(String name);

	public void insertOperationBinding(String name, AsyncApiOperationBindings value, int atIndex);

	public AsyncApiMessage createMessage();

	public Map<String, AsyncApiMessage> getMessages();

	public void addMessage(String name, AsyncApiMessage value);

	public void clearMessages();

	public void removeMessage(String name);

	public void insertMessage(String name, AsyncApiMessage value, int atIndex);
}