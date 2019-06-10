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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat.CommandListDeserializer;
import io.apicurio.datamodels.compat.MarshallCompat.CommandListSerializer;
import io.apicurio.datamodels.compat.MarshallCompat.NullableJsonNodeDeserializer;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to aggregate an array of other commands into a single logical command.  This is used
 * for example to make multiple changes as a single "undoable" change.
 * @author eric.wittmann@gmail.com
 */
public class AggregateCommand extends AbstractCommand {

    public String name;
    @JsonDeserialize(using=NullableJsonNodeDeserializer.class)
    public Object info;
    @JsonSerialize(using=CommandListSerializer.class)
    @JsonDeserialize(using=CommandListDeserializer.class)
    public List<ICommand> _commands;

    AggregateCommand() {
    }
    
    AggregateCommand(String name, Object info, List<ICommand> commands) {
        this.name = name;
        this.info = info;
        this._commands = commands;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[AggregateCommand] Executing %d child commands.", this._commands.size());
        this._commands.forEach( command -> {
            command.execute(document);
        });
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[AggregateCommand] Reverting %d child commands.", this._commands.size());

        for (int idx = this._commands.size() - 1; idx >= 0; idx--) {
            ICommand command = this._commands.get(idx);
            command.undo(document);
        }
    }
    
}
