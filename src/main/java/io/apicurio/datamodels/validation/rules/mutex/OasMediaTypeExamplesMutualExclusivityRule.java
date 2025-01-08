package io.apicurio.datamodels.validation.rules.mutex;

import io.apicurio.datamodels.models.openapi.OpenApiExamplesParent;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Media Type Example/Examples Mutual Exclusivity Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasMediaTypeExamplesMutualExclusivityRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasMediaTypeExamplesMutualExclusivityRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter#visitMediaType(io.apicurio.datamodels.models.openapi.OpenApiMediaType)
     */
    @Override
    public void visitMediaType(OpenApiMediaType node) {
        OpenApiExamplesParent examplesParent = (OpenApiExamplesParent) node;
        this.reportIf(hasValue(node.getExample()) && !examplesParent.getExamples().isEmpty(), node, "example", map());
    }

}
