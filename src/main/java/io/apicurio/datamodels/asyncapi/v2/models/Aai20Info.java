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

package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiInfo;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Info;

/**
 * Models an AsyncAPI 'Info' node.
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20Info extends AaiInfo {

    /**
     * Constructor.
     */
    public Aai20Info() {
    }

    public Aai20Info(Node parent) {
        super(parent);
    }

    /**
     * @see Info#createContact()
     */
    @Override
    public Aai20Contact createContact() {
        return new Aai20Contact(this);
    }

    /**
     * @see Info#createLicense()
     */
    @Override
    public Aai20License createLicense() {
        return new Aai20License(this);
    }
	
}
