package io.apicurio.datamodels.deref;

import io.apicurio.datamodels.models.Document;
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

    public OpenApi2NodeImporter(Document doc, String ref) {
        super(doc, ref);
    }

    @Override
    public void visitItems(OpenApi20Items node) {
        // TODO how to import an "items" object?  Do we import it as a schema definition?
    }

    @Override
    public void visitParameter(Parameter node) {
        OpenApi20Document doc = (OpenApi20Document) getDoc();
        OpenApi20ParameterDefinitions params = doc.getParameters();
        if (params == null) {
            params = doc.createParameterDefinitions();
            doc.setParameters(params);
        }
        String name = generateNodeName(getNameHintFromRef("ImportedParameter"), params.getItemNames());
        params.addItem(name, (OpenApi20Parameter) node);
        node.attach(params);
        setPathToImportedNode("parameters", name);
    }

    @Override
    public void visitPathItem(OpenApiPathItem node) {
        // TODO how to import a path item?  inline it??
    }

    @Override
    public void visitResponse(OpenApiResponse node) {
        OpenApi20Document doc = (OpenApi20Document) getDoc();
        OpenApi20ResponseDefinitions responses = doc.getResponses();
        if (responses == null) {
            responses = doc.createResponseDefinitions();
            doc.setResponses(responses);
        }
        String name = generateNodeName(getNameHintFromRef("ImportedResponse"), responses.getItemNames());
        responses.addItem(name, (OpenApi20Response) node);
        node.attach(responses);
        setPathToImportedNode("responses", name);
    }

    @Override
    public void visitSchema(Schema node) {
        OpenApi20Document doc = (OpenApi20Document) getDoc();
        OpenApi20Definitions definitions = doc.getDefinitions();
        if (definitions == null) {
            definitions = doc.createDefinitions();
            doc.setDefinitions(definitions);
        }
        String name = generateNodeName(getNameHintFromRef("ImportedSchema"), definitions.getItemNames());
        definitions.addItem(name, (OpenApi20Schema) node);
        node.attach(definitions);
        setPathToImportedNode("definitions", name);
    }

    private void setPathToImportedNode(String collection, String name) {
        setPathToImportedNode("#/" + collection + "/" + name);
    }

}
