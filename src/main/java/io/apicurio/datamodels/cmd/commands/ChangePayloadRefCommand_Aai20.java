/*
 * Copyright 2021 JBoss Inc
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
import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to change the $ref inside an AsyncAPI message payload.
 * @author laurent.broudoux@gmail.com
 */
public class ChangePayloadRefCommand_Aai20 extends AbstractCommand {

   public NodePath _operationPath;
   public NodePath _componentPath;
   public String _payloadRef;
   public String _oldPayloadRef = null;

   public boolean _changed;

   private String _key;
   private boolean _fromOp = true;

   ChangePayloadRefCommand_Aai20() {
   }

   ChangePayloadRefCommand_Aai20(String payloadRef, AaiOperation operationNode) {
      this._fromOp = true;
      this._operationPath = Library.createNodePath(operationNode);
      this._payloadRef = payloadRef;
   }

   ChangePayloadRefCommand_Aai20(String payloadRef, AaiComponents componentNode, String key) {
      this._fromOp = false;
      this._componentPath = Library.createNodePath(componentNode);
      this._payloadRef = payloadRef;
      this._key = key;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[ChangePayloadRefCommand_Aai20] Executing.");

      //change the payload in a message from an operation
      if(this._fromOp){
         AaiOperation operation = (AaiOperation) this._operationPath.resolve(document);
         this._changed = false;

         if (this.isNullOrUndefined(operation) || this.isNullOrUndefined(operation.message) || !isValidRef(this._payloadRef)) {
            return;
         }

         Object payload = operation.message.payload;
         if (payload == null) {
            payload = JsonCompat.objectNode();
         }
         Object oldValue = JsonCompat.getProperty(payload, "$ref");
         if (oldValue != null && JsonCompat.isString(oldValue)) {
            this._oldPayloadRef = JsonCompat.toString(oldValue);
         }
         JsonCompat.setProperty(payload, "$ref", this._payloadRef);
         this._changed = true;

      }else{ //change the payload in a message from a component (Message block)
         AaiComponents component = (AaiComponents) this._componentPath.resolve(document);
         this._changed = false;

         if (this.isNullOrUndefined(component) || this.isNullOrUndefined(component.messages.get(this._key)) || !isValidRef(this._payloadRef)) {
            return;
         }

         Object payload = component.messages.get(this._key).payload;
         if (payload == null) {
            payload = JsonCompat.objectNode();
         }
         Object oldValue = JsonCompat.getProperty(payload, "$ref");
         if (oldValue != null && JsonCompat.isString(oldValue)) {
            this._oldPayloadRef = JsonCompat.toString(oldValue);
         }
         JsonCompat.setProperty(payload, "$ref", this._payloadRef);
         this._changed = true;
      }







   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[ChangePayloadRefCommand_Aai20] Reverting.");

      AaiOperation operation = (AaiOperation) this._operationPath.resolve(document);

      if (!this._changed || this.isNullOrUndefined(operation)
            || this.isNullOrUndefined(operation.message)) {
         return;
      }

      Object payload = operation.message.payload;
      if (this._oldPayloadRef != null) {
         JsonCompat.setProperty(payload, "$ref", this._oldPayloadRef);
      } else {
         JsonCompat.consumeProperty(payload, "$ref");
      }
   }
   
   private boolean isValidRef(String refCandidate) {
      return ModelUtils.isDefined(refCandidate)
              && refCandidate.startsWith("#/");
   }
}
