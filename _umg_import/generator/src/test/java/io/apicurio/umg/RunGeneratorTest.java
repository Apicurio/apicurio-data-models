package io.apicurio.umg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import io.apicurio.umg.io.SpecificationLoader;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.main.Main;
import io.apicurio.umg.models.spec.SpecificationModel;
import lombok.SneakyThrows;

public class RunGeneratorTest {

    @Test
    public void run() throws Exception {
        var specs = loadSpecs();
        var generator = UnifiedModelGenerator.create(specs);
        generator.generateInto(getTargetDir());
        Logger.info("Model generated successfully!");
    }

    @SneakyThrows
    public File getTargetDir() {
        String relPath = getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
        System.out.println(relPath);
        File targetDir = new File(relPath + "../../target/generated-models");
        if (targetDir.exists()) {
            FileUtils.deleteDirectory(targetDir);
        }
        targetDir.mkdir();
        return targetDir;
    }

    private static List<SpecificationModel> loadSpecs() {
        Logger.info("Loading specifications.");
        List<SpecificationModel> specs = new ArrayList<>();

        specs.add(SpecificationLoader.loadSpec("specs/asyncapi.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specs/json-schema.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specs/openapi.yaml", Main.class.getClassLoader()));

        return specs;
    }
}
