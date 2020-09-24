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

import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;

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
     * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitSchema(io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    public void visitSchema(Schema node) {
        if (node.ownerDocument().getDocumentType() == DocumentType.asyncapi2) {
            this.setItems((AaiSchema) node);
        } else {
            this.setItems((OasSchema) node);
        }
    }
    
    private void setItems(OasSchema node) {
        OasSchema schema = (OasSchema) node;
        schema.items = schema.createItemsSchema();
        if (ModelUtils.isDefined(this.type.of)) {
            // TODO Handle the case where "items" is actually a List of schemas.
            OasSchema items = (OasSchema) schema.items;
            if (this.type.of.isRef()) {
                items.$ref = this.type.of.type;
            } else {
                items.type = this.type.of.type;
                items.format = this.type.of.as;
            }
        }
    }

    private void setItems(AaiSchema node) {
        AaiSchema schema = (AaiSchema) node;
        schema.items = schema.createItemsSchema();
        if (ModelUtils.isDefined(this.type.of)) {
            // TODO Handle the case where "items" is actually a List of schemas.
            AaiSchema items = (AaiSchema) schema.items;
            if (this.type.of.isRef()) {
                items.$ref = this.type.of.type;
            } else {
                items.type = this.type.of.type;
                items.format = this.type.of.as;
            }
        }
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitParameter(io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        if (node.ownerDocument().getDocumentType() == DocumentType.openapi2) {
            Oas20Parameter param = (Oas20Parameter) node;
            param.items = param.createItems();
            if (ModelUtils.isDefined(this.type.of)) {
                param.items.type = this.type.of.type;
                param.items.format = this.type.of.as;
            }
        }
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitSchemaDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitSchemaDefinition(IDefinition node) {
        this.visitSchema((Schema) node);
    }
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitPropertySchema(io.apicurio.datamodels.core.models.common.IPropertySchema)
     */
    @Override
    public void visitPropertySchema(IPropertySchema node) {
        this.visitSchema((Schema) node);
    }
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitAdditionalPropertiesSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAdditionalPropertiesSchema(OasSchema node) {
        this.visitSchema((Schema) node);
    }
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitAllOfSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAllOfSchema(OasSchema node) {
        this.visitSchema((Schema) node);
    }
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitItemsSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitItemsSchema(OasSchema node) {
        this.visitSchema((Schema) node);
    }
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitParameterDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitParameterDefinition(IDefinition node) {
        this.visitParameter((Parameter) node);
    }

}
