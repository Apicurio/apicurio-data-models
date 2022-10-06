package io.apicurio.umg.pipe;

import java.io.File;

public class DebugStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;

        // Debug output
        // TODO remove this
        if (Boolean.TRUE) {
            return;
        }
        System.out.println("---");
        state.getIndex().findPackages("io.apicurio").forEach(pkg -> {
            System.out.println("Package: " + pkg.getName());
            pkg.getClasses().values().forEach(clss -> {
                System.out.println("    Class: " + clss.getName());
                clss.getFields().values().forEach(field -> {
                    System.out.println("        Field: " + field.getName() + " (" + field.getType() + ")");
                });
            });
        });
        System.out.println("---");
    }


}
