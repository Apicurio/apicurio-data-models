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

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.visitors.TraverserDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to rename a message definition item in an AsyncAPI document.
 * @author laurent.broudoux@gmail.com
 */
public class RenameMessageDefinitionCommand extends AbstractCommand {

   public String _oldName;
   public String _newName;
   public List<NodePath> _references;

   public RenameMessageDefinitionCommand() {
   }

   public RenameMessageDefinitionCommand(String oldName, String newName) {
      this._oldName = oldName;
      this._newName = newName;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[RenameMessageDefinitionCommand] Executing.");
      this._references = new ArrayList<>();

      AaiDocument doc = (AaiDocument)document;
      if (renameMessageDefinition(doc, this._oldName, this._newName)) {
         String oldRef = nameToReference(this._oldName);
         String newRef = nameToReference(this._newName);
         MessageRefFinder finder = new MessageRefFinder(oldRef);
         List<AaiMessage> messages = finder.findIn(document);
         for (AaiMessage message : messages) {
            this._references.add(Library.createNodePath(message));
            message.$ref = newRef;
         }
      }
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[RenameMessageDefinitionCommand] Reverting.");
      if (this.renameMessageDefinition((AaiDocument) document, this._newName, this._oldName)) {
         String oldRef = this.nameToReference(this._oldName);
         if (ModelUtils.isDefined(this._references)) {
            this._references.forEach( ref -> {
               AaiMessage message = (AaiMessage) ref.resolve(document);
               message.$ref = oldRef;
            });
         }
      }
   }

   protected String nameToReference(String name) {
      return "#/components/messages/" + name;
   }

   protected boolean renameMessageDefinition(AaiDocument document, String fromName, String toName) {
      Aai20Document doc20 = (Aai20Document) document;
      if (this.isNullOrUndefined(doc20.components) || this.isNullOrUndefined(doc20.components.messages)) {
         return false;
      }
      if (ModelUtils.isDefined(doc20.components.getMessage(toName))) {
         return false;
      }
      AaiMessage msgDef = doc20.components.removeMessage(fromName);
      msgDef.rename(toName);
      doc20.components.addMessage(toName, msgDef);
      return true;
   }

   private static class MessageRefFinder extends CombinedVisitorAdapter {

      private final String reference;
      private final List<AaiMessage> messages = new ArrayList<>();

      public MessageRefFinder(String ref) {
         this.reference = ref;
      }

      public List<AaiMessage> findIn(Document document) {
         VisitorUtil.visitTree(document, this, TraverserDirection.down);
         return messages;
      }

      @Override
      public void visitMessage(AaiMessage node) {
         if (accept(node)) {
            messages.add(node);
         }
      }

      protected boolean accept(AaiMessage message) {
         return ModelUtils.isDefined(message.$ref) && NodeCompat.equals(message.$ref, this.reference);
      }
   }
}
