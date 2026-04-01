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

public class AsyncApi23ModelReaderDispatcher implements AsyncApi23Visitor {

	private final ObjectNode json;
	private final AsyncApi23ModelReader reader;

	public AsyncApi23ModelReaderDispatcher(ObjectNode json, AsyncApi23ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.reader.readMessageExample(this.json, (AsyncApi23MessageExample) node);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.reader.readSecurityRequirement(this.json, (AsyncApi23SecurityRequirement) node);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.reader.readOneOfMessages(this.json, (AsyncApi23OneOfMessages) node);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.reader.readChannelItem(this.json, (AsyncApi23ChannelItem) node);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.reader.readMessageBindings(this.json, (AsyncApi23MessageBindings) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (AsyncApi23OAuthFlow) node);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.reader.readOperationBindings(this.json, (AsyncApi23OperationBindings) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (AsyncApi23Components) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (AsyncApi23Parameter) node);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.reader.readChannelBindings(this.json, (AsyncApi23ChannelBindings) node);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.reader.readParameters(this.json, (AsyncApi23Parameters) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (AsyncApi23Contact) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (AsyncApi23ServerVariable) node);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.reader.readMessage(this.json, (AsyncApi23Message) node);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.reader.readOperationTrait(this.json, (AsyncApi23OperationTrait) node);
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (AsyncApi23Server) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (AsyncApi23OAuthFlows) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (AsyncApi23Schema) node);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.reader.readServerBindings(this.json, (AsyncApi23ServerBindings) node);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.reader.readCorrelationID(this.json, (AsyncApi23CorrelationID) node);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.reader.readServers(this.json, (AsyncApi23Servers) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (AsyncApi23ExternalDocumentation) node);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.reader.readChannels(this.json, (AsyncApi23Channels) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (AsyncApi23Tag) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (AsyncApi23License) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (AsyncApi23Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (AsyncApi23Operation) node);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.reader.readMessageTrait(this.json, (AsyncApi23MessageTrait) node);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.reader.readBinding(this.json, (AsyncApi23Binding) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (AsyncApi23SecurityScheme) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (AsyncApi23Document) node);
	}
}