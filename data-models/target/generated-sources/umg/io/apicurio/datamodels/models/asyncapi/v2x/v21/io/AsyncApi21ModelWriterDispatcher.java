package io.apicurio.datamodels.models.asyncapi.v2x.v21.io;

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
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21License;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.AsyncApi21Tag;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.visitors.AsyncApi21Visitor;

public class AsyncApi21ModelWriterDispatcher implements AsyncApi21Visitor {

	private final ObjectNode json;
	private final AsyncApi21ModelWriter writer;

	public AsyncApi21ModelWriterDispatcher(ObjectNode json, AsyncApi21ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.writer.writeSecurityRequirement((AsyncApi21SecurityRequirement) node, this.json);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.writer.writeOneOfMessages((AsyncApi21OneOfMessages) node, this.json);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.writer.writeChannelItem((AsyncApi21ChannelItem) node, this.json);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.writer.writeMessageBindings((AsyncApi21MessageBindings) node, this.json);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.writer.writeOAuthFlow((AsyncApi21OAuthFlow) node, this.json);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.writer.writeOperationBindings((AsyncApi21OperationBindings) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((AsyncApi21Components) node, this.json);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.writer.writeParameter((AsyncApi21Parameter) node, this.json);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.writer.writeChannelBindings((AsyncApi21ChannelBindings) node, this.json);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.writer.writeParameters((AsyncApi21Parameters) node, this.json);
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((AsyncApi21Contact) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((AsyncApi21ServerVariable) node, this.json);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.writer.writeMessage((AsyncApi21Message) node, this.json);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.writer.writeOperationTrait((AsyncApi21OperationTrait) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((AsyncApi21Server) node, this.json);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.writer.writeOAuthFlows((AsyncApi21OAuthFlows) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((AsyncApi21Schema) node, this.json);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.writer.writeServerBindings((AsyncApi21ServerBindings) node, this.json);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.writer.writeCorrelationID((AsyncApi21CorrelationID) node, this.json);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.writer.writeServers((AsyncApi21Servers) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((AsyncApi21ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.writer.writeChannels((AsyncApi21Channels) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((AsyncApi21Tag) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((AsyncApi21License) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((AsyncApi21Info) node, this.json);
	}

	@Override
	public void visitOperation(Operation node) {
		this.writer.writeOperation((AsyncApi21Operation) node, this.json);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.writer.writeMessageTrait((AsyncApi21MessageTrait) node, this.json);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.writer.writeBinding((AsyncApi21Binding) node, this.json);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.writer.writeSecurityScheme((AsyncApi21SecurityScheme) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((AsyncApi21Document) node, this.json);
	}
}