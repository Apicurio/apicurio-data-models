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

package io.apicurio.datamodels.validation.rules.invalid.type;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Schema;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Schema;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Schema;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Invalid Property Type validation rule.  This rule is responsible
 * for reporting whenever the **type** and **items** of a property fails to conform to the required
 * format defined by the specification
 * @author eric.wittmann@gmail.com
 */
public abstract class OasInvalidPropertyTypeValidationRule extends ValidationRule {

    protected static final String[] ALLOWED_TYPES = {"string", "number", "integer", "boolean", "array", "object"};
    protected static final String[] ALLOWED_TYPES31 = {"string", "number", "integer", "boolean", "array", "object", "null"};

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidPropertyTypeValidationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    public static void getTypes(Schema node, BiConsumer<List<String>, String[]> handler) {
        final String[] allowedTypes;
        final List<String> types;

        if (node instanceof OpenApi31Schema) {
            allowedTypes = ALLOWED_TYPES31;
            OpenApi31Schema schema = (OpenApi31Schema) node;
            types = Optional.of(schema)
                    .map(OpenApi31Schema::getType)
                    .map(type -> type.isString() ? Collections.singletonList(type.asString()) : type.asStringList())
                    .orElseGet(Collections::emptyList);
        } else if (node instanceof OpenApi30Schema) {
            allowedTypes = ALLOWED_TYPES;
            OpenApi30Schema schema = (OpenApi30Schema) node;
            types = Optional.of(schema)
                    .map(OpenApi30Schema::getType)
                    .map(Collections::singletonList)
                    .orElseGet(Collections::emptyList);
        } else if (node instanceof OpenApi20Schema) {
            allowedTypes = ALLOWED_TYPES;
            OpenApi20Schema schema = (OpenApi20Schema) node;
            types = Optional.of(schema)
                    .map(OpenApi20Schema::getType)
                    .map(Collections::singletonList)
                    .orElseGet(Collections::emptyList);
        } else {
            allowedTypes = null;
            types = Collections.emptyList();
        }

        handler.accept(types, allowedTypes);
    }
}
