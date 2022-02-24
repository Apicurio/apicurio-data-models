package io.apicurio.datamodels.core.diff.ruleset;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.diff.DiffRuleConfigGroup;

public class OasDiffRuleset extends Ruleset {

    private final DiffRuleConfigGroup paths;
    private final DiffRuleConfigGroup pathItem;
    private final DiffRuleConfigGroup operation;
    private final DiffRuleConfigGroup requestBody;
    private final DiffRuleConfigGroup mediaType;

    public OasDiffRuleset(Object rules) {
        super(rules);
        this.paths = loadRules(Constants.PROP_PATHS);
        this.pathItem = loadRules(Constants.PROP_PATH_ITEM);
        this.operation = loadRules(Constants.PROP_OPERATION);
        this.requestBody = loadRules(Constants.PROP_REQUEST_BODY);
        this.mediaType = loadRules(Constants.PROP_MEDIA_TYPE);
    }

    public DiffRuleConfigGroup getPathsRuleConfig() {
        return paths;
    }

    public DiffRuleConfigGroup getPathItemRuleConfig() {
        return pathItem;
    }

    public DiffRuleConfigGroup getOperationRuleConfig() {
        return operation;
    }

    public DiffRuleConfigGroup getRequestBodyRuleConfig() {
        return requestBody;
    }

    public DiffRuleConfigGroup getMediaTypeRuleConfig() {
        return mediaType;
    }
}
