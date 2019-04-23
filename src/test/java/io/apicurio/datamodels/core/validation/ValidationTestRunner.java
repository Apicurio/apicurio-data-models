/*
 * Copyright 2019 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.core.validation;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.ValidationProblem;

/**
 * @author eric.wittmann@gmail.com
 */
public class ValidationTestRunner extends ParentRunner<ValidationTestCase> {

    private Class<?> testClass;
    private List<ValidationTestCase> children;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructor.
     * @param testClass
     * @throws InitializationError
     */
    public ValidationTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;
        this.children = loadTests();
    }

    private List<ValidationTestCase> loadTests() throws InitializationError {
        try {
            List<ValidationTestCase> allTests = new LinkedList<>();
            
            URL testsJsonUrl = Thread.currentThread().getContextClassLoader().getResource("fixtures/validation/tests.json");
            String testsJsonSrc = IOUtils.toString(testsJsonUrl, "UTF-8");
            JsonNode tree = mapper.readTree(testsJsonSrc);
            ArrayNode tests = (ArrayNode) tree;
            tests.forEach( test -> {
                ObjectNode testNode = (ObjectNode) test;
                ValidationTestCase fit = new ValidationTestCase();
                fit.setName(testNode.get("name").asText());
                fit.setTest(testNode.get("test").asText());
                allTests.add(fit);
            });
            
            return allTests;
        } catch (IOException e) {
            throw new InitializationError(e);
        }
    }

    /**
     * @see org.junit.runners.ParentRunner#getChildren()
     */
    @Override
    protected List<ValidationTestCase> getChildren() {
        return children;
    }

    /**
     * @see org.junit.runners.ParentRunner#describeChild(java.lang.Object)
     */
    @Override
    protected Description describeChild(ValidationTestCase child) {
        return Description.createTestDescription(this.testClass, child.getName());
    }

    /**
     * @see org.junit.runners.ParentRunner#runChild(java.lang.Object, org.junit.runner.notification.RunNotifier)
     */
    @Override
    protected void runChild(ValidationTestCase child, RunNotifier notifier) {
        Description description = this.describeChild(child);
        Statement statement = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String testCP = "fixtures/validation/" + child.getTest();
                URL testUrl = Thread.currentThread().getContextClassLoader().getResource(testCP);
                Assert.assertNotNull("Could not load test resource: " + testCP, testUrl);

                // Read the test source
                String original = loadResource(testUrl);
                Assert.assertNotNull(original);
                // Parse into a Json object
                Object originalParsed = mapper.readTree(original);
                
                // Parse into a data model
                Document doc = Library.readDocument(originalParsed);
                
                // Validate the document
                List<ValidationProblem> problems = Library.validate(doc, null);
                
                // Now compare with expected
                String actual = formatProblems(problems);
                String expectedCP = testCP + ".expected";
                URL expectedUrl = Thread.currentThread().getContextClassLoader().getResource(expectedCP);
                Assert.assertNotNull("Could not load test resource: " + expectedCP, expectedUrl);
                String expected = loadResource(expectedUrl);
                
                Assert.assertEquals(normalize(expected), normalize(actual));
            }
        };
        runLeaf(statement, description, notifier);
    }

    /**
     * @param expected
     */
    protected String normalize(String value) {
        return value.trim().replace("\r\n", "\n");
    }

    /**
     * Format the list of problems as a string.
     * @param problems
     */
    protected String formatProblems(List<ValidationProblem> problems) {
        StringBuilder builder = new StringBuilder();
        problems.sort(new Comparator<ValidationProblem>() {
            @Override
            public int compare(ValidationProblem o1, ValidationProblem o2) {
                int rval = o1.errorCode.compareTo(o2.errorCode);
                return rval;
            }
        });
        problems.forEach(problem -> {
            builder.append(problem.errorCode);
            builder.append("::");
            builder.append(problem.severity);
            builder.append("::");
            builder.append(problem.property);
            builder.append("::");
            builder.append(problem.message);
            builder.append("\n");
            // TODO include the node path in this!
        });
        return builder.toString();
    }

    /**
     * Loads a resource as a string (reads the content at the URL).
     * @param testResource
     * @throws IOException
     */
    static String loadResource(URL testResource) throws IOException {
        return IOUtils.toString(testResource, "UTF-8");
    }

    /**
     * Compares two JSON strings.
     * @param expected
     * @param actual
     * @throws JSONException
     */
    static void assertJsonEquals(String expected, String actual) throws JSONException {
        JSONAssert.assertEquals(expected, actual, true);
    }

}
