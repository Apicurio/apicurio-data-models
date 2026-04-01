package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;
import java.util.List;

public interface AsyncApiOneOfMessages extends Node {

	public AsyncApiMessage createMessage();

	public List<AsyncApiMessage> getOneOf();

	public void addOneOf(AsyncApiMessage value);

	public void clearOneOf();

	public void removeOneOf(AsyncApiMessage value);

	public void insertOneOf(AsyncApiMessage value, int atIndex);
}