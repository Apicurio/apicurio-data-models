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
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * A command used to add a new pathItem in a document.  Source for the new
 * pathItem must be provided.  This source will be converted to an OAS
 * pathItem object and then added to the data model.
 * @author eric.wittmann@gmail.com
 */
public class AddPathItemCommand extends AbstractCommand {

    public boolean _pathItemExists;
    public String _newPathItemName;
    @JsonDeserialize(using=JsonNodeDeserializer.class)
    public Object _newPathItemObj;
    public boolean _nullPathItems;
    
    AddPathItemCommand() {
    }
    
    AddPathItemCommand(String pathItemName) {
        this._newPathItemName = pathItemName;
    }

    AddPathItemCommand(String pathItemName, Object from) {
        this._newPathItemName = pathItemName;
        this._newPathItemObj = from;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[AddPathItemCommand] Executing.");
        OasDocument doc = (OasDocument) document;
        if (this.isNullOrUndefined(doc.paths)) {
            doc.paths = doc.createPaths();
            this._nullPathItems = true;
        }

        if (!this.isNullOrUndefined(doc.paths.getPathItem(this._newPathItemName))) {
            LoggerCompat.info("[AddPathItemCommand] PathItem with name %s already exists.", this._newPathItemName);
            this._pathItemExists = true;
        } else {
            OasPathItem pathItem = doc.paths.createPathItem(this._newPathItemName);
            Library.readNode(this._newPathItemObj, pathItem);
            doc.paths.addPathItem(this._newPathItemName, pathItem);
            this._pathItemExists = false;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[AddPathItemCommand] Reverting.");
        if (this._pathItemExists) {
            return;
        }
        OasDocument doc = (OasDocument) document;
        if (this._nullPathItems) {
            doc.paths = null;
        } else {
            doc.paths.removePathItem(this._newPathItemName);
        }
    }


}
