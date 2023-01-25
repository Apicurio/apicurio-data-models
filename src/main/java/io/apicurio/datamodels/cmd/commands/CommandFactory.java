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

import io.apicurio.datamodels.cmd.ICommand;

/**
 * This factory is used to create an instance of {@link ICommand} given a "type" that was previously
 * serialized as part of the marshalling of a command to a JS object (and ultimately a JSON string).
 * The type of command is typically its short classname, but not always.
 * @author eric.wittmann@gmail.com
 */
public class CommandFactory {

    /**
     * Called to create a command of a given type.
     * @param cmdType
     */
    public static ICommand create(String cmdType) {
        switch (cmdType) {

            case "AggregateCommand":
            { return new AggregateCommand(); }

            case "ChangeDescriptionCommand_20":
            case "ChangeDescriptionCommand_30":
            case "ChangeDescriptionCommand":
            { return new ChangeDescriptionCommand(); }

        }
        return null;
    }

    public static final ICommand createAggregateCommand(String name, Object info, List<ICommand> commands) {
        return new AggregateCommand(name, info, commands);
    }


}
