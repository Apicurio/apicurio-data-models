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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.models.ExtensibleNode;

/**
 * @author eric.wittmann@gmail.com
 */
public abstract class Server extends ExtensibleNode {

    public String url;
    public String description;
    public Map<String, ServerVariable> variables;

    /**
     * Creates a server variable.
     * @param name
     */
    public abstract ServerVariable createServerVariable(String name);

    /**
     * Adds a server variable.
     * @param name
     * @param serverVariable
     */
    public void addServerVariable(String name, ServerVariable serverVariable) {
        if (this.variables == null) {
            this.variables = new LinkedHashMap<>();
        }
        this.variables.put(name, serverVariable);
    }

    /**
     * Gets a single server variable by name.
     * @param name
     */
    public ServerVariable getServerVariable(String name) {
        if (this.variables != null) {
            return this.variables.get(name);
        }
        return null;
    }

    /**
     * Removes a single server variable and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public ServerVariable removeServerVariable(String name) {
        if (this.variables != null) {
            return this.variables.remove(name);
        }
        return null;
    }

    /**
     * Gets a list of all server variables.
     */
    public List<ServerVariable> getServerVariables() {
        List<ServerVariable> rval = new ArrayList<>();
        if (this.variables != null) {
            rval.addAll(this.variables.values());
        }
        return rval;
    }

}
