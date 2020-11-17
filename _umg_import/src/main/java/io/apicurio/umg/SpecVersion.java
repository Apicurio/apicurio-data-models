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

package io.apicurio.umg;

import java.util.Objects;

/**
 * @author eric.wittmann@gmail.com
 */
public class SpecVersion {

    private final Spec spec;
    private final String version;
    
    /**
     * Constructor.
     */
    public SpecVersion(Spec spec, String version) {
        this.spec = spec;
        this.version = version;
    }

    /**
     * @return the spec
     */
    public Spec getSpec() {
        return spec;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(spec, version);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SpecVersion other = (SpecVersion) obj;
        return spec == other.spec && Objects.equals(version, other.version);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("{%s} %s", spec, version);
    }
    
    
    
}
