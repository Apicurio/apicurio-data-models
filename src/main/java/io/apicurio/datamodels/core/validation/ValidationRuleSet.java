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

package io.apicurio.datamodels.core.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.apicurio.datamodels.compat.ValidationCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.validation.rules.invalid.format.AaiInvalidApiIdRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.AaiInvalidChannelItemDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.AaiInvalidChannelPathRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.AaiInvalidCorrelationIdDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.AaiInvalidMessageDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.AaiInvalidMessageTraitDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.AaiInvalidOperationTraitDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.AaiInvalidSchemaDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidApiDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidContactEmailRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidContactUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidExternalDocsDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidLicenseUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidOAuthRefreshUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidOperationDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidParameterDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidSecuritySchemeDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidServerDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidServerUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidServerVariableDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidTagDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.InvalidTermsOfServiceUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidApiBasePathRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidApiHostRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidExampleDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidExternalDocsUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidHeaderDefaultValueRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidHeaderDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidLinkDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidOAuthAuthorizationUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidOAuthTokenUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidOpenIDConnectUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidOperationConsumesRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidOperationProducesRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidPathItemDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidRequestBodyDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidResponseDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidSchemaItemsDefaultValueRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidSecuritySchemeAuthUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidSecuritySchemeTokenUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.format.OasInvalidXmlNamespaceUrlRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasDuplicatePathSegmentRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasEmptyPathSegmentRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasIdenticalPathTemplateRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidCallbackDefinitionNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidExampleDefinitionNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidHeaderDefinitionNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidHttpResponseCodeRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidLinkDefinitionNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidParameterDefNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidPathSegmentRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidRequestBodyDefinitionNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidResponseDefNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidSchemaDefNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidScopeNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasInvalidSecuritySchemeNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasUnmatchedEncodingPropertyRule;
import io.apicurio.datamodels.core.validation.rules.invalid.name.OasUnmatchedExampleTypeRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.AaiInvalidChannelBindingsReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.AaiInvalidChannelItemReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.AaiInvalidCorrelationIdReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.AaiInvalidMessageBindingsReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.AaiInvalidMessageReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.AaiInvalidMessageTraitReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.AaiInvalidOperationBindingsReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.AaiInvalidOperationTraitReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.AaiInvalidServerBindingsReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.InvalidParameterReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.InvalidSchemaReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.InvalidSecurityRequirementNameRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.InvalidSecuritySchemeReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.OasInvalidCallbackReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.OasInvalidExampleReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.OasInvalidHeaderReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.OasInvalidLinkOperationReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.OasInvalidLinkReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.OasInvalidPathItemReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.OasInvalidRequestBodyReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.reference.OasInvalidResponseReferenceRule;
import io.apicurio.datamodels.core.validation.rules.invalid.type.OasInvalidSchemaArrayItemsRule;
import io.apicurio.datamodels.core.validation.rules.invalid.type.OasInvalidSchemaTypeValueRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.InvalidSecurityReqScopesRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasAllowReservedNotAllowedForParamRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasAllowReservedNotAllowedRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasEncodingStyleNotAllowedRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasExplodeNotAllowedRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasFormDataParamNotAllowedRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasInvalidApiConsumesMTRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasInvalidApiProducesMTRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasInvalidApiSchemeRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasInvalidEncodingForMPMTRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasInvalidHeaderStyleRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasInvalidHttpSecuritySchemeTypeRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasInvalidLinkOperationIdRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasInvalidOperationIdRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasInvalidOperationSchemeRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasMissingPathParamDefinitionRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasMissingResponseForOperationRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasOperationSummaryTooLongRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasPathParamNotFoundRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasRequiredParamWithDefaultValueRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasServerVarNotFoundInTemplateRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedArrayCollectionFormatRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedHeaderCollectionFormatRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedHeaderUsageRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedNumOfParamMTsRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedNumberOfHeaderMTsRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedParamAllowEmptyValueRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedParamCollectionFormatRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedParamMultiRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedRequestBodyRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedSecurityRequirementScopesRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedUsageOfBearerTokenRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedUsageOfDiscriminatorRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnexpectedXmlWrappingRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownApiKeyLocationRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownArrayCollectionFormatRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownArrayFormatRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownArrayTypeRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownCookieParamStyleRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownEncodingStyleRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownHeaderCollectionFormatRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownHeaderFormatRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownHeaderParamStyleRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownHeaderTypeRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownOauthFlowTypeRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownParamCollectionFormatRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownParamFormatRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownParamLocationRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownParamStyleRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownParamTypeRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownPathParamStyleRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownQueryParamStyleRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.OasUnknownSecuritySchemeTypeRule;
import io.apicurio.datamodels.core.validation.rules.invalid.value.SecurityRequirementScopesMustBeEmptyRule;
import io.apicurio.datamodels.core.validation.rules.mutex.OasBodyAndFormDataMutualExclusivityRule;
import io.apicurio.datamodels.core.validation.rules.mutex.OasExampleValueMutualExclusivityRule;
import io.apicurio.datamodels.core.validation.rules.mutex.OasHeaderExamplesMutualExclusivityRule;
import io.apicurio.datamodels.core.validation.rules.mutex.OasHeaderSchemaContentMutualExclusivityRule;
import io.apicurio.datamodels.core.validation.rules.mutex.OasLinkOperationRefMutualExclusivityRule;
import io.apicurio.datamodels.core.validation.rules.mutex.OasMediaTypeExamplesMutualExclusivityRule;
import io.apicurio.datamodels.core.validation.rules.mutex.OasParameterExamplesMutualExclusivityRule;
import io.apicurio.datamodels.core.validation.rules.mutex.OasParameterSchemaContentMutualExclusivityRule;
import io.apicurio.datamodels.core.validation.rules.other.OasBodyParameterUniquenessValidationRule;
import io.apicurio.datamodels.core.validation.rules.other.OasIgnoredContentTypeHeaderRule;
import io.apicurio.datamodels.core.validation.rules.other.OasIgnoredHeaderParameterRule;
import io.apicurio.datamodels.core.validation.rules.other.OasParameterUniquenessValidationRule;
import io.apicurio.datamodels.core.validation.rules.other.OasUnknownPropertyRule;
import io.apicurio.datamodels.core.validation.rules.other.OperationIdUniquenessValidationRule;
import io.apicurio.datamodels.core.validation.rules.other.SecurityRequirementUniquenessValidationRule;
import io.apicurio.datamodels.core.validation.rules.other.TagUniquenessValidationRule;
import io.apicurio.datamodels.core.validation.rules.required.AaiMissingApiChannelsRule;
import io.apicurio.datamodels.core.validation.rules.required.AaiMissingCorrelationIdRule;
import io.apicurio.datamodels.core.validation.rules.required.AaiMissingServerProtocolRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingApiKeySchemeParamLocationRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingApiKeySchemeParamNameRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingApiTitleRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingApiVersionRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingHttpSecuritySchemeTypeRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingLicenseNameRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingOAuthFlowAuthUrlRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingOAuthFlowRokenUrlRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingOAuthFlowScopesRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingOAuthSecuritySchemeFlowsRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingOpenIdConnectSecuritySchemeConnectUrlRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingOperationDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingOperationIdRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingOperationSummaryRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingSecuritySchemeTypeRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingServerTemplateUrlRule;
import io.apicurio.datamodels.core.validation.rules.required.MissingTagNameRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingApiInformationRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingApiPathsRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingBodyParameterSchemaRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingDiscriminatorPropertyNameRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingExternalDocumentationUrlRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingHeaderArrayInformationRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingHeaderTypeRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingItemsArrayInformationRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingItemsTypeRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingOAuthSchemeAuthUrlRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingOAuthSchemeFlowTypeRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingOAuthSchemeScopesRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingOAuthSchemeTokenUrlRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingOpenApiPropertyRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingOperationResponsesRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingOperationTagsRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingParameterArrayTypeRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingParameterLocationRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingParameterNameRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingParameterTypeRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingRequestBodyContentRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingResponseDefinitionDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingResponseDescriptionRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingSchemaArrayInformationRule;
import io.apicurio.datamodels.core.validation.rules.required.OasMissingServerVarDefaultValueRule;
import io.apicurio.datamodels.core.validation.rules.required.OasPathParamsMustBeRequiredRule;

/**
 * The full set of validation rules.
 * @author eric.wittmann@gmail.com
 */
public class ValidationRuleSet {
    
    private static final DocumentType aai20 = DocumentType.asyncapi2;
    private static final DocumentType oai20 = DocumentType.openapi2;
    private static final DocumentType oai30 = DocumentType.openapi3;
    
    public static ValidationRuleSet instance = new ValidationRuleSet();

    public static ValidationRuleMetaData md(String code, String name, String type, String entity,
            DocumentType[] versions, boolean specMandated, String messageTemplate, Class<?> ruleClass) {
        return new ValidationRuleMetaData(code, name, type, entity, versions, specMandated,
                messageTemplate, ruleClass);
    }

    private List<ValidationRuleMetaData> rules;

    /**
     * Constructor.
     */
    public ValidationRuleSet() {
        this.rules = new ArrayList<>();

        this.rules.add(md("UNKNOWN-001", "Unknown/Unexpected Property", "Unknown Property", "All", new DocumentType[] { oai20, oai30 }, true, "An unexpected property \"${'property'}\" was found.  Extension properties should begin with \"x-\".", OasUnknownPropertyRule.class));
        /** Uniqueness **/
        this.rules.add(md("TAG-003", "Duplicate Tag Definition", "Uniqueness", "Tag", new DocumentType[] { oai20, oai30, aai20 }, true, "Duplicate tag '${'tagName'}' found (every tag must have a unique name).", TagUniquenessValidationRule.class));
        this.rules.add(md("OP-003", "Duplicate Operation ID", "Uniqueness", "Operation", new DocumentType[] { oai20, oai30, aai20 }, true, "Duplicate operationId '${'operationId'}' found (operation IDs must be unique across all operations in the API).", OperationIdUniquenessValidationRule.class));
        this.rules.add(md("PAR-019", "Duplicate Parameter", "Uniqueness", "Parameter", new DocumentType[] { oai20, oai30 }, true, "Duplicate ${'paramIn'} parameter named '${'paramName'}' found (parameters must be unique by name and location).", OasParameterUniquenessValidationRule.class));
        this.rules.add(md("PAR-020", "Duplicate Body Parameter", "Uniqueness", "Parameter", new DocumentType[] { oai20 }, true, "Operation has multiple \"body\" parameters.", OasBodyParameterUniquenessValidationRule.class));
        this.rules.add(md("SREQ-005", "Duplicate Security Requirements", "Uniqueness", "Security Requirement", new DocumentType[] { oai20, oai30 }, true, "Duplicate Security Requirement '${'securityReq'}' in Security field", SecurityRequirementUniquenessValidationRule.class));
        /** Invalid Property Format **/
        this.rules.add(md("R-004", "Invalid API Host", "Invalid Property Format", "API", new DocumentType[] { oai20 }, true, "Host not properly formatted - only the host name (and optionally port) should be specified.", OasInvalidApiHostRule.class));
        this.rules.add(md("R-005", "Invalid API Base Path", "Invalid Property Format", "API", new DocumentType[] { oai20 }, true, "Base Path should being with a '/' character.", OasInvalidApiBasePathRule.class));
        this.rules.add(md("INF-003", "Invalid API Description", "Invalid Property Format", "Info", new DocumentType[] { oai20, oai30, aai20 }, true, "API description is an incorrect format.", InvalidApiDescriptionRule.class));
        this.rules.add(md("INF-004", "Invalid Terms of Service URL", "Invalid Property Format", "Info", new DocumentType[] { oai30, aai20 }, true, "Terms of Service URL is an incorrect format.", InvalidTermsOfServiceUrlRule.class));
        this.rules.add(md("CTC-001", "Invalid Contact URL", "Invalid Property Format", "Contact", new DocumentType[] { oai20, oai30, aai20 }, true, "Contact URL is an incorrect format.", InvalidContactUrlRule.class));
        this.rules.add(md("CTC-002", "Invalid Contact Email", "Invalid Property Format", "Contact", new DocumentType[] { oai20, oai30, aai20 }, true, "Contact Email is an incorrect format.", InvalidContactEmailRule.class));
        this.rules.add(md("LIC-002", "Invalid License URL", "Invalid Property Format", "License", new DocumentType[] { oai20, oai30, aai20 }, true, "License URL is an incorrect format.", InvalidLicenseUrlRule.class));
        this.rules.add(md("OP-002", "Invalid Operation Description", "Invalid Property Format", "Operation", new DocumentType[] { oai20, oai30, aai20 }, true, "Operation Description is an incorrect format.", InvalidOperationDescriptionRule.class));
        this.rules.add(md("OP-005", "Invalid Operation 'Consumes' Type", "Invalid Property Format", "Operation", new DocumentType[] { oai20 }, true, "Operation \"consumes\" must be a valid mime type.", OasInvalidOperationConsumesRule.class));
        this.rules.add(md("OP-006", "Invalid Operation 'Produces' Type", "Invalid Property Format", "Operation", new DocumentType[] { oai20 }, true, "Operation \"produces\" must be a valid mime type.", OasInvalidOperationProducesRule.class));
        this.rules.add(md("ED-002", "Invalid External Documentation Description", "Invalid Property Format", "External Documentation", new DocumentType[] { oai20, oai30, aai20 }, true, "External Docs Description is an incorrect format.", InvalidExternalDocsDescriptionRule.class));
        this.rules.add(md("ED-003", "Invalid External Documentation URL", "Invalid Property Format", "External Documentation", new DocumentType[] { oai20, oai30 }, true, "External Docs URL is an incorrect format.", OasInvalidExternalDocsUrlRule.class));
        this.rules.add(md("PAR-010", "Invalid Parameter Description", "Invalid Property Format", "Parameter", new DocumentType[] { oai20, oai30, aai20 }, true, "Parameter Description is an incorrect format.", InvalidParameterDescriptionRule.class));
        this.rules.add(md("IT-007", "Invalid Schema Items Default Value", "Invalid Property Format", "Shema Items", new DocumentType[] { oai20, oai30 }, true, "Schema Items default value does not conform to the correct type.", OasInvalidSchemaItemsDefaultValueRule.class));
        this.rules.add(md("HEAD-005", "Invalid Header Default Value", "Invalid Property Format", "Header Items", new DocumentType[] { oai20 }, true, "The \"default\" property must conform to the \"type\" of the items.", OasInvalidHeaderDefaultValueRule.class));
        this.rules.add(md("TAG-002", "Invalid Tag Description", "Invalid Property Format", "Tag", new DocumentType[] { oai20, oai30, aai20 }, true, "Tag Description is an incorrect format.", InvalidTagDescriptionRule.class));
        this.rules.add(md("SS-011", "Invalid Security Scheme Auth URL", "Invalid Property Format", "Security Scheme", new DocumentType[] { oai20 }, true, "Security Scheme Authorization URL is an incorrect format.", OasInvalidSecuritySchemeAuthUrlRule.class));
        this.rules.add(md("SS-012", "Invalid Security Scheme Token URL", "Invalid Property Format", "Security Scheme", new DocumentType[] { oai20 }, true, "Security Scheme Token URL is an incorrect format.", OasInvalidSecuritySchemeTokenUrlRule.class));
        this.rules.add(md("XML-001", "Invalid XML Namespace URL", "Invalid Property Format", "XML", new DocumentType[] { oai20, oai30 }, true, "XML Namespace URL is an incorrect format.", OasInvalidXmlNamespaceUrlRule.class));
        this.rules.add(md("RES-004", "Invalid Response Description", "Invalid Property Format", "Response", new DocumentType[] { oai30 }, true, "Response description is an incorrect format.", OasInvalidResponseDescriptionRule.class));
        this.rules.add(md("EX-002", "Invalid Example Description", "Invalid Property Format", "Example", new DocumentType[] { oai30 }, true, "Example Description is an incorrect format.", OasInvalidExampleDescriptionRule.class));
        this.rules.add(md("LINK-004", "Invalid Link Description", "Invalid Property Format", "Link", new DocumentType[] { oai30 }, true, "Link Description is an incorrect format.", OasInvalidLinkDescriptionRule.class));
        this.rules.add(md("FLOW-003", "Invalid OAuth Authorization URL", "Invalid Property Format", "Link", new DocumentType[] { oai30 }, true, "OAuth Authorization URL is an incorrect format.", OasInvalidOAuthAuthorizationUrlRule.class));
        this.rules.add(md("FLOW-004", "Invalid OAuth Token URL", "Invalid Property Format", "Link", new DocumentType[] { oai30 }, true, "OAuth Token URL is an incorrect format.", OasInvalidOAuthTokenUrlRule.class));
        this.rules.add(md("FLOW-005", "Invalid OAuth Refresh URL", "Invalid Property Format", "Link", new DocumentType[] { oai30, aai20 }, true, "OAuth Refresh URL is an incorrect format.", InvalidOAuthRefreshUrlRule.class));
        this.rules.add(md("PATH-008", "Invalid Path Item Description", "Invalid Property Format", "Path Item", new DocumentType[] { oai30 }, true, "Path Item Description is an incorrect format.", OasInvalidPathItemDescriptionRule.class));
        this.rules.add(md("RB-001", "Invalid Request Body Description", "Invalid Property Format", "Request Body", new DocumentType[] { oai30 }, true, "Request Body Description is an incorrect format.", OasInvalidRequestBodyDescriptionRule.class));
        this.rules.add(md("HEAD-009", "Invalid Header Description", "Invalid Property Format", "Header", new DocumentType[] { oai30 }, true, "Header Description is an incorrect format.", OasInvalidHeaderDescriptionRule.class));
        this.rules.add(md("SS-014", "Invalid OpenID Connect URL", "Invalid Property Format", "Security Scheme", new DocumentType[] { oai30 }, true, "OpenID Connect URL is an incorrect format.", OasInvalidOpenIDConnectUrlRule.class));
        this.rules.add(md("SS-015", "Invalid Security Scheme Description", "Invalid Property Format", "Security Scheme", new DocumentType[] { oai30, aai20 }, true, "Security Scheme Description is an incorrect format.", InvalidSecuritySchemeDescriptionRule.class));
        this.rules.add(md("SRV-003", "Invalid Server Description", "Invalid Property Format", "Server", new DocumentType[] { oai30, aai20 }, true, "Server Description is an incorrect format.", InvalidServerDescriptionRule.class));
        this.rules.add(md("SRV-002", "Invalid Server URL", "Invalid Property Format", "Server", new DocumentType[] { oai30, aai20 }, true, "Server URL is an incorrect format.", InvalidServerUrlRule.class));
        this.rules.add(md("SVAR-002", "Invalid Server Variable Description", "Invalid Property Format", "Server Variable", new DocumentType[] { oai30, aai20 }, true, "Server Variable Description is an incorrect format.", InvalidServerVariableDescriptionRule.class));
        this.rules.add(md("CHAN-001", "Invalid Channel Item Description", "Invalid Property Format", "Channel Item", new DocumentType[] { aai20 }, true, "Channel Item Description is an incorrect format.", AaiInvalidChannelItemDescriptionRule.class));
        this.rules.add(md("OTRAIT-001", "Invalid Operation Trait Description", "Invalid Property Format", "Operation Trait", new DocumentType[] { aai20 }, true, "Operation Trait Description is an incorrect format.", AaiInvalidOperationTraitDescriptionRule.class));
        this.rules.add(md("MSG-001", "Invalid Message Description", "Invalid Property Format", "Message", new DocumentType[] { aai20 }, true, "Message Description is an incorrect format.", AaiInvalidMessageDescriptionRule.class));
        this.rules.add(md("MTRAIT-001", "Invalid Message Trait Description", "Invalid Property Format", "Message Trait", new DocumentType[] { aai20 }, true, "Message Trait Description is an incorrect format.", AaiInvalidMessageTraitDescriptionRule.class));
        this.rules.add(md("SCH-006", "Invalid Schema Description", "Invalid Property Format", "Schema", new DocumentType[] { aai20 }, true, "Schema Description is an incorrect format.", AaiInvalidSchemaDescriptionRule.class));
        this.rules.add(md("CORRID-001", "Invalid Correlation ID Description", "Invalid Property Format", "Correlation ID", new DocumentType[] { aai20 }, true, "Correlation ID Description is an incorrect format.", AaiInvalidCorrelationIdDescriptionRule.class));
        this.rules.add(md("R-010", "Invalid API ID Format", "Invalid Property Format", "API", new DocumentType[] { aai20 }, true, "The API's \"id\" must be a valid unique identifier URI as defined by RFC 3986.  It is recommended to use a URN to globally and uniquely identify the application.", AaiInvalidApiIdRule.class));
        this.rules.add(md("CHAN-003", "Invalid Channel Path", "Invalid Property Format", "Channel Item", new DocumentType[] { aai20 }, true, "The channel path is not valid.  All channel paths must be in the form of a RFC 6570 URI template, but without query parameters or fragments.", AaiInvalidChannelPathRule.class));
        /** Invalid Property Name **/
        this.rules.add(md("PATH-006", "Empty Path Segment", "Invalid Property Name", "Path Item", new DocumentType[] { oai20, oai30 }, true, "Path template \"${'path'}\" contains one or more empty segment.", OasEmptyPathSegmentRule.class));
        this.rules.add(md("PATH-007", "Duplicate Path Segment", "Invalid Property Name", "Path Item", new DocumentType[] { oai20, oai30 }, true, "Path template \"${'path'}\" contains duplicate variable names (${'duplicates'}).", OasDuplicatePathSegmentRule.class));
        this.rules.add(md("PATH-005", "Invalid Path Segment", "Invalid Property Name", "Path Item", new DocumentType[] { oai20, oai30 }, true, "Path template \"${'path'}\" is not valid.", OasInvalidPathSegmentRule.class));
        this.rules.add(md("PATH-009", "Identical Path Template", "Invalid Property Name", "Path Item", new DocumentType[] { oai20, oai30 }, true, "Path template \"${'path'}\" is semantically identical to at least one other path.", OasIdenticalPathTemplateRule.class));
        this.rules.add(md("RES-003", "Invalid HTTP Response Code", "Invalid Property Name", "Response", new DocumentType[] { oai20, oai30 }, true, "\"${'statusCode'}\" is not a valid HTTP response status code.", OasInvalidHttpResponseCodeRule.class));
        this.rules.add(md("EX-001", "Unmatched Example Type", "Invalid Property Name", "Example", new DocumentType[] { oai20 }, true, "Example '${'contentType'}' must match one of the \"produces\" mime-types.", OasUnmatchedExampleTypeRule.class));
        this.rules.add(md("SDEF-001", "Invalid Schema Definition Name", "Invalid Property Name", "Schema Definition", new DocumentType[] { oai20, oai30 }, true, "Schema Definition Name is not valid.", OasInvalidSchemaDefNameRule.class));
        this.rules.add(md("PDEF-001", "Invalid Parameter Definition Name", "Invalid Property Name", "Parameter Definition", new DocumentType[] { oai20, oai30 }, true, "Parameter Definition Name is not valid.", OasInvalidParameterDefNameRule.class));
        this.rules.add(md("RDEF-001", "Invalid Response Definition Name", "Invalid Property Name", "Response Definition", new DocumentType[] { oai20, oai30 }, true, "Response Definition Name is not valid.", OasInvalidResponseDefNameRule.class));
        this.rules.add(md("SCPS-001", "Invalid Scope Name", "Invalid Scope Name", "Scopes", new DocumentType[] { oai20, oai30 }, true, "'${'scope'}' is not a valid scope name.", OasInvalidScopeNameRule.class));
        this.rules.add(md("SS-013", "Invalid Security Scheme Name", "Invalid Property Name", "Security Scheme", new DocumentType[] { oai20, oai30 }, true, "Security Scheme Name is not valid.", OasInvalidSecuritySchemeNameRule.class));
        this.rules.add(md("EDEF-001", "Invalid Example Definition Name", "Invalid Property Name", "Components", new DocumentType[] { oai30 }, true, "Example Definition Name is not valid.", OasInvalidExampleDefinitionNameRule.class));
        this.rules.add(md("RBDEF-001", "Invalid Request Body Definition Name", "Invalid Property Name", "Components", new DocumentType[] { oai30 }, true, "Request Body Definition Name is not valid.", OasInvalidRequestBodyDefinitionNameRule.class));
        this.rules.add(md("HDEF-001", "Invalid Header Definition Name", "Invalid Property Name", "Components", new DocumentType[] { oai30 }, true, "Header Definition Name is not valid.", OasInvalidHeaderDefinitionNameRule.class));
        this.rules.add(md("LDEF-001", "Invalid Link Definition Name", "Invalid Property Name", "Components", new DocumentType[] { oai30 }, true, "Link Definition Name is not valid.", OasInvalidLinkDefinitionNameRule.class));
        this.rules.add(md("CDEF-001", "Invalid Callback Definition Name", "Invalid Property Name", "Components", new DocumentType[] { oai30 }, true, "Callback Definition Name is not valid.", OasInvalidCallbackDefinitionNameRule.class));
        this.rules.add(md("ENC-006", "Unmatched Encoding Property", "Invalid Property Name", "Components", new DocumentType[] { oai30 }, true, "Encoding Property \"${'name'}\" not found in the associated schema.", OasUnmatchedEncodingPropertyRule.class));
        /** Invalid Property Value **/
        this.rules.add(md("R-006", "Invalid API Scheme", "Invalid Property Value", "API", new DocumentType[] { oai20 }, true, "API scheme \"${'scheme'}\" not allowed.  Must be one of: http, https, ws, wss", OasInvalidApiSchemeRule.class));
        this.rules.add(md("R-007", "Invalid 'Consumes' Mime-Type", "Invalid Property Value", "API", new DocumentType[] { oai20 }, true, "API \"consumes\" must be a valid mime-type.", OasInvalidApiConsumesMTRule.class));
        this.rules.add(md("R-008", "Invalid 'Produces' Mime-Type", "Invalid Property Value", "API", new DocumentType[] { oai20 }, true, "API \"produces\" must be a valid mime-type.", OasInvalidApiProducesMTRule.class));
        this.rules.add(md("OP-001", "Operation Summary Too Long", "Invalid Property Value", "Operation", new DocumentType[] { oai20, oai30 }, true, "Operation Summary should be less than 120 characters.", OasOperationSummaryTooLongRule.class));
        this.rules.add(md("OP-004", "Invalid Operation ID", "Invalid Property Value", "Operation", new DocumentType[] { oai20, oai30 }, true, "Operation ID is an invalid format.", OasInvalidOperationIdRule.class));
        this.rules.add(md("OP-010", "Invalid Operation Scheme", "Invalid Property Value", "Operation", new DocumentType[] { oai20 }, true, "Operation scheme \"${'scheme'}\" not allowed.  Must be one of: http, https, ws, wss", OasInvalidOperationSchemeRule.class));
        this.rules.add(md("PAR-007", "Path Parameter Not Found", "Invalid Property Value", "Parameter", new DocumentType[] { oai20, oai30 }, true, "Path Parameter \"${'name'}\" not found in path template.", OasPathParamNotFoundRule.class));
        this.rules.add(md("PAR-008", "Form Data Parameter Not Allowed", "Invalid Property Value", "Parameter", new DocumentType[] { oai20 }, true, "Form Data Parameters are only used in 'application/x-www-form-urlencoded' or 'multipart/form-data' requests.", OasFormDataParamNotAllowedRule.class));
        this.rules.add(md("PAR-009", "Unknown Parameter Location", "Invalid Property Value", "Parameter", new DocumentType[] { oai20, oai30 }, true, "Parameters can only be found in one of: ${'options'}", OasUnknownParamLocationRule.class));
        this.rules.add(md("PAR-011", "Unknown Parameter Type", "Invalid Property Value", "Parameter", new DocumentType[] { oai20 }, true, "Parameter types are limited to: string, number, integer, boolean, array, file", OasUnknownParamTypeRule.class));
        this.rules.add(md("PAR-012", "Unknown Parameter Format", "Invalid Property Value", "Parameter", new DocumentType[] { oai20 }, true, "Parameter Format must be one of: int32, int64, float, double, byte, binary, date, date-time, password", OasUnknownParamFormatRule.class));
        this.rules.add(md("PAR-013", "Unexpected Usage of Parameter 'allowEmptyValue'", "Invalid Property Value", "Parameter", new DocumentType[] { oai20, oai30 }, true, "Allow Empty Value is not allowed (only for ${'options'} params).", OasUnexpectedParamAllowEmptyValueRule.class));
        this.rules.add(md("PAR-014", "Unexpected Usage of Parameter 'collectionFormat'", "Invalid Property Value", "Parameter", new DocumentType[] { oai20 }, true, "The \"collectionFormat\" property is only allowed for 'array' type parameters.", OasUnexpectedParamCollectionFormatRule.class));
        this.rules.add(md("PAR-015", "Unknown Parameter Collection Format", "Invalid Property Value", "Parameter", new DocumentType[] { oai20 }, true, "Parameter Collection Format must be one of: csv, ssv, tsv, pipes, multi", OasUnknownParamCollectionFormatRule.class));
        this.rules.add(md("PAR-016", "Unexpected Parameter Usage of 'multi'", "Invalid Property Value", "Parameter", new DocumentType[] { oai20 }, true, "Parameter Collection Format can only be \"multi\" for Query and Form Data parameters.", OasUnexpectedParamMultiRule.class));
        this.rules.add(md("PAR-017", "Required Parameter With Default Value Not Allowed", "Invalid Property Value", "Parameter", new DocumentType[] { oai20 }, true, "Required Parameters can not have a default value.", OasRequiredParamWithDefaultValueRule.class));
        this.rules.add(md("IT-003", "Unknown Array Type", "Invalid Property Value", "Items", new DocumentType[] { oai20 }, true, "Schema Items Type must be one of: string, number, integer, boolean, array", OasUnknownArrayTypeRule.class));
        this.rules.add(md("IT-004", "Unknown Array Format", "Invalid Property Value", "Items", new DocumentType[] { oai20 }, true, "Schema Items Format must be one of: int32, int64, float, double, byte, binary, date, date-time, password", OasUnknownArrayFormatRule.class));
        this.rules.add(md("IT-005", "Unknown Array Collection Format", "Invalid Property Value", "Items", new DocumentType[] { oai20 }, true, "Schema Items Collection Format must be one of: csv, ssv, tsv, pipes", OasUnknownArrayCollectionFormatRule.class));
        this.rules.add(md("IT-006", "Unexpected Usage of Array 'collectionFormat'", "Invalid Property Value", "Items", new DocumentType[] { oai20 }, true, "Schema Items Collection Format is only allowed for Array style parameters.", OasUnexpectedArrayCollectionFormatRule.class));
        this.rules.add(md("HEAD-003", "Unknown Header Type", "Invalid Property Value", "Header", new DocumentType[] { oai20 }, true, "Header Type must be one of: string, number, integer, boolean, array", OasUnknownHeaderTypeRule.class));
        this.rules.add(md("HEAD-004", "Unknown Header Format", "Invalid Property Value", "Header", new DocumentType[] { oai20 }, true, "Header Format must be one of: int32, int64, float, double, byte, binary, date, date-time, password", OasUnknownHeaderFormatRule.class));
        this.rules.add(md("HEAD-006", "Unexpected Usage of Header 'collectionFormat'", "Invalid Property Value", "Header", new DocumentType[] { oai20 }, true, "Header Collection Format is only allowed for \"array\" type headers.", OasUnexpectedHeaderCollectionFormatRule.class));
        this.rules.add(md("HEAD-007", "Unknown Header Collection Format", "Invalid Property Value", "Header", new DocumentType[] { oai20 }, true, "Header Collection Format must be one of: csv, ssv, tsv, pipes", OasUnknownHeaderCollectionFormatRule.class));
        this.rules.add(md("XML-002", "Unexpected Usage of XML Wrapping", "Invalid Property Value", "XML", new DocumentType[] { oai20, oai30 }, true, "XML Wrapped elements can only be used for \"array\" properties.", OasUnexpectedXmlWrappingRule.class));
        this.rules.add(md("SS-008", "Unknown Security Scheme Type", "Invalid Property Value", "Security Scheme", new DocumentType[] { oai20, oai30 }, true, "Security Scheme Type must be one of: ${'options'}", OasUnknownSecuritySchemeTypeRule.class));
        this.rules.add(md("SS-009", "Unknown API Key Location", "Invalid Property Value", "Security Scheme", new DocumentType[] { oai20, oai30 }, true, "API Key Security Scheme must be located \"in\" one of: ${'options'}", OasUnknownApiKeyLocationRule.class));
        this.rules.add(md("SS-010", "Unknown OAuth Flow Type", "Invalid Property Value", "Security Scheme", new DocumentType[] { oai20 }, true, "OAuth Security Scheme \"flow\" must be one of: implicit, password, application, accessCode", OasUnknownOauthFlowTypeRule.class));
        this.rules.add(md("SREQ-002", "Security Requirement Scopes Must Be Empty", "Invalid Property Value", "Security Requirement", new DocumentType[] { oai20, oai30, aai20 }, true, "Security Requirement '${'sname'}' scopes must be an empty array because the referenced Security Definition not ${'options'}.", SecurityRequirementScopesMustBeEmptyRule.class));
        this.rules.add(md("SREQ-003", "Unexpected Security Requirement Scope(s)", "Invalid Property Value", "Security Requirement", new DocumentType[] { oai20 }, true, "Security Requirement '${'sname'}' scopes must be an array of values from the possible scopes defined by the referenced Security Definition.", OasUnexpectedSecurityRequirementScopesRule.class));
        this.rules.add(md("ENC-001", "Unexpected Usage of Headers (Multipart Media Type)", "Invalid Property Value", "Encoding", new DocumentType[] { oai30 }, true, "Headers are not allowed for \"${'name'}\" media types.", OasUnexpectedHeaderUsageRule.class));
        this.rules.add(md("ENC-002", "Encoding Style Not Allowed for Media Type", "Invalid Property Value", "Encoding", new DocumentType[] { oai30 }, true, "Encoding Style is not allowed for \"${'name'}\" media types.", OasEncodingStyleNotAllowedRule.class));
        this.rules.add(md("ENC-003", "'Explode' Not Allowed for Media Type", "Invalid Property Value", "Encoding", new DocumentType[] { oai30 }, true, "\"Explode\" is not allowed for \"${'name'}\" media types.", OasExplodeNotAllowedRule.class));
        this.rules.add(md("ENC-004", "'Allow Reserved' Not Allowed for Media Type", "Invalid Property Value", "Encoding", new DocumentType[] { oai30 }, true, "\"Allow Reserved\" is not allowed for \"${'name'}\" media types.", OasAllowReservedNotAllowedRule.class));
        this.rules.add(md("ENC-005", "Unknown Encoding Style", "Invalid Property Value", "Encoding", new DocumentType[] { oai30 }, true, "Encoding Style is an invalid value.", OasUnknownEncodingStyleRule.class));
        this.rules.add(md("HEAD-010", "InvalidHeaderStyle", "Invalid Property Value", "Header", new DocumentType[] { oai30 }, true, "Header Style must be \"simple\".", OasInvalidHeaderStyleRule.class));
        this.rules.add(md("HEAD-011", "Unexpected Number of Header Media Types", "Invalid Property Value", "Header", new DocumentType[] { oai30 }, true, "Header content cannot have multiple media types.", OasUnexpectedNumberOfHeaderMTsRule.class));
        this.rules.add(md("LINK-002", "Invalid Link OperationID Reference", "Invalid Reference", "Link", new DocumentType[] { oai30 }, true, "The Operation ID does not refer to an existing Operation.", OasInvalidLinkOperationIdRule.class));
        this.rules.add(md("MT-003", "Invalid Encoding For Multi-Part Media Type", "Invalid Property Value", "Media Type", new DocumentType[] { oai30 }, true, "Encoding is not allowed for \"${'name'}\" media types.", OasInvalidEncodingForMPMTRule.class));
        this.rules.add(md("OP-009", "Unexpected Request Body", "Invalid Property Value", "Operation", new DocumentType[] { oai30 }, true, "Request Body is not supported for ${'method'} operations.", OasUnexpectedRequestBodyRule.class));
        this.rules.add(md("OP-011", "Missing Path Parameter Definition", "Invalid Property Value", "Operation", new DocumentType[] { oai30 }, true, "No definition found for path variable \"${'param'}\" for path '${'path'}' and method '${'method'}'.", OasMissingPathParamDefinitionRule.class));
        this.rules.add(md("OP-013", "Missing Response for Operation", "Invalid Property Value", "Operation", new DocumentType[] { oai30 }, true, "Operation must have at least one Response.", OasMissingResponseForOperationRule.class));
        this.rules.add(md("PAR-022", "Unknown Parameter Style", "Invalid Property Value", "Parameter", new DocumentType[] { oai30 }, true, "Parameter Style must be one of: [\"matrix\", \"label\", \"form\", \"simple\", \"spaceDelimited\", \"pipeDelimited\", \"deepObject\"] (Found \"${'style'}\").", OasUnknownParamStyleRule.class));
        this.rules.add(md("PAR-023", "Unknown Query Parameter Style", "Invalid Property Value", "Parameter", new DocumentType[] { oai30 }, true, "Query Parameter Style must be one of: [\"form\", \"spaceDelimited\", \"pipeDelimited\", \"deepObject\"] (Found \"${'style'}\").", OasUnknownQueryParamStyleRule.class));
        this.rules.add(md("PAR-024", "Unknown Cookie Parameter Style", "Invalid Property Value", "Parameter", new DocumentType[] { oai30 }, true, "Cookie Parameter style must be \"form\". (Found \"${'style'}\")", OasUnknownCookieParamStyleRule.class));
        this.rules.add(md("PAR-025", "Unknown Header Parameter Style", "Invalid Property Value", "Parameter", new DocumentType[] { oai30 }, true, "Header Parameter Style must be \"simple\". (Found \"${'style'}\").", OasUnknownHeaderParamStyleRule.class));
        this.rules.add(md("PAR-027", "Unknown Path Parameter Style", "Invalid Property Value", "Parameter", new DocumentType[] { oai30 }, true, "Path Parameter Style must be one of: [\"matrix\", \"label\", \"simple\"]  (Found \"${'style'}\").", OasUnknownPathParamStyleRule.class));
        this.rules.add(md("PAR-028", "'Allow Reserved' Not Allowed for Param", "Invalid Property Value", "Parameter", new DocumentType[] { oai30 }, true, "Allow Reserved is only allowed for Query Parameters.", OasAllowReservedNotAllowedForParamRule.class));
        this.rules.add(md("PAR-029", "Unexpected Number of Parameter Media Types", "Invalid Property Value", "Parameter", new DocumentType[] { oai30 }, true, "Parameter content cannot have multiple media types.", OasUnexpectedNumOfParamMTsRule.class));
        this.rules.add(md("SCH-002", "Unexpected Usage of Discriminator", "Invalid Property Value", "Schema", new DocumentType[] { oai30 }, true, "Schema Discriminator is only allowed when using one of: [\"oneOf\", \"anyOf\", \"allOf\"]", OasUnexpectedUsageOfDiscriminatorRule.class));
        this.rules.add(md("SS-016", "Invalid HTTP Security Scheme Type", "Invalid Property Value", "Security Scheme", new DocumentType[] { oai30 }, true, "HTTP Security Scheme must be one of: [\"basic\", \"bearer\", \"digest\", \"hoba\", \"mutual\", \"negotiate\", \"oauth\", \"vapid\", \"scram-sha-1\", \"scram-sha-256\"] (Found: '${'scheme'}')", OasInvalidHttpSecuritySchemeTypeRule.class));
        this.rules.add(md("SS-017", "Unexpected Usage of 'bearerFormat'", "Invalid Property Value", "Security Scheme", new DocumentType[] { oai30 }, true, "Security Scheme \"Bearer Format\" only allowed for HTTP Bearer auth scheme.", OasUnexpectedUsageOfBearerTokenRule.class));
        this.rules.add(md("SREQ-004", "Invalid Security Requirement Scopes", "Invalid Property Value", "Security Requirement", new DocumentType[] { oai30, aai20 }, true, "Value (scopes) for Security Requirement \"${'name'}\" must be an array.", InvalidSecurityReqScopesRule.class));
        this.rules.add(md("SVAR-003", "Server Variable Not Found in Template", "Invalid Property Value", "XXX", new DocumentType[] { oai30 }, true, "Server Variable \"${'name'}\" is not found in the server url template.", OasServerVarNotFoundInTemplateRule.class));
        /** Invalid Reference **/
        this.rules.add(md("PAR-018", "Invalid Parameter Reference", "Invalid Reference", "Parameter", new DocumentType[] { oai20, oai30, aai20 }, true, "Parameter Reference must refer to a valid Parameter Definition.", InvalidParameterReferenceRule.class));
        this.rules.add(md("PATH-001", "Invalid Path Item Reference", "Invalid Reference", "Path Item", new DocumentType[] { oai20, oai30 }, true, "Path Item Reference must refer to a valid Path Item Definition.", OasInvalidPathItemReferenceRule.class));
        this.rules.add(md("RES-002", "Invalid Response Reference", "Invalid Reference", "Response", new DocumentType[] { oai20, oai30 }, true, "Response Reference must refer to a valid Response Definition.", OasInvalidResponseReferenceRule.class));
        this.rules.add(md("SCH-001", "Invalid Schema Reference", "Invalid Reference", "Schema", new DocumentType[] { oai20, oai30, aai20 }, true, "Schema Reference must refer to a valid Schema Definition.", InvalidSchemaReferenceRule.class));
        this.rules.add(md("SREQ-001", "Invalid Security Requirement Name", "Invalid Reference", "Security Requirement", new DocumentType[] { oai20, oai30, aai20 }, true, "Security Requirement '${'name'}' must refer to a valid Security Scheme.", InvalidSecurityRequirementNameRule.class));
        this.rules.add(md("SS-018", "Invalid Security Scheme Reference", "Invalid Reference", "Security Scheme", new DocumentType[] { oai30, aai20, aai20 }, true, "Security Scheme Reference must refer to a valid Security Scheme Definition.", InvalidSecuritySchemeReferenceRule.class));
        this.rules.add(md("CALL-001", "Invalid Callback Reference", "Invalid Reference", "Callback", new DocumentType[] { oai30 }, true, "Callback Reference must refer to a valid Callback Definition.", OasInvalidCallbackReferenceRule.class));
        this.rules.add(md("EX-003", "Invalid Example Reference", "Invalid Reference", "Example", new DocumentType[] { oai30 }, true, "Example Reference must refer to a valid Example Definition.", OasInvalidExampleReferenceRule.class));
        this.rules.add(md("HEAD-012", "Invalid Head Reference", "Invalid Reference", "Header", new DocumentType[] { oai30 }, true, "Header Reference must refer to a valid Header Definition.", OasInvalidHeaderReferenceRule.class));
        this.rules.add(md("LINK-005", "Invalid Link Reference", "Invalid Reference", "Link", new DocumentType[] { oai30 }, true, "Link Reference must refer to a valid Link Definition.", OasInvalidLinkReferenceRule.class));
        this.rules.add(md("LINK-003", "Invalid Link Operation Reference", "Invalid Reference", "Link", new DocumentType[] { oai30 }, true, "Link \"Operation Reference\" must refer to a valid Operation Definition.", OasInvalidLinkOperationReferenceRule.class));
        this.rules.add(md("RB-003", "Invalid Request Body Reference", "Invalid Reference", "Request Body", new DocumentType[] { oai30 }, true, "Request Body Reference must refer to a valid Request Body Definition.", OasInvalidRequestBodyReferenceRule.class));
        this.rules.add(md("CHAN-002", "Invalid Channel Item Reference", "Invalid Reference", "Channel Item", new DocumentType[] { aai20 }, true, "Channel Item Reference must refer to a valid Channel Item.", AaiInvalidChannelItemReferenceRule.class));
        this.rules.add(md("MSG-002", "Invalid Message Reference", "Invalid Reference", "Message", new DocumentType[] { aai20 }, true, "Message Reference must refer to a valid Message.", AaiInvalidMessageReferenceRule.class));
        this.rules.add(md("CORRID-002", "Invalid Correlation ID Reference", "Invalid Reference", "Correlation ID", new DocumentType[] { aai20 }, true, "Correlation ID Reference must refer to a valid Correlation ID.", AaiInvalidCorrelationIdReferenceRule.class));
        this.rules.add(md("OTRAIT-002", "Invalid Operation Trait Reference", "Invalid Reference", "Operation Trait", new DocumentType[] { aai20 }, true, "Operation Trait Reference must refer to a valid Operation Trait.", AaiInvalidOperationTraitReferenceRule.class));
        this.rules.add(md("MTRAIT-002", "Invalid Message Trait Reference", "Invalid Reference", "Message Trait", new DocumentType[] { aai20 }, true, "Message Trait Reference must refer to a valid Message Trait.", AaiInvalidMessageTraitReferenceRule.class));
        this.rules.add(md("SBIND-001", "Invalid Server Bindings Reference", "Invalid Reference", "Server Bindings", new DocumentType[] { aai20 }, true, "Server Bindings Reference must refer to a valid Server Bindings.", AaiInvalidServerBindingsReferenceRule.class));
        this.rules.add(md("CBIND-001", "Invalid Channel Bindings Reference", "Invalid Reference", "Channel Bindings", new DocumentType[] { aai20 }, true, "Channel Bindings Reference must refer to a valid Channel Bindings.", AaiInvalidChannelBindingsReferenceRule.class));
        this.rules.add(md("OBIND-001", "Invalid Operation Bindings Reference", "Invalid Reference", "Operation Bindings", new DocumentType[] { aai20 }, true, "Operation Bindings Reference must refer to a valid Operation Bindings.", AaiInvalidOperationBindingsReferenceRule.class));
        this.rules.add(md("MBIND-001", "Invalid Message Bindings Reference", "Invalid Reference", "Message Bindings", new DocumentType[] { aai20 }, true, "Message Bindings Reference must refer to a valid Message Bindings.", AaiInvalidMessageBindingsReferenceRule.class));
        /** Mutual Exclusivity **/
        this.rules.add(md("PATH-002", "Body and Form Data Params are Mutually Exclusive", "Mutual Exclusivity", "Operation", new DocumentType[] { oai20 }, true, "Operation may not have both Body and Form Data parameters.", OasBodyAndFormDataMutualExclusivityRule.class));
        this.rules.add(md("EX-004", "Example Value and External Value are Mutually Exclusive", "Mutual Exclusivity", "Example", new DocumentType[] { oai30 }, true, "Example \"Value\" and \"External Value\" are mutually exclusive.", OasExampleValueMutualExclusivityRule.class));
        this.rules.add(md("HEAD-013", "Header Example and Examples are Mutually Exclusive", "Mutual Exclusivity", "Header", new DocumentType[] { oai30 }, true, "Header \"Example\" and \"Examples\" are mutually exclusive.", OasHeaderExamplesMutualExclusivityRule.class));
        this.rules.add(md("HEAD-014", "Header Schema and Content are Mutually Exclusive", "Mutual Exclusivity", "Header", new DocumentType[] { oai30 }, true, "Header cannot have both a Schema and Content.", OasHeaderSchemaContentMutualExclusivityRule.class));
        this.rules.add(md("LINK-001", "Link Operation Ref and Operation ID are Mutually Exclusive", "Mutual Exclusivity", "Link", new DocumentType[] { oai30 }, true, "Link Operation Reference and Operation ID cannot both be used.", OasLinkOperationRefMutualExclusivityRule.class));
        this.rules.add(md("MT-001", "Media Type Example and Examples are Mutually Exclusive", "Mutual Exclusivity", "Media Type", new DocumentType[] { oai30 }, true, "Media Type \"Example\" and \"Examples\" are mutually exclusive.", OasMediaTypeExamplesMutualExclusivityRule.class));
        this.rules.add(md("PAR-030", "Parameter Schema and Content are Mutually Exclusive", "Mutual Exclusivity", "Parameter", new DocumentType[] { oai30 }, true, "Parameter cannot have both a Schema and Content.", OasParameterSchemaContentMutualExclusivityRule.class));
        this.rules.add(md("PAR-031", "Parameter Example and Examples are Mutually Exclusive", "Mutual Exclusivity", "Parameter", new DocumentType[] { oai30 }, true, "Parameter \"Example\" and \"Examples\" are mutually exclusive.", OasParameterExamplesMutualExclusivityRule.class));
        /** Required Property **/
        this.rules.add(md("R-001", "Missing OpenAPI Property", "Required Property", "API", new DocumentType[] { oai20, oai30 }, true, "API is missing the 'openapi' property.", OasMissingOpenApiPropertyRule.class));
        this.rules.add(md("R-002", "Missing API Information", "Required Property", "API", new DocumentType[] { oai20, oai30, aai20 }, true, "API is missing the 'info' property.", OasMissingApiInformationRule.class));
        this.rules.add(md("R-003", "Missing API Paths", "Required Property", "API", new DocumentType[] { oai20, oai30 }, true, "API is missing the 'paths' property.", OasMissingApiPathsRule.class));
        this.rules.add(md("R-009", "Missing API Channels", "Required Property", "API", new DocumentType[] { aai20 }, true, "API is missing the 'channels' property.", AaiMissingApiChannelsRule.class));
        this.rules.add(md("INF-001", "Missing API Title", "Required Property", "Info", new DocumentType[] { oai20, oai30, aai20 }, true, "API is missing a title.", MissingApiTitleRule.class));
        this.rules.add(md("INF-002", "Missing API Version", "Required Property", "Info", new DocumentType[] { oai20, oai30, aai20 }, true, "API is missing a version.", MissingApiVersionRule.class));
        this.rules.add(md("LIC-001", "Missing License Name", "Required Property", "License", new DocumentType[] { oai20, oai30, aai20 }, true, "License is missing a name.", MissingLicenseNameRule.class));
        this.rules.add(md("OP-007", "Missing Operation Responses", "Required Property", "Operation", new DocumentType[] { oai20, oai30 }, true, "Operation must have at least one response.", OasMissingOperationResponsesRule.class));
        this.rules.add(md("OP-008", "Missing Operation ID", "Required Property", "Operation", new DocumentType[] { oai20, oai30, aai20 }, false, "Operation is missing a operation id.", MissingOperationIdRule.class));
        this.rules.add(md("OP-014", "Missing Operation Summary", "Required Property", "Operation", new DocumentType[] { oai20, oai30, aai20 }, false, "Operation is missing a summary.", MissingOperationSummaryRule.class));
        this.rules.add(md("OP-015", "Missing Operation Description", "Required Property", "Operation", new DocumentType[] { oai20, oai30, aai20 }, false, "Operation is missing a description.", MissingOperationDescriptionRule.class));
        this.rules.add(md("OP-016", "Missing Operation Tags", "Required Property", "Operation", new DocumentType[] { oai20, oai30 }, false, "Operation must have at least one tag.", OasMissingOperationTagsRule.class));
        this.rules.add(md("ED-001", "Missing External Documentation URL", "Required Property", "External Documentation", new DocumentType[] { oai20, oai30 }, true, "External Documentation is missing a URL.", OasMissingExternalDocumentationUrlRule.class));
        this.rules.add(md("PAR-001", "Missing Parameter Name", "Required Property", "Parameter", new DocumentType[] { oai20, oai30 }, true, "Parameter is missing a name.", OasMissingParameterNameRule.class));
        this.rules.add(md("PAR-002", "Missing Parameter Location", "Required Property", "Parameter", new DocumentType[] { oai20, oai30 }, true, "Parameter is missing a location (Query, Header, etc).", OasMissingParameterLocationRule.class));
        this.rules.add(md("PAR-003", "Path Parameters Must Be Required", "Required Property", "Parameter", new DocumentType[] { oai20, oai30 }, true, "Path Parameter \"${'name'}\" must be marked as required.", OasPathParamsMustBeRequiredRule.class));
        this.rules.add(md("PAR-004", "Missing Body Parameter Schema", "Required Property", "Parameter", new DocumentType[] { oai20 }, true, "Body Parameters must have a schema defined.", OasMissingBodyParameterSchemaRule.class));
        this.rules.add(md("PAR-005", "Missing Parameter Type", "Required Property", "Parameter", new DocumentType[] { oai20 }, true, "Parameter '${'name'}' is missing a type.", OasMissingParameterTypeRule.class));
        this.rules.add(md("PAR-006", "Missing Parameter Array Type", "Required Property", "Parameter", new DocumentType[] { oai20 }, true, "Parameter '${'name'}' marked as array but no array type provided.", OasMissingParameterArrayTypeRule.class));
        this.rules.add(md("IT-001", "Missing Items Type", "Required Property", "Items", new DocumentType[] { oai20 }, true, "Type information is missing for array items.", OasMissingItemsTypeRule.class));
        this.rules.add(md("IT-002", "Missing Items Array Information", "Required Property", "Items", new DocumentType[] { oai20 }, true, "Type information missing for array items.", OasMissingItemsArrayInformationRule.class));
        this.rules.add(md("RES-001", "Missing Response Description", "Required Property", "Response", new DocumentType[] { oai20, oai30 }, true, "Response (code ${'statusCode'}) is missing a description.", OasMissingResponseDescriptionRule.class));
        this.rules.add(md("RDEF-002", "Missing Response Definition Description", "Required Property", "Response Definition", new DocumentType[] { oai20, oai30 }, true, "Response Definition '${'name'}' is missing a description.", OasMissingResponseDefinitionDescriptionRule.class));
        this.rules.add(md("HEAD-001", "Missing Header Type", "Required Property", "Header", new DocumentType[] { oai20 }, true, "Header is missing type information.", OasMissingHeaderTypeRule.class));
        this.rules.add(md("HEAD-002", "Missing Header Array Information", "Required Property", "Header", new DocumentType[] { oai20 }, true, "Header is missing array type information.", OasMissingHeaderArrayInformationRule.class));
        this.rules.add(md("SCH-005", "Missing Schema Array Information", "Required Property", "Schema", new DocumentType[] { oai20, oai30 }, true, "Schema is missing array type information.", OasMissingSchemaArrayInformationRule.class));
        this.rules.add(md("TAG-001", "Missing Tag Name", "Required Property", "Tag", new DocumentType[] { oai20, oai30, aai20 }, true, "Tag is missing a name.", MissingTagNameRule.class));
        this.rules.add(md("SS-001", "Missing Security Scheme Type", "Required Property", "Security Scheme", new DocumentType[] { oai20, oai30, aai20 }, true, "Security Scheme is missing a type.", MissingSecuritySchemeTypeRule.class));
        this.rules.add(md("SS-002", "Missing API-Key Scheme Parameter Name", "Required Property", "Security Scheme", new DocumentType[] { oai20, oai30, aai20 }, true, "API Key Security Scheme is missing a parameter name (e.g. name of a header or query param).", MissingApiKeySchemeParamNameRule.class));
        this.rules.add(md("SS-003", "Missing API-Key Scheme Parameter Location", "Required Property", "Security Scheme", new DocumentType[] { oai20, oai30, aai20 }, true, "API Key Security Scheme must describe where the Key can be found (e.g. header, query param, etc).", MissingApiKeySchemeParamLocationRule.class));
        this.rules.add(md("SS-004", "Missing OAuth Scheme Flow Type", "Required Property", "Security Scheme", new DocumentType[] { oai20 }, true, "OAuth Security Scheme is missing a flow type.", OasMissingOAuthSchemeFlowTypeRule.class));
        this.rules.add(md("SS-005", "Missing OAuth Scheme Auth URL", "Required Property", "Security Scheme", new DocumentType[] { oai20 }, true, "OAuth Security Scheme is missing an Authorization URL.", OasMissingOAuthSchemeAuthUrlRule.class));
        this.rules.add(md("SS-006", "Missing OAuth Scheme Token URL", "Required Property", "Security Scheme", new DocumentType[] { oai20 }, true, "OAuth Security Scheme is missing a Token URL.", OasMissingOAuthSchemeTokenUrlRule.class));
        this.rules.add(md("SS-007", "Missing OAuth Scheme Scopes", "Required Property", "Security Scheme", new DocumentType[] { oai20 }, true, "OAuth Security Scheme is missing defined scopes.", OasMissingOAuthSchemeScopesRule.class));
        this.rules.add(md("DISC-001", "Missing a Discriminator Property Name", "Required Property", "Discriminator", new DocumentType[] { oai30 }, true, "Discriminator must indicate a property (by name).", OasMissingDiscriminatorPropertyNameRule.class));
        this.rules.add(md("FLOW-006", "Missing OAuth Flow Scopes", "Required Property", "OAuth Flow", new DocumentType[] { oai30, aai20 }, true, "OAuth Flow is missing defined scopes.", MissingOAuthFlowScopesRule.class));
        this.rules.add(md("FLOW-001", "Missing OAuth Flow Authorization URL", "Required Property", "OAuth Flow", new DocumentType[] { oai30, aai20 }, true, "${'flowType'} OAuth Flow is missing an Authorization URL.", MissingOAuthFlowAuthUrlRule.class));
        this.rules.add(md("FLOW-002", "Missing OAuth Flow Token URL", "Required Property", "OAuth Flow", new DocumentType[] { oai30, aai20 }, true, "${'flowType'} OAuth Flow is missing a Token URL.", MissingOAuthFlowRokenUrlRule.class));
        this.rules.add(md("RB-002", "Missing Request Body Content", "Required Property", "Request Body", new DocumentType[] { oai30 }, true, "Request Body content is missing.", OasMissingRequestBodyContentRule.class));
        this.rules.add(md("SRV-001", "Missing Server Template URL", "Required Property", "Server", new DocumentType[] { oai30, aai20 }, true, "Server is missing a template URL.", MissingServerTemplateUrlRule.class));
        this.rules.add(md("SRV-004", "Missing Server Protocol", "Required Property", "Server", new DocumentType[] { aai20 }, true, "Server is missing a protocol.", AaiMissingServerProtocolRule.class));
        this.rules.add(md("SS-019", "Missing HTTP Security Scheme Style", "Required Property", "Security Scheme", new DocumentType[] { oai30, aai20 }, true, "HTTP Security Scheme is missing a scheme (Basic, Digest, etc).", MissingHttpSecuritySchemeTypeRule.class));
        this.rules.add(md("SS-020", "Missing OAuth Security Scheme Flows", "Required Property", "Security Scheme", new DocumentType[] { oai30, aai20 }, true, "OAuth Security Scheme does not define any OAuth flows.", MissingOAuthSecuritySchemeFlowsRule.class));
        this.rules.add(md("SS-021", "Missing OID Connect Security Scheme Connect URL", "Required Property", "Security Scheme", new DocumentType[] { oai30, aai20 }, true, "OpenID Connect Security Scheme is missing a Connect URL.", MissingOpenIdConnectSecuritySchemeConnectUrlRule.class));
        this.rules.add(md("SVAR-001", "Missing Server Variable Default Value", "Required Property", "Server Variable", new DocumentType[] { oai30 }, true, "Server Variable \"${'name'}\" is missing a default value.", OasMissingServerVarDefaultValueRule.class));
        this.rules.add(md("CID-001", "Missing Correlation ID Location", "Required Property", "Correlation ID", new DocumentType[] { aai20 }, true, "Correlation ID is missing a Location", AaiMissingCorrelationIdRule.class));

        /** Ignored Property **/
        this.rules.add(md("HEAD-008", "Ignored Content-Type Header", "Ignored Property", "Header", new DocumentType[] { oai30 }, true, "The \"Content-Type\" header will be ignored.", OasIgnoredContentTypeHeaderRule.class));
        this.rules.add(md("PAR-021", "Ignored Header Parameter", "Ignored Property", "Parameter", new DocumentType[] { oai30 }, true, "The \"${'name'}\" header parameter will be ignored.", OasIgnoredHeaderParameterRule.class));
        /** Invalid Property Type **/
        this.rules.add(md("SCH-003", "Invalid Schema Type Value", "Invalid Property Type", "Schema", new DocumentType[] { oai30 }, true, "Schema type value of \"${'type'}\" is not allowed.  Must be one of: [${'allowedTypes'}]", OasInvalidSchemaTypeValueRule.class));
        this.rules.add(md("SCH-004", "Invalid Schema Array Items", "Invalid Property Type", "Schema", new DocumentType[] { oai30 }, true, "Schema items must be present only for schemas of type 'array'.", OasInvalidSchemaArrayItemsRule.class));
        
        this.validateRuleData();
    }

    /**
     * Verify that there are no duplicate codes in the set of rules.
     */
    private void validateRuleData() {
        Set<String> codes = new HashSet<>();
        Set<String> names = new HashSet<>();
        
        for (ValidationRuleMetaData rule : this.rules) {
            if (codes.contains(rule.code)) {
                throw new RuntimeException("Duplicate rule code found: " + rule.code);
            } else {
                codes.add(rule.code);
            }
            if (names.contains(rule.name)) {
                throw new RuntimeException("Duplicate rule name found: " + rule.name);
            } else {
                names.add(rule.name);
            }
        }
    }

    /**
     * Gets all of the registered rules.
     */
    public List<ValidationRuleMetaData> getAllRules() {
        return this.rules;
    }

    /**
     * Gets the actual rule instances (visitors) that should be applied to the given document.
     * @param document
     */
    public List<ValidationRule> getRulesFor(Document document) {
        List<ValidationRule> rval = new ArrayList<>();
        DocumentType type = document.getDocumentType();
        for (ValidationRuleMetaData rule : this.rules) {
            if (rule.appliesTo(type)) {
                ValidationRule rc = ValidationCompat.instantiate(rule);
                rval.add(rc);
            }
        }
        return rval;
    }


}
