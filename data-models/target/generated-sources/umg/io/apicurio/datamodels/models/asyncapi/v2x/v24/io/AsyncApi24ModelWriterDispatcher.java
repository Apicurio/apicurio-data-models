package io.apicurio.datamodels.models.asyncapi.v2x.v24.io;

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
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24License;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24MessageExample;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Tag;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.visitors.AsyncApi24Visitor;

public class AsyncApi24ModelWriterDispatcher implements AsyncApi24Visitor {

	private final ObjectNode json;
	private final AsyncApi24ModelWriter writer;

	public AsyncApi24ModelWriterDispatcher(ObjectNode json, AsyncApi24ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.writer.writeMessageExample((AsyncApi24MessageExample) node, this.json);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((AsyncApi24SecurityRequirement) node, this.json);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.writer.writeOneOfMessages((AsyncApi24OneOfMessages) node, this.json);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.writer.writeChannelItem((AsyncApi24ChannelItem) node, this.json);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.writer.writeMessageBindings((AsyncApi24MessageBindings) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((AsyncApi24OAuthFlow) node, this.json);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.writer.writeOperationBindings((AsyncApi24OperationBindings) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((AsyncApi24Components) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((AsyncApi24Parameter) node, this.json);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.writer.writeChannelBindings((AsyncApi24ChannelBindings) node, this.json);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.writer.writeParameters((AsyncApi24Parameters) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((AsyncApi24Contact) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((AsyncApi24ServerVariable) node, this.json);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.writer.writeMessage((AsyncApi24Message) node, this.json);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.writer.writeOperationTrait((AsyncApi24OperationTrait) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((AsyncApi24Server) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((AsyncApi24OAuthFlows) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((AsyncApi24Schema) node, this.json);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.writer.writeServerBindings((AsyncApi24ServerBindings) node, this.json);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.writer.writeCorrelationID((AsyncApi24CorrelationID) node, this.json);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.writer.writeServers((AsyncApi24Servers) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((AsyncApi24ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.writer.writeChannels((AsyncApi24Channels) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((AsyncApi24Tag) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((AsyncApi24License) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((AsyncApi24Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((AsyncApi24Operation) node, this.json);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.writer.writeMessageTrait((AsyncApi24MessageTrait) node, this.json);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.writer.writeBinding((AsyncApi24Binding) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((AsyncApi24SecurityScheme) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((AsyncApi24Document) node, this.json);
	}
}