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

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.ISecurityRequirementParent;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;

/**
 * A command used to create a new security requirement in a document.
 * @author eric.wittmann@gmail.com
 */
public class AddSecurityRequirementCommand extends AbstractCommand {

    public NodePath _parentPath;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _requirement;

    public boolean _added;
    
    AddSecurityRequirementCommand() {
    }

    AddSecurityRequirementCommand(ISecurityRequirementParent parent, SecurityRequirement requirement) {
        this._parentPath = Library.createNodePath((Node) parent);
        this._requirement = Library.writeNode(requirement);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[AddSecurityRequirementCommand] Executing.");
        this._added = false;

        ISecurityRequirementParent parent = (ISecurityRequirementParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }
        SecurityRequirement requirement = parent.createSecurityRequirement();
        Library.readNode(this._requirement, requirement);
        parent.addSecurityRequirement(requirement);
        this._added = true;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[AddSecurityRequirementCommand] Reverting.");
        if (!this._added) {
            return;
        }

        ISecurityRequirementParent parent = (ISecurityRequirementParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        List<SecurityRequirement> security = parent.getSecurityRequirements();
        if (this.isNullOrUndefined(security)) {
            LoggerCompat.info("[AddSecurityRequirementCommand] Security requirement not found, skipping undo.");
            return;
        }

        SecurityRequirement requirement = parent.createSecurityRequirement();
        Library.readNode(this._requirement, requirement);

        int idx = this.indexOfRequirement(security, requirement);
        if (idx != -1) {
            security.remove(idx);
        }
    }

    protected int indexOfRequirement(List<SecurityRequirement> requirements, SecurityRequirement requirement) {
        int idx = 0;
        for (SecurityRequirement r : requirements) {
            if (this.isEqual(r, requirement)) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    protected boolean isEqual(SecurityRequirement req1, SecurityRequirement req2) {
        List<String> names1 = req1.getSecurityRequirementNames();
        List<String> names2 = req2.getSecurityRequirementNames();
        if (names1.size() != names2.size()) {
            return false;
        }
        boolean rval = true;
        for (String name1 : names1) {
            if (names2.indexOf(name1) == -1) {
                rval = false;
            }
        }
        return rval;
    }

}
