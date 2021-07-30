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

package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiMessageBindings;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author eric.wittmann@gmail.com
 */
public class Aai20MessageBindings extends AaiMessageBindings {

    /**
     * Constructor.
     */
    public Aai20MessageBindings() {
        super();
    }

    /**
     * Constructor.
     * @param parent
     */
    public Aai20MessageBindings(Node parent) {
        super(parent);
    }

}
