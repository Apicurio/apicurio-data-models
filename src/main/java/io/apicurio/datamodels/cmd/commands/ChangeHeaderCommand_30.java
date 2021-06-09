package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;

public class ChangeHeaderCommand_30 extends ChangeHeaderCommand {

   ChangeHeaderCommand_30() {
   }

   ChangeHeaderCommand_30(Oas30Header header, OasHeader newHeader, String name) {
      super(header, newHeader, name);
   }


   @Override
   protected void doChangeHeader(Document document, OasHeader pHeader, String pDescription, String pExampleName) {
      Oas30Header header = (Oas30Header) pHeader;

      header.schema = header.createSchema();
      header.description = pDescription;
      header.example = header.createExample(pExampleName);

   }

   @Override
   protected void doRestoreHeader(OasHeader pHeader, OasHeader pOldHeader) {
      Oas30Header header = (Oas30Header) pHeader;
      Oas30Header oldHeader = (Oas30Header) pOldHeader;

      header.schema = oldHeader.schema;
      if (ModelUtils.isDefined(header.schema)) {
         header.schema._parent = header;
         header.schema._ownerDocument = header.ownerDocument();
      }

      header.example = oldHeader.example;
      header.description = oldHeader.description;

   }
}
