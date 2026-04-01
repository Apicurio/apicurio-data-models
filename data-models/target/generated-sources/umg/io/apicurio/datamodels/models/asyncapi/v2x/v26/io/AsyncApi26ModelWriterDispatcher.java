package io.apicurio.datamodels.models.asyncapi.v2x.v26.io;

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
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26License;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26MessageExample;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.AsyncApi26Tag;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.visitors.AsyncApi26Visitor;

public class AsyncApi26ModelWriterDispatcher implements AsyncApi26Visitor {

	private final ObjectNode json;
	private final AsyncApi26ModelWriter writer;

	public AsyncApi26ModelWriterDispatcher(ObjectNode json, AsyncApi26ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.writer.writeMessageExample((AsyncApi26MessageExample) node, this.json);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((AsyncApi26SecurityRequirement) node, this.json);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.writer.writeOneOfMessages((AsyncApi26OneOfMessages) node, this.json);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.writer.writeChannelItem((AsyncApi26ChannelItem) node, this.json);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.writer.writeMessageBindings((AsyncApi26MessageBindings) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((AsyncApi26OAuthFlow) node, this.json);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.writer.writeOperationBindings((AsyncApi26OperationBindings) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((AsyncApi26Components) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((AsyncApi26Parameter) node, this.json);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.writer.writeChannelBindings((AsyncApi26ChannelBindings) node, this.json);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.writer.writeParameters((AsyncApi26Parameters) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((AsyncApi26Contact) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((AsyncApi26ServerVariable) node, this.json);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.writer.writeMessage((AsyncApi26Message) node, this.json);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.writer.writeOperationTrait((AsyncApi26OperationTrait) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((AsyncApi26Server) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((AsyncApi26OAuthFlows) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((AsyncApi26Schema) node, this.json);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.writer.writeServerBindings((AsyncApi26ServerBindings) node, this.json);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.writer.writeCorrelationID((AsyncApi26CorrelationID) node, this.json);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.writer.writeServers((AsyncApi26Servers) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((AsyncApi26ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.writer.writeChannels((AsyncApi26Channels) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((AsyncApi26Tag) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((AsyncApi26License) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((AsyncApi26Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((AsyncApi26Operation) node, this.json);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.writer.writeMessageTrait((AsyncApi26MessageTrait) node, this.json);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.writer.writeBinding((AsyncApi26Binding) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((AsyncApi26SecurityScheme) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((AsyncApi26Document) node, this.json);
	}
}