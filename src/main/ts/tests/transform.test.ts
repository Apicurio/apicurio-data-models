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
import {DocumentType} from "../src/io/apicurio/datamodels/core/models/DocumentType";
import {Oas20to30TransformationVisitor} from "../src/io/apicurio/datamodels/openapi/visitors/transform/Oas20to30TransformationVisitor";
import {Library} from "../src/io/apicurio/datamodels/Library";
import {TraverserDirection} from "../src/io/apicurio/datamodels/core/visitors/TraverserDirection";
import {readJSON} from "./util/tutils";


interface TestSpec {
    name: string;
    input: string;
    expected: string;
}


let allTests: TestSpec[] = readJSON("tests/fixtures/transformation/tests.json");
allTests.forEach(spec => {
    test(spec.name, () => {
        // Read the original/source file JSON
        let testPath: string = "tests/fixtures/transformation/" + spec.input;
        let originalJson: any = readJSON(testPath);
        expect(originalJson).not.toBeNull();

        // Parse/read the document
        let doc20: Document = Library.readDocument(originalJson);
        expect(doc20.getDocumentType()).toEqual(DocumentType.openapi2);
        
        // Transform the document
        let transformer: Oas20to30TransformationVisitor = new Oas20to30TransformationVisitor();
        Library.visitTree(doc20, transformer, TraverserDirection.down);
        let doc30: Document = transformer.getResult();
        
        // Serialize/write the document
        let actual: any = Library.writeNode(doc30);
        expect(actual).not.toBeNull();

        let expectedPath: string = "tests/fixtures/transformation/" + spec.expected;
        let expected: any = readJSON(expectedPath);
        expect(expected).not.toBeNull();

        // Compare what we got with what we expected
        expect(actual).toEqual(expected);
    });
});
