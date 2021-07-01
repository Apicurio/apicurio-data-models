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

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author eric.wittmann@gmail.com
 */
public class ExtraPropertyDetectionVisitor extends CombinedAllNodeVisitor {
    
    public List<String> extraProperties = new ArrayList<>();

    public int getExtraPropertyCount() {
        return extraProperties.size();
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitNode(io.apicurio.datamodels.core.models.Node)
     */
    @Override
    protected void visitNode(Node node) {
        if (node.getExtraPropertyNames().size() > 0) {
            extraProperties.addAll(node.getExtraPropertyNames());
        }
    }
        
}
