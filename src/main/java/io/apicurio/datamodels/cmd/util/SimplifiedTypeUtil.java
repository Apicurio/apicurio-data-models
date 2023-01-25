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

import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.asyncapi.AsyncApiSchema;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Parameter;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Schema;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * @author eric.wittmann@gmail.com
 */
public class SimplifiedTypeUtil {

    public static void setSimplifiedType(Schema node, SimplifiedType type) {
        if (ModelTypeUtil.isAsyncApiModel(node)) {
            setSimplifiedType_aai((AsyncApiSchema) node, type);
        } else {
            setSimplifiedType_oas((OpenApiSchema) node, type);
        }

    }

    private static void setSimplifiedType_oas(OpenApiSchema node, SimplifiedType type) {
        ((Referenceable) node).set$ref(null);
        node.setType(null);
        node.setEnum(null);
        node.setFormat(null);
        if (ModelTypeUtil.isOpenApi2Model(node)) {
            ((OpenApi20Schema) node).setItems(null);
        }

        if (type.isSimpleType()) {
            node.setType(type.type);
            node.setFormat(type.as);
        }
        if (type.isFileType()) {
            node.setType(type.type);
        }
        if (type.isEnum()) {
            node.setEnum(new ArrayList<>());
            type.enum_.forEach( v -> {
                node.getEnum().add(String.valueOf(v));
            });
        }
        if (type.isRef()) {
            ((Referenceable) node).set$ref(type.type);
        }
        if (type.isArray()) {
            node.setType("array");
            SetItemsTypeVisitor viz = new SetItemsTypeVisitor(type);
            node.accept(viz);
        }
    }

    private static void setSimplifiedType_aai(AsyncApiSchema node, SimplifiedType type) {
        ((Referenceable) node).set$ref(null);
        node.setType(null);
        node.setEnum(null);
        node.setFormat(null);
        node.setItems(null);

        if (type.isSimpleType()) {
            node.setType(type.type);
            node.setFormat(type.as);
        }
        if (type.isFileType()) {
            node.setType(type.type);
        }
        if (type.isEnum()) {
            node.setEnum(new ArrayList<>());
            type.enum_.forEach( v -> {
                // TODO add support for this - need to add something to JsonUtil probably - to create a text node
                //node.getEnum().add(String.valueOf(v));
            });
        }
        if (type.isRef()) {
            ((Referenceable) node).set$ref(type.type);
        }
        if (type.isArray()) {
            node.setType("array");
            SetItemsTypeVisitor viz = new SetItemsTypeVisitor(type);
            node.accept(viz);
        }
    }

    public static void setSimplifiedTypeOnParam(OpenApi20Parameter node, SimplifiedType type) {
        ((Referenceable) node).set$ref(null);
        node.setType(null);
        node.setEnum(null);
        node.setFormat(null);
        node.setItems(null);

        if (type.isSimpleType()) {
            node.setType(type.type);
            node.setFormat(type.as);
        }
        if (type.isFileType()) {
            node.setType(type.type);
        }
        if (type.isEnum()) {
            node.setEnum(new ArrayList<>());
            type.enum_.forEach( v -> {
                node.getEnum().add(String.valueOf(v));
            });
        }
        if (type.isRef()) {
            ((Referenceable) node).set$ref(type.type);
        }
        if (type.isArray()) {
            node.setType("array");
            SetItemsTypeVisitor viz = new SetItemsTypeVisitor(type);
            node.accept(viz);
        }
    }
}
