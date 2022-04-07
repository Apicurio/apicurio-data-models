# Apicurio Datamodels

A Typescript library for reading, manipulating, and writing OpenAPI and AsyncAPI documents.

Install with `npm install @apicurio/data-models`.

## Overview

You can use this library to read an OpenAPI or AsyncAPI document, resulting in an instance
of a data model.  The data model can then be read or manipulated.  It can also be validated.

The data model can be accessed directly, but there is also a robust visitor
pattern available for more advanced analysis or transformation of the model.

The next section (Quickstart) explains, in a nutshell, how to use the library
for standard/basic tasks.  The API section below contains more information,
necessary to fully leverage the capabilities of the library.

## See It In Action!

If you want to quickly see what this library can do, you can check out 
[this simple demo](https://apicurio-data-models-demo.stackblitz.io), or go all out and
[give Apicurio a try](https://www.apicur.io/) (this library is used by the 
Apicurio editor when editing an OpenAPI or AsyncAPI definition).

## Quickstart

The easiest way to get started is to use the library utility class:

_Typescript:_

```Typescript
// Get the OpenAPI document from somewhere
const openApiData: string = ...;

// Use the library util to create a data model instance from the given
// data.  This will convert from the source string into an instance of 
// the OpenAPI data model.
const document: Document = Library.readDocumentFromJSONString(openApiData);

// Here you can analyze or manipulate the model.
document.info.version = "1.7";
document.info.description = "Made some changes to the OpenAPI document!";

// Validate that your changes are OK.
const problems = Library.validateDocument(document, null);

// And now write the node back out as a JSON string
let modifiedOpenApiData: string = Library.writeDocumentToJSONString(document);
```

_Browser (UMD):_

```JavaScript
var openApiData = ...; // Get your OpenAPI data somehow (can be string or JS object)

var document = ApicurioDM.Library.readDocumentFromJSONString(openApiData);

document.info.version = "1.1";
document.info.description = "Made some changes to the OpenAPI document!";

var problems = await ApicurioDM.Library.validateDocument(document, null);

var modifiedOpenApiData = JSON.stringify(ApicurioDM.Library.writeDocumentToJSONString(document));
```

## API

### Library Util Class

The library comes with a util class that makes certain common tasks easier.
These tasks include:

* Creating a document (data model)
* Reading a document from a string or JS object
* Writing a document to a string or JS object
* Validating a model
* Creating a node path
* Visiting a model

#### Create Document

`Library.createDocument(DocumentType): Document`

Use this method to create an empty OpenAPI or AsyncAPI document (data model).  You
must pass one of the values of the DocumentType enum to indicate what sort of
document you want (OpenAPI 2, OpenAPI 3, AsyncAPI 2, etc).

#### Read Document

`Library.readDocument(any): Document`
`Library.readDocumentFromJSONString(string): Document`

These two methods allow you to parse a document either from a JS object or from a 
string and turn it into a Document.  The correct type of document will automatically
be figured out based on the content passed (by interrogating the `openapi` or `asyncapi`
properties).

#### Write Node

`Library.writeNode(Node): any`
`Library.readDocumentFromJSONString(Document): string`

Use these method to convert from a data model instance back to a JS object or
string.  You can pass any node from the data model tree into the `writeNode` method
and the appropriate JS object will be returned.  If you pass in the root document node, then the
full OpenAPI JS object will be returned.  If, for example, you pass in only the
`document.info` child node, then a JS object representing on that portion of the
data model will be returned.  The `readDocumentFromJSONString` method must be
sent a full Document, and will return a stringified object.

### Resolve External References

`Library.addReferenceResolver(resolver: IReferenceResolver): void`

The OpenAPI specification allows references across documents (in various places)
using the `$ref` property.  The library itself cannot resolve external references,
but rather supports a customizable reference resolution layer.  Use this layer by
providing a custom implementation of the `IReferenceResolver` interface and 
installing it via the `Library::addReferenceResolver(resolver: IReferenceResolver)`
method.  Multiple reference resolvers can be installed - the first resolver that
can successfully resolve a reference will win.  The library has one default resolver
that is capable of resolving internal references - for example `#!/components/schemas/Widget`.

#### Validate (deprecated)

`Library::validate(Node, IValidationSeverityRegistry): ValidationProblem[]`

Use this method to validate a document (or subsection of the document).  The
library includes all validation rules defined by the OpenAPI and AsyncAPI specifications.
You can use this method to apply the appropriate rules to any section of the
data model.  The return result is an array of validation problems, or an empty
array if the document is fully valid.

Note that in addition to returning an array of problems, the problems will also
be stored on the model itself.  Any node that violates a validation rule
will have the problem object added to a collection of problems stored directly
on the node itself.  Thus, you can check if an individual node has any 
validation problems:

```Typescript
let node: Node = ...;
let problems: ValidationProblem[] = node.getValidationProblems();
if (problems && problems.length > 0) {
    // The node failed validation!
}
````

Additionally, convenience methods exist on the node to get a more granular
list of problems, in the case where you are only interested in problems for a
specific property of the node (e.g. you might only be interested in problems
for the `description` property):

```Typescript
let node: Node = ...;
let problems: ValidationProblem[] = node.getValidationProblemsFor('description');
if (problems && problems.length > 0) {
    // The node failed validation!
}
```

#### Create a Node Path

`Library::createNodePath(Node): NodePath`

For more information about node paths, see the "Node Paths" section below.


### The Data Model

This library has data model classes representing each of the objects defined
by the OpenAPI and AsyncAPI specifications.  Overall, an instance of a data model is simply
a tree of nodes corresponding to the appropriate specification.  Each node in the
model is unique depending on its specification definition, in addition to 
sharing a common set of functionality:

* _Parent_: Every node has a reference to its parent node.
* _Owner Document_: Every node has a reference to its owning document.
* _Node Attributes_:  Every node has a set of transient attributes which
  are not serialized when converting back to a JS object.
* _Model ID_: Each node has a unique ID generating when the node is created.


### Node Paths
As mentioned, the OpenAPI library's data model is essentially a tree of nodes
of specific types, as defined by the specification.  An additional feature
of the library is the ability to identify any node in the model by its "node
path".  A node path is a bit like a simple XPath for an XML document.  You
can use a node path to quickly resolve a node.  Node paths are even (sort of)
human readable!

For example, you could quickly get a specific node in the standard OpenAPI
Pet Store example document with the following code:

```Typescript
let document: Document = ...;
let path: NodePath = new NodePath("/paths[/pet/{petId}]/get/responses[200]");
let resolvedNode: Node = path.resolve(document);
```

Additionally, you can easily create a node path from a given node in the 
data model by using the `createNodePath(Node)` method in the 
`Library` class:

```Typescript
let document: Document = ...;
let node: Node = document.paths.pathItem("/pet/{petId}").get.responses.response("200");
let path: NodePath = Library.createNodePath(node);
```


### Visiting the Data Model

In addition to basic reading and writing of a data model, this library also
includes an implementation of the visitor pattern (useful for more advanced
analysis or transformation of the data model).

To use this feature, you must create a Typescript class that extends the 
`IVisitor` interface.  You can then either call `accept` on any node in 
the model (which will visit just that one node) or else traverse the entire 
model (either up or down).  Some examples are below.

#### Visit a Single Node

```Typescript
let document: Document = getOrCreateDocument();
let visitor: IVisitor = new MyCustomVisitor();
// Visit ONLY the "Info" node.
Library.visitNode(document.info, visitor);
```

#### Visit the Entire Document

```Typescript
let document: Document = getOrCreateDocument();
let visitor: IVisitor = new MyCustomVisitor();
Library.visitTree(document, visitor, TraverserDirection.down);
```

#### Visit a Node And Its Parents

```Typescript
let document: Document = getOrCreateDocument();
let visitor: IVisitor = new MyCustomVisitor();
// Visit the Info node and then the Document (root) node
Library.visitTree(document.info, visitor, OasTraverserDirection.up);
```
