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

package io.apicurio.datamodels.cmd.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20SchemaDefinition;

/**
 * @author eric.wittmann@gmail.com
 */
public class SimplifiedTypeTest {
    
    private static final String OPENAPI_20_DATA = "{\r\n" + 
            "    \"swagger\": \"2.0\",\r\n" + 
            "    \"info\": {\r\n" + 
            "        \"title\": \"Simple API 2.0\",\r\n" + 
            "        \"version\": \"1.0.1\",\r\n" + 
            "        \"description\": \"Just a simple API in 2.0 (swagger) format.\"\r\n" + 
            "    },\r\n" + 
            "    \"consumes\": [\r\n" + 
            "        \"application/json\"\r\n" + 
            "    ],\r\n" + 
            "    \"produces\": [\r\n" + 
            "        \"application/json\"\r\n" + 
            "    ],\r\n" + 
            "    \"paths\": {\r\n" + 
            "        \"/\": {\r\n" + 
            "            \"parameters\": [\r\n" + 
            "                {\r\n" + 
            "                    \"name\": \"string-parameter\",\r\n" + 
            "                    \"description\": \"\",\r\n" + 
            "                    \"in\": \"query\",\r\n" + 
            "                    \"required\": true,\r\n" + 
            "                    \"type\": \"string\",\r\n" + 
            "                    \"format\": \"date-time\"\r\n" + 
            "                },\r\n" + 
            "                {\r\n" + 
            "                    \"name\": \"number-parameter\",\r\n" + 
            "                    \"description\": \"\",\r\n" + 
            "                    \"in\": \"query\",\r\n" + 
            "                    \"required\": true,\r\n" + 
            "                    \"type\": \"number\",\r\n" + 
            "                    \"format\": \"float\"\r\n" + 
            "                },\r\n" + 
            "                {\r\n" + 
            "                    \"name\": \"enum-parameter\",\r\n" + 
            "                    \"description\": \"\",\r\n" + 
            "                    \"in\": \"query\",\r\n" + 
            "                    \"type\": \"string\",\r\n" + 
            "                    \"enum\": [\r\n" + 
            "                        \"val1\",\r\n" + 
            "                        \"val2\",\r\n" + 
            "                        \"val3\"\r\n" + 
            "                    ]\r\n" + 
            "                },\r\n" + 
            "                {\r\n" + 
            "                    \"name\": \"file-parameter\",\r\n" + 
            "                    \"description\": \"\",\r\n" + 
            "                    \"in\": \"query\",\r\n" + 
            "                    \"type\": \"file\"\r\n" + 
            "                },\r\n" + 
            "                {\r\n" + 
            "                    \"name\": \"array-parameter\",\r\n" + 
            "                    \"description\": \"\",\r\n" + 
            "                    \"in\": \"query\",\r\n" + 
            "                    \"type\": \"array\",\r\n" + 
            "                    \"items\": {\r\n" + 
            "                        \"type\": \"boolean\"\r\n" + 
            "                    }\r\n" + 
            "                }\r\n" + 
            "            ]\r\n" + 
            "        }\r\n" + 
            "    },\r\n" + 
            "    \"definitions\": {\r\n" + 
            "        \"ExampleObject\": {\r\n" + 
            "            \"title\": \"Root Type for Example\",\r\n" + 
            "            \"description\": \"The root of the Example type's schema.\",\r\n" + 
            "            \"type\": \"object\",\r\n" + 
            "            \"properties\": {\r\n" + 
            "                \"prop1\": {\r\n" + 
            "                    \"type\": \"string\"\r\n" + 
            "                },\r\n" + 
            "                \"prop2\": {\r\n" + 
            "                    \"format\": \"int32\",\r\n" + 
            "                    \"type\": \"integer\"\r\n" + 
            "                }\r\n" + 
            "            },\r\n" + 
            "            \"example\": \"{\\n    \\\"prop1\\\": \\\"foo\\\",\\n    \\\"prop2\\\": 17\\n}\"\r\n" + 
            "        },\r\n" + 
            "        \"Examples\": {\r\n" + 
            "            \"description\": \"\",\r\n" + 
            "            \"required\": [\r\n" + 
            "                \"boolean-property\",\r\n" + 
            "                \"number-property\"\r\n" + 
            "            ],\r\n" + 
            "            \"type\": \"object\",\r\n" + 
            "            \"properties\": {\r\n" + 
            "                \"string-property\": {\r\n" + 
            "                    \"description\": \"\",\r\n" + 
            "                    \"type\": \"string\"\r\n" + 
            "                },\r\n" + 
            "                \"number-property\": {\r\n" + 
            "                    \"description\": \"\",\r\n" + 
            "                    \"type\": \"number\"\r\n" + 
            "                },\r\n" + 
            "                \"boolean-property\": {\r\n" + 
            "                    \"description\": \"\",\r\n" + 
            "                    \"type\": \"boolean\"\r\n" + 
            "                },\r\n" + 
            "                \"enum-property\": {\r\n" + 
            "                    \"description\": \"\",\r\n" + 
            "                    \"enum\": [\r\n" + 
            "                        \"val1\",\r\n" + 
            "                        \"val2\",\r\n" + 
            "                        \"val3\"\r\n" + 
            "                    ],\r\n" + 
            "                    \"type\": \"string\"\r\n" + 
            "                },\r\n" + 
            "                \"ref-property\": {\r\n" + 
            "                    \"$ref\": \"#/definitions/ExampleObject\",\r\n" + 
            "                    \"description\": \"\"\r\n" + 
            "                },\r\n" + 
            "                \"array-property\": {\r\n" + 
            "                    \"description\": \"\",\r\n" + 
            "                    \"type\": \"array\",\r\n" + 
            "                    \"items\": {\r\n" + 
            "                        \"type\": \"string\"\r\n" + 
            "                    }\r\n" + 
            "                }\r\n" + 
            "            }\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "}";

    @Test
    public void testFromSchema() {
        Oas20Document doc = (Oas20Document) Library.readDocumentFromJSONString(OPENAPI_20_DATA);
        Oas20SchemaDefinition definition = doc.definitions.getDefinition("ExampleObject");
        Assert.assertNotNull(definition);
        SimplifiedType simplifiedType = SimplifiedType.fromSchema(definition);
        assertSimplifiedType(simplifiedType, false, false, false, false, false, null, null, null);

        definition = doc.definitions.getDefinition("Examples");
        // Array
        OasSchema property = definition.getProperty("array-property");
        simplifiedType = SimplifiedType.fromSchema(property);
        assertSimplifiedType(simplifiedType, false, true, false, false, false, "array", null, null);
        Assert.assertNotNull(simplifiedType.of);
        Assert.assertEquals("string", simplifiedType.of.type);
        // Boolean
        property = definition.getProperty("boolean-property");
        simplifiedType = SimplifiedType.fromSchema(property);
        assertSimplifiedType(simplifiedType, false, false, false, false, true, "boolean", null, null);
        // Enum
        property = definition.getProperty("enum-property");
        simplifiedType = SimplifiedType.fromSchema(property);
        List<Object> vals = new ArrayList<>(3);
        vals.add("val1");
        vals.add("val2");
        vals.add("val3");
        assertSimplifiedType(simplifiedType, false, false, true, false, true, "string", null, vals);
        // Number
        property = definition.getProperty("number-property");
        simplifiedType = SimplifiedType.fromSchema(property);
        assertSimplifiedType(simplifiedType, false, false, false, false, true, "number", null, null);
        // Ref
        property = definition.getProperty("ref-property");
        simplifiedType = SimplifiedType.fromSchema(property);
        assertSimplifiedType(simplifiedType, true, false, false, false, false, "#/definitions/ExampleObject", null, null);
        // String
        property = definition.getProperty("string-property");
        simplifiedType = SimplifiedType.fromSchema(property);
        assertSimplifiedType(simplifiedType, false, false, false, false, true, "string", null, null);
    }
    
    @Test
    public void testFromParameter() {
        Oas20Document doc = (Oas20Document) Library.readDocumentFromJSONString(OPENAPI_20_DATA);
        
        
        // Testing fromParameter on SimplifiedType
        Oas20Parameter parameter = (Oas20Parameter) doc.paths.getPathItem("/").getParameter("query", "array-parameter");
        SimplifiedType simplifiedType = SimplifiedType.fromParameter(parameter);
        assertSimplifiedType(simplifiedType, false, true, false, false, false, "array", null, null);

        parameter = (Oas20Parameter) doc.paths.getPathItem("/").getParameter("query", "enum-parameter");
        simplifiedType = SimplifiedType.fromParameter(parameter);
        List<Object> vals = new ArrayList<>(3);
        vals.add("val1");
        vals.add("val2");
        vals.add("val3");
        assertSimplifiedType(simplifiedType, false, false, true, false, true, "string", null, vals);

        parameter = (Oas20Parameter) doc.paths.getPathItem("/").getParameter("query", "file-parameter");
        simplifiedType = SimplifiedType.fromParameter(parameter);
        assertSimplifiedType(simplifiedType, false, false, false, true, false, "file", null, null);

        parameter = (Oas20Parameter) doc.paths.getPathItem("/").getParameter("query", "number-parameter");
        simplifiedType = SimplifiedType.fromParameter(parameter);
        assertSimplifiedType(simplifiedType, false, false, false, false, true, "number", "float", null);

        parameter = (Oas20Parameter) doc.paths.getPathItem("/").getParameter("query", "string-parameter");
        simplifiedType = SimplifiedType.fromParameter(parameter);
        assertSimplifiedType(simplifiedType, false, false, false, false, true, "string", "date-time", null);
        
        
        // Testing fromParameter on SimplifiedParameterType
        parameter = (Oas20Parameter) doc.paths.getPathItem("/").getParameter("query", "array-parameter");
        SimplifiedParameterType paramType = SimplifiedParameterType.fromParameter((OasParameter) parameter);
        assertSimplifiedType(paramType, false, true, false, false, false, "array", null, null);
        Assert.assertEquals("#required", paramType.required, null);

        parameter = (Oas20Parameter) doc.paths.getPathItem("/").getParameter("query", "enum-parameter");
        paramType = SimplifiedParameterType.fromParameter((OasParameter) parameter);
        assertSimplifiedType(paramType, false, false, true, false, true, "string", null, vals);
        Assert.assertEquals("#required", paramType.required, null);

        parameter = (Oas20Parameter) doc.paths.getPathItem("/").getParameter("query", "file-parameter");
        paramType = SimplifiedParameterType.fromParameter((OasParameter) parameter);
        assertSimplifiedType(paramType, false, false, false, true, false, "file", null, null);
        Assert.assertEquals("#required", paramType.required, null);

        parameter = (Oas20Parameter) doc.paths.getPathItem("/").getParameter("query", "number-parameter");
        paramType = SimplifiedParameterType.fromParameter((OasParameter) parameter);
        assertSimplifiedType(paramType, false, false, false, false, true, "number", "float", null);
        Assert.assertEquals("#required", paramType.required, Boolean.TRUE);

        parameter = (Oas20Parameter) doc.paths.getPathItem("/").getParameter("query", "string-parameter");
        paramType = SimplifiedParameterType.fromParameter((OasParameter) parameter);
        assertSimplifiedType(paramType, false, false, false, false, true, "string", "date-time", null);
        Assert.assertEquals("#required", paramType.required, Boolean.TRUE);
    }
    
    @Test
    public void testFromPropertySchema() {
        Oas20Document doc = (Oas20Document) Library.readDocumentFromJSONString(OPENAPI_20_DATA);
        Oas20SchemaDefinition definition = doc.definitions.getDefinition("Examples");

        // Array
        SimplifiedPropertyType propType = SimplifiedPropertyType.fromPropertySchema((IPropertySchema) definition.getProperty("array-property"));
        assertSimplifiedType(propType, false, true, false, false, false, "array", null, null);
        Assert.assertFalse("#required", propType.required);
        // Boolean
        propType = SimplifiedPropertyType.fromPropertySchema((IPropertySchema) definition.getProperty("boolean-property"));
        assertSimplifiedType(propType, false, false, false, false, true, "boolean", null, null);
        Assert.assertTrue("#required", propType.required);
        // Enum
        propType = SimplifiedPropertyType.fromPropertySchema((IPropertySchema) definition.getProperty("enum-property"));
        List<Object> vals = new ArrayList<>(3);
        vals.add("val1");
        vals.add("val2");
        vals.add("val3");
        assertSimplifiedType(propType, false, false, true, false, true, "string", null, vals);
        Assert.assertFalse("#required", propType.required);
        // Number
        propType = SimplifiedPropertyType.fromPropertySchema((IPropertySchema) definition.getProperty("number-property"));
        assertSimplifiedType(propType, false, false, false, false, true, "number", null, null);
        Assert.assertTrue("#required", propType.required);
        // Ref
        propType = SimplifiedPropertyType.fromPropertySchema((IPropertySchema) definition.getProperty("ref-property"));
        assertSimplifiedType(propType, true, false, false, false, false, "#/definitions/ExampleObject", null, null);
        Assert.assertFalse("#required", propType.required);
        // String
        propType = SimplifiedPropertyType.fromPropertySchema((IPropertySchema) definition.getProperty("string-property"));
        assertSimplifiedType(propType, false, false, false, false, true, "string", null, null);
        Assert.assertFalse("#required", propType.required);

    }

    private void assertSimplifiedType(SimplifiedType schema, boolean isRef, boolean isArray, boolean isEnum, 
            boolean isFile, boolean isSimpleType, String type, String as, List<Object> enums) {
        Assert.assertNotNull(schema);
        Assert.assertEquals("#isRef", isRef, schema.isRef());
        Assert.assertEquals("#isArray", isArray, schema.isArray());
        Assert.assertEquals("#isEnum", isEnum, schema.isEnum());
        Assert.assertEquals("#isFile", isFile, schema.isFileType());
        Assert.assertEquals("#isSimpleType", isSimpleType, schema.isSimpleType());
        Assert.assertEquals("#type", type, schema.type);
        Assert.assertEquals("#as", as, schema.as);
        Assert.assertEquals("#enums", enums, schema.enum_);
    }

}
