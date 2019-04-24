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
import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.io.DataModelWriter;
import io.apicurio.datamodels.core.models.Document;

/**
 * @author eric.wittmann@gmail.com
 */
public class AaiDataModelWriter extends DataModelWriter implements IAaiVisitor {

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeDocument(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void writeDocument(Document node, Object json) {
        AaiDocument doc = (AaiDocument) node;
        JsonCompat.setPropertyString(json, Constants.PROP_ASYNCAPI, doc.asyncapi);
        JsonCompat.setPropertyString(json, Constants.PROP_ID, doc.id);
        writeExtraProperties(json, node);
    }
    
    /**
     * @see io.apicurio.asyncapi.core.visitors.IAaiNodeVisitor#visitInfo(io.apicurio.datamodels.asyncapi.models.AaiInfo)
     */
    @Override
    public void visitInfo(AaiInfo node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_TITLE, node.title);
        JsonCompat.setPropertyString(json, Constants.PROP_VERSION, node.version);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setProperty(parent, Constants.PROP_INFO, json);
        writeExtraProperties(json, node);

        this.updateIndex(node, json);
    }

}
