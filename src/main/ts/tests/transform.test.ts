import {Library} from "../src/io/apicurio/datamodels/Library";
import {readJSON} from "./util/tutils";
import {Document} from "../src/io/apicurio/datamodels/models/Document";
import {ModelType} from "../src/io/apicurio/datamodels/models/ModelType";
import {
    OpenApi20to30TransformationVisitor
} from "../src/io/apicurio/datamodels/transform/OpenApi20to30TransformationVisitor";
import {TraverserDirection} from "../src/io/apicurio/datamodels/TraverserDirection";


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
        expect(doc20.root().modelType()).toEqual(ModelType.OPENAPI20);

        // Transform the document
        let transformer: OpenApi20to30TransformationVisitor = new OpenApi20to30TransformationVisitor();
        Library.visitTree(doc20, transformer, TraverserDirection.down);
        let doc30: Document = transformer.getResult();
        expect(doc30.root().modelType()).toEqual(ModelType.OPENAPI30);

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
