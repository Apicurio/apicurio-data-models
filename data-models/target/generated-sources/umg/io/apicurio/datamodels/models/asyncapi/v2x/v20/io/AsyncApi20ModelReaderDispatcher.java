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

public class AsyncApi20ModelReaderDispatcher implements AsyncApi20Visitor {

	private final ObjectNode json;
	private final AsyncApi20ModelReader reader;

	public AsyncApi20ModelReaderDispatcher(ObjectNode json, AsyncApi20ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.reader.readSecurityRequirement(this.json, (AsyncApi20SecurityRequirement) node);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.reader.readOneOfMessages(this.json, (AsyncApi20OneOfMessages) node);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.reader.readChannelItem(this.json, (AsyncApi20ChannelItem) node);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.reader.readMessageBindings(this.json, (AsyncApi20MessageBindings) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (AsyncApi20OAuthFlow) node);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.reader.readOperationBindings(this.json, (AsyncApi20OperationBindings) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (AsyncApi20Components) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (AsyncApi20Parameter) node);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.reader.readChannelBindings(this.json, (AsyncApi20ChannelBindings) node);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.reader.readParameters(this.json, (AsyncApi20Parameters) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (AsyncApi20Contact) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (AsyncApi20ServerVariable) node);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.reader.readMessage(this.json, (AsyncApi20Message) node);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.reader.readOperationTrait(this.json, (AsyncApi20OperationTrait) node);
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (AsyncApi20Server) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (AsyncApi20OAuthFlows) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (AsyncApi20Schema) node);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.reader.readServerBindings(this.json, (AsyncApi20ServerBindings) node);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.reader.readCorrelationID(this.json, (AsyncApi20CorrelationID) node);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.reader.readServers(this.json, (AsyncApi20Servers) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (AsyncApi20ExternalDocumentation) node);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.reader.readChannels(this.json, (AsyncApi20Channels) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (AsyncApi20Tag) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (AsyncApi20License) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (AsyncApi20Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (AsyncApi20Operation) node);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.reader.readMessageTrait(this.json, (AsyncApi20MessageTrait) node);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.reader.readBinding(this.json, (AsyncApi20Binding) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (AsyncApi20SecurityScheme) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (AsyncApi20Document) node);
	}
}