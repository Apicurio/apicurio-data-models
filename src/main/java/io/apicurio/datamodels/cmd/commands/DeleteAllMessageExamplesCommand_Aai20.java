/*
 * Copyright 2021 Red Hat
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
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * A command used to delete all examples from an AsyncAPI message.
 * @author laurent.broudoux@gmail.com
 */
public class DeleteAllMessageExamplesCommand_Aai20 extends AbstractCommand {

    public NodePath _parentPath;

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public List<Map<String, Object>> _oldExamples;

    DeleteAllMessageExamplesCommand_Aai20() {
    }

    DeleteAllMessageExamplesCommand_Aai20(AaiOperation operationNode) {
        this._parentPath = Library.createNodePath(operationNode);
    }
    
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllMessageExamplesCommand_Aai20] Executing.");

        AaiOperation operation = (AaiOperation) this._parentPath.resolve(document);

        if (this.isNullOrUndefined(operation) || this.isNullOrUndefined(operation.message)) {
            return;
        }

        this._oldExamples = operation.message.examples;
        operation.message.examples = null;
    }
    
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[DeleteAllMessageExamplesCommand_Aai20] Reverting.");

        AaiOperation operation = (AaiOperation) this._parentPath.resolve(document);

        if (this._oldExamples == null || this.isNullOrUndefined(operation) || this.isNullOrUndefined(operation.message)) {
            return;
        }

        // Just redstore previous ones.
        operation.message.examples = this._oldExamples;
    }
}
