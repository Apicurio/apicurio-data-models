/**
 * @license
 * Copyright 2020 JBoss Inc
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
import {readJSON} from "./util/tutils";
import {IReferenceResolver} from "../src/io/apicurio/datamodels/core/util/IReferenceResolver";
import {ModelCloner} from "../src/io/apicurio/datamodels/cloning/ModelCloner";


interface TestSpec {
    name: string;
    input: string;
    expected: string;
}


const refs: any = readJSON("tests/fixtures/dereference/tests.refs.json");
class DereferenceTestReferenceResolver implements IReferenceResolver {
    
    public resolveRef(reference: string, from: Node): Node {
        if (refs[reference]) {
            return this.toModel(refs[reference], from);
        }
        return null;
    }
    
    public toModel(jsonNode: any, from: Node): Node {
        let rval: Node = ModelCloner.createEmptyClone(from);
        return Library.readNode(jsonNode, rval);
    }
    
}
Library.addReferenceResolver(new DereferenceTestReferenceResolver());


let allTests: TestSpec[] = readJSON("tests/fixtures/dereference/tests.json");
allTests.forEach(spec => {
    test(spec.name, () => {
        // Read the original/source file JSON
        let testPath: string = "tests/fixtures/dereference/" + spec.input;
        let originalJson: any = readJSON(testPath);
        expect(originalJson).not.toBeNull();

        // Parse/read the document
        let sourceDoc: Document = Library.readDocument(originalJson);
        expect(sourceDoc).not.toBeNull();
        
        // Dereference the document
        let dereferencedDoc: Document = Library.dereferenceDocument(sourceDoc);
        
        // Serialize/write the document
        let actual: any = Library.writeNode(dereferencedDoc);
        expect(actual).not.toBeNull();

        let expectedPath: string = "tests/fixtures/dereference/" + spec.expected;
        let expected: any = readJSON(expectedPath);
        expect(expected).not.toBeNull();

        // Compare what we got with what we expected
        expect(actual).toEqual(expected);
    });
});
