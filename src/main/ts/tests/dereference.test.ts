import {Library} from "../src/io/apicurio/datamodels/Library";
import {readJSON} from "./util/tutils";
import {IReferenceResolver} from "../src/io/apicurio/datamodels/refs/IReferenceResolver";
import {Node} from "../src/io/apicurio/datamodels/models/Node";
import {Document} from "../src/io/apicurio/datamodels/models/Document";


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
        let rval: Node = from.emptyClone();
        rval.attach(from.parent());
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
        let dereferencedDoc: Document = Library.dereferenceDocument(sourceDoc, true);

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
