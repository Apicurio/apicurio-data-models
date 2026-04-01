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

public class AsyncApi24ModelReaderDispatcher implements AsyncApi24Visitor {

	private final ObjectNode json;
	private final AsyncApi24ModelReader reader;

	public AsyncApi24ModelReaderDispatcher(ObjectNode json, AsyncApi24ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.reader.readMessageExample(this.json, (AsyncApi24MessageExample) node);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.reader.readSecurityRequirement(this.json, (AsyncApi24SecurityRequirement) node);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.reader.readOneOfMessages(this.json, (AsyncApi24OneOfMessages) node);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.reader.readChannelItem(this.json, (AsyncApi24ChannelItem) node);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.reader.readMessageBindings(this.json, (AsyncApi24MessageBindings) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (AsyncApi24OAuthFlow) node);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.reader.readOperationBindings(this.json, (AsyncApi24OperationBindings) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (AsyncApi24Components) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (AsyncApi24Parameter) node);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.reader.readChannelBindings(this.json, (AsyncApi24ChannelBindings) node);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.reader.readParameters(this.json, (AsyncApi24Parameters) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (AsyncApi24Contact) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (AsyncApi24ServerVariable) node);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.reader.readMessage(this.json, (AsyncApi24Message) node);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.reader.readOperationTrait(this.json, (AsyncApi24OperationTrait) node);
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (AsyncApi24Server) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (AsyncApi24OAuthFlows) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (AsyncApi24Schema) node);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.reader.readServerBindings(this.json, (AsyncApi24ServerBindings) node);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.reader.readCorrelationID(this.json, (AsyncApi24CorrelationID) node);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.reader.readServers(this.json, (AsyncApi24Servers) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (AsyncApi24ExternalDocumentation) node);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.reader.readChannels(this.json, (AsyncApi24Channels) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (AsyncApi24Tag) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (AsyncApi24License) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (AsyncApi24Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (AsyncApi24Operation) node);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.reader.readMessageTrait(this.json, (AsyncApi24MessageTrait) node);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.reader.readBinding(this.json, (AsyncApi24Binding) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (AsyncApi24SecurityScheme) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (AsyncApi24Document) node);
	}
}