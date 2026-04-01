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

public class AsyncApi21ModelReaderDispatcher implements AsyncApi21Visitor {

	private final ObjectNode json;
	private final AsyncApi21ModelReader reader;

	public AsyncApi21ModelReaderDispatcher(ObjectNode json, AsyncApi21ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.reader.readSecurityRequirement(this.json, (AsyncApi21SecurityRequirement) node);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.reader.readOneOfMessages(this.json, (AsyncApi21OneOfMessages) node);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.reader.readChannelItem(this.json, (AsyncApi21ChannelItem) node);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.reader.readMessageBindings(this.json, (AsyncApi21MessageBindings) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (AsyncApi21OAuthFlow) node);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.reader.readOperationBindings(this.json, (AsyncApi21OperationBindings) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (AsyncApi21Components) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (AsyncApi21Parameter) node);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.reader.readChannelBindings(this.json, (AsyncApi21ChannelBindings) node);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.reader.readParameters(this.json, (AsyncApi21Parameters) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (AsyncApi21Contact) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (AsyncApi21ServerVariable) node);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.reader.readMessage(this.json, (AsyncApi21Message) node);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.reader.readOperationTrait(this.json, (AsyncApi21OperationTrait) node);
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (AsyncApi21Server) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (AsyncApi21OAuthFlows) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (AsyncApi21Schema) node);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.reader.readServerBindings(this.json, (AsyncApi21ServerBindings) node);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.reader.readCorrelationID(this.json, (AsyncApi21CorrelationID) node);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.reader.readServers(this.json, (AsyncApi21Servers) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (AsyncApi21ExternalDocumentation) node);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.reader.readChannels(this.json, (AsyncApi21Channels) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (AsyncApi21Tag) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (AsyncApi21License) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (AsyncApi21Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (AsyncApi21Operation) node);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.reader.readMessageTrait(this.json, (AsyncApi21MessageTrait) node);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.reader.readBinding(this.json, (AsyncApi21Binding) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (AsyncApi21SecurityScheme) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (AsyncApi21Document) node);
	}
}