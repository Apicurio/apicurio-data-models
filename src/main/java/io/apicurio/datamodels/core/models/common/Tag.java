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

package io.apicurio.datamodels.core.models.common;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Models a Tag node.
 * @author eric.wittmann@gmail.com
 */
public abstract class Tag extends ExtensibleNode implements IExternalDocumentationParent, INamed {

    public String name;
    public String description;
    public ExternalDocumentation externalDocs;
    
    /**
     * Constructor.
     */
    public Tag() {
    }
    
    /**
     * Constructor.
     */
    public Tag(String name) {
        this.name = name;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        visitor.visitTag(this);
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

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {
        this.name = newName;
    }

}
