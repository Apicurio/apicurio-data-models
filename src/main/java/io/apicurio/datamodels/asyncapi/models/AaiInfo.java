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

import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.License;

/**
 * Models an AsyncAPI 'Info' node.
 * @author eric.wittmann@gmail.com
 */
public class AaiInfo extends Info {

    /**
     * @see io.apicurio.datamodels.core.models.common.Info#createContact()
     */
    @Override
    public Contact createContact() {
        Contact contact = new AaiContact();
        contact._ownerDocument = this.ownerDocument();
        contact._parent = this;
        return contact;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.Info#createLicense()
     */
    @Override
    public License createLicense() {
        License license = new AaiLicense();
        license._ownerDocument = this.ownerDocument();
        license._parent = this;
        return license;
    }
	
}
