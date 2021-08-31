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

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;

/**
 * A command used to delete all examples from a parameter.
 * @author laurent.broudoux@gmail.com
 */
public class DeleteAllParameterExamplesCommand extends AbstractCommand {

    public NodePath _parameterPath;

    @JsonDeserialize(contentUsing=NullableJsonNodeDeserializer.class)
    public Map<String, Object> _oldExamples;

    DeleteAllParameterExamplesCommand() {
    }

    DeleteAllParameterExamplesCommand(Oas30Parameter parameter) {
        this._parameterPath = Library.createNodePath(parameter);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[DeleteAllParameterExamplesCommand] Executing.");

        if (this.isNullOrUndefined(document)) {
            LoggerCompat.debug("[DeleteAllParameterExamplesCommand] Could not execute the command, invalid argument.");
            return;
        }

        if (this.isNullOrUndefined(this._parameterPath)) {
            LoggerCompat.debug("[DeleteAllParameterExamplesCommand] Could not execute the command, problem when unmarshalling.");
            return;
        }

        Oas30Parameter parameter = (Oas30Parameter) this._parameterPath.resolve(document);

        if (this.isNullOrUndefined(parameter)) {
            LoggerCompat.debug("[DeleteAllParameterExamplesCommand] Parameter not found.");
            return;
        }

        this._oldExamples = new HashMap<>();
        parameter.getExamples().forEach(e -> {
            this._oldExamples.put(e.getName(), Library.writeNode((Node) e));
        });
        parameter.clearExamples();
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        if (this.isNullOrUndefined(document)) {
            LoggerCompat.debug("[DeleteAllParameterExamplesCommand] Could not revert the command, invalid argument.");
            return;
        }

        if (this.isNullOrUndefined(this._parameterPath)) {
            LoggerCompat.debug("[DeleteAllParameterExamplesCommand] Could not revert the command, problem when unmarshalling.");
            return;
        }

        LoggerCompat.info("[DeleteAllParameterExamplesCommand] Reverting.");

        Oas30Parameter parameter = (Oas30Parameter) this._parameterPath.resolve(document);
        if (this.isNullOrUndefined(parameter)) {
            LoggerCompat.info("[DeleteAllParameterExamplesCommand] No parameter found.");
            return;
        }

        if (this.isNullOrUndefined(this._oldExamples)) {
            LoggerCompat.info("[DeleteAllExamplesCommand] Could not revert. Previous data is not available.");
            return;
        }

        for (String k : this._oldExamples.keySet()) {
            Oas30Example example = parameter.createExample(k);
            Library.readNode(this._oldExamples.get(k), example);
            parameter.addExample(example);
        }
    }

}
