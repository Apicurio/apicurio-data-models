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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * All data models that can be extended using the "x-*" approach to adding vendor extension 
 * properties must extend this class.
 * @author eric.wittmann@gmail.com
 */
public abstract class ExtensibleNode extends Node {

    protected Map<String, Extension> _extensions;
   
    /**
     * @see io.apicurio.datamodels.core.models.Node#isExtensible()
     */
    @Override
    public boolean isExtensible() {
        return true;
    }

    /**
     * Called to create an extension child.
     */
    public Extension createExtension() {
        Extension extension = new Extension();
        extension._ownerDocument = this.ownerDocument();
        extension._parent = this;
        return extension;
    }
    
    /**
     * Called to add a named extension to this node.
     * @param name
     * @param extension
     */
    public void addExtension(String name, Extension extension) {
        if (this._extensions == null) {
            this._extensions = new LinkedHashMap<>();
        }
        this._extensions.put(name, extension);
    }
    
    /**
     * Returns all of the extensions defined on this node.
     */
    public Collection<Extension> getExtensions() {
        if (this._extensions != null) {
            return new ArrayList<>(this._extensions.values());
        } else {
            return null;
        }
    }
    
    /**
     * Gets a single extension by name.
     * @param name
     */
    public Extension getExtension(String name) {
        Extension rval = null;
        if (this._extensions != null) {
            rval = this._extensions.get(name);
        }
        return rval;
    }

    /**
     * Removes a single extension by name.
     * @param name
     */
    public void removeExtension(String name) {
        if (this._extensions != null) {
            this._extensions.remove(name);
        }
    }
    
    /**
     * Removes all extensions from this node.
     */
    public void clearExtensions() {
        if (this._extensions != null) {
            this._extensions.clear();
        }
    }
    
}
