package io.apicurio.datamodels.models.asyncapi.v3x.v31.io;

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
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Binding;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Channel;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Channels;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Components;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Contact;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Document;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Info;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31License;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Message;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageExample;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Operation;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationReply;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Operations;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Parameter;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Parameters;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Reference;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Schema;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Server;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Servers;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Tag;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;

public class AsyncApi31ModelReaderDispatcher implements AsyncApi31Visitor {

	private final ObjectNode json;
	private final AsyncApi31ModelReader reader;

	public AsyncApi31ModelReaderDispatcher(ObjectNode json, AsyncApi31ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitChannel(AsyncApiChannel node) {
		this.reader.readChannel(this.json, (AsyncApi31Channel) node);
	}

	@Override
	public void visitReference(AsyncApiReference node) {
		this.reader.readReference(this.json, (AsyncApi31Reference) node);
	}

	@Override
	public void visitMultiFormatSchema(AsyncApiMultiFormatSchema node) {
		this.reader.readMultiFormatSchema(this.json, (AsyncApi31MultiFormatSchema) node);
	}

	@Override
	public void visitOperationReplyAddress(AsyncApiOperationReplyAddress node) {
		this.reader.readOperationReplyAddress(this.json, (AsyncApi31OperationReplyAddress) node);
	}

	@Override
	public void visitOperationReply(AsyncApiOperationReply node) {
		this.reader.readOperationReply(this.json, (AsyncApi31OperationReply) node);
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.reader.readMessageExample(this.json, (AsyncApi31MessageExample) node);
	}

	@Override
	public void visitOperations(AsyncApiOperations node) {
		this.reader.readOperations(this.json, (AsyncApi31Operations) node);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.reader.readMessageBindings(this.json, (AsyncApi31MessageBindings) node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.reader.readOAuthFlow(this.json, (AsyncApi31OAuthFlow) node);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.reader.readOperationBindings(this.json, (AsyncApi31OperationBindings) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (AsyncApi31Components) node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.reader.readParameter(this.json, (AsyncApi31Parameter) node);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.reader.readChannelBindings(this.json, (AsyncApi31ChannelBindings) node);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.reader.readParameters(this.json, (AsyncApi31Parameters) node);
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (AsyncApi31Contact) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (AsyncApi31ServerVariable) node);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.reader.readMessage(this.json, (AsyncApi31Message) node);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.reader.readOperationTrait(this.json, (AsyncApi31OperationTrait) node);
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (AsyncApi31Server) node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.reader.readOAuthFlows(this.json, (AsyncApi31OAuthFlows) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (AsyncApi31Schema) node);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.reader.readServerBindings(this.json, (AsyncApi31ServerBindings) node);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.reader.readCorrelationID(this.json, (AsyncApi31CorrelationID) node);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.reader.readServers(this.json, (AsyncApi31Servers) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (AsyncApi31ExternalDocumentation) node);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.reader.readChannels(this.json, (AsyncApi31Channels) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (AsyncApi31Tag) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (AsyncApi31License) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (AsyncApi31Info) node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.reader.readOperation(this.json, (AsyncApi31Operation) node);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.reader.readMessageTrait(this.json, (AsyncApi31MessageTrait) node);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.reader.readBinding(this.json, (AsyncApi31Binding) node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.reader.readSecurityScheme(this.json, (AsyncApi31SecurityScheme) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (AsyncApi31Document) node);
	}
}