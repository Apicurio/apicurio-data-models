package io.apicurio.datamodels.models.asyncapi.v2x.v23.io;

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
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23License;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23MessageExample;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Tag;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.visitors.AsyncApi23Visitor;

public class AsyncApi23ModelWriterDispatcher implements AsyncApi23Visitor {

	private final ObjectNode json;
	private final AsyncApi23ModelWriter writer;

	public AsyncApi23ModelWriterDispatcher(ObjectNode json, AsyncApi23ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.writer.writeMessageExample((AsyncApi23MessageExample) node, this.json);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((AsyncApi23SecurityRequirement) node, this.json);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.writer.writeOneOfMessages((AsyncApi23OneOfMessages) node, this.json);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.writer.writeChannelItem((AsyncApi23ChannelItem) node, this.json);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.writer.writeMessageBindings((AsyncApi23MessageBindings) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((AsyncApi23OAuthFlow) node, this.json);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.writer.writeOperationBindings((AsyncApi23OperationBindings) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((AsyncApi23Components) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((AsyncApi23Parameter) node, this.json);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.writer.writeChannelBindings((AsyncApi23ChannelBindings) node, this.json);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.writer.writeParameters((AsyncApi23Parameters) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((AsyncApi23Contact) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((AsyncApi23ServerVariable) node, this.json);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.writer.writeMessage((AsyncApi23Message) node, this.json);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.writer.writeOperationTrait((AsyncApi23OperationTrait) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((AsyncApi23Server) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((AsyncApi23OAuthFlows) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((AsyncApi23Schema) node, this.json);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.writer.writeServerBindings((AsyncApi23ServerBindings) node, this.json);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.writer.writeCorrelationID((AsyncApi23CorrelationID) node, this.json);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.writer.writeServers((AsyncApi23Servers) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((AsyncApi23ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.writer.writeChannels((AsyncApi23Channels) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((AsyncApi23Tag) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((AsyncApi23License) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((AsyncApi23Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((AsyncApi23Operation) node, this.json);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.writer.writeMessageTrait((AsyncApi23MessageTrait) node, this.json);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.writer.writeBinding((AsyncApi23Binding) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((AsyncApi23SecurityScheme) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((AsyncApi23Document) node, this.json);
	}
}