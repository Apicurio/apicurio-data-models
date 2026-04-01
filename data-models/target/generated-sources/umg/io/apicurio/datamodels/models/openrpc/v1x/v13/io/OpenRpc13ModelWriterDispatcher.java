package io.apicurio.datamodels.models.openrpc.v1x.v13.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Components;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Contact;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13ContentDescriptor;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Document;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Error;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Example;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13ExamplePairing;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13ExternalDocumentation;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Info;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13License;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Link;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Method;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Schema;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Server;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13ServerVariable;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Tag;
import io.apicurio.datamodels.models.openrpc.v1x.v13.visitors.OpenRpc13Visitor;

public class OpenRpc13ModelWriterDispatcher implements OpenRpc13Visitor {

	private final ObjectNode json;
	private final OpenRpc13ModelWriter writer;

	public OpenRpc13ModelWriterDispatcher(ObjectNode json, OpenRpc13ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((OpenRpc13Contact) node, this.json);
	}

	@Override
	public void visitExamplePairing(OpenRpcExamplePairing node) {
		this.writer.writeExamplePairing((OpenRpc13ExamplePairing) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((OpenRpc13License) node, this.json);
	}

	@Override
	public void visitLink(Link node) {
		this.writer.writeLink((OpenRpc13Link) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((OpenRpc13Tag) node, this.json);
	}

	@Override
	public void visitMethod(OpenRpcMethod node) {
		this.writer.writeMethod((OpenRpc13Method) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((OpenRpc13ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((OpenRpc13Components) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((OpenRpc13Info) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((OpenRpc13ServerVariable) node, this.json);
	}

	@Override
	public void visitError(OpenRpcError node) {
		this.writer.writeError((OpenRpc13Error) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((OpenRpc13Schema) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((OpenRpc13Server) node, this.json);
	}

	@Override
	public void visitExample(Example node) {
		this.writer.writeExample((OpenRpc13Example) node, this.json);
	}

	@Override
	public void visitContentDescriptor(OpenRpcContentDescriptor node) {
		this.writer.writeContentDescriptor((OpenRpc13ContentDescriptor) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((OpenRpc13Document) node, this.json);
	}
}