package io.apicurio.datamodels.models.visitors;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.Contact;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Example;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.License;
import io.apicurio.datamodels.models.Link;
import io.apicurio.datamodels.models.Node;
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
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannel;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelItem;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannels;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageExample;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOneOfMessages;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReply;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperations;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameters;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReference;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServers;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.openapi.OpenApiCallback;
import io.apicurio.datamodels.models.openapi.OpenApiDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiDiscriminator;
import io.apicurio.datamodels.models.openapi.OpenApiEncoding;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiItems;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.OpenApiParameterDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiPaths;
import io.apicurio.datamodels.models.openapi.OpenApiRequestBody;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.OpenApiResponseDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiResponses;
import io.apicurio.datamodels.models.openapi.OpenApiScopes;
import io.apicurio.datamodels.models.openapi.OpenApiSecurityDefinitions;
import io.apicurio.datamodels.models.openapi.OpenApiXML;
import io.apicurio.datamodels.models.openrpc.OpenRpcContentDescriptor;
import io.apicurio.datamodels.models.openrpc.OpenRpcError;
import io.apicurio.datamodels.models.openrpc.OpenRpcExamplePairing;
import io.apicurio.datamodels.models.openrpc.OpenRpcMethod;

public abstract class AllNodeVisitor implements CombinedVisitor {

	protected abstract void visitNode(Node node);

	@Override
	public void visitContact(Contact node) {
		this.visitNode(node);
	}

	@Override
	public void visitExamplePairing(OpenRpcExamplePairing node) {
		this.visitNode(node);
	}

	@Override
	public void visitLicense(License node) {
		this.visitNode(node);
	}

	@Override
	public void visitLink(Link node) {
		this.visitNode(node);
	}

	@Override
	public void visitTag(Tag node) {
		this.visitNode(node);
	}

	@Override
	public void visitMethod(OpenRpcMethod node) {
		this.visitNode(node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.visitNode(node);
	}

	@Override
	public void visitComponents(Components node) {
		this.visitNode(node);
	}

	@Override
	public void visitInfo(Info node) {
		this.visitNode(node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.visitNode(node);
	}

	@Override
	public void visitError(OpenRpcError node) {
		this.visitNode(node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.visitNode(node);
	}

	@Override
	public void visitServer(Server node) {
		this.visitNode(node);
	}

	@Override
	public void visitExample(Example node) {
		this.visitNode(node);
	}

	@Override
	public void visitContentDescriptor(OpenRpcContentDescriptor node) {
		this.visitNode(node);
	}

	@Override
	public void visitDocument(Document node) {
		this.visitNode(node);
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
		this.visitNode(node);
	}

	@Override
	public void visitParameterDefinitions(OpenApiParameterDefinitions node) {
		this.visitNode(node);
	}

	@Override
	public void visitItems(OpenApiItems node) {
		this.visitNode(node);
	}

	@Override
	public void visitDefinitions(OpenApiDefinitions node) {
		this.visitNode(node);
	}

	@Override
	public void visitResponseDefinitions(OpenApiResponseDefinitions node) {
		this.visitNode(node);
	}

	@Override
	public void visitScopes(OpenApiScopes node) {
		this.visitNode(node);
	}

	@Override
	public void visitSecurityDefinitions(OpenApiSecurityDefinitions node) {
		this.visitNode(node);
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
		this.visitNode(node);
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
		this.visitNode(node);
	}

	@Override
	public void visitXML(OpenApiXML node) {
		this.visitNode(node);
	}

	@Override
	public void visitParameter(Parameter node) {
		this.visitNode(node);
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
		this.visitNode(node);
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
		this.visitNode(node);
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
		this.visitNode(node);
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
		this.visitNode(node);
	}

	@Override
	public void visitOperation(Operation node) {
		this.visitNode(node);
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
		this.visitNode(node);
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
		this.visitNode(node);
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
		this.visitNode(node);
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
		this.visitNode(node);
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
		this.visitNode(node);
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
		this.visitNode(node);
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
		this.visitNode(node);
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
		this.visitNode(node);
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
		this.visitNode(node);
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
		this.visitNode(node);
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
		this.visitNode(node);
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
		this.visitNode(node);
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
		this.visitNode(node);
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
		this.visitNode(node);
	}

	@Override
	public void visitServers(AsyncApiServers node) {
		this.visitNode(node);
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
		this.visitNode(node);
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
		this.visitNode(node);
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
		this.visitNode(node);
	}

	@Override
	public void visitChannel(AsyncApiChannel node) {
		this.visitNode(node);
	}

	@Override
	public void visitReference(AsyncApiReference node) {
		this.visitNode(node);
	}

	@Override
	public void visitMultiFormatSchema(AsyncApiMultiFormatSchema node) {
		this.visitNode(node);
	}

	@Override
	public void visitOperationReplyAddress(AsyncApiOperationReplyAddress node) {
		this.visitNode(node);
	}

	@Override
	public void visitOperationReply(AsyncApiOperationReply node) {
		this.visitNode(node);
	}

	@Override
	public void visitOperations(AsyncApiOperations node) {
		this.visitNode(node);
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
		this.visitNode(node);
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
		this.visitNode(node);
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
		this.visitNode(node);
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
		this.visitNode(node);
	}

	@Override
	public void visitDiscriminator(OpenApiDiscriminator node) {
		this.visitNode(node);
	}
}