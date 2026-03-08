package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiLink;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Link;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Response;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Components;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Link;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Response;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

/**
 * A command used to add a link to a response or to components/links.
 * @author eric.wittmann@gmail.com
 */
public class AddLinkCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _linkName;
    public ObjectNode _from;

    public boolean _created;

    public AddLinkCommand() {
    }

    public AddLinkCommand(Node parent, String linkName, ObjectNode from) {
        this._parentPath = NodePathUtil.createNodePath(parent);
        this._linkName = linkName;
        this._from = from;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[AddLinkCommand] Executing.");
        this._created = false;

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        if (parent instanceof OpenApi30Response) {
            OpenApi30Response response30 = (OpenApi30Response) parent;
            if (!this.isNullOrUndefined(response30.getLinks()) && response30.getLinks().containsKey(this._linkName)) {
                return;
            }
            OpenApi30Link link = response30.createLink();
            Library.readNode(this._from, link);
            response30.addLink(this._linkName, link);
            this._created = true;
        } else if (parent instanceof OpenApi31Response) {
            OpenApi31Response response31 = (OpenApi31Response) parent;
            if (!this.isNullOrUndefined(response31.getLinks()) && response31.getLinks().containsKey(this._linkName)) {
                return;
            }
            OpenApi31Link link = response31.createLink();
            Library.readNode(this._from, link);
            response31.addLink(this._linkName, link);
            this._created = true;
        } else if (parent instanceof OpenApi30Components) {
            OpenApi30Components components30 = (OpenApi30Components) parent;
            if (!this.isNullOrUndefined(components30.getLinks()) && components30.getLinks().containsKey(this._linkName)) {
                return;
            }
            OpenApiLink link = components30.createLink();
            Library.readNode(this._from, link);
            components30.addLink(this._linkName, link);
            this._created = true;
        } else if (parent instanceof OpenApi31Components) {
            OpenApi31Components components31 = (OpenApi31Components) parent;
            if (!this.isNullOrUndefined(components31.getLinks()) && components31.getLinks().containsKey(this._linkName)) {
                return;
            }
            OpenApiLink link = components31.createLink();
            Library.readNode(this._from, link);
            components31.addLink(this._linkName, link);
            this._created = true;
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[AddLinkCommand] Reverting.");
        if (!this._created) {
            return;
        }

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        if (parent instanceof OpenApi30Response) {
            ((OpenApi30Response) parent).removeLink(this._linkName);
        } else if (parent instanceof OpenApi31Response) {
            ((OpenApi31Response) parent).removeLink(this._linkName);
        } else if (parent instanceof OpenApi30Components) {
            ((OpenApi30Components) parent).removeLink(this._linkName);
        } else if (parent instanceof OpenApi31Components) {
            ((OpenApi31Components) parent).removeLink(this._linkName);
        }
    }

}
