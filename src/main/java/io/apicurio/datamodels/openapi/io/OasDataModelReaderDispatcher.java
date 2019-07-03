/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.openapi.io;

import io.apicurio.datamodels.core.io.DataModelReaderDispatcher;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * Implements an OpenAPI version of a data model reader dispatcher.
 * @author eric.wittmann@gmail.com
 */
public class OasDataModelReaderDispatcher extends DataModelReaderDispatcher implements IOasVisitor {

    /**
     * Constructor.
     * @param json
     * @param reader
     */
    public OasDataModelReaderDispatcher(Object json, OasDataModelReader reader) {
        super(json, reader);
    }
    
    /**
     * Casts the reader.
     */
    private OasDataModelReader oasReader() {
        return (OasDataModelReader) this.reader;
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPaths(io.apicurio.datamodels.openapi.models.OasPaths)
     */
    @Override
    public void visitPaths(OasPaths node) {
        this.oasReader().readPaths(this.json, node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void visitPathItem(OasPathItem node) {
        this.oasReader().readPathItem(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponse(io.apicurio.datamodels.openapi.models.OasResponse)
     */
    @Override
    public void visitResponse(OasResponse node) {
        this.oasReader().readResponse(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponses(io.apicurio.datamodels.openapi.models.OasResponses)
     */
    @Override
    public void visitResponses(OasResponses node) {
        this.oasReader().readResponses(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitXML(io.apicurio.datamodels.openapi.models.OasXML)
     */
    @Override
    public void visitXML(OasXML node) {
        this.oasReader().readXML(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitAllOfSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAllOfSchema(OasSchema node) {
        this.oasReader().readSchema(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitItemsSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitItemsSchema(OasSchema node) {
        this.oasReader().readSchema(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitAdditionalPropertiesSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAdditionalPropertiesSchema(OasSchema node) {
        this.oasReader().readSchema(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPropertySchema(io.apicurio.datamodels.openapi.models.IOasPropertySchema)
     */
    @Override
    public void visitPropertySchema(IOasPropertySchema node) {
        this.oasReader().readSchema(this.json, (Schema) node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitHeader(io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    public void visitHeader(OasHeader node) {
        this.oasReader().readHeader(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponseDefinition(io.apicurio.datamodels.openapi.models.IDefinition)
     */
    @Override
    public void visitResponseDefinition(IDefinition node) {
        this.oasReader().readResponse(this.json, (OasResponse) node);
        
    }

}
