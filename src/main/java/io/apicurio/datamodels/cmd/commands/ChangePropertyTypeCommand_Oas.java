package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.models.SimplifiedPropertyType;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.openapi.models.OasSchema;

public class ChangePropertyTypeCommand_Oas extends ChangePropertyTypeCommand {
    // TODO: Ordering on undo
    public ChangePropertyTypeCommand_Oas() {
    }

    public ChangePropertyTypeCommand_Oas(IPropertySchema property, SimplifiedPropertyType newType) {
        super(property, newType);
    }

    /**
     * @see ChangePropertyTypeCommand#restoreSchemaInternalProperties(Schema, Schema)
     */
    @Override
    protected void restoreSchemaInternalProperties(Schema toSchema, Schema fromSchema) {
        final OasSchema newSchema = (OasSchema) toSchema;
        final OasSchema oldSchema = (OasSchema) fromSchema;
        
        newSchema.$ref = null;
        newSchema.type = null;
        newSchema.enum_ = null;
        newSchema.format = null;
        newSchema.items = null;
        if (ModelUtils.isDefined(oldSchema)) {
            newSchema.$ref = oldSchema.$ref;
            newSchema.type = oldSchema.type;
            newSchema.enum_ = oldSchema.enum_;
            newSchema.format = oldSchema.format;
            newSchema.items = oldSchema.items;
            if (ModelUtils.isDefined(newSchema.items) && !NodeCompat.isList(newSchema.items)) {
                Node itemsNode = (Node) newSchema.items;
                itemsNode._parent = newSchema;
                itemsNode._ownerDocument = newSchema.ownerDocument();
            }
        }
    }
    
    
}