/*
 * Copyright 2020 Red Hat
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

package io.apicurio.datamodels.openapi.visitors.dereference;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;

import java.util.Map;

/**
 * Contains basic functionality for manipulation with references within a document.
 * <p>
 * Each schema may work with references in a different way, so an implementation
 * of this interface is provided for each {@link io.apicurio.datamodels.core.models.Document} type.
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public interface IReferenceManipulationStrategy {

    /**
     * Add the node to the document (wrapped) as a component/definition.
     * <p>
     * Given a node, if it can be represented as a {@link io.apicurio.datamodels.core.models.common.IDefinition},
     * it's wrapped into one and attached to the appropriate place in the document.
     * <p>
     * This new definition node is returned (the original itself is not attached),
     * together with a canonical reference to the new definition.
     * <p>
     * The reference is generated from the provided name. If a definition with the provided name
     * already exists, throw an {@link java.lang.IllegalArgumentException}.
     *
     * @param model     Target model to manipulate
     * @param name      Suggested definition name
     * @param component Node to be attached as a definition
     * @return new definition node with the new reference to it
     * @throws java.lang.IllegalArgumentException if there is a naming conflict. // TODO some better way?
     */
    ReferenceAndNode attachAsDefinition(Document model, String name, Node component);


    /**
     * Get a collection of local components/definitions, mapped by their local reference string.
     *
     * @param model
     * @return
     */
    Map<String, Node> getExistingLocalComponents(Document model);


    class ReferenceAndNode {
        private final String ref;
        private final Node node;

        /**
         * @param ref  nullable
         * @param node nullable
         */
        public ReferenceAndNode(String ref, Node node) {
            this.ref = ref;
            this.node = node;
        }

        public String getRef() {
            return ref;
        }

        public Node getNode() {
            return node;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ReferenceAndNode that = (ReferenceAndNode) o;

            if (ref != null ? !ref.equals(that.ref) : that.ref != null) return false;
            return node != null ? node.equals(that.node) : that.node == null;
        }

        @Override
        public int hashCode() {
            int result = ref != null ? ref.hashCode() : 0;
            result = 31 * result + (node != null ? node.hashCode() : 0);
            return result;
        }
    }
}
