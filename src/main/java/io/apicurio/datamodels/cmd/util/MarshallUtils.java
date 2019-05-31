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

package io.apicurio.datamodels.cmd.util;

import java.util.List;

import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.cmd.models.SimplifiedParameterType;
import io.apicurio.datamodels.cmd.models.SimplifiedPropertyType;
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * @author eric.wittmann@gmail.com
 */
public class MarshallUtils {

    /**
     * Marshalls the given command into a JS object and returns it.
     */
    public static Object marshallCommand(ICommand command) {
        return command.marshall();
    }

    /**
     * Unmarshalls the given JS object into a command and returns it.
     * @param object
     */
    public static ICommand unmarshallCommand(Object from) {
        String cmdType = JsonCompat.getPropertyString(from, Constants.PROP___TYPE);
        ICommand cmd = MarshallCommandFactory.create(cmdType);
        if (cmd == null) {
            throw new RuntimeException("No unmarshalling factory found for command type: " + cmdType);
        }
        cmd.unmarshall(from);
        return cmd;
    }

    /**
     * Marshalls the given node path into a JS string.
     */
    public static String marshallNodePath(NodePath nodePath) {
        if (ModelUtils.isNullOrUndefined(nodePath)) {
            return null;
        }
        return nodePath.toString();
    }

    /**
     * Unmarshalls a node path back into an instance of OasNodePath.
     * @param path
     */
    public static NodePath unmarshallNodePath(String path) {
        if (ModelUtils.isNullOrUndefined(path)) {
            return null;
        }
        NodePath nodePath = new NodePath(path);
        return nodePath;
    }

    /**
     * Marshalls the given simple type into a JS object.
     * @param sType
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Object marshallSimplifiedType(SimplifiedType sType) {
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
    public static SimplifiedType unmarshallSimplifiedType(Object from) {
        if (ModelUtils.isNullOrUndefined(from)) {
            return null;
        }
        SimplifiedType type = new SimplifiedType();
        type.type = JsonCompat.getPropertyString(from, Constants.PROP_TYPE);
        // TODO convert from List<String> to List<Object> here rather than coercing it
        type.enum_ = (List) JsonCompat.getPropertyStringArray(from, Constants.PROP_ENUM);
        type.of = MarshallUtils.unmarshallSimplifiedType(JsonCompat.getProperty(from, Constants.PROP_OF));
        type.as = JsonCompat.getPropertyString(from, Constants.PROP_AS);
        return type;
    }

    /**
     * Marshalls the given simple type into a JS object.
     * @param sType
     */
    public static Object marshallSimplifiedParameterType(SimplifiedParameterType sType) {
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
    public static SimplifiedParameterType unmarshallSimplifiedParameterType(Object from) {
        if (ModelUtils.isNullOrUndefined(from)) {
            return null;
        }
        SimplifiedParameterType type = new SimplifiedParameterType();
        type.type = JsonCompat.getPropertyString(from, Constants.PROP_TYPE);
        // TODO convert from List<String> to List<Object> here rather than coercing it
        type.enum_ = (List) JsonCompat.getPropertyStringArray(from, Constants.PROP_ENUM);
        type.of = MarshallUtils.unmarshallSimplifiedType(JsonCompat.getProperty(from, Constants.PROP_OF));
        type.as = JsonCompat.getPropertyString(from, Constants.PROP_AS);
        type.required = JsonCompat.getPropertyBoolean(from, Constants.PROP_REQUIRED);
        return type;
    }

    /**
     * Marshalls the given simple type into a JS object.
     * @param sType
     */
    public static Object marshallSimplifiedPropertyType(SimplifiedPropertyType sType) {
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
    public static SimplifiedPropertyType unmarshallSimplifiedPropertyType(Object from) {
        if (ModelUtils.isNullOrUndefined(from)) {
            return null;
        }
        SimplifiedPropertyType type = new SimplifiedPropertyType();
        type.type = JsonCompat.getPropertyString(from, Constants.PROP_TYPE);
        // TODO convert from List<String> to List<Object> here rather than coercing it
        type.enum_ = (List) JsonCompat.getPropertyStringArray(from, Constants.PROP_ENUM);
        type.of = MarshallUtils.unmarshallSimplifiedType(JsonCompat.getProperty(from, Constants.PROP_OF));
        type.as = JsonCompat.getPropertyString(from, Constants.PROP_AS);
        type.required = JsonCompat.getPropertyBoolean(from, Constants.PROP_REQUIRED);
        return type;
    }

}
