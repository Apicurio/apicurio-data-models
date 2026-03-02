package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiTag;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.List;

/**
 * A command used to delete a single tag from a document.
 * @author eric.wittmann@gmail.com
 */
public class DeleteTagCommand extends AbstractCommand {

    public String _tagName;

    public ObjectNode _oldTag;
    public int _tagIndex;

    public DeleteTagCommand() {
    }

    public DeleteTagCommand(String tagName) {
        this._tagName = tagName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteTagCommand] Executing.");
        this._oldTag = null;
        this._tagIndex = -1;

        OpenApiDocument doc = (OpenApiDocument) document;
        List<OpenApiTag> tags = (List<OpenApiTag>) doc.getTags();
        if (this.isNullOrUndefined(tags)) {
            return;
        }

        int idx = 0;
        for (OpenApiTag tag : tags) {
            if (this._tagName.equals(tag.getName())) {
                this._oldTag = Library.writeNode(tag);
                this._tagIndex = idx;
                doc.removeTag(tag);
                return;
            }
            idx++;
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteTagCommand] Reverting.");
        if (this._tagIndex < 0 || this.isNullOrUndefined(this._oldTag)) {
            return;
        }

        OpenApiDocument doc = (OpenApiDocument) document;
        OpenApiTag newTag = doc.createTag();
        Library.readNode(this._oldTag, newTag);
        doc.insertTag(newTag, this._tagIndex);
    }

}
