/*
 * Copyright 2019 JBoss Inc
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

package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to modify the simple property of a node.  Should not be used
 * to modify complex (object) properties, only simple property types like
 * string, boolean, number, etc.  This differs from {@link ChangePropertyCommand}
 * in that it can be constructed from a {@link NodePath} rather than a {@link Node}
 * and therefore can be used in {@link AggregateCommand} use-cases where there is
 * a Create followed by a SetProperty.
 * 
 * @author eric.wittmann@gmail.com
 */
public class SetPropertyCommand<T> extends ChangePropertyCommand<T> {

    /**
     * Constructor.
     */
    SetPropertyCommand() {
    }

    /**
     * C'tor.
     */
    SetPropertyCommand(NodePath nodePath, String property, T newValue) {
        super();
        if (ModelUtils.isDefined(nodePath)) {
            this._nodePath = nodePath;
        }
        this._property = property;
        this._newValue = newValue;
    }

}
