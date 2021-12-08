package io.apicurio;

import java.util.List;
import org.jsweet.transpiler.extension.PrinterAdapter;
import org.jsweet.transpiler.model.MethodInvocationElement;
import org.jsweet.transpiler.model.NewClassElement;

import java.util.concurrent.CompletableFuture;

import javax.lang.model.element.Element;

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
                    // TODO: map `addAll` to `push` in JavaScript
                    // case "addAll":
                }
            }
        }
        // delegate to the adapter chain
        return super.substituteMethodInvocation(invocation);
    }
}
