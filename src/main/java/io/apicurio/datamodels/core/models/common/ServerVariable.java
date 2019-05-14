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

import java.util.List;

import io.apicurio.datamodels.core.models.ExtensibleNode;

/**
 * Models a server variable.
 * @author eric.wittmann@gmail.com
 */
public abstract class ServerVariable extends ExtensibleNode {
    
    public final String _name;
    public List<String> enum_;
    public String default_;
    public String description;
    
    /**
     * Constructor.
     */
    public ServerVariable(String name) {
        this._name = name;
    }
    
    /**
     * Gets the server variable name.
     */
    public String getName() {
        return this._name;
    }

}
