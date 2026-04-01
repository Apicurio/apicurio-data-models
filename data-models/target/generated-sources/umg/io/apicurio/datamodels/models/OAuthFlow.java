package io.apicurio.datamodels.models;
public interface OAuthFlow extends Node {

	public String getTokenUrl();

	public void setTokenUrl(String value);

	public String getRefreshUrl();

	public void setRefreshUrl(String value);

	public String getAuthorizationUrl();

	public void setAuthorizationUrl(String value);
}