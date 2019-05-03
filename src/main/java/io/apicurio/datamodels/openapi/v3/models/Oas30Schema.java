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

package io.apicurio.datamodels.openapi.v3.models;

import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * Models an OpenAPI 3.0.x schema.
 * @author eric.wittmann@gmail.com
 */
public class Oas30Schema extends OasSchema {

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createExternalDocumentation()
     */
    @Override
    public ExternalDocumentation createExternalDocumentation() {
        ExternalDocumentation rval = new Oas30ExternalDocumentation();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createXML()
     */
    @Override
    public OasXML createXML() {
        OasXML rval = new Oas30XML();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createAllOfSchema()
     */
    @Override
    public OasSchema createAllOfSchema() {
        Oas30AllOfSchema rval = new Oas30AllOfSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createItemsSchema()
     */
    @Override
    public OasSchema createItemsSchema() {
        Oas30ItemsSchema rval = new Oas30ItemsSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createAdditionalPropertiesSchema()
     */
    @Override
    public OasSchema createAdditionalPropertiesSchema() {
        Oas30AdditionalPropertiesSchema rval = new Oas30AdditionalPropertiesSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createPropertySchema(java.lang.String)
     */
    @Override
    public OasSchema createPropertySchema(String propertyName) {
        Oas30PropertySchema rval = new Oas30PropertySchema(propertyName);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    
    public class Oas30AdditionalPropertiesSchema extends Oas30Schema {

        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOasVisitor viz = (IOasVisitor) visitor;
            viz.visitAdditionalPropertiesSchema(this);
        }

    }

    public class Oas30ItemsSchema extends Oas30Schema {
        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOasVisitor viz = (IOasVisitor) visitor;
            viz.visitItemsSchema(this);
        }

    }

    public class Oas30AllOfSchema extends Oas30Schema {

        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOasVisitor viz = (IOasVisitor) visitor;
            viz.visitAllOfSchema(this);
        }

    }

    public class Oas30PropertySchema extends Oas30Schema implements IOasPropertySchema {
        
        private String _propertyName;
        
        /**
         * Constructor.
         * @param propertyName
         */
        public Oas30PropertySchema(String propertyName) {
            this._propertyName = propertyName;
        }
        
        /**
         * @see io.apicurio.datamodels.openapi.models.IOasPropertySchema#getPropertyName()
         */
        @Override
        public String getPropertyName() {
            return this._propertyName;
        }

        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOasVisitor viz = (IOasVisitor) visitor;
            viz.visitPropertySchema((IOasPropertySchema) this);
        }

    }

}
