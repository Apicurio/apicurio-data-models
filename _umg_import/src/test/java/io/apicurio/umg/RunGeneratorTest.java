package io.apicurio.umg;

import io.apicurio.umg.beans.beans.Specification;
import io.apicurio.umg.io.SpecificationLoader;
import io.apicurio.umg.logging.Logger;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RunGeneratorTest {

    @Test
    public void run() throws Exception {

        var specs = loadSpecs();
        var generator = UnifiedModelGenerator2.create(specs);
        generator.generateInto(getTargetDir());
        Logger.info("Model generated successfully!");
    }

    public File getTargetDir(){
        String relPath = getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
        System.out.println(relPath);
        File targetDir = new File(relPath+"../../target/generated-models");
        if(targetDir.exists()) {
            targetDir.delete();
        }
        targetDir.mkdir();
        return targetDir;
    }

    private static List<Specification> loadSpecs() {
        Logger.info("Loading specifications.");
        List<Specification> specs = new ArrayList<>();
//        specs.add(SpecificationLoader.loadSpec("specifications/openapi/openapi-2.0.x.yaml"));
//        specs.add(SpecificationLoader.loadSpec("specifications/openapi/openapi-3.0.x.yaml"));
//        specs.add(SpecificationLoader.loadSpec("specifications/openapi/openapi-3.1.x.yaml"));
//        specs.add(SpecificationLoader.loadSpec("specifications/asyncapi/asyncapi-2.0.x.yaml"));
        specs.add(SpecificationLoader.loadSpec("specifications/jsonschema/jsonschema-2020‑12.yaml"));
        return specs;
    }
}
