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
import java.util.function.Consumer;

import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SecurityScheme;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
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
        this._doSecuritySchemeRename(document, this._oldSchemeName, this._newSchemeName);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenameSecuritySchemeCommand] Reverting.");
        this._doSecuritySchemeRename(document, this._newSchemeName, this._oldSchemeName);
    }

    /**
     * Does the work of renaming a path from one name to another.
     * @param document
     * @param from
     * @param to
     * @private
     */
    private void _doSecuritySchemeRename(Document document, String from, String to) {
        final Consumer<SecurityScheme> schemeUpdater = scheme -> {
            scheme.rename(to);
            
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
        };
        
        // Different place to find the security scheme depending on the version.
        if (document.getDocumentType() == DocumentType.openapi2) {
            Oas20Document doc20 = (Oas20Document) document;
            
            if (ModelUtils.isDefined(doc20.securityDefinitions)) {
                doc20.securityDefinitions.renameSecurityScheme(from, to, schemeUpdater);
            }
        } else if (document.getDocumentType() == DocumentType.openapi3) {
            Oas30Document doc30 = (Oas30Document) document;
            if (ModelUtils.isDefined(doc30.components)) {
                doc30.components.renameSecurityScheme(from, to, schemeUpdater);
            }
        } else {
            Aai20Document aai20Document = (Aai20Document) document;
            if (ModelUtils.isDefined(aai20Document.components)) {
                aai20Document.components.renameSecurityScheme(from, to, schemeUpdater);
            }
        }
    }
}
