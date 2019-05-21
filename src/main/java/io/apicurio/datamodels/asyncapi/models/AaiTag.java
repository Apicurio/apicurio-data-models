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

package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Tag;

import static java.util.Objects.requireNonNull;

/**
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiTag extends Tag {

    /**
     * Constructor.
     */
    public AaiTag(Node parent) {
        requireNonNull(parent);
        this._parent = parent;
        this._ownerDocument = parent.ownerDocument();
    }
}
