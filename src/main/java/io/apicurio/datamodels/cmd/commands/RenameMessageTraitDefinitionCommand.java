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
import io.apicurio.datamodels.asyncapi.models.AaiMessageTrait;
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
 * A command used to rename a message trait definition item in an AsyncAPI document.
 * @author laurent.broudoux@gmail.com
 */
public class RenameMessageTraitDefinitionCommand extends AbstractCommand {

   public String _oldName;
   public String _newName;
   public List<NodePath> _references;

   public RenameMessageTraitDefinitionCommand() {
   }

   public RenameMessageTraitDefinitionCommand(String oldName, String newName) {
      this._oldName = oldName;
      this._newName = newName;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[RenameMessageTraitDefinitionCommand] Executing.");
      this._references = new ArrayList<>();

      AaiDocument doc = (AaiDocument)document;
      if (renameMessageTraitDefinition(doc, this._oldName, this._newName)) {
         String oldRef = nameToReference(this._oldName);
         String newRef = nameToReference(this._newName);
         MessageTraitRefFinder finder = new MessageTraitRefFinder(oldRef);
         List<AaiMessageTrait> traits = finder.findIn(document);
         for (AaiMessageTrait trait : traits) {
            this._references.add(Library.createNodePath(trait));
            trait.$ref = newRef;
         }
      }
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[RenameMessageTraitDefinitionCommand] Reverting.");
      if (this.renameMessageTraitDefinition((AaiDocument) document, this._newName, this._oldName)) {
         String oldRef = this.nameToReference(this._oldName);
         if (ModelUtils.isDefined(this._references)) {
            this._references.forEach( ref -> {
               AaiMessageTrait msgTrait = (AaiMessageTrait) ref.resolve(document);
               msgTrait.$ref = oldRef;
            });
         }
      }
   }

   protected String nameToReference(String name) {
      return "#/components/messageTraits/" + name;
   }

   protected boolean renameMessageTraitDefinition(AaiDocument document, String fromName, String toName) {
      Aai20Document doc20 = (Aai20Document) document;
      if (this.isNullOrUndefined(doc20.components) || this.isNullOrUndefined(doc20.components.messageTraits)) {
         return false;
      }
      if (ModelUtils.isDefined(doc20.components.getMessageTraitDefinition(toName))) {
         return false;
      }
      doc20.components.renameMessageTraitDefinition(fromName, toName, traitDef -> traitDef.rename(toName));
      return true;
   }

   private static class MessageTraitRefFinder extends CombinedVisitorAdapter {

      private final String reference;
      private final List<AaiMessageTrait> traits = new ArrayList<>();

      public MessageTraitRefFinder(String ref) {
         this.reference = ref;
      }

      public List<AaiMessageTrait> findIn(Document document) {
         VisitorUtil.visitTree(document, this, TraverserDirection.down);
         return traits;
      }

      @Override
      public void visitMessageTrait(AaiMessageTrait node) {
         if (accept(node)) {
            traits.add(node);
         }
      }

      protected boolean accept(AaiMessageTrait trait) {
         return ModelUtils.isDefined(trait.$ref) && NodeCompat.equals(trait.$ref, this.reference);
      }
   }
}
