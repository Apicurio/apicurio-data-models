package io.apicurio.umg.io;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.apicurio.umg.beans.beans.Specification;
import io.apicurio.umg.logging.Logger;

public class SpecificationLoader {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    /**
     * Loads a single specification file, parses it into an object, and
     * returns that object.
     * @param specPath
     */
    public static Specification loadSpec(String specPath) {
    	File file = new File(specPath);
    	return loadSpec(file);
    }

    /**
     * Loads a single specification file/resource, parses it into an object, and
     * returns that object.
     * @param specPath
     */
    public static Specification loadSpec(String specPath, ClassLoader cl) {
        URL resource = cl.getResource(specPath);
        if (resource == null) {
            throw new NullPointerException("Specification not found: " + specPath);
        }
        return loadSpec(resource);
    }

    /**
     * Loads a single specification resource, parses it into an object, and
     * returns that object.
     * @param specFile
     */
    public static Specification loadSpec(File specFile) {
        try {
        	if (!specFile.isFile()) {
        		throw new RuntimeException("Invalid specification file (not a file): " + specFile);
        	}
        	return loadSpec(specFile.toURI().toURL());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    /**
     * Loads a single specification resource, parses it into an object, and
     * returns that object.
     * @param specURL
     */
    public static Specification loadSpec(URL specURL) {
        Logger.info("Loading specification from: %s", specURL);
        try {
            Specification spec = mapper.readValue(specURL, Specification.class);
            Logger.info("Specification '%s' loaded with %d entities.", spec.getName(),
                    Optional.ofNullable(spec.getEntities()).orElse(Collections.emptySet()).size());
            return spec;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

}
