package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * @author eric.wittmann@gmail.com
 */
public class ChangeContactCommand extends AbstractCommand {

    public String _newName;
    public String _newEmail;
    public String _newUrl;

    public ObjectNode _oldContact;
    public boolean _nullInfo;

    public ChangeContactCommand() {
    }

    public ChangeContactCommand(String name, String email, String url) {
        this._newName = name;
        this._newEmail = email;
        this._newUrl = url;
    }

    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ChangeContactCommand] Executing.");
        if (ModelTypeUtil.isJsonSchemaModel(document)) {
            return;
        }
        this._oldContact = null;
        this._nullInfo = false;

        Info info = (Info) NodeUtil.getProperty(document, "info");
        if (this.isNullOrUndefined(info)) {
            this._nullInfo = true;
            Info newInfo = (Info) NodeUtil.invokeMethod(document, "createInfo");
            NodeUtil.setProperty(document, "info", newInfo);
            this._oldContact = null;
            info = newInfo;
        } else {
            this._oldContact = null;
            if (NodeUtil.isDefined(info.getContact())) {
                this._oldContact = Library.writeNode(info.getContact());
            }
        }

        info.setContact(info.createContact());
        info.getContact().setName(this._newName);
        info.getContact().setUrl(this._newUrl);
        info.getContact().setEmail(this._newEmail);
    }

    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangeContactCommand] Reverting.");
        if (ModelTypeUtil.isJsonSchemaModel(document)) {
            return;
        }
        Info info = (Info) NodeUtil.getProperty(document, "info");
        if (this._nullInfo) {
            NodeUtil.setProperty(document, "info", null);
        } else if (NodeUtil.isDefined(this._oldContact)) {
            info.setContact(info.createContact());
            Library.readNode(this._oldContact, info.getContact());
        } else {
            info.setContact(null);
        }
    }

}
