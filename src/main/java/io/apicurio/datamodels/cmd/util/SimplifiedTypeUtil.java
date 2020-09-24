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

import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;

/**
 * @author eric.wittmann@gmail.com
 */
public class SimplifiedTypeUtil {

    public static void setSimplifiedType(OasSchema node, SimplifiedType type) {
        node.$ref = null;
        node.type = null;
        node.enum_ = null;
        node.format = null;
        node.items = null;

        if (type.isSimpleType()) {
            node.type = type.type;
            node.format = type.as;
        }
        if (type.isFileType()) {
            node.type = type.type;
        }
        if (type.isEnum()) {
            node.enum_ = new ArrayList<>();
            type.enum_.forEach( v -> {
                node.enum_.add(String.valueOf(v));
            });
        }
        if (type.isRef()) {
            node.$ref = type.type;
        }
        if (type.isArray()) {
            node.type = "array";
            SetItemsTypeVisitor viz = new SetItemsTypeVisitor(type);
            VisitorUtil.visitNode(node, viz);
        }
    }

    public static void setSimplifiedType(AaiSchema node, SimplifiedType type) {
        node.$ref = null;
        node.type = null;
        node.enum_ = null;
        node.format = null;
        node.items = null;

        if (type.isSimpleType()) {
            node.type = type.type;
            node.format = type.as;
        }
        if (type.isFileType()) {
            node.type = type.type;
        }
        if (type.isEnum()) {
            node.enum_ = new ArrayList<>();
            type.enum_.forEach( v -> {
                node.enum_.add(String.valueOf(v));
            });
        }
        if (type.isRef()) {
            node.$ref = type.type;
        }
        if (type.isArray()) {
            node.type = "array";
            SetItemsTypeVisitor viz = new SetItemsTypeVisitor(type);
            VisitorUtil.visitNode(node, viz);
        }
    }

    public static void setSimplifiedTypeOnParam(Oas20Parameter node, SimplifiedType type) {
        node.$ref = null;
        node.type = null;
        node.enum_ = null;
        node.format = null;
        node.items = null;

        if (type.isSimpleType()) {
            node.type = type.type;
            node.format = type.as;
        }
        if (type.isFileType()) {
            node.type = type.type;
        }
        if (type.isEnum()) {
            node.enum_ = new ArrayList<>();
            type.enum_.forEach( v -> {
                node.enum_.add(String.valueOf(v));
            });
        }
        if (type.isRef()) {
            node.$ref = type.type;
        }
        if (type.isArray()) {
            node.type = "array";
            SetItemsTypeVisitor viz = new SetItemsTypeVisitor(type);
            VisitorUtil.visitNode(node, viz);
        }
    }
}
