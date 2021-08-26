/*
 * Copyright 2021 Red Hat Inc
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

package io.apicurio.datamodels.core.util;

import io.apicurio.datamodels.core.models.Document;

/**
 * @author eric.wittmann@gmail.com
 */
public class CloneUtil {

    /**
     * Clones the given document by serializing it to a JS object, and then re-parsing it.
     * @param source
     */
    public static Document cloneDocument(Document source) {
        Object jsObj = NodeUtil.writeNode(source);
        return DocumentUtil.readDocument(jsObj);
    }

}
