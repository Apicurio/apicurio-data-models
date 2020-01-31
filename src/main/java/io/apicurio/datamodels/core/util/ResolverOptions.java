/*
 * Copyright 2020 Red Hat
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

import java.util.HashSet;
import java.util.Set;

/**
 * @author eric.wittmann@gmail.com
 */
public class ResolverOptions {
    
    public static ResolverOptions of(ResolverOptionsType ... options) {
        ResolverOptions rval = new ResolverOptions();
        if (options != null) {
            for (ResolverOptionsType option : options) {
                rval.add(option);
            }
        }
        return rval;
    }
    
    private Set<ResolverOptionsType> options = new HashSet<>();
    
    /**
     * Constructor.
     */
    public ResolverOptions() {
    }
    
    public void add(ResolverOptionsType option) {
        this.options.add(option);
    }
    
    public boolean contains(ResolverOptionsType option) {
        return this.options.contains(option);
    }
    

}
