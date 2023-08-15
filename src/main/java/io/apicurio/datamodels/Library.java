/*
 * Copyright 2022 Red Hat
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

package io.apicurio.datamodels;

import java.util.List;
import java.util.function.UnaryOperator;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.datamodels.deref.Dereferencer;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.asyncapi.AsyncApiDocument;
import io.apicurio.datamodels.models.io.ModelReader;
import io.apicurio.datamodels.models.io.ModelReaderFactory;
import io.apicurio.datamodels.models.io.ModelWriter;
import io.apicurio.datamodels.models.io.ModelWriterFactory;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Operation;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.refs.IReferenceResolver;
import io.apicurio.datamodels.refs.ReferenceResolverChain;
import io.apicurio.datamodels.transform.OpenApi20to30TransformationVisitor;
import io.apicurio.datamodels.transform.OpenApi30to31TransformationVisitor;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.util.ValidationUtil;
import io.apicurio.datamodels.validation.DefaultSeverityRegistry;
import io.apicurio.datamodels.validation.IValidationSeverityRegistry;
import io.apicurio.datamodels.validation.ValidationProblem;
import io.apicurio.datamodels.validation.ValidationVisitor;

/**
 * The most common entry points into using the data models library.  Provides convenience methods
 * for performing common actions such as i/o, visiting, and validation.
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Library {

    /**
     * Adds a reference resolver to the library.  The resolver will be used whenever the library
     * needs to resolve a $ref reference.
     * @param resolver
     */
    public static void addReferenceResolver(IReferenceResolver resolver) {
        ReferenceResolverChain.getInstance().addResolver(resolver);
    }
    public static void removeReferenceResolver(IReferenceResolver resolver) {
        ReferenceResolverChain.getInstance().removeResolver(resolver);
    }

    /**
     * Creates a new, empty document of the given type.
     * @param type
     */
    public static Document createDocument(ModelType type) {
        ModelReader reader = ModelReaderFactory.createModelReader(type);
        return (Document) reader.readRoot(JsonUtil.objectNode());
    }

    /**
     * Reads an entire document from JSON data.  The JSON data (already parsed, not in string format) is
     * read as a data model {@link Document} and returned.
     * @param json
     */
    public static Document readDocument(ObjectNode json) {
        // Clone the input because the reader is destructive to the source data.
        ObjectNode clonedJson = (ObjectNode) JsonUtil.clone(json);
        ModelType type = ModelTypeDetector.discoverModelType(clonedJson);

        ModelReader reader = ModelReaderFactory.createModelReader(type);
        return (Document) reader.readRoot(clonedJson);
    }

    /**
     * Reads an entire document from a JSON formatted string.  This will parse the given string into
     * JSON data and then call Library.readDocument.
     * @param jsonString
     */
    public static Document readDocumentFromJSONString(String jsonString) {
        ObjectNode json = (ObjectNode) JsonUtil.parseJSON(jsonString);
        return readDocument(json);
    }

    /**
     * Called to serialize a given data model node to a JSON object.
     * @param node
     */
    public static ObjectNode writeDocument(Document document) {
        ModelWriter writer = ModelWriterFactory.createModelWriter(document.root().modelType());
        return writer.writeRoot((RootNode) document);
    }

    /**
     * Called to serialize a given data model to a JSON formatted string.
     * @param document
     */
    public static String writeDocumentToJSONString(Document document) {
        ObjectNode json = Library.writeDocument( document);
        return JsonUtil.stringify(json);
    }

    /**
     * Call this to do a "partial read" on a given node.  You must pass the JSON data for the node
     * type and an empty instance of the node class.  For example, you could read just an
     * Operation by passing the JSON data for the operation along with an instance of e.g.
     * {@link OpenApi30Operation} and this will read the data and store it in the instance.
     * @param json
     * @param node
     */
    public static Node readNode(ObjectNode json, Node node) {
        // Clone the input because the reader is destructive to the source data.
        ObjectNode clonedJson = (ObjectNode) JsonUtil.clone(json);
        Visitor readerDispatcher = ModelReaderFactory.createModelReaderDispatcher(node.root().modelType(), clonedJson);
        node.accept(readerDispatcher);
        return node;
    }

    /**
     * Called to serialize a given data model node to a JSON object.
     * @param node
     */
    public static ObjectNode writeNode(Node node) {
        ObjectNode json = JsonUtil.objectNode();
        Visitor writerDispatcher = ModelWriterFactory.createModelWriterDispatcher(node.root().modelType(), json);
        node.accept(writerDispatcher);
        return json;
    }

    /**
     * Called to serialize a given data model node to a JSON string.
     * @param node
     */
    public static String writeNodeToString(Node node) {
        ObjectNode json = writeNode(node);
        return JsonUtil.stringify(json);
    }

    /**
     * Visits a node with the given visitor.  Convenience method really - you could just call
     * node.accept(visitor) ... and probably should.
     * @param node
     * @param visitor
     */
    public static void visitNode(Node node, Visitor visitor) {
        node.accept(visitor);
    }

    /**
     * Visits an entire tree (either up or down).
     * @param node
     * @param visitor
     * @param direction
     */
    public static void visitTree(Node node, Visitor visitor, TraverserDirection direction) {
        VisitorUtil.visitTree(node, visitor, direction);
    }

    /**
     * Called to create a node path for a given data model node.
     * @param node
     */
    public static NodePath createNodePath(Node node) {
        return NodePathUtil.createNodePath(node);
    }

    /**
     * Called to create a node path instance for a stringified node path.
     * @param node
     */
    public static NodePath parseNodePath(String path) {
        return NodePathUtil.parseNodePath(path);
    }

    /**
     * Resolves the given node path relative to a root document.
     * @param nodePath
     * @param doc
     */
    public static Node resolveNodePath(NodePath nodePath, Document doc) {
        return NodePathUtil.resolveNodePath(nodePath, doc);
    }

    /**
     * Transforms from older versions of specs to newer versions.  Currently supports:
     *
     *  - OpenAPI 2.0 document into a 3.0 document
     *  - OpenAPI 3.0 document into a 3.1 document
     *  - AsyncAPI early version to any later version
     * @param source
     */
    public static Document transformDocument(Document source, ModelType toType) {
        if (source.root().modelType() == ModelType.OPENAPI20 && toType == ModelType.OPENAPI30) {
            // Transform from OpenApi20 to OpenApi30
            OpenApi20Document clone = (OpenApi20Document) cloneDocument(source);
            OpenApi20to30TransformationVisitor transformer = new OpenApi20to30TransformationVisitor();
            VisitorUtil.visitTree(clone, transformer, TraverserDirection.down);
            return transformer.getResult();
        }

        if (source.root().modelType() == ModelType.OPENAPI30 && toType == ModelType.OPENAPI31) {
            // Transform from OpenApi30 to OpenApi31
            OpenApi30to31TransformationVisitor transformer = new OpenApi30to31TransformationVisitor((OpenApi30Document) source);
            VisitorUtil.visitTree(source, transformer, TraverserDirection.down);
            return transformer.getResult();
        }

        if (source.root().modelType() == ModelType.OPENAPI20 && toType == ModelType.OPENAPI31) {
            // Transform to 3.0 first, then from 3.0 to 3.1
            Document doc30 = Library.transformDocument(source, ModelType.OPENAPI30);
            return Library.transformDocument(doc30, toType);
        }

        if (ModelTypeUtil.isAsyncApiModel(source)) {
            AsyncApiDocument doc = (AsyncApiDocument) source;
            String oldVersion = doc.getAsyncapi();
            String newVersion = ModelTypeUtil.getVersion(toType);

            doc.setAsyncapi(newVersion);
            AsyncApiDocument newDoc = (AsyncApiDocument) cloneDocument(source);
            doc.setAsyncapi(oldVersion);
            return newDoc;
        }

        throw new RuntimeException("Transformation not supported.");
    }

    /**
     * Clones the given document by serializing it to a JS object, and then re-parsing it.
     * @param source
     */
    public static Document cloneDocument(Document source) {
        return cloneDocument(source, UnaryOperator.identity());
    }

    /**
     * Clones the given document by serializing it to a JS object, and then re-parsing it.
     * @param source
     * @param transformer
     */
    public static Document cloneDocument(Document source, UnaryOperator<ObjectNode> transformer) {
        // TODO have the code generator produce a Cloner of some kind that knows how to clone any Node.
        //      We already have reader/writer dispatchers.  We only need something that can create a new,
        //      empty model instance from an existing (not empty) model.
        ObjectNode jsObj = transformer.apply(writeNode(source));
        return readDocument(jsObj);
    }

    /**
     * Called to validate a data model node.  All validation rules will be evaluated and reported.  The list
     * of validation problems found during validation is returned.  In addition, validation problems will be
     * reported on the individual nodes themselves.  Validation problem severity is determined by checking
     * with the included severity registry.  If the severity registry is null, a default registry is used.
     * @param node
     * @param severityRegistry
     */
    public static List<ValidationProblem> validate(Node node, IValidationSeverityRegistry severityRegistry) {
        if (severityRegistry == null) {
            severityRegistry = new DefaultSeverityRegistry();
        }

        // Validate the data model.
        ValidationVisitor validator = ValidationUtil.createValidationVisitorForNode(node.root());
        validator.setSeverityRegistry(severityRegistry);
        visitTree(node, validator, TraverserDirection.down);

        return validator.getValidationProblems();
    }

    /**
     * Dereferences a document - this will take all external references ($ref) found in
     * the document and pull them into this document.  It will then update any external
     * reference to instead point to the local copy.  The result is a functionally
     * equivalent document with all resolvable external references removed.
     *
     * @param source the source document
     */
    public static Document dereferenceDocument(Document source) {
        return dereferenceDocument(source, ReferenceResolverChain.getInstance(), false);
    }

    /**
     * Dereferences a document - this will take all external references ($ref) found in
     * the document and pull them into this document.  It will then update any external
     * reference to instead point to the local copy.  The result is a functionally
     * equivalent document with all resolvable external references removed.
     *
     * @param source the source document
     * @param strict if true, throws an exception if unresolvable references remain
     */
    public static Document dereferenceDocument(Document source, boolean strict) {
        return dereferenceDocument(source, ReferenceResolverChain.getInstance(), strict);
    }

    /**
     * Dereferences a document - this will take all external references ($ref) found in
     * the document and pull them into this document.  It will then update any external
     * reference to instead point to the local copy.  The result is a functionally
     * equivalent document with all resolvable external references removed.
     *
     * @param source the source document
     * @param resolver a custom reference resolver to use on this dereference operation
     * @param strict if true, throws an exception if unresolvable references remain
     */
    public static Document dereferenceDocument(Document source, IReferenceResolver resolver, boolean strict) {
        Dereferencer rl = new Dereferencer(resolver, strict);
        return rl.dereference(cloneDocument(source));
    }
}
