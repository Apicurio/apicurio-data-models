package io.apicurio.datamodels.core.diff.ruleset;

import io.apicurio.datamodels.core.diff.ChangeSeverity;
import io.apicurio.datamodels.core.diff.DiffRule;
import io.apicurio.datamodels.core.diff.DiffType;

import java.util.HashMap;
import java.util.Map;

public class OasDiffRuleset extends Ruleset {

    private Map<DiffType, DiffRule> paths;
    private Map<DiffType, DiffRule> pathItem;
    private Map<DiffType, DiffRule> operation;
    private Map<DiffType, DiffRule> requestBody;
    private Map<DiffType, DiffRule> mediaType;

    public OasDiffRuleset() {
        this.paths = (new HashMap<DiffType, DiffRule>()
        {
            {
                put(DiffType.PATH_ADDED, new DiffRule(ChangeSeverity.NON_BREAKING, "Path added"));
            }
            {
                put(DiffType.PATH_REMOVED, new DiffRule(ChangeSeverity.BREAKING_CHANGE, "Path removed"));
            }
        });
        this.operation = (new HashMap<DiffType, DiffRule>()
        {
            // TODO: Move to pathItem rules
            {
                put(DiffType.PATH_OPERATION_ADDED, new DiffRule(ChangeSeverity.NON_BREAKING, "Path operation added"));
            }
            // TODO: Move to pathItem rules
            {
                put(DiffType.PATH_OPERATION_REMOVED, new DiffRule(ChangeSeverity.BREAKING_CHANGE, "Path operation removed"));
            }
            {
                put(DiffType.REQUEST_BODY_ADDED, new DiffRule(ChangeSeverity.BREAKING_CHANGE, "Request body added"));
            }
            {
                put(DiffType.REQUEST_BODY_REMOVED, new DiffRule(ChangeSeverity.BREAKING_CHANGE, "Request body removed"));
            }
            {
                put(DiffType.OPERATION_ID_ADDED, new DiffRule(ChangeSeverity.NON_BREAKING, "Operation ID added"));
            }
            {
                put(DiffType.OPERATION_ID_MODIFIED, new DiffRule(ChangeSeverity.DANGEROUS_CHANGE, "Operation ID modified"));
            }
            {
                put(DiffType.OPERATION_ID_REMOVED, new DiffRule(ChangeSeverity.DANGEROUS_CHANGE, "Operation ID removed"));
            }
        });
        this.requestBody = (new HashMap<DiffType, DiffRule>()
        {
            {
                put(DiffType.REQUEST_BODY_REQUIRED_FIELD_ADDED, new DiffRule(ChangeSeverity.BREAKING_CHANGE, "Request body has been set to required"));
            }
            {
                put(DiffType.REQUEST_BODY_REQUIRED_FIELD_REMOVED, new DiffRule(ChangeSeverity.NON_BREAKING, "Request body has been set to not required"));
            }
            {
                put(DiffType.REQUEST_BODY_MEDIA_TYPE_ADDED, new DiffRule(ChangeSeverity.NON_BREAKING, "Media type added to request body"));
            }
            {
                put(DiffType.REQUEST_BODY_MEDIA_TYPE_REMOVED, new DiffRule(ChangeSeverity.BREAKING_CHANGE, "Media type removed from request body"));
            }
        });
        this.mediaType = (new HashMap<DiffType, DiffRule>()
        {
            {
                put(DiffType.REQUEST_BODY_MEDIA_TYPE_ADDED, new DiffRule(ChangeSeverity.NON_BREAKING, "Media type added"));
            }
            {
                put(DiffType.REQUEST_BODY_MEDIA_TYPE_REMOVED, new DiffRule(ChangeSeverity.BREAKING_CHANGE, "Media type added"));
            }
        });
    }

    public Map<DiffType, DiffRule> getPathsRules() {
        return paths;
    }

    public void setPaths(Map<DiffType, DiffRule> paths) {
        this.paths = paths;
    }

    public Map<DiffType, DiffRule> getPathItemRules() {
        return pathItem;
    }

    public Map<DiffType, DiffRule> getOperationRules() {
        return operation;
    }

    public Map<DiffType, DiffRule> getRequestBodyRules() {
        return requestBody;
    }

    public Map<DiffType, DiffRule> getMediaTypeRules() {
        return mediaType;
    }
}
