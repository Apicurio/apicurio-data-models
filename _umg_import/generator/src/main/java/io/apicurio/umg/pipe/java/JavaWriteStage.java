package io.apicurio.umg.pipe.java;

import io.apicurio.umg.pipe.AbstractStage;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JavaWriteStage extends AbstractStage {

    private File outputDirectory;

    public JavaWriteStage(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            writeToFile(t.getJavaSource(), outputDirectory);
        });
    }

    /**
     * Writes the given class out to a file.
     *
     * @param modelClass
     * @param outputDirectory
     */
    private void writeToFile(JavaSource modelClass, File outputDirectory) {
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
