package io.apicurio.datamodels.deref;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.openapi.OpenApiCallback;
import io.apicurio.datamodels.models.openapi.OpenApiComponents;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiLink;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiRequestBody;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.OpenApiResponses;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * Imports an external Node into a OpenAPI 3 document.  Used during dereferencing to internalize
 * an external reference.  This importer figures out what kind of thing is being internalized
 * so it can be put in the right place.
 *
 * TODO when the source of the $ref is a definition/component *and* the only property is the $ref,
 *      we can just replace the contents of the source with the external content rather than import
 *      as a new definition/component and change the pointer
 *
 * @author eric.wittmann@gmail.com
 */
public class OpenApi3NodeImporter extends ReferencedNodeImporter {

    public OpenApi3NodeImporter(Document doc, String ref) {
        super(doc, ref);
    }

    private void setPathToImportedNode(String type, String name) {
        setPathToImportedNode("#/components/" + type + "/" + name);
    }

    @Override
    public void visitSchema(Schema node) {
        OpenApiComponents components = ensureOpenApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedSchema"), getComponentNames(components.getSchemas()));
        components.addSchema(name, node);
        node.attach(components);
        setPathToImportedNode("schemas", name);
    }

    @Override
    public void visitCallback(OpenApiCallback node) {
        OpenApiComponents components = ensureOpenApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedCallback"), getComponentNames(components.getCallbacks()));
        components.addCallback(name, node);
        node.attach(components);
        setPathToImportedNode("callbacks", name);
    }

    @Override
    public void visitExample(OpenApiExample node) {
        OpenApiComponents components = ensureOpenApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedExample"), getComponentNames(components.getExamples()));
        components.addExample(name, node);
        node.attach(components);
        setPathToImportedNode("examples", name);
    }

    @Override
    public void visitHeader(OpenApiHeader node) {
        OpenApiComponents components = ensureOpenApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedHeader"), getComponentNames(components.getHeaders()));
        components.addHeader(name, node);
        node.attach(components);
        setPathToImportedNode("headers", name);
    }

    @Override
    public void visitLink(OpenApiLink node) {
        OpenApiComponents components = ensureOpenApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedLink"), getComponentNames(components.getLinks()));
        components.addLink(name, node);
        node.attach(components);
        setPathToImportedNode("links", name);
    }

    @Override
    public void visitParameter(Parameter node) {
        OpenApiComponents components = ensureOpenApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedParameter"), getComponentNames(components.getParameters()));
        components.addParameter(name, node);
        node.attach(components);
        setPathToImportedNode("parameters", name);
    }

    @Override
    public void visitPathItem(OpenApiPathItem node) {
        // TODO support importing path items!  these cannot be put into #/components
    }

    @Override
    public void visitRequestBody(OpenApiRequestBody node) {
        OpenApiComponents components = ensureOpenApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedRequestBody"), getComponentNames(components.getRequestBodies()));
        components.addRequestBody(name, node);
        node.attach(components);
        setPathToImportedNode("requestBodies", name);
    }

    @Override
    public void visitResponse(OpenApiResponse node) {
        OpenApiComponents components = ensureOpenApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedResponse"), getComponentNames(components.getResponses()));
        components.addResponse(name, node);
        node.attach(components);
        setPathToImportedNode("responses", name);
    }

    @Override
    public void visitResponses(OpenApiResponses node) {
        // TODO support responses
    }

    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        OpenApiComponents components = ensureOpenApiComponents();
        String name = generateNodeName(getNameHintFromRef("ImportedSecurityScheme"), getComponentNames(components.getSecuritySchemes()));
        components.addSecurityScheme(name, node);
        node.attach(components);
        setPathToImportedNode("securitySchemes", name);
    }

    private OpenApiComponents ensureOpenApiComponents() {
        if (ModelTypeUtil.isOpenApi30Model(getDoc())) {
            OpenApi30Document doc = (OpenApi30Document) getDoc();
            if (doc.getComponents() == null) {
                doc.setComponents(doc.createComponents());
            }
            return doc.getComponents();
        }
        if (ModelTypeUtil.isOpenApi31Model(getDoc())) {
            OpenApi31Document doc = (OpenApi31Document) getDoc();
            if (doc.getComponents() == null) {
                doc.setComponents(doc.createComponents());
            }
            return doc.getComponents();
        }
        return null;
    }

}
