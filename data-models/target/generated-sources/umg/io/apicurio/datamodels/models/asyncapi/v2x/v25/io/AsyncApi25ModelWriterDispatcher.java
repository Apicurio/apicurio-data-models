package io.apicurio.datamodels.models.asyncapi.v2x.v25.io;

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
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25License;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25MessageExample;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Tag;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.visitors.AsyncApi25Visitor;

public class AsyncApi25ModelWriterDispatcher implements AsyncApi25Visitor {

	private final ObjectNode json;
	private final AsyncApi25ModelWriter writer;

	public AsyncApi25ModelWriterDispatcher(ObjectNode json, AsyncApi25ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.writer.writeMessageExample((AsyncApi25MessageExample) node, this.json);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((AsyncApi25SecurityRequirement) node, this.json);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.writer.writeOneOfMessages((AsyncApi25OneOfMessages) node, this.json);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.writer.writeChannelItem((AsyncApi25ChannelItem) node, this.json);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.writer.writeMessageBindings((AsyncApi25MessageBindings) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((AsyncApi25OAuthFlow) node, this.json);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.writer.writeOperationBindings((AsyncApi25OperationBindings) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((AsyncApi25Components) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((AsyncApi25Parameter) node, this.json);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.writer.writeChannelBindings((AsyncApi25ChannelBindings) node, this.json);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.writer.writeParameters((AsyncApi25Parameters) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((AsyncApi25Contact) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((AsyncApi25ServerVariable) node, this.json);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.writer.writeMessage((AsyncApi25Message) node, this.json);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.writer.writeOperationTrait((AsyncApi25OperationTrait) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((AsyncApi25Server) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((AsyncApi25OAuthFlows) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((AsyncApi25Schema) node, this.json);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.writer.writeServerBindings((AsyncApi25ServerBindings) node, this.json);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.writer.writeCorrelationID((AsyncApi25CorrelationID) node, this.json);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.writer.writeServers((AsyncApi25Servers) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((AsyncApi25ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.writer.writeChannels((AsyncApi25Channels) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((AsyncApi25Tag) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((AsyncApi25License) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((AsyncApi25Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((AsyncApi25Operation) node, this.json);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.writer.writeMessageTrait((AsyncApi25MessageTrait) node, this.json);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.writer.writeBinding((AsyncApi25Binding) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((AsyncApi25SecurityScheme) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((AsyncApi25Document) node, this.json);
	}
}