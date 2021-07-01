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
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Components;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SchemaDefinition;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to add a new definition in a document. Source for the new
 * definition must be provided. This source will be converted to an AAI
 * definition object and then added to the data model.
 * @author laurent.broudoux@gmail.com
 */
public class AddSchemaDefinitionCommand_Aai20 extends AbstractCommand {

   public boolean _defExisted;
   public String _newDefinitionName;
   @JsonDeserialize(using= MarshallCompat.NullableJsonNodeDeserializer.class)
   public Object _newDefinitionObj;

   public boolean _nullComponents;

   AddSchemaDefinitionCommand_Aai20() {
   }

   AddSchemaDefinitionCommand_Aai20(String definitionName) {
      this._newDefinitionName = definitionName;
   }

   AddSchemaDefinitionCommand_Aai20(String definitionName, Object from) {
      this._newDefinitionName = definitionName;
      this._newDefinitionObj = from;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[AddSchemaDefinitionCommand_Aai20] Executing.");

      Aai20Document doc = (Aai20Document) document;

      // Do nothing if the definition already exists.
      if (this.defExists(doc)) {
         LoggerCompat.info("[AddSchemaDefinitionCommand_Aai20] Definition with name %s already exists.", this._newDefinitionName);
         this._defExisted = true;
         return;
      }

      if (this.isNullOrUndefined(doc.components)) {
         doc.components = doc.createComponents();
         this._nullComponents = true;
      } else {
         this._nullComponents = false;
      }

      // Now create, initialize and attach the SchemaDefinition.
      Aai20Components components = (Aai20Components) doc.components;
      Aai20SchemaDefinition definition = components.createSchemaDefinition(this._newDefinitionName);
      Library.readNode(this._newDefinitionObj, definition);
      components.addSchemaDefinition(this._newDefinitionName, definition);
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[AddSchemaDefinitionCommand_Aai20] Reverting.");
      if (this._defExisted) {
         return;
      }

      Aai20Document doc = (Aai20Document) document;
      if (this._nullComponents) {
         doc.components = null;
      }
      ((Aai20Components) doc.components).removeSchemaDefinition(this._newDefinitionName);
   }

   private boolean defExists(Aai20Document document) {
      if (this.isNullOrUndefined(document.components)) {
         return false;
      }
      Aai20Components components = (Aai20Components)document.components;
      return !this.isNullOrUndefined(components.getSchemaDefinition(this._newDefinitionName));
   }
}
