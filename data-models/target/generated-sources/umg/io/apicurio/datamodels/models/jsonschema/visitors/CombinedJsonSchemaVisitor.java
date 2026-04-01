package io.apicurio.datamodels.models.jsonschema.visitors;

import io.apicurio.datamodels.models.jsonschema.draft.draft4.visitors.JSDraft4Visitor;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.visitors.JSDraft6Visitor;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.visitors.JSDraft7Visitor;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.visitors.JS201909Visitor;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.visitors.JS202012Visitor;

public interface CombinedJsonSchemaVisitor
		extends
			JS202012Visitor,
			JSDraft7Visitor,
			JSDraft6Visitor,
			JSDraft4Visitor,
			JS201909Visitor {
}