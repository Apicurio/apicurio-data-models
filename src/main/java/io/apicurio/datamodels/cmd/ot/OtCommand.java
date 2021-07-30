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

package io.apicurio.datamodels.cmd.ot;

import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.core.models.Document;

/**
 * @author eric.wittmann@gmail.com
 */
public class OtCommand {

    public String author;
    public long contentVersion;
    public ICommand command;
    public boolean local;
    public boolean reverted;

    /**
     * Executes the command against the given document.  Skips execution if the command
     * has been reverted/undone.
     * @param document
     */
    public void execute(Document document) {
        if (!this.reverted) {
            this.command.execute(document);
        }
    }

    /**
     * Invokes 'undo' on the underlying ICommand but only if it hasn't already been reverted.
     * Any command already reverted will simply be skipped.
     * @param document
     */
    public void undo(Document document) {
        if (this.reverted) {
            //console.info("Skipped undo of CV: ", this);
        } else {
            this.command.undo(document);
            this.reverted = true;
        }
    }

    /**
     * Invokes 'redo' on the underlying ICommand but only if it hasn't already been reverted.
     * Any command already reverted will simply be skipped.
     * @param document
     */
    public void redo(Document document) {
        if (!this.reverted) {
            //console.info("Skipped redo of CV: ", this);
        } else {
            this.command.execute(document);
            this.reverted = false;
        }
    }

}
