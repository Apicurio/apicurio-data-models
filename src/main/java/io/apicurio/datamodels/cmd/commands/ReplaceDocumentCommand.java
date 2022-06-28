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

import java.util.Collection;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;

/**
 * A command used to replace a path item with a newer version.
 * @author eric.wittmann@gmail.com
 */
public class ReplaceDocumentCommand extends AbstractCommand {
    
    public NodePath _nodePath;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _new;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _old;
    
    ReplaceDocumentCommand() {
    }
    
    ReplaceDocumentCommand(Document old, Document replacement) {
        this._nodePath = Library.createNodePath(old);
        this._new = Library.writeNode(replacement);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ReplaceDocumentCommand] Executing.");
        this._old = null;

        Document oldNode = (Document) this._nodePath.resolve(document);
        if (this.isNullOrUndefined(oldNode)) {
            return;
        }

        this._old = Library.writeNode(oldNode);
        this.removeNode(document, oldNode);
        this.readNode(document, this._new);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ReplaceDocumentCommand] Reverting.");
        if (this.isNullOrUndefined(this._old)) {
            return;
        }

        Document node = (Document) this._nodePath.resolve(document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        this.removeNode(document, node);
        this.readNode(document, this._old);
    }
    
    private void removeNode(Document doc, Document node) {
        switch (node.getDocumentType()) {
            case asyncapi2:
                this.resetAai2xDocument((Aai20Document) node);
                break;
            case openapi2:
                this.resetOas2xDocument((Oas20Document) node);
                break;
            case openapi3:
                this.resetOas3xDocument((Oas30Document) node);
                break;
            default:
                break;
        }
    }
    
    private Document readNode(Document doc, Object node) {
        Library.readNode(node, doc);
        return (Document) doc;
    }

    /**
     * Resets a 2.0 OAI document by nulling out all of its properties.
     * @param doc
     */
    private void resetOas2xDocument(Oas20Document doc) {
        doc.host = null;
        doc.basePath = null;
        doc.schemes= null;
        doc.consumes= null;
        doc.produces= null;
        doc.definitions = null;
        doc.parameters = null;
        doc.responses = null;
        doc.securityDefinitions = null;
        this.resetOasDocument(doc);
    }

    /**
     * Resets a 3.x OAI document by nulling out all of its properties.
     * @param doc
     */
    private void resetOas3xDocument(Oas30Document doc) {
        doc.servers = null;
        doc.components = null;
        this.resetOasDocument(doc);
    }

    /**
     * Resets a 2.0 AAI document by nulling out all of its properties.
     * @param doc
     */
    private void resetAai2xDocument(Aai20Document doc) {
        doc.id = null;
        doc.channels = new LinkedHashMap<>();
        doc.servers = null;
        doc.components = null;
        doc.defaultContentType = null;
        this.resetDocument(doc);
    }

    /**
     * Resets the common properties of an OAS doc.
     * @param doc
     */
    private void resetOasDocument(OasDocument doc) {
        doc.paths = null;
        doc.security = null;
        this.resetDocument(doc);
    }

    /**
     * Resets the common properties.
     * @param doc
     */
    private void resetDocument(Document doc) {
        doc.info = null;
        doc.tags = null;
        doc.externalDocs = null;

        Collection<Extension> extensions = doc.getExtensions();
        if (ModelUtils.isDefined(extensions)) {
            extensions.forEach(ext -> {
                doc.removeExtension(ext.name);
            });
        }
        
        doc.getExtraPropertyNames().forEach(pname -> {
            doc.removeExtraProperty(pname);
        });
    }

}
