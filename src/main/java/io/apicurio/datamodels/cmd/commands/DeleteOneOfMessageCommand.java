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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IMessageParent;

/**
 * A command used to delete a single oneOf from a message.
 * @author vvilerio
 */
public class DeleteOneOfMessageCommand extends AbstractCommand {

    public int _oneOfIdc;
    public  NodePath _parentPath;
    public AaiMessage _oneOf;
    public NodePath _schemaPath;


    public boolean _oldRequired;

    @JsonDeserialize(using= MarshallCompat.NullableJsonNodeDeserializer.class)
    public Object _oldOneOf;

    DeleteOneOfMessageCommand() {
    }

    DeleteOneOfMessageCommand(final AaiMessage message, final int oneOfIdc) {
        this._parentPath = Library.createNodePath(message.parent());
        this._oneOfIdc = oneOfIdc;

//
        this._oneOf = message;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteOneOfMessageCommand] Executing.");

        IMessageParent parent = (IMessageParent) this._parentPath.resolve(document);
        AaiMessage oneOf = parent.getMessage(null);

        if (this.isNullOrUndefined(oneOf)) {
            return;
        }

        oneOf.deleteOneOfMessage(this._oneOf);

        this._oldOneOf = Library.writeNode(oneOf);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteOneOfMessageCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldOneOf)) {
            return;
        }

        IMessageParent parent = (IMessageParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

//        OasHeader header = parent.createOneOfMessage(null);
//        Library.readNode(this._oldOneOf, header);
//        parent.addMessage("oneOf", schema);
    }

}
