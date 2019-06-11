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

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityScheme;

/**
 * A command used to rename a security scheme, along with all references to it.
 * @author eric.wittmann@gmail.com
 */
public class RenameSecuritySchemeCommand extends AbstractCommand {

    public String _oldSchemeName;
    public String _newSchemeName;

    RenameSecuritySchemeCommand() {
    }
    
    RenameSecuritySchemeCommand(String oldSchemeName, String newSchemeName) {
        this._oldSchemeName = oldSchemeName;
        this._newSchemeName = newSchemeName;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[RenameSecuritySchemeCommand] Executing.");
        this._doSecuritySchemeRename((OasDocument) document, this._oldSchemeName, this._newSchemeName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenameSecuritySchemeCommand] Reverting.");
        this._doSecuritySchemeRename((OasDocument) document, this._newSchemeName, this._oldSchemeName);
    }

    /**
     * Does the work of renaming a path from one name to another.
     * @param document
     * @param from
     * @param to
     * @private
     */
    private void _doSecuritySchemeRename(OasDocument document, String from, String to) {
        SecurityScheme scheme = null;

        // Different place to find the security scheme depending on the version.
        if (document.is2xDocument()) {
            Oas20Document doc20 = (Oas20Document) document;
            if (ModelUtils.isDefined(doc20.securityDefinitions)) {
                // If the "to" scheme already exists, do nothing!
                if (ModelUtils.isDefined(doc20.securityDefinitions.getSecurityScheme(to))) {
                    return;
                }
                scheme = doc20.securityDefinitions.removeSecurityScheme(from);
            }
        } else {
            Oas30Document doc30 = (Oas30Document) document;
            if (ModelUtils.isDefined(doc30.components)) {
                // If the "to" scheme already exists, do nothing!
                if (!this.isNullOrUndefined(doc30.components.getSecurityScheme(to))) {
                    return;
                }
                scheme = doc30.components.removeSecurityScheme(from);
            }
        }

        // If we didn't find a scheme with the "from" name, then nothing to do.
        if (this.isNullOrUndefined(scheme)) {
            return;
        }

        // Now we have the scheme - rename it!
        scheme.rename(to);
        if (document.is2xDocument()) {
            Oas20Document doc20 = (Oas20Document) document;
            doc20.securityDefinitions.addSecurityScheme(to, (Oas20SecurityScheme)scheme);
        } else {
            Oas30Document doc30 = (Oas30Document) document;
            doc30.components.addSecurityScheme(to, (Oas30SecurityScheme)scheme);
        }

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
