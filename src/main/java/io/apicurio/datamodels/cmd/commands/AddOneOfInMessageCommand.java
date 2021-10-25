package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Message;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to add an OneOf value in operation.
 *
 * @author vvilerio
 */
public class AddOneOfInMessageCommand extends AbstractCommand {

   public NodePath _parentPath;

   @JsonDeserialize(using = MarshallCompat.NullableJsonNodeDeserializer.class)
   public Object _newOneOf;


   @JsonDeserialize(using = MarshallCompat.NullableJsonNodeDeserializer.class)
   public Object _oldMessage;

   AddOneOfInMessageCommand() {
   }

   AddOneOfInMessageCommand(AaiMessage message, AaiMessage parentMessage) {
      this._parentPath = Library.createNodePath(parentMessage);
      this._newOneOf = Library.writeNode(message);
   }


   /**
    * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
    */
   @Override
   public void execute(Document document) {

      LoggerCompat.info("[AddOneOfInMessageCommand] Executing.");

      final AaiMessage parent = (AaiMessage) this._parentPath.resolve(document);

      if (this.isNullOrUndefined(parent)) {
         return;
      }

      this._oldMessage = Library.writeNode(parent);
      AaiMessage message = new Aai20Message(parent, null);
      message.setIsOneOfMessage(true);
      Library.readNode(this._newOneOf, message);
      parent.addOneOfMessage(message, null);

   }


   /**
    * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
    */
   @Override
   public void undo(Document document) {
      LoggerCompat.info("[AddOneOfInMessageCommand] Reverting.");
      if (this.isNullOrUndefined(this._oldMessage)) {
         return;
      }

      final AaiMessage parentMessage = (AaiMessage) this._parentPath.resolve(document);
      if (this.isNullOrUndefined(parentMessage)) {
         return;
      }

      NodeCompat.setProperty(parentMessage.parent(), "message", parentMessage);
      parentMessage.oneOf = null;
      Library.readNode(this._oldMessage, parentMessage);
   }


}
