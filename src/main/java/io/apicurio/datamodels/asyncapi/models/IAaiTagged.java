package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.common.Tag;

import java.util.List;

public interface IAaiTagged {

    /**
     * Gets list of all tags
     */
    List<Tag> getTags();

    void deleteAllTags();
    
    /**
     * Gets the tag with given name
     */
    static Tag getTag(IAaiTagged node, String name) {
        for (Tag tag : node.getTags()) {
            if (NodeCompat.equals(tag.name, name)) {
                return tag;
            }
        }
        return null;
    }

    /**
     * Adds a tag
     */
    void addTag(AaiTag tag);
}
