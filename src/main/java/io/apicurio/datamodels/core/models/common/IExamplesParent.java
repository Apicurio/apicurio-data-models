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

package io.apicurio.datamodels.core.models.common;

import java.util.List;

/**
 * @author eric.wittmann@gmail.com
 */
public interface IExamplesParent {

    /**
     * Creates a child Example model.
     */
    public IExample createExample(String name);

    /**
     * Adds the Example to the map of examples.
     * @param example
     */
    public void addExample(IExample example);

    /**
     * Removes an Example and returns it.
     * @param name
     */
    public IExample removeExample(String name);
    
    /**
     * Restore a deleted example in its original position.
     * @param index
     * @param name
     * @param example
     */
    public void restoreExample(Integer index, String name, IExample example);

    /**
     * Gets a single example by name.
     * @param name
     */
    public IExample getExample(String name);

    /**
     * Gets all examples.
     */
    public List<IExample> getExamples();

    /**
     * Remove all examples.
     */
    public void clearExamples();
}
