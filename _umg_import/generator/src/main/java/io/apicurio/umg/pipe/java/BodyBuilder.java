package io.apicurio.umg.pipe.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.apicurio.umg.logging.Logger;

public class BodyBuilder {

    private StringBuilder str = new StringBuilder();
    private Map<String, String> context = new HashMap<>();

    public void addContext(String name, String value) {
        if (value == null) {
            Logger.warn("Adding null value to BodyBuilder for: " + name);
        } else {
            context.put(name, value);
        }
    }

    public void clearContext() {
        context.clear();
    }

    public void append(String line) {
        String lineResolved = line;
        for (Entry<String, String> entry : context.entrySet()) {
            lineResolved = lineResolved.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        str.append(lineResolved);
        str.append("\n");
        if (lineResolved.contains("${")) {
            Logger.warn("[BodyBuilder] append detected unresolved variables: " + lineResolved);
        }
    }

    @Override
    public String toString() {
        return str.toString();
    }

}
