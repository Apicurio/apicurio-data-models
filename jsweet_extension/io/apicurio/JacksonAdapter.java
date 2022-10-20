/*
 * Copyright 2021 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.apicurio;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.lang.model.element.Element;

import org.jsweet.transpiler.extension.PrinterAdapter;
import org.jsweet.transpiler.model.MethodInvocationElement;
import org.jsweet.transpiler.model.NewClassElement;

/**
 * This adapter provides transpilation from {@link com.fasterxml.jackson.databind.JsonNode} and {@link com.fasterxml.jackson.databind.ObjectNode}
 * to Typescript "any" and "object", respectively.
 */
public class JacksonAdapter extends PrinterAdapter {
    public JacksonAdapter(PrinterAdapter parent) {
        super(parent);
        addTypeMapping("com.fasterxml.jackson.databind.node.ObjectNode", "object");
        addTypeMapping("com.fasterxml.jackson.databind.JsonNode", "any");
    }

    @Override
    public boolean substituteNewClass(NewClassElement newClass) {
        return super.substituteNewClass(newClass);
    }

    @Override
    public boolean substituteMethodInvocation(MethodInvocationElement invocation) {
        if (invocation.getTargetExpression() != null) {
            Element targetType = invocation.getTargetExpression().getTypeAsElement();
            if ("com.fasterxml.jackson.databind.node.ObjectNode".equals(targetType.toString())) {
                switch (invocation.getMethodName()) {
                    case "get":
                        print(invocation.getTargetExpression().toString()).print("['").print(invocation.getArgument(0)).print("']");
                        return true;
                }
            }
            if ("com.fasterxml.jackson.databind.JsonNode".equals(targetType.toString())) {
                switch (invocation.getMethodName()) {
                    case "asText":
                        print(invocation.getTargetExpression());
                        return true;
                    case "textValue":
                        print(invocation.getTargetExpression());
                        return true;
                }
            }
        }
        return super.substituteMethodInvocation(invocation);
    }
}
