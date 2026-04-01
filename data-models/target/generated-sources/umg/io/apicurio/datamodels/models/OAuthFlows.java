package io.apicurio.datamodels.models;
public interface OAuthFlows extends Node {

	public OAuthFlow getClientCredentials();

	public void setClientCredentials(OAuthFlow value);

	public OAuthFlow createOAuthFlow();

	public OAuthFlow getImplicit();

	public void setImplicit(OAuthFlow value);

	public OAuthFlow getAuthorizationCode();

	public void setAuthorizationCode(OAuthFlow value);

	public OAuthFlow getPassword();

	public void setPassword(OAuthFlow value);
}