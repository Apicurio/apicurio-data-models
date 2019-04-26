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

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.factories.DocumentFactory;
import io.apicurio.datamodels.core.factories.VisitorFactory;
import io.apicurio.datamodels.core.io.DataModelReader;
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

/**
 * The most common entry points into using the data models library.  Provides convenience methods
 * for performing common actions such as i/o, visiting, and validation.
 * @author eric.wittmann@gmail.com
 */
public class Library {
    
    public static NodePath createNodePath(Node node) {
        return NodePathUtil.createNodePath(node);
    }

    public static void visitNode(Node node, IVisitor visitor) {
        VisitorUtil.visitNode(node, visitor);
    }
    
    public static void visitTree(Node node, IVisitor visitor, TraverserDirection direction) {
        VisitorUtil.visitTree(node, visitor, direction);
    }

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
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Document readDocument(Object json) {
        // Clone the input because the reader is destructive to the source data.
        Object clonedJson = JsonCompat.clone(json);
        DocumentType type = discoverDocumentType(clonedJson);
        DataModelReader reader = VisitorFactory.createDataModelReader(type);
        Document document = DocumentFactory.create(type);
        reader.readDocument(clonedJson, document);
        return document;
    }
    
    public static Object writeNode(Node node) {
        DataModelWriter writer = VisitorFactory.createDataModelWriter(node.ownerDocument());
        visitTree(node, writer, TraverserDirection.down);
        return writer.getResult();
    }
    
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

}
