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

/**
 * Represents a single segment of a node path.
 * @author eric.wittmann@gmail.com
 */
public class NodePointerSegment {

    private final String value;

    public static NodePointerSegment parse(String encodedValue) {
        return new NodePointerSegment(unescapePathSegmentValue(encodedValue));
    }

    /**
     * Constructor.
     * @param value
     */
    public NodePointerSegment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String _value = escapePathSegmentValue(value);
        builder.append(_value);
        return builder.toString();
    }

    /**
     * When a path is represented as a string, we are using the '/' character as the
     * separator.  Therefore, slashes need to be escaped.  Slashes are encoded as '~1'.  Since
     * that means the '~' character also has special meaning, it also needs to be encoded
     * (as '~0').
     *
     * - '~0' = '~'
     * - '~1' = '/'
     */
    private static String escapePathSegmentValue(String rawValue) {
        // order is important
        return rawValue.replace("~", "~0").replace("/", "~1");
    }

    private static String unescapePathSegmentValue(String escapedValue) {
        // order is important
        return escapedValue.replace("~1", "/").replace("~0", "~");
    }

}
