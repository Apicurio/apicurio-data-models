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
import org.jsweet.transpiler.extension.PrinterAdapter;
import org.jsweet.transpiler.model.MethodInvocationElement;
import org.jsweet.transpiler.model.NewClassElement;

import java.util.concurrent.CompletableFuture;

import javax.lang.model.element.Element;

/**
 * This adapter provides transpilation from {@link java.util.concurrent.CompletableFuture}
 * and its methods to <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise">JavaScript Promise</a>
 */
public class PromiseAdapter extends PrinterAdapter {
    public PromiseAdapter(PrinterAdapter parent) {
        super(parent);
        addTypeMapping(CompletableFuture.class.getName(), "Promise");
        addTypeMapping(List.class.getName(), "Array");
    }

    @Override
    public boolean substituteNewClass(NewClassElement newClass) {
        String className = newClass.getTypeAsElement().toString();

        // map the Promise constructors
        if (CompletableFuture.class.getName().equals(className)) {
            print("new Promise<").print(newClass.toString()).print(">(").printArgList(newClass.getArguments()).print(")");
            return true;
        }
        // delegate to the adapter chain
        return super.substituteNewClass(newClass);
    }

    @Override
    public boolean substituteMethodInvocation(MethodInvocationElement invocation) {
        if (invocation.getTargetExpression() != null) {
            Element targetType = invocation.getTargetExpression().getTypeAsElement();
            if (CompletableFuture.class.getName().equals(targetType.toString())) {
                switch (invocation.getMethodName()) {
                    case "completedFuture":
                        printMacroName(invocation.getMethodName());
                        print("Promise.resolve(").print(invocation.getArgument(0)).print(")");
                        return true;
                    case "thenAccept":
                        printMacroName(invocation.getMethodName());
                        print(invocation.getTargetExpression()).print(".then(").print(invocation.getArgument(0)).print(")");
                        return true;
                     case "addAll":
                         printMacroName(invocation.getMethodName());
                         print(invocation.getTargetExpression()).print(".concat(").print(invocation.getArguments().toString()).print(")");
                         return true;
                }
            }
        }
        // delegate to the adapter chain
        return super.substituteMethodInvocation(invocation);
    }
}
