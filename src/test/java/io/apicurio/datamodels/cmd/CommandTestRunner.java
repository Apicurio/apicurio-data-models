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

package io.apicurio.datamodels.cmd;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.ot.OtCommand;
import io.apicurio.datamodels.cmd.ot.OtEngine;
import io.apicurio.datamodels.cmd.util.MarshallUtils;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * @author eric.wittmann@gmail.com
 */
public class CommandTestRunner extends ParentRunner<CommandTestCase> {

    private Class<?> testClass;
    private List<CommandTestCase> children;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructor.
     * @param testClass
     * @throws InitializationError
     */
    public CommandTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;
        this.children = loadTests();
    }

    private List<CommandTestCase> loadTests() throws InitializationError {
        try {
            URL testsJsonUrl = Thread.currentThread().getContextClassLoader().getResource("fixtures/cmd/tests.json");
            List<CommandTestCase> allTests = mapper.readValue(testsJsonUrl, mapper.getTypeFactory().constructCollectionType(List.class, CommandTestCase.class));
            return allTests;
        } catch (IOException e) {
            throw new InitializationError(e);
        }
    }

    /**
     * @see org.junit.runners.ParentRunner#getChildren()
     */
    @Override
    protected List<CommandTestCase> getChildren() {
        return children;
    }

    /**
     * @see org.junit.runners.ParentRunner#describeChild(java.lang.Object)
     */
    @Override
    protected Description describeChild(CommandTestCase child) {
        return Description.createTestDescription(this.testClass, child.getName());
    }

    /**
     * @see org.junit.runners.ParentRunner#runChild(java.lang.Object, org.junit.runner.notification.RunNotifier)
     */
    @Override
    protected void runChild(CommandTestCase child, RunNotifier notifier) {
        Description description = this.describeChild(child);
        Statement statement = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                LoggerCompat.info("------------------------");
                LoggerCompat.info("[CommandTestRunner] Running Test: %s", child.getName());
                String beforeCP = "fixtures/cmd/" + child.getTest() + ".before.json";
                String afterCP = "fixtures/cmd/" + child.getTest() + ".after.json";
                String commandsCP = "fixtures/cmd/" + child.getTest() + ".commands.json";
                
                if (child.getCommands() != null) {
                    commandsCP = "fixtures/cmd/" + child.getTest() + child.getCommands();
                }
                
                URL beforeUrl = Thread.currentThread().getContextClassLoader().getResource(beforeCP);
                URL afterUrl = Thread.currentThread().getContextClassLoader().getResource(afterCP);
                URL commandsUrl = Thread.currentThread().getContextClassLoader().getResource(commandsCP);

                Assert.assertNotNull("Test file not found on classpath: " + beforeCP, beforeUrl);
                Assert.assertNotNull("Test file not found on classpath: " + afterCP, afterUrl);
                Assert.assertNotNull("Test file not found on classpath: " + commandsCP, commandsUrl);

                // Read the test source
                String beforeJson = loadResource(beforeUrl);
                String afterJson = loadResource(afterUrl);
                String commandsJson = loadResource(commandsUrl);

                Assert.assertNotNull(beforeJson);
                Assert.assertNotNull(afterJson);
                Assert.assertNotNull(commandsJson);
                
                // Parse into a Json object
                Object beforeJs = JsonCompat.parseJSON(beforeJson);
                Object commandsJs = JsonCompat.parseJSON(commandsJson);

                Assert.assertNotNull(beforeJs);
                Assert.assertNotNull(commandsJs);

                // Read the before doc into a Document
                Document document = Library.readDocument(beforeJs);
                Assert.assertNotNull(document);
                
                // Load all the commands to apply.
                List<OtCommand> commands = new ArrayList<>();
                ArrayNode allCommands = (ArrayNode) commandsJs;
                for (int cidx = 0; cidx < allCommands.size(); cidx++) {
                    JsonNode commandNode = allCommands.get(cidx);
                    ICommand cmd = MarshallUtils.unmarshallCommand(commandNode);
                    OtCommand otc = new OtCommand();
                    otc.author = "user";
                    otc.command = cmd;
                    otc.contentVersion = cidx;
                    otc.local = false;
                    otc.reverted = false;
                    if (commandNode.has("__contentVersion")) {
                        otc.contentVersion = commandNode.get("__contentVersion").asLong();
                    }
                    commands.add(otc);
                    
                    // Make sure we can marshall/unmarshall the command
                    Object marshalledCommand = MarshallUtils.marshallCommand(cmd);
                    String mcs = JsonCompat.stringify(marshalledCommand);
                    ICommand unmarshalledCommand = MarshallUtils.unmarshallCommand(JsonCompat.parseJSON(mcs));
                    Assert.assertEquals("Failed to marshall/unmarshall command: " + cmd.getClass(), cmd.getClass(), unmarshalledCommand.getClass());
                }
                
                // Apply all the commands to the Document (modifying the document).
                OtEngine engine = new OtEngine(document);
                commands.forEach(cmd -> {
                    engine.executeCommand(cmd, false);
                });

                // Check that the resulting (modified) document is what we expected.
                Document actualDoc = engine.getCurrentDocument();
                String actual = Library.writeDocumentToJSONString(actualDoc);
                String expected = afterJson;
                
                assertJsonEquals(expected, actual);
            }
        };
        runLeaf(statement, description, notifier);
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
