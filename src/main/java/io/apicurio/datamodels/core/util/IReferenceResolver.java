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

package io.apicurio.datamodels.core.util;

import io.apicurio.datamodels.core.models.Node;

/**
 * Used to resolve references ($ref or operationRef) to a JSON object.  Users of the
 * apicurio-data-models library can provide zero or more custom implementations of
 * this interface in order to provide a way to resolve reference URI formats that
 * are not supported by default.  However, internal references and external http[s]
 * based references are supported by default.
 * 
 * When providing custom reference resolvers, those custom resolvers will be used
 * before any default/built-in resolvers.
 * 
 * @author eric.wittmann@gmail.com
 */
public interface IReferenceResolver {
    
    /**
     * Resolves a reference to a JSON object or {@link Node}.
     * @param reference
     * @param from
     */
    public Node resolveRef(String reference, Node from);

}
