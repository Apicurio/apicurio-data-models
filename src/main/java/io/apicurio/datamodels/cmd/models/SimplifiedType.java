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

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Items;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Parameter;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Schema;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Schema;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Schema;
import io.apicurio.datamodels.util.NodeUtil;

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

    public static SimplifiedType fromParameter(OpenApi20Parameter param) {
        SimplifiedType rval = new SimplifiedType();
        if (NodeUtil.isDefined(param) && NodeUtil.isDefined(param.getEnum()) && param.getEnum().size() >= 0) {
            // Need to clone the enum values
            rval.enum_ = new ArrayList<>(param.getEnum());
        }
        if (NodeUtil.isDefined(param) && NodeUtil.isDefined(param.getType()) && !NodeUtil.equals(param.getType(), "array") && !NodeUtil.equals(param.getType(), "object")) {
            rval.type = param.getType();
            if (NodeUtil.isDefined(param.getFormat())) {
                rval.as = param.getFormat();
            }
        }
        if (NodeUtil.isDefined(param) && NodeUtil.equals(param.getType(), "array") && NodeUtil.isDefined(param.getItems())) {
            rval.type = "array";
            rval.of = SimplifiedType.fromItems(param.getItems());
        }
        return rval;
    }

    public static SimplifiedType fromItems(OpenApi20Items items) {
        SimplifiedType rval = new SimplifiedType();
        if (NodeUtil.isDefined(items) && NodeUtil.isDefined(items.getEnum()) && items.getEnum().size() >= 0) {
            // Need to clone the enum values
            rval.enum_ = new ArrayList<>(items.getEnum());
        }
        if (NodeUtil.isDefined(items) && NodeUtil.isDefined(items.getType()) && !NodeUtil.equals(items.getType(), "array") && !NodeUtil.equals(items.getType(), "object")) {
            rval.type = items.getType();
            if (NodeUtil.isDefined(items.getFormat())) {
                rval.as = items.getFormat();
            }
        }
        if (NodeUtil.isDefined(items) && NodeUtil.equals(items.getType(), "array") && NodeUtil.isDefined(items.getItems())) {
            rval.type = "array";
            rval.of = SimplifiedType.fromItems(items.getItems());
        }
        return rval;
    }

    public static SimplifiedType fromSchema(OpenApiSchema schema) {
        Referenceable referenceable = (Referenceable) schema;
        SimplifiedType rval = new SimplifiedType();
        if (NodeUtil.isDefined(schema) && NodeUtil.isDefined(referenceable.get$ref())) {
            rval.type = referenceable.get$ref();
        }
        if (NodeUtil.isDefined(schema) && NodeUtil.isDefined(schema.getEnum()) && schema.getEnum().size() >= 0) {
            // Need to clone the enum values
            rval.enum_ = new ArrayList<>(schema.getEnum());
        }
        if (NodeUtil.isDefined(schema) && NodeUtil.isDefined(schema.getType()) && !NodeUtil.equals(schema.getType(), "array") &&
                !NodeUtil.equals(schema.getType(), "object"))
        {
            rval.type = schema.getType();
            if (NodeUtil.isDefined(schema.getFormat())) {
                rval.as = schema.getFormat();
            }
        }

        if (NodeUtil.isDefined(schema) && NodeUtil.equals(schema.getType(), "array")) {
            if (schema.root().modelType() == ModelType.OPENAPI20) {
                OpenApi20Schema schema20 = (OpenApi20Schema) schema;
                if (NodeUtil.isDefined(schema20.getItems()) && schema20.getItems().isSchema()) {
                    if (NodeUtil.isDefined(schema20.getItems())) {
                        rval.type = "array";
                        rval.of = SimplifiedType.fromSchema((OpenApi20Schema) schema20.getItems().asSchema());
                    }
                } else {
                    // TODO handle the case where .items is a list of schemas
                }
            } else if (schema.root().modelType() == ModelType.OPENAPI30) {
                OpenApi30Schema schema30 = (OpenApi30Schema) schema;
                if (NodeUtil.isDefined(schema30.getItems())) {
                    rval.type = "array";
                    rval.of = SimplifiedType.fromSchema(schema30.getItems());
                }
            } else if (schema.root().modelType() == ModelType.OPENAPI31) {
                OpenApi31Schema schema31 = (OpenApi31Schema) schema;
                if (NodeUtil.isDefined(schema31.getItems())) {
                    rval.type = "array";
                    rval.of = SimplifiedType.fromSchema(schema31.getItems());
                }
            }
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
        return NodeUtil.equals(this.type, "file");
    }

    public boolean isEnum() {
        return NodeUtil.isDefined(this.enum_) && this.enum_.size() >= 0;
    }

    public boolean isArray() {
        return NodeUtil.equals(this.type, "array");
    }

    public boolean isRef() {
        return NodeUtil.isDefined(this.type) && this.type.indexOf("#/") == 0;
    }

}
