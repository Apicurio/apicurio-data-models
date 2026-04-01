package io.apicurio.datamodels.models.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.io.AsyncApi20ModelWriter;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.io.AsyncApi20ModelWriterDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.io.AsyncApi21ModelWriter;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.io.AsyncApi21ModelWriterDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.io.AsyncApi22ModelWriter;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.io.AsyncApi22ModelWriterDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.io.AsyncApi23ModelWriter;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.io.AsyncApi23ModelWriterDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.io.AsyncApi24ModelWriter;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.io.AsyncApi24ModelWriterDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.io.AsyncApi25ModelWriter;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.io.AsyncApi25ModelWriterDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.io.AsyncApi26ModelWriter;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.io.AsyncApi26ModelWriterDispatcher;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.io.AsyncApi30ModelWriter;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.io.AsyncApi30ModelWriterDispatcher;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.io.AsyncApi31ModelWriter;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.io.AsyncApi31ModelWriterDispatcher;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.io.JSDraft4ModelWriter;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.io.JSDraft4ModelWriterDispatcher;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.io.JSDraft6ModelWriter;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.io.JSDraft6ModelWriterDispatcher;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.io.JSDraft7ModelWriter;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.io.JSDraft7ModelWriterDispatcher;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.io.JS201909ModelWriter;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.io.JS201909ModelWriterDispatcher;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.io.JS202012ModelWriter;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.io.JS202012ModelWriterDispatcher;
import io.apicurio.datamodels.models.openapi.v2x.v20.io.OpenApi20ModelWriter;
import io.apicurio.datamodels.models.openapi.v2x.v20.io.OpenApi20ModelWriterDispatcher;
import io.apicurio.datamodels.models.openapi.v3x.v30.io.OpenApi30ModelWriter;
import io.apicurio.datamodels.models.openapi.v3x.v30.io.OpenApi30ModelWriterDispatcher;
import io.apicurio.datamodels.models.openapi.v3x.v31.io.OpenApi31ModelWriter;
import io.apicurio.datamodels.models.openapi.v3x.v31.io.OpenApi31ModelWriterDispatcher;
import io.apicurio.datamodels.models.openapi.v3x.v32.io.OpenApi32ModelWriter;
import io.apicurio.datamodels.models.openapi.v3x.v32.io.OpenApi32ModelWriterDispatcher;
import io.apicurio.datamodels.models.openrpc.v1x.v13.io.OpenRpc13ModelWriter;
import io.apicurio.datamodels.models.openrpc.v1x.v13.io.OpenRpc13ModelWriterDispatcher;
import io.apicurio.datamodels.models.openrpc.v1x.v14.io.OpenRpc14ModelWriter;
import io.apicurio.datamodels.models.openrpc.v1x.v14.io.OpenRpc14ModelWriterDispatcher;
import io.apicurio.datamodels.models.visitors.Visitor;

public class ModelWriterFactory {

	public static ModelWriter createModelWriter(ModelType modelType) {
		ModelWriter writer = null;
		switch (modelType) {
			case ASYNCAPI20 :
				writer = new AsyncApi20ModelWriter();
				break;
			case OPENRPC14 :
				writer = new OpenRpc14ModelWriter();
				break;
			case OPENAPI30 :
				writer = new OpenApi30ModelWriter();
				break;
			case JSDRAFT4 :
				writer = new JSDraft4ModelWriter();
				break;
			case OPENRPC13 :
				writer = new OpenRpc13ModelWriter();
				break;
			case OPENAPI20 :
				writer = new OpenApi20ModelWriter();
				break;
			case ASYNCAPI25 :
				writer = new AsyncApi25ModelWriter();
				break;
			case JSDRAFT6 :
				writer = new JSDraft6ModelWriter();
				break;
			case OPENAPI31 :
				writer = new OpenApi31ModelWriter();
				break;
			case JSDRAFT7 :
				writer = new JSDraft7ModelWriter();
				break;
			case ASYNCAPI24 :
				writer = new AsyncApi24ModelWriter();
				break;
			case ASYNCAPI23 :
				writer = new AsyncApi23ModelWriter();
				break;
			case ASYNCAPI21 :
				writer = new AsyncApi21ModelWriter();
				break;
			case ASYNCAPI22 :
				writer = new AsyncApi22ModelWriter();
				break;
			case ASYNCAPI30 :
				writer = new AsyncApi30ModelWriter();
				break;
			case OPENAPI32 :
				writer = new OpenApi32ModelWriter();
				break;
			case JS201909 :
				writer = new JS201909ModelWriter();
				break;
			case ASYNCAPI26 :
				writer = new AsyncApi26ModelWriter();
				break;
			case JS202012 :
				writer = new JS202012ModelWriter();
				break;
			case ASYNCAPI31 :
				writer = new AsyncApi31ModelWriter();
				break;
		}
		return writer;
	}

	public static Visitor createModelWriterDispatcher(ModelType modelType, ObjectNode json) {
		ModelWriter writer = ModelWriterFactory.createModelWriter(modelType);
		Visitor visitor = null;
		switch (modelType) {
			case ASYNCAPI20 :
				visitor = new AsyncApi20ModelWriterDispatcher(json, (AsyncApi20ModelWriter) writer);
				break;
			case OPENRPC14 :
				visitor = new OpenRpc14ModelWriterDispatcher(json, (OpenRpc14ModelWriter) writer);
				break;
			case OPENAPI30 :
				visitor = new OpenApi30ModelWriterDispatcher(json, (OpenApi30ModelWriter) writer);
				break;
			case JSDRAFT4 :
				visitor = new JSDraft4ModelWriterDispatcher(json, (JSDraft4ModelWriter) writer);
				break;
			case OPENRPC13 :
				visitor = new OpenRpc13ModelWriterDispatcher(json, (OpenRpc13ModelWriter) writer);
				break;
			case OPENAPI20 :
				visitor = new OpenApi20ModelWriterDispatcher(json, (OpenApi20ModelWriter) writer);
				break;
			case ASYNCAPI25 :
				visitor = new AsyncApi25ModelWriterDispatcher(json, (AsyncApi25ModelWriter) writer);
				break;
			case JSDRAFT6 :
				visitor = new JSDraft6ModelWriterDispatcher(json, (JSDraft6ModelWriter) writer);
				break;
			case OPENAPI31 :
				visitor = new OpenApi31ModelWriterDispatcher(json, (OpenApi31ModelWriter) writer);
				break;
			case JSDRAFT7 :
				visitor = new JSDraft7ModelWriterDispatcher(json, (JSDraft7ModelWriter) writer);
				break;
			case ASYNCAPI24 :
				visitor = new AsyncApi24ModelWriterDispatcher(json, (AsyncApi24ModelWriter) writer);
				break;
			case ASYNCAPI23 :
				visitor = new AsyncApi23ModelWriterDispatcher(json, (AsyncApi23ModelWriter) writer);
				break;
			case ASYNCAPI21 :
				visitor = new AsyncApi21ModelWriterDispatcher(json, (AsyncApi21ModelWriter) writer);
				break;
			case ASYNCAPI22 :
				visitor = new AsyncApi22ModelWriterDispatcher(json, (AsyncApi22ModelWriter) writer);
				break;
			case ASYNCAPI30 :
				visitor = new AsyncApi30ModelWriterDispatcher(json, (AsyncApi30ModelWriter) writer);
				break;
			case OPENAPI32 :
				visitor = new OpenApi32ModelWriterDispatcher(json, (OpenApi32ModelWriter) writer);
				break;
			case JS201909 :
				visitor = new JS201909ModelWriterDispatcher(json, (JS201909ModelWriter) writer);
				break;
			case ASYNCAPI26 :
				visitor = new AsyncApi26ModelWriterDispatcher(json, (AsyncApi26ModelWriter) writer);
				break;
			case JS202012 :
				visitor = new JS202012ModelWriterDispatcher(json, (JS202012ModelWriter) writer);
				break;
			case ASYNCAPI31 :
				visitor = new AsyncApi31ModelWriterDispatcher(json, (AsyncApi31ModelWriter) writer);
				break;
		}
		return visitor;
	}
}