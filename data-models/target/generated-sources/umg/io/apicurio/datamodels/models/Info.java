package io.apicurio.datamodels.models;
public interface Info extends Node {

	public String getTitle();

	public void setTitle(String value);

	public License getLicense();

	public void setLicense(License value);

	public License createLicense();

	public String getDescription();

	public void setDescription(String value);

	public String getTermsOfService();

	public void setTermsOfService(String value);

	public Contact getContact();

	public void setContact(Contact value);

	public Contact createContact();

	public String getVersion();

	public void setVersion(String value);
}