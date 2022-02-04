package io.apicurio.datamodels.core.diff;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.apicurio.datamodels.core.diff.change.Change;
import io.apicurio.datamodels.core.diff.change.ChangeType;
import io.apicurio.datamodels.core.diff.ruleset.OasDiffRuleset;
import io.apicurio.datamodels.core.diff.ruleset.Ruleset;
import io.apicurio.datamodels.core.models.NodePath;

public class DiffContext {

    private final Set<Difference> diff = new HashSet<>();
    private DiffContext rootContext;
    private DiffContext parentContext;
    public Ruleset ruleSet;

    public DiffContext(DiffContext rootContext, DiffContext parentContext, String pathUpdated) {
        this.rootContext = rootContext;
        this.parentContext = parentContext;
        this.ruleSet = new OasDiffRuleset();
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

    public void addDifference(DiffType diffType, Change change, Map<String, String> templateEntries, NodePath path) {
        diff.add(new Difference(diffType, change.getType(), path, interpolateTemplateLiterals(change.getMessage(), templateEntries)));
    }

    // TODO: Remove this once message variables have been applied
    public void addDifference(DiffType diffType, Change change, NodePath path) {
        diff.add(new Difference(diffType, change.getType(), path, change.getMessage()));
    }

    public Set<Difference> getDifferences() {
        return this.diff;
    }

    public Set<Difference> getBreakingChanges() {
        return this.diff.stream().filter(d -> d.getChangeType() == ChangeType.BREAKING).collect(Collectors.toSet());
    }

    private String interpolateTemplateLiterals(String messageTemplate, Map<String, String> templateEntries) {
        for (Map.Entry<String, String> templateEntry : templateEntries.entrySet()) {
            String templateLiteral = "${" + templateEntry.getKey() + "}";
            if (messageTemplate.contains(templateLiteral)) {
                messageTemplate = messageTemplate.replace(templateLiteral, templateEntry.getValue());
            }
        }
        return messageTemplate;
    }
}
