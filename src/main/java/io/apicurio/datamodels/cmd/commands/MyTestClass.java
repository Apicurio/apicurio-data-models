package io.apicurio.datamodels.cmd.commands;

import java.util.LinkedHashMap;

public class MyTestClass {
    private LinkedHashMap<String, String> widgets;

    public MyTestClass() {
        this.widgets = new LinkedHashMap<>();
    }

    public String getWidget(String name) {
        return widgets.get(name);
    }

    public void addWidget(String name, String value) {
        widgets.put(name, value);
    }

}
