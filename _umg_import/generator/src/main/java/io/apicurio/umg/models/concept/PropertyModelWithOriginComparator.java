package io.apicurio.umg.models.concept;

import java.util.Comparator;

public class PropertyModelWithOriginComparator implements Comparator<PropertyModelWithOrigin> {

    @Override
    public int compare(PropertyModelWithOrigin property1, PropertyModelWithOrigin property2) {
        String name1 = property1.getProperty().getName();
        String name2 = property2.getProperty().getName();
        if (name1.equals(name2)) {
            return 0;
        }
        // Always put the "*" property at the end of any list of properties.
        if (name1.equals("*")) {
            return 1;
        } else if (name2.equals("*")) {
            return -1;
        }
        // Next to last (after "*" properties) should be any regex properties.
        if (name1.startsWith("/")) {
            return 1;
        } else if (name2.startsWith("/")) {
            return -1;
        }
        return name1.compareTo(name2);
    }

}
