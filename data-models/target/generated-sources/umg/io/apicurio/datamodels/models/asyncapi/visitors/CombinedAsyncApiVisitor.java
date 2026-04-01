package io.apicurio.datamodels.models.asyncapi.visitors;

import io.apicurio.datamodels.models.asyncapi.v2x.v20.visitors.AsyncApi20Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.visitors.AsyncApi21Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.visitors.AsyncApi22Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.visitors.AsyncApi23Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.visitors.AsyncApi24Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.visitors.AsyncApi25Visitor;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.visitors.AsyncApi26Visitor;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;

public interface CombinedAsyncApiVisitor
		extends
			AsyncApi26Visitor,
			AsyncApi25Visitor,
			AsyncApi31Visitor,
			AsyncApi20Visitor,
			AsyncApi24Visitor,
			AsyncApi30Visitor,
			AsyncApi23Visitor,
			AsyncApi22Visitor,
			AsyncApi21Visitor {
}