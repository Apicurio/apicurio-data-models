package io.apicurio.datamodels.models.asyncapi.v2x.v20.io;

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
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOneOfMessages;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameters;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServers;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20License;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Tag;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.visitors.AsyncApi20Visitor;

public class AsyncApi20ModelWriterDispatcher implements AsyncApi20Visitor {

	private final ObjectNode json;
	private final AsyncApi20ModelWriter writer;

	public AsyncApi20ModelWriterDispatcher(ObjectNode json, AsyncApi20ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((AsyncApi20SecurityRequirement) node, this.json);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.writer.writeOneOfMessages((AsyncApi20OneOfMessages) node, this.json);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.writer.writeChannelItem((AsyncApi20ChannelItem) node, this.json);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.writer.writeMessageBindings((AsyncApi20MessageBindings) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((AsyncApi20OAuthFlow) node, this.json);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.writer.writeOperationBindings((AsyncApi20OperationBindings) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((AsyncApi20Components) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((AsyncApi20Parameter) node, this.json);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.writer.writeChannelBindings((AsyncApi20ChannelBindings) node, this.json);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.writer.writeParameters((AsyncApi20Parameters) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((AsyncApi20Contact) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((AsyncApi20ServerVariable) node, this.json);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.writer.writeMessage((AsyncApi20Message) node, this.json);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.writer.writeOperationTrait((AsyncApi20OperationTrait) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((AsyncApi20Server) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((AsyncApi20OAuthFlows) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((AsyncApi20Schema) node, this.json);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.writer.writeServerBindings((AsyncApi20ServerBindings) node, this.json);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.writer.writeCorrelationID((AsyncApi20CorrelationID) node, this.json);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.writer.writeServers((AsyncApi20Servers) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((AsyncApi20ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.writer.writeChannels((AsyncApi20Channels) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((AsyncApi20Tag) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((AsyncApi20License) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((AsyncApi20Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((AsyncApi20Operation) node, this.json);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.writer.writeMessageTrait((AsyncApi20MessageTrait) node, this.json);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.writer.writeBinding((AsyncApi20Binding) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((AsyncApi20SecurityScheme) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((AsyncApi20Document) node, this.json);
	}
}