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

package io.apicurio.datamodels.core.io;

import java.util.List;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.Node;

/**
 * Base class for all data model readers.  Provides some common reading capabilities.
 * @author eric.wittmann@gmail.com
 */
public abstract class DataModelReader<T extends Document> {
    
    protected void readExtensions(Object json, ExtensibleNode node) {
        List<String> keys = JsonCompat.keys(json);
        for (String key : keys) {
            if (key.startsWith(Constants.EXTENSION_PREFIX)) {
                Extension extension = node.createExtension();
                extension.name = key;
                extension.value = JsonCompat.consumePropertyObject(json, key);
                node.addExtension(key, extension);
            }
        }
    }

    /**
     * Reads all remaining properties.  Anything left is an "extra" (or unexpected) property.  These
     * are not extension properties - they are actually properties that SHOULD NOT have existed on
     * the node.  (all extension properties must start with "x-" and are consumed by "readExtensions".
     * @param jsData
     * @param model
     */
    protected void readExtraProperties(Object json, Node node) {
        List<String> keys = JsonCompat.keys(json);
        for (String key : keys) {
            Object value = JsonCompat.consumePropertyObject(json, key);
            node.addExtraProperty(key, value);
            LoggerCompat.warn("Found unexpected data model property: ", key);
        }
    }

    public abstract void readDocument(Object json, T node);
    
}
