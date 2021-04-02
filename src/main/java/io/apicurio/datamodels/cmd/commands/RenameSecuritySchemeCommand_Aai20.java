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

import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SecurityScheme;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.visitors.TraverserDirection;

import java.util.List;

/**
 * A command used to rename a security scheme, along with all references to it.
 * @author c.desc2@gmail.com
 */
public class RenameSecuritySchemeCommand_Aai20 extends AbstractCommand {

    public String _oldSchemeName;
    public String _newSchemeName;

    RenameSecuritySchemeCommand_Aai20() {
    }

    RenameSecuritySchemeCommand_Aai20(String oldSchemeName, String newSchemeName) {
        this._oldSchemeName = oldSchemeName;
        this._newSchemeName = newSchemeName;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[RenameSecuritySchemeCommand_Aai20] Executing.");
        this._doSecuritySchemeRename((Aai20Document) document, this._oldSchemeName, this._newSchemeName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenameSecuritySchemeCommand_Aai20] Reverting.");
        this._doSecuritySchemeRename((Aai20Document) document, this._newSchemeName, this._oldSchemeName);
    }

    /**
     * Does the work of renaming a path from one name to another.
     * @param document
     * @param from
     * @param to
     * @private
     */
    private void _doSecuritySchemeRename(Aai20Document document, String from, String to) {
        SecurityScheme scheme = null;

        if (ModelUtils.isDefined(document.components)) {
            // If the "to" scheme already exists, do nothing!
            if (!this.isNullOrUndefined(document.components.getSecurityScheme(to))) {
                return;
            }
            scheme = document.components.removeSecurityScheme(from);
        }

        // If we didn't find a scheme with the "from" name, then nothing to do.
        if (this.isNullOrUndefined(scheme)) {
            return;
        }

        // Now we have the scheme - rename it!
        scheme.rename(to);
        document.components.addSecurityScheme(to, (Aai20SecurityScheme)scheme);

        // Now find all security requirements that reference the scheme and change them too.
        VisitorUtil.visitTree(document, new CombinedVisitorAdapter() {
            @Override
            public void visitSecurityRequirement(SecurityRequirement node) {
                List<String> scopes = node.removeSecurityRequirementItem(from);
                if (ModelUtils.isDefined(scopes)) {
                    node.addSecurityRequirementItem(to, scopes);
                }
            }
        }, TraverserDirection.down);
    }
}
