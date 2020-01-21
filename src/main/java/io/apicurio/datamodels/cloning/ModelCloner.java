/*
 * Copyright 2019 JBoss Inc
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

package io.apicurio.datamodels.cloning;

import io.apicurio.datamodels.core.models.Node;

/**
 * Class used to create an empty clone of any data model node.
 * @author eric.wittmann@gmail.com
 */
public class ModelCloner {
    
    public static Node createEmptyClone(Node source) {
        ModelClonerVisitor visitor = null;
        switch (source.ownerDocument().getDocumentType()) {
            case asyncapi2:
                visitor = new Aai20ModelClonerVisitor();
                break;
            case openapi2:
                visitor = new Oas20ModelClonerVisitor();
                break;
            case openapi3:
                visitor = new Oas30ModelClonerVisitor();
                break;
            default:
                break;
        }
        if (visitor == null) {
            throw new RuntimeException("No model cloner found for: " + source.ownerDocument().getDocumentType());
        }
        source.accept(visitor);
        return visitor.getClone();
    }

}
