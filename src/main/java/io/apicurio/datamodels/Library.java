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

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.io.ModelReader;
import io.apicurio.datamodels.models.io.ModelReaderFactory;
import io.apicurio.datamodels.models.io.ModelWriter;
import io.apicurio.datamodels.models.io.ModelWriterFactory;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Operation;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.visitors.Visitor;

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
     * Visits an entire tree (either up or down).
     * @param type
     * @param node
     * @param visitor
     * @param direction
     */
    public static void visitTree(Node node, Visitor visitor, TraverserDirection direction) {
        ModelType type = node.root().modelType();
        VisitorUtil.visitTree(type, node, visitor, direction);
    }

}
