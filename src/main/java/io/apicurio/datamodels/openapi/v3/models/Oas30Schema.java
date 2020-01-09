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

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * Models an OpenAPI 3.0.x schema.
 * @author eric.wittmann@gmail.com
 */
public class Oas30Schema extends OasSchema {

    public List<OasSchema> oneOf;
    public List<OasSchema> anyOf;
    public OasSchema not;

    public Oas30Discriminator discriminator;

    public Boolean nullable;
    public Boolean writeOnly;
    public Boolean deprecated;

    /**
     * Creates a child Discriminator model.
     * @return {Oas30Discriminator}
     */
    public Oas30Discriminator createDiscriminator() {
        Oas30Discriminator rval = new Oas30Discriminator();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

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

    /**
     * Creates a child schema model.
     */
    public Oas30OneOfSchema createOneOfSchema() {
        Oas30OneOfSchema rval = new Oas30OneOfSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Creates a child schema model.
     */
    public Oas30AnyOfSchema createAnyOfSchema() {
        Oas30AnyOfSchema rval = new Oas30AnyOfSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /**
     * Creates a child schema model.
     */
    public Oas30NotSchema createNotSchema() {
        Oas30NotSchema rval = new Oas30NotSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }
    
    /**
     * Adds a OneOf schema.
     * @param schema
     */
    public void addOneOfSchema(Oas30OneOfSchema schema) {
        if (this.oneOf == null) {
            this.oneOf = new ArrayList<>();
        }
        this.oneOf.add(schema);
    }
    
    /**
     * Adds an AnyOf schema.
     * @param schema
     */
    public void addAnyOfSchema(Oas30AnyOfSchema schema) {
        if (this.anyOf == null) {
            this.anyOf = new ArrayList<>();
        }
        this.anyOf.add(schema);
    }
    
    /**
     * Removes a oneOf schema.
     * @param schema
     */
    public void removeOneOfSchema(Oas30OneOfSchema schema) {
        if (this.oneOf != null) {
            this.oneOf.remove(schema);
        }
    }
    
    /**
     * Removes a anyOf schema.
     * @param schema
     */
    public void removeAnyOfSchema(Oas30AnyOfSchema schema) {
        if (this.anyOf != null) {
            this.anyOf.remove(schema);
        }
    }
    
    
    /* ************************************************************************
     * Schema subclasses.
     * ************************************************************************ */
    
    public static class Oas30AdditionalPropertiesSchema extends Oas30Schema {

        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOasVisitor viz = (IOasVisitor) visitor;
            viz.visitAdditionalPropertiesSchema(this);
        }

    }

    public static class Oas30ItemsSchema extends Oas30Schema {
        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOasVisitor viz = (IOasVisitor) visitor;
            viz.visitItemsSchema(this);
        }

    }

    public static class Oas30AllOfSchema extends Oas30Schema {

        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOasVisitor viz = (IOasVisitor) visitor;
            viz.visitAllOfSchema(this);
        }

    }


    public static class Oas30NotSchema extends Oas30Schema {

        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOas30Visitor viz = (IOas30Visitor) visitor;
            viz.visitNotSchema(this);
        }

    }

    public static class Oas30OneOfSchema extends Oas30Schema {

        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOas30Visitor viz = (IOas30Visitor) visitor;
            viz.visitOneOfSchema(this);
        }

    }

    public static class Oas30AnyOfSchema extends Oas30Schema {

        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IOas30Visitor viz = (IOas30Visitor) visitor;
            viz.visitAnyOfSchema(this);
        }

    }

    public static class Oas30PropertySchema extends Oas30Schema implements IOasPropertySchema, INamed {
        
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

        @Override
        public String getName() {
            return _propertyName;
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
