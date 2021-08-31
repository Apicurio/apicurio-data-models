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

package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IExampleParent;
import io.apicurio.datamodels.core.models.common.IExamplesParent;

/**
 * A command used to set the Example for a 3.0 MediaType or a 2.0 Response.
 * @author eric.wittmann@gmail.com
 */
public abstract class SetExampleCommand extends AbstractCommand {

    public NodePath _parentPath;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _newExample;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldValue;

    SetExampleCommand() {
    }

    /**
     * Constructor.
     * @param parent should be either {@link IExampleParent} or {@link IExamplesParent}
     * @param example
     */
    SetExampleCommand(Node parent, Object example) {
        this._parentPath = Library.createNodePath(parent);
        this._newExample = example;
    }

}
