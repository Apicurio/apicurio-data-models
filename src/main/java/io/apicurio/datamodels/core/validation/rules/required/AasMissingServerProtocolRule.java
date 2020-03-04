package io.apicurio.datamodels.core.validation.rules.required;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

public class AasMissingServerProtocolRule extends RequiredPropertyValidationRule {

	public AasMissingServerProtocolRule(ValidationRuleMetaData ruleInfo) {
		super(ruleInfo);
	}

	/**
	 * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
	 */
	@Override
	public void visitServer(Server node) {
		this.requireProperty(node, Constants.PROP_PROTOCOL, map());
	}

}