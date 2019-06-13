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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.SecurityScheme;

/**
 * A command used to modify a security scheme.
 * @author eric.wittmann@gmail.com
 */
public abstract class ChangeSecuritySchemeCommand extends AbstractCommand {

    public String _schemeName;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _schemeObj;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldScheme;

    ChangeSecuritySchemeCommand() {
    }

    ChangeSecuritySchemeCommand(SecurityScheme scheme) {
        this._schemeName = scheme.getName();
        this._schemeObj = Library.writeNode(scheme);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeSecuritySchemeCommand] Executing.");
        this._oldScheme  = null;

        SecurityScheme scheme = this.getSchemeFromDocument(document);
        if (this.isNullOrUndefined(scheme)) {
            return;
        }

        // Back up the old scheme info (for undo)
        this._oldScheme = Library.writeNode(scheme);

        // Replace with new scheme info
        this.replaceSchemeWith(scheme, this._schemeObj);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeSecuritySchemeCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldScheme)) {
            return;
        }

        SecurityScheme scheme = this.getSchemeFromDocument(document);
        if (this.isNullOrUndefined(scheme)) {
            return;
        }

        this.nullScheme(scheme);
        Library.readNode(this._oldScheme, scheme);
    }

    /**
     * Gets the scheme from the document.
     */
    protected abstract SecurityScheme getSchemeFromDocument(Document document);

    /**
     * Replaces the content of a scheme with the content from another scheme.
     * @param toScheme
     * @param fromScheme
     */
    protected void replaceSchemeWith(SecurityScheme toScheme, Object fromScheme) {
        this.nullScheme(toScheme);
        Library.readNode(fromScheme, toScheme);
    }

    /**
     * Null out all values in the given scheme.
     * @param scheme
     */
    protected void nullScheme(SecurityScheme scheme) {
        scheme.description = null;
        scheme.type = null;
        scheme.name = null;
        scheme.in = null;
    }

}
