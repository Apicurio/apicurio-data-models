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

import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v2.models.Oas20Items;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;

/**
 * @author eric.wittmann@gmail.com
 */
public class SimplifiedType {
    
    private static final List<String> VALID_TYPES = new ArrayList<>();
    static {
        VALID_TYPES.add("string");
        VALID_TYPES.add("number");
        VALID_TYPES.add("integer");
        VALID_TYPES.add("boolean");
    }
    
    public static SimplifiedType fromParameter(Oas20Parameter param) {
        SimplifiedType rval = new SimplifiedType();
        if (ModelUtils.isDefined(param) && ModelUtils.isDefined(param.enum_) && param.enum_.size() >= 0) {
            // Need to clone the enum values
            rval.enum_ = new ArrayList<>(param.enum_);
        }
        if (ModelUtils.isDefined(param) && ModelUtils.isDefined(param.type) && !NodeCompat.equals(param.type, "array") && !NodeCompat.equals(param.type, "object")) {
            rval.type = param.type;
            if (ModelUtils.isDefined(param.format)) {
                rval.as = param.format;
            }
        }
        if (ModelUtils.isDefined(param) && NodeCompat.equals(param.type, "array") && ModelUtils.isDefined(param.items)) {
            rval.type = "array";
            rval.of = SimplifiedType.fromItems(param.items);
        }
        return rval;
    }
    
    public static SimplifiedType fromItems(Oas20Items items) {
        SimplifiedType rval = new SimplifiedType();
        if (ModelUtils.isDefined(items) && ModelUtils.isDefined(items.enum_) && items.enum_.size() >= 0) {
            // Need to clone the enum values
            rval.enum_ = new ArrayList<>(items.enum_);
        }
        if (ModelUtils.isDefined(items) && ModelUtils.isDefined(items.type) && !NodeCompat.equals(items.type, "array") && !NodeCompat.equals(items.type, "object")) {
            rval.type = items.type;
            if (ModelUtils.isDefined(items.format)) {
                rval.as = items.format;
            }
        }
        if (ModelUtils.isDefined(items) && NodeCompat.equals(items.type, "array") && ModelUtils.isDefined(items.items)) {
            rval.type = "array";
            rval.of = SimplifiedType.fromItems(items.items);
        }
        return rval;
    }

    public static SimplifiedType fromSchema(OasSchema schema) {
        SimplifiedType rval = new SimplifiedType();
        if (ModelUtils.isDefined(schema) && ModelUtils.isDefined(schema.$ref)) {
            rval.type = schema.$ref;
        }
        if (ModelUtils.isDefined(schema) && ModelUtils.isDefined(schema.enum_) && schema.enum_.size() >= 0) {
            // Need to clone the enum values
            rval.enum_ = new ArrayList<>(schema.enum_);
        }
        if (ModelUtils.isDefined(schema) && ModelUtils.isDefined(schema.type) && !NodeCompat.equals(schema.type, "array") &&
            !NodeCompat.equals(schema.type, "object"))
        {
            rval.type = schema.type;
            if (ModelUtils.isDefined(schema.format)) {
                rval.as = schema.format;
            }
        }
        // TODO handle the case where .items is a list of schemas
        if (ModelUtils.isDefined(schema) && NodeCompat.equals(schema.type, "array") && ModelUtils.isDefined(schema.items)) {
            rval.type = "array";
            rval.of = SimplifiedType.fromSchema((OasSchema)schema.items);
        }
        return rval;
    }

    public static SimplifiedType fromAaiSchema(AaiSchema schema) {
        SimplifiedType rval = new SimplifiedType();
        if (ModelUtils.isDefined(schema) && ModelUtils.isDefined(schema.$ref)) {
            rval.type = schema.$ref;
        }
        if (ModelUtils.isDefined(schema) && ModelUtils.isDefined(schema.enum_) && schema.enum_.size() >= 0) {
            // Need to clone the enum values
            rval.enum_ = new ArrayList<>(schema.enum_);
        }
        if (ModelUtils.isDefined(schema) && ModelUtils.isDefined(schema.type) && !NodeCompat.equals(schema.type, "array") &&
                !NodeCompat.equals(schema.type, "object"))
        {
            rval.type = schema.type;
            if (ModelUtils.isDefined(schema.format)) {
                rval.as = schema.format;
            }
        }
        // TODO handle the case where .items is a list of schemas
        if (ModelUtils.isDefined(schema) && NodeCompat.equals(schema.type, "array") && ModelUtils.isDefined(schema.items)) {
            rval.type = "array";
            rval.of = SimplifiedType.fromSchema((OasSchema)schema.items);
        }
        return rval;
    }

    public String type;
    public List<Object> enum_;
    public SimplifiedType of;
    public String as;

    public boolean isSimpleType() {
        return VALID_TYPES.indexOf(this.type) != -1;
    }

    public boolean isFileType() {
        return NodeCompat.equals(this.type, "file");
    }

    public boolean isEnum() {
        return ModelUtils.isDefined(this.enum_) && this.enum_.size() >= 0;
    }

    public boolean isArray() {
        return NodeCompat.equals(this.type, "array");
    }

    public boolean isRef() {
        return ModelUtils.isDefined(this.type) && this.type.indexOf("#/") == 0;
    }

}
