package io.apicurio.datamodels.core.validation;

import io.apicurio.datamodels.core.models.ValidationProblem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IValidationExtension {
    <T> CompletableFuture<List<ValidationProblem>> validate(T document);
}
