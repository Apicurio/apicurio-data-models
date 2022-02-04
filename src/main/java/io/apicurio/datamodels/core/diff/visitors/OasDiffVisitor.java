package io.apicurio.datamodels.core.diff.visitors;

import io.apicurio.datamodels.core.diff.DiffContext;
import io.apicurio.datamodels.core.diff.DiffType;
import io.apicurio.datamodels.core.diff.DiffUtil;
import io.apicurio.datamodels.core.diff.change.Change;
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
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class OasDiffVisitor implements IOasVisitor {
    private final DiffContext ctx;
    private final OasDocument original;
    private final OasDiffRuleset ruleSet;

    public OasDiffVisitor(DiffContext ctx, Node original) {
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
        OasPathItem parent = (OasPathItem)node.parent();
        OasPathItem originalPathItem = original.paths.getPathItem(parent.getPath());
        if (originalPathItem == null) {
            return;
        }
        OasOperation originalOperation = originalPathItem.getOperation(node.getType());
        if (originalOperation == null) {
            return;
        }

        NodePath nodePath = NodePathUtil.createNodePath(node);

        Map<DiffType, Change> rules = ruleSet.getOperationRules();

        boolean originalIdIsNull = Objects.equals(originalOperation.operationId, null);
        boolean newIdIsNull = Objects.equals(node.operationId, null);
        if (originalIdIsNull && !newIdIsNull) {
            Change operationIdAddedRuleConfig = rules.get(DiffType.OPERATION_ID_ADDED);
            ctx.addDifference(DiffType.OPERATION_ID_ADDED, operationIdAddedRuleConfig, nodePath);
        } else if (!originalIdIsNull && newIdIsNull) {
            Change operationIdRemovedRuleConfig = rules.get(DiffType.OPERATION_ID_REMOVED);
            ctx.addDifference(DiffType.OPERATION_ID_REMOVED, operationIdRemovedRuleConfig, nodePath);
        } else if (!originalIdIsNull && !Objects.equals(originalOperation.operationId, node.operationId)) {
            Change operationIdChangedRuleConfig = rules.get(DiffType.OPERATION_ID_MODIFIED);
            ctx.addDifference(DiffType.OPERATION_ID_MODIFIED, operationIdChangedRuleConfig, nodePath);
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
        Map<DiffType, Change> rules = ruleSet.getPathsRules();
        NodePath nodePath = NodePathUtil.createNodePath(node);

        Change pathAddedRuleConfig = rules.get(DiffType.PATH_ADDED);
        Change pathRemovedRuleConfig = rules.get(DiffType.PATH_REMOVED);

        OasPaths originalPaths = original.paths;

        for (OasPathItem pathItem : node.getPathItems()) {
            if (DiffUtil.isNodeAdded(originalPaths.getPathItem(pathItem.getPath()), node)) {
               ctx.addDifference(DiffType.PATH_ADDED, pathAddedRuleConfig, Map.of("Path", pathItem.getPath()), nodePath);
            }
        }
        for (OasPathItem pathItem : originalPaths.getPathItems()) {
            if (DiffUtil.isNodeRemoved(pathItem, node.getPathItem(pathItem.getPath()))) {
                ctx.addDifference(DiffType.PATH_REMOVED, pathRemovedRuleConfig, Map.of("Path", pathItem.getPath()), nodePath);
            }
        }
    }

    @Override
    public void visitPathItem(OasPathItem node) {
        Map<DiffType, Change> rules = ruleSet.getPathItemRules();
        NodePath nodePath = NodePathUtil.createNodePath(node);

        OasPathItem originalPathItem = original.paths.getPathItem(node.getPath());

        if (originalPathItem == null) {
            return;
        }

        List<String> methods = node.getMethods();
        Change operationAddedRuleConfig = rules.get(DiffType.PATH_OPERATION_ADDED);

        for (String method : methods) {
            OasOperation operation = node.getOperation(method);
            if (DiffUtil.isNodeAdded(originalPathItem.getOperation(method), operation)) {
                ctx.addDifference(DiffType.PATH_OPERATION_ADDED, operationAddedRuleConfig, nodePath);
            }
        }

        List<String> originalMethods = originalPathItem.getMethods();
        Change operationRemovedRuleConfig = rules.get(DiffType.PATH_OPERATION_REMOVED);

        for (String method : originalMethods) {
            OasOperation operation = originalPathItem.getOperation(method);
            if (DiffUtil.isNodeRemoved(operation, node.getOperation(method))) {
                ctx.addDifference(DiffType.PATH_OPERATION_REMOVED, operationRemovedRuleConfig, nodePath);
            }
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
