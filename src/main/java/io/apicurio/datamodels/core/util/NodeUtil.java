/*
 * Copyright 2021 Red Hat Inc
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

package io.apicurio.datamodels.core.util;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.factories.VisitorFactory;
import io.apicurio.datamodels.core.io.DataModelReader;
import io.apicurio.datamodels.core.io.DataModelReaderDispatcher;
import io.apicurio.datamodels.core.io.DataModelWriter;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;

/**
 * @author eric.wittmann@gmail.com
 */
public class NodeUtil {

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
        VisitorUtil.visitNode(node, dispatcher);
        return node;
    }

    /**
     * Called to serialize a given data model node to a JSON object.
     * @param node
     */
    public static Object writeNode(Node node) {
        DataModelWriter writer = VisitorFactory.createDataModelWriter(node.ownerDocument());
        VisitorUtil.visitTree(node, writer, TraverserDirection.down);
        return writer.getResult();
    }

}
