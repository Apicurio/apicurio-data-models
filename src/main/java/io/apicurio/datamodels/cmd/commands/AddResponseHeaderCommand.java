package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiHeadersParent;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.Map;

/**
 * A command used to add a header to a response.
 * @author eric.wittmann@gmail.com
 */
public class AddResponseHeaderCommand extends AbstractCommand {

    public NodePath _responsePath;
    public String _headerName;
    public String _description;
    public String _schemaType;
    public String _schemaRef;

    public boolean _created;

    public AddResponseHeaderCommand() {
    }

    public AddResponseHeaderCommand(OpenApiHeadersParent response, String headerName,
                                    String description, String schemaType, String schemaRef) {
        this._responsePath = NodePathUtil.createNodePath((Node) response);
        this._headerName = headerName;
        this._description = description;
        this._schemaType = schemaType;
        this._schemaRef = schemaRef;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[AddResponseHeaderCommand] Executing.");
        this._created = false;

        OpenApiHeadersParent response = (OpenApiHeadersParent) NodePathUtil.resolveNodePath(this._responsePath, document);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        // Check if header already exists
        Map<String, ? extends OpenApiHeader> headers = (Map<String, ? extends OpenApiHeader>) response.getHeaders();
        if (!this.isNullOrUndefined(headers) && headers.containsKey(this._headerName)) {
            return;
        }

        // Create the header
        OpenApiHeader header = response.createHeader();
        header.setDescription(this._description);

        // Set schema if provided
        if (!this.isNullOrUndefined(this._schemaType) || !this.isNullOrUndefined(this._schemaRef)) {
            OpenApiSchema schema = header.createSchema();
            if (!this.isNullOrUndefined(this._schemaRef)) {
                schema.set$ref(this._schemaRef);
            } else if (!this.isNullOrUndefined(this._schemaType)) {
                schema.setType(this._schemaType);
            }
            header.setSchema(schema);
        }

        response.addHeader(this._headerName, header);
        this._created = true;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[AddResponseHeaderCommand] Reverting.");
        if (!this._created) {
            return;
        }

        OpenApiHeadersParent response = (OpenApiHeadersParent) NodePathUtil.resolveNodePath(this._responsePath, document);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        response.removeHeader(this._headerName);
    }

}
