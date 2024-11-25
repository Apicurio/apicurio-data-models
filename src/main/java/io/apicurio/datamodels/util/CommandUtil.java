package io.apicurio.datamodels.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.paths.NodePath;

import java.io.IOException;

public class CommandUtil {

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
                rval = NodePath.parse(path);
            }
            return rval;
        }
    }

    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(NodePath.class, new NodePathDeserializer());
        mapper.registerModule(module);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ICommand create(String cmdType) {
        try {
            String cmdFQCN = "io.apicurio.datamodels.cmd.commands." + cmdType;
            Class<?> aClass = Class.forName(cmdFQCN);
            return (ICommand) aClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ICommand unmarshall(ObjectNode from) {
        String type = from.get("__type").asText();
        if (type == null) {
            throw new RuntimeException("Missing __type from source data.");
        }

        try {
            Class<? extends ICommand> cmdClass = create(type).getClass();
            ICommand cmd = mapper.treeToValue((TreeNode) from, cmdClass);
            return cmd;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error unmarshalling command: " + type, e);
        } catch (NullPointerException e) {
            throw new RuntimeException("Missing command from unmarshalling factory: " + type, e);
        }

    }

}
