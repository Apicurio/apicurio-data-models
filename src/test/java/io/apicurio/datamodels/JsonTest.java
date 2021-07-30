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

package io.apicurio.datamodels;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author eric.wittmann@gmail.com
 */
public class JsonTest {

    private ObjectMapper mapper = new ObjectMapper();
    private String source1 = "{\r\n" + 
            "    \"property-1\": \"hello world\",\r\n" + 
            "    \"property-2\": true,\r\n" + 
            "    \"fields\": [\r\n" + 
            "        {\"name\": \"object1\", \"description\": \"the first\"},\r\n" + 
            "        {\"description\": \"the second\", \"name\": \"object2\" },\r\n" + 
            "        {\"name\": \"object3\", \"description\": \"the third\"}\r\n" + 
            "    ]\r\n" + 
            "}";
    private String source2 = "{\"property-2\": true, \"fields\":[{\"name\":\"object1\", \"description\":\"the first\"},{\"description\":\"the second\",\"name\":\"object2\"},{\"description\":\"the third\",\"name\":\"object3\"}],\"property-1\":\"hello world\",\"property-2\":true}\r\n" + 
            "{\"property-1\": \"hello world\"}";

    @Test
    public void testFromSchema() throws Exception {
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        JsonNode tree1 = mapper.readTree(source1);
        String serialized1 = mapper.writeValueAsString(mapper.treeToValue(tree1, Object.class));
        System.out.println(serialized1);
        
        JsonNode tree2 = mapper.readTree(source2);
        String serialized2 = mapper.writeValueAsString(mapper.treeToValue(tree2, Object.class));
        System.out.println(serialized2);
        
        Assert.assertEquals(serialized1, serialized2);
    }
}
