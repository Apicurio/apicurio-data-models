/*
 * Copyright 2020 Red Hat
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
package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * An AsyncAPI 2.0.x schema model.
 * 
 * @author laurent.broudoux@gmail.com
 */
public class Aai20Schema extends AaiSchema {

    @Override
    public ExternalDocumentation createExternalDocumentation() {
        ExternalDocumentation rval = new Aai20ExternalDocumentation();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    @Override
    public AaiSchema createAllOfSchema() {
        Aai20AllOfSchema rval = new Aai20AllOfSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    @Override
    public AaiSchema createOneOfSchema() {
        Aai20OneOfSchema rval = new Aai20OneOfSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    @Override
    public AaiSchema createAnyOfSchema() {
        Aai20AnyOfSchema rval = new Aai20AnyOfSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    @Override
    public AaiSchema createNotSchema() {
        Aai20NotSchema rval = new Aai20NotSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    @Override
    public AaiSchema createItemsSchema() {
        Aai30ItemsSchema rval = new Aai30ItemsSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    @Override
    public AaiSchema createAdditionalPropertiesSchema() {
        Aai20AdditionalPropertiesSchema rval = new Aai20AdditionalPropertiesSchema();
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    @Override
    public AaiSchema createPropertySchema(String propertyName) {
        Aai20PropertySchema rval = new Aai20PropertySchema(propertyName);
        rval._ownerDocument = this.ownerDocument();
        rval._parent = this;
        return rval;
    }

    /* ************************************************************************
     * Schema subclasses.
     * ************************************************************************ */

    public static class Aai20AllOfSchema extends Aai20Schema {
        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IAaiVisitor viz = (IAaiVisitor) visitor;
            viz.visitAllOfSchema(this);
        }
    }

    public static class Aai20OneOfSchema extends Aai20Schema {
        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IAaiVisitor viz = (IAaiVisitor) visitor;
            viz.visitOneOfSchema(this);
        }
    }

    public static class Aai20AnyOfSchema extends Aai20Schema {
        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IAaiVisitor viz = (IAaiVisitor) visitor;
            viz.visitAnyOfSchema(this);
        }
    }

    public static class Aai20NotSchema extends Aai20Schema {
        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IAaiVisitor viz = (IAaiVisitor) visitor;
            viz.visitNotSchema(this);
        }
    }

    public static class Aai20AdditionalPropertiesSchema extends Aai20Schema {
        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IAaiVisitor viz = (IAaiVisitor) visitor;
            viz.visitAdditionalPropertiesSchema(this);
        }
    }

    public static class Aai30ItemsSchema extends Aai20Schema {
        /**
         * @see io.apicurio.datamodels.core.models.common.Schema#accept(io.apicurio.datamodels.core.visitors.IVisitor)
         */
        @Override
        public void accept(IVisitor visitor) {
            IAaiVisitor viz = (IAaiVisitor) visitor;
            viz.visitItemsSchema(this);
        }

    }

    public static class Aai20PropertySchema extends Aai20Schema implements IPropertySchema, INamed {

        private String _propertyName;

        /**
         * Constructor.
         * @param propertyName
         */
        public Aai20PropertySchema(String propertyName) {
            this._propertyName = propertyName;
        }

        /**
         * @see io.apicurio.datamodels.core.models.common.IPropertySchema#getPropertyName()
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
         * @see io.apicurio.datamodels.core.models.common.IPropertySchema#rename(java.lang.String)
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
            IAaiVisitor viz = (IAaiVisitor) visitor;
            viz.visitPropertySchema((IPropertySchema) this);
        }

    }
}
