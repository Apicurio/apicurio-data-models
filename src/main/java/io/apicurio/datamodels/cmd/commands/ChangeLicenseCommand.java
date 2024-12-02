package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to modify the license information of a document.
 * @author eric.wittmann@gmail.com
 */
public class ChangeLicenseCommand extends AbstractCommand {

    public String _newLicenseName;
    public String _newLicenseUrl;

    public ObjectNode _oldLicense;
    public boolean _nullInfo;
    
    public ChangeLicenseCommand() {
    }

    public ChangeLicenseCommand(String name, String url) {
        this._newLicenseName = name;
        this._newLicenseUrl = url;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ChangeLicenseCommand] Executing.");
        this._oldLicense = null;
        this._nullInfo = false;

        if (this.isNullOrUndefined(document.getInfo())) {
            this._nullInfo = true;
            document.setInfo(document.createInfo());
            this._oldLicense = null;
        } else {
            this._oldLicense = null;
            if (!this.isNullOrUndefined(document.getInfo().getLicense())) {
                this._oldLicense = Library.writeNode(document.getInfo().getLicense());
            }
        }
        document.getInfo().setLicense(document.getInfo().createLicense());
        document.getInfo().getLicense().setName(this._newLicenseName);
        document.getInfo().getLicense().setUrl(this._newLicenseUrl);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangeLicenseCommand] Reverting.");
        if (this._nullInfo) {
            document.setInfo(null);
        } else if (NodeUtil.isDefined(this._oldLicense)) {
            document.getInfo().setLicense(document.getInfo().createLicense());
            Library.readNode(this._oldLicense, document.getInfo().getLicense());
        } else {
            document.getInfo().setLicense(null);
        }
    }

}
