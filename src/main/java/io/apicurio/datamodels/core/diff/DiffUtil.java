package io.apicurio.datamodels.core.diff;

import java.util.Map;

public class DiffUtil {
    public static boolean isNodeAdded(Object original, Object updated) {
        return (original == null && updated != null);
    }

    public static boolean isNodeRemoved(Object original, Object updated) {
        return (original != null && updated == null);
    }

    public static String interpolateTemplateLiterals(String messageTemplate, Map<String, String> templateEntries) {
        for (Map.Entry<String, String> templateEntry : templateEntries.entrySet()) {
            String templateLiteral = "${" + templateEntry.getKey() + "}";
            if (messageTemplate.contains(templateLiteral)) {
                messageTemplate = messageTemplate.replace(templateLiteral, templateEntry.getValue());
            }
        }
        return messageTemplate;
    }
}
