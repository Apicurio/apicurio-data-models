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

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to delete a security scheme.
 * @author eric.wittmann@gmail.com
 */
public abstract class DeleteSecuritySchemeCommand extends AbstractCommand {

    public String _schemeName;

    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object _oldScheme;
    
    DeleteSecuritySchemeCommand() {
    }
    
    DeleteSecuritySchemeCommand(String schemeName) {
        this._schemeName = schemeName;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteSecuritySchemeCommand] Executing.");
        this._oldScheme = null;

        this._oldScheme = this.doDeleteScheme(document);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteSecuritySchemeCommand] Reverting.");
        if (ModelUtils.isDefined(this._oldScheme)) {
            this.doRestoreScheme(document, this._oldScheme);
        }
    }

    /**
     * Deletes the scheme.
     * @param document
     */
    protected abstract Object doDeleteScheme(Document document);

    /**
     * Restores the previously deleted scheme.
     * @param document
     * @param oldScheme
     */
    protected abstract void doRestoreScheme(Document document, Object oldScheme);
    
}
