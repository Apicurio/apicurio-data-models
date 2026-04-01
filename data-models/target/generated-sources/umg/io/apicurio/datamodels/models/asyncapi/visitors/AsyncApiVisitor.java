package io.apicurio.datamodels.models.asyncapi.visitors;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.Contact;
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
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannels;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameters;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServers;
import io.apicurio.datamodels.models.visitors.Visitor;

public interface AsyncApiVisitor extends Visitor {

	public void visitMessageBindings(AsyncApiMessageBindings node);

	public void visitOAuthFlow(OAuthFlow node);

	public void visitOperationBindings(AsyncApiOperationBindings node);

	public void visitComponents(Components node);

	public void visitParameter(Parameter node);

	public void visitChannelBindings(AsyncApiChannelBindings node);

	public void visitParameters(AsyncApiParameters node);

	public void visitContact(Contact node);

	public void visitServerVariable(ServerVariable node);

	public void visitMessage(AsyncApiMessage node);

	public void visitOperationTrait(AsyncApiOperationTrait node);

	public void visitServer(Server node);

	public void visitOAuthFlows(OAuthFlows node);

	public void visitSchema(Schema node);

	public void visitServerBindings(AsyncApiServerBindings node);

	public void visitCorrelationID(AsyncApiCorrelationID node);

	public void visitServers(AsyncApiServers node);

	public void visitExternalDocumentation(ExternalDocumentation node);

	public void visitChannels(AsyncApiChannels node);

	public void visitTag(Tag node);

	public void visitLicense(License node);

	public void visitInfo(Info node);

	public void visitOperation(Operation node);

	public void visitMessageTrait(AsyncApiMessageTrait node);

	public void visitBinding(AsyncApiBinding node);

	public void visitSecurityScheme(SecurityScheme node);
}