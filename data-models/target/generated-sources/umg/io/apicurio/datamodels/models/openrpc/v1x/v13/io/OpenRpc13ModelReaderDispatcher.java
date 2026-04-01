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

public class OpenRpc13ModelReaderDispatcher implements OpenRpc13Visitor {

	private final ObjectNode json;
	private final OpenRpc13ModelReader reader;

	public OpenRpc13ModelReaderDispatcher(ObjectNode json, OpenRpc13ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (OpenRpc13Contact) node);
	}

	@Override
	public void visitExamplePairing(OpenRpcExamplePairing node) {
		this.reader.readExamplePairing(this.json, (OpenRpc13ExamplePairing) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (OpenRpc13License) node);
	}

	@Override
	public void visitLink(Link node) {
		this.reader.readLink(this.json, (OpenRpc13Link) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (OpenRpc13Tag) node);
	}

	@Override
	public void visitMethod(OpenRpcMethod node) {
		this.reader.readMethod(this.json, (OpenRpc13Method) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (OpenRpc13ExternalDocumentation) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (OpenRpc13Components) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (OpenRpc13Info) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (OpenRpc13ServerVariable) node);
	}

	@Override
	public void visitError(OpenRpcError node) {
		this.reader.readError(this.json, (OpenRpc13Error) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (OpenRpc13Schema) node);
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (OpenRpc13Server) node);
	}

	@Override
	public void visitExample(Example node) {
		this.reader.readExample(this.json, (OpenRpc13Example) node);
	}

	@Override
	public void visitContentDescriptor(OpenRpcContentDescriptor node) {
		this.reader.readContentDescriptor(this.json, (OpenRpc13ContentDescriptor) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (OpenRpc13Document) node);
	}
}