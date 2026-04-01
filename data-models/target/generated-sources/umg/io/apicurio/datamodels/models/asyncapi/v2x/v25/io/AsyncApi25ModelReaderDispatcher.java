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

public class AsyncApi25ModelReaderDispatcher implements AsyncApi25Visitor {

	private final ObjectNode json;
	private final AsyncApi25ModelReader reader;

	public AsyncApi25ModelReaderDispatcher(ObjectNode json, AsyncApi25ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.reader.readMessageExample(this.json, (AsyncApi25MessageExample) node);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.reader.readSecurityRequirement(this.json, (AsyncApi25SecurityRequirement) node);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.reader.readOneOfMessages(this.json, (AsyncApi25OneOfMessages) node);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.reader.readChannelItem(this.json, (AsyncApi25ChannelItem) node);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.reader.readMessageBindings(this.json, (AsyncApi25MessageBindings) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (AsyncApi25OAuthFlow) node);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.reader.readOperationBindings(this.json, (AsyncApi25OperationBindings) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (AsyncApi25Components) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (AsyncApi25Parameter) node);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.reader.readChannelBindings(this.json, (AsyncApi25ChannelBindings) node);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.reader.readParameters(this.json, (AsyncApi25Parameters) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (AsyncApi25Contact) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (AsyncApi25ServerVariable) node);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.reader.readMessage(this.json, (AsyncApi25Message) node);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.reader.readOperationTrait(this.json, (AsyncApi25OperationTrait) node);
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (AsyncApi25Server) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (AsyncApi25OAuthFlows) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (AsyncApi25Schema) node);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.reader.readServerBindings(this.json, (AsyncApi25ServerBindings) node);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.reader.readCorrelationID(this.json, (AsyncApi25CorrelationID) node);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.reader.readServers(this.json, (AsyncApi25Servers) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (AsyncApi25ExternalDocumentation) node);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.reader.readChannels(this.json, (AsyncApi25Channels) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (AsyncApi25Tag) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (AsyncApi25License) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (AsyncApi25Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (AsyncApi25Operation) node);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.reader.readMessageTrait(this.json, (AsyncApi25MessageTrait) node);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.reader.readBinding(this.json, (AsyncApi25Binding) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (AsyncApi25SecurityScheme) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (AsyncApi25Document) node);
	}
}