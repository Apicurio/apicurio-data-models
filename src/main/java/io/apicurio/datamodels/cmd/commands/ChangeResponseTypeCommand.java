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
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.v2.models.Oas20Response;

/**
 * A command used to modify the type of a response.
 * @author eric.wittmann@gmail.com
 */
public class ChangeResponseTypeCommand extends AbstractCommand {

    public NodePath _responsePath;
    public SimplifiedType _newType;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldSchema;
    
    ChangeResponseTypeCommand() {
    }

    ChangeResponseTypeCommand(Oas20Response response, SimplifiedType newType) {
        this._responsePath = Library.createNodePath(response);
        this._newType = newType;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeResponseTypeCommand] Executing.");
        Oas20Response response = (Oas20Response) this._responsePath.resolve(document);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        this._oldSchema = null;
        if (ModelUtils.isDefined(response.schema)) {
            this._oldSchema = Library.writeNode(response.schema);
        }

        response.schema = response.createSchema();
        SimplifiedTypeUtil.setSimplifiedType(response.schema, this._newType);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeResponseTypeCommand] Reverting.");
        Oas20Response response = (Oas20Response) this._responsePath.resolve(document);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        if (ModelUtils.isDefined(this._oldSchema)) {
            response.schema = response.createSchema();
            Library.readNode(this._oldSchema, response.schema);
        } else {
            response.schema = null;
        }
    }

}
