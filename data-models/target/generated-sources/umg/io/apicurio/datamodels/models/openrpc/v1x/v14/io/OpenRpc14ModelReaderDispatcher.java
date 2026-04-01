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

public class OpenRpc14ModelReaderDispatcher implements OpenRpc14Visitor {

	private final ObjectNode json;
	private final OpenRpc14ModelReader reader;

	public OpenRpc14ModelReaderDispatcher(ObjectNode json, OpenRpc14ModelReader reader) {
		this.json = json;
		this.reader = reader;
	}

	@Override
	public void visitContact(Contact node) {
		this.reader.readContact(this.json, (OpenRpc14Contact) node);
	}

	@Override
	public void visitExamplePairing(OpenRpcExamplePairing node) {
		this.reader.readExamplePairing(this.json, (OpenRpc14ExamplePairing) node);
	}

	@Override
	public void visitLicense(License node) {
		this.reader.readLicense(this.json, (OpenRpc14License) node);
	}

	@Override
	public void visitLink(Link node) {
		this.reader.readLink(this.json, (OpenRpc14Link) node);
	}

	@Override
	public void visitTag(Tag node) {
		this.reader.readTag(this.json, (OpenRpc14Tag) node);
	}

	@Override
	public void visitMethod(OpenRpcMethod node) {
		this.reader.readMethod(this.json, (OpenRpc14Method) node);
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		this.reader.readExternalDocumentation(this.json, (OpenRpc14ExternalDocumentation) node);
	}

	@Override
	public void visitComponents(Components node) {
		this.reader.readComponents(this.json, (OpenRpc14Components) node);
	}

	@Override
	public void visitInfo(Info node) {
		this.reader.readInfo(this.json, (OpenRpc14Info) node);
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		this.reader.readServerVariable(this.json, (OpenRpc14ServerVariable) node);
	}

	@Override
	public void visitError(OpenRpcError node) {
		this.reader.readError(this.json, (OpenRpc14Error) node);
	}

	@Override
	public void visitSchema(Schema node) {
		this.reader.readSchema(this.json, (OpenRpc14Schema) node);
	}

	@Override
	public void visitServer(Server node) {
		this.reader.readServer(this.json, (OpenRpc14Server) node);
	}

	@Override
	public void visitExample(Example node) {
		this.reader.readExample(this.json, (OpenRpc14Example) node);
	}

	@Override
	public void visitContentDescriptor(OpenRpcContentDescriptor node) {
		this.reader.readContentDescriptor(this.json, (OpenRpc14ContentDescriptor) node);
	}

	@Override
	public void visitDocument(Document node) {
		this.reader.readDocument(this.json, (OpenRpc14Document) node);
	}
}