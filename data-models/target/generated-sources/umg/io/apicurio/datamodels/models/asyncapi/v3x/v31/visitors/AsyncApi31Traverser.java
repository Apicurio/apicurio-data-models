package io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.Contact;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.License;
import io.apicurio.datamodels.models.OAuthFlow;
import io.apicurio.datamodels.models.OAuthFlows;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.asyncapi.AsyncApiBinding;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannel;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannels;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageExample;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReply;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperations;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameters;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReference;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServers;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Channel;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Channels;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Components;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Document;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Info;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Message;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Operation;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationReply;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Operations;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Parameters;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Schema;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Server;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Servers;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Tag;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class AsyncApi31Traverser extends AbstractTraverser implements AsyncApi31Visitor {

	public AsyncApi31Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitChannel(AsyncApiChannel node) {
		node.accept(this.visitor);
		AsyncApi31Channel model = (AsyncApi31Channel) node;
		this.traverseMap("messages", model.getMessages());
		this.traverseList("servers", model.getServers());
		this.traverseNode("parameters", model.getParameters());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseNode("bindings", model.getBindings());
	}

	@Override
	public void visitReference(AsyncApiReference node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitMultiFormatSchema(AsyncApiMultiFormatSchema node) {
		node.accept(this.visitor);
		AsyncApi31MultiFormatSchema model = (AsyncApi31MultiFormatSchema) node;
		this.traverseUnion("schema", model.getSchema());
	}

	@Override
	public void visitOperationReplyAddress(AsyncApiOperationReplyAddress node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitOperationReply(AsyncApiOperationReply node) {
		node.accept(this.visitor);
		AsyncApi31OperationReply model = (AsyncApi31OperationReply) node;
		this.traverseNode("address", model.getAddress());
		this.traverseNode("channel", model.getChannel());
		this.traverseList("messages", model.getMessages());
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitOperations(AsyncApiOperations node) {
		node.accept(this.visitor);
		AsyncApi31Operations model = (AsyncApi31Operations) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		node.accept(this.visitor);
		AsyncApi31MessageBindings model = (AsyncApi31MessageBindings) node;
		this.traverseNode("http", model.getHttp());
		this.traverseNode("ws", model.getWs());
		this.traverseNode("kafka", model.getKafka());
		this.traverseNode("anypointmq", model.getAnypointmq());
		this.traverseNode("amqp", model.getAmqp());
		this.traverseNode("amqp1", model.getAmqp1());
		this.traverseNode("mqtt", model.getMqtt());
		this.traverseNode("mqtt5", model.getMqtt5());
		this.traverseNode("nats", model.getNats());
		this.traverseNode("jms", model.getJms());
		this.traverseNode("sns", model.getSns());
		this.traverseNode("solace", model.getSolace());
		this.traverseNode("sqs", model.getSqs());
		this.traverseNode("stomp", model.getStomp());
		this.traverseNode("redis", model.getRedis());
		this.traverseNode("mercure", model.getMercure());
		this.traverseNode("ibmmq", model.getIbmmq());
		this.traverseNode("googlepubsub", model.getGooglepubsub());
		this.traverseNode("pulsar", model.getPulsar());
		this.traverseNode("ros2", model.getRos2());
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		node.accept(this.visitor);
		AsyncApi31OperationBindings model = (AsyncApi31OperationBindings) node;
		this.traverseNode("http", model.getHttp());
		this.traverseNode("ws", model.getWs());
		this.traverseNode("kafka", model.getKafka());
		this.traverseNode("anypointmq", model.getAnypointmq());
		this.traverseNode("amqp", model.getAmqp());
		this.traverseNode("amqp1", model.getAmqp1());
		this.traverseNode("mqtt", model.getMqtt());
		this.traverseNode("mqtt5", model.getMqtt5());
		this.traverseNode("nats", model.getNats());
		this.traverseNode("jms", model.getJms());
		this.traverseNode("sns", model.getSns());
		this.traverseNode("solace", model.getSolace());
		this.traverseNode("sqs", model.getSqs());
		this.traverseNode("stomp", model.getStomp());
		this.traverseNode("redis", model.getRedis());
		this.traverseNode("mercure", model.getMercure());
		this.traverseNode("ibmmq", model.getIbmmq());
		this.traverseNode("googlepubsub", model.getGooglepubsub());
		this.traverseNode("pulsar", model.getPulsar());
		this.traverseNode("ros2", model.getRos2());
	}

	@Override
	public void visitComponents(Components node) {
		node.accept(this.visitor);
		AsyncApi31Components model = (AsyncApi31Components) node;
		this.traverseMap("servers", model.getServers());
		this.traverseMap("channels", model.getChannels());
		this.traverseMap("operations", model.getOperations());
		this.traverseMap("messages", model.getMessages());
		this.traverseMap("securitySchemes", model.getSecuritySchemes());
		this.traverseMap("serverVariables", model.getServerVariables());
		this.traverseMap("parameters", model.getParameters());
		this.traverseMap("correlationIds", model.getCorrelationIds());
		this.traverseMap("replies", model.getReplies());
		this.traverseMap("replyAddresses", model.getReplyAddresses());
		this.traverseMap("externalDocs", model.getExternalDocs());
		this.traverseMap("tags", model.getTags());
		this.traverseMap("operationTraits", model.getOperationTraits());
		this.traverseMap("messageTraits", model.getMessageTraits());
		this.traverseMap("serverBindings", model.getServerBindings());
		this.traverseMap("channelBindings", model.getChannelBindings());
		this.traverseMap("operationBindings", model.getOperationBindings());
		this.traverseMap("messageBindings", model.getMessageBindings());
	}

	@Override
	public void visitParameter(Parameter node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		node.accept(this.visitor);
		AsyncApi31ChannelBindings model = (AsyncApi31ChannelBindings) node;
		this.traverseNode("http", model.getHttp());
		this.traverseNode("ws", model.getWs());
		this.traverseNode("kafka", model.getKafka());
		this.traverseNode("anypointmq", model.getAnypointmq());
		this.traverseNode("amqp", model.getAmqp());
		this.traverseNode("amqp1", model.getAmqp1());
		this.traverseNode("mqtt", model.getMqtt());
		this.traverseNode("mqtt5", model.getMqtt5());
		this.traverseNode("nats", model.getNats());
		this.traverseNode("jms", model.getJms());
		this.traverseNode("sns", model.getSns());
		this.traverseNode("solace", model.getSolace());
		this.traverseNode("sqs", model.getSqs());
		this.traverseNode("stomp", model.getStomp());
		this.traverseNode("redis", model.getRedis());
		this.traverseNode("mercure", model.getMercure());
		this.traverseNode("ibmmq", model.getIbmmq());
		this.traverseNode("googlepubsub", model.getGooglepubsub());
		this.traverseNode("pulsar", model.getPulsar());
		this.traverseNode("ros2", model.getRos2());
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		node.accept(this.visitor);
		AsyncApi31Parameters model = (AsyncApi31Parameters) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitContact(Contact node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		node.accept(this.visitor);
		AsyncApi31Message model = (AsyncApi31Message) node;
		this.traverseUnion("headers", model.getHeaders());
		this.traverseUnion("payload", model.getPayload());
		this.traverseNode("correlationId", model.getCorrelationId());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseNode("bindings", model.getBindings());
		this.traverseList("examples", model.getExamples());
		this.traverseList("traits", model.getTraits());
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		node.accept(this.visitor);
		AsyncApi31OperationTrait model = (AsyncApi31OperationTrait) node;
		this.traverseList("security", model.getSecurity());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseNode("bindings", model.getBindings());
	}

	@Override
	public void visitServer(Server node) {
		node.accept(this.visitor);
		AsyncApi31Server model = (AsyncApi31Server) node;
		this.traverseMap("variables", model.getVariables());
		this.traverseList("security", model.getSecurity());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseNode("bindings", model.getBindings());
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		node.accept(this.visitor);
		AsyncApi31OAuthFlows model = (AsyncApi31OAuthFlows) node;
		this.traverseNode("implicit", model.getImplicit());
		this.traverseNode("password", model.getPassword());
		this.traverseNode("clientCredentials", model.getClientCredentials());
		this.traverseNode("authorizationCode", model.getAuthorizationCode());
	}

	@Override
	public void visitSchema(Schema node) {
		node.accept(this.visitor);
		AsyncApi31Schema model = (AsyncApi31Schema) node;
		this.traverseNode("if", model.getIf());
		this.traverseNode("then", model.getThen());
		this.traverseNode("else", model.getElse());
		this.traverseMap("properties", model.getProperties());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseNode("additionalItems", model.getAdditionalItems());
		this.traverseUnion("items", model.getItems());
		this.traverseNode("propertyNames", model.getPropertyNames());
		this.traverseNode("contains", model.getContains());
		this.traverseList("allOf", model.getAllOf());
		this.traverseList("oneOf", model.getOneOf());
		this.traverseList("anyOf", model.getAnyOf());
		this.traverseNode("not", model.getNot());
		this.traverseNode("externalDocs", model.getExternalDocs());
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		node.accept(this.visitor);
		AsyncApi31ServerBindings model = (AsyncApi31ServerBindings) node;
		this.traverseNode("http", model.getHttp());
		this.traverseNode("ws", model.getWs());
		this.traverseNode("kafka", model.getKafka());
		this.traverseNode("anypointmq", model.getAnypointmq());
		this.traverseNode("amqp", model.getAmqp());
		this.traverseNode("amqp1", model.getAmqp1());
		this.traverseNode("mqtt", model.getMqtt());
		this.traverseNode("mqtt5", model.getMqtt5());
		this.traverseNode("nats", model.getNats());
		this.traverseNode("jms", model.getJms());
		this.traverseNode("sns", model.getSns());
		this.traverseNode("solace", model.getSolace());
		this.traverseNode("sqs", model.getSqs());
		this.traverseNode("stomp", model.getStomp());
		this.traverseNode("redis", model.getRedis());
		this.traverseNode("mercure", model.getMercure());
		this.traverseNode("ibmmq", model.getIbmmq());
		this.traverseNode("googlepubsub", model.getGooglepubsub());
		this.traverseNode("pulsar", model.getPulsar());
		this.traverseNode("ros2", model.getRos2());
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		node.accept(this.visitor);
		AsyncApi31Servers model = (AsyncApi31Servers) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		node.accept(this.visitor);
		AsyncApi31Channels model = (AsyncApi31Channels) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitTag(Tag node) {
		node.accept(this.visitor);
		AsyncApi31Tag model = (AsyncApi31Tag) node;
		this.traverseNode("externalDocs", model.getExternalDocs());
	}

	@Override
	public void visitLicense(License node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitInfo(Info node) {
		node.accept(this.visitor);
		AsyncApi31Info model = (AsyncApi31Info) node;
		this.traverseNode("contact", model.getContact());
		this.traverseNode("license", model.getLicense());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
	}

	@Override
	public void visitOperation(Operation node) {
		node.accept(this.visitor);
		AsyncApi31Operation model = (AsyncApi31Operation) node;
		this.traverseNode("channel", model.getChannel());
		this.traverseList("security", model.getSecurity());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseNode("bindings", model.getBindings());
		this.traverseList("traits", model.getTraits());
		this.traverseList("messages", model.getMessages());
		this.traverseNode("reply", model.getReply());
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		node.accept(this.visitor);
		AsyncApi31MessageTrait model = (AsyncApi31MessageTrait) node;
		this.traverseUnion("headers", model.getHeaders());
		this.traverseNode("correlationId", model.getCorrelationId());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseNode("bindings", model.getBindings());
		this.traverseList("examples", model.getExamples());
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		node.accept(this.visitor);
		AsyncApi31SecurityScheme model = (AsyncApi31SecurityScheme) node;
		this.traverseNode("flows", model.getFlows());
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		AsyncApi31Document model = (AsyncApi31Document) node;
		this.traverseNode("info", model.getInfo());
		this.traverseNode("servers", model.getServers());
		this.traverseNode("channels", model.getChannels());
		this.traverseNode("operations", model.getOperations());
		this.traverseNode("components", model.getComponents());
	}
}