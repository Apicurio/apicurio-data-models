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

package io.apicurio.datamodels.openapi.visitors.dereference;

/**
 * Encapsulates a reference string in OpenAPI/AsyncAPI schema,
 * for easier manipulation and parsing.
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Reference {


    private final String ref;

    private String abs;
    private String rel;

    /**
     * @param ref Encapsulated reference string. MUST NOT be null.
     */
    public Reference(String ref) {
        if (ref == null)
            throw new IllegalArgumentException();
        this.ref = ref;
        // TODO consider using java.net.URL
        String[] parts = ref.split("#");
        if (parts.length == 0) {
            abs = ref;
            rel = null; // TODO is this even valid?
        } else if (parts.length == 2) {
            abs = "".equals(parts[0]) ? null : parts[0];
            rel = "#" + parts[1];
        } else {
            throw new IllegalArgumentException("Wrong reference format.");
        }
    }

    /**
     * @return the original/full reference string.
     */
    public String getRef() {
        return ref;
    }

    public String getAbsPart() {
        return abs;
    }

    public String getRelPart() {
        return rel;
    }

    public boolean isRelative() {
        return abs == null;
    }

    /**
     * @return a `name` of the referenced component, as parsed from the reference string.
     * @throws java.lang.RuntimeException if the reference does not contain relative part
     */
    public String getName() {
        if (rel == null)
            throw new RuntimeException("No relative part in the reference.");
        String[] parts = rel.split("/");
        return parts[parts.length - 1];
    }

    /**
     * @param that other reference
     * @return a new reference with the absolute part copied form the other one,
     * and relative from this one
     */
    public Reference withAbsoluteFrom(Reference that) {
        if (that.getAbsPart() == null)
            throw new IllegalArgumentException();
        return new Reference(that.abs + rel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reference reference = (Reference) o;

        return ref.equals(reference.ref);
    }

    @Override
    public int hashCode() {
        return ref.hashCode();
    }
}
