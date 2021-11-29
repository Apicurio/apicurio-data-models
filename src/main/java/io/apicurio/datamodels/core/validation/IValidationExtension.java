package io.apicurio.datamodels.core.validation;

import io.apicurio.datamodels.core.models.ValidationProblem;

import java.util.List;
import def.js.Promise;

public interface IValidationExtension {
    <T> Promise<List<ValidationProblem>> validate(T document);
}
