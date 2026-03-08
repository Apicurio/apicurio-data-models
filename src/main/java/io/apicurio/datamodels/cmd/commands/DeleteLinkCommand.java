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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A command used to delete a link from a response or from components/links.
 * @author eric.wittmann@gmail.com
 */
public class DeleteLinkCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _linkName;

    public ObjectNode _oldLink;
    public int _oldIndex;

    public DeleteLinkCommand() {
    }

    public DeleteLinkCommand(Node parent, String linkName) {
        this._parentPath = NodePathUtil.createNodePath(parent);
        this._linkName = linkName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteLinkCommand] Executing.");
        this._oldLink = null;

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        if (parent instanceof OpenApi30Response) {
            OpenApi30Response response30 = (OpenApi30Response) parent;
            Map<String, OpenApi30Link> links = response30.getLinks();
            if (this.isNullOrUndefined(links) || !links.containsKey(this._linkName)) {
                return;
            }
            OpenApi30Link link = links.get(this._linkName);
            this._oldLink = Library.writeNode(link);
            List<String> linkNames = new ArrayList<>(links.keySet());
            this._oldIndex = linkNames.indexOf(this._linkName);
            response30.removeLink(this._linkName);
        } else if (parent instanceof OpenApi31Response) {
            OpenApi31Response response31 = (OpenApi31Response) parent;
            Map<String, OpenApi31Link> links = response31.getLinks();
            if (this.isNullOrUndefined(links) || !links.containsKey(this._linkName)) {
                return;
            }
            OpenApi31Link link = links.get(this._linkName);
            this._oldLink = Library.writeNode(link);
            List<String> linkNames = new ArrayList<>(links.keySet());
            this._oldIndex = linkNames.indexOf(this._linkName);
            response31.removeLink(this._linkName);
        } else if (parent instanceof OpenApi30Components) {
            OpenApi30Components components30 = (OpenApi30Components) parent;
            Map<String, OpenApiLink> links = components30.getLinks();
            if (this.isNullOrUndefined(links) || !links.containsKey(this._linkName)) {
                return;
            }
            OpenApiLink link = links.get(this._linkName);
            this._oldLink = Library.writeNode(link);
            List<String> linkNames = new ArrayList<>(links.keySet());
            this._oldIndex = linkNames.indexOf(this._linkName);
            components30.removeLink(this._linkName);
        } else if (parent instanceof OpenApi31Components) {
            OpenApi31Components components31 = (OpenApi31Components) parent;
            Map<String, OpenApiLink> links = components31.getLinks();
            if (this.isNullOrUndefined(links) || !links.containsKey(this._linkName)) {
                return;
            }
            OpenApiLink link = links.get(this._linkName);
            this._oldLink = Library.writeNode(link);
            List<String> linkNames = new ArrayList<>(links.keySet());
            this._oldIndex = linkNames.indexOf(this._linkName);
            components31.removeLink(this._linkName);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteLinkCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldLink)) {
            return;
        }

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        if (parent instanceof OpenApi30Response) {
            OpenApi30Response response30 = (OpenApi30Response) parent;
            OpenApi30Link link = response30.createLink();
            Library.readNode(this._oldLink, link);
            response30.insertLink(this._linkName, link, this._oldIndex);
        } else if (parent instanceof OpenApi31Response) {
            OpenApi31Response response31 = (OpenApi31Response) parent;
            OpenApi31Link link = response31.createLink();
            Library.readNode(this._oldLink, link);
            response31.insertLink(this._linkName, link, this._oldIndex);
        } else if (parent instanceof OpenApi30Components) {
            OpenApi30Components components30 = (OpenApi30Components) parent;
            OpenApiLink link = components30.createLink();
            Library.readNode(this._oldLink, link);
            components30.insertLink(this._linkName, link, this._oldIndex);
        } else if (parent instanceof OpenApi31Components) {
            OpenApi31Components components31 = (OpenApi31Components) parent;
            OpenApiLink link = components31.createLink();
            Library.readNode(this._oldLink, link);
            components31.insertLink(this._linkName, link, this._oldIndex);
        }
    }

}
