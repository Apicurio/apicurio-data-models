package io.apicurio.umg;

import io.apicurio.umg.beans.Specification;
import io.apicurio.umg.io.SpecificationLoader;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.main.Main;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    private static List<Specification> loadSpecs() {
        Logger.info("Loading specifications.");
        List<Specification> specs = new ArrayList<>();
        specs.add(SpecificationLoader.loadSpec("specifications/openapi/openapi-2.0.x.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specifications/openapi/openapi-3.0.x.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specifications/openapi/openapi-3.1.x.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specifications/asyncapi/asyncapi-2.0.x.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specifications/json-schema/json-schema-2020-12.yaml", Main.class.getClassLoader()));
        return specs;
    }
}
