package io.apicurio.datamodels.deref;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
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
 * @author eric.wittmann@gmail.com
 */
public class OpenApi3NodeImporter extends ReferencedNodeImporter {

    public OpenApi3NodeImporter(Document doc, Node nodeWithUnresolvedRef, String ref, boolean shouldInline) {
        super(doc, nodeWithUnresolvedRef, ref, shouldInline);
    }

    @Override
    protected void setPathToImportedNode(Node importedNode, String type, String name) {
        setPathToImportedNode(importedNode, "#/components/" + type + "/" + name);
    }

    @Override
    public void visitSchema(Schema node) {
        String componentType = "schemas";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            OpenApiComponents components = ensureOpenApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedSchema"), getComponentNames(components.getSchemas()));
            components.addSchema(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitCallback(OpenApiCallback node) {
        String componentType = "callbacks";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            OpenApiComponents components = ensureOpenApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedCallback"), getComponentNames(components.getCallbacks()));
            components.addCallback(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitExample(OpenApiExample node) {
        String componentType = "examples";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            OpenApiComponents components = ensureOpenApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedExample"), getComponentNames(components.getExamples()));
            components.addExample(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitHeader(OpenApiHeader node) {
        String componentType = "headers";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            OpenApiComponents components = ensureOpenApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedHeader"), getComponentNames(components.getHeaders()));
            components.addHeader(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitLink(OpenApiLink node) {
        String componentType = "links";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            OpenApiComponents components = ensureOpenApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedLink"), getComponentNames(components.getLinks()));
            components.addLink(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitParameter(Parameter node) {
        String componentType = "parameters";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            OpenApiComponents components = ensureOpenApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedParameter"), getComponentNames(components.getParameters()));
            components.addParameter(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitPathItem(OpenApiPathItem node) {
        // Note: there is no place in #/components to store path items, so they must be inlined.
        ObjectNode json = Library.writeNode(node);
        Library.readNode(json, getNodeWithUnresolvedRef());
        setPathToImportedNode(getNodeWithUnresolvedRef(), null);
    }

    @Override
    public void visitRequestBody(OpenApiRequestBody node) {
        String componentType = "requestBodies";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            OpenApiComponents components = ensureOpenApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedRequestBody"), getComponentNames(components.getRequestBodies()));
            components.addRequestBody(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitResponse(OpenApiResponse node) {
        String componentType = "responses";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            OpenApiComponents components = ensureOpenApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedResponse"), getComponentNames(components.getResponses()));
            components.addResponse(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
    }

    @Override
    public void visitResponses(OpenApiResponses node) {
        // Note: there is no place in #/components to store a Responses node, so inline it
        ObjectNode json = Library.writeNode(node);
        Library.readNode(json, getNodeWithUnresolvedRef());
        setPathToImportedNode(getNodeWithUnresolvedRef(), null);
    }

    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        String componentType = "securitySchemes";
        if (shouldInline()) {
            inlineComponent(componentType, node);
        } else {
            OpenApiComponents components = ensureOpenApiComponents();
            String name = generateNodeName(getNameHintFromRef("ImportedSecurityScheme"), getComponentNames(components.getSecuritySchemes()));
            components.addSecurityScheme(name, node);
            node.attach(components);
            setPathToImportedNode(node, componentType, name);
        }
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
