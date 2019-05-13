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

package io.apicurio.datamodels.core.validation.rules;

import io.apicurio.datamodels.combined.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * Finds the first path item.
 * @author eric.wittmann@gmail.com
 */
public class PathItemFinder extends CombinedVisitorAdapter {
    
    public OasPathItem found;
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasVisitorAdapter#visitPathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void visitPathItem(OasPathItem node) {
        if (found == null) {
            this.found = node;
        }
    }
    
    /**
     * Returns true if an operation was found.
     */
    public boolean isFound() {
        return this.found != null;
    }
    
}