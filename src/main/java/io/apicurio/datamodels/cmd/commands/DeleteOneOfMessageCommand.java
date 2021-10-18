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
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Message;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to delete a single oneOf from a message.
 *
 * @author vvilerio
 */
public class DeleteOneOfMessageCommand extends AbstractCommand {

   public int _oneOfIdc;
   public NodePath _parentPath;


   @JsonDeserialize(using = MarshallCompat.NullableJsonNodeDeserializer.class)
   public Object _oldMessage;

   DeleteOneOfMessageCommand() {
   }

   DeleteOneOfMessageCommand(final AaiMessage message, final int oneOfIdc) {
      //oneOfIdc, beware of competition problems
      this._parentPath = Library.createNodePath(message);
      this._oneOfIdc = oneOfIdc;
   }

   /**
    * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
    */
   @Override
   public void execute(final Document document) {
      LoggerCompat.info("[DeleteOneOfMessageCommand] Executing.");

      final AaiMessage parent = (AaiMessage) this._parentPath.resolve(document);

      final AaiMessage res = parent.deleteOneOfMessage(this._oneOfIdc);
      boolean isOneOfMessage = res._isOneOfMessage;

      if (!isOneOfMessage) {
         return;
      }

      if (!this.isNullOrUndefined(res)) {
         this._oldMessage = Library.writeNode(res);

      }

   }

   /**
    * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
    */
   @Override
   public void undo(final Document document) {
      LoggerCompat.info("[DeleteOneOfMessageCommand] Reverting.");
      if (this.isNullOrUndefined(this._oldMessage)) {
         return;
      }

      final AaiMessage parent = (AaiMessage) this._parentPath.resolve(document);
      if (this.isNullOrUndefined(parent)) {
         return;
      }

      AaiMessage message = new Aai20Message(parent);
      Library.readNode(this._oldMessage, message);
      message.setIsOneOfMessage(true);
      parent.addOneOfMessage(message, this._oneOfIdc);

   }

}
