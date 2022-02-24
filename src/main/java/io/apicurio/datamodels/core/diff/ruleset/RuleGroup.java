package io.apicurio.datamodels.core.diff.ruleset;

import io.apicurio.datamodels.core.diff.DiffType;
import io.apicurio.datamodels.core.diff.change.Change;

import java.util.Map;

public class RuleGroup {
    private Boolean disabled;
    private Map<DiffType, Change> rules;

    RuleGroup() {
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Map<DiffType, Change> getRules() {
        return rules;
    }

    public void setRules(Map<DiffType, Change> rules) {
        this.rules = rules;
    }

    public Change getRuleConfig(DiffType diffType) {
        return rules.get(diffType);
    }
}
