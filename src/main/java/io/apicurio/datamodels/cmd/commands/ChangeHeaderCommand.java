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
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.IOasHeaderParent;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;

/**
 * @author vvilerio
 */
public abstract class ChangeHeaderCommand extends AbstractCommand {

   public NodePath _headerPath;
   public String _headerName;
   public OasHeader _newHeader;

   public boolean _changed;

   @JsonDeserialize(using = NullableJsonNodeDeserializer.class)
   public Object _oldHeader;

   ChangeHeaderCommand() {
   }

   ChangeHeaderCommand(OasHeader header, OasHeader newHeader, String name) {
      this._headerName = name;
      this._headerPath = Library.createNodePath(header);
      this._newHeader = newHeader;
   }

   /**
    * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
    */
   @Override
   public void execute(Document document) {
      LoggerCompat.info("[ChangeHeaderCommand] Executing.");
      this._changed = false;

      Oas30Header header = (Oas30Header) this._headerPath.resolve(document);
      if (this.isNullOrUndefined(header)) {
         return;
      }

      // Save the old info (for later undo operation)
      this._oldHeader = Library.writeNode(header);

      // Change the parameter type
//      String pDescription = (String) NodeCompat.getProperty(header,"description");
      this.doChangeHeader(document, header,  null, null);
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

      OasHeader header = (OasHeader) this._headerPath.resolve(document);
      if (this.isNullOrUndefined(header)) {
         return;
      }

      IOasHeaderParent parent = (IOasHeaderParent) header.parent();

      OasHeader oldHeader = parent.createHeader(this._headerName);
      Library.readNode(this._oldHeader, oldHeader);
      this.doRestoreHeader(header, oldHeader);
   }


   /**
    * doChangeHeader
    *
    * @param document
    * @param header
    */
   protected abstract void doChangeHeader(Document document, OasHeader header,  String pDescription, String pExampleName);

   /**
    * doRestoreHeader
    *
    * @param header
    * @param oldHeader
    */
   protected abstract void doRestoreHeader(OasHeader header, OasHeader oldHeader);

}
