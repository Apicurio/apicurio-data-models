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

package io.apicurio.datamodels.core.models;

import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * The base class for all document nodes.  This base class is extended
 * by the OpenAPI and AsyncAPI data model classes.  A Document model
 * represents the root node of the data model.
 * @author eric.wittmann@gmail.com
 */
public abstract class Document extends ExtensibleNode {
    
    public abstract DocumentType getDocumentType();
    
    /**
     * Constructor.
     */
    public Document() {
        this._ownerDocument = this;
        this._parent = null;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitDocument(this);
    }

}
