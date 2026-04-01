package io.apicurio.datamodels.models.asyncapi.v3x.v30.io;

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
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.asyncapi.AsyncApiBinding;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannel;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannels;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageExample;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReply;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperations;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameters;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReference;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServers;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Binding;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Channel;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Channels;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Components;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Contact;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Document;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Info;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30License;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Message;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30MessageExample;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30MultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Operation;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OperationReply;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Operations;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Parameter;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Parameters;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Reference;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Schema;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Server;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Servers;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.AsyncApi30Tag;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;

public class AsyncApi30ModelReaderDispatcher implements AsyncApi30Visitor {

	private final ObjectNode json;
	private final AsyncApi30ModelReader reader;

	public AsyncApi30ModelReaderDispatcher(ObjectNode json, AsyncApi30ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitChannel(AsyncApiChannel node) {
		this.reader.readChannel(this.json, (AsyncApi30Channel) node);
	}

	@Override
	public void visitReference(AsyncApiReference node) {
		this.reader.readReference(this.json, (AsyncApi30Reference) node);
	}

	@Override
	public void visitMultiFormatSchema(AsyncApiMultiFormatSchema node) {
		this.reader.readMultiFormatSchema(this.json, (AsyncApi30MultiFormatSchema) node);
	}

	@Override
	public void visitOperationReplyAddress(AsyncApiOperationReplyAddress node) {
		this.reader.readOperationReplyAddress(this.json, (AsyncApi30OperationReplyAddress) node);
	}

	@Override
	public void visitOperationReply(AsyncApiOperationReply node) {
		this.reader.readOperationReply(this.json, (AsyncApi30OperationReply) node);
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.reader.readMessageExample(this.json, (AsyncApi30MessageExample) node);
	}

	@Override
	public void visitOperations(AsyncApiOperations node) {
		this.reader.readOperations(this.json, (AsyncApi30Operations) node);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.reader.readMessageBindings(this.json, (AsyncApi30MessageBindings) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (AsyncApi30OAuthFlow) node);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.reader.readOperationBindings(this.json, (AsyncApi30OperationBindings) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (AsyncApi30Components) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (AsyncApi30Parameter) node);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.reader.readChannelBindings(this.json, (AsyncApi30ChannelBindings) node);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.reader.readParameters(this.json, (AsyncApi30Parameters) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (AsyncApi30Contact) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (AsyncApi30ServerVariable) node);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.reader.readMessage(this.json, (AsyncApi30Message) node);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.reader.readOperationTrait(this.json, (AsyncApi30OperationTrait) node);
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (AsyncApi30Server) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (AsyncApi30OAuthFlows) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (AsyncApi30Schema) node);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.reader.readServerBindings(this.json, (AsyncApi30ServerBindings) node);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.reader.readCorrelationID(this.json, (AsyncApi30CorrelationID) node);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.reader.readServers(this.json, (AsyncApi30Servers) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (AsyncApi30ExternalDocumentation) node);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.reader.readChannels(this.json, (AsyncApi30Channels) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (AsyncApi30Tag) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (AsyncApi30License) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (AsyncApi30Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (AsyncApi30Operation) node);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.reader.readMessageTrait(this.json, (AsyncApi30MessageTrait) node);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.reader.readBinding(this.json, (AsyncApi30Binding) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (AsyncApi30SecurityScheme) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (AsyncApi30Document) node);
	}
}