package io.apicurio.datamodels.asyncapi.v2.io;

import io.apicurio.datamodels.asyncapi.io.AaiDataModelWriter;
import io.apicurio.datamodels.asyncapi.models.AaiComponents;
import io.apicurio.datamodels.asyncapi.models.AaiSchemaDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Components;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Schema;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SchemaDefinition;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;

import java.util.Map;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20DataModelWriter extends AaiDataModelWriter implements IAai20Visitor {

   @Override
   public void visitComponents(Components node) {
      Aai20Components components = (Aai20Components) node;

      Object parent = this.lookupParentJson(node);
      Object json = JsonCompat.objectNode();

      JsonCompat.setPropertyNull(json, Constants.PROP_SCHEMAS); // map
      JsonCompat.setPropertyNull(json, Constants.PROP_MESSAGES); // map
      JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY_SCHEMES); // map
      JsonCompat.setPropertyNull(json, Constants.PROP_PARAMETERS); // map
      JsonCompat.setPropertyNull(json, Constants.PROP_CORRELATION_IDS); // map
      JsonCompat.setPropertyNull(json, Constants.PROP_OPERATION_TRAITS); // map
      JsonCompat.setPropertyNull(json, Constants.PROP_MESSAGE_TRAITS); // map
      JsonCompat.setPropertyNull(json, Constants.PROP_SERVER_BINDINGS); // map
      JsonCompat.setPropertyNull(json, Constants.PROP_CHANNEL_BINDINGS); // map
      JsonCompat.setPropertyNull(json, Constants.PROP_OPERATION_BINDINGS); // map
      JsonCompat.setPropertyNull(json, Constants.PROP_MESSAGE_BINDINGS); // map

      // PROCESS PARENT
      JsonCompat.setProperty(parent, Constants.PROP_COMPONENTS, json);

      this.writeExtraProperties(json, node);
      this.updateIndex(node, json);
   }

   /**
    * @see io.apicurio.datamodels.core.io.DataModelWriter#addSchemaDefinitionToParent(java.lang.Object, java.lang.Object, io.apicurio.datamodels.core.models.common.IDefinition)
    */
   @Override
   protected void addSchemaDefinitionToParent(Object parent, Object json, IDefinition node) {
      Object schemas = JsonCompat.getProperty(parent, Constants.PROP_SCHEMAS);
      if (schemas == null) {
         schemas = JsonCompat.objectNode();
         JsonCompat.setProperty(parent, Constants.PROP_SCHEMAS, schemas);
      }

      JsonCompat.setProperty(schemas, node.getName(), json);
   }

   /**
    * @see io.apicurio.datamodels.asyncapi.io.AaiDataModelWriter#writeSchema(java.lang.Object, io.apicurio.datamodels.core.models.common.Schema)
    */
   @Override
   protected void writeSchema(Object json, Schema node) {
      Aai20Schema schema = (Aai20Schema) node;

      JsonCompat.setPropertyNull(json, Constants.PROP_DISCRIMINATOR);
      /*
      JsonCompat.setPropertyBoolean(json, Constants.PROP_NULLABLE, schema.nullable);
      JsonCompat.setPropertyBoolean(json, Constants.PROP_WRITE_ONLY, schema.writeOnly);
      JsonCompat.setPropertyBoolean(json, Constants.PROP_DEPRECATED, schema.deprecated);
       */
      super.writeSchema(json, node);
   }
}
