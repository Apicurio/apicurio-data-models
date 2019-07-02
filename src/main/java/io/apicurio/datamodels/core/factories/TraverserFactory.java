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

package io.apicurio.datamodels.core.factories;

import io.apicurio.datamodels.asyncapi.v2.visitors.Aai20Traverser;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.combined.visitors.CombinedReverseTraverser;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.visitors.ITraverser;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;
import io.apicurio.datamodels.openapi.v2.visitors.Oas20Traverser;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;
import io.apicurio.datamodels.openapi.v3.visitors.Oas30Traverser;

/**
 * Creates a traverser for a particular data model.
 *
 * @author eric.wittmann@gmail.com
 */
public class TraverserFactory {

    /**
     * Create the appropriate traverser for the given document/data model.
     *
     * @param doc
     * @param visitor
     * @param direction
     */
    public static ITraverser create(Document doc, IVisitor visitor, TraverserDirection direction) {
        if (direction == null) {
            direction = TraverserDirection.down;
        }
        switch (doc.getDocumentType()) {
            case asyncapi2:
                return direction == TraverserDirection.down ? new Aai20Traverser((IAai20Visitor) visitor) : new CombinedReverseTraverser((IAai20Visitor) visitor);
            case openapi2:
                return direction == TraverserDirection.down ? new Oas20Traverser((IOas20Visitor) visitor) : new CombinedReverseTraverser((IOas20Visitor) visitor);
            case openapi3:
                return direction == TraverserDirection.down ? new Oas30Traverser((IOas30Visitor) visitor) : new CombinedReverseTraverser((IOas30Visitor) visitor);
            default:
                throw new RuntimeException("Failed to create a traverser for document type: " + doc.getDocumentType());
        }
    }

}
