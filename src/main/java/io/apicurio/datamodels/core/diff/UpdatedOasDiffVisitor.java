package io.apicurio.datamodels.core.diff;

import io.apicurio.datamodels.core.diff.ruleset.OasDiffRuleset;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.License;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Tag;
import io.apicurio.datamodels.core.util.NodePathUtil;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

import java.util.Map;
import java.util.Objects;

public abstract class UpdatedOasDiffVisitor implements IOasVisitor {
    private DiffContext ctx;
    private OasDocument original;
    private OasDiffRuleset ruleSet;

    public UpdatedOasDiffVisitor(DiffContext ctx, Node original) {
        this.ctx = ctx;
        this.original = (OasDocument) original;
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
    public void visitOperation(Operation node) {
        Map<DiffType, DiffRule> rules = ruleSet.getOperationRules();
        OasPathItem pi = (OasPathItem) node.parent();

        OasPathItem originalPi = original.paths.getPathItem(pi.getPath());
        if (originalPi == null) {
            return;
        }

        NodePath nodePath = NodePathUtil.createNodePath(node);

        DiffRule operationAddedRuleConfig = rules.get(DiffType.PATH_OPERATION_ADDED);

        Operation originalOperation = originalPi.getOperation(node.getType());
        if (originalOperation == null) {
            this.ctx.addDifference(DiffType.PATH_OPERATION_ADDED, operationAddedRuleConfig.getChangeSeverity(), "Operation added", nodePath);
            return;
        }

        boolean originalIdIsNull = Objects.equals(originalOperation.operationId, null);
        boolean newIdIsNull = Objects.equals(node.operationId, null);
        if (originalIdIsNull && !newIdIsNull) {
            DiffRule operationIdAddedRuleConfig = rules.get(DiffType.OPERATION_ID_ADDED);
            ctx.addDifference(DiffType.OPERATION_ID_ADDED, operationIdAddedRuleConfig.getChangeSeverity(), operationIdAddedRuleConfig.getMessage(), nodePath);
        } else if (!originalIdIsNull && newIdIsNull) {
            DiffRule operationIdRemovedRuleConfig = rules.get(DiffType.OPERATION_ID_REMOVED);
            ctx.addDifference(DiffType.OPERATION_ID_REMOVED, operationIdRemovedRuleConfig.getChangeSeverity(), operationIdRemovedRuleConfig.getMessage(), nodePath);
        } else if (!originalIdIsNull && !Objects.equals(originalOperation.operationId, node.operationId)) {
            DiffRule operationIdChangedRuleConfig = rules.get(DiffType.OPERATION_ID_MODIFIED);
            ctx.addDifference(DiffType.OPERATION_ID_MODIFIED, operationIdChangedRuleConfig.getChangeSeverity(), operationIdChangedRuleConfig.getMessage(), nodePath);
        }
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
    public void visitPaths(OasPaths node) {

    }

    @Override
    public void visitPathItem(OasPathItem node) {
        Map<DiffType, DiffRule> rules = ruleSet.getPathsRules();

        OasPaths originalPaths = this.original.paths;

        NodePath nodePath = NodePathUtil.createNodePath(node);
        DiffRule pathAddedRuleConfig = rules.get(DiffType.PATH_ADDED);
        String path = node.getPath();
        if (originalPaths.getPathItem(path) == null) {
            ctx.addDifference(DiffType.PATH_ADDED, pathAddedRuleConfig.getChangeSeverity(), pathAddedRuleConfig.getMessage(), nodePath);
        }
    }

    @Override
    public void visitResponse(OasResponse node) {
    }

    @Override
    public void visitResponses(OasResponses node) {

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
}
