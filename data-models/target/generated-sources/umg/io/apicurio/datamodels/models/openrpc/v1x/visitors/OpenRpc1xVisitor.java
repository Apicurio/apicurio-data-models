package io.apicurio.datamodels.models.openrpc.v1x.visitors;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.Contact;
import io.apicurio.datamodels.models.Example;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.License;
import io.apicurio.datamodels.models.Link;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.openrpc.OpenRpcContentDescriptor;
import io.apicurio.datamodels.models.openrpc.OpenRpcError;
import io.apicurio.datamodels.models.openrpc.OpenRpcExamplePairing;
import io.apicurio.datamodels.models.openrpc.OpenRpcMethod;
import io.apicurio.datamodels.models.visitors.Visitor;

public interface OpenRpc1xVisitor extends Visitor {

	public void visitContact(Contact node);

	public void visitExamplePairing(OpenRpcExamplePairing node);

	public void visitLicense(License node);

	public void visitLink(Link node);

	public void visitTag(Tag node);

	public void visitMethod(OpenRpcMethod node);

	public void visitExternalDocumentation(ExternalDocumentation node);

	public void visitComponents(Components node);

	public void visitInfo(Info node);

	public void visitServerVariable(ServerVariable node);

	public void visitError(OpenRpcError node);

	public void visitSchema(Schema node);

	public void visitServer(Server node);

	public void visitExample(Example node);

	public void visitContentDescriptor(OpenRpcContentDescriptor node);
}