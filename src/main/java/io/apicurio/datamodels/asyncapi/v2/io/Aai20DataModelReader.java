package io.apicurio.datamodels.asyncapi.v2.io;

import io.apicurio.datamodels.asyncapi.io.AaiDataModelReader;
import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.IAaiNodeFactory;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Components;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SchemaDefinition;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Components;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20DataModelReader extends AaiDataModelReader {

    private IAaiNodeFactory nodeFactory;

    public Aai20DataModelReader(Aai20NodeFactory nodeFactory) {
        super(nodeFactory);
        this.nodeFactory = nodeFactory;
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, Document node) {
        AaiDocument doc = (AaiDocument) node;
        
        // components
        /*
        Object json_ = JsonCompat.consumeProperty(json, Constants.PROP_COMPONENTS);
        if (json_ != null) {
            AaiComponents components = nodeFactory.createComponents(node);
            this.readComponents(json_, components);
            doc.components = components;
        }
        */
        
        super.readDocument(json, node);
    }


    @Override
    public void readComponents(Object json, Components node) {
        Aai20Components components = (Aai20Components) node;
        // schemas
        final Object schemas = JsonCompat.consumeProperty(json, Constants.PROP_SCHEMAS);
        if (schemas != null) {
            JsonCompat.keys(schemas).forEach(name -> {
                Object jsonValue = JsonCompat.consumeProperty(schemas, name);
                Aai20SchemaDefinition schemaModel = new Aai20SchemaDefinition(node, name);
                this.readSchema(jsonValue, schemaModel);
                components.addSchemaDefinition(name, schemaModel);
            });
        }

        super.readComponents(json, node);
    }
}
