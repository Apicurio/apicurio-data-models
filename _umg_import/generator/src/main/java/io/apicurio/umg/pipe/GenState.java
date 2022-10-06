package io.apicurio.umg.pipe;

import io.apicurio.umg.beans.beans.Specification;
import io.apicurio.umg.index.ModelIndex;
import io.apicurio.umg.index.SpecificationIndex;
import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.PackageModel;
import io.apicurio.umg.pipe.java.JavaTypeResolver;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class GenState {

    private ModelIndex index = new ModelIndex();

    private SpecificationIndex specIndex = new SpecificationIndex();

    private PackageModel basePackage;

    private ClassModel nodeClass;

    private ClassModel extensibleNodeClass;

    private List<Specification> specifications;

    private JavaTypeResolver javaTypeResolver = new JavaTypeResolver();

    /**
     * Returns all classes with the same name as the given parent class.  Does a search of
     * the package tree to find such classes.
     * @param parentClass
     */
    public Collection<ClassModel> findChildClassesFor(ClassModel parentClass) {
        String className = parentClass.getName();
        PackageModel parentPackage = parentClass.getPackage();
        List<ClassModel> childClasses = new ArrayList<>();
        for (PackageModel packageModel : parentPackage.getChildren().values()) {
            ClassModel childClass = findChildClassIn(packageModel, className);
            if (childClass != null) {
                childClasses.add(childClass);
            }
        }
        return childClasses;
    }

    /**
     * Finds a child class in the given package with the given name.
     * @param packageModel
     * @param className
     */
    public ClassModel findChildClassIn(PackageModel packageModel, String className) {
        if (packageModel.getClasses().containsKey(className)) {
            return packageModel.getClasses().get(className);
        }
        for (PackageModel childPackage : packageModel.getChildren().values()) {
            ClassModel classModel = findChildClassIn(childPackage, className);
            if (classModel != null) {
                return classModel;
            }
        }
        return null;
    }
}
