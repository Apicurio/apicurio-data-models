/*
 * Copyright 2019 Red Hat
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

package io.apicurio.datamodels.compat;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.cmd.commands.ChangeVersionCommand;

/**
 * @author eric.wittmann@gmail.com
 */
public class MarshallCompatTest {
    
    @Test
    public void testChangeVersion() throws Exception {
        ICommand command = ChangeVersionCommand.create("1.0");
        Object mc = MarshallCompat.marshallCommand(command);
        String output = JsonCompat.stringify(mc);
        System.out.println(output);
        
        command = MarshallCompat.unmarshallCommand(mc);
        Assert.assertEquals("1.0", ((ChangeVersionCommand) command)._newVersion);
    }
//    
//    @Test
//    public void testReplaceNode() throws Exception {
//        Oas30Document doc = (Oas30Document) Library.readDocumentFromJSONString("{" + 
//                "  \"openapi\": \"3.0.0\"," + 
//                "  \"info\": {" + 
//                "    \"title\": \"Simple OAI 3.0.0 API\"," + 
//                "    \"description\": \"A simple API using OpenAPI 3.0.0.\"," + 
//                "    \"contact\": {" + 
//                "      \"name\": \"Example Org\"," + 
//                "      \"url\": \"http://www.example.org\"," + 
//                "      \"email\": \"contact@example.org\"" + 
//                "    }," + 
//                "    \"license\": {" + 
//                "      \"name\": \"Apache 2.0\"," + 
//                "      \"url\": \"https://www.apache.org/licenses/LICENSE-2.0\"" + 
//                "    }," + 
//                "    \"version\": \"2.0.11\"" + 
//                "  }" + 
//                "}");
//        Object with = JsonCompat.parseJSON("{" + 
//                "    \"title\": \"Updated API Title\"," + 
//                "    \"description\": \"A simple API.\"," + 
//                "    \"version\": \"1.0.0\"" + 
//                "  }");
//        ICommand command = ReplaceNodeCommand.create(doc.info, with);
//        
//        Object mc = MarshallCompat.marshallCommand(command);
//        String output = JsonCompat.stringify(mc);
//        System.out.println(output);
//
//        command = MarshallCompat.unmarshallCommand(mc);
//        Assert.assertEquals("/info", ((ReplaceNodeCommand) command)._nodePath.toString());
//        Assert.assertEquals(with, ((ReplaceNodeCommand) command)._new);
//    }

    static void assertJsonEquals(String expected, String actual) throws JSONException {
        JSONAssert.assertEquals(expected, actual, true);
    }

}
