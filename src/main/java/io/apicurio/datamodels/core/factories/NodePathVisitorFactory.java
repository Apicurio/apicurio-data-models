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

import io.apicurio.datamodels.asyncapi.v2.visitors.Aai20NodePathVisitor;
import io.apicurio.datamodels.asyncapi.visitors.AaiNodePathVisitor;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.visitors.NodePathVisitor;
import io.apicurio.datamodels.openapi.v2.visitors.Oas20NodePathVisitor;
import io.apicurio.datamodels.openapi.v3.visitors.Oas30NodePathVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class NodePathVisitorFactory {

    public static NodePathVisitor createNodePathVisitor(Document doc) {
        switch (doc.getDocumentType()) {
            case asyncapi2:
                return new Aai20NodePathVisitor();
            case openapi2:
                return new Oas20NodePathVisitor();
            case openapi3:
                return new Oas30NodePathVisitor();
            default:
                throw new RuntimeException("Failed to create a node path visitor for type: " + doc.getDocumentType());
        }
    }

}
