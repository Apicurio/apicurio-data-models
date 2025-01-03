package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to modify the version of a document.
 * @author eric.wittmann@gmail.com
 */
public class ChangeVersionCommand extends AbstractCommand {
    
    public String _newVersion;

    public String _oldVersion;
    public boolean _nullInfo;
    
    /**
     * Constructor.
     */
    public ChangeVersionCommand() {
    }
    
    /**
     * Constructor.
     * @param newVersion
     */
    public ChangeVersionCommand(String newVersion) {
        this._newVersion = newVersion;
    }
    
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ChangeVersionCommand] Executing.");
        if (NodeUtil.isNullOrUndefined(document.getInfo())) {
            document.setInfo(document.createInfo());
            this._nullInfo = true;
            this._oldVersion = null;
        } else {
            this._oldVersion = document.getInfo().getVersion();
        }
        document.getInfo().setVersion(this._newVersion);
    }

    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangeVersionCommand] Reverting.");
        if (this._nullInfo) {
            document.setInfo(null);
        } else {
            document.getInfo().setVersion(this._oldVersion);
        }
    }

}
