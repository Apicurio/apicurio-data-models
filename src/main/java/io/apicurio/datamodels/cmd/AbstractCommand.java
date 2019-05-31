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

package io.apicurio.datamodels.cmd;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;

/**
 * A base class for all command implementations.
 * @author eric.wittmann@gmail.com
 */
public abstract class AbstractCommand {
    
    /**
     * Constructor.
     */
    public AbstractCommand() {
    }

    /**
     * Returns true if the argument is either null or undefined.
     * @param object
     */
    protected boolean isNullOrUndefined(Object object) {
        return NodeCompat.isNullOrUndefined(object);
    }

    /**
     * Returns the type of the command (i.e. the command's class name).
     */
    protected final String type() {
        return this.getClass().getSimpleName();
    }

    /**
     * Marshall the command into a JS object.
     */
    public Object marshall() {
        String cmdType = this.type();
        Object to = JsonCompat.objectNode();
        JsonCompat.setPropertyString(to, Constants.PROP___TYPE, cmdType);
        return to;
    }

    /**
     * Unmarshall the JS object.
     * @param obj
     */
    public void unmarshall(Object from) {
    }

}
