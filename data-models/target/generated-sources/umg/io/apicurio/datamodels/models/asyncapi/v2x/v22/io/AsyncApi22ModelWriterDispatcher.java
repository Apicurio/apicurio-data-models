package io.apicurio.datamodels.models.asyncapi.v2x.v22.io;

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
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22License;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22MessageExample;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Tag;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.visitors.AsyncApi22Visitor;

public class AsyncApi22ModelWriterDispatcher implements AsyncApi22Visitor {

	private final ObjectNode json;
	private final AsyncApi22ModelWriter writer;

	public AsyncApi22ModelWriterDispatcher(ObjectNode json, AsyncApi22ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.writer.writeMessageExample((AsyncApi22MessageExample) node, this.json);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((AsyncApi22SecurityRequirement) node, this.json);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.writer.writeOneOfMessages((AsyncApi22OneOfMessages) node, this.json);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.writer.writeChannelItem((AsyncApi22ChannelItem) node, this.json);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.writer.writeMessageBindings((AsyncApi22MessageBindings) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((AsyncApi22OAuthFlow) node, this.json);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.writer.writeOperationBindings((AsyncApi22OperationBindings) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((AsyncApi22Components) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((AsyncApi22Parameter) node, this.json);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.writer.writeChannelBindings((AsyncApi22ChannelBindings) node, this.json);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.writer.writeParameters((AsyncApi22Parameters) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((AsyncApi22Contact) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((AsyncApi22ServerVariable) node, this.json);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.writer.writeMessage((AsyncApi22Message) node, this.json);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.writer.writeOperationTrait((AsyncApi22OperationTrait) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((AsyncApi22Server) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((AsyncApi22OAuthFlows) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((AsyncApi22Schema) node, this.json);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.writer.writeServerBindings((AsyncApi22ServerBindings) node, this.json);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.writer.writeCorrelationID((AsyncApi22CorrelationID) node, this.json);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.writer.writeServers((AsyncApi22Servers) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((AsyncApi22ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.writer.writeChannels((AsyncApi22Channels) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((AsyncApi22Tag) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((AsyncApi22License) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((AsyncApi22Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((AsyncApi22Operation) node, this.json);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.writer.writeMessageTrait((AsyncApi22MessageTrait) node, this.json);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.writer.writeBinding((AsyncApi22Binding) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((AsyncApi22SecurityScheme) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((AsyncApi22Document) node, this.json);
	}
}