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

import io.apicurio.datamodels.asyncapi.v20.visitors.AsyncApi20Traverser;
import io.apicurio.datamodels.asyncapi.v21.visitors.AsyncApi21Traverser;
import io.apicurio.datamodels.asyncapi.v22.visitors.AsyncApi22Traverser;
import io.apicurio.datamodels.asyncapi.v23.visitors.AsyncApi23Traverser;
import io.apicurio.datamodels.asyncapi.v24.visitors.AsyncApi24Traverser;
import io.apicurio.datamodels.asyncapi.v25.visitors.AsyncApi25Traverser;
import io.apicurio.datamodels.openapi.v20.visitors.OpenApi20Traverser;
import io.apicurio.datamodels.openapi.v30.visitors.OpenApi30Traverser;
import io.apicurio.datamodels.openapi.v31.visitors.OpenApi31Traverser;
import io.apicurio.datamodels.visitors.ReverseTraverser;
import io.apicurio.datamodels.visitors.Traverser;
import io.apicurio.datamodels.visitors.Visitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class VisitorUtil {

    public static void visitTree(DocumentType type, Node node, Visitor visitor, TraverserDirection direction) {
        Traverser traverser = null;
        if (direction == TraverserDirection.up) {
            traverser = new ReverseTraverser(visitor);
        } else {
            switch (type) {
                case ASYNCAPI_20:
                    traverser = new AsyncApi20Traverser(visitor);
                    break;
                case ASYNCAPI_21:
                    traverser = new AsyncApi21Traverser(visitor);
                    break;
                case ASYNCAPI_22:
                    traverser = new AsyncApi22Traverser(visitor);
                    break;
                case ASYNCAPI_23:
                    traverser = new AsyncApi23Traverser(visitor);
                    break;
                case ASYNCAPI_24:
                    traverser = new AsyncApi24Traverser(visitor);
                    break;
                case ASYNCAPI_25:
                    traverser = new AsyncApi25Traverser(visitor);
                    break;
                case OPENAPI_2:
                    traverser = new OpenApi20Traverser(visitor);
                    break;
                case OPENAPI_30:
                    traverser = new OpenApi30Traverser(visitor);
                    break;
                case OPENAPI_31:
                    traverser = new OpenApi31Traverser(visitor);
                    break;
            }
        }
        if (traverser == null) {
            throw new RuntimeException("Traverser not found for type: " + type);
        }
        traverser.traverse(node);
    }

}
