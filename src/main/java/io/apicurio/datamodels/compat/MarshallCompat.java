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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.SimpleType;

import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.cmd.commands.CommandFactory;
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * Compatibility layer for marshalling and unmarshalling commands.
 * @author eric.wittmann@gmail.com
 */
public class MarshallCompat {
    
    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(NodePath.class, new NodePathSerializer());
        module.addSerializer(SimplifiedType.class, new SimplifiedTypeSerializer());
        module.addDeserializer(NodePath.class, new NodePathDeserializer());
        module.addDeserializer(SimplifiedType.class, new SimplifiedTypeDeserializer());
        mapper.registerModule(module);
    }

    /**
     * Marshal a command to a JS object.
     * @param command
     */
    public static Object marshallCommand(ICommand command) {
        JsonNode rval = mapper.valueToTree(command);
        JsonCompat.setPropertyString(rval, Constants.PROP___TYPE, command.type());
        return rval;
    }

    /**
     * Unmarshal a command by reading from the provided JS object data.
     * @param from
     */
    public static ICommand unmarshallCommand(Object from) {
        String type = JsonCompat.consumePropertyString(from, Constants.PROP___TYPE);
        if (type == null) {
            throw new RuntimeException("Missing __type from source data.");
        }
        
        try {
            Class<? extends ICommand> cmdClass = CommandFactory.create(type).getClass();
            ICommand cmd = mapper.treeToValue((TreeNode) from, cmdClass);
            return cmd;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error unmarshalling command: " + type, e);
        } catch (NullPointerException e) {
            throw new RuntimeException("Missing command from unmarshalling factory: " + type, e);
        }
    }

    /* Some custom serializers/deserializers for marshalling and unmarshalling commands using jackson */
    
    public static class NodePathSerializer extends JsonSerializer<NodePath> {
        /**
         * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
         */
        @Override
        public void serialize(NodePath value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeString(value.toString());
        }
    }
    
    public static class NodePathDeserializer extends JsonDeserializer<NodePath> {
        /**
         * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
         */
        @Override
        public NodePath deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            NodePath rval = null;
            String path = p.getText();
            if (path != null) {
                rval = new NodePath(path);
            }
            return rval;
        }
    }
    
    public static class SimplifiedTypeSerializer extends JsonSerializer<SimplifiedType> {
        /**
         * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
         */
        @Override
        public void serialize(SimplifiedType value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            // TODO Auto-generated method stub
            throw new IOException("Not yet implemented.");
        }
    }
    
    public static class SimplifiedTypeDeserializer extends JsonDeserializer<SimplifiedType> {
        /**
         * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
         */
        @Override
        public SimplifiedType deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            // TODO Auto-generated method stub
            throw new IOException("Not yet implemented.");
        }
    }
    
    public static class CommandListSerializer extends JsonSerializer<List<ICommand>> {

        /**
         * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
         */
        @Override
        public void serialize(List<ICommand> value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeStartArray(value.size());
            try {
                for (ICommand command : value) {
                    Object c = MarshallCompat.marshallCommand(command);
                    JsonSerializer<Object> cmdSerializer = serializers.findValueSerializer(c.getClass());
                    cmdSerializer.serialize(c, gen, serializers);
                }
            } catch (JsonMappingException e) {
                throw new IOException(e);
            }
            gen.writeEndArray();
        }
        
    }
    
    public static class CommandListDeserializer extends JsonDeserializer<List<ICommand>> {

        /**
         * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
         */
        @Override
        public List<ICommand> deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            List<ICommand> commands = new ArrayList<>();
            boolean done = false;
            while (!done) {
                JsonToken token = p.nextToken();
                if (token == JsonToken.END_ARRAY) {
                    done = true;
                } else {
                    JsonDeserializer<Object> deserializer = ctxt.findNonContextualValueDeserializer(SimpleType.constructUnsafe(JsonNode.class));
                    TreeNode value = (TreeNode) deserializer.deserialize(p, ctxt);
                    ICommand cmd = MarshallCompat.unmarshallCommand(value);
                    commands.add(cmd);
                }
            }
            return commands;
        }
        
    }

}
