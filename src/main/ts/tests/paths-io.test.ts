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


export interface TestSpec {
    name: string;
    path: string;
    segments: string[];
}

/**
 * Full I/O Tests for the AsyncAPI library.
 */
describe("Node Paths", () => {

    let TESTS: TestSpec[] = readJSON("tests/fixtures/paths/io-tests.json");

    // All tests in the list above.
    TESTS.forEach( spec => {
        it(spec.name, () => {
            let nodePath: NodePath = new NodePath(spec.path);
            let actual: string = nodePath.toString();
            let expected: string = spec.path;
            expect(expected).toEqual(actual);
            let actualSegments: string[] = nodePath.toSegments();
            let expectedSegments: string[] = spec.segments;
            expect(expectedSegments).toEqual(actualSegments);
        });
    });

});

