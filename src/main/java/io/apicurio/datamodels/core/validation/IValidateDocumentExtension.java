/*
 * Copyright 2021 Red Hat
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
package io.apicurio.datamodels.core.validation;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.ValidationProblem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Interface which defines a way to extend the basic document validation rules with additional rules.
 * Validation problems can be custom, or adapted from other validation libraries such as <a href="https://stoplight.io/open-source/spectral/"></a>
 */
public interface IValidateDocumentExtension {
    /**
     * This method should implement the validation of the document and return a list of validation problems.
     * @param document The document which will be validated.
     * @return The list of validation problems
     */
    CompletableFuture<List<ValidationProblem>> validateDocument(Node document);
}
