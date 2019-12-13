/*
 * Copyright 2019 JBoss Inc
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

import io.apicurio.datamodels.core.models.Node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * The chain of reference resolvers.
 * 
 * @author eric.wittmann@gmail.com
 */
public class ReferenceResolverChain implements IReferenceResolver {
    
    private static final ReferenceResolverChain instance = new ReferenceResolverChain();
    public static ReferenceResolverChain getInstance() {
        return instance;
    }
    
    private final List<IReferenceResolver> resolvers = new LinkedList<>();
    
    /**
     * Constructor.
     */
    public ReferenceResolverChain() {
        addResolver(new ReferenceResolver());
    }
    
    /**
     * @return the resolvers
     */
    public List<IReferenceResolver> getResolvers() {
        return resolvers;
    }
    
    /**
     * @param resolver
     */
    public void addResolver(IReferenceResolver resolver) {
        resolvers.add(0, resolver);
    }

    @Override
    public Node resolveRef(String reference, Node from) {
        for (IReferenceResolver resolver : resolvers) {
            Node resolvedNode = resolver.resolveRef(reference, from);
            if(resolvedNode != null) {
                return resolvedNode;
            }
        }
        return null;
    }
}
