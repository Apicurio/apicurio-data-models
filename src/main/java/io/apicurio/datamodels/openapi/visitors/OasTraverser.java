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

package io.apicurio.datamodels.openapi.visitors;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.visitors.IVisitor;
import io.apicurio.datamodels.core.visitors.Traverser;
import io.apicurio.datamodels.openapi.models.OasDocument;

/**
 * An OpenAPI data model traverser.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasTraverser extends Traverser implements IOasVisitor {

    /**
     * Constructor.
     * @param visitor
     */
    public OasTraverser(IVisitor visitor) {
        super(visitor);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.Traverser#doVisitDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    protected void doVisitDocument(Document node) {
        OasDocument doc = (OasDocument) node;
        super.doVisitDocument(node);
        this.traverseCollection(doc.security);
    }

}
