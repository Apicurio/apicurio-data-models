package io.apicurio.datamodels.models.visitors;

import io.apicurio.datamodels.models.asyncapi.v2x.v20.visitors.AsyncApi20Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.visitors.AsyncApi21Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.visitors.AsyncApi22Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.visitors.AsyncApi23Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.visitors.AsyncApi24Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.visitors.AsyncApi25Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.visitors.AsyncApi26Visitor;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.visitors.JSDraft4Visitor;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.visitors.JSDraft6Visitor;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.visitors.JSDraft7Visitor;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.visitors.JS201909Visitor;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.visitors.JS202012Visitor;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v31.visitors.OpenApi31Visitor;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;
import io.apicurio.datamodels.models.openrpc.v1x.v13.visitors.OpenRpc13Visitor;
import io.apicurio.datamodels.models.openrpc.v1x.v14.visitors.OpenRpc14Visitor;

public interface CombinedVisitor
		extends
			OpenRpc14Visitor,
			JS202012Visitor,
			JSDraft7Visitor,
			JSDraft6Visitor,
			JSDraft4Visitor,
			OpenRpc13Visitor,
			OpenApi20Visitor,
			AsyncApi26Visitor,
			AsyncApi25Visitor,
			AsyncApi31Visitor,
			AsyncApi20Visitor,
			JS201909Visitor,
			AsyncApi24Visitor,
			AsyncApi30Visitor,
			OpenApi30Visitor,
			AsyncApi23Visitor,
			AsyncApi22Visitor,
			OpenApi32Visitor,
			AsyncApi21Visitor,
			OpenApi31Visitor {
}