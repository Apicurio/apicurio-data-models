///<reference path="../node_modules/@types/jasmine/index.d.ts"/>
///<reference path="@types/karma-read-json/index.d.ts"/>

/**
 * @license
 * Copyright 2016 JBoss Inc
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
import {CombinedAllNodeVisitor} from "../src/io/apicurio/datamodels/combined/visitors/CombinedAllNodeVisitor";
import {TraverserDirection} from "../src/io/apicurio/datamodels/core/visitors/TraverserDirection";


class ExtraPropertyDetectionVisitor extends CombinedAllNodeVisitor {
    
    public extraPropertyCount: number = 0;
    
    visitNode(node: Node): void {
        this.extraPropertyCount += node.getExtraPropertyNames().length;
    }

}


export interface TestSpec {
    name: string;
    test: string;
    extraProperties?: number;
}

/**
 * Full I/O Tests for the AsyncAPI library.
 */
describe("Full I/O", () => {

    let TESTS: TestSpec[] = readJSON("tests/fixtures/io/tests.json");

    // All tests in the list above.
    TESTS.forEach( spec => {
        it(spec.name, () => {
            // Read the original/source file JSON
            let testPath: string = "tests/fixtures/io/" + spec.test;
            let json: any = readJSON(testPath);
            expect(json).not.toBeNull();
            
            // Parse/read the document
            let document: Document = Library.readDocument(json);
            
            // Make sure the correct # of extra properties were read
            let extraPropVis: ExtraPropertyDetectionVisitor = new ExtraPropertyDetectionVisitor();
            Library.visitTree(document, extraPropVis, TraverserDirection.down);
            let actualExtraProps: number = extraPropVis.extraPropertyCount;
            let expectedExtraProps: number = spec.extraProperties ? spec.extraProperties : 0;
            expect(actualExtraProps).toEqual(expectedExtraProps);

            // Serialize/write the document
            let jsObj: any = Library.writeNode(document);

            // Compare what we started with vs. what we now have
            expect(jsObj).toEqual(json);
        });
    });

});

