/*
 * Copyright 2022 Red Hat
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

package io.apicurio.datamodels.paths;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eric.wittmann@gmail.com
 */
public class NodePointerIoTestCase {

    private String name;
    private String pointer;
    private List<String> segments = new ArrayList<>();

    /**
     * Constructor.
     */
    public NodePointerIoTestCase() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the path
     */
    public String getPointer() {
        return pointer;
    }

    /**
     * @param path the path to set
     */
    public void setPointer(String path) {
        this.pointer = path;
    }

    /**
     * @return the segments
     */
    public List<String> getSegments() {
        return segments;
    }

    /**
     * @param segments the segments to set
     */
    public void setSegments(List<String> segments) {
        this.segments = segments;
    }

}
