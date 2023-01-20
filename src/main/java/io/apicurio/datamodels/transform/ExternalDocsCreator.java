package io.apicurio.datamodels.transform;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;

public class ExternalDocsCreator extends CombinedVisitorAdapter {

    ExternalDocumentation externalDocs;

    @Override
    public void visitSchema(Schema node) {
        externalDocs = node.createExternalDocumentation();
        node.setExternalDocs(externalDocs);
    }

    @Override
    public void visitDocument(Document node) {
        externalDocs = node.createExternalDocumentation();
        node.setExternalDocs(externalDocs);
    }

    @Override
    public void visitOperation(Operation node) {
        externalDocs = node.createExternalDocumentation();
        node.setExternalDocs(externalDocs);
    }

    @Override
    public void visitTag(Tag node) {
        externalDocs = node.createExternalDocumentation();
        node.setExternalDocs(externalDocs);
    }

}
