package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to modify the description of a document.
 * @author eric.wittmann@gmail.com
 */
public class ChangeDescriptionCommand extends AbstractCommand {
    
    public String _newDescription;

    public String _oldDescription;
    public boolean _nullInfo;
    
    /**
     * Constructor.
     */
    public ChangeDescriptionCommand() {
    }
    
    /**
     * Constructor.
     * @param newDescription
     */
    public ChangeDescriptionCommand(String newDescription) {
        this._newDescription = newDescription;
    }
    
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ChangeDescriptionCommand] Executing.");
        if (NodeUtil.isNullOrUndefined(document.getInfo())) {
            document.setInfo(document.createInfo());
            this._nullInfo = true;
            this._oldDescription = null;
        } else {
            this._oldDescription = document.getInfo().getDescription();
        }
        document.getInfo().setDescription(this._newDescription);
    }
    
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangeDescriptionCommand] Reverting.");
        if (this._nullInfo) {
            document.setInfo(null);
        } else {
            document.getInfo().setDescription(this._oldDescription);
        }
    }

}
