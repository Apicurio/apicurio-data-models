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

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;

/**
 * Models an AsyncAPI root document.
 * @author eric.wittmann@gmail.com
 */
public class AaiDocument extends Document {
	
	public String asyncapi;
	public String id;
	public AaiInfo info;
	
	/**
     * Constructor.
     */
    public AaiDocument() {
    }

	/**
	 * Creates an {@link AaiInfo} child node.
	 */
    public AaiInfo createInfo() {
        AaiInfo info = new AaiInfo();
        info._parent = this;
        info._ownerDocument = this;
        return info;
    }

    /**
     * @see io.apicurio.datamodels.core.models.Document#getDocumentType()
     */
    @Override
    public DocumentType getDocumentType() {
        return DocumentType.asyncapi2;
    }

}
