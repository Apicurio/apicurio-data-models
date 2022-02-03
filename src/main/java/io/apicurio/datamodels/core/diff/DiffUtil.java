package io.apicurio.datamodels.core.diff;

public class DiffUtil {
    public static boolean isNodeAdded(Object original, Object updated) {
        return (original == null && updated != null);
    }

    public static boolean isNodeRemoved(Object original, Object updated) {
        return (original != null && updated == null);
    }
}
