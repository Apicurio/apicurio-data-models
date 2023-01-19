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

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.asyncapi.v20.visitors.AsyncApi20Traverser;
import io.apicurio.datamodels.models.asyncapi.v21.visitors.AsyncApi21Traverser;
import io.apicurio.datamodels.models.asyncapi.v22.visitors.AsyncApi22Traverser;
import io.apicurio.datamodels.models.asyncapi.v23.visitors.AsyncApi23Traverser;
import io.apicurio.datamodels.models.asyncapi.v24.visitors.AsyncApi24Traverser;
import io.apicurio.datamodels.models.asyncapi.v25.visitors.AsyncApi25Traverser;
import io.apicurio.datamodels.models.openapi.v20.visitors.OpenApi20Traverser;
import io.apicurio.datamodels.models.openapi.v30.visitors.OpenApi30Traverser;
import io.apicurio.datamodels.models.openapi.v31.visitors.OpenApi31Traverser;
import io.apicurio.datamodels.models.visitors.ReverseTraverser;
import io.apicurio.datamodels.models.visitors.Traverser;
import io.apicurio.datamodels.models.visitors.Visitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class VisitorUtil {

    public static void visitTree(Node node, Visitor visitor, TraverserDirection direction) {
        ModelType type = node.root().modelType();
        Traverser traverser = null;
        if (direction == TraverserDirection.up) {
            traverser = new ReverseTraverser(visitor);
        } else {
            switch (type) {
                case ASYNCAPI20:
                    traverser = new AsyncApi20Traverser(visitor);
                    break;
                case ASYNCAPI21:
                    traverser = new AsyncApi21Traverser(visitor);
                    break;
                case ASYNCAPI22:
                    traverser = new AsyncApi22Traverser(visitor);
                    break;
                case ASYNCAPI23:
                    traverser = new AsyncApi23Traverser(visitor);
                    break;
                case ASYNCAPI24:
                    traverser = new AsyncApi24Traverser(visitor);
                    break;
                case ASYNCAPI25:
                    traverser = new AsyncApi25Traverser(visitor);
                    break;
                case OPENAPI20:
                    traverser = new OpenApi20Traverser(visitor);
                    break;
                case OPENAPI30:
                    traverser = new OpenApi30Traverser(visitor);
                    break;
                case OPENAPI31:
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
