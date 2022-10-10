package io.apicurio.umg.pipe;

import io.apicurio.umg.models.java.PackageModel;

public class NormalizeModelsStage extends AbstractStage {
    @Override
    protected void doProcess() {
//
//        // Process every class model we've created thus far
//        Queue<ClassModel> modelsToProcess = new ConcurrentLinkedQueue<>();
//        modelsToProcess.addAll(getState().getModelIndex().findClasses(""));
//        Set<String> modelsProcessed = new HashSet<>();
//
//        // Keep working until we've processed every model (including any new models we
//        // might create during processing).
//        while (!modelsToProcess.isEmpty()) {
//            ClassModel classModel = modelsToProcess.remove();
//            if (modelsProcessed.contains(classModel.getFullyQualifiedName())) {
//                continue;
//            }
//
//            // Check if we need to create a parent class for this model in any parent scope
//            PackageModel ancestorPackageModel = classModel.getPackage().getParent();
//            while (ancestorPackageModel != null) {
//                if (needsParentClass(ancestorPackageModel, classModel.getName())) {
//                    ClassModel ancestorClass = ClassModel.builder().build();
//                    ancestorClass.setName(classModel.getName());
//                    ancestorClass.getParents().addAll(classModel.getParents()); // TODO Handle middle classes in the hierarchy
//                    ancestorClass.setPackage(ancestorPackageModel);
//                    ancestorClass.setAbstract(true);
//                    ancestorPackageModel.addClass(ancestorClass);
//                    modelsToProcess.add(ancestorClass);
//                    getState().getModelIndex().indexClass(ancestorClass);
//
//                    Collection<ClassModel> childClasses = getState().findChildClassesFor(ancestorClass);
//                    // Make the new parent class the actual parent of each child class
//                    childClasses.forEach(childClass -> {
//                        childClass.getParents().add(ancestorClass);
//                        // Skip processing this model if its turn comes up in the queue.
//                        modelsProcessed.add(childClass.getFullyQualifiedName());
//                    });
//                    // break out of loop - no need to search further up the hierarchy
//                    ancestorPackageModel = null;
//                } else {
//                    ancestorPackageModel = ancestorPackageModel.getParent();
//                }
//            }
//        }
    }


    /**
     * A class needs a parent class if there are multiple classes with the same name in the
     * package hierarchy.
     *
     * @param packageModel
     * @param className
     */
    private boolean needsParentClass(PackageModel packageModel, String className) {
        int count = 0;
        for (PackageModel childPackage : packageModel.getChildren().values()) {
            if (childPackage.containsClass(className)) {
                count++;
            }
        }
        return count > 1;
    }


}
