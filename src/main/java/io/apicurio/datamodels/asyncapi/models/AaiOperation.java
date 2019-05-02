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

package io.apicurio.datamodels.asyncapi.models;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Tag;

/**
 * @author eric.wittmann@gmail.com
 */
public class AaiOperation extends Operation {

    public List<Tag> tags;
    
    /**
     * Constructor.
     * @param type
     */
    public AaiOperation(String type) {
        super(type);
    }

    /**
     * Creates a Tag node.
     */
    public AaiTag createTag() {
        AaiTag tag = new AaiTag();
        tag._ownerDocument = this.ownerDocument();
        tag._parent = this;
        return tag;
    }

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
     * @see io.apicurio.datamodels.core.models.common.Operation#createExternalDocumentation()
     */
    @Override
    public ExternalDocumentation createExternalDocumentation() {
        AaiExternalDocumentation externalDocs = new AaiExternalDocumentation();
        externalDocs._ownerDocument = this.ownerDocument();
        externalDocs._parent = this;
        return this.externalDocs;
    }

}
