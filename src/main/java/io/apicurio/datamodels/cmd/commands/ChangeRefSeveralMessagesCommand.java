package io.apicurio.datamodels.cmd.commands;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

import java.util.ArrayList;

/**
 * A command used to modify the reference from (OneOf) messages in operations.
 *
 * @author vvilerio
 */
public class ChangeRefSeveralMessagesCommand extends AbstractCommand {
    public NodePath _parentPath;
    public String _newReference;
    public String _oldReference;

    @JsonDeserialize(using = MarshallCompat.NullableJsonNodeDeserializer.class)
    public Object _oldParentMessage;

    /**
     * Constructor.
     */
    ChangeRefSeveralMessagesCommand() {
    }

    /**
     * C'tor.
     */
    ChangeRefSeveralMessagesCommand(AaiMessage message, String reference) {
        if (ModelUtils.isDefined(message) && ModelUtils.isDefined(message.parent())) {
            this._parentPath = Library.createNodePath(message.parent());
        }
        this._newReference = reference;
        this._oldReference = message.getReference();

    }

    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeRefSeveralMessagesCommand] Executing.");
        this._oldParentMessage = null;

        AaiMessage parentMessage = (AaiMessage) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parentMessage)) {
            return;
        }

        // Save the old info (for later undo operation)
        if (!this.isNullOrUndefined(parentMessage)) {
            this._oldParentMessage = Library.writeNode(parentMessage);
        }

        // Update the reference
        ArrayList<AaiMessage> res = new ArrayList<>();
        parentMessage.oneOf.forEach( msg -> {
            if(this._oldReference.equals(msg.getReference())){
                msg.setReference(this._newReference);
            }
            res.add(msg);
        });
        parentMessage.oneOf = res;
    }

    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeRefSeveralMessagesCommand] Reverting.");
        AaiMessage parentMessage = (AaiMessage) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parentMessage)) {
            return;
        }

        parentMessage.oneOf = new ArrayList<AaiMessage>();
        Library.readNode(this._oldParentMessage, parentMessage);
    }

}
