/*
 * Copyright 2022 Red Hat
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

package io.apicurio.datamodels.paths;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author eric.wittmann@gmail.com
 */
public class NodePointerIoTestRunner extends ParentRunner<NodePointerIoTestCase> {

    private Class<?> testClass;
    private List<NodePointerIoTestCase> children;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructor.
     * @param testClass
     * @throws InitializationError
     */
    public NodePointerIoTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;
        this.children = loadTests();
    }

    private List<NodePointerIoTestCase> loadTests() throws InitializationError {
        try {
            URL testsJsonUrl = Thread.currentThread().getContextClassLoader().getResource("fixtures/pointers/io-tests.json");
            List<NodePointerIoTestCase> allTests = mapper.readValue(testsJsonUrl, mapper.getTypeFactory().constructCollectionType(List.class, NodePointerIoTestCase.class));
            return allTests;
        } catch (IOException e) {
            throw new InitializationError(e);
        }
    }

    /**
     * @see org.junit.runners.ParentRunner#getChildren()
     */
    @Override
    protected List<NodePointerIoTestCase> getChildren() {
        return children;
    }

    /**
     * @see org.junit.runners.ParentRunner#describeChild(java.lang.Object)
     */
    @Override
    protected Description describeChild(NodePointerIoTestCase child) {
        return Description.createTestDescription(this.testClass, child.getName());
    }

    /**
     * @see org.junit.runners.ParentRunner#runChild(java.lang.Object, org.junit.runner.notification.RunNotifier)
     */
    @Override
    protected void runChild(NodePointerIoTestCase child, RunNotifier notifier) {
        Description description = this.describeChild(child);
        Statement statement = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String pointer = child.getPointer();

                // Parse the pointer
                NodePointer np = NodePointerUtil.parse(pointer);

                // Write that pointer back out to a string
                String actual = np.toString();

                // Compare
                String expected = pointer;
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
