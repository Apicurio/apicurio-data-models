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

package io.apicurio.datamodels.core.models.common;

import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Models an Info node.
 * @author eric.wittmann@gmail.com
 */
public abstract class Info extends ExtensibleNode {

    public String title;
    public String description;
    public String termsOfService;
    public Contact contact;
    public License license;
    public String version;

    /**
     * Constructor.
     */
    public Info() {
    }
    
    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        visitor.visitInfo(this);
    }

    /**
     * Creates a Contact node.
     */
    public abstract Contact createContact();

    /**
     * Creates a License node.
     */
    public abstract License createLicense();

}
