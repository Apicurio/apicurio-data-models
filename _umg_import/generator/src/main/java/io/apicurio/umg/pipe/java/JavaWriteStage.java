package io.apicurio.umg.pipe.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.jboss.forge.roaster.model.source.JavaSource;

import io.apicurio.umg.pipe.AbstractStage;

public class JavaWriteStage extends AbstractStage {

    @Override
    protected void doProcess() {
        getState().getJavaIndex().getClasses().values().forEach(_class -> {
            writeToFile(_class, getState().getConfig().getOutputDirectory());
        });
        getState().getJavaIndex().getInterfaces().values().forEach(_interface -> {
            writeToFile(_interface, getState().getConfig().getOutputDirectory());
        });
    }

    /**
     * Writes the given class out to a file.
     *
     * @param modelClass
     * @param outputDirectory
     */
    private void writeToFile(JavaSource<?> modelClass, File outputDirectory) {
        String pkg = modelClass.getPackage();
        String fpath = pkg.replace(".", "/");
        File dir = new File(outputDirectory, fpath);
        dir.mkdirs();
        File file = new File(dir, modelClass.getName() + ".java");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(modelClass.toString());
            writer.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
