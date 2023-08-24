package io.apicurio.datamodels.deref;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Definitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Items;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Parameter;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20ParameterDefinitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Response;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20ResponseDefinitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Schema;

public class OpenApi2NodeImporter extends ReferencedNodeImporter {

    public OpenApi2NodeImporter(Document doc, Node nodeWithUnresolvedRef, String ref, boolean shouldInline) {
        super(doc, nodeWithUnresolvedRef, ref, shouldInline);
    }

    @Override
    public void visitItems(OpenApi20Items node) {
        // Note: there is no place in #/components to store items, so we will inline them.
        ObjectNode json = Library.writeNode(node);
        Library.readNode(json, getNodeWithUnresolvedRef());
        setPathToImportedNode(getNodeWithUnresolvedRef(), null);
    }

    @Override
    public void visitParameter(Parameter node) {
        String collection = "parameters";
        if (shouldInline()) {
            inlineDefinition(collection, node);
        } else {
            OpenApi20Document doc = (OpenApi20Document) getDoc();
            OpenApi20ParameterDefinitions params = doc.getParameters();
            if (params == null) {
                params = doc.createParameterDefinitions();
                doc.setParameters(params);
            }
            String name = generateNodeName(getNameHintFromRef("ImportedParameter"), params.getItemNames());
            params.addItem(name, (OpenApi20Parameter) node);
            node.attach(params);
            setPathToImportedNode(node, collection, name);
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
    public void visitResponse(OpenApiResponse node) {
        String collection = "responses";
        if (shouldInline()) {
            inlineDefinition(collection, node);
        } else {
            OpenApi20Document doc = (OpenApi20Document) getDoc();
            OpenApi20ResponseDefinitions responses = doc.getResponses();
            if (responses == null) {
                responses = doc.createResponseDefinitions();
                doc.setResponses(responses);
            }
            String name = generateNodeName(getNameHintFromRef("ImportedResponse"), responses.getItemNames());
            responses.addItem(name, (OpenApi20Response) node);
            node.attach(responses);
            setPathToImportedNode(node, collection, name);
        }
    }

    @Override
    public void visitSchema(Schema node) {
        String collection = "definitions";
        if (shouldInline()) {
            inlineDefinition(collection, node);
        } else {
            OpenApi20Document doc = (OpenApi20Document) getDoc();
            OpenApi20Definitions definitions = doc.getDefinitions();
            if (definitions == null) {
                definitions = doc.createDefinitions();
                doc.setDefinitions(definitions);
            }
            String name = generateNodeName(getNameHintFromRef("ImportedSchema"), definitions.getItemNames());
            definitions.addItem(name, (OpenApi20Schema) node);
            node.attach(definitions);
            setPathToImportedNode(node, collection, name);
        }
    }

    @Override
    protected void setPathToImportedNode(Node importedNode, String collection, String name) {
        setPathToImportedNode(importedNode, "#/" + collection + "/" + name);
    }

    private void inlineDefinition(String collection, Node node) {
        inlineComponent(collection, node);
    }

}
