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

package io.apicurio.datamodels.cmd;

import io.apicurio.datamodels.core.models.Document;

/**
 * All editor commands must implement this interface.
 * @author eric.wittmann@gmail.com
 */
public interface ICommand {

    /**
     * Called to execute the command against the given document.
     * @param document
     */
    public void execute(Document document);

    /**
     * Called to undo the command (restore the document to a previous state).
     * @param document
     */
    public void undo(Document document);

    /**
     * Marshall the command into a JS object.
     */
    public Object marshall();

    /**
     * Unmarshall the JS object.
     * @param from
     */
    public void unmarshall(Object from);

}
