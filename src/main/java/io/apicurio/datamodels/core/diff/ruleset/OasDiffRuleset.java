package io.apicurio.datamodels.core.diff.ruleset;

import io.apicurio.datamodels.core.diff.DiffRuleConfigGroup;

public class OasDiffRuleset extends Ruleset {

    private DiffRuleConfigGroup paths;
    private DiffRuleConfigGroup pathItem;
    private DiffRuleConfigGroup operation;
    private DiffRuleConfigGroup requestBody;
    private DiffRuleConfigGroup mediaType;

    public OasDiffRuleset(Object rules) {
        super(rules);
        this.setPathsRules();
        this.setPathItemRules();
        this.setOperationRules();
        this.setMediaTypeRules();
        this.setRequestBodyRules();
    }

    private void setPathsRules() {
        this.paths = loadRules("paths");
    }

    private void setPathItemRules() {
        this.pathItem = loadRules("pathItem");
    }

    private void setOperationRules() {
        this.operation = loadRules("operation");
    }

    private void setRequestBodyRules() {
        this.requestBody = loadRules("requestBody");
    }

    private void setMediaTypeRules() {
        this.mediaType = loadRules("mediaType");
    }

    public DiffRuleConfigGroup getPathsRules() {
        return paths;
    }

    public DiffRuleConfigGroup getPathItemRules() {
        return pathItem;
    }

    public DiffRuleConfigGroup getOperationRules() {
        return operation;
    }

    public DiffRuleConfigGroup getRequestBodyRules() {
        return requestBody;
    }

    public DiffRuleConfigGroup getMediaTypeRules() {
        return mediaType;
    }
}
