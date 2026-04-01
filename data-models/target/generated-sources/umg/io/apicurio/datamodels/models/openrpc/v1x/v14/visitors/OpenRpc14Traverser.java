package io.apicurio.datamodels.models.openrpc.v1x.v14.visitors;

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
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14ContentDescriptor;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Document;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14ExamplePairing;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Info;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Link;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Method;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Schema;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Server;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Tag;
import io.apicurio.datamodels.models.visitors.AbstractTraverser;
import io.apicurio.datamodels.models.visitors.Visitor;

public class OpenRpc14Traverser extends AbstractTraverser implements OpenRpc14Visitor {

	public OpenRpc14Traverser(Visitor visitor) {
		super(visitor);
	}

	@Override
	public void visitContact(Contact node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitExamplePairing(OpenRpcExamplePairing node) {
		node.accept(this.visitor);
		OpenRpc14ExamplePairing model = (OpenRpc14ExamplePairing) node;
		this.traverseList("params", model.getParams());
		this.traverseNode("result", model.getResult());
	}

	@Override
	public void visitLicense(License node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitLink(Link node) {
		node.accept(this.visitor);
		OpenRpc14Link model = (OpenRpc14Link) node;
		this.traverseNode("server", model.getServer());
	}

	@Override
	public void visitTag(Tag node) {
		node.accept(this.visitor);
		OpenRpc14Tag model = (OpenRpc14Tag) node;
		this.traverseNode("externalDocs", model.getExternalDocs());
	}

	@Override
	public void visitMethod(OpenRpcMethod node) {
		node.accept(this.visitor);
		OpenRpc14Method model = (OpenRpc14Method) node;
		this.traverseList("tags", model.getTags());
		this.traverseNode("externalDocs", model.getExternalDocs());
		this.traverseList("params", model.getParams());
		this.traverseNode("result", model.getResult());
		this.traverseList("servers", model.getServers());
		this.traverseList("errors", model.getErrors());
		this.traverseList("links", model.getLinks());
		this.traverseList("examples", model.getExamples());
	}

	@Override
	public void visitExternalDocumentation(ExternalDocumentation node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitComponents(Components node) {
		node.accept(this.visitor);
		OpenRpc14Components model = (OpenRpc14Components) node;
		this.traverseMap("contentDescriptors", model.getContentDescriptors());
		this.traverseMap("schemas", model.getSchemas());
		this.traverseMap("examples", model.getExamples());
		this.traverseMap("links", model.getLinks());
		this.traverseMap("errors", model.getErrors());
		this.traverseMap("examplePairings", model.getExamplePairings());
		this.traverseMap("tags", model.getTags());
	}

	@Override
	public void visitInfo(Info node) {
		node.accept(this.visitor);
		OpenRpc14Info model = (OpenRpc14Info) node;
		this.traverseNode("contact", model.getContact());
		this.traverseNode("license", model.getLicense());
	}

	@Override
	public void visitServerVariable(ServerVariable node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitError(OpenRpcError node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitSchema(Schema node) {
		node.accept(this.visitor);
		OpenRpc14Schema model = (OpenRpc14Schema) node;
		this.traverseNode("if", model.getIf());
		this.traverseNode("then", model.getThen());
		this.traverseNode("else", model.getElse());
		this.traverseMap("properties", model.getProperties());
		this.traverseUnion("additionalProperties", model.getAdditionalProperties());
		this.traverseNode("additionalItems", model.getAdditionalItems());
		this.traverseUnion("items", model.getItems());
		this.traverseNode("propertyNames", model.getPropertyNames());
		this.traverseNode("contains", model.getContains());
		this.traverseList("allOf", model.getAllOf());
		this.traverseList("oneOf", model.getOneOf());
		this.traverseList("anyOf", model.getAnyOf());
		this.traverseNode("not", model.getNot());
	}

	@Override
	public void visitServer(Server node) {
		node.accept(this.visitor);
		OpenRpc14Server model = (OpenRpc14Server) node;
		this.traverseMap("variables", model.getVariables());
	}

	@Override
	public void visitExample(Example node) {
		node.accept(this.visitor);
	}

	@Override
	public void visitContentDescriptor(OpenRpcContentDescriptor node) {
		node.accept(this.visitor);
		OpenRpc14ContentDescriptor model = (OpenRpc14ContentDescriptor) node;
		this.traverseNode("schema", model.getSchema());
	}

	@Override
	public void visitDocument(Document node) {
		node.accept(this.visitor);
		OpenRpc14Document model = (OpenRpc14Document) node;
		this.traverseNode("info", model.getInfo());
		this.traverseList("servers", model.getServers());
		this.traverseList("methods", model.getMethods());
		this.traverseNode("components", model.getComponents());
		this.traverseNode("externalDocs", model.getExternalDocs());
	}
}