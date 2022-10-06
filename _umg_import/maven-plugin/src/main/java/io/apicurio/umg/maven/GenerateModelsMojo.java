package io.apicurio.umg.maven;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import io.apicurio.umg.UnifiedModelGenerator2;
import io.apicurio.umg.beans.beans.Specification;
import io.apicurio.umg.io.SpecificationLoader;

/**
 * The main generate code mojo implementation.
 * 
 * @author eric.wittmann@gmail.com
 */
@Mojo(name = "generate")
public class GenerateModelsMojo extends AbstractMojo {

    /**
     * The current Maven project.
     */
    @Parameter(defaultValue = "${project}", readonly = true)
    protected MavenProject project;
    
    @Parameter(required = true)
    List<File> specifications;

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/umg")
    File outputDir;

    /**
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Generating unified models from: " + specifications);
        if (specifications == null || specifications.isEmpty()) {
            throw new MojoFailureException("No input specifications found.");
        }
        if (!specifications.stream().map(specFile -> specFile.isFile()).reduce((a, b) -> a && b).orElse(false)) {
        	throw new MojoFailureException("At least one configured specification does not exist.");
        }

        if (outputDir.isFile()) {
            throw new MojoFailureException(
                    "Output directory is unexpectedly a file (should be a directory or non-existent).");
        }

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // Add the output directory as a compile source.
        getLog().info("Generating code into: " + outputDir.getAbsolutePath());
        this.project.addCompileSourceRoot(outputDir.getAbsolutePath());

        // Load the specs
        List<Specification> specs = loadSpecifications();
        // Create a unified model generator
        UnifiedModelGenerator2 generator = UnifiedModelGenerator2.create(specs);
        // Generate the source code into the target output directory.
        try {
			generator.generateInto(outputDir);
		} catch (Exception e) {
			throw new MojoExecutionException("Error generating unified model classes.", e);
		}

        getLog().info("Models successfully generated.");
    }

    /**
     * Loads the configured specifications.
     */
	private List<Specification> loadSpecifications() {
		return this.specifications.stream().map(file -> SpecificationLoader.loadSpec(file)).collect(Collectors.toUnmodifiableList());
	}
	
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("foo", "bar", "baz", "blerg");
		
        System.out.println(strings.stream().map(s -> "bar".equals(s)).reduce((a, b) -> {
        	System.out.println("(a, b) = " + a + "," + b);
        	return a || b;
        }).orElse(false));
	}

}
