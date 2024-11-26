package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Schema;
import io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.Schema;

public class DeleteChildSchemaCommand_Aai20 extends AbstractSchemaInhCommand {

    public NodePath _schemaPath;
    public NodePath _parentPath;

    @JsonDeserialize(using= MarshallCompat.NullableJsonNodeDeserializer.class)
    public Object _oldSchema;
    public String _oldSchemaType;
    public Integer _oldSchemaIndex; // nullable for backwards compatibility

    DeleteChildSchemaCommand_Aai20() {
    }

    DeleteChildSchemaCommand_Aai20(AaiSchema schema) {
        this._schemaPath = Library.createNodePath(schema);
        this._parentPath = Library.createNodePath(schema.parent());
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteChildSchemaCommand_Aai20] Executing.");
        this._oldSchema = null;

        AaiSchema childSchema = (AaiSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(childSchema)) {
            return;
        }

        // Remove the schema from its parent
        DeleteChildSchemaCommand_Aai20.SchemaRemoverVisitor schemaRemover = new DeleteChildSchemaCommand_Aai20.SchemaRemoverVisitor();
        Library.visitNode(childSchema, schemaRemover);

        this._oldSchema = Library.writeNode(childSchema);
        this._oldSchemaType = schemaRemover.type;
        this._oldSchemaIndex = schemaRemover.index;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteChildSchemaCommand_Aai20] Reverting.");
        if (this.isNullOrUndefined(this._oldSchema)) {
            return;
        }

        AaiSchema parent = (AaiSchema) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        // Create the schema and unmarshal it from the saved JSON data
        Schema schema = this.createSchema(parent, this._oldSchemaType);
        Library.readNode(this._oldSchema, schema);

        // Add the schema back to the parent
        DeleteChildSchemaCommand_Aai20.SchemaAdderVisitor schemaAdder = new DeleteChildSchemaCommand_Aai20.SchemaAdderVisitor(this._oldSchemaIndex);
        Library.visitNode(schema, schemaAdder);
    }

    /**
     * Visitor used to remove a child schema from its parent.
     * @author eric.wittmann@gmail.com
     */
    private static class SchemaRemoverVisitor extends CombinedVisitorAdapter {

        public String type;
        public int index;

        /**
         * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitAllOfSchema(io.apicurio.datamodels.openapi.models.OasSchema)
         */
        @Override
        public void visitAllOfSchema(AaiSchema node) {
            AaiSchema parentSchema = (AaiSchema) node.parent();
            this.index = parentSchema.allOf.indexOf(node);
            parentSchema.removeAllOfSchema(node);
            this.type = TYPE_ALL_OF;
        }

        /**
         * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitAnyOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema)
         */
        @Override
        public void visitAnyOfSchema(AaiSchema node) {
            Aai20Schema parentSchema = (Aai20Schema) node.parent();
            this.index = parentSchema.anyOf.indexOf(node);
            parentSchema.removeAnyOfSchema(node);
            this.type = TYPE_ANY_OF;
        }

        /**
         * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitOneOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema)
         */
        @Override
        public void visitOneOfSchema(AaiSchema node) {
            Aai20Schema parentSchema = (Aai20Schema) node.parent();
            this.index = parentSchema.oneOf.indexOf(node);
            parentSchema.removeOneOfSchema(node);
            this.type = TYPE_ONE_OF;
        }

    }


    /**
     * Visitor used to add a child schema to its parent.
     * @author eric.wittmann@gmail.com
     */
    private static class SchemaAdderVisitor extends CombinedVisitorAdapter {

        private Integer index;

        SchemaAdderVisitor(Integer index) {
            this.index = index;
        }

        /**
         * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitAllOfSchema(io.apicurio.datamodels.openapi.models.OasSchema)
         */
        @Override
        public void visitAllOfSchema(AaiSchema node) {
            Aai20Schema parentSchema = (Aai20Schema) node.parent();
            parentSchema.restoreAllOfSchema(this.index, node);
        }

        /**
         * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitAnyOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema)
         */
        @Override
        public void visitAnyOfSchema(AaiSchema node) {
            Aai20Schema parentSchema = (Aai20Schema) node.parent();
            parentSchema.restoreAnyOfSchema(this.index, node);
        }

        /**
         * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitOneOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema)
         */
        @Override
        public void visitOneOfSchema(AaiSchema node) {
            Aai20Schema parentSchema = (Aai20Schema) node.parent();
            parentSchema.restoreOneOfSchema(this.index, node);
        }

    }
}
