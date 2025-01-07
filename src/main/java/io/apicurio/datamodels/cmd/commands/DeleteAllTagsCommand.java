package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiTag;
import io.apicurio.datamodels.util.LoggerUtil;

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

        OpenApiDocument doc = (OpenApiDocument) document;

        this._oldTags = new ArrayList<>();
        // Save the old tags (if any)
        List<? extends Tag> tags = doc.getTags();
        if (!this.isNullOrUndefined(tags)) {
            tags.forEach( tag -> {
                this._oldTags.add(Library.writeNode(tag));
            });
        }
        doc.clearTags();
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

        OpenApiDocument doc = (OpenApiDocument) document;

        this._oldTags.forEach( oldTag -> {
            OpenApiTag tag = doc.createTag();
            Library.readNode(oldTag, tag);
            doc.addTag(tag);
        });
    }

}
