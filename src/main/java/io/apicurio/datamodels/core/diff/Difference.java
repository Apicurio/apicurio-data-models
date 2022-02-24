package io.apicurio.datamodels.core.diff;

import io.apicurio.datamodels.core.models.NodePath;

public class Difference {
    private final DiffType code;
    private final ChangeType changeType;
    private final NodePath nodePath;
    private final String message;

    public Difference(DiffType code, ChangeType changeType, NodePath path, String message) {
        this.code = code;
        this.changeType = changeType;
        this.message = message;
        this.nodePath = path;
    }

    public NodePath getPath() {
        return nodePath;
    }

    public DiffType getCode() {
        return code;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public String getMessage() {
        return message;
    }
}
