package io.apicurio.umg.pipe;

import io.apicurio.umg.models.PackageModel;

import java.util.HashSet;

public class ExpandPackageModelsStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;
        var copy = new HashSet<>(state.getIndex().findPackages(""));
        copy.forEach(pkg -> {
            this.makePackageWithHierarchy(pkg);
        });
    }

    private void makePackageWithHierarchy(PackageModel pkg) {

        PackageModel _package = pkg;
        while(_package != state.getBasePackage()) {
            String packageName = _package.getName();
            String parentPackageName = this.parentpkg(packageName);
            PackageModel parentPackage = state.getIndex().lookupPackage(parentPackageName, (_t) -> {
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
