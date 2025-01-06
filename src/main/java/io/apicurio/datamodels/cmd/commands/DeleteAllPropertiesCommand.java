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

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A command used to delete all properties from a schema.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllPropertiesCommand extends AbstractCommand {

    public NodePath _schemaPath;

    public Map<String, ObjectNode> _oldProperties;
    public List<String> _oldRequired;

    public DeleteAllPropertiesCommand() {
    }

    public DeleteAllPropertiesCommand(Schema schema) {
        this._schemaPath = Library.createNodePath(schema);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllPropertiesCommand] Executing.");
        this._oldProperties = new LinkedHashMap<>();
        this._oldRequired = new ArrayList<>();

        Schema schema = (Schema) NodePathUtil.resolveNodePath(this._schemaPath, document);

        if (this.isNullOrUndefined(schema)) {
            return;
        }

        List<String> propertyNames = new ArrayList<>();
        propertyNames.addAll(schema.getProperties().keySet());
        propertyNames.forEach(pname -> {
            Schema pvalue = schema.getProperties().get(pname);
            this._oldProperties.put(pname, Library.writeNode(pvalue));
            if (isRequired(schema, pname)) {
                this._oldRequired.add(pname);
            }
            schema.removeProperty(pname);
            removeRequired(schema, pname);
        });
        if (schema.getRequired() != null && schema.getRequired().isEmpty()) {
            schema.setRequired(null);
        }
    }

    private void removeRequired(Schema schema, String pname) {
        if (schema.getRequired() != null) {
            schema.getRequired().remove(pname);
        }
    }

    private boolean isRequired(Schema schema, String pname) {
        return schema.getRequired() != null && schema.getRequired().contains(pname);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteAllPropertiesCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldProperties) || this._oldProperties.isEmpty()) {
            return;
        }

        Schema schema = (Schema) NodePathUtil.resolveNodePath(this._schemaPath, document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        this._oldProperties.keySet().forEach( pname -> {
            Schema pschema = schema.createSchema();
            Library.readNode(this._oldProperties.get(pname), pschema);
            schema.addProperty(pname, pschema);
            if (this._oldRequired.contains(pname)) {
                addRequired(schema, pname);
            }
        });
    }

    private void addRequired(Schema schema, String pname) {
        if (schema.getRequired() == null) {
            schema.setRequired(new ArrayList<>());
        }
        if (!schema.getRequired().contains(pname)) {
            schema.getRequired().add(pname);
        }
    }


}
