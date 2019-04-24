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

import {Document} from "../src/io/apicurio/datamodels/core/models/Document";
import {Library} from "../src/io/apicurio/datamodels/Library";
import {ValidationProblem} from "../src/io/apicurio/datamodels/core/models/ValidationProblem";
import {readText} from "./util/readText";
import {ValidationProblemSeverity} from "../src/io/apicurio/datamodels/core/models/ValidationProblemSeverity";


export interface TestSpec {
    name: string;
    test: string;
}

function formatSeverity(severity: ValidationProblemSeverity): string {
    if (severity == ValidationProblemSeverity.ignore) {
        return "ignore";
    } else if (severity == ValidationProblemSeverity.low) {
        return "low";
    } else if (severity == ValidationProblemSeverity.medium) {
        return "medium";
    } else if (severity == ValidationProblemSeverity.high) {
        return "high";
    }
}

function formatProblems(problems: ValidationProblem[]): string {
    let rval: string = "";
    problems.sort((p1, p2) => {
        return p1.errorCode.localeCompare(p2.errorCode);
    }).forEach(problem => {
        rval += (
            "[" + problem.nodePath.toString() + "]::" +
            problem.errorCode + "::" +
            formatSeverity(problem.severity) + "::" +
            problem.property + "::" +
            problem.message + "\n"
        );
    });
    return rval;
}

function normalize(value: string): string {
    return value.trim().replace("\r\n", "\n");
}

/**
 * Full I/O Tests for the AsyncAPI library.
 */
describe("Validation", () => {

    let TESTS: TestSpec[] = readJSON("tests/fixtures/validation/tests.json");

    // All tests in the list above.
    TESTS.forEach( spec => {
        it(spec.name, () => {
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
            let expected: string = normalize(readText(testPath + ".expected"));
            expect(expected).not.toBeNull();
            expect(expected).toEqual(actual);
        });
    });

});

