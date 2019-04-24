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

package io.apicurio.datamodels.core.paths;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.datamodels.core.models.NodePath;

/**
 * @author eric.wittmann@gmail.com
 */
public class NodePathIoTestRunner extends ParentRunner<NodePathIoTestCase> {

    private Class<?> testClass;
    private List<NodePathIoTestCase> children;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructor.
     * @param testClass
     * @throws InitializationError
     */
    public NodePathIoTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;
        this.children = loadTests();
    }

    private List<NodePathIoTestCase> loadTests() throws InitializationError {
        try {
            List<NodePathIoTestCase> allTests = new LinkedList<>();
            
            URL testsJsonUrl = Thread.currentThread().getContextClassLoader().getResource("fixtures/paths/io-tests.json");
            String testsJsonSrc = IOUtils.toString(testsJsonUrl, "UTF-8");
            JsonNode tree = mapper.readTree(testsJsonSrc);
            ArrayNode tests = (ArrayNode) tree;
            tests.forEach( test -> {
                ObjectNode testNode = (ObjectNode) test;
                NodePathIoTestCase testCase = new NodePathIoTestCase();
                testCase.setName(testNode.get("name").asText());
                testCase.setPath(testNode.get("path").asText());
                ArrayNode segs = (ArrayNode) testNode.get("segments");
                Assert.assertNotNull(segs);
                segs.forEach(s -> {
                    testCase.getSegments().add(s.asText());
                });
                allTests.add(testCase);
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
    protected List<NodePathIoTestCase> getChildren() {
        return children;
    }

    /**
     * @see org.junit.runners.ParentRunner#describeChild(java.lang.Object)
     */
    @Override
    protected Description describeChild(NodePathIoTestCase child) {
        return Description.createTestDescription(this.testClass, child.getName());
    }

    /**
     * @see org.junit.runners.ParentRunner#runChild(java.lang.Object, org.junit.runner.notification.RunNotifier)
     */
    @Override
    protected void runChild(NodePathIoTestCase child, RunNotifier notifier) {
        Description description = this.describeChild(child);
        Statement statement = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String path = child.getPath();
                
                // Parse the path
                NodePath np = new NodePath(path);
                
                // Write that path back out to a string
                String actual = np.toString();
                
                // Compare
                String expected = path;
                Assert.assertEquals(expected, actual);
                
                // Get the sequence of segments from the path
                List<String> segments = np.toSegments();
                List<String> expectedSegments = child.getSegments();
                Assert.assertEquals(expectedSegments, segments);
            }
        };
        runLeaf(statement, description, notifier);
    }

}
