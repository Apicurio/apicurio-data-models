package io.apicurio.umg.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.apicurio.umg.beans.beans.Specification;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.main.Main;

import java.net.URL;
import java.util.Collections;
import java.util.Optional;

public class SpecificationLoader {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    /**
     * Loads a single specification file/resource, parses it into an object, and
     * returns that object.
     * @param specPath
     */
    public static Specification loadSpec(String specPath) {
        Logger.info("Loading specification from: %s", specPath);
        try {
            URL resource = Main.class.getClassLoader().getResource(specPath);
            if (resource == null) {
                throw new NullPointerException("Specification not found: " + specPath);
            }
            Specification spec = mapper.readValue(resource, Specification.class);
            Logger.info("Specification '%s' loaded with %d entities.", spec.getName(),
                    Optional.ofNullable(spec.getEntities()).orElse(Collections.emptySet()).size());
            return spec;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

}
