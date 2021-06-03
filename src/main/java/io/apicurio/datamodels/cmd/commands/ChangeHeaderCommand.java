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
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;

/**
 * @author vvilerio
 */
public class ChangeHeaderCommand extends AbstractCommand {

    public NodePath _headerPath;
    public String _headerName;
    public OasHeader _newHeader;

    public boolean _changed;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldHeaderSchema;

    ChangeHeaderCommand() {
    }

    ChangeHeaderCommand(Oas30Header header, OasHeader newHeader) {
        this._headerName = header.getName();
        this._headerPath = Library.createNodePath(header);
        this._newHeader = newHeader;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeHeaderCommand] Executing.");
        this._changed = false;

        Oas30Header header = (Oas30Header) this._headerPath.resolve(document);
        if (this.isNullOrUndefined(header)) {
            return;
        }

        // Save the old info (for later undo operation)
        if (this.isNullOrUndefined(header.schema)) {
            this._oldHeaderSchema = null;
            header.schema = header.createSchema();
        } else {
            this._oldHeaderSchema = Library.writeNode(header.schema);
        }

        this._changed = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeHeaderCommand] Reverting.");
        if (!this._changed) {
            return;
        }

        Oas30Header header = (Oas30Header) this._headerPath.resolve(document);
        if (this.isNullOrUndefined(header)) {
            return;
        }

        if (this.isNullOrUndefined(this._oldHeaderSchema)) {
            header.schema = null;
        } else {
            header.schema = header.createSchema();
            Library.readNode(this._oldHeaderSchema, header.schema);
        }
    }

}
