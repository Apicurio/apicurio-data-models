package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.License;

/**
 * A command used to delete the license.
 * 
 * @author eric.wittmann@gmail.com
 */
public class DeleteLicenseCommand extends DeleteNodeCommand<License> {
    
    public DeleteLicenseCommand() {
    }

    public DeleteLicenseCommand(Info info) {
        super("license", info);
    }

    /**
     * @see io.apicurio.datamodels.cmd.commands.DeleteNodeCommand#readNode(Document, ObjectNode)
     */
    @Override
    protected License readNode(Document doc, ObjectNode node) {
        License license = doc.getInfo().createLicense();
        Library.readNode(node, license);
        return license;
    }

}
