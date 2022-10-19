package io.apicurio.umg.pipe.java;

import io.apicurio.umg.pipe.AbstractStage;

import java.util.HashMap;

public class AddPrefixes extends AbstractStage {
    @Override
    protected void doProcess() {

        var prefixMap = new HashMap<String, String>();

        getState().getSpecIndex().getAllSpecifications().forEach(specificationModel -> {
            prefixMap.put(specificationModel.getNamespace(), specificationModel.getPrefix());
            specificationModel.getVersions().forEach(specificationVersion -> {
                prefixMap.put(specificationVersion.getNamespace(), specificationVersion.getPrefix());
            });
        });

        getState().getJavaIndex().getAllJavaEntitiesWithCopy().forEach(je -> {
            var prefix = prefixMap.get(je.get_package().getName());
            if (prefix != null) {
                je.setName(prefix + je.getName());
            }
        });
    }
}
