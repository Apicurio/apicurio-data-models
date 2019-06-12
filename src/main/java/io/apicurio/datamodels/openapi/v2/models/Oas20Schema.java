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

package io.apicurio.datamodels.openapi.v2.models;

import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas20Schema extends OasSchema {

    public String discriminator;

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createExternalDocumentation()
     */
    @Override
    public ExternalDocumentation createExternalDocumentation() {
        Oas20ExternalDocumentation rval = new Oas20ExternalDocumentation();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createXML()
     */
    @Override
    public OasXML createXML() {
        Oas20XML rval = new Oas20XML();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createAllOfSchema()
     */
    @Override
    public OasSchema createAllOfSchema() {
        Oas20AllOfSchema rval = new Oas20AllOfSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createItemsSchema()
     */
    @Override
    public OasSchema createItemsSchema() {
        Oas20ItemsSchema rval = new Oas20ItemsSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createAdditionalPropertiesSchema()
     */
    @Override
    public OasSchema createAdditionalPropertiesSchema() {
        Oas20AdditionalPropertiesSchema rval = new Oas20AdditionalPropertiesSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * @see io.apicurio.datamodels.openapi.models.OasSchema#createPropertySchema(java.lang.String)
     */
    @Override
    public OasSchema createPropertySchema(String propertyName) {
        Oas20PropertySchema rval = new Oas20PropertySchema(propertyName);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    
    /* ************************************************************************
     * Schema subclasses.
     * ************************************************************************ */

    
    public static class Oas20AdditionalPropertiesSchema extends Oas20Schema {

        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOasVisitor viz = (IOasVisitor) visitor;
            viz.visitAdditionalPropertiesSchema(this);
        }

    }

    public static class Oas20ItemsSchema extends Oas20Schema {
        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOasVisitor viz = (IOasVisitor) visitor;
            viz.visitItemsSchema(this);
        }

    }

    public static class Oas20AllOfSchema extends Oas20Schema {

        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOasVisitor viz = (IOasVisitor) visitor;
            viz.visitAllOfSchema(this);
        }

    }

    public static class Oas20PropertySchema extends Oas20Schema implements IOasPropertySchema {
        
        private String _propertyName;
        
        /**
         * Constructor.
         * @param propertyName
         */
        public Oas20PropertySchema(String propertyName) {
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
         * @see io.apicurio.datamodels.openapi.models.IOasPropertySchema#rename(java.lang.String)
         */
        @Override
        public void rename(String newName) {
            this._propertyName = newName;
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
