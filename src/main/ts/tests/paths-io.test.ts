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
import {readJSON} from "./util/tutils";


interface TestSpec {
    name: string;
    path: string;
    segments: string[];
}


let allTests: TestSpec[] = readJSON("tests/fixtures/paths/io-tests.json");
allTests.forEach(spec => {
    test(spec.name, () => {
        let nodePath: NodePath = new NodePath(spec.path);
        let actual: string = nodePath.toString();
        let expected: string = spec.path;
        expect(actual).toEqual(expected);
        let actualSegments: string[] = nodePath.toSegments();
        let expectedSegments: string[] = spec.segments;
        expect(actualSegments).toEqual(expectedSegments);
    });
});
