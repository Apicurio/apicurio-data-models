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

package io.apicurio.datamodels.asyncapi.io;

import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.models.AaiInfo;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelReader;

/**
 * A data model reader for the AsyncAPI data model.
 * @author eric.wittmann@gmail.com
 */
public class AaiDataModelReader extends DataModelReader<AaiDocument> {

    /**
     * @see io.apicurio.datamodels.core.io.DataModelReader#readDocument(java.lang.Object, io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void readDocument(Object json, AaiDocument node) {
        String asyncapi = JsonCompat.propertyString(json, Constants.PROP_ASYNCAPI);
        String id = JsonCompat.propertyString(json, Constants.PROP_ID);
        Object info = JsonCompat.property(json, Constants.PROP_INFO);
        
        node.asyncapi = asyncapi;
        node.id = id;
        if (info != null) {
            node.info = node.createInfo();
            this.readInfo(info, node.info);
        }
        this.readExtensions(json, node);
    }

    /**
     * Reads an info object into a data model instance (info node).
     * @param json
     * @param node
     */
    public void readInfo(Object json, AaiInfo node) {
        String title = JsonCompat.propertyString(json, Constants.PROP_TITLE);
        String version = JsonCompat.propertyString(json, Constants.PROP_VERSION);
        String description = JsonCompat.propertyString(json, Constants.PROP_DESCRIPTION);
        
        node.title = title;
        node.version = version;
        node.description = description;

        this.readExtensions(json, node);
    }
}
