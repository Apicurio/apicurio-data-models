package io.apicurio.umg.models.concept;

import java.util.Comparator;

public class PropertyModelComparator implements Comparator<PropertyModel> {

    @Override
    public int compare(PropertyModel property1, PropertyModel property2) {
        String name1 = property1.getName();
        String name2 = property2.getName();
        if (name1.equals("*")) {
            return 1;
        }
        return name1.compareTo(name2);
    }

}
