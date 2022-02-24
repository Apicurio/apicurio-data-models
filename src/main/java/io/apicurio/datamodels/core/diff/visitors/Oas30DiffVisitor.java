package io.apicurio.datamodels.core.diff.visitors;

import io.apicurio.datamodels.core.diff.DiffContext;
import io.apicurio.datamodels.core.diff.DiffType;
import io.apicurio.datamodels.core.diff.DiffUtil;
import io.apicurio.datamodels.core.diff.DiffRuleConfig;
import io.apicurio.datamodels.core.diff.ruleset.OasDiffRuleset;
import io.apicurio.datamodels.core.diff.DiffRuleConfigGroup;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.License;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.models.common.Tag;
import io.apicurio.datamodels.core.util.NodePathUtil;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;
import io.apicurio.datamodels.openapi.v3.models.Oas30Callback;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Encoding;
import io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Link;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;
import io.apicurio.datamodels.openapi.v3.models.Oas30PathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

import java.util.HashMap;
import java.util.Map;

public class Oas30DiffVisitor extends OasDiffVisitor implements IOas30Visitor {
    private final OasDiffRuleset ruleSet;
    private DiffContext ctx;
    private Oas30Document original;

    public Oas30DiffVisitor(DiffContext ctx, Node original) {
        super(ctx, original);
        this.ctx = ctx;
        this.original = (Oas30Document) original;
        this.ruleSet = (OasDiffRuleset) ctx.ruleSet;
    }

    @Override
    public void visitContact(Contact node) {

    }

    @Override
    public void visitDocument(Document node) {

    }

    @Override
    public void visitExtension(Extension node) {

    }

    @Override
    public void visitExternalDocumentation(ExternalDocumentation node) {

    }

    @Override
    public void visitInfo(Info node) {
    }

    @Override
    public void visitLicense(License node) {

    }

    @Override
    public void visitParameterDefinition(IDefinition node) {

    }

    @Override
    public void visitParameter(Parameter node) {

    }

    @Override
    public void visitSchemaDefinition(IDefinition node) {

    }

    @Override
    public void visitSchema(Schema node) {

    }

    @Override
    public void visitSecurityRequirement(SecurityRequirement node) {

    }

    @Override
    public void visitSecurityScheme(SecurityScheme node) {

    }

    @Override
    public void visitTag(Tag node) {

    }

    @Override
    public void visitValidationProblem(ValidationProblem problem) {

    }

    @Override
    public void visitResponse(OasResponse node) {

    }

    @Override
    public void visitResponses(OasResponses node) {

    }

    @Override
    public void visitOperation(Operation node) {
        DiffRuleConfigGroup rules = ruleSet.getOperationRules();
        DiffRuleConfig bodyAddedRuleConfig = rules.getRuleConfig(DiffType.REQUEST_BODY_MEDIA_TYPE_ADDED);

        Oas30Operation operation = (Oas30Operation)node;

        NodePath nodePath = NodePathUtil.createNodePath(node);

        Oas30PathItem pi = (Oas30PathItem) node.parent();
        Oas30PathItem originalPathItem = (Oas30PathItem) original.paths.getPathItem(pi.getPath());
        Oas30Operation oas3Operation = (Oas30Operation) node;
        if (oas3Operation.requestBody != null) {

            if (originalPathItem != null) {
                Oas30Operation originalOp = (Oas30Operation) originalPathItem.getOperation(operation.getMethod());
                if (originalOp != null) {
                    Oas30RequestBody originalRequestBody = originalOp.requestBody;

                    if (originalRequestBody == null) {
                        ctx.addDifferenceIfEnabled(bodyAddedRuleConfig, null, nodePath);
                    }
                }
            }
        }

        super.visitOperation(node);
    }

    @Override
    public void visitXML(OasXML node) {

    }

    @Override
    public void visitAllOfSchema(OasSchema node) {

    }

    @Override
    public void visitItemsSchema(OasSchema node) {

    }

    @Override
    public void visitAdditionalPropertiesSchema(OasSchema node) {

    }

    @Override
    public void visitPropertySchema(IPropertySchema node) {

    }

    @Override
    public void visitHeader(OasHeader node) {

    }

    @Override
    public void visitResponseDefinition(IDefinition node) {

    }

    @Override
    public void visitExample(IExample node) {

    }

    @Override
    public void visitComponents(Components node) {

    }

    @Override
    public void visitCallbackPathItem(Oas30CallbackPathItem node) {

    }

    @Override
    public void visitCallback(Oas30Callback node) {

    }

    @Override
    public void visitLinkServer(Oas30LinkServer node) {

    }

    @Override
    public void visitCallbackDefinition(Oas30CallbackDefinition node) {

    }

    @Override
    public void visitLink(Oas30Link node) {

    }

    @Override
    public void visitLinkRequestBodyExpression(Oas30LinkRequestBodyExpression node) {

    }

    @Override
    public void visitLinkParameterExpression(Oas30LinkParameterExpression node) {

    }

    @Override
    public void visitLinkDefinition(Oas30LinkDefinition node) {

    }

    @Override
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node) {

    }

    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {

    }

    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {

    }

    @Override
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node) {

    }

    @Override
    public void visitOAuthFlows(OAuthFlows node) {

    }

    @Override
    public void visitEncoding(Oas30Encoding node) {

    }

    @Override
    public void visitMediaType(Oas30MediaType node) {

    }

    @Override
    public void visitHeaderDefinition(Oas30HeaderDefinition node) {
    }

    @Override
    public void visitRequestBody(Oas30RequestBody node) {
        DiffRuleConfigGroup requestBodyRules = ruleSet.getRequestBodyRules();

        Oas30Operation op = (Oas30Operation) node.parent();
        Oas30PathItem pi = (Oas30PathItem) op.parent();

        Oas30Operation originalOp = (Oas30Operation) original.paths.getPathItem(pi.getPath()).getOperation(op.getMethod());
        Oas30RequestBody originalRequestBody = originalOp.requestBody;
        if (originalRequestBody == null) {
            return;
        }

        NodePath nodePath = NodePathUtil.createNodePath(node);

        if ((originalRequestBody.required == null || !originalRequestBody.required) && node.required != null && node.required) {
            DiffRuleConfig ruleConfig = requestBodyRules.getRuleConfig(DiffType.REQUEST_BODY_REQUIRED_TRUE);
            ctx.addDifferenceIfEnabled(ruleConfig, null, nodePath);
        } else if (originalRequestBody.required != null && originalRequestBody.required && (node.required == null || !node.required)) {
            DiffRuleConfig ruleConfig = requestBodyRules.getRuleConfig(DiffType.REQUEST_BODY_REQUIRED_TRUE);
            ctx.addDifferenceIfEnabled(ruleConfig, null, nodePath);
        }

        DiffRuleConfig mediaTypeAddedRuleConfig = requestBodyRules.getRuleConfig(DiffType.REQUEST_BODY_MEDIA_TYPE_ADDED);
        Map<String, Oas30MediaType> content = node.content;
        Map<String, Oas30MediaType> originalContent = originalRequestBody.content;
        nodePath.appendSegment("content");
        for (Oas30MediaType mediaType : content.values()) {
            String name = mediaType.getName();
            Oas30MediaType originalMediaType = originalContent.get(name);
            if (DiffUtil.isNodeAdded(originalMediaType, mediaType)) {
                Map<String, String> templateVars = new HashMap<String, String>() {{
                    put("MediaType", name);
                }};
                ctx.addDifferenceIfEnabled(mediaTypeAddedRuleConfig, templateVars, nodePath);
            }
        }

        DiffRuleConfig mediaTypeRemovedRuleConfig = requestBodyRules.getRuleConfig(DiffType.REQUEST_BODY_MEDIA_TYPE_REMOVED);
        for (Oas30MediaType originalMediaType : originalContent.values()) {
            String name = originalMediaType.getName();
            Oas30MediaType mediaType = content.get(name);
            if (DiffUtil.isNodeRemoved(originalMediaType, mediaType)) {
                Map<String, String> templateVars = new HashMap<String, String>() {{
                    put("MediaType", name);
                }};
                ctx.addDifferenceIfEnabled(mediaTypeRemovedRuleConfig, templateVars, nodePath);
            }
        }
    }

    @Override
    public void visitRequestBodyDefinition(Oas30RequestBodyDefinition node) {
    }

    @Override
    public void visitExampleDefinition(Oas30ExampleDefinition node) {

    }

    @Override
    public void visitDiscriminator(Oas30Discriminator node) {

    }

    @Override
    public void visitNotSchema(Oas30Schema.Oas30NotSchema node) {

    }

    @Override
    public void visitOneOfSchema(Oas30Schema.Oas30OneOfSchema node) {

    }

    @Override
    public void visitAnyOfSchema(Oas30Schema.Oas30AnyOfSchema node) {

    }

    @Override
    public void visitServer(Server node) {

    }

    @Override
    public void visitServerVariable(ServerVariable node) {

    }
}
