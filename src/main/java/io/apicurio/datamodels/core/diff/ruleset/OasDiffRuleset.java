package io.apicurio.datamodels.core.diff.ruleset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.diff.change.BreakingChange;
import io.apicurio.datamodels.core.diff.change.Change;
import io.apicurio.datamodels.core.diff.change.ChangeType;
import io.apicurio.datamodels.core.diff.change.DangerousChange;
import io.apicurio.datamodels.core.diff.DiffType;
import io.apicurio.datamodels.core.diff.change.NonBreakingChange;

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
