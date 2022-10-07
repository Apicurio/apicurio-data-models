package io.apicurio.umg.pipe;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.FieldModel;

public class NormalizePropertiesStage extends AbstractStage {
    @Override
    protected void doProcess() {
//
//        List<ClassModel> abstractClasses = getState().getModelIndex().findClasses("").stream().filter(model -> model.isAbstract()).collect(Collectors.toList());
//        int changesMade;
//        do {
//            changesMade = 0;
//            for (ClassModel parentClass : abstractClasses) {
//                // Get all direct children of this parent class.
//                Collection<ClassModel> childClasses = getState().findChildClassesFor(parentClass);
//                // Get a collection of all fields for all
//                Set<FieldModel> allFields = new HashSet<>();
//                childClasses.forEach(child -> allFields.addAll(child.getFields().values()));
//
//                // Filter the full list of fields - only keep the fields that exist in *all* children.
//                List<FieldModel> fieldsToPullup = allFields.stream()
//                        .filter(field -> childClasses.stream().map(c -> c.getFields().containsKey(field.getName())).reduce(true, (sub, element) -> sub && element))
//                        .collect(Collectors.toList());
//
//                // Now pull up each of the fields in the above list
//                fieldsToPullup.forEach(field -> {
//                    parentClass.addField(field);
//                    childClasses.forEach(childClass -> childClass.getFields().remove(field.getName()));
//                });
//
//                // Did we find any fields to pull up?  If yes, increment "changes made".  We're going to keep
//                // going through our model hierarchy until we've pulled up all the shared fields we can.
//                changesMade += fieldsToPullup.size();
//            }
//        } while (changesMade > 0);
    }
}
