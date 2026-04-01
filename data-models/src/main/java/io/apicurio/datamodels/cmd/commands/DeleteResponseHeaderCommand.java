package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiHeadersParent;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A command used to delete a header from a response.
 * @author eric.wittmann@gmail.com
 */
public class DeleteResponseHeaderCommand extends AbstractCommand {

    public NodePath _responsePath;
    public String _headerName;

    public ObjectNode _oldHeader;
    public int _oldIndex;

    public DeleteResponseHeaderCommand() {
    }

    public DeleteResponseHeaderCommand(OpenApiHeadersParent response, String headerName) {
        this._responsePath = NodePathUtil.createNodePath((Node) response);
        this._headerName = headerName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteResponseHeaderCommand] Executing.");
        this._oldHeader = null;

        OpenApiHeadersParent response = (OpenApiHeadersParent) NodePathUtil.resolveNodePath(this._responsePath, document);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        Map<String, ? extends OpenApiHeader> headers = (Map<String, ? extends OpenApiHeader>) response.getHeaders();
        if (this.isNullOrUndefined(headers) || !headers.containsKey(this._headerName)) {
            return;
        }

        // Save the header and its index for undo
        OpenApiHeader header = headers.get(this._headerName);
        this._oldHeader = Library.writeNode(header);
        List<String> headerNames = new ArrayList<>(headers.keySet());
        this._oldIndex = headerNames.indexOf(this._headerName);

        // Remove the header
        response.removeHeader(this._headerName);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteResponseHeaderCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldHeader)) {
            return;
        }

        OpenApiHeadersParent response = (OpenApiHeadersParent) NodePathUtil.resolveNodePath(this._responsePath, document);
        if (this.isNullOrUndefined(response)) {
            return;
        }

        OpenApiHeader newHeader = response.createHeader();
        Library.readNode(this._oldHeader, newHeader);
        response.insertHeader(this._headerName, newHeader, this._oldIndex);
    }

}
