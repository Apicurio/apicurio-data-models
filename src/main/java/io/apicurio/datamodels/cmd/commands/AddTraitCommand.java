package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20MessageTrait;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20OperationTrait;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

public class AddTraitCommand extends AbstractCommand {

    public NodePath _operationPath;
    public NodePath _messagePath;

    @JsonDeserialize(using = MarshallCompat.NullableJsonNodeDeserializer.class)
    public Object _newTrait;
    
    @JsonDeserialize(using = MarshallCompat.NullableJsonNodeDeserializer.class)
    public Object _oldNode;

    AddTraitCommand() {
    }

    AddTraitCommand(AaiOperationTrait trait, AaiOperation operation) {
        this._operationPath = Library.createNodePath(operation);
        this._newTrait = Library.writeNode(trait);
    }

    AddTraitCommand(AaiMessageTrait trait, AaiMessage message) {
        this._messagePath = Library.createNodePath(message);
        this._newTrait = Library.writeNode(trait);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {

        LoggerCompat.info("[AddTraitCommand] Executing.");
        
        if (!this.isNullOrUndefined(this._messagePath)) {
            AaiMessage message = (AaiMessage) this._messagePath.resolve(document);
            
            if (this.isNullOrUndefined(message)) {
                return;
            }

            this._oldNode = Library.writeNode(message);
            AaiMessageTrait trait = new Aai20MessageTrait(message, null);
            Library.readNode(this._newTrait, trait);
            message.addTrait(trait);
        } else if (!this.isNullOrUndefined(this._operationPath)) {
            AaiOperation operation = (AaiOperation) this._operationPath.resolve(document);

            if (this.isNullOrUndefined(operation)) {
                return;
            }

            this._oldNode = Library.writeNode(operation);
            AaiOperationTrait trait = new Aai20OperationTrait(operation, null);
            Library.readNode(this._newTrait, trait);
            operation.addTrait(trait);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[AddTraitCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldNode)) {
            return;
        }
        
        if (!this.isNullOrUndefined(this._messagePath)) {
            AaiMessage message = (AaiMessage) this._messagePath.resolve(document);
            
            if (this.isNullOrUndefined(message)) {
                return;
            }
            
            NodeCompat.setProperty(message.parent(), "message", message);
            message.traits = null;
            Library.readNode(this._oldNode, message);
        } else if (!this.isNullOrUndefined(this._operationPath)) {
            AaiOperation operation = (AaiOperation) this._operationPath.resolve(document);
            
            if (this.isNullOrUndefined(operation)) {
                return;
            }

            NodeCompat.setProperty(operation.parent(), "operation", operation);
            operation.traits = null;
            Library.readNode(this._oldNode, operation);
        }
    }

}