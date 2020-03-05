package io.apicurio.datamodels.core.validation.rules.required;

import io.apicurio.datamodels.asyncapi.v2.models.Aai20CorrelationId;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

public class AaMissingCorrelationIdRule extends RequiredPropertyValidationRule {

    public AaMissingCorrelationIdRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    public void visitCorrelationId(Aai20CorrelationId node) {
        this.requireProperty(node, Constants.PROP_CORRELATION_ID, map());
    }

}
