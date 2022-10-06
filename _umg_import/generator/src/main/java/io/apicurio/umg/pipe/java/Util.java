package io.apicurio.umg.pipe.java;

import java.util.Map;

public class Util {

    private static Map<String, String> JAVA_KEYWORD_MAP = Map.ofEntries(
            Map.entry("default", "_default"),
            Map.entry("enum", "_enum"),
            Map.entry("const", "_const"),
            Map.entry("if", "_if"),
            Map.entry("else", "_else")
    );

    public static String sanitizeFieldName(String name) {
        return JAVA_KEYWORD_MAP.getOrDefault(name, name);
    }
}
