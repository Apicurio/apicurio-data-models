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

package io.apicurio.datamodels.core.models;

/**
 * Marks a node that can be referenced (replaced by a reference),
 * e.g. <a href="http://spec.openapis.org/oas/v3.0.2#reference-object">OpenAPI 3 reference object</a> .
 *
 * Any class implementing this interface MUST have {@link io.apicurio.datamodels.core.models.Node} as it's supertype.
 * Conversely, any subclass of {@link io.apicurio.datamodels.core.models.Node} that can be referenced MUST implement this interface.
 *
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public interface IReferenceNode {

    /**
     * Return the reference string, if this {@link io.apicurio.datamodels.core.models.Node} is a reference object.
     *
     * @return null otherwise (this node is either empty or a proper node)
     */
    String getReference();

    void setReference(String reference);
}
