package io.apicurio.umg;

import org.junit.Test;

import io.apicurio.umg.io.SpecificationLoader;
import io.apicurio.umg.main.Main;

public class ValidateSpecificationsTest {

    @Test
    public void validateAllSpecifications() {
        validateSpec("specs/openapi.yaml");
        validateSpec("specs/asyncapi.yaml");
        validateSpec("specs/json-schema.yaml");
    }

    private void validateSpec(String specPath) {
        SpecificationLoader.loadSpec(specPath, Main.class.getClassLoader());
    }

}
