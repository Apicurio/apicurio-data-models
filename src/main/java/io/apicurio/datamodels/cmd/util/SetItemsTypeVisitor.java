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

import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.asyncapi.AsyncApiSchema;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Parameter;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * @author eric.wittmann@gmail.com
 */
public class SetItemsTypeVisitor extends CombinedVisitorAdapter {

    private SimplifiedType type;

    /**
     * Constructor.
     * @param type
     */
    public SetItemsTypeVisitor(SimplifiedType type) {
        this.type = type;
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitSchema(io.apicurio.datamodels.models.Schema)
     */
    @Override
    public void visitSchema(Schema node) {
        if (ModelTypeUtil.isAsyncApiModel(node)) {
            this.setAsyncApiItems((AsyncApiSchema) node);
        } else {
            this.setOpenApiItems(node);
        }
    }

    private void setOpenApiItems(Schema node) {
        // TODO implement setItems()
        throw new UnsupportedOperationException("Setting type of Items (OpenAPI) not yet implemented!");
        //        OpenApiSchema schema = (OpenApiSchema) node;
        //        schema.items = schema.createItemsSchema();
        //        if (ModelUtils.isDefined(this.type.of)) {
        //            // TODO Handle the case where "items" is actually a List of schemas.
        //            OpenApiSchema items = (OpenApiSchema) schema.items;
        //            if (this.type.of.isRef()) {
        //                items.$ref = this.type.of.type;
        //            } else {
        //                items.type = this.type.of.type;
        //                items.format = this.type.of.as;
        //            }
        //        }
    }

    private void setAsyncApiItems(AsyncApiSchema node) {
        AsyncApiSchema schema = node;
        schema.setItems(schema.createSchema());
        if (NodeUtil.isDefined(this.type.of)) {
            // TODO Handle the case where "items" is actually a List of schemas.
            AsyncApiSchema items = (AsyncApiSchema) schema.getItems().asSchema();
            if (this.type.of.isRef()) {
                ((Referenceable) items).set$ref(this.type.of.type);
            } else {
                items.setType(this.type.of.type);
                items.setFormat(this.type.of.as);
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitParameter(io.apicurio.datamodels.models.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        if (ModelTypeUtil.isOpenApi2Model(node)) {
            OpenApi20Parameter param = (OpenApi20Parameter) node;
            param.setItems(param.createItems());
            if (NodeUtil.isDefined(this.type.of)) {
                param.getItems().setType(this.type.of.type);
                param.getItems().setFormat(this.type.of.as);
            }
        }
    }

}
