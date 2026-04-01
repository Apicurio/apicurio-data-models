package io.apicurio.datamodels.models;

import java.util.List;

public interface SecurityRequirementsParent {

	public SecurityRequirement createSecurityRequirement();

	public List<SecurityRequirement> getSecurity();

	public void addSecurity(SecurityRequirement value);

	public void clearSecurity();

	public void removeSecurity(SecurityRequirement value);

	public void insertSecurity(SecurityRequirement value, int atIndex);
}