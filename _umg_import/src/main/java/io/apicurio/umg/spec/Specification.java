/*
 * Copyright 2020 JBoss Inc
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

package io.apicurio.umg.spec;

import java.net.URI;
import java.util.List;

import io.apicurio.umg.Spec;
import io.apicurio.umg.SpecVersion;

/**
 * @author eric.wittmann@gmail.com
 */
public class Specification {
    
    private Spec specification;
    private String version;
    private List<String> versions;
    private String name;
    private String prefix;
    private URI url;
    private List<Entity> entities;
    
    /**
     * Constructor.
     */
    public Specification() {
    }

    public SpecVersion specVersion() {
        return new SpecVersion(this.getSpecification(), this.getVersion());
    }

    /**
     * @return the specification
     */
    public Spec getSpecification() {
        return specification;
    }

    /**
     * @param specification the specification to set
     */
    public void setSpecification(Spec specification) {
        this.specification = specification;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the versions
     */
    public List<String> getVersions() {
        return versions;
    }

    /**
     * @param versions the versions to set
     */
    public void setVersions(List<String> versions) {
        this.versions = versions;
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
     * @return the url
     */
    public URI getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(URI url) {
        this.url = url;
    }

    /**
     * @return the entities
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * @param entities the entities to set
     */
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
