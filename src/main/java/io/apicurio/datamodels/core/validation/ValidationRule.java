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

package io.apicurio.datamodels.core.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.compat.RegexCompat;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Base class for all validation rule implementations.
 * @author eric.wittmann@gmail.com
 */
public abstract class ValidationRule extends CombinedAllNodeVisitor implements IVisitor {

    /**
     * List of valid HTTP response status codes from:  https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml
     */
    private static List<String> HTTP_STATUS_CODES;
    {
        HTTP_STATUS_CODES = new ArrayList<>(Arrays.asList(new String[] {
            "100", "101", "102", "1XX", "10X",
            "200", "201", "202", "203", "204", "205", "206", "207", "208", "226", "2XX", "20X", "21X", "22X",
            "300", "301", "302", "303", "304", "305", "306", "307", "308", "3XX", "30X",
            "400", "401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413", "414", "415", "416", "417", "4XX", "40X", "41X",
            "421", "422", "423", "424", "426", "427", "428", "429", "431", "451", "42X", "43X", "44X", "45X",
            "500", "501", "502", "503", "504", "505", "506", "507", "508", "510", "511", "5XX", "50X", "51X"
        }));
    };

    private static String PATH_MATCH_REGEX = "^(\\/[^{}\\/]*(\\{[a-zA-Z_][0-9a-zA-Z_\\-\\.]*\\})?(\\.\\{[a-zA-Z_][0-9a-zA-Z_\\-\\.]*\\})?)+$";
    private static String SEG_MATCH_REGEX = "[\\/\\.]([^{}\\/]*)(\\{([a-zA-Z_][0-9a-zA-Z_\\-\\.]*)\\})?";
    private static String URL_MATCH_REGEX = "^(?!mailto:)(?:(?:http|https|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?:(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[0-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))|localhost)(?::\\d{2,5})?(?:(/|\\?|#)[^\\s]*)?$";
    private static String EMAIL_MATCH_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static String MIME_TYPE_MATCH_REGEX = "^.*\\/.*(;.*)?$";

    private IValidationProblemReporter reporter;
    private ValidationRuleMetaData ruleInfo;
    
    /**
     * Constructor.
     * @param ruleInfo
     */
    public ValidationRule(ValidationRuleMetaData ruleInfo) {
        this.ruleInfo = ruleInfo;
    }
    
    /**
     * Sets the validation problem reporter.
     * @param reporter
     */
    public void setReporter(IValidationProblemReporter reporter) {
        this.reporter = reporter;
    }

    /**
     * Called by validation rules to report an error.
     * @param node
     * @param property
     * @param messageParams
     */
    protected void report(Node node, String property, Map<String, String> messageParams) {
        String messageTemplate = this.ruleInfo.messageTemplate;
        String message = this.resolveMessage(messageTemplate, messageParams);
        reporter.report(ruleInfo, node, property, message);
    }

    /**
     * Creates a message from a template and set of params.
     * @param messageTemplate
     * @param messageParams
     */
    private String resolveMessage(String messageTemplate, Map<String, String> messageParams) {
        if (messageParams == null || messageParams.size() == 0) {
            return messageTemplate;
        }

        List<String[]> matches = RegexCompat.findMatches(messageTemplate, "\\$\\{\\s*'\\s*([a-zA-Z0-9]+)\\s*'\\s*\\}");
        if (matches.size() == 0) {
            return messageTemplate;
        }
        String rval = messageTemplate;
        for (String[] match : matches) {
            String mval = match[0];
            String paramName = match[1];
            String paramValue = messageParams.get(paramName);
            int start = rval.indexOf(mval);
            int end = start + mval.length();
            rval = rval.substring(0, start) + paramValue + rval.substring(end);
        }
        
        return rval;
    }

    /**
     * Reports a validation error if the property is not valid.
     * @param isValid
     * @param node
     * @param property
     * @param messageParams
     */
    protected void reportIfInvalid(boolean isValid, Node node, String property, Map<String, String> messageParams) {
        if (!isValid) {
            this.report(node, property, messageParams);
        }
    }

    /**
     * Reports a validation error if the given condition is true.
     * @param condition
     * @param node
     * @param property
     * @param messageParams
     */
    protected void reportIf(boolean condition, Node node, String property, Map<String, String> messageParams) {
        if (condition) {
            this.report(node, property, messageParams);
        }
    }

    /**
     * Utility function to report path related errors.
     * @param node
     * @param messageParams
     */
    protected void reportPathError(Node node, Map<String, String> messageParams) {
        this.report(node, null, messageParams);
    }

    /**
     * Check if a property was defined.
     * @param propertyValue
     * @return {boolean}
     */
    protected boolean isDefined(Object propertyValue) {
        return !NodeCompat.isNullOrUndefined(propertyValue);
    }

    /**
     * Check if the property value exists (is not undefined and is not null).
     * @param propertyValue
     * @return {boolean}
     */
    protected boolean hasValue(Object propertyValue) {
        return isDefined(propertyValue);
    }

    /**
     * Checks the path template against the regular expression and returns match result.
     *
     * @param pathTemplate
     * @return {boolean}
     */
    protected boolean isPathWellFormed(String pathTemplate) {
        return RegexCompat.matches(pathTemplate, PATH_MATCH_REGEX);
    }
    
    /**
     * Checks if the given location is a valid AsyncAPI expression.  See the spec:
     *     https://www.asyncapi.com/docs/specifications/2.0.0#runtimeExpression
     * 
     * @param location
     */
    protected boolean isValidAsyncApiExpression(String location) {
        // TODO must be a valid AsyncAPI "expression"
        return false;
    }

    /**
     * Finds all occurences of path segment patterns in a path template.
     *
     * @param pathTemplate
     * @return {PathSegment[]}
     */
    protected List<PathSegment> getPathSegments(String pathTemplate) {
        List<PathSegment> pathSegments = new ArrayList<>();
        
        // If path is root '/', simply return empty segments
        if (pathTemplate == null || "".equals(pathTemplate)) {
            return pathSegments;
        }
        
        String normalizedPath = pathTemplate;
        
        // Remove the trailing slash if the path ends with slash
        if (pathTemplate.lastIndexOf("/") == pathTemplate.length() - 1) {
            normalizedPath = pathTemplate.substring(0, pathTemplate.length() - 1);
        }
        
        // Look for all occurrence of string like {param1}
        int segId = 0;

        List<String[]> matches = RegexCompat.findMatches(normalizedPath, SEG_MATCH_REGEX);
        for (String[] mi : matches) {
            PathSegment pathSegment = new PathSegment();
            pathSegment.segId = segId;
            pathSegment.prefix = mi[1];
            // parameter name is inside the curly braces (group 3)
            String fn = mi[3];
            if (fn != null && !fn.trim().isEmpty()) {
                pathSegment.formalName = fn;
                pathSegment.normalizedName = "__param__" + segId;
            }
            pathSegments.add(pathSegment);
            segId++;
        }
        return pathSegments;

    }

    /**
     * Check if a value is either null or undefined.
     * @param value
     * @return {boolean}
     */
    protected boolean isNullOrUndefined(Object value) {
        return NodeCompat.isNullOrUndefined(value);
    }

    /**
     * Returns true only if the given value is a valid URL.
     * @param propertyValue
     * @return {boolean}
     */
    protected boolean isValidUrl(String propertyValue) {
        return propertyValue.length() < 2083 && RegexCompat.matches(propertyValue, URL_MATCH_REGEX);
    }

    /**
     * Returns true only if the given value is a valid URL template.
     * @param propertyValue
     */
    protected boolean isValidUrlTemplate(String propertyValue) {
        // TODO is there a regular expression we can use to validate a URL template??
        return true;
    }

    /**
     * Returns true only if the given value is valid GFM style markup.
     * @param propertyValue
     */
    protected boolean isValidGFM(String propertyValue) {
        // TODO implement a regexp to test for a valid Github Flavored Markdown string
        return true;
    }

    /**
     * Returns true only if the given value is valid CommonMark style markup.
     * @param propertyValue
     */
    protected boolean isValidCommonMark(String propertyValue) {
        // TODO implement a regexp to test for a valid CommonMark string
        return true;
    }

    /**
     * Returns true only if the given value is a valid email address.
     * @param propertyValue
     */
    protected boolean isValidEmailAddress(String propertyValue) {
        return RegexCompat.matches(propertyValue, EMAIL_MATCH_REGEX);
    }

    /**
     * Returns true only if the given value is a valid mime-type.
     * @param propertyValue
     */
    protected boolean isValidMimeType(List<String> propertyValue) {
        for (String v : propertyValue) {
            if (!RegexCompat.matches(v, MIME_TYPE_MATCH_REGEX)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the given value is an item in the enum list.
     * @param value
     * @param items
     */
    protected boolean isValidEnumItem(String value, String[] items) {
        for (String item : items) {
            if (item != null && item.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the given value is valid according to the schema provided.
     * @param value
     * @param node
     */
    protected boolean isValidForType(Object value, Node node) {
        // TODO validate the value against the schema
        // Note: the "node" argument must be "Schema-Like" - Schema, Header, Parameter, Items, etc.
        return true;
    }

    /**
     * Returns true if the given status code is a valid HTTP response code.
     * @param statusCode
     */
    protected boolean isValidHttpCode(String statusCode) {
        return HTTP_STATUS_CODES.indexOf(statusCode) != -1;
    }

    /**
     * Turns a list of args into a map suitable for use as template arguments.  The args must come 
     * in pairs of "key, value".  For example:
     * 
     *   map("key1", "value1", "key2", "true");
     *   
     * That would return a {@link Map} with two mappings.
     */
    protected Map<String, String> map(String ...args) {
        if (args == null || args.length < 2) {
            return null;
        }
        Map<String, String> rval = new HashMap<>();
        for (int idx = 0; idx < args.length; idx+=2) {
            String key = args[idx];
            String value = args[idx+1];
            rval.put(key, value);
        }
        return rval;
    }
    
    /**
     * Creates an array.
     * @param args
     */
    protected String[] array(String ...args) {
        return args;
    }
    
    /**
     * Returns true if the two values are equal.
     * @param value1
     * @param value2
     */
    protected boolean equals(Object value1, Object value2) {
        return NodeCompat.equals(value1, value2);
    }
    
    /**
     * Type encapsulating information about a path segment.
     * Consumers of this type should not rely on normalizedName property which is only provided to weed out duplicates.
     */
    public static class PathSegment {
        public int segId;
        public String prefix;
        public String formalName;
        public String normalizedName;
    }

}
