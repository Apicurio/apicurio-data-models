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

import io.apicurio.datamodels.asyncapi.v20.AsyncApi20Document;
import io.apicurio.datamodels.asyncapi.v20.AsyncApi20DocumentImpl;
import io.apicurio.datamodels.asyncapi.v20.io.AsyncApi20ModelReader;
import io.apicurio.datamodels.asyncapi.v21.AsyncApi21Document;
import io.apicurio.datamodels.asyncapi.v21.AsyncApi21DocumentImpl;
import io.apicurio.datamodels.asyncapi.v21.io.AsyncApi21ModelReader;
import io.apicurio.datamodels.asyncapi.v22.AsyncApi22Document;
import io.apicurio.datamodels.asyncapi.v22.AsyncApi22DocumentImpl;
import io.apicurio.datamodels.asyncapi.v22.io.AsyncApi22ModelReader;
import io.apicurio.datamodels.asyncapi.v23.AsyncApi23Document;
import io.apicurio.datamodels.asyncapi.v23.AsyncApi23DocumentImpl;
import io.apicurio.datamodels.asyncapi.v23.io.AsyncApi23ModelReader;
import io.apicurio.datamodels.asyncapi.v24.AsyncApi24Document;
import io.apicurio.datamodels.asyncapi.v24.AsyncApi24DocumentImpl;
import io.apicurio.datamodels.asyncapi.v24.io.AsyncApi24ModelReader;
import io.apicurio.datamodels.asyncapi.v25.AsyncApi25Document;
import io.apicurio.datamodels.asyncapi.v25.AsyncApi25DocumentImpl;
import io.apicurio.datamodels.asyncapi.v25.io.AsyncApi25ModelReader;
import io.apicurio.datamodels.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.openapi.v20.OpenApi20DocumentImpl;
import io.apicurio.datamodels.openapi.v20.io.OpenApi20ModelReader;
import io.apicurio.datamodels.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.openapi.v30.OpenApi30DocumentImpl;
import io.apicurio.datamodels.openapi.v30.io.OpenApi30ModelReader;
import io.apicurio.datamodels.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.openapi.v31.OpenApi31DocumentImpl;
import io.apicurio.datamodels.openapi.v31.io.OpenApi31ModelReader;
import io.apicurio.datamodels.util.JsonUtil;
import io.apicurio.datamodels.visitors.Visitor;

/**
 * The most common entry points into using the data models library.  Provides convenience methods
 * for performing common actions such as i/o, visiting, and validation.
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Library {

    /**
     * Reads an entire document from JSON data.  The JSON data (already parsed, not in string format) is
     * read as a data model {@link Document} and returned.
     * @param json
     */
    public static Document readDocument(ObjectNode json) {
        // Clone the input because the reader is destructive to the source data.
        ObjectNode clonedJson = (ObjectNode) JsonUtil.clone(json);
        DocumentType type = DocumentTypeDetector.discoverDocumentType(clonedJson);
        switch (type) {
            case ASYNCAPI_20:
            {
                AsyncApi20ModelReader reader = new AsyncApi20ModelReader();
                AsyncApi20Document document = new AsyncApi20DocumentImpl();
                reader.readDocument(clonedJson, document);
                return document;
            }
            case ASYNCAPI_21:
            {
                AsyncApi21ModelReader reader = new AsyncApi21ModelReader();
                AsyncApi21Document document = new AsyncApi21DocumentImpl();
                reader.readDocument(clonedJson, document);
                return document;
            }
            case ASYNCAPI_22:
            {
                AsyncApi22ModelReader reader = new AsyncApi22ModelReader();
                AsyncApi22Document document = new AsyncApi22DocumentImpl();
                reader.readDocument(clonedJson, document);
                return document;
            }
            case ASYNCAPI_23:
            {
                AsyncApi23ModelReader reader = new AsyncApi23ModelReader();
                AsyncApi23Document document = new AsyncApi23DocumentImpl();
                reader.readDocument(clonedJson, document);
                return document;
            }
            case ASYNCAPI_24:
            {
                AsyncApi24ModelReader reader = new AsyncApi24ModelReader();
                AsyncApi24Document document = new AsyncApi24DocumentImpl();
                reader.readDocument(clonedJson, document);
                return document;
            }
            case ASYNCAPI_25:
            {
                AsyncApi25ModelReader reader = new AsyncApi25ModelReader();
                AsyncApi25Document document = new AsyncApi25DocumentImpl();
                reader.readDocument(clonedJson, document);
                return document;
            }
            case OPENAPI_2:
            {
                OpenApi20ModelReader reader = new OpenApi20ModelReader();
                OpenApi20Document document = new OpenApi20DocumentImpl();
                reader.readDocument(clonedJson, document);
                return document;
            }
            case OPENAPI_30:
            {
                OpenApi30ModelReader reader = new OpenApi30ModelReader();
                OpenApi30Document document = new OpenApi30DocumentImpl();
                reader.readDocument(clonedJson, document);
                return document;
            }
            case OPENAPI_31:
            {
                OpenApi31ModelReader reader = new OpenApi31ModelReader();
                OpenApi31Document document = new OpenApi31DocumentImpl();
                reader.readDocument(clonedJson, document);
                return document;
            }
            default:
            {
                throw new RuntimeException("Unsupported document type.");
            }
        }
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
     * Visits an entire tree (either up or down).
     * @param type
     * @param node
     * @param visitor
     * @param direction
     */
    public static void visitTree(DocumentType type, Node node, Visitor visitor, TraverserDirection direction) {
        VisitorUtil.visitTree(type, node, visitor, direction);
    }

}
