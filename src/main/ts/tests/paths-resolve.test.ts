///<reference path="../node_modules/@types/jasmine/index.d.ts"/>
///<reference path="@types/karma-read-json/index.d.ts"/>

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

import {Library} from "../src/io/apicurio/datamodels/Library";
import {NodePath} from "../src/io/apicurio/datamodels/core/models/NodePath";
import {Document} from "../src/io/apicurio/datamodels/core/models/Document";
import {Node} from "../src/io/apicurio/datamodels/core/models/Node";


export interface TestSpec {
    name: string;
    test: string;
    path: string;
}

/**
 * Full I/O Tests for the AsyncAPI library.
 */
describe("Node Path Resolution", () => {

    let TESTS: TestSpec[] = readJSON("tests/fixtures/paths/resolve-tests.json");

    // All tests in the list above.
    TESTS.forEach( spec => {
        it(spec.name, () => {
            // Read the original/source file JSON
            let testPath: string = "tests/fixtures/paths/" + spec.test;
            let json: any = readJSON(testPath);
            expect(json).not.toBeNull();

            // Parse/read the document
            let document: Document = Library.readDocument(json);
            expect(document).not.toBeNull();

            // Parse the test path
            let np: NodePath = new NodePath(spec.path);

            // Resolve the path to a node in the source
            let resolvedNode: Node = np.resolve(document, null);
            expect(resolvedNode).not.toBeNull();

            // Compare source path to node path (test generating a node path from a node)
            let createdPath: NodePath = Library.createNodePath(resolvedNode);
            let expectedPath: string = spec.path;
            let actualPath: string = createdPath.toString();
            expect(actualPath).toEqual(expectedPath);

            // Verify that the resolved node is what we expected it to be
            let actual: any = Library.writeNode(resolvedNode);
            let expectedCP: string = "tests/fixtures/paths/" + spec.test + ".expected.json";
            let expected: any = readJSON(expectedCP);
            expect(expected).not.toBeNull();
            expect(expected).toEqual(actual);
        });
    });

});

