package io.apicurio.datamodels.core.diff.ruleset;

import java.util.HashMap;
import java.util.Map;

import io.apicurio.datamodels.core.diff.change.BreakingChange;
import io.apicurio.datamodels.core.diff.change.Change;
import io.apicurio.datamodels.core.diff.change.DangerousChange;
import io.apicurio.datamodels.core.diff.DiffType;
import io.apicurio.datamodels.core.diff.change.NonBreakingChange;

public class OasDiffRuleset extends Ruleset {

    private Map<DiffType, Change> paths;
    private Map<DiffType, Change> pathItem;
    private Map<DiffType, Change> operation;
    private Map<DiffType, Change> requestBody;
    private Map<DiffType, Change> mediaType;

    public OasDiffRuleset() {
        this.paths = (new HashMap<DiffType, Change>()
        {
            {
                put(DiffType.PATH_ADDED, new NonBreakingChange("Path added"));
            }
            {
                put(DiffType.PATH_REMOVED, new BreakingChange("Path removed"));
            }
        });
        this.operation = (new HashMap<DiffType, Change>()
        {
            {
                put(DiffType.REQUEST_BODY_ADDED, new BreakingChange("Request body added"));
            }
            {
                put(DiffType.REQUEST_BODY_REMOVED, new BreakingChange("Request body removed"));
            }
            {
                put(DiffType.OPERATION_ID_ADDED, new NonBreakingChange("Operation ID added"));
            }
            {
                put(DiffType.OPERATION_ID_MODIFIED, new DangerousChange("Operation ID modified"));
            }
            {
                put(DiffType.OPERATION_ID_REMOVED, new DangerousChange("Operation ID removed"));
            }
        });
        this.requestBody = (new HashMap<DiffType, Change>()
        {
            {
                put(DiffType.REQUEST_BODY_REQUIRED_FIELD_ADDED, new BreakingChange("Request body has been set to required"));
            }
            {
                put(DiffType.REQUEST_BODY_REQUIRED_FIELD_REMOVED, new NonBreakingChange("Request body has been set to not required"));
            }
            {
                put(DiffType.REQUEST_BODY_MEDIA_TYPE_ADDED, new NonBreakingChange("Media type added to request body"));
            }
            {
                put(DiffType.REQUEST_BODY_MEDIA_TYPE_REMOVED, new BreakingChange("Media type removed from request body"));
            }
        });
        this.mediaType = (new HashMap<DiffType, Change>()
        {
            {
                put(DiffType.REQUEST_BODY_MEDIA_TYPE_ADDED, new NonBreakingChange("Media type added"));
            }
            {
                put(DiffType.REQUEST_BODY_MEDIA_TYPE_REMOVED, new BreakingChange("Media type added"));
            }
        });
        this.pathItem = new HashMap<DiffType, Change>()
        {
            {
                put(DiffType.PATH_OPERATION_ADDED, new NonBreakingChange("Path operation added"));
            }
            {
                put(DiffType.PATH_OPERATION_REMOVED, new BreakingChange("Path operation removed"));
            }
        };
    }

    public Map<DiffType, Change> getPathsRules() {
        return paths;
    }

    public Map<DiffType, Change> getPathItemRules() {
        return pathItem;
    }

    public Map<DiffType, Change> getOperationRules() {
        return operation;
    }

    public Map<DiffType, Change> getRequestBodyRules() {
        return requestBody;
    }

    public Map<DiffType, Change> getMediaTypeRules() {
        return mediaType;
    }
}
