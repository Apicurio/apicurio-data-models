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
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.SimpleType;

import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.cmd.commands.CommandFactory;
import io.apicurio.datamodels.cmd.models.SimplifiedParameterType;
import io.apicurio.datamodels.cmd.models.SimplifiedPropertyType;
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
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
        module.addSerializer(SimplifiedType.class, new SimplifiedTypeSerializer<SimplifiedType>(SerializerFlavor.base));
        module.addSerializer(SimplifiedParameterType.class, new SimplifiedTypeSerializer<SimplifiedParameterType>(SerializerFlavor.parameter));
        module.addSerializer(SimplifiedPropertyType.class, new SimplifiedTypeSerializer<SimplifiedPropertyType>(SerializerFlavor.property));
        module.addDeserializer(NodePath.class, new NodePathDeserializer());
        module.addDeserializer(SimplifiedType.class, new SimplifiedTypeDeserializer<SimplifiedType>(SerializerFlavor.base));
        module.addDeserializer(SimplifiedParameterType.class, new SimplifiedTypeDeserializer<SimplifiedParameterType>(SerializerFlavor.parameter));
        module.addDeserializer(SimplifiedPropertyType.class, new SimplifiedTypeDeserializer<SimplifiedPropertyType>(SerializerFlavor.property ));
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


    /**
     * Marshalls the given simple type into a JS object.
     * @param sType
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Object marshallSimplifiedType(SimplifiedType sType) {
        if (ModelUtils.isNullOrUndefined(sType)) {
            return null;
        }
        Object obj = JsonCompat.objectNode();
        JsonCompat.setPropertyString(obj, Constants.PROP_TYPE, sType.type);
        // TODO convert from List<Object> to List<String> here rather than coercing it
        JsonCompat.setPropertyStringArray(obj, Constants.PROP_ENUM, (List) sType.enum_);
        JsonCompat.setProperty(obj, Constants.PROP_OF, marshallSimplifiedType(sType.of));
        JsonCompat.setPropertyString(obj, Constants.PROP_AS, sType.as);
        return obj;
    }

    /**
     * Unmarshalls a simple type back into a JS object.
     * @param from
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static SimplifiedType unmarshallSimplifiedType(Object from) {
        if (ModelUtils.isNullOrUndefined(from)) {
            return null;
        }
        SimplifiedType type = new SimplifiedType();
        type.type = JsonCompat.getPropertyString(from, Constants.PROP_TYPE);
        // TODO convert from List<String> to List<Object> here rather than coercing it
        type.enum_ = (List) JsonCompat.getPropertyStringArray(from, Constants.PROP_ENUM);
        type.of = MarshallCompat.unmarshallSimplifiedType(JsonCompat.getProperty(from, Constants.PROP_OF));
        type.as = JsonCompat.getPropertyString(from, Constants.PROP_AS);
        return type;
    }

    /**
     * Marshalls the given simple type into a JS object.
     * @param sType
     */
    private static Object marshallSimplifiedParameterType(SimplifiedParameterType sType) {
        if (ModelUtils.isNullOrUndefined(sType)) {
            return null;
        }
        Object obj = marshallSimplifiedType(sType);
        JsonCompat.setPropertyBoolean(obj, Constants.PROP_REQUIRED, sType.required);
        return obj;
    }

    /**
     * Unmarshalls a simple parameter type back into a JS object.
     * @param from
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static SimplifiedParameterType unmarshallSimplifiedParameterType(Object from) {
        if (ModelUtils.isNullOrUndefined(from)) {
            return null;
        }
        SimplifiedParameterType type = new SimplifiedParameterType();
        type.type = JsonCompat.getPropertyString(from, Constants.PROP_TYPE);
        // TODO convert from List<String> to List<Object> here rather than coercing it
        type.enum_ = (List) JsonCompat.getPropertyStringArray(from, Constants.PROP_ENUM);
        type.of = MarshallCompat.unmarshallSimplifiedType(JsonCompat.getProperty(from, Constants.PROP_OF));
        type.as = JsonCompat.getPropertyString(from, Constants.PROP_AS);
        Boolean req = JsonCompat.getPropertyBoolean(from, Constants.PROP_REQUIRED);
        if (req != null) {
            type.required = req;
        }
        return type;
    }

    /**
     * Marshalls the given simple type into a JS object.
     * @param sType
     */
    private static Object marshallSimplifiedPropertyType(SimplifiedPropertyType sType) {
        if (ModelUtils.isNullOrUndefined(sType)) {
            return null;
        }
        Object obj = marshallSimplifiedType(sType);
        JsonCompat.setPropertyBoolean(obj, Constants.PROP_REQUIRED, sType.required);
        return obj;
    }

    /**
     * Unmarshalls a simple parameter type back into a JS object.
     * @param from
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static SimplifiedPropertyType unmarshallSimplifiedPropertyType(Object from) {
        if (ModelUtils.isNullOrUndefined(from)) {
            return null;
        }
        SimplifiedPropertyType type = new SimplifiedPropertyType();
        type.type = JsonCompat.getPropertyString(from, Constants.PROP_TYPE);
        // TODO convert from List<String> to List<Object> here rather than coercing it
        type.enum_ = (List) JsonCompat.getPropertyStringArray(from, Constants.PROP_ENUM);
        type.of = MarshallCompat.unmarshallSimplifiedType(JsonCompat.getProperty(from, Constants.PROP_OF));
        type.as = JsonCompat.getPropertyString(from, Constants.PROP_AS);
        Boolean req = JsonCompat.getPropertyBoolean(from, Constants.PROP_REQUIRED);
        if (req != null) {
            type.required = req;
        }
        return type;
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
    
    public static enum SerializerFlavor {
        base, parameter, property
    }
    
    public static class SimplifiedTypeSerializer<T extends SimplifiedType> extends JsonSerializer<T> {

        private SerializerFlavor flavor;
        
        /**
         * Constructor.
         */
        public SimplifiedTypeSerializer(SerializerFlavor flavor) {
            this.flavor = flavor;
        }
        
        /**
         * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
         */
        @Override
        public void serialize(T value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            Object c;
            if (this.flavor == SerializerFlavor.parameter) {
                c = MarshallCompat.marshallSimplifiedParameterType((SimplifiedParameterType)value);
            } else if (this.flavor == SerializerFlavor.property) {
                c = MarshallCompat.marshallSimplifiedPropertyType((SimplifiedPropertyType)value);
            } else {
                c = MarshallCompat.marshallSimplifiedType(value);
            }
            JsonSerializer<Object> cmdSerializer = serializers.findValueSerializer(c.getClass());
            cmdSerializer.serialize(c, gen, serializers);
        }
    }
    
    public static class SimplifiedTypeDeserializer<T extends SimplifiedType> extends JsonDeserializer<T> {

        private SerializerFlavor flavor;
        
        /**
         * Constructor.
         */
        public SimplifiedTypeDeserializer(SerializerFlavor flavor) {
            this.flavor = flavor;
        }
        
        /**
         * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
         */
        @SuppressWarnings("unchecked")
        @Override
        public T deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            JsonDeserializer<Object> deserializer = ctxt.findNonContextualValueDeserializer(SimpleType.constructUnsafe(JsonNode.class));
            TreeNode value = (TreeNode) deserializer.deserialize(p, ctxt);
            if (this.flavor == SerializerFlavor.property) {
                return (T) MarshallCompat.unmarshallSimplifiedPropertyType(value);
            } else if (this.flavor == SerializerFlavor.parameter) {
                return (T) MarshallCompat.unmarshallSimplifiedParameterType(value);
            } else {
                return (T) MarshallCompat.unmarshallSimplifiedType(value);
            }
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
    
    public static class NullableJsonNodeDeserializer extends JsonNodeDeserializer {
        private static final long serialVersionUID = -3295567082289568935L;

        /**
         * @see com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer#getNullValue(com.fasterxml.jackson.databind.DeserializationContext)
         */
        @Override
        public JsonNode getNullValue(DeserializationContext ctxt) {
            return null;
        }
    }

}
