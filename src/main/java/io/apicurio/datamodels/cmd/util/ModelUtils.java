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

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.compat.RegexCompat;
import io.apicurio.datamodels.core.models.Extension;

/**
 * @author eric.wittmann@gmail.com
 */
public class ModelUtils {

    /**
     * Returns true if the given object is null or undefined.  Obviously Java does not have the concept
     * of "undefined" but this must also support the case where this code is running in Javascript.
     * @param object
     */
    public static boolean isNullOrUndefined(Object object) {
        return NodeCompat.isNullOrUndefined(object);
    }

    /**
     * The opposite of isNullOrUndefined.
     * @param object
     */
    public static boolean isDefined(Object object) {
        return NodeCompat.isDefined(object);
    }

    /**
     * Detects the appropriate path parameter names from a path.  For example, if the
     * string "/resources/{fooId}/subresources/{barId}" is passed in, the following
     * string array will be returned:  [ "fooId", "barId" ]
     * @param path
     */
    public static List<String> detectPathParamNames(String path) {
        List<String> paramNames = new ArrayList<>();
        List<String[]> matches = RegexCompat.findMatches(path, "\\{([^\\}]+)\\}");
        for (String[] match : matches) {
            String name = match[1];
            paramNames.add(name.trim());
        }
        return paramNames;
    }

    public static Object marshalExtension(Extension extension) {
        Object wrapper = JsonCompat.objectNode();
        JsonCompat.setProperty(wrapper, extension.name, extension.value);
        return wrapper;
    }

    public static void unmarshalExtension(Object oldExtension, Extension extension) {
        String name = JsonCompat.keys(oldExtension).get(0);
        Object value = JsonCompat.getPropertyObject(oldExtension, name);
        extension.name = name;
        extension.value = value;
    }
}
