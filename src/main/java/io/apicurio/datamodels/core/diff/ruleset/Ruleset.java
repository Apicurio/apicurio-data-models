package io.apicurio.datamodels.core.diff.ruleset;

import io.apicurio.datamodels.compat.FilesystemCompat;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.diff.DiffType;
import io.apicurio.datamodels.core.diff.DiffRuleConfig;
import io.apicurio.datamodels.core.diff.ChangeType;
import io.apicurio.datamodels.core.diff.DiffRuleConfigGroup;
import jsweet.lang.Async;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Ruleset {
    protected Object rulesConfig;

    Ruleset(Object rulesConfig) {
        this.rulesConfig = rulesConfig;
    }

    /**
     * Loads the ruleset configuration from a JSON file
     */
//    @Async
    public static CompletableFuture<Ruleset> loadRuleset(String name) {
        // TODO: load rulesets from a static location embedded in the library
         String pathToFile = "/Users/ephelan/code/src/github.com/Apicurio/apicurio-data-models/src/main" + "/resources/io/apicurio/datamodels/core/diff/ruleset/" + name + ".json";
         String fileContents;
         try {
             fileContents = FilesystemCompat.readFileSync(pathToFile).get();
             Object ruleConfig = JsonCompat.parseJSON(fileContents);

             Ruleset rs = new OasDiffRuleset(ruleConfig);
             return CompletableFuture.completedFuture(rs);
         } catch (InterruptedException | ExecutionException e) {
             throw new RuntimeException(e);
         }
    }

    protected DiffRuleConfigGroup loadRules(String groupName) {
        DiffRuleConfigGroup ruleGroup = new DiffRuleConfigGroup(groupName);
        Map<DiffType, DiffRuleConfig> ruleConfigMap = new HashMap<>();

        Object ruleGroupConfig = JsonCompat.getProperty(this.rulesConfig, groupName);
        if (ruleGroupConfig == null) {
            return null;
        }

        Boolean disabled = JsonCompat.getPropertyBoolean(ruleGroupConfig, "disabled");
        if (disabled != null && disabled) {
            ruleGroup.setDisabled(true);
        }
        Object rules = JsonCompat.getProperty(ruleGroupConfig, "rules");

        List<String> keys = JsonCompat.keys(rules);
        keys.forEach(key -> {
            Object ruleConfig = JsonCompat.getProperty(rules, key);
            DiffType id = DiffType.valueOf(key);
            String message = JsonCompat.getPropertyString(ruleConfig, "message");
            ChangeType changeType = ChangeType.valueOf(JsonCompat.getPropertyString(ruleConfig, "type"));
            Boolean ruleDisabled = JsonCompat.getPropertyBoolean(ruleConfig, "disabled");

            if (disabled != null && disabled) {
                ruleDisabled = true;
            } else if (ruleDisabled == null) {
                ruleDisabled = false;
            }

            ruleConfigMap.put(id, new DiffRuleConfig(id, changeType, message, ruleDisabled));
        });
        ruleGroup.setRules(ruleConfigMap);

        return ruleGroup;
    }
}
