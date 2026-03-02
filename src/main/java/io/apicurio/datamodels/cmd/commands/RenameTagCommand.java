package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiTag;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.List;

/**
 * A command used to rename a tag.
 * @author eric.wittmann@gmail.com
 */
public class RenameTagCommand extends AbstractCommand {

    public String _oldName;
    public String _newName;

    public boolean _tagRenamed;

    public RenameTagCommand() {
    }

    public RenameTagCommand(String oldName, String newName) {
        this._oldName = oldName;
        this._newName = newName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[RenameTagCommand] Executing.");
        this._tagRenamed = false;

        OpenApiDocument doc = (OpenApiDocument) document;
        List<OpenApiTag> tags = (List<OpenApiTag>) doc.getTags();
        if (this.isNullOrUndefined(tags)) {
            return;
        }

        // Check if new name already exists
        for (OpenApiTag tag : tags) {
            if (this._newName.equals(tag.getName())) {
                return;
            }
        }

        // Find and rename the tag
        for (OpenApiTag tag : tags) {
            if (this._oldName.equals(tag.getName())) {
                tag.setName(this._newName);
                this._tagRenamed = true;
                return;
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[RenameTagCommand] Reverting.");
        if (!this._tagRenamed) {
            return;
        }

        OpenApiDocument doc = (OpenApiDocument) document;
        List<OpenApiTag> tags = (List<OpenApiTag>) doc.getTags();
        if (this.isNullOrUndefined(tags)) {
            return;
        }

        for (OpenApiTag tag : tags) {
            if (this._newName.equals(tag.getName())) {
                tag.setName(this._oldName);
                return;
            }
        }
    }

}
