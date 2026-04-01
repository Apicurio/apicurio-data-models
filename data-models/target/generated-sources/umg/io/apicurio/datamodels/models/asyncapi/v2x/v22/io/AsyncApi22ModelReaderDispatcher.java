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

public class AsyncApi22ModelReaderDispatcher implements AsyncApi22Visitor {

	private final ObjectNode json;
	private final AsyncApi22ModelReader reader;

	public AsyncApi22ModelReaderDispatcher(ObjectNode json, AsyncApi22ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.reader.readMessageExample(this.json, (AsyncApi22MessageExample) node);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.reader.readSecurityRequirement(this.json, (AsyncApi22SecurityRequirement) node);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.reader.readOneOfMessages(this.json, (AsyncApi22OneOfMessages) node);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.reader.readChannelItem(this.json, (AsyncApi22ChannelItem) node);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.reader.readMessageBindings(this.json, (AsyncApi22MessageBindings) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (AsyncApi22OAuthFlow) node);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.reader.readOperationBindings(this.json, (AsyncApi22OperationBindings) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (AsyncApi22Components) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (AsyncApi22Parameter) node);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.reader.readChannelBindings(this.json, (AsyncApi22ChannelBindings) node);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.reader.readParameters(this.json, (AsyncApi22Parameters) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (AsyncApi22Contact) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (AsyncApi22ServerVariable) node);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.reader.readMessage(this.json, (AsyncApi22Message) node);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.reader.readOperationTrait(this.json, (AsyncApi22OperationTrait) node);
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (AsyncApi22Server) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (AsyncApi22OAuthFlows) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (AsyncApi22Schema) node);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.reader.readServerBindings(this.json, (AsyncApi22ServerBindings) node);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.reader.readCorrelationID(this.json, (AsyncApi22CorrelationID) node);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.reader.readServers(this.json, (AsyncApi22Servers) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (AsyncApi22ExternalDocumentation) node);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.reader.readChannels(this.json, (AsyncApi22Channels) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (AsyncApi22Tag) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (AsyncApi22License) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (AsyncApi22Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (AsyncApi22Operation) node);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.reader.readMessageTrait(this.json, (AsyncApi22MessageTrait) node);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.reader.readBinding(this.json, (AsyncApi22Binding) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (AsyncApi22SecurityScheme) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (AsyncApi22Document) node);
	}
}