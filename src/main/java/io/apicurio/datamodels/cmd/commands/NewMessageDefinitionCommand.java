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
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Components;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to create a new message definition item in aa AsyncAPI document.
 * @author laurent.broudoux@gmail.com
 */
public class NewMessageDefinitionCommand extends AbstractCommand {

   public String _newName;
   public String _newDescription;

   public boolean _nullComponents;
   public boolean _defExisted;

   public NewMessageDefinitionCommand() {
   }

   public NewMessageDefinitionCommand(String newName, String newDescription) {
      this._newName = newName;
      this._newDescription = newDescription;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[NewMessageDefinitionCommand] Executing.");

      Aai20Document doc20 = (Aai20Document) document;
      if (this.isNullOrUndefined(doc20.components)) {
         doc20.components = doc20.createComponents();
         this._nullComponents = true;
      }
      this._nullComponents = false;

      Aai20Components components = (Aai20Components) doc20.components;
      if (this.isNullOrUndefined(components.getMessageTraitDefinition(_newName))) {
         Aai20NodeFactory factory = new Aai20NodeFactory();
         AaiMessage msgDef = factory.createMessage(components, _newName);

         if (!this.isNullOrUndefined(_newDescription != null)) {
            msgDef.description = _newDescription;
         }

         components.addMessage(_newName, msgDef);
         this._defExisted = false;
      } else {
         this._defExisted = true;
      }
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[NewMessageDefinitionCommand] Reverting.");

      Aai20Document doc20 = (Aai20Document) document;

      if (this._nullComponents) {
         doc20.components = null;
      }
      if (this._defExisted) {
         return;
      }

      doc20.components.removeMessage(this._newName);
   }
}
