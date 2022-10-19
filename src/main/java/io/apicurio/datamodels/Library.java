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

package io.apicurio.datamodels;

import io.apicurio.datamodels.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.openapi.v30.OpenApi30DocumentImpl;

/**
 * The most common entry points into using the data models library.  Provides convenience methods
 * for performing common actions such as i/o, visiting, and validation.
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Library {

    public static Document testLibrary() {
        Document doc = new OpenApi30DocumentImpl();
        OpenApi30Document oa31Doc = ((OpenApi30Document) doc);
        oa31Doc.setOpenapi("3.1.0");
        return oa31Doc;
    }

}
