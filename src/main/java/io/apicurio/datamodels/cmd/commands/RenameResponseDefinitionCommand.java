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

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.models.OasResponse;

/**
 * A command used to rename a response definition, along with all references to it.
 * @author eric.wittmann@gmail.com
 */
public abstract class RenameResponseDefinitionCommand extends AbstractCommand {

    public String _oldName;
    public String _newName;
    public List<NodePath> _references;
    
    RenameResponseDefinitionCommand() {
    }
    
    RenameResponseDefinitionCommand(String oldName, String newName) {
        this._oldName = oldName;
        this._newName = newName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[RenameResponseDefinitionCommand] Executing.");
        this._references = new ArrayList<>();
        if (this._renameResponseDefinition((OasDocument) document, this._oldName, this._newName)) {
            String oldRef = this._nameToReference(this._oldName);
            String newRef = this._nameToReference(this._newName);
            ResponseRefFinder responseFinder = new ResponseRefFinder(oldRef);
            List<OasResponse> responses = responseFinder.findIn(document);
            for (OasResponse response : responses) {
                this._references.add(Library.createNodePath(response));
                response.$ref = newRef;
            }
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenameResponseDefinitionCommand] Reverting.");
        if (this._renameResponseDefinition((OasDocument) document, this._newName, this._oldName)) {
            String oldRef = this._nameToReference(this._oldName);
            if (ModelUtils.isDefined(this._references)) {
                this._references.forEach( ref -> {
                    OasResponse response = (OasResponse) ref.resolve(document);
                    response.$ref = oldRef;
                });
            }
        }
    }
    
    /**
     * Convert a simple name to a reference.  This will be different for 2.0 vs. 3.0
     * data models.
     */
    protected abstract String _nameToReference(String name);

    /**
     * Called to actually change the name of the response definition.  This impl will vary
     * depending on the OAI data model version.  Returns true if the rename actually happened.
     */
    protected abstract boolean _renameResponseDefinition(OasDocument document, String fromName, String toName);
    
    private static class ResponseRefFinder extends CombinedVisitorAdapter {

        private String _reference;
        private List<OasResponse> _responses = new ArrayList<>();

        /**
         * Constructor.
         */
        public ResponseRefFinder(String reference) {
            this._reference = reference;
        }

        public List<OasResponse> findIn(Document document) {
            VisitorUtil.visitTree(document, this, TraverserDirection.down);
            return this._responses;
        }

        protected boolean _accept(OasResponse response) {
            return ModelUtils.isDefined(response.$ref) && NodeCompat.equals(response.$ref, this._reference);
        }

        /**
         * @see io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter#visitResponse(io.apicurio.datamodels.openapi.models.OasResponse)
         */
        @Override
        public void visitResponse(OasResponse node) {
            if (this._accept(node)) {
                this._responses.add(node);
            }
        }

    }

}
