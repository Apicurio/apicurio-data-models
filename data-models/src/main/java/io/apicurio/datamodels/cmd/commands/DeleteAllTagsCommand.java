package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiTag;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.util.NodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete all tags from a document.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllTagsCommand extends AbstractCommand {

    public List<ObjectNode> _oldTags;

    public DeleteAllTagsCommand() {
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllTagsCommand] Executing.");

        this._oldTags = new ArrayList<>();

        if (ModelTypeUtil.isOpenApiModel(document)) {
            executeForOpenApi((OpenApiDocument) document);
        } else if (ModelTypeUtil.isAsyncApi2Model(document)) {
            executeForAsyncApi2(document);
        }
    }

    private void executeForOpenApi(OpenApiDocument doc) {
        // Save the old tags (if any)
        List<? extends Tag> tags = doc.getTags();
        if (!this.isNullOrUndefined(tags)) {
            tags.forEach( tag -> {
                this._oldTags.add(Library.writeNode(tag));
            });
        }
        doc.clearTags();
    }

    @SuppressWarnings("unchecked")
    private void executeForAsyncApi2(Document document) {
        // Save the old tags (if any)
        List<? extends Tag> tags = (List<? extends Tag>) NodeUtil.getNodeProperty(document, "tags");
        if (!this.isNullOrUndefined(tags)) {
            tags.forEach( tag -> {
                this._oldTags.add(Library.writeNode(tag));
            });
        }
        NodeUtil.invokeMethod(document, "clearTags");
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteAllTagsCommand] Reverting.");
        if (this._oldTags.isEmpty()) {
            return;
        }

        if (ModelTypeUtil.isOpenApiModel(document)) {
            undoForOpenApi((OpenApiDocument) document);
        } else if (ModelTypeUtil.isAsyncApi2Model(document)) {
            undoForAsyncApi2(document);
        }
    }

    private void undoForOpenApi(OpenApiDocument doc) {
        this._oldTags.forEach( oldTag -> {
            OpenApiTag tag = doc.createTag();
            Library.readNode(oldTag, tag);
            doc.addTag(tag);
        });
    }

    private void undoForAsyncApi2(Document document) {
        this._oldTags.forEach( oldTag -> {
            Tag tag = (Tag) NodeUtil.invokeMethod(document, "createTag");
            Library.readNode(oldTag, tag);
            NodeUtil.invokeMethod(document, "addTag", tag);
        });
    }

}
