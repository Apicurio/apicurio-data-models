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
import {Node} from "../src/io/apicurio/datamodels/core/models/Node";
import {Library} from "../src/io/apicurio/datamodels/Library";
import {ValidationProblem} from "../src/io/apicurio/datamodels/core/models/ValidationProblem";
import {ValidationProblemSeverity} from "../src/io/apicurio/datamodels/core/models/ValidationProblemSeverity";
import {IValidationSeverityRegistry} from "../src/io/apicurio/datamodels/core/validation/IValidationSeverityRegistry";
import {IReferenceResolver} from "../src/io/apicurio/datamodels/core/util/IReferenceResolver";
import {ReferenceUtil} from "../src/io/apicurio/datamodels/core/util/ReferenceUtil";
import {ModelCloner} from "../src/io/apicurio/datamodels/cloning/ModelCloner";
import {readJSON} from "./util/tutils";
import {readTXT} from "./util/tutils";
import {formatProblems} from "./util/tutils";
import {readSeverity} from "./util/tutils";
import { IDocumentValidatorExtension } from "../src/io/apicurio/datamodels/core/validation/IDocumentValidatorExtension";
import { NodePath } from "../src/io/apicurio/datamodels/core/models/NodePath";


interface TestSpec {
    name: string;
    test: string;
    severity?: string;
}

class CustomSeverityRegistry implements IValidationSeverityRegistry {

    constructor(private severity: ValidationProblemSeverity) {}

    public lookupSeverity(): ValidationProblemSeverity {
        return this.severity;
    }

}

class ValidationTestReferenceResolver implements IReferenceResolver {
    
    public resolveRef(reference: string, from: Node): Node {
        if (!reference) {
            return null;
        }
        if (reference.indexOf("test:") != 0) {
            return null;
        }
        
        let colonIdx: number = reference.indexOf(":");
        let hashIdx: number = reference.indexOf("#");
        let resourceName: string = reference.substring(colonIdx + 1, hashIdx);
        if (!resourceName) {
            throw "Invalid resource name from reference: " + reference;
        }
        let fragment: string = reference.substring(hashIdx + 1);
        if (!fragment) {
            throw "Invalid fragment from reference: " + reference;
        }
        let content: any = readJSON("tests/fixtures/validation/shared/" + resourceName);
        if (!resourceName) {
            throw "Failed to load JSON from reference: " + reference;
        }
        let resolvedContent: any = ReferenceUtil.resolveFragmentFromJS(content, fragment);
        if (!resourceName) {
            throw "Failed to resolve content for reference: " + reference;
        }
        return this.toModel(resolvedContent, from);
    }
    
    public toModel(jsonNode: any, from: Node): Node {
        let rval: Node = ModelCloner.createEmptyClone(from);
        return Library.readNode(jsonNode, rval);
    }
    
}
Library.addReferenceResolver(new ValidationTestReferenceResolver());


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
        let severityRegistry: IValidationSeverityRegistry = null;
        if (spec.severity) {
            severityRegistry = new CustomSeverityRegistry(readSeverity(spec.severity));
        }
        let problems: ValidationProblem[] = Library.validate(document, severityRegistry);
        
        // Format the list of problems into a string for comparison with the expected value
        let actual: string[] = formatProblems(problems);

        // Load the expected result and compare with actual
        let expectedStr: string = readTXT(testPath + ".expected");
        expect(expectedStr).not.toBeNull();
        let expected: string[] = [];
        if (expectedStr) {
            expected = expectedStr.split(/\r?\n/);
        }
        expect(actual).toEqual(expected);
    });
});

class CustomValidationExtension implements IDocumentValidatorExtension {
    validateDocument(_: Node): Promise<ValidationProblem[]>{
        const testProblems: ValidationProblem[] = [
           new ValidationProblem("TEST-001", new NodePath("testpath"), "test", "Test problem", ValidationProblemSeverity.low)
        ];

        return Promise.resolve(testProblems);
    }
}

describe("validateDocument()", () => {
    test("return custom validation problem defined by the extension", async () => {
        // Read the source JSON file
        let testPath: string = "tests/fixtures/validation/openapi/3.0/valid-pet-store.json";
        let json: any = readJSON(testPath);
        expect(json).not.toBeNull();

        // Parse/read the document
        let document: Document = Library.readDocument(json);

        const testCustomValidator = new CustomValidationExtension();
        const problems = await Library.validateDocument(document, null, [testCustomValidator]);
        expect(problems).toHaveLength(1);
        expect(problems[0].errorCode).toBe("TEST-001");
    });

    test("return no validation problems when validation extensions is null", async () => {
        // Read the source JSON file
        let testPath: string = "tests/fixtures/validation/openapi/3.0/valid-pet-store.json";
        let json: any = readJSON(testPath);
        expect(json).not.toBeNull();

        // Parse/read the document
        let document: Document = Library.readDocument(json);

        const problems = await Library.validateDocument(document, null, null);
        expect(problems).toHaveLength(0);
    });

    test("return no validation problems when validation extensions list is empty", async () => {
        // Read the source JSON file
        let testPath: string = "tests/fixtures/validation/openapi/3.0/valid-pet-store.json";
        let json: any = readJSON(testPath);
        expect(json).not.toBeNull();

        // Parse/read the document
        let document: Document = Library.readDocument(json);

        const problems = await Library.validateDocument(document, null, []);
        expect(problems).toHaveLength(0);
    });

    test("return validation problems from both ApicurioDM validation and external validation extensions", async () => {
        // Read the source JSON file
        const openapiData = {
            openapi: "3.0.2",
            info: {
                title: "Very Simple API",
            }
        };

        const document = Library.readDocument(openapiData);

        const testCustomValidator = new CustomValidationExtension();
        const problems = await Library.validateDocument(document, null, [testCustomValidator]);
        expect(problems.find((p => p.errorCode === "TEST-001"))).toBeDefined();
        expect(problems.find((p => p.errorCode === "INF-002"))).toBeDefined();
        expect(problems.find((p => p.errorCode === "R-003"))).toBeDefined();
    });

    test("return validation problems from ApicurioDM validation", async () => {
        // Read the source JSON file
        const openapiData = {
            openapi: "3.0.2",
            info: {
                title: "Very Simple API",
            }
        };

        const document = Library.readDocument(openapiData);

        const problems = await Library.validateDocument(document, null, null);
        expect(problems.find((p => p.errorCode === "INF-002"))).toBeDefined();
        expect(problems.find((p => p.errorCode === "R-003"))).toBeDefined();
    });
});
