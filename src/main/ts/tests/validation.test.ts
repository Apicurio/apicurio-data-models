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
import {Library} from "../src/io/apicurio/datamodels/Library";
import {ValidationProblem} from "../src/io/apicurio/datamodels/core/models/ValidationProblem";
import {ValidationProblemSeverity} from "../src/io/apicurio/datamodels/core/models/ValidationProblemSeverity";
import {readJSON} from "./util/tutils";
import {readTXT} from "./util/tutils";
import {normalize} from "./util/tutils";
import {formatProblems} from "./util/tutils";


interface TestSpec {
    name: string;
    test: string;
}


let allTests: TestSpec[] = readJSON("tests/fixtures/validation/tests.json");
allTests.forEach(spec => {
    test(spec.name, () => {
        // Read the source JSON file
        let testPath: string = "tests/fixtures/validation/" + spec.test;
        let json: any = readJSON(testPath);
        expect(json).not.toBeNull();
        
        // Parse/read the document
        let document: Document = Library.readDocument(json);

        // Validate the document
        let problems: ValidationProblem[] = Library.validate(document, null);
        
        // Sort and format the list of problems into a string for comparison with the expected value
        let actual: string = normalize(formatProblems(problems));

        // Load the expected result and compare with actual
        let expected: string = normalize(readTXT(testPath + ".expected"));
        expect(expected).not.toBeNull();
        expect(actual).toEqual(expected);
    });
});
