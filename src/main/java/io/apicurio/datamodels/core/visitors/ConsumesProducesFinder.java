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

package io.apicurio.datamodels.core.visitors;

import java.util.List;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Operation;
import io.apicurio.datamodels.openapi.v2.visitors.Oas20VisitorAdapter;

/**
 * Finds the first path item.
 * @author eric.wittmann@gmail.com
 */
public class ConsumesProducesFinder extends Oas20VisitorAdapter {
    
    public List<String> consumes;
    public List<String> produces;
    
    /**
     * @see io.apicurio.datamodels.core.visitors.VisitorAdapter#visitDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void visitDocument(Document node) {
        Oas20Document doc = (Oas20Document) node;
        if (!NodeCompat.isNullOrUndefined(doc.consumes)) {
            this.consumes = doc.consumes;
        }
        if (!NodeCompat.isNullOrUndefined(doc.produces)) {
            this.produces = doc.produces;
        }
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.VisitorAdapter#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        consumes = ((Oas20Operation) node).consumes;
        produces = ((Oas20Operation) node).produces;
    }
    
}