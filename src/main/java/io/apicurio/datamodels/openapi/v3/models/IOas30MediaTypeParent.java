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

package io.apicurio.datamodels.openapi.v3.models;

import java.util.List;

/**
 * All parents of {@link Oas30MediaType} must implement this.
 * @author eric.wittmann@gmail.com
 */
public interface IOas30MediaTypeParent {

    /**
     * Creates a media type.
     * @param name
     */
    public Oas30MediaType createMediaType(String name);

    /**
     * Adds a media type.
     * @param name
     * @param mediaType
     */
    public void addMediaType(String name, Oas30MediaType mediaType);

    /**
     * Gets a single media type by name.
     * @param name
     */
    public Oas30MediaType getMediaType(String name);

    /**
     * Removes a single media type and returns it.  This may return null or undefined if none found.
     * @param name
     */
    public Oas30MediaType removeMediaType(String name);

    /**
     * Gets a list of all media types.
     */
    public List<Oas30MediaType> getMediaTypes();

}
