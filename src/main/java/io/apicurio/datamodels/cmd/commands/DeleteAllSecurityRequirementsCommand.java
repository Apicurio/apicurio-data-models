/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.cmd.commands;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.ISecurityRequirementParent;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.openapi.models.OasSecurityRequirement;

/**
 * A command used to delete all security requirements from a document or operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllSecurityRequirementsCommand extends AbstractCommand {

    public NodePath _parentPath;
    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldSecurityRequirements;
    
    DeleteAllSecurityRequirementsCommand() {
    }
    
    DeleteAllSecurityRequirementsCommand(ISecurityRequirementParent parent) {
        Library.createNodePath((Node) parent);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllSecurityRequirementsCommand] Executing.");
        this._oldSecurityRequirements = new ArrayList<>();

        ISecurityRequirementParent parent = (ISecurityRequirementParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }
        
        // Save the old security-requirements (if any)
        List<SecurityRequirement> requirements = parent.getSecurityRequirements();
        if (!this.isNullOrUndefined(requirements)) {
            requirements.forEach( req -> {
                this._oldSecurityRequirements.add(Library.writeNode(req));
            });
        }

        NodeCompat.setProperty(parent, Constants.PROP_SECURITY, null);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllSecurityRequirementsCommand] Reverting.");
        if (ModelUtils.isDefined(this._oldSecurityRequirements) && this._oldSecurityRequirements.size() == 0) {
            return;
        }

        ISecurityRequirementParent parent = (ISecurityRequirementParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        List<SecurityRequirement> requirements = parent.getSecurityRequirements();
        if (this.isNullOrUndefined(requirements)) {
            requirements = new ArrayList<>();
            NodeCompat.setProperty(parent, Constants.PROP_SECURITY, requirements);
        }
        for (Object oldSecurityRequirement : this._oldSecurityRequirements) {
            OasSecurityRequirement requirement = (OasSecurityRequirement) parent.createSecurityRequirement();
            Library.readNode(oldSecurityRequirement, requirement);
            requirements.add(requirement);
        }
    }

}
