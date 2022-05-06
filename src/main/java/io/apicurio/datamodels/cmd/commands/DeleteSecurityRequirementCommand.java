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
 * A command used to delete a single securityRequirement from an operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteSecurityRequirementCommand extends AbstractCommand {
    // TODO: Ordering on undo needs to be preserved

    public NodePath _parentPath;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _requirement;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldRequirement;
    
    DeleteSecurityRequirementCommand() {
    }
    
    DeleteSecurityRequirementCommand(ISecurityRequirementParent parent, SecurityRequirement requirement) {
        this._parentPath = Library.createNodePath((Node) parent);
        this._requirement = Library.writeNode(requirement);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteSecurityRequirementCommand] Executing.");
        this._oldRequirement = null;

        ISecurityRequirementParent parent = (ISecurityRequirementParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        SecurityRequirement requirement = parent.createSecurityRequirement();
        Library.readNode(this._requirement, requirement);

        List<SecurityRequirement> requirements = parent.getSecurityRequirements();
        int idx = this.indexOfRequirement(requirements, requirement);
        if (idx != -1) {
            this._oldRequirement = Library.writeNode(requirements.get(idx));
            requirements.remove(idx);
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteSecurityRequirementCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldRequirement)) {
            return;
        }

        ISecurityRequirementParent parent = (ISecurityRequirementParent) this._parentPath.resolve(document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        SecurityRequirement restoredRequirement = parent.createSecurityRequirement();
        Library.readNode(this._oldRequirement, restoredRequirement);
        parent.addSecurityRequirement(restoredRequirement);
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
            if (names2.indexOf(name1) == -1 || !areScopesEqual(req1.getScopes(name1), req2.getScopes(name1))) {
                rval = false;
            }
        }
        return rval;
    }
    
    protected boolean areScopesEqual(List<String> scopes1, List<String> scopes2) {
        if(scopes1.size() != scopes2.size()) {
            return false;
        }
        boolean rval = true;
        for (String scope1 : scopes1) {
            if (scopes2.indexOf(scope1) == -1) {
                rval = false;
            }
        }
        return rval;
    }

}
