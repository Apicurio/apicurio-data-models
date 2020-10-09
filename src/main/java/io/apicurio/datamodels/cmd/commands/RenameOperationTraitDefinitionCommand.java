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
import io.apicurio.datamodels.asyncapi.models.AaiOperationTrait;
import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
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
 * A command used to rename a operation trait definition item in an AsyncAPI document.
 * @author laurent.broudoux@gmail.com
 */
public class RenameOperationTraitDefinitionCommand extends AbstractCommand {

   public String _oldName;
   public String _newName;
   public List<NodePath> _references;

   public RenameOperationTraitDefinitionCommand() {
   }

   public RenameOperationTraitDefinitionCommand(String oldName, String newName) {
      this._oldName = oldName;
      this._newName = newName;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[RenameOperationTraitDefinitionCommand] Executing.");
      this._references = new ArrayList<>();

      AaiDocument doc = (AaiDocument)document;
      if (renameOperationTraitDefinition(doc, this._oldName, this._newName)) {
         String oldRef = nameToReference(this._oldName);
         String newRef = nameToReference(this._newName);
         OperationTraitRefFinder finder = new OperationTraitRefFinder(oldRef);
         List<AaiOperationTrait> traits = finder.findIn(document);
         for (AaiOperationTrait trait : traits) {
            this._references.add(Library.createNodePath(trait));
            trait.$ref = newRef;
         }
      }
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[RenameOperationTraitDefinitionCommand] Reverting.");
      if (this.renameOperationTraitDefinition((AaiDocument) document, this._newName, this._oldName)) {
         String oldRef = this.nameToReference(this._oldName);
         if (ModelUtils.isDefined(this._references)) {
            this._references.forEach( ref -> {
               AaiOperationTrait trait = (AaiOperationTrait) ref.resolve(document);
               trait.$ref = oldRef;
            });
         }
      }
   }

   protected String nameToReference(String name) {
      return "#/components/operationTraits/" + name;
   }

   protected boolean renameOperationTraitDefinition(AaiDocument document, String fromName, String toName) {
      Aai20Document doc20 = (Aai20Document) document;
      if (this.isNullOrUndefined(doc20.components) || this.isNullOrUndefined(doc20.components.operationTraits)) {
         return false;
      }
      if (ModelUtils.isDefined(doc20.components.getOperationTraitDefinition(toName))) {
         return false;
      }
      AaiOperationTraitDefinition traitDef = doc20.components.removeOperationTraitDefinition(fromName);
      traitDef.rename(toName);
      doc20.components.addOperationTraitDefinition(toName, traitDef);
      return true;
   }

   private static class OperationTraitRefFinder extends CombinedVisitorAdapter {

      private final String reference;
      private final List<AaiOperationTrait> traits = new ArrayList<>();

      public OperationTraitRefFinder(String ref) {
         this.reference = ref;
      }

      public List<AaiOperationTrait> findIn(Document document) {
         VisitorUtil.visitTree(document, this, TraverserDirection.down);
         return traits;
      }

      @Override
      public void visitOperationTrait(AaiOperationTrait node) {
         if (accept(node)) {
            traits.add(node);
         }
      }

      protected boolean accept(AaiOperationTrait trait) {
         return ModelUtils.isDefined(trait.$ref) && NodeCompat.equals(trait.$ref, this.reference);
      }
   }
}
