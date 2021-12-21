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
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to change the $ref inside an AsyncAPI message payload.
 * @author laurent.broudoux@gmail.com
 */
public class ChangePayloadRefCommand_Aai20 extends AbstractCommand {

   public NodePath _operationPath; // deprecated
   public NodePath _messagePath;
   public String _payloadRef;
   public String _oldPayloadRef = null;

   public boolean _changed;

   ChangePayloadRefCommand_Aai20() {
   }

   /**
    * Constructor.
    * @deprecated Use the AaiMessage variant instead
    * @param payloadRef
    * @param operationNode
    */
   @Deprecated
   ChangePayloadRefCommand_Aai20(String payloadRef, AaiOperation operationNode) {
      this._operationPath = Library.createNodePath(operationNode);
      this._payloadRef = payloadRef;
   }

   ChangePayloadRefCommand_Aai20(String payloadRef, AaiMessage messageNode) {
       this._messagePath = Library.createNodePath(messageNode);
       this._payloadRef = payloadRef;
    }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[ChangePayloadRefCommand_Aai20] Executing.");

      this._changed = false;

      AaiMessage message;

      // Legacy: lookup from operation path
      if (!this.isNullOrUndefined(this._operationPath)) {
          AaiOperation operation = (AaiOperation) this._operationPath.resolve(document);

          if (this.isNullOrUndefined(operation) || this.isNullOrUndefined(operation.message)) {
             return;
          }

          message = operation.message;
      } else {
          message = (AaiMessage) this._messagePath.resolve(document);
      }

      if (this.isNullOrUndefined(message)) {
          return;
      }

      Object payload = message.payload;
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

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[ChangePayloadRefCommand_Aai20] Reverting.");

      if (!this._changed) {
          return;
      }

      AaiMessage message;

      if (!this.isNullOrUndefined(this._operationPath)) {
          AaiOperation operation = (AaiOperation) this._operationPath.resolve(document);
          if (this.isNullOrUndefined(operation)) {
              return;
          }
          message = operation.message;
      } else {
          message = (AaiMessage) this._messagePath.resolve(document);
      }

      if (this.isNullOrUndefined(message)) {
          return;
      }

      Object payload = message.payload;
      if (this._oldPayloadRef != null) {
         JsonCompat.setProperty(payload, "$ref", this._oldPayloadRef);
      } else {
         JsonCompat.consumeProperty(payload, "$ref");
      }
   }

}
