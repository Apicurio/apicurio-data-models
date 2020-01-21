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

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.IExternalDocumentationParent;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.Tag;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * The base class for all document nodes.  This base class is extended
 * by the OpenAPI and AsyncAPI data model classes.  A Document model
 * represents the root node of the data model.
 * @author eric.wittmann@gmail.com
 */
public abstract class Document extends ExtensibleNode implements IExternalDocumentationParent {

    public Info info;
    public List<Tag> tags;
    public ExternalDocumentation externalDocs;

    /**
     * Constructor.
     */
    public Document() {
        this._ownerDocument = this;
        this._parent = null;
    }

    /**
     * Called to get the type of document.
     */
    public abstract DocumentType getDocumentType();

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        visitor.visitDocument(this);
    }
    
    /**
     * Creates an Info node.
     */
    public abstract Info createInfo();

    /**
     * Creates a Tag node.
     */
    public abstract Tag createTag();

    /**
     * Adds a tag.
     * @param name
     * @param description
     */
    public Tag addTag(String name, String description) {
        Tag tag = this.createTag();
        tag.name = name;
        tag.description = description;
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tag);
        return tag;
    }

    /**
     * Creates an External Documentation node.
     */
    public abstract ExternalDocumentation createExternalDocumentation();
    
    /**
     * @see io.apicurio.datamodels.core.models.common.IExternalDocumentationParent#setExternalDocumentation(io.apicurio.datamodels.core.models.common.ExternalDocumentation)
     */
    @Override
    public void setExternalDocumentation(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }
    
    /**
     * Sets the external documentation information.
     * @param description
     * @param url
     */
    public ExternalDocumentation setExternalDocumentation(String description, String url) {
        ExternalDocumentation ed = this.createExternalDocumentation();
        ed.description = description;
        ed.url = url;
        this.externalDocs = ed;
        return ed;
    }

    @Override
    public boolean isAttached() {
        return true;
    }
}
