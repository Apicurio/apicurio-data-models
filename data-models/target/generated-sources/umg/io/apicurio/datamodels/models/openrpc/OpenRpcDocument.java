package io.apicurio.datamodels.models.openrpc;

import io.apicurio.datamodels.models.Document;
import java.util.List;

public interface OpenRpcDocument extends Document {

	public OpenRpcInfo getInfo();

	public void setInfo(OpenRpcInfo value);

	public OpenRpcInfo createInfo();

	public String getOpenrpc();

	public void setOpenrpc(String value);

	public OpenRpcComponents getComponents();

	public void setComponents(OpenRpcComponents value);

	public OpenRpcComponents createComponents();

	public OpenRpcMethod createMethod();

	public List<OpenRpcMethod> getMethods();

	public void addMethod(OpenRpcMethod value);

	public void clearMethods();

	public void removeMethod(OpenRpcMethod value);

	public void insertMethod(OpenRpcMethod value, int atIndex);

	public OpenRpcServer createServer();

	public List<OpenRpcServer> getServers();

	public void addServer(OpenRpcServer value);

	public void clearServers();

	public void removeServer(OpenRpcServer value);

	public void insertServer(OpenRpcServer value, int atIndex);

	public OpenRpcExternalDocumentation getExternalDocs();

	public void setExternalDocs(OpenRpcExternalDocumentation value);

	public OpenRpcExternalDocumentation createExternalDocumentation();
}