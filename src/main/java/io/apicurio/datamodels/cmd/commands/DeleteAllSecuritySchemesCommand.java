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
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityScheme;

/**
 * A command used to delete all security schemes from a document or operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllSecuritySchemesCommand extends AbstractCommand {

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Object> _oldSecuritySchemes;
    
    DeleteAllSecuritySchemesCommand() {
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllSecuritySchemesCommand] Executing.");
        this._oldSecuritySchemes = new ArrayList<>();

        // Logic for a 2.0 doc
        if (document.getDocumentType() == DocumentType.openapi2) {
            Oas20Document doc = (Oas20Document) document;
            if (!this.isNullOrUndefined(doc.securityDefinitions)) {
                doc.securityDefinitions.getSecuritySchemes().forEach(scheme -> {
                    Object savedScheme = Library.writeNode(scheme);
                    JsonCompat.setPropertyString(savedScheme, "__name", scheme.getSchemeName());
                    this._oldSecuritySchemes.add(savedScheme);
                });
            }
            doc.securityDefinitions = null;
        }

        // Logic for a 3.0 doc
        if (document.getDocumentType() == DocumentType.openapi3) {
            Oas30Document doc = (Oas30Document) document;
            if (!this.isNullOrUndefined(doc.components)) {
                doc.components.getSecuritySchemes().forEach( scheme -> {
                    Object savedScheme = Library.writeNode(scheme);
                    JsonCompat.setPropertyString(savedScheme, "__name", scheme.getSchemeName());
                    this._oldSecuritySchemes.add(savedScheme);
                    doc.components.removeSecurityScheme(scheme.getSchemeName());
                });
            }
        }
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllSecuritySchemesCommand] Reverting.");
        if (this.isNullOrUndefined(_oldSecuritySchemes) || this._oldSecuritySchemes.size() == 0) {
            return;
        }

        // Logic for a 2.0 doc
        if (document.getDocumentType() == DocumentType.openapi2) {
            Oas20Document doc = (Oas20Document) document;
            if (this.isNullOrUndefined(doc.securityDefinitions)) {
                doc.securityDefinitions = doc.createSecurityDefinitions();
            }
            this._oldSecuritySchemes.forEach( savedScheme -> {
                String name = JsonCompat.consumePropertyString(savedScheme, "__name");
                Oas20SecurityScheme scheme = doc.securityDefinitions.createSecurityScheme(name);
                Library.readNode(savedScheme, scheme);
                doc.securityDefinitions.addSecurityScheme(name, scheme);
            });
        }

        // Logic for a 3.0 doc
        if (document.getDocumentType() == DocumentType.openapi3) {
            Oas30Document doc = (Oas30Document) document;
            if (this.isNullOrUndefined(doc.components)) {
                doc.components = doc.createComponents();
            }
            this._oldSecuritySchemes.forEach( savedScheme -> {
                String name = JsonCompat.consumePropertyString(savedScheme, "__name");
                Oas30SecurityScheme scheme = doc.components.createSecurityScheme(name);
                Library.readNode(savedScheme, scheme);
                doc.components.addSecurityScheme(name, scheme);
            });
        }
    }
}
