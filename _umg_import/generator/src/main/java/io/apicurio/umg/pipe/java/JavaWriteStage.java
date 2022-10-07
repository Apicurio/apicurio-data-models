package io.apicurio.umg.pipe.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.jboss.forge.roaster.model.source.JavaSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.pipe.AbstractStage;

public class JavaWriteStage extends AbstractStage {

    private File outputDirectory;

    public JavaWriteStage(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @Override
    protected void doProcess() {
        getState().getIndex().findClasses("").forEach(model -> {
            if (!model.isCore()) {

                Logger.info("Generating model for entity '%s'", model.getName());

                if(model.is_interface()) {
                    writeToFile(model.getInterfaceSource(), outputDirectory);
                } else {
                    writeToFile(model.getClassSource(), outputDirectory);
                }
            }
        });
    }

    /**
     * Writes the given class out to a file.
     * @param modelClass
     * @param outputDirectory
     */
    private void writeToFile(JavaSource modelClass, File outputDirectory) {
        String pkg = modelClass.getPackage();
        String fpath = pkg.replaceAll("[\\.]", "/");
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
