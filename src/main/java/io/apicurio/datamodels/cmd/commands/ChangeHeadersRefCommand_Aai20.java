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
import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to change the $ref inside an AsyncAPI message headers.
 * @author laurent.broudoux@gmail.com
 */
public class ChangeHeadersRefCommand_Aai20 extends AbstractCommand {

   public NodePath _operationPath;
   public String _headersRef;
   public String _oldHeadersRef = null;

   public boolean _changed;

   ChangeHeadersRefCommand_Aai20() {
   }

   ChangeHeadersRefCommand_Aai20(String headersRef, AaiOperation operationNode) {
      this._operationPath = Library.createNodePath(operationNode);;
      this._headersRef = headersRef;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[ChangeHeadersRefCommand_Aai20] Executing.");

      AaiOperation operation = (AaiOperation) this._operationPath.resolve(document);
      this._changed = false;

      if (this.isNullOrUndefined(operation) || this.isNullOrUndefined(operation.message)) {
         return;
      }

      AaiHeaderItem headerItem = operation.message.headers;
      if (headerItem == null) {
         Aai20NodeFactory nodeFactory = new Aai20NodeFactory();
         headerItem = nodeFactory.createHeaderItem(operation.message);
         operation.message.headers = headerItem;
      }
      if (headerItem.$ref != null) {
         this._oldHeadersRef = headerItem.$ref;
      }
      headerItem.$ref = this._headersRef;
      this._changed = true;
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[ChangeHeadersRefCommand_Aai20] Reverting.");

      AaiOperation operation = (AaiOperation) this._operationPath.resolve(document);

      if (!this._changed || this.isNullOrUndefined(operation)
            || this.isNullOrUndefined(operation.message)) {
         return;
      }

      AaiHeaderItem headerItem = operation.message.headers;
      if (this._oldHeadersRef != null) {
         headerItem.$ref = this._oldHeadersRef;
      } else {
         headerItem.$ref = null;
      }
   }
}
