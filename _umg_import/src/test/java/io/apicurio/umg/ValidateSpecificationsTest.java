package io.apicurio.umg;

import io.apicurio.umg.io.SpecificationLoader;
import org.junit.Test;

public class ValidateSpecificationsTest {

    @Test
    public void validateAllSpecifications() {
        validateSpec("specifications/openapi/openapi-2.0.x.yaml");
        validateSpec("specifications/openapi/openapi-3.0.x.yaml");
        validateSpec("specifications/openapi/openapi-3.1.x.yaml");
    }

    private void validateSpec(String specPath) {
        SpecificationLoader.loadSpec(specPath);
    }

}
