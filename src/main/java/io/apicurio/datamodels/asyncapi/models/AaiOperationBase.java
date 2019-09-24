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
import java.util.LinkedList;
import java.util.List;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Tag;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiOperationBase extends Operation {

    public String $ref;
    public List<Tag> tags;
    public AaiOperationBindings bindings;
    
    /**
     * Constructor.
     */
    public AaiOperationBase(String opType) {
        super(opType);
    }

    /**
     * Constructor.
     * @param parent
     * @param opType
     */
    public AaiOperationBase(Node parent, String opType) {
        super(opType);
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiOperationBase(Node parent) {
        this(parent, null);
    }

    /**
     * Adds a tag.
     * @param tag
     */
    public void addTag(AaiTag tag) {
        if(tags == null)
            tags = new LinkedList<>();
        tags.add(tag);
    }

    /**
     * Adds a tag.
     *
     * @param name
     * @param description
     */
    public AaiTag addTag(String name, String description) {
        AaiTag tag = this.createTag();
        tag.name = name;
        tag.description = description;
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tag);
        return tag;
    }
    
    public abstract AaiTag createTag();
}
