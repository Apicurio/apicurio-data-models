package io.apicurio.datamodels.models.visitors;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.Contact;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Example;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.License;
import io.apicurio.datamodels.models.Link;
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
import io.apicurio.datamodels.models.asyncapi.v2x.v20.visitors.AsyncApi20Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.visitors.AsyncApi21Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.visitors.AsyncApi22Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.visitors.AsyncApi23Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.visitors.AsyncApi24Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.visitors.AsyncApi25Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.visitors.AsyncApi26Visitor;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.visitors.JSDraft4Visitor;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.visitors.JSDraft6Visitor;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.visitors.JSDraft7Visitor;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.visitors.JS201909Visitor;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.visitors.JS202012Visitor;
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
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v31.visitors.OpenApi31Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;
import io.apicurio.datamodels.models.openrpc.OpenRpcContentDescriptor;
import io.apicurio.datamodels.models.openrpc.OpenRpcError;
import io.apicurio.datamodels.models.openrpc.OpenRpcExamplePairing;
import io.apicurio.datamodels.models.openrpc.OpenRpcMethod;
import io.apicurio.datamodels.models.openrpc.v1x.v13.visitors.OpenRpc13Visitor;
import io.apicurio.datamodels.models.openrpc.v1x.v14.visitors.OpenRpc14Visitor;

public class CombinedVisitorAdapter
		implements
			OpenRpc14Visitor,
			JS202012Visitor,
			JSDraft7Visitor,
			JSDraft6Visitor,
			JSDraft4Visitor,
			OpenRpc13Visitor,
			OpenApi20Visitor,
			AsyncApi26Visitor,
			AsyncApi25Visitor,
			AsyncApi31Visitor,
			AsyncApi20Visitor,
			JS201909Visitor,
			AsyncApi24Visitor,
			AsyncApi30Visitor,
			OpenApi30Visitor,
			AsyncApi23Visitor,
			AsyncApi22Visitor,
			OpenApi32Visitor,
			AsyncApi21Visitor,
			OpenApi31Visitor {

	@Override
	public void visitContact(Contact node) {
	}

	@Override
	public void visitExamplePairing(OpenRpcExamplePairing node) {
	}

	@Override
	public void visitLicense(License node) {
	}

	@Override
	public void visitLink(Link node) {
	}

	@Override
	public void visitTag(Tag node) {
	}

	@Override
	public void visitMethod(OpenRpcMethod node) {
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
	}

	@Override
	public void visitComponents(Components node) {
	}

	@Override
	public void visitInfo(Info node) {
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
	}

	@Override
	public void visitError(OpenRpcError node) {
	}

	@Override
	public void visitSchema(Schema node) {
	}

	@Override
	public void visitServer(Server node) {
	}

	@Override
	public void visitExample(Example node) {
	}

	@Override
	public void visitContentDescriptor(OpenRpcContentDescriptor node) {
	}

	@Override
	public void visitDocument(Document node) {
	}

	@Override
	public void visitJSchema(JsonSchemaJSchema node) {
	}

	@Override
	public void visitParameterDefinitions(OpenApiParameterDefinitions node) {
	}

	@Override
	public void visitItems(OpenApiItems node) {
	}

	@Override
	public void visitDefinitions(OpenApiDefinitions node) {
	}

	@Override
	public void visitResponseDefinitions(OpenApiResponseDefinitions node) {
	}

	@Override
	public void visitScopes(OpenApiScopes node) {
	}

	@Override
	public void visitSecurityDefinitions(OpenApiSecurityDefinitions node) {
	}

	@Override
	public void visitPaths(OpenApiPaths node) {
	}

	@Override
	public void visitHeader(OpenApiHeader node) {
	}

	@Override
	public void visitXML(OpenApiXML node) {
	}

	@Override
	public void visitParameter(Parameter node) {
	}

	@Override
	public void visitSecurityScheme(SecurityScheme node) {
	}

	@Override
	public void visitPathItem(OpenApiPathItem node) {
	}

	@Override
	public void visitSecurityRequirement(SecurityRequirement node) {
	}

	@Override
	public void visitResponse(OpenApiResponse node) {
	}

	@Override
	public void visitOperation(Operation node) {
	}

	@Override
	public void visitResponses(OpenApiResponses node) {
	}

	@Override
	public void visitMessageExample(AsyncApiMessageExample node) {
	}

	@Override
	public void visitOneOfMessages(AsyncApiOneOfMessages node) {
	}

	@Override
	public void visitChannelItem(AsyncApiChannelItem node) {
	}

	@Override
	public void visitMessageBindings(AsyncApiMessageBindings node) {
	}

	@Override
	public void visitOAuthFlow(OAuthFlow node) {
	}

	@Override
	public void visitOperationBindings(AsyncApiOperationBindings node) {
	}

	@Override
	public void visitChannelBindings(AsyncApiChannelBindings node) {
	}

	@Override
	public void visitParameters(AsyncApiParameters node) {
	}

	@Override
	public void visitMessage(AsyncApiMessage node) {
	}

	@Override
	public void visitOperationTrait(AsyncApiOperationTrait node) {
	}

	@Override
	public void visitOAuthFlows(OAuthFlows node) {
	}

	@Override
	public void visitServerBindings(AsyncApiServerBindings node) {
	}

	@Override
	public void visitCorrelationID(AsyncApiCorrelationID node) {
	}

	@Override
	public void visitServers(AsyncApiServers node) {
	}

	@Override
	public void visitChannels(AsyncApiChannels node) {
	}

	@Override
	public void visitMessageTrait(AsyncApiMessageTrait node) {
	}

	@Override
	public void visitBinding(AsyncApiBinding node) {
	}

	@Override
	public void visitChannel(AsyncApiChannel node) {
	}

	@Override
	public void visitReference(AsyncApiReference node) {
	}

	@Override
	public void visitMultiFormatSchema(AsyncApiMultiFormatSchema node) {
	}

	@Override
	public void visitOperationReplyAddress(AsyncApiOperationReplyAddress node) {
	}

	@Override
	public void visitOperationReply(AsyncApiOperationReply node) {
	}

	@Override
	public void visitOperations(AsyncApiOperations node) {
	}

	@Override
	public void visitRequestBody(OpenApiRequestBody node) {
	}

	@Override
	public void visitMediaType(OpenApiMediaType node) {
	}

	@Override
	public void visitEncoding(OpenApiEncoding node) {
	}

	@Override
	public void visitCallback(OpenApiCallback node) {
	}

	@Override
	public void visitDiscriminator(OpenApiDiscriminator node) {
	}
}