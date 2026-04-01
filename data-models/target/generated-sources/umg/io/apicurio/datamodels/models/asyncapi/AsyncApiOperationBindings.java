package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;

public interface AsyncApiOperationBindings extends Node {

	public AsyncApiBinding getStomp();

	public void setStomp(AsyncApiBinding value);

	public AsyncApiBinding createBinding();

	public AsyncApiBinding getMqtt();

	public void setMqtt(AsyncApiBinding value);

	public AsyncApiBinding getJms();

	public void setJms(AsyncApiBinding value);

	public AsyncApiBinding getAmqp();

	public void setAmqp(AsyncApiBinding value);

	public AsyncApiBinding getSqs();

	public void setSqs(AsyncApiBinding value);

	public AsyncApiBinding getSns();

	public void setSns(AsyncApiBinding value);

	public AsyncApiBinding getKafka();

	public void setKafka(AsyncApiBinding value);

	public AsyncApiBinding getRedis();

	public void setRedis(AsyncApiBinding value);

	public AsyncApiBinding getAmqp1();

	public void setAmqp1(AsyncApiBinding value);

	public AsyncApiBinding getMqtt5();

	public void setMqtt5(AsyncApiBinding value);

	public AsyncApiBinding getNats();

	public void setNats(AsyncApiBinding value);

	public AsyncApiBinding getWs();

	public void setWs(AsyncApiBinding value);

	public AsyncApiBinding getHttp();

	public void setHttp(AsyncApiBinding value);
}