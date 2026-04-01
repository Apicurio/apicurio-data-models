package io.apicurio.datamodels.models.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.io.AsyncApi20ModelReader;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.io.AsyncApi20ModelReaderDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.io.AsyncApi21ModelReader;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.io.AsyncApi21ModelReaderDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.io.AsyncApi22ModelReader;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.io.AsyncApi22ModelReaderDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.io.AsyncApi23ModelReader;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.io.AsyncApi23ModelReaderDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.io.AsyncApi24ModelReader;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.io.AsyncApi24ModelReaderDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.io.AsyncApi25ModelReader;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.io.AsyncApi25ModelReaderDispatcher;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.io.AsyncApi26ModelReader;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.io.AsyncApi26ModelReaderDispatcher;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.io.AsyncApi30ModelReader;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.io.AsyncApi30ModelReaderDispatcher;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.io.AsyncApi31ModelReader;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.io.AsyncApi31ModelReaderDispatcher;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.io.JSDraft4ModelReader;
import io.apicurio.datamodels.models.jsonschema.draft.draft4.io.JSDraft4ModelReaderDispatcher;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.io.JSDraft6ModelReader;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.io.JSDraft6ModelReaderDispatcher;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.io.JSDraft7ModelReader;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.io.JSDraft7ModelReaderDispatcher;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.io.JS201909ModelReader;
import io.apicurio.datamodels.models.jsonschema.modern.v201909.io.JS201909ModelReaderDispatcher;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.io.JS202012ModelReader;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.io.JS202012ModelReaderDispatcher;
import io.apicurio.datamodels.models.openapi.v2x.v20.io.OpenApi20ModelReader;
import io.apicurio.datamodels.models.openapi.v2x.v20.io.OpenApi20ModelReaderDispatcher;
import io.apicurio.datamodels.models.openapi.v3x.v30.io.OpenApi30ModelReader;
import io.apicurio.datamodels.models.openapi.v3x.v30.io.OpenApi30ModelReaderDispatcher;
import io.apicurio.datamodels.models.openapi.v3x.v31.io.OpenApi31ModelReader;
import io.apicurio.datamodels.models.openapi.v3x.v31.io.OpenApi31ModelReaderDispatcher;
import io.apicurio.datamodels.models.openapi.v3x.v32.io.OpenApi32ModelReader;
import io.apicurio.datamodels.models.openapi.v3x.v32.io.OpenApi32ModelReaderDispatcher;
import io.apicurio.datamodels.models.openrpc.v1x.v13.io.OpenRpc13ModelReader;
import io.apicurio.datamodels.models.openrpc.v1x.v13.io.OpenRpc13ModelReaderDispatcher;
import io.apicurio.datamodels.models.openrpc.v1x.v14.io.OpenRpc14ModelReader;
import io.apicurio.datamodels.models.openrpc.v1x.v14.io.OpenRpc14ModelReaderDispatcher;
import io.apicurio.datamodels.models.visitors.Visitor;

public class ModelReaderFactory {

	public static ModelReader createModelReader(ModelType modelType) {
		ModelReader reader = null;
		switch (modelType) {
			case ASYNCAPI20 :
				reader = new AsyncApi20ModelReader();
				break;
			case OPENRPC14 :
				reader = new OpenRpc14ModelReader();
				break;
			case OPENAPI30 :
				reader = new OpenApi30ModelReader();
				break;
			case JSDRAFT4 :
				reader = new JSDraft4ModelReader();
				break;
			case OPENRPC13 :
				reader = new OpenRpc13ModelReader();
				break;
			case OPENAPI20 :
				reader = new OpenApi20ModelReader();
				break;
			case ASYNCAPI25 :
				reader = new AsyncApi25ModelReader();
				break;
			case JSDRAFT6 :
				reader = new JSDraft6ModelReader();
				break;
			case OPENAPI31 :
				reader = new OpenApi31ModelReader();
				break;
			case JSDRAFT7 :
				reader = new JSDraft7ModelReader();
				break;
			case ASYNCAPI24 :
				reader = new AsyncApi24ModelReader();
				break;
			case ASYNCAPI23 :
				reader = new AsyncApi23ModelReader();
				break;
			case ASYNCAPI21 :
				reader = new AsyncApi21ModelReader();
				break;
			case ASYNCAPI22 :
				reader = new AsyncApi22ModelReader();
				break;
			case ASYNCAPI30 :
				reader = new AsyncApi30ModelReader();
				break;
			case OPENAPI32 :
				reader = new OpenApi32ModelReader();
				break;
			case JS201909 :
				reader = new JS201909ModelReader();
				break;
			case ASYNCAPI26 :
				reader = new AsyncApi26ModelReader();
				break;
			case JS202012 :
				reader = new JS202012ModelReader();
				break;
			case ASYNCAPI31 :
				reader = new AsyncApi31ModelReader();
				break;
		}
		return reader;
	}

	public static Visitor createModelReaderDispatcher(ModelType modelType, ObjectNode json) {
		ModelReader reader = ModelReaderFactory.createModelReader(modelType);
		Visitor visitor = null;
		switch (modelType) {
			case ASYNCAPI20 :
				visitor = new AsyncApi20ModelReaderDispatcher(json, (AsyncApi20ModelReader) reader);
				break;
			case OPENRPC14 :
				visitor = new OpenRpc14ModelReaderDispatcher(json, (OpenRpc14ModelReader) reader);
				break;
			case OPENAPI30 :
				visitor = new OpenApi30ModelReaderDispatcher(json, (OpenApi30ModelReader) reader);
				break;
			case JSDRAFT4 :
				visitor = new JSDraft4ModelReaderDispatcher(json, (JSDraft4ModelReader) reader);
				break;
			case OPENRPC13 :
				visitor = new OpenRpc13ModelReaderDispatcher(json, (OpenRpc13ModelReader) reader);
				break;
			case OPENAPI20 :
				visitor = new OpenApi20ModelReaderDispatcher(json, (OpenApi20ModelReader) reader);
				break;
			case ASYNCAPI25 :
				visitor = new AsyncApi25ModelReaderDispatcher(json, (AsyncApi25ModelReader) reader);
				break;
			case JSDRAFT6 :
				visitor = new JSDraft6ModelReaderDispatcher(json, (JSDraft6ModelReader) reader);
				break;
			case OPENAPI31 :
				visitor = new OpenApi31ModelReaderDispatcher(json, (OpenApi31ModelReader) reader);
				break;
			case JSDRAFT7 :
				visitor = new JSDraft7ModelReaderDispatcher(json, (JSDraft7ModelReader) reader);
				break;
			case ASYNCAPI24 :
				visitor = new AsyncApi24ModelReaderDispatcher(json, (AsyncApi24ModelReader) reader);
				break;
			case ASYNCAPI23 :
				visitor = new AsyncApi23ModelReaderDispatcher(json, (AsyncApi23ModelReader) reader);
				break;
			case ASYNCAPI21 :
				visitor = new AsyncApi21ModelReaderDispatcher(json, (AsyncApi21ModelReader) reader);
				break;
			case ASYNCAPI22 :
				visitor = new AsyncApi22ModelReaderDispatcher(json, (AsyncApi22ModelReader) reader);
				break;
			case ASYNCAPI30 :
				visitor = new AsyncApi30ModelReaderDispatcher(json, (AsyncApi30ModelReader) reader);
				break;
			case OPENAPI32 :
				visitor = new OpenApi32ModelReaderDispatcher(json, (OpenApi32ModelReader) reader);
				break;
			case JS201909 :
				visitor = new JS201909ModelReaderDispatcher(json, (JS201909ModelReader) reader);
				break;
			case ASYNCAPI26 :
				visitor = new AsyncApi26ModelReaderDispatcher(json, (AsyncApi26ModelReader) reader);
				break;
			case JS202012 :
				visitor = new JS202012ModelReaderDispatcher(json, (JS202012ModelReader) reader);
				break;
			case ASYNCAPI31 :
				visitor = new AsyncApi31ModelReaderDispatcher(json, (AsyncApi31ModelReader) reader);
				break;
		}
		return visitor;
	}
}