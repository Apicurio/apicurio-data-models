package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Header;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Headers;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Response;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Header;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Response;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Header;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Response;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A command used to delete all headers from a document.
 * @author vvilerio
 */
public class DeleteAllHeadersCommand extends AbstractCommand {

    public NodePath _parentPath;

    public Map<String, ObjectNode> _oldHeaders;

    public DeleteAllHeadersCommand() {
    }

    public DeleteAllHeadersCommand(OpenApiResponse parent) {
        this._parentPath = Library.createNodePath(parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllHeadersCommand] Executing.");

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        Map<String, ? extends Node> headers = getHeaders(parent);
        if (this.isNullOrUndefined(headers) || headers.size() == 0) {
            return;
        }

        // Preserve insertion order with LinkedHashMap
        this._oldHeaders = new LinkedHashMap<>();

        // Save the headers we're about to delete for later undo
        headers.keySet().forEach(name -> {
            Node header = headers.get(name);
            this._oldHeaders.put(name, Library.writeNode(header));
        });

        clearHeaders(parent);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteAllHeaders] Reverting.");

        if (this.isNullOrUndefined(this._oldHeaders) || this._oldHeaders.size() == 0) {
            return;
        }

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        for (String k : this._oldHeaders.keySet()) {
            Node header = createHeader(parent, k);
            Library.readNode(this._oldHeaders.get(k), header);
            addHeader(parent, k, header);
        }

    }

    private Map<String, ? extends Node> getHeaders(Node parent) {
        if (ModelTypeUtil.isOpenApi2Model(parent)) {
            OpenApi20Response response = (OpenApi20Response) parent;
            OpenApi20Headers headers = response.getHeaders();
            if (headers == null) {
                return null;
            }
            Map<String, Node> rval = new LinkedHashMap<>();
            List<String> headerNames = headers.getItemNames();
            for (String headerName : headerNames) {
                OpenApi20Header header = headers.getItem(headerName);
                rval.put(headerName, header);
            }
            return rval;
        }
        if (ModelTypeUtil.isOpenApi30Model(parent)) {
            OpenApi30Response response = (OpenApi30Response) parent;
            return response.getHeaders();
        }
        if (ModelTypeUtil.isOpenApi31Model(parent)) {
            OpenApi31Response response = (OpenApi31Response) parent;
            return response.getHeaders();
        }
        return null;
    }

    private void clearHeaders(Node parent) {
        if (ModelTypeUtil.isOpenApi2Model(parent)) {
            OpenApi20Response response = (OpenApi20Response) parent;
            OpenApi20Headers headers = response.getHeaders();
            if (headers == null) {
                return;
            }
            headers.clearItems();
        }
        if (ModelTypeUtil.isOpenApi30Model(parent)) {
            OpenApi30Response response = (OpenApi30Response) parent;
            response.clearHeaders();
        }
        if (ModelTypeUtil.isOpenApi31Model(parent)) {
            OpenApi31Response response = (OpenApi31Response) parent;
            response.clearHeaders();
        }
    }

    private Node createHeader(Node parent, String k) {
        if (ModelTypeUtil.isOpenApi2Model(parent)) {
            OpenApi20Response response = (OpenApi20Response) parent;
            OpenApi20Headers headers = response.getHeaders();
            if (headers == null) {
                return null;
            }
            return headers.createHeader();
        }
        if (ModelTypeUtil.isOpenApi30Model(parent)) {
            OpenApi30Response response = (OpenApi30Response) parent;
            return response.createHeader();
        }
        if (ModelTypeUtil.isOpenApi31Model(parent)) {
            OpenApi31Response response = (OpenApi31Response) parent;
            return response.createHeader();
        }
        return null;
    }

    private void addHeader(Node parent, String name, Node header) {
        if (ModelTypeUtil.isOpenApi2Model(parent)) {
            OpenApi20Response response = (OpenApi20Response) parent;
            OpenApi20Headers headers = response.getHeaders();
            if (headers == null) {
                return;
            }
            headers.addItem(name, (OpenApi20Header) header);
        }
        if (ModelTypeUtil.isOpenApi30Model(parent)) {
            OpenApi30Response response = (OpenApi30Response) parent;
            response.addHeader(name, (OpenApi30Header) header);
        }
        if (ModelTypeUtil.isOpenApi31Model(parent)) {
            OpenApi31Response response = (OpenApi31Response) parent;
            response.addHeader(name, (OpenApi31Header) header);
        }
    }

}
