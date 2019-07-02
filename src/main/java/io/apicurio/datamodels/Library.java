/*
 * Copyright 2019 Red Hat
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

import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.factories.DocumentFactory;
import io.apicurio.datamodels.core.factories.VisitorFactory;
import io.apicurio.datamodels.core.io.DataModelReader;
import io.apicurio.datamodels.core.io.DataModelReaderDispatcher;
import io.apicurio.datamodels.core.io.DataModelWriter;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.util.NodePathUtil;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.validation.DefaultSeverityRegistry;
import io.apicurio.datamodels.core.validation.IValidationSeverityRegistry;
import io.apicurio.datamodels.core.validation.ValidationProblemsResetVisitor;
import io.apicurio.datamodels.core.validation.ValidationVisitor;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;
import io.apicurio.datamodels.openapi.visitors.transform.Oas20to30TransformationVisitor;

/**
 * The most common entry points into using the data models library.  Provides convenience methods
 * for performing common actions such as i/o, visiting, and validation.
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Library {
    
    /**
     * Creates a new, empty document of the given type.
     * @param type
     */
    public static Document createDocument(DocumentType type) {
        switch (type) {
            case asyncapi2:
                return new Aai20Document();
            case openapi2:
                return new Oas20Document();
            case openapi3:
                return new Oas30Document();
            default:
                throw new RuntimeException("Unknown document type: " + type);
        }
    }
    
    /**
     * Called to create a node path for a given data model node.
     * @param node
     */
    public static NodePath createNodePath(Node node) {
        return NodePathUtil.createNodePath(node);
    }

    /**
     * Convenience method for visiting a single data model nodel.
     * @param node
     * @param visitor
     */
    public static void visitNode(Node node, IVisitor visitor) {
        VisitorUtil.visitNode(node, visitor);
    }
    
    /**
     * Convenience method for visiting a data model tree.  Supports traversing the data model either
     * up or down the tree.
     * @param node
     * @param visitor
     * @param direction
     */
    public static void visitTree(Node node, IVisitor visitor, TraverserDirection direction) {
        VisitorUtil.visitTree(node, visitor, direction);
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
        
        // Clear/reset any problems that may have been found before.
        ValidationProblemsResetVisitor resetter = VisitorFactory.createValidationProblemsResetVisitor(node.ownerDocument());
        visitTree(node, resetter, TraverserDirection.down);
        
        // Validate the data model.
        ValidationVisitor validator = VisitorFactory.createValidationVisitor(node.ownerDocument());
        validator.setSeverityRegistry(severityRegistry);
        visitTree(node, validator, TraverserDirection.down);
        
        return validator.getValidationProblems();
    }
    
    /**
     * Reads an entire document from JSON data.  The JSON data (already parsed, not in string format) is
     * read as a data model {@link Document} and returned.
     * @param json
     */
    public static Document readDocument(Object json) {
        // Clone the input because the reader is destructive to the source data.
        Object clonedJson = JsonCompat.clone(json);
        DocumentType type = discoverDocumentType(clonedJson);
        DataModelReader reader = VisitorFactory.createDataModelReader(type);
        Document document = DocumentFactory.create(type);
        reader.readDocument(clonedJson, document);
        return document;
    }
    
    /**
     * Reads an entire document from a JSON formatted string.  This will parse the given string into
     * JSON data and then call Library.readDocument.
     * @param jsonString
     */
    public static Document readDocumentFromJSONString(String jsonString) {
        Object json = JsonCompat.parseJSON(jsonString);
        return readDocument(json);
    }
    
    /**
     * Call this to do a "partial read" on a given node.  You must pass the JSON data for the node 
     * type and an empty instance of the property node class.  For example, you could read just an
     * Operation by passing the JSON data for the operation along with an instance of e.g. 
     * {@link Oas30Operation} and this will read the data and store it in the instance.
     * @param json
     * @param node
     */
    public static Node readNode(Object json, Node node) {
        // Clone the input because the reader is destructive to the source data.
        Object clonedJson = JsonCompat.clone(json);
        DocumentType type = node.ownerDocument().getDocumentType();
        DataModelReader reader = VisitorFactory.createDataModelReader(type);
        DataModelReaderDispatcher dispatcher = VisitorFactory.createDataModelReaderDispatcher(type, clonedJson, reader);
        Library.visitNode(node, dispatcher);
        return node;
    }
    
    /**
     * Called to serialize a given data model node to a JSON object.
     * @param node
     */
    public static Object writeNode(Node node) {
        DataModelWriter writer = VisitorFactory.createDataModelWriter(node.ownerDocument());
        visitTree(node, writer, TraverserDirection.down);
        return writer.getResult();
    }
    
    /**
     * Called to serialize a given data model to a JSON formatted string.
     * @param document
     */
    public static String writeDocumentToJSONString(Document document) {
        Object json = Library.writeNode(document);
        return JsonCompat.stringify(json);
    }

    /**
     * Called to discover what type of document the given JSON data represents.  This method
     * interrogates the following appropriate top level properties:
     * 
     * - asyncapi
     * - openapi
     * - swagger
     * 
     * Based on which property is defined, and the value of that property, the correct document
     * type is determined and returned.
     * 
     * @param json
     */
    public static DocumentType discoverDocumentType(Object json) {
        String asyncapi = JsonCompat.getPropertyString(json, Constants.PROP_ASYNCAPI);
        String openapi = JsonCompat.getPropertyString(json, Constants.PROP_OPENAPI);
        String swagger = JsonCompat.getPropertyString(json, Constants.PROP_SWAGGER);
        if (asyncapi != null && asyncapi.startsWith("2.")) {
            return DocumentType.asyncapi2;
        }
        if (openapi != null) {
            if (openapi.startsWith("2.")) {
                return DocumentType.openapi2;
            }
            if (openapi.startsWith("3.0")) {
                return DocumentType.openapi3;
            }
        }
        if (swagger != null) {
            if (swagger.startsWith("2.")) {
                return DocumentType.openapi2;
            }
        }
        
        throw new RuntimeException("Unknown data model type or version.");
    }

    /**
     * Clones the given document by serializing it to a JS object, and then re-parsing it.
     * @param source
     */
    public static Document cloneDocument(Document source) {
        Object jsObj = writeNode(source);
        return readDocument(jsObj);
    }

    /**
     * Transforms from an OpenAPI 2.0 document into a 3.0 document.
     * @param source
     */
    public static Oas30Document transformDocument(Oas20Document source) {
        Oas20Document clone = (Oas20Document) cloneDocument(source);
        Oas20to30TransformationVisitor transformer = new Oas20to30TransformationVisitor();
        VisitorUtil.visitTree(clone, transformer, TraverserDirection.down);
        return transformer.getResult();
    }

}
