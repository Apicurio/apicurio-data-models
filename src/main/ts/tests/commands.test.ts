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
import {MarshallUtils} from "../src/io/apicurio/datamodels/cmd/util/MarshallUtils";
import {OtCommand} from "../src/io/apicurio/datamodels/cmd/ot/OtCommand";
import {OtEngine} from "../src/io/apicurio/datamodels/cmd/ot/OtEngine";
import {Library} from "../src/io/apicurio/datamodels/Library";
import {readJSON} from "./util/tutils";


interface TestSpec {
    name: string;
    test: string;
    commands?: string;
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

        // Load all of the commands
        let commands: OtCommand[] = [];
        let cjs: any[] = commandsJs;
        cjs.forEach((cnode, cidx) => {
            let cmd: ICommand = MarshallUtils.unmarshallCommand(cnode);
            let otc: OtCommand = new OtCommand();
            otc.author = "user";
            otc.command = cmd;
            otc.contentVersion = cidx;
            otc.local = false;
            otc.reverted = false;
            if (cnode["__contentVersion"]) {
                otc.contentVersion = cnode["__contentVersion"];
            }
            commands.push(otc);
        });

        // Apply all the commands to the Document (modifying the document).
        let engine: OtEngine = new OtEngine(document);
        commands.forEach(cmd => {
            engine.executeCommand(cmd, false);
        });

        // Check that the resulting (modified) document is what we expected.
        let actualDoc: Document = engine.getCurrentDocument();
        let actual: any = Library.writeNode(actualDoc);
        let expected: any = afterJs;
        
        expect(actual).toEqual(expected);
    });
});
