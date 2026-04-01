package io.apicurio.datamodels.models.asyncapi.v3x.v30.io;

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
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Binding;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Channel;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Channels;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Components;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Contact;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Document;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Info;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30License;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Message;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30MessageExample;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30MultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Operation;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OperationReply;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Operations;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Parameter;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Parameters;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Reference;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Schema;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Server;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Servers;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Tag;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;

public class AsyncApi30ModelWriterDispatcher implements AsyncApi30Visitor {

	private final ObjectNode json;
	private final AsyncApi30ModelWriter writer;

	public AsyncApi30ModelWriterDispatcher(ObjectNode json, AsyncApi30ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitChannel(AsyncApiChannel node) {
		this.writer.writeChannel((AsyncApi30Channel) node, this.json);
	}

	@Override
	public void visitReference(AsyncApiReference node) {
		this.writer.writeReference((AsyncApi30Reference) node, this.json);
	}

	@Override
	public void visitMultiFormatSchema(AsyncApiMultiFormatSchema node) {
		this.writer.writeMultiFormatSchema((AsyncApi30MultiFormatSchema) node, this.json);
	}

	@Override
	public void visitOperationReplyAddress(AsyncApiOperationReplyAddress node) {
		this.writer.writeOperationReplyAddress((AsyncApi30OperationReplyAddress) node, this.json);
	}

	@Override
	public void visitOperationReply(AsyncApiOperationReply node) {
		this.writer.writeOperationReply((AsyncApi30OperationReply) node, this.json);
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.writer.writeMessageExample((AsyncApi30MessageExample) node, this.json);
	}

	@Override
	public void visitOperations(AsyncApiOperations node) {
		this.writer.writeOperations((AsyncApi30Operations) node, this.json);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.writer.writeMessageBindings((AsyncApi30MessageBindings) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((AsyncApi30OAuthFlow) node, this.json);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.writer.writeOperationBindings((AsyncApi30OperationBindings) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((AsyncApi30Components) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((AsyncApi30Parameter) node, this.json);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.writer.writeChannelBindings((AsyncApi30ChannelBindings) node, this.json);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.writer.writeParameters((AsyncApi30Parameters) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((AsyncApi30Contact) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((AsyncApi30ServerVariable) node, this.json);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.writer.writeMessage((AsyncApi30Message) node, this.json);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.writer.writeOperationTrait((AsyncApi30OperationTrait) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((AsyncApi30Server) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((AsyncApi30OAuthFlows) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((AsyncApi30Schema) node, this.json);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.writer.writeServerBindings((AsyncApi30ServerBindings) node, this.json);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.writer.writeCorrelationID((AsyncApi30CorrelationID) node, this.json);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.writer.writeServers((AsyncApi30Servers) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((AsyncApi30ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.writer.writeChannels((AsyncApi30Channels) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((AsyncApi30Tag) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((AsyncApi30License) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((AsyncApi30Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((AsyncApi30Operation) node, this.json);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.writer.writeMessageTrait((AsyncApi30MessageTrait) node, this.json);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.writer.writeBinding((AsyncApi30Binding) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((AsyncApi30SecurityScheme) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((AsyncApi30Document) node, this.json);
	}
}