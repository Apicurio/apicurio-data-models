package io.apicurio.datamodels.core.diff.ruleset;

public class OasDiffRuleset extends Ruleset {

    private RuleGroup paths;
    private RuleGroup pathItem;
    private RuleGroup operation;
    private RuleGroup requestBody;
    private RuleGroup mediaType;

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

    public RuleGroup getPathsRules() {
        return paths;
    }

    public RuleGroup getPathItemRules() {
        return pathItem;
    }

    public RuleGroup getOperationRules() {
        return operation;
    }

    public RuleGroup getRequestBodyRules() {
        return requestBody;
    }

    public RuleGroup getMediaTypeRules() {
        return mediaType;
    }
}
