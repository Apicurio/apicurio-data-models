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

import {Document} from "../../src/io/apicurio/datamodels/core/models/Document";
import {Library} from "../../src/io/apicurio/datamodels/Library";
import {ValidationProblem} from "../../src/io/apicurio/datamodels/core/models/ValidationProblem";
import {ValidationProblemSeverity} from "../../src/io/apicurio/datamodels/core/models/ValidationProblemSeverity";

var fs = require("fs");

export function readJSON(path: string): any {
    let fileContent: string = fs.readFileSync(path);
    return JSON.parse(fileContent);
}

export function readTXT(path: string): string {
    let fileContent: string = fs.readFileSync(path, "utf8");
    if (typeof fileContent == "string") {
        return fileContent;
    }
    console.warn("WARNING: Failed to read text file: " + path);
    return "";
}


export function formatSeverity(severity: ValidationProblemSeverity): string {
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

export function formatProblems(problems: ValidationProblem[]): string {
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

export function normalize(value: string): string {
    return value.trim().replace("\r\n", "\n");
}
