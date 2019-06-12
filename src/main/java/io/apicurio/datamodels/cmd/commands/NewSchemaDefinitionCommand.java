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

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;

/**
 * A command used to create a new definition in a document.
 * @author eric.wittmann@gmail.com
 */
public abstract class NewSchemaDefinitionCommand extends AbstractCommand {

    public String _newDefinitionName;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _newDefinitionExample;
    public String _newDefinitionDescription;

    public boolean _defExisted;
    
    NewSchemaDefinitionCommand() {
    }
    
    NewSchemaDefinitionCommand(String definitionName, Object example, String description) {
        this._newDefinitionName = definitionName;
        this._newDefinitionExample = example;
        this._newDefinitionDescription = description;
    }

}
