/*
 * Copyright 2019 Red Hat
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
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.IOasHeaderParent;
import io.apicurio.datamodels.openapi.models.OasHeader;

/**
 * @author vvilerio
 */
public class ChangeHeaderCommand extends AbstractCommand {

   public NodePath _parentPath;
   public String _headerName;
   @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
   public Object _newHeader;

   public boolean _changed;

   @JsonDeserialize(using = NullableJsonNodeDeserializer.class)
   public Object _oldHeader;
   ChangeHeaderCommand() {
   }

   ChangeHeaderCommand(OasHeader header, OasHeader newHeader) {
      this._headerName = header.getName();
      this._parentPath = Library.createNodePath(header);
      this._newHeader = Library.writeNode(newHeader);
   }

   /**
    * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
    */
   @Override
   public void execute(Document document) {
      LoggerCompat.info("[ChangeHeaderCommand] Executing.");
      this._changed = false;

      IOasHeaderParent parent = (IOasHeaderParent) this._parentPath.resolve(document);
      if (this.isNullOrUndefined(parent)) {
          return;
      }
      OasHeader header = (OasHeader) parent.getHeader(_headerName);
      if (this.isNullOrUndefined(header)) {
         return;
      }

      // Save the old info (for later undo operation)
      this._oldHeader = Library.writeNode(header);

      OasHeader newHeader = parent.createHeader(_headerName);
      Library.readNode(this._newHeader, newHeader);
      this.doChangeHeader(header, newHeader);
      this._changed = true;
   }

   /**
    * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
    */
   @Override
   public void undo(Document document) {
      LoggerCompat.info("[ChangeHeaderCommand] Reverting.");
      if (!this._changed) {
         return;
      }

      IOasHeaderParent parent = (IOasHeaderParent) this._parentPath.resolve(document);
      if (this.isNullOrUndefined(parent)) {
          return;
      }
      OasHeader header = (OasHeader) parent.getHeader(_headerName);
      if (this.isNullOrUndefined(header)) {
         return;
      }

      OasHeader oldHeader = parent.createHeader(this._headerName);
      Library.readNode(this._oldHeader, oldHeader);
      this.doChangeHeader(header, oldHeader);
   }

   /**
    * doChangeHeader
    *
    * @param from
    * @param to
    */
   protected void doChangeHeader(OasHeader from, OasHeader to) {
       IOasHeaderParent parent = (IOasHeaderParent) from.parent();
       parent.replaceHeader(_headerName, to);
   }

}
