package io.apicurio.datamodels.models.asyncapi.v2x.v23.visitors;

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
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.asyncapi.AsyncApiBinding;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelItem;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannels;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageExample;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOneOfMessages;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameters;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServers;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Tag;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class AsyncApi23Traverser extends AbstractTraverser implements AsyncApi23Visitor {

	public AsyncApi23Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		node.accept(this.visitor);
		AsyncApi23OneOfMessages model = (AsyncApi23OneOfMessages) node;
		this.traverseList("oneOf", model.getOneOf());
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		node.accept(this.visitor);
		AsyncApi23ChannelItem model = (AsyncApi23ChannelItem) node;
		this.traverseNode("subscribe", model.getSubscribe());
		this.traverseNode("publish", model.getPublish());
		this.traverseNode("parameters", model.getParameters());
		this.traverseNode("bindings", model.getBindings());
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		node.accept(this.visitor);
		AsyncApi23MessageBindings model = (AsyncApi23MessageBindings) node;
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
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		node.accept(this.visitor);
		AsyncApi23OperationBindings model = (AsyncApi23OperationBindings) node;
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
	}

	@Override
	public void visitComponents(Components node) {
		node.accept(this.visitor);
		AsyncApi23Components model = (AsyncApi23Components) node;
		this.traverseMap("schemas", model.getSchemas());
		this.traverseMap("servers", model.getServers());
		this.traverseMap("channels", model.getChannels());
		this.traverseMap("messages", model.getMessages());
		this.traverseMap("securitySchemes", model.getSecuritySchemes());
		this.traverseMap("parameters", model.getParameters());
		this.traverseMap("correlationIds", model.getCorrelationIds());
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
		AsyncApi23Parameter model = (AsyncApi23Parameter) node;
		this.traverseNode("schema", model.getSchema());
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		node.accept(this.visitor);
		AsyncApi23ChannelBindings model = (AsyncApi23ChannelBindings) node;
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
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		node.accept(this.visitor);
		AsyncApi23Parameters model = (AsyncApi23Parameters) node;
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
		AsyncApi23Message model = (AsyncApi23Message) node;
		this.traverseList("oneOf", model.getOneOf());
		this.traverseNode("headers", model.getHeaders());
		this.traverseNode("correlationId", model.getCorrelationId());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseNode("bindings", model.getBindings());
		this.traverseNode("examples", model.getExamples());
		this.traverseList("traits", model.getTraits());
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		node.accept(this.visitor);
		AsyncApi23OperationTrait model = (AsyncApi23OperationTrait) node;
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseNode("bindings", model.getBindings());
	}

	@Override
	public void visitServer(Server node) {
		node.accept(this.visitor);
		AsyncApi23Server model = (AsyncApi23Server) node;
		this.traverseMap("variables", model.getVariables());
		this.traverseList("security", model.getSecurity());
		this.traverseNode("bindings", model.getBindings());
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		node.accept(this.visitor);
		AsyncApi23OAuthFlows model = (AsyncApi23OAuthFlows) node;
		this.traverseNode("implicit", model.getImplicit());
		this.traverseNode("password", model.getPassword());
		this.traverseNode("clientCredentials", model.getClientCredentials());
		this.traverseNode("authorizationCode", model.getAuthorizationCode());
	}

	@Override
	public void visitSchema(Schema node) {
		node.accept(this.visitor);
		AsyncApi23Schema model = (AsyncApi23Schema) node;
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
		AsyncApi23ServerBindings model = (AsyncApi23ServerBindings) node;
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
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		node.accept(this.visitor);
		AsyncApi23Servers model = (AsyncApi23Servers) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		node.accept(this.visitor);
		AsyncApi23Channels model = (AsyncApi23Channels) node;
		this.traverseMappedNode(model);
	}

	@Override
	public void visitTag(Tag node) {
		node.accept(this.visitor);
		AsyncApi23Tag model = (AsyncApi23Tag) node;
		this.traverseNode("externalDocs", model.getExternalDocs());
	}

	@Override
	public void visitLicense(License node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitInfo(Info node) {
		node.accept(this.visitor);
		AsyncApi23Info model = (AsyncApi23Info) node;
		this.traverseNode("contact", model.getContact());
		this.traverseNode("license", model.getLicense());
	}

	@Override
	public void visitOperation(Operation node) {
		node.accept(this.visitor);
		AsyncApi23Operation model = (AsyncApi23Operation) node;
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseNode("bindings", model.getBindings());
		this.traverseList("traits", model.getTraits());
		this.traverseNode("message", model.getMessage());
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		node.accept(this.visitor);
		AsyncApi23MessageTrait model = (AsyncApi23MessageTrait) node;
		this.traverseNode("headers", model.getHeaders());
		this.traverseNode("correlationId", model.getCorrelationId());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseNode("bindings", model.getBindings());
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		node.accept(this.visitor);
		AsyncApi23SecurityScheme model = (AsyncApi23SecurityScheme) node;
		this.traverseNode("flows", model.getFlows());
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		AsyncApi23Document model = (AsyncApi23Document) node;
		this.traverseNode("info", model.getInfo());
		this.traverseNode("servers", model.getServers());
		this.traverseNode("channels", model.getChannels());
		this.traverseNode("components", model.getComponents());
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
	}
}