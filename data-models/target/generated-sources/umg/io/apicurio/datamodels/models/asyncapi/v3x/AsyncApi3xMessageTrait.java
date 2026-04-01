package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.union.MultiFormatSchemaSchemaUnion;
import java.util.List;

public interface AsyncApi3xMessageTrait extends AsyncApiMessageTrait {

	public AsyncApi3xMessageExample createMessageExample();

	public List<AsyncApi3xMessageExample> getExamples();

	public void addExample(AsyncApi3xMessageExample value);

	public void clearExamples();

	public void removeExample(AsyncApi3xMessageExample value);

	public void insertExample(AsyncApi3xMessageExample value, int atIndex);

	public MultiFormatSchemaSchemaUnion getHeaders();

	public void setHeaders(MultiFormatSchemaSchemaUnion value);

	public AsyncApi3xMultiFormatSchema createMultiFormatSchema();

	public AsyncApi3xSchema createSchema();
}