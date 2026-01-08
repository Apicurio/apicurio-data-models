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

package io.apicurio.datamodels.refs;

import io.apicurio.datamodels.models.Node;

/**
 * Used to resolve references ($ref or operationRef) to a JSON object.  Users of the
 * apicurio-data-models library can provide zero or more custom implementations of
 * this interface in order to provide a way to resolve reference URI formats that
 * are not supported by default. Internal references are supported by default.
 *
 * When providing custom reference resolvers, those custom resolvers will be used
 * before any default/built-in resolvers.
 *
 * @author eric.wittmann@gmail.com
 */
public interface IReferenceResolver {

    /**
     * Resolves a reference to a {@link Node}.
     *
     * <p>For references to JSON content, the resolver should return a Node containing
     * the parsed JSON structure.</p>
     *
     * <p>For references to non-JSON content (such as Protocol Buffers .proto files,
     * Apache Avro .avsc files, or other text-based schema formats), the resolver must
     * return a Node with the text content stored in an extension element named
     * "x-text-content". This allows the dereferencer to properly detect and wrap
     * non-JSON schemas in AsyncAPI 3.0 Multi-Format Schema Objects.</p>
     *
     * <p>Example for a .proto file reference:</p>
     * <pre>
     * {
     *   "x-text-content": "syntax = \"proto3\";\n\nmessage MyMessage {\n  string field = 1;\n}\n"
     * }
     * </pre>
     *
     * @param reference the reference URI to resolve (e.g., "http://example.com/schema.proto")
     * @param from the node containing the reference
     * @return a Node containing the resolved content, or null if the resolver cannot resolve the reference
     */
    public Node resolveRef(String reference, Node from);

}
