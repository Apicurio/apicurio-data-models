/*
 * Copyright 2020 Red Hat
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
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to remove a message definition item in an AsyncAPI document.
 * @author laurent.broudoux@gmail.com
 */
public class DeleteMessageDefinitionCommand extends AbstractCommand {
    // TODO: Ordering on undo
   public String _name;

   @JsonDeserialize(using= MarshallCompat.NullableJsonNodeDeserializer.class)
   public Object _oldDefinition;

   public DeleteMessageDefinitionCommand() {
   }

   public DeleteMessageDefinitionCommand(String name) {
      this._name = name;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[DeleteMessageDefinitionCommand] Executing.");

      Aai20Document doc20 = (Aai20Document) document;
      if (ModelUtils.isDefined(doc20.components)) {
         AaiMessage msgDef = doc20.components.removeMessage(_name);
         this._oldDefinition = Library.writeNode(msgDef);
      }
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[DeleteMessageDefinitionCommand] Reverting.");
      if (this.isNullOrUndefined(this._oldDefinition)) {
         return;
      }

      Aai20Document doc20 = (Aai20Document) document;
      if (ModelUtils.isDefined(doc20.components)) {
         Aai20NodeFactory factory = new Aai20NodeFactory();
         AaiMessage msgDef = factory.createMessage(doc20.components, _name);
         Library.readNode(_oldDefinition, msgDef);
         doc20.components.addMessage(_name, msgDef);
      }
   }
}
