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
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.IOasHeaderParent;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A command used to delete all headers from a document.
 * @author vvilerio
 */
public class DeleteAllHeadersCommand extends AbstractCommand {


    public NodePath _parentPath;

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public Map<String,Object> _oldHeaders;

    DeleteAllHeadersCommand() {
    }

    DeleteAllHeadersCommand(IOasHeaderParent parent) {
        this._parentPath = Library.createNodePath((Node) parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllHeadersCommand] Executing.");

        IOasHeaderParent parent = (IOasHeaderParent) this._parentPath.resolve(document);
        List<OasHeader> headers = parent.getHeaders();
        if (this.isNullOrUndefined(parent) || this.isNullOrUndefined(headers) || headers.size() == 0) {
            return;
        }

        this._oldHeaders = new HashMap<>();

        // Save the params we're about to delete for later undo
        List<OasHeader> headersToRemove = new ArrayList<>();
        for (OasHeader header : headers) {
            this._oldHeaders.put(header.getName(),Library.writeNode(header));
            headersToRemove.add(header);

        }

        if (this._oldHeaders.size() == 0) {
            return;
        }

        headersToRemove.forEach(headerToRemove -> {
            parent.removeHeader(headerToRemove.getName());
//            headers.remove(headerToRemove);
        });

    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllHeaders] Reverting.");

        if (this.isNullOrUndefined(this._oldHeaders) || this._oldHeaders.size() == 0) {
            return;
        }

        IOasHeaderParent parent = (IOasHeaderParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        for (String k : this._oldHeaders.keySet()) {
            OasHeader header = parent.createHeader(k);
            Library.readNode(this._oldHeaders.get(k), header);
            parent.addHeader(k,header);
        }

    }

}
