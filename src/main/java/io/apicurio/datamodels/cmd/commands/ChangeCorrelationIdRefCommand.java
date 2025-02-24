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
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiMessageBase;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to change the $ref inside an AsyncAPI message correlation id.
 */
public class ChangeCorrelationIdRefCommand extends AbstractCommand {

   public NodePath _messagePath;
   public String _correlationIdRef;
   public String _oldCorrelationIdRef = null;

   public boolean _changed;

   ChangeCorrelationIdRefCommand() {
   }
   
   ChangeCorrelationIdRefCommand(String correlationIdRef, AaiMessageBase messageNode) {
      this._messagePath = Library.createNodePath(messageNode);
      this._correlationIdRef = correlationIdRef;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[ChangeCorrelationIdRefCommand] Executing.");

      this._changed = false;
      
      AaiMessageBase message = (AaiMessageBase) this._messagePath.resolve(document);
      if (this.isNullOrUndefined(message) || !isValidRef(this._correlationIdRef)) {
         return;
      }

      AaiCorrelationId correlationId = message.correlationId;
      if (correlationId == null) {
         Aai20NodeFactory nodeFactory = new Aai20NodeFactory();
         correlationId = nodeFactory.createCorrelationId(message, null);
         message.correlationId = correlationId;
      }
      if (correlationId.$ref != null) {
         this._oldCorrelationIdRef = correlationId.$ref;
      }
      correlationId.$ref = this._correlationIdRef;
      this._changed = true;
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[ChangeCorrelationIdRefCommand] Reverting.");

      AaiMessageBase message = (AaiMessageBase) this._messagePath.resolve(document);

      if (!this._changed || this.isNullOrUndefined(message)) {
         return;
      }

      AaiCorrelationId correlationId = message.correlationId;
      if (this._oldCorrelationIdRef != null) {
         correlationId.$ref = this._oldCorrelationIdRef;
      } else {
         message.correlationId = null;
      }
   }

   private boolean isValidRef(String refCandidate) {
      return ModelUtils.isDefined(refCandidate)
              && refCandidate.startsWith("#/");
   }
}
