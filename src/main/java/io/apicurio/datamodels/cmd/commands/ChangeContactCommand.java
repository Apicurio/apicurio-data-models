
package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.util.LoggerUtil;
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
        this._oldContact = null;
        this._nullInfo = false;

        if (this.isNullOrUndefined(document.getInfo())) {
            this._nullInfo = true;
            document.setInfo(document.createInfo());
            this._oldContact = null;
        } else {
            this._oldContact = null;
            if (NodeUtil.isDefined(document.getInfo().getContact())) {
                this._oldContact = Library.writeNode(document.getInfo().getContact());
            }
        }

        document.getInfo().setContact(document.getInfo().createContact());
        document.getInfo().getContact().setName(this._newName);
        document.getInfo().getContact().setUrl(this._newUrl);
        document.getInfo().getContact().setEmail(this._newEmail);
    }
    
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangeContactCommand] Reverting.");
        if (this._nullInfo) {
            document.setInfo(null);
        } else if (NodeUtil.isDefined(this._oldContact)) {
            document.getInfo().setContact(document.getInfo().createContact());
            Library.readNode(this._oldContact, document.getInfo().getContact());
        } else {
            document.getInfo().setContact(null);
        }
    }
    

}
