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

package io.apicurio.datamodels.openapi.models;

import java.util.List;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.openapi.visitors.IOasVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class OasPathItem extends ExtensibleNode implements IOasParameterParent {

    private String _path;
    public String $ref;
    public OasOperation get;
    public OasOperation put;
    public OasOperation post;
    public OasOperation delete;
    public OasOperation options;
    public OasOperation head;
    public OasOperation patch;
    public List<OasParameter> parameters;
    
    /**
     * Constructor.
     * @param path
     */
    public OasPathItem(String path) {
        this._path = path;
    }
    
    /**
     * Gets the path string.
     */
    public String getPath() {
        return this._path;
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IOasVisitor oasVisitor = (IOasVisitor) visitor;
        oasVisitor.visitPathItem(this);
    }

}
