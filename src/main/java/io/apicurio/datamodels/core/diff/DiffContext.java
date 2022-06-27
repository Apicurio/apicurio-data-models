package io.apicurio.datamodels.core.diff;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import io.apicurio.datamodels.core.diff.ruleset.Ruleset;
import io.apicurio.datamodels.core.models.NodePath;
import jsweet.lang.Async;

import static io.apicurio.datamodels.core.diff.DiffUtil.interpolateTemplateLiterals;

public class DiffContext {

    private final Set<Difference> diff = new HashSet<>();
    private DiffContext rootContext;
    private DiffContext parentContext;
    private Ruleset ruleSet;

    public DiffContext(DiffContext rootContext, DiffContext parentContext, String pathUpdated) {
        this.rootContext = rootContext;
        this.parentContext = parentContext;
    }

    @Async
    public void loadAndSetRuleset() {
        try {
            this.ruleSet = Ruleset.loadRuleset("oas").get();
        } catch (ExecutionException | InterruptedException e) {
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
        rootContext.loadAndSetRuleset();
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

    public Ruleset getRuleSet() {
        return this.ruleSet;
    }

    // TODO: Get this to transpile to TypeScript
//    public Set<Difference> getBreakingChanges() {
//        return this.diff.stream().filter(d -> d.getChangeType() == ChangeType.BREAKING).collect(Collectors.toSet());
//    }
}
