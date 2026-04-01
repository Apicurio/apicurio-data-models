package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiParameter;
import java.util.List;

public interface AsyncApi3xParameter extends AsyncApiParameter {

	public List<String> getExamples();

	public void setExamples(List<String> value);

	public String getDefault();

	public void setDefault(String value);

	public List<String> getEnum();

	public void setEnum(List<String> value);
}