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

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IMessageParent;
import io.apicurio.datamodels.core.models.common.IMessageSchema;
import io.apicurio.datamodels.core.models.common.IPropertyParent;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.util.NodePathUtil;
import io.apicurio.datamodels.core.visitors.NodePathVisitor;

import java.util.List;

/**
 * A command used to delete a single oneOf from a message.
 * @author vvilerio
 */
public class DeleteOneOfMessageCommand extends AbstractCommand {

    public AaiMessage _oneOf;
    public String _oneOfName;
    public NodePath _oneOfPath;
    public NodePath _schemaPath;

    public Object _oldOneOf;
    public boolean _oldRequired;

    DeleteOneOfMessageCommand() {
    }

    DeleteOneOfMessageCommand(AaiMessage message) {
        if(message._isOneOfMessage){
            LoggerCompat.info("[DeleteOneOfMessageCommand] The is a _isOneOfMessage.");
        }

        this._oneOf = message;
        this._oneOfName = message._name;
        this._oneOfPath = Library.createNodePath((Node) message);
        LoggerCompat.debug("_oneOfPath : ",this._oneOfPath);
        this._schemaPath = Library.createNodePath(((Node) message).parent());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteOneOfMessageCommand] Executing.");
        this._oldOneOf = null;

        IMessageSchema onOfMessage = (IMessageSchema) this._oneOfPath.resolve(document);
        if (this.isNullOrUndefined(onOfMessage)) {
            return;
        }

        IMessageParent schema = (IMessageParent) ((Node) onOfMessage).parent();
        this._oldOneOf = Library.writeNode(schema.removeMessage(this._oneOfName));

        this._oldRequired = schema.isMessageRequired(this._oneOfName);
        if (this._oldRequired) {
            schema.unsetMessageRequired(this._oneOfName);
        }
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

//        Aai20Document doc20 = (Aai20Document) document;
//        if (ModelUtils.isDefined(doc20.channels)) {
//            Aai20NodeFactory factory = new Aai20NodeFactory();
//            AaiMessage msgDef = factory.createMessage(doc20.channels, _oneOfName);
//            Library.readNode(_oldOneOf, msgDef);
//            doc20.channels.addMessage(_oneOfName, msgDef);
//        }


    }

}
