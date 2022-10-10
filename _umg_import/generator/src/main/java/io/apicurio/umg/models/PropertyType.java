package io.apicurio.umg.models;

import lombok.Builder;
import lombok.Data;

/**
 * Models the type of a property.
 */
@Builder
@Data
public class PropertyType {

    public static PropertyType create(String type) {
        PropertyType rval = PropertyType.builder().build();
        if (type.startsWith("[")) {
            rval.setList(true);
            rval.setName(type.substring(1, type.length() - 1));
        } else if (type.startsWith("{")) {
            rval.setMap(true);
            rval.setName(type.substring(1, type.length() - 1));
        } else if (type.contains("|")) {
            rval.setUnion(true);
            rval.setName(type);
        } else {
            rval.setName(type);
        }

        switch (rval.getName())  {
            case "string":
            case "number":
            case "integer":
            case "object":
            case "boolean":
                rval.setEntity(false);
                rval.setSimple(true);
                break;
            default:
                rval.setEntity(true);
                rval.setSimple(false);
        }

        return rval;
    }

    private String name;
    private boolean list;
    private boolean map;
    private boolean union;
    private boolean simple;
    private boolean entity;

}
