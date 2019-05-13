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

package io.apicurio.datamodels.core.validation.rules.invalid.type;

import io.apicurio.datamodels.core.models.common.ISchemaDefinition;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema;

/**
 * Implements the Invalid Property Type validation rule.  This rule is responsible
 * for reporting whenever the **type** and **items** of a property fails to conform to the required
 * format defined by the specification
 * @author eric.wittmann@gmail.com
 */
public abstract class OasInvalidPropertyTypeValidationRule extends ValidationRule {

    protected static final String[] ALLOWED_TYPES = {"string", "number", "integer", "boolean", "array", "object"};

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidPropertyTypeValidationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if the type node has a valid type.
     * @param type
     * @return {boolean}
     */
    protected boolean isValidType(String type) {
        if (hasValue(type)) {
            return isValidEnumItem(type, ALLOWED_TYPES);
        }
        return true;
    }
    
    @Override
    public void visitAllOfSchema(OasSchema node) { this.visitSchema(node); }
    @Override
    public void visitAnyOfSchema(Oas30AnyOfSchema node) { this.visitSchema(node); }
    @Override
    public void visitOneOfSchema(Oas30OneOfSchema node) { this.visitSchema(node); }
    @Override
    public void visitNotSchema(Oas30NotSchema node) { this.visitSchema(node); }
    @Override
    public void visitPropertySchema(IOasPropertySchema node) { this.visitSchema((Schema) node); }
    @Override
    public void visitItemsSchema(OasSchema node) { this.visitSchema(node); }
    @Override
    public void visitAdditionalPropertiesSchema(OasSchema node) { this.visitSchema(node); }
    @Override
    public void visitSchemaDefinition(ISchemaDefinition node) { this.visitSchema((Schema) node); }

}
