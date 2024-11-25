package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to modify the title of a document.
 * @author eric.wittmann@gmail.com
 */
public class ChangeTitleCommand extends AbstractCommand {
    
    public String _newTitle;

    public String _oldTitle;
    public boolean _nullInfo;
    
    /**
     * Constructor.
     */
    public ChangeTitleCommand() {
    }
    
    /**
     * Constructor.
     * @param newTitle
     */
    public ChangeTitleCommand(String newTitle) {
        this._newTitle = newTitle;
    }
    
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ChangeTitleCommand] Executing.");
        if (NodeUtil.isNullOrUndefined(document.getInfo())) {
            document.setInfo(document.createInfo());
            this._nullInfo = true;
            this._oldTitle = null;
        } else {
            this._oldTitle = document.getInfo().getTitle();
        }
        document.getInfo().setTitle(this._newTitle);
    }
    
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangeTitleCommand] Reverting.");
        if (this._nullInfo) {
            document.setInfo(null);
        } else {
            document.getInfo().setTitle(this._oldTitle);
        }
    }

}
