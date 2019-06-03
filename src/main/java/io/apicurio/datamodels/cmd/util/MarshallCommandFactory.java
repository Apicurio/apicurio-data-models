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

package io.apicurio.datamodels.cmd.util;

import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.cmd.commands.ChangeDescriptionCommand;
import io.apicurio.datamodels.cmd.commands.ChangePropertyCommand;
import io.apicurio.datamodels.cmd.commands.ChangeTitleCommand;
import io.apicurio.datamodels.cmd.commands.ChangeVersionCommand;

/**
 * This factory is used to create an instance of {@link ICommand} given a "type" that was previously
 * serialized as part of the marshalling of a command to a JS object (and ultimately a JSON string).
 * The type of command is typically its short classname, but not always.
 * @author eric.wittmann@gmail.com
 */
public class MarshallCommandFactory {

    /**
     * Called to create a command of a given type.
     * @param cmdType
     */
    public static ICommand create(String cmdType) {
        switch (cmdType) {
            case "ChangePropertyCommand_20": 
            case "ChangePropertyCommand_30":
            case "ChangePropertyCommand":
            { return ChangePropertyCommand.create(null, null, null); }
            case "ChangeDescriptionCommand_20": 
            case "ChangeDescriptionCommand_30":
            case "ChangeDescriptionCommand":
            { return ChangeDescriptionCommand.create(null); }
            case "ChangeTitleCommand_20": 
            case "ChangeTitleCommand_30":
            case "ChangeTitleCommand":
            { return ChangeTitleCommand.create(null); }
            case "ChangeVersionCommand_20": 
            case "ChangeVersionCommand_30":
            case "ChangeVersionCommand":
            { return ChangeVersionCommand.create(null); }
        }
        return null;
    }

}
