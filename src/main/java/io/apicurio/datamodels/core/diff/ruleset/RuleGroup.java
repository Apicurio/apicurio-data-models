package io.apicurio.datamodels.core.diff.ruleset;

import io.apicurio.datamodels.core.diff.DiffType;
import io.apicurio.datamodels.core.diff.change.Change;

import java.util.Map;

public class RuleGroup {
    private final String groupName;
    private Boolean disabled = false;
    private Map<DiffType, Change> rules;

    RuleGroup(String groupName) {
        this.groupName = groupName;
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

    public Change get(DiffType diffType) {
        // TODO: Throw an exception when rule does not exist??
        return rules.get(diffType);
    }
}
