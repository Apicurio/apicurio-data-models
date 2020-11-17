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

/**
 * @author eric.wittmann@gmail.com
 */
public enum Spec {

    OpenAPI("openapi", "Oai"), AsyncAPI("asyncapi", "Aai");

    String _package;
    String _prefix;

    /**
     * Constructor.
     */
    private Spec(String _package, String _prefix) {
        this._package = _package;
        this._prefix = _prefix;
    }

    public String getPackage() {
        return _package;
    }
    
    public String getPrefix() {
        return _prefix;
    }

}
