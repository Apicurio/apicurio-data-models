package io.apicurio.datamodels.core.diff.ruleset;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.diff.DiffType;
import io.apicurio.datamodels.core.diff.change.Change;
import io.apicurio.datamodels.core.diff.change.ChangeType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Ruleset {
    protected Object rulesConfig;

    Ruleset(Object rulesConfig) {
        this.rulesConfig = rulesConfig;
    }

    /**
     * Loads the ruleset configuration from a JSON file
     */
    public static Ruleset loadRuleset(String name) throws IOException {
        InputStream is = Ruleset.class.getResourceAsStream(name + ".json");
        if (is == null) {
            throw new FileNotFoundException("Compatibility rule config file '" + name + "' does not exist");
        }
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(is, StandardCharsets.UTF_8);
        for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
            out.append(buffer, 0, numRead);
        }
        Object ruleConfig = JsonCompat.parseJSON(out.toString());

        if ("oas".equals(name)) {
            return new OasDiffRuleset(ruleConfig);
        }
        return null;
    }

    protected RuleGroup loadRules(String groupName) {
        RuleGroup ruleGroup = new RuleGroup();
        Map<DiffType, Change> ruleConfigMap = new HashMap<>();

        Object pathsConfig = JsonCompat.getProperty(this.rulesConfig, groupName);

        Boolean disabled = JsonCompat.getPropertyBoolean(pathsConfig, "disabled");
        if (disabled != null && disabled) {
            ruleGroup.setDisabled(true);
        }
        Object rules = JsonCompat.getProperty(pathsConfig, "rules");

        List<String> keys = JsonCompat.keys(rules);
        keys.forEach(key -> {
            Object ruleConfig = JsonCompat.getProperty(rules, key);
            DiffType id = DiffType.valueOf(key);
            String message = JsonCompat.getPropertyString(ruleConfig, "message");
            ChangeType changeType = ChangeType.valueOf(JsonCompat.getPropertyString(ruleConfig, "type"));
            Boolean ruleDisabled = JsonCompat.getPropertyBoolean(ruleConfig, "disabled");

            if (disabled) {
                ruleDisabled = true;
            } else if (ruleDisabled == null) {
                ruleDisabled = false;
            }

            ruleConfigMap.put(id, new Change(id, changeType, message, ruleDisabled));
        });
        ruleGroup.setRules(ruleConfigMap);

        return ruleGroup;
    }
}
