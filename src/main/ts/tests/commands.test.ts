/**
 * @license
 * Copyright 2019 JBoss Inc
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

import {Document} from "../src/io/apicurio/datamodels/core/models/Document";
import {ICommand} from "../src/io/apicurio/datamodels/cmd/ICommand";
import {MarshallCompat} from "../src/io/apicurio/datamodels/compat/MarshallCompat";
import {OtCommand} from "../src/io/apicurio/datamodels/cmd/ot/OtCommand";
import {OtEngine} from "../src/io/apicurio/datamodels/cmd/ot/OtEngine";
import {Library} from "../src/io/apicurio/datamodels/Library";
import {readJSON} from "./util/tutils";


interface TestSpec {
    name: string;
    test: string;
    commands?: string;
}


enum TestDirectiveType {
    command, finalize, undo, redo, undoLast, redoLast
}
class TestDirective {
    public dtype: TestDirectiveType;
}
class TestDirectiveCommand extends TestDirective {
    public command: ICommand;
    public pending: boolean;
    public contentVersion: number;
    constructor() {
        super();
        this.dtype = TestDirectiveType.command;
    }
}
class TestDirectiveFinalize extends TestDirective {
    public pendingVersion: number;
    public contentVersion: number;
    constructor() {
        super();
        this.dtype = TestDirectiveType.finalize;
    }
}
class TestDirectiveUndoRedo extends TestDirective {
    public contentVersion: number;
}


let allTests: TestSpec[] = readJSON("tests/fixtures/cmd/tests.json");
allTests.forEach(spec => {
    test(spec.name, () => {
        console.info("------------------------");

        let beforePath: string = "tests/fixtures/cmd/" + spec.test + ".before.json";
        let afterPath: string = "tests/fixtures/cmd/" + spec.test + ".after.json";
        let commandsPath: string = "tests/fixtures/cmd/" + spec.test + ".commands.json";

        if (spec.commands) {
            commandsPath = "tests/fixtures/cmd/" + spec.test + spec.commands;
        }

        let beforeJs: any = readJSON(beforePath);
        let afterJs: any = readJSON(afterPath);
        let commandsJs: any = readJSON(commandsPath);

        expect(beforeJs).not.toBeNull();
        expect(afterJs).not.toBeNull();
        expect(commandsJs).not.toBeNull();
        
        // Parse/read the document
        let document: Document = Library.readDocument(beforeJs);
        expect(document).not.toBeNull();

        // Load all the commands to apply.
        let directives: TestDirective[] = [];
        let cjs: any[] = commandsJs;
        cjs.forEach((cnode, cidx) => {
            let directiveNode: any = cnode;
            let type: string = directiveNode["__type"];
            if ("_FINALIZE_" == type) {
                let directive: TestDirectiveFinalize = new TestDirectiveFinalize();
                directive.contentVersion = directiveNode["__contentVersion"];
                directive.pendingVersion = directiveNode["__pendingVersion"];
                directives.push(directive);
            } else if ("_UNDO_" == type) {
                let directive: TestDirectiveUndoRedo = new TestDirectiveUndoRedo();
                directive.dtype = TestDirectiveType.undo;
                directive.contentVersion = directiveNode["__contentVersion"];
                directives.push(directive);
            } else if ("_REDO_" == type) {
                let directive: TestDirectiveUndoRedo = new TestDirectiveUndoRedo();
                directive.dtype = TestDirectiveType.redo;
                directive.contentVersion = directiveNode["__contentVersion"];
                directives.push(directive);
            } else if ("_UNDO_LAST_" == type) {
                let directive: TestDirectiveUndoRedo = new TestDirectiveUndoRedo();
                directive.dtype = TestDirectiveType.undoLast;
                directives.push(directive);
            } else if ("_REDO_LAST_" == type) {
                let directive: TestDirectiveUndoRedo = new TestDirectiveUndoRedo();
                directive.dtype = TestDirectiveType.redoLast;
                directives.push(directive);
            } else {
                let directive: TestDirectiveCommand = new TestDirectiveCommand();
                directive.contentVersion = cidx;

                if (directiveNode["__contentVersion"]) {
                    directive.contentVersion = directiveNode["__contentVersion"];
                }
                if (directiveNode["__pending"]) {
                    directive.pending = directiveNode["__pending"];
                }

                // Load the command
                let command: ICommand = MarshallCompat.unmarshallCommand(directiveNode);

                // Make sure we can marshall/unmarshall the command fully
                let marshalledCommand: any = MarshallCompat.marshallCommand(command);
                let unmarshalledCommand: ICommand = MarshallCompat.unmarshallCommand(marshalledCommand);
                
                directive.command = unmarshalledCommand;
                directives.push(directive);
            }
        });

        // Apply all the commands to the Document (modifying the document).
        let engine: OtEngine = new OtEngine(document);
        directives.forEach(directive => {
            if (directive.dtype == TestDirectiveType.command) {
                let dcmd: TestDirectiveCommand = <TestDirectiveCommand>directive;
                let cmd: OtCommand = new OtCommand();
                cmd.author = "user";
                cmd.command = dcmd.command;
                cmd.contentVersion = dcmd.contentVersion;
                // Command is local if it's pending, or if there is only 1
                cmd.local = dcmd.pending || directives.length == 1;
                cmd.reverted = false;
                engine.executeCommand(cmd, dcmd.pending);
            } else if (directive.dtype == TestDirectiveType.finalize) {
                let dcmd: TestDirectiveFinalize = <TestDirectiveFinalize>directive;
                engine.finalizeCommand(dcmd.pendingVersion, dcmd.contentVersion);
            } else if (directive.dtype == TestDirectiveType.undo) {
                let dcmd: TestDirectiveUndoRedo = <TestDirectiveUndoRedo>directive;
                engine.undo(dcmd.contentVersion);
            } else if (directive.dtype == TestDirectiveType.redo) {
                let dcmd: TestDirectiveUndoRedo = <TestDirectiveUndoRedo>directive;
                engine.redo(dcmd.contentVersion);
            } else if (directive.dtype == TestDirectiveType.undoLast) {
                engine.undoLastLocalCommand();
            } else if (directive.dtype == TestDirectiveType.redoLast) {
                engine.redoLastLocalCommand();
            }
        });

        // Check that the resulting (modified) document is what we expected.
        let actualDoc: Document = engine.getCurrentDocument();
        let actual: any = Library.writeNode(actualDoc);
        let expected: any = afterJs;
        
        expect(actual).toEqual(expected);

        // If there was only ONE command, then undo it and make sure
        // that results in the original document.
        if (directives.length == 1) {
            engine.undoLastLocalCommand();

            actualDoc = engine.getCurrentDocument();
            actual = Library.writeNode(actualDoc);
            expected = beforeJs;
            
            expect(actual).toEqual(expected);
        }
    });
});
