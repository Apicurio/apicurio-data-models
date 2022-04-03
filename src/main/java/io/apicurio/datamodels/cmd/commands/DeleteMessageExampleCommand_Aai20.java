/*
 * Copyright 2021 Red Hat
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
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A command used to delete an Example for an AsyncAPI 2.0 Message.
 * @author laurent.broudoux@gmail.com
 */
public class DeleteMessageExampleCommand_Aai20 extends AbstractCommand {
    // TODO: Ordering on undo

   public NodePath _parentPath;

   @JsonDeserialize(using= MarshallCompat.NullableJsonNodeDeserializer.class)
   public Object _exampleValue;

   public boolean _exampleRemoved;

   public DeleteMessageExampleCommand_Aai20() {
   }

   public DeleteMessageExampleCommand_Aai20(AaiOperation operationNode, Object exampleValue) {
      this._parentPath = Library.createNodePath(operationNode);
      this._exampleValue = exampleValue;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[DeleteMessageExampleCommand_Aai20] Executing.");

      AaiOperation operation = (AaiOperation) this._parentPath.resolve(document);
      this._exampleRemoved = false;

      if (this.isNullOrUndefined(operation) || this.isNullOrUndefined(operation.message)) {
         return;
      }

      List<Map<String, Object>> examples = operation.message.examples;
      if (examples == null) {
         // Nothing to remove here...
         return;
      }

      if (JsonCompat.isString(this._exampleValue)) {
         this._exampleValue = JsonCompat.parseJSON(JsonCompat.toString(this._exampleValue));
      }


      Map<String, Object> newExample = new HashMap<>();
      for (String key : JsonCompat.keys(this._exampleValue)) {
         newExample.put(key, JsonCompat.getProperty(this._exampleValue, key));
      }
      //operation.message.examples.remove(newExample);  /// does not simply work...

      // As Jest transpilation will tranform this into simple TS array.indexOf(element)
      // deep equality is not realized and we never found any corresponding element...
      // We have to make deep comparison using JSON stringify for value comparison.
      int elementIndex = -1;
      for (int i=0; elementIndex == -1 & i < operation.message.examples.size(); i++) {
         Map<String, Object> example = operation.message.examples.get(i);
         if (example.size() == newExample.size()) {
            elementIndex = i;
            for (Map.Entry<String, Object> entry : example.entrySet()) {
               if (!newExample.containsKey(entry.getKey())
                     || !JsonCompat.stringify(newExample.get(entry.getKey()))
                           .equals(JsonCompat.stringify(entry.getValue()))) {
                  elementIndex = -1;
                  break;
               }
            }
         }
      }
      if (elementIndex != -1) {
         operation.message.examples.remove(elementIndex);
      }

      this._exampleRemoved = true;
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[DeleteMessageExampleCommand_Aai20] Reverting.");

      AaiOperation operation = (AaiOperation) this._parentPath.resolve(document);

      if (!this._exampleRemoved || this.isNullOrUndefined(operation) || this.isNullOrUndefined(operation.message)) {
         return;
      }

      Map<String, Object> newExample = new HashMap<>();
      for (String key : JsonCompat.keys(this._exampleValue)) {
         newExample.put(key, JsonCompat.getProperty(this._exampleValue, key));
      }
      operation.message.examples.add(newExample);
   }
}
