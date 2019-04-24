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

package io.apicurio.datamodels.core.io;

import io.apicurio.datamodels.asyncapi.visitors.AaiAllNodeVisitor;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class ExtraPropertyDetectionVisitors {
    
    public static IExtraPropertyDetectionVisitor create(Document doc) {
        switch (doc.getDocumentType()) {
            case asyncapi2:
                return new AaiExtraPropertyDetectionVisitor();
            case openapi2:
            case openapi3:
            default:
                throw new RuntimeException("Unimplemented extra property detection visitor for data model type: " + doc.getDocumentType());
        }
    }
    
    public static interface IExtraPropertyDetectionVisitor extends IVisitor {
        public int getExtraPropertyCount();
    }

    private static class AaiExtraPropertyDetectionVisitor extends AaiAllNodeVisitor implements IExtraPropertyDetectionVisitor {

        public int extraPropertyCount = 0;
        
        /**
         * @see io.apicurio.datamodels.core.io.ExtraPropertyDetectionVisitors.IExtraPropertyDetectionVisitor#getExtraPropertyCount()
         */
        @Override
        public int getExtraPropertyCount() {
            return extraPropertyCount;
        }

        /**
         * @see io.apicurio.datamodels.core.visitors.AllNodeVisitor#visitNode(io.apicurio.datamodels.core.models.Node)
         */
        @Override
        protected void visitNode(Node node) {
            extraPropertyCount += node.getExtraPropertyNames().size();
        }
        
    }
    
}
