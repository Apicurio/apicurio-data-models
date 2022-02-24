package io.apicurio.datamodels.core.diff;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.apicurio.datamodels.core.diff.ruleset.Ruleset;
import io.apicurio.datamodels.core.models.NodePath;

import static io.apicurio.datamodels.core.diff.DiffUtil.interpolateTemplateLiterals;

public class DiffContext {

    private final Set<Difference> diff = new HashSet<>();
    private DiffContext rootContext;
    private DiffContext parentContext;
    public Ruleset ruleSet;

    public DiffContext(DiffContext rootContext, DiffContext parentContext, String pathUpdated) {
        this.rootContext = rootContext;
        this.parentContext = parentContext;
        try {
            this.ruleSet = Ruleset.loadRuleset("oas");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRootContext(DiffContext rootContext) {
        if (this.rootContext != null || parentContext != null) {
            throw new IllegalStateException();
        }
        this.rootContext = rootContext;
        parentContext = rootContext;
    }

    public static DiffContext createRootContext(String basePathFragmentedUpdated) {
        DiffContext rootContext = new DiffContext(null, null, basePathFragmentedUpdated);
        rootContext.initRootContext(rootContext);
        return rootContext;
    }

    public static DiffContext createRootContext() {
        return createRootContext("");
    }

    public void addDifferenceIfEnabled(DiffRuleConfig change, Map<String, String> templateEntries, NodePath path) {
        if (change.isDisabled()) {
            return;
        }
        String message = change.getMessageTemplate();
        if (templateEntries != null) {
            message = interpolateTemplateLiterals(message, templateEntries);
        }
        diff.add(new Difference(change.getCode(), change.getChangeType(), path, message));
    }

    public Set<Difference> getDifferences() {
        return this.diff;
    }

    // TODO: Get this to transpile to TypeScript
//    public Set<Difference> getBreakingChanges() {
//        return this.diff.stream().filter(d -> d.getChangeType() == ChangeType.BREAKING).collect(Collectors.toSet());
//    }
}
