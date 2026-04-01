package io.apicurio.datamodels.models.asyncapi.v2x.v24;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameter;
import io.apicurio.datamodels.models.asyncapi.AsyncApiSecurityScheme;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xSchema;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.visitors.AsyncApi24Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class AsyncApi24ComponentsImpl extends NodeImpl implements AsyncApi24Components {

	private Map<String, AsyncApi2xSchema> schemas;
	private Map<String, AsyncApi24Server> servers;
	private Map<String, AsyncApi24ServerVariable> serverVariables;
	private Map<String, AsyncApi24ChannelItem> channels;
	private Map<String, AsyncApiMessage> messages;
	private Map<String, AsyncApiSecurityScheme> securitySchemes;
	private Map<String, AsyncApiParameter> parameters;
	private Map<String, AsyncApiCorrelationID> correlationIds;
	private Map<String, AsyncApiOperationTrait> operationTraits;
	private Map<String, AsyncApiMessageTrait> messageTraits;
	private Map<String, AsyncApiServerBindings> serverBindings;
	private Map<String, AsyncApiChannelBindings> channelBindings;
	private Map<String, AsyncApiOperationBindings> operationBindings;
	private Map<String, AsyncApiMessageBindings> messageBindings;
	private Map<String, JsonNode> extensions;

	@Override
	public AsyncApi24Schema createSchema() {
		AsyncApi24SchemaImpl node = new AsyncApi24SchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApi2xSchema> getSchemas() {
		return schemas;
	}

	@Override
	public void addSchema(String name, AsyncApi2xSchema value) {
		if (this.schemas == null) {
			this.schemas = new LinkedHashMap<>();
		}
		this.schemas.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("schemas");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearSchemas() {
		if (this.schemas != null) {
			this.schemas.clear();
		}
	}

	@Override
	public void removeSchema(String name) {
		if (this.schemas != null) {
			this.schemas.remove(name);
		}
	}

	@Override
	public void insertSchema(String name, AsyncApi2xSchema value, int atIndex) {
		if (this.schemas == null) {
			this.schemas = new LinkedHashMap<>();
			this.schemas.put(name, value);
		} else {
			this.schemas = DataModelUtil.insertMapEntry(this.schemas, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("schemas");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24Server createServer() {
		AsyncApi24ServerImpl node = new AsyncApi24ServerImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApi24Server> getServers() {
		return servers;
	}

	@Override
	public void addServer(String name, AsyncApi24Server value) {
		if (this.servers == null) {
			this.servers = new LinkedHashMap<>();
		}
		this.servers.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearServers() {
		if (this.servers != null) {
			this.servers.clear();
		}
	}

	@Override
	public void removeServer(String name) {
		if (this.servers != null) {
			this.servers.remove(name);
		}
	}

	@Override
	public void insertServer(String name, AsyncApi24Server value, int atIndex) {
		if (this.servers == null) {
			this.servers = new LinkedHashMap<>();
			this.servers.put(name, value);
		} else {
			this.servers = DataModelUtil.insertMapEntry(this.servers, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24ServerVariable createServerVariable() {
		AsyncApi24ServerVariableImpl node = new AsyncApi24ServerVariableImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApi24ServerVariable> getServerVariables() {
		return serverVariables;
	}

	@Override
	public void addServerVariable(String name, AsyncApi24ServerVariable value) {
		if (this.serverVariables == null) {
			this.serverVariables = new LinkedHashMap<>();
		}
		this.serverVariables.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("serverVariables");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearServerVariables() {
		if (this.serverVariables != null) {
			this.serverVariables.clear();
		}
	}

	@Override
	public void removeServerVariable(String name) {
		if (this.serverVariables != null) {
			this.serverVariables.remove(name);
		}
	}

	@Override
	public void insertServerVariable(String name, AsyncApi24ServerVariable value, int atIndex) {
		if (this.serverVariables == null) {
			this.serverVariables = new LinkedHashMap<>();
			this.serverVariables.put(name, value);
		} else {
			this.serverVariables = DataModelUtil.insertMapEntry(this.serverVariables, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("serverVariables");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24ChannelItem createChannelItem() {
		AsyncApi24ChannelItemImpl node = new AsyncApi24ChannelItemImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApi24ChannelItem> getChannels() {
		return channels;
	}

	@Override
	public void addChannel(String name, AsyncApi24ChannelItem value) {
		if (this.channels == null) {
			this.channels = new LinkedHashMap<>();
		}
		this.channels.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("channels");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearChannels() {
		if (this.channels != null) {
			this.channels.clear();
		}
	}

	@Override
	public void removeChannel(String name) {
		if (this.channels != null) {
			this.channels.remove(name);
		}
	}

	@Override
	public void insertChannel(String name, AsyncApi24ChannelItem value, int atIndex) {
		if (this.channels == null) {
			this.channels = new LinkedHashMap<>();
			this.channels.put(name, value);
		} else {
			this.channels = DataModelUtil.insertMapEntry(this.channels, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("channels");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24Message createMessage() {
		AsyncApi24MessageImpl node = new AsyncApi24MessageImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiMessage> getMessages() {
		return messages;
	}

	@Override
	public void addMessage(String name, AsyncApiMessage value) {
		if (this.messages == null) {
			this.messages = new LinkedHashMap<>();
		}
		this.messages.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messages");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearMessages() {
		if (this.messages != null) {
			this.messages.clear();
		}
	}

	@Override
	public void removeMessage(String name) {
		if (this.messages != null) {
			this.messages.remove(name);
		}
	}

	@Override
	public void insertMessage(String name, AsyncApiMessage value, int atIndex) {
		if (this.messages == null) {
			this.messages = new LinkedHashMap<>();
			this.messages.put(name, value);
		} else {
			this.messages = DataModelUtil.insertMapEntry(this.messages, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messages");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24SecurityScheme createSecurityScheme() {
		AsyncApi24SecuritySchemeImpl node = new AsyncApi24SecuritySchemeImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiSecurityScheme> getSecuritySchemes() {
		return securitySchemes;
	}

	@Override
	public void addSecurityScheme(String name, AsyncApiSecurityScheme value) {
		if (this.securitySchemes == null) {
			this.securitySchemes = new LinkedHashMap<>();
		}
		this.securitySchemes.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("securitySchemes");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearSecuritySchemes() {
		if (this.securitySchemes != null) {
			this.securitySchemes.clear();
		}
	}

	@Override
	public void removeSecurityScheme(String name) {
		if (this.securitySchemes != null) {
			this.securitySchemes.remove(name);
		}
	}

	@Override
	public void insertSecurityScheme(String name, AsyncApiSecurityScheme value, int atIndex) {
		if (this.securitySchemes == null) {
			this.securitySchemes = new LinkedHashMap<>();
			this.securitySchemes.put(name, value);
		} else {
			this.securitySchemes = DataModelUtil.insertMapEntry(this.securitySchemes, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("securitySchemes");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24Parameter createParameter() {
		AsyncApi24ParameterImpl node = new AsyncApi24ParameterImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiParameter> getParameters() {
		return parameters;
	}

	@Override
	public void addParameter(String name, AsyncApiParameter value) {
		if (this.parameters == null) {
			this.parameters = new LinkedHashMap<>();
		}
		this.parameters.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("parameters");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearParameters() {
		if (this.parameters != null) {
			this.parameters.clear();
		}
	}

	@Override
	public void removeParameter(String name) {
		if (this.parameters != null) {
			this.parameters.remove(name);
		}
	}

	@Override
	public void insertParameter(String name, AsyncApiParameter value, int atIndex) {
		if (this.parameters == null) {
			this.parameters = new LinkedHashMap<>();
			this.parameters.put(name, value);
		} else {
			this.parameters = DataModelUtil.insertMapEntry(this.parameters, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("parameters");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24CorrelationID createCorrelationID() {
		AsyncApi24CorrelationIDImpl node = new AsyncApi24CorrelationIDImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiCorrelationID> getCorrelationIds() {
		return correlationIds;
	}

	@Override
	public void addCorrelationId(String name, AsyncApiCorrelationID value) {
		if (this.correlationIds == null) {
			this.correlationIds = new LinkedHashMap<>();
		}
		this.correlationIds.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("correlationIds");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearCorrelationIds() {
		if (this.correlationIds != null) {
			this.correlationIds.clear();
		}
	}

	@Override
	public void removeCorrelationId(String name) {
		if (this.correlationIds != null) {
			this.correlationIds.remove(name);
		}
	}

	@Override
	public void insertCorrelationId(String name, AsyncApiCorrelationID value, int atIndex) {
		if (this.correlationIds == null) {
			this.correlationIds = new LinkedHashMap<>();
			this.correlationIds.put(name, value);
		} else {
			this.correlationIds = DataModelUtil.insertMapEntry(this.correlationIds, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("correlationIds");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24OperationTrait createOperationTrait() {
		AsyncApi24OperationTraitImpl node = new AsyncApi24OperationTraitImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiOperationTrait> getOperationTraits() {
		return operationTraits;
	}

	@Override
	public void addOperationTrait(String name, AsyncApiOperationTrait value) {
		if (this.operationTraits == null) {
			this.operationTraits = new LinkedHashMap<>();
		}
		this.operationTraits.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("operationTraits");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearOperationTraits() {
		if (this.operationTraits != null) {
			this.operationTraits.clear();
		}
	}

	@Override
	public void removeOperationTrait(String name) {
		if (this.operationTraits != null) {
			this.operationTraits.remove(name);
		}
	}

	@Override
	public void insertOperationTrait(String name, AsyncApiOperationTrait value, int atIndex) {
		if (this.operationTraits == null) {
			this.operationTraits = new LinkedHashMap<>();
			this.operationTraits.put(name, value);
		} else {
			this.operationTraits = DataModelUtil.insertMapEntry(this.operationTraits, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("operationTraits");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24MessageTrait createMessageTrait() {
		AsyncApi24MessageTraitImpl node = new AsyncApi24MessageTraitImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiMessageTrait> getMessageTraits() {
		return messageTraits;
	}

	@Override
	public void addMessageTrait(String name, AsyncApiMessageTrait value) {
		if (this.messageTraits == null) {
			this.messageTraits = new LinkedHashMap<>();
		}
		this.messageTraits.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messageTraits");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearMessageTraits() {
		if (this.messageTraits != null) {
			this.messageTraits.clear();
		}
	}

	@Override
	public void removeMessageTrait(String name) {
		if (this.messageTraits != null) {
			this.messageTraits.remove(name);
		}
	}

	@Override
	public void insertMessageTrait(String name, AsyncApiMessageTrait value, int atIndex) {
		if (this.messageTraits == null) {
			this.messageTraits = new LinkedHashMap<>();
			this.messageTraits.put(name, value);
		} else {
			this.messageTraits = DataModelUtil.insertMapEntry(this.messageTraits, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messageTraits");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24ServerBindings createServerBindings() {
		AsyncApi24ServerBindingsImpl node = new AsyncApi24ServerBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiServerBindings> getServerBindings() {
		return serverBindings;
	}

	@Override
	public void addServerBinding(String name, AsyncApiServerBindings value) {
		if (this.serverBindings == null) {
			this.serverBindings = new LinkedHashMap<>();
		}
		this.serverBindings.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("serverBindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearServerBindings() {
		if (this.serverBindings != null) {
			this.serverBindings.clear();
		}
	}

	@Override
	public void removeServerBinding(String name) {
		if (this.serverBindings != null) {
			this.serverBindings.remove(name);
		}
	}

	@Override
	public void insertServerBinding(String name, AsyncApiServerBindings value, int atIndex) {
		if (this.serverBindings == null) {
			this.serverBindings = new LinkedHashMap<>();
			this.serverBindings.put(name, value);
		} else {
			this.serverBindings = DataModelUtil.insertMapEntry(this.serverBindings, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("serverBindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24ChannelBindings createChannelBindings() {
		AsyncApi24ChannelBindingsImpl node = new AsyncApi24ChannelBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiChannelBindings> getChannelBindings() {
		return channelBindings;
	}

	@Override
	public void addChannelBinding(String name, AsyncApiChannelBindings value) {
		if (this.channelBindings == null) {
			this.channelBindings = new LinkedHashMap<>();
		}
		this.channelBindings.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("channelBindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearChannelBindings() {
		if (this.channelBindings != null) {
			this.channelBindings.clear();
		}
	}

	@Override
	public void removeChannelBinding(String name) {
		if (this.channelBindings != null) {
			this.channelBindings.remove(name);
		}
	}

	@Override
	public void insertChannelBinding(String name, AsyncApiChannelBindings value, int atIndex) {
		if (this.channelBindings == null) {
			this.channelBindings = new LinkedHashMap<>();
			this.channelBindings.put(name, value);
		} else {
			this.channelBindings = DataModelUtil.insertMapEntry(this.channelBindings, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("channelBindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24OperationBindings createOperationBindings() {
		AsyncApi24OperationBindingsImpl node = new AsyncApi24OperationBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiOperationBindings> getOperationBindings() {
		return operationBindings;
	}

	@Override
	public void addOperationBinding(String name, AsyncApiOperationBindings value) {
		if (this.operationBindings == null) {
			this.operationBindings = new LinkedHashMap<>();
		}
		this.operationBindings.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("operationBindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearOperationBindings() {
		if (this.operationBindings != null) {
			this.operationBindings.clear();
		}
	}

	@Override
	public void removeOperationBinding(String name) {
		if (this.operationBindings != null) {
			this.operationBindings.remove(name);
		}
	}

	@Override
	public void insertOperationBinding(String name, AsyncApiOperationBindings value, int atIndex) {
		if (this.operationBindings == null) {
			this.operationBindings = new LinkedHashMap<>();
			this.operationBindings.put(name, value);
		} else {
			this.operationBindings = DataModelUtil.insertMapEntry(this.operationBindings, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("operationBindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi24MessageBindings createMessageBindings() {
		AsyncApi24MessageBindingsImpl node = new AsyncApi24MessageBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiMessageBindings> getMessageBindings() {
		return messageBindings;
	}

	@Override
	public void addMessageBinding(String name, AsyncApiMessageBindings value) {
		if (this.messageBindings == null) {
			this.messageBindings = new LinkedHashMap<>();
		}
		this.messageBindings.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messageBindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearMessageBindings() {
		if (this.messageBindings != null) {
			this.messageBindings.clear();
		}
	}

	@Override
	public void removeMessageBinding(String name) {
		if (this.messageBindings != null) {
			this.messageBindings.remove(name);
		}
	}

	@Override
	public void insertMessageBinding(String name, AsyncApiMessageBindings value, int atIndex) {
		if (this.messageBindings == null) {
			this.messageBindings = new LinkedHashMap<>();
			this.messageBindings.put(name, value);
		} else {
			this.messageBindings = DataModelUtil.insertMapEntry(this.messageBindings, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messageBindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public Map<String, JsonNode> getExtensions() {
		return extensions;
	}

	@Override
	public void addExtension(String name, JsonNode value) {
		if (this.extensions == null) {
			this.extensions = new LinkedHashMap<>();
		}
		this.extensions.put(name, value);
	}

	@Override
	public void clearExtensions() {
		if (this.extensions != null) {
			this.extensions.clear();
		}
	}

	@Override
	public void removeExtension(String name) {
		if (this.extensions != null) {
			this.extensions.remove(name);
		}
	}

	@Override
	public void insertExtension(String name, JsonNode value, int atIndex) {
		if (this.extensions == null) {
			this.extensions = new LinkedHashMap<>();
			this.extensions.put(name, value);
		} else {
			this.extensions = DataModelUtil.insertMapEntry(this.extensions, name, value, atIndex);
		}
	}

	@Override
	public void accept(Visitor visitor) {
		AsyncApi24Visitor viz = (AsyncApi24Visitor) visitor;
		viz.visitComponents(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi24ComponentsImpl();
	}
}