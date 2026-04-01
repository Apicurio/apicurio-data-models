package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;

public interface AsyncApi3xServerBindings extends AsyncApiServerBindings {

	public AsyncApi3xBinding getIbmmq();

	public void setIbmmq(AsyncApi3xBinding value);

	public AsyncApi3xBinding createBinding();

	public AsyncApi3xBinding getGooglepubsub();

	public void setGooglepubsub(AsyncApi3xBinding value);

	public AsyncApi3xBinding getPulsar();

	public void setPulsar(AsyncApi3xBinding value);

	public AsyncApi3xBinding getMercure();

	public void setMercure(AsyncApi3xBinding value);

	public AsyncApi3xBinding getAnypointmq();

	public void setAnypointmq(AsyncApi3xBinding value);

	public AsyncApi3xBinding getSolace();

	public void setSolace(AsyncApi3xBinding value);
}