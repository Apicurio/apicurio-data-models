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
import io.apicurio.datamodels.asyncapi.models.AaiCorrelationId;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
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
 * A command used to rename a correlation id definition item in an AsyncAPI document.
 */
public class RenameCorrelationIdDefinitionCommand extends AbstractCommand {

   public String _oldName;
   public String _newName;
   public List<NodePath> _references;

   public RenameCorrelationIdDefinitionCommand() {
   }

   public RenameCorrelationIdDefinitionCommand(String oldName, String newName) {
      this._oldName = oldName;
      this._newName = newName;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[RenameCorrelationIdDefinitionCommand] Executing.");
      this._references = new ArrayList<>();

      AaiDocument doc = (AaiDocument)document;
      if (renameCorrelationIdDefinition(doc, this._oldName, this._newName)) {
         String oldRef = nameToReference(this._oldName);
         String newRef = nameToReference(this._newName);
         CorrelationIdRefFinder finder = new CorrelationIdRefFinder(oldRef);
         List<AaiCorrelationId> messages = finder.findIn(document);
         for (AaiCorrelationId correlationId : messages) {
            this._references.add(Library.createNodePath(correlationId));
            correlationId.$ref = newRef;
         }
      }
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[RenameCorrelationIdDefinitionCommand] Reverting.");
      if (this.renameCorrelationIdDefinition((AaiDocument) document, this._newName, this._oldName)) {
         String oldRef = this.nameToReference(this._oldName);
         if (ModelUtils.isDefined(this._references)) {
            this._references.forEach( ref -> {
               AaiCorrelationId corrId = (AaiCorrelationId) ref.resolve(document);
               corrId.$ref = oldRef;
            });
         }
      }
   }

   protected String nameToReference(String name) {
      return "#/components/correlationIds/" + name;
   }

   protected boolean renameCorrelationIdDefinition(AaiDocument document, String fromName, String toName) {
      Aai20Document doc20 = (Aai20Document) document;
      if (this.isNullOrUndefined(doc20.components) || this.isNullOrUndefined(doc20.components.correlationIds)) {
         return false;
      }
      if (ModelUtils.isDefined(doc20.components.getCorrelationId(toName))) {
         return false;
      }
      doc20.components.renameCorrelationId(fromName, toName, corrIdDef -> corrIdDef.rename(toName));
      return true;
   }

   private static class CorrelationIdRefFinder extends CombinedVisitorAdapter {

      private final String reference;
      private final List<AaiCorrelationId> correlationIds = new ArrayList<>();

      public CorrelationIdRefFinder(String ref) {
         this.reference = ref;
      }

      public List<AaiCorrelationId> findIn(Document document) {
         VisitorUtil.visitTree(document, this, TraverserDirection.down);
         return correlationIds;
      }

      @Override
      public void visitCorrelationId(AaiCorrelationId node) {
         if (accept(node)) {
            correlationIds.add(node);
         }
      }

      protected boolean accept(AaiCorrelationId correlationId) {
         return ModelUtils.isDefined(correlationId.$ref) && NodeCompat.equals(correlationId.$ref, this.reference);
      }
   }
}
