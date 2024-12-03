package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.OpenApiRequestBody;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Header;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30MediaType;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Parameter;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Response;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

/**
 * A command used to delete a single mediaType from an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteMediaTypeCommand extends AbstractCommand {

    public String _mediaTypeName;
    public NodePath _mediaTypePath;
    public NodePath _parentPath;

    public ObjectNode _oldMediaType;
    public int _oldMediaTypeIndex;

    public DeleteMediaTypeCommand() {
    }

    public DeleteMediaTypeCommand(OpenApiMediaType mediaType) {
        this._mediaTypeName = mediaType.mapPropertyName();
        this._mediaTypePath = Library.createNodePath(mediaType);
        this._parentPath = Library.createNodePath(mediaType.parent());
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteMediaTypeCommand] Executing.");
        this._oldMediaType = null;

        OpenApiMediaType mediaType = (OpenApiMediaType) NodePathUtil.resolveNodePath(this._mediaTypePath, document);
        if (this.isNullOrUndefined(mediaType)) {
            return;
        }

        this._oldMediaType = Library.writeNode(mediaType);

        MediaTypeRemover viz = new MediaTypeRemover(this._mediaTypeName);
        mediaType.parent().accept(viz);
        this._oldMediaTypeIndex = viz.index;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteMediaTypeCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldMediaType)) {
            return;
        }

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        MediaTypeRestorer viz = new MediaTypeRestorer(this._mediaTypeName, this._oldMediaType, this._oldMediaTypeIndex);
        parent.accept(viz);
    }

    public static class MediaTypeRemover extends CombinedVisitorAdapter {
        private String mediaTypeName;
        public int index = -1;

        public MediaTypeRemover(String mediaTypeName) {
            this.mediaTypeName = mediaTypeName;
        }

        @Override
        public void visitParameter(Parameter node) {
            OpenApi30Parameter param = (OpenApi30Parameter) node;
            index = AbstractCommand.indexOf(param.getContent().keySet(), this.mediaTypeName);
            param.removeContent(this.mediaTypeName);
        }

        @Override
        public void visitRequestBody(OpenApiRequestBody node) {
            index = AbstractCommand.indexOf(node.getContent().keySet(), this.mediaTypeName);
            node.removeContent(this.mediaTypeName);
        }

        @Override
        public void visitResponse(OpenApiResponse node) {
            OpenApi30Response response = (OpenApi30Response) node;
            index = AbstractCommand.indexOf(response.getContent().keySet(), this.mediaTypeName);
            response.removeContent(this.mediaTypeName);
        }

        @Override
        public void visitHeader(OpenApiHeader node) {
            OpenApi30Header header = (OpenApi30Header) node;
            index = AbstractCommand.indexOf(header.getContent().keySet(), this.mediaTypeName);
            header.removeContent(this.mediaTypeName);
        }
    }

    public static class MediaTypeRestorer extends CombinedVisitorAdapter {
        private final String mediaTypeName;
        private final ObjectNode oldMediaType;
        private final int oldMediaTypeIndex;

        public MediaTypeRestorer(String mediaTypeName, ObjectNode oldMediaType, int oldMediaTypeIndex) {
            this.mediaTypeName = mediaTypeName;
            this.oldMediaType = oldMediaType;
            this.oldMediaTypeIndex = oldMediaTypeIndex;
        }

        @Override
        public void visitParameter(Parameter node) {
            OpenApi30Parameter param = (OpenApi30Parameter) node;
            OpenApi30MediaType mediaType = param.createMediaType();
            Library.readNode(this.oldMediaType, mediaType);
            if (this.oldMediaTypeIndex > -1) {
                param.insertContent(this.mediaTypeName, mediaType, this.oldMediaTypeIndex);
            } else {
                param.addContent(this.mediaTypeName, mediaType);
            }
        }

        @Override
        public void visitRequestBody(OpenApiRequestBody node) {
            OpenApiMediaType mediaType = node.createMediaType();
            Library.readNode(this.oldMediaType, mediaType);
            if (this.oldMediaTypeIndex > -1) {
                node.insertContent(this.mediaTypeName, mediaType, this.oldMediaTypeIndex);
            } else {
                node.addContent(this.mediaTypeName, mediaType);
            }
        }

        @Override
        public void visitResponse(OpenApiResponse node) {
            OpenApi30Response response = (OpenApi30Response) node;
            OpenApi30MediaType mediaType = response.createMediaType();
            Library.readNode(this.oldMediaType, mediaType);
            if (this.oldMediaTypeIndex > -1) {
                response.insertContent(this.mediaTypeName, mediaType, this.oldMediaTypeIndex);
            } else {
                response.addContent(this.mediaTypeName, mediaType);
            }
        }

        @Override
        public void visitHeader(OpenApiHeader node) {
            OpenApi30Header header = (OpenApi30Header) node;
            OpenApi30MediaType mediaType = header.createMediaType();
            Library.readNode(this.oldMediaType, mediaType);
            if (this.oldMediaTypeIndex > -1) {
                header.insertContent(this.mediaTypeName, mediaType, this.oldMediaTypeIndex);
            } else {
                header.addContent(this.mediaTypeName, mediaType);
            }
        }
    }

}
