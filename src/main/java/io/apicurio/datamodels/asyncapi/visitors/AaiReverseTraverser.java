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

package io.apicurio.datamodels.asyncapi.visitors;

import io.apicurio.datamodels.asyncapi.models.AaiInfo;
import io.apicurio.datamodels.core.visitors.ReverseTraverser;

/**
 * A reverse traverse for the AsyncAPI data model.
 * @author eric.wittmann@gmail.com
 */
public class AaiReverseTraverser extends ReverseTraverser implements IAaiVisitor {

    /**
     * Constructor.
     * @param visitor
     */
    public AaiReverseTraverser(IAaiVisitor visitor) {
        super(visitor);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor#visitInfo(io.apicurio.datamodels.asyncapi.models.AaiInfo)
     */
    @Override
    public void visitInfo(AaiInfo node) {
        node.accept(this.visitor);
        this.traverse(node.parent());
    }

}
