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

import io.apicurio.datamodels.asyncapi.v2.models.Aai20Components;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SchemaDefinition;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.factories.AaiSchemaFactory;
import io.apicurio.datamodels.core.models.Document;

/**
 * @author laurent.broudoux@gmail.com
 */
public class NewSchemaDefinitionCommand_Aai20 extends NewSchemaDefinitionCommand {

   public boolean _nullComponents;

   NewSchemaDefinitionCommand_Aai20() {
   }

   NewSchemaDefinitionCommand_Aai20(String definitionName, Object example, String description) {
      this._newDefinitionName = definitionName;
      this._newDefinitionExample = example;
      this._newDefinitionDescription = description;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[NewSchemaDefinitionCommand_Aai20] Executing.");

      Aai20Document doc20 = (Aai20Document) document;
      if (this.isNullOrUndefined(doc20.components)) {
         doc20.components = doc20.createComponents();
         this._nullComponents = true;
      }
      this._nullComponents = false;

      Aai20Components components = (Aai20Components) doc20.components;

      if (this.isNullOrUndefined(components.getSchemaDefinition(this._newDefinitionName))) {
         Aai20SchemaDefinition definition;

         if (!this.isNullOrUndefined(this._newDefinitionExample)) {
            definition = (Aai20SchemaDefinition) AaiSchemaFactory.createSchemaDefinitionFromExample(doc20,
                  this._newDefinitionName, this._newDefinitionExample);
            definition.example = this._newDefinitionExample;
         } else {
            definition = components.createSchemaDefinition(this._newDefinitionName);
            definition.type = "object";
         }
         if (ModelUtils.isDefined(this._newDefinitionDescription)) {
            definition.description = this._newDefinitionDescription;
         }
         components.addSchemaDefinition(this._newDefinitionName, definition);

         this._defExisted = false;
      } else {
         this._defExisted = true;
      }
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[NewSchemaDefinitionCommand_Aai20] Reverting.");

      Aai20Document doc20 = (Aai20Document) document;

      if (this._nullComponents) {
         doc20.components = null;
      }
      if (this._defExisted) {
         return;
      }

      ((Aai20Components) doc20.components).removeSchemaDefinition(this._newDefinitionName);
   }
}
