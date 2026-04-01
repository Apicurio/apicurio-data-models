package io.apicurio.datamodels.models.asyncapi.v3x.v31.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Binding;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Channel;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Channels;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Components;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Contact;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Document;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Info;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31License;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Message;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageExample;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Operation;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationReply;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Operations;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Parameter;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Parameters;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Reference;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Schema;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Server;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Servers;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Tag;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;

public class AsyncApi31ModelWriterDispatcher implements AsyncApi31Visitor {

	private final ObjectNode json;
	private final AsyncApi31ModelWriter writer;

	public AsyncApi31ModelWriterDispatcher(ObjectNode json, AsyncApi31ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitChannel(AsyncApiChannel node) {
		this.writer.writeChannel((AsyncApi31Channel) node, this.json);
	}

	@Override
	public void visitReference(AsyncApiReference node) {
		this.writer.writeReference((AsyncApi31Reference) node, this.json);
	}

	@Override
	public void visitMultiFormatSchema(AsyncApiMultiFormatSchema node) {
		this.writer.writeMultiFormatSchema((AsyncApi31MultiFormatSchema) node, this.json);
	}

	@Override
	public void visitOperationReplyAddress(AsyncApiOperationReplyAddress node) {
		this.writer.writeOperationReplyAddress((AsyncApi31OperationReplyAddress) node, this.json);
	}

	@Override
	public void visitOperationReply(AsyncApiOperationReply node) {
		this.writer.writeOperationReply((AsyncApi31OperationReply) node, this.json);
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.writer.writeMessageExample((AsyncApi31MessageExample) node, this.json);
	}

	@Override
	public void visitOperations(AsyncApiOperations node) {
		this.writer.writeOperations((AsyncApi31Operations) node, this.json);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.writer.writeMessageBindings((AsyncApi31MessageBindings) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((AsyncApi31OAuthFlow) node, this.json);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.writer.writeOperationBindings((AsyncApi31OperationBindings) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((AsyncApi31Components) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((AsyncApi31Parameter) node, this.json);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.writer.writeChannelBindings((AsyncApi31ChannelBindings) node, this.json);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.writer.writeParameters((AsyncApi31Parameters) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((AsyncApi31Contact) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((AsyncApi31ServerVariable) node, this.json);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.writer.writeMessage((AsyncApi31Message) node, this.json);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.writer.writeOperationTrait((AsyncApi31OperationTrait) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((AsyncApi31Server) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((AsyncApi31OAuthFlows) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((AsyncApi31Schema) node, this.json);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.writer.writeServerBindings((AsyncApi31ServerBindings) node, this.json);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.writer.writeCorrelationID((AsyncApi31CorrelationID) node, this.json);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.writer.writeServers((AsyncApi31Servers) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((AsyncApi31ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.writer.writeChannels((AsyncApi31Channels) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((AsyncApi31Tag) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((AsyncApi31License) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((AsyncApi31Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((AsyncApi31Operation) node, this.json);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.writer.writeMessageTrait((AsyncApi31MessageTrait) node, this.json);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.writer.writeBinding((AsyncApi31Binding) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((AsyncApi31SecurityScheme) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((AsyncApi31Document) node, this.json);
	}
}