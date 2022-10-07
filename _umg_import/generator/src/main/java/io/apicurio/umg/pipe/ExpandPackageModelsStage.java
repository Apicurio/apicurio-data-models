package io.apicurio.umg.pipe;

import java.util.HashSet;

import io.apicurio.umg.models.PackageModel;

public class ExpandPackageModelsStage extends AbstractStage {
    @Override
    protected void doProcess() {
        var copy = new HashSet<>(getState().getIndex().findPackages(""));
        copy.forEach(pkg -> {
            this.makePackageWithHierarchy(pkg);
        });
    }

    private void makePackageWithHierarchy(PackageModel pkg) {

        PackageModel _package = pkg;
        while(_package != getState().getBasePackage()) {
            String packageName = _package.getName();
            String parentPackageName = this.parentpkg(packageName);
            PackageModel parentPackage = getState().getIndex().lookupPackage(parentPackageName, (_t) -> {
                PackageModel model = PackageModel.builder().build();
                model.setName(parentPackageName);
                return model;
            });
            parentPackage.getChildren().put(packageName, _package);
            _package.setParent(parentPackage);
            _package = parentPackage;
        }
    }

    private String parentpkg(String packageName) {
        int idx = packageName.lastIndexOf(".");
        return packageName.substring(0, idx);
    }
}
