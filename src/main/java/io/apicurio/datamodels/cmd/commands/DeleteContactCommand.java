package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Contact;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Info;

/**
 * A command used to delete the contact.
 * @author eric.wittmann@gmail.com
 */
public class DeleteContactCommand extends DeleteNodeCommand<Contact> {
    
    public DeleteContactCommand() {
    }

    public DeleteContactCommand(Info info) {
        super("contact", info);
    }
    
    /**
     * @see DeleteNodeCommand#readNode(Document, ObjectNode)
     */
    @Override
    protected Contact readNode(Document doc, ObjectNode node) {
        Contact contact = doc.getInfo().createContact();
        Library.readNode(node, contact);
        return contact;
    }

}
