package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.ServerVariable;
import java.util.List;

public interface AsyncApiServerVariable extends ServerVariable {

	public List<String> getExamples();

	public void setExamples(List<String> value);
}