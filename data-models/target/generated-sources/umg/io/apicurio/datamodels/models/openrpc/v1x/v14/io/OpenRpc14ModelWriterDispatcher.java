package io.apicurio.datamodels.models.openrpc.v1x.v14.io;

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
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Components;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Contact;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14ContentDescriptor;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Document;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Error;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Example;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14ExamplePairing;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14ExternalDocumentation;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Info;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14License;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Link;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Method;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Schema;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Server;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14ServerVariable;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Tag;
import io.apicurio.datamodels.models.openrpc.v1x.v14.visitors.OpenRpc14Visitor;

public class OpenRpc14ModelWriterDispatcher implements OpenRpc14Visitor {

	private final ObjectNode json;
	private final OpenRpc14ModelWriter writer;

	public OpenRpc14ModelWriterDispatcher(ObjectNode json, OpenRpc14ModelWriter writer) {
		this.json = json;
		this.writer = writer;
	}

	@Override
	public void visitContact(Contact node) {
		this.writer.writeContact((OpenRpc14Contact) node, this.json);
	}

	@Override
	public void visitExamplePairing(OpenRpcExamplePairing node) {
		this.writer.writeExamplePairing((OpenRpc14ExamplePairing) node, this.json);
	}

	@Override
	public void visitLicense(License node) {
		this.writer.writeLicense((OpenRpc14License) node, this.json);
	}

	@Override
	public void visitLink(Link node) {
		this.writer.writeLink((OpenRpc14Link) node, this.json);
	}

	@Override
	public void visitTag(Tag node) {
		this.writer.writeTag((OpenRpc14Tag) node, this.json);
	}

	@Override
	public void visitMethod(OpenRpcMethod node) {
		this.writer.writeMethod((OpenRpc14Method) node, this.json);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.writer.writeExternalDocumentation((OpenRpc14ExternalDocumentation) node, this.json);
	}

	@Override
	public void visitComponents(Components node) {
		this.writer.writeComponents((OpenRpc14Components) node, this.json);
	}

	@Override
	public void visitInfo(Info node) {
		this.writer.writeInfo((OpenRpc14Info) node, this.json);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.writer.writeServerVariable((OpenRpc14ServerVariable) node, this.json);
	}

	@Override
	public void visitError(OpenRpcError node) {
		this.writer.writeError((OpenRpc14Error) node, this.json);
	}

	@Override
	public void visitSchema(Schema node) {
		this.writer.writeSchema((OpenRpc14Schema) node, this.json);
	}

	@Override
	public void visitServer(Server node) {
		this.writer.writeServer((OpenRpc14Server) node, this.json);
	}

	@Override
	public void visitExample(Example node) {
		this.writer.writeExample((OpenRpc14Example) node, this.json);
	}

	@Override
	public void visitContentDescriptor(OpenRpcContentDescriptor node) {
		this.writer.writeContentDescriptor((OpenRpc14ContentDescriptor) node, this.json);
	}

	@Override
	public void visitDocument(Document node) {
		this.writer.writeDocument((OpenRpc14Document) node, this.json);
	}
}