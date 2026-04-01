package io.apicurio.datamodels.models.openrpc.v1x.v13.visitors;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.Contact;
import io.apicurio.datamodels.models.Document;
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

public class OpenRpc13VisitorAdapter implements OpenRpc13Visitor {

	@Override
	public void visitContact(Contact node) {
	}

	@Override
	public void visitExamplePairing(OpenRpcExamplePairing node) {
	}

	@Override
	public void visitLicense(License node) {
	}

	@Override
	public void visitLink(Link node) {
	}

	@Override
	public void visitTag(Tag node) {
	}

	@Override
	public void visitMethod(OpenRpcMethod node) {
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
	}

	@Override
	public void visitComponents(Components node) {
	}

	@Override
	public void visitInfo(Info node) {
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
	}

	@Override
	public void visitError(OpenRpcError node) {
	}

	@Override
	public void visitSchema(Schema node) {
	}

	@Override
	public void visitServer(Server node) {
	}

	@Override
	public void visitExample(Example node) {
	}

	@Override
	public void visitContentDescriptor(OpenRpcContentDescriptor node) {
	}

	@Override
	public void visitDocument(Document node) {
	}
}