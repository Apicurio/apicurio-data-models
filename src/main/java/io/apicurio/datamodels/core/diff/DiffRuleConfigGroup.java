package io.apicurio.datamodels.core.diff;

import io.apicurio.datamodels.core.diff.DiffType;
import io.apicurio.datamodels.core.diff.DiffRuleConfig;

import java.util.Map;

public class DiffRuleConfigGroup {
    private final String groupName;
    private Boolean disabled = false;
    private Map<DiffType, DiffRuleConfig> rules;

    public DiffRuleConfigGroup(String groupName) {
        this.groupName = groupName;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Map<DiffType, DiffRuleConfig> getRules() {
        return rules;
    }

    public void setRules(Map<DiffType, DiffRuleConfig> rules) {
        this.rules = rules;
    }

    public DiffRuleConfig getRuleConfig(DiffType diffType) {
        return rules.get(diffType);
    }
}
