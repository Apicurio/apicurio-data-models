package io.apicurio.umg.pipe.concept;

import io.apicurio.umg.pipe.AbstractStage;

public class IndexSpecificationsStage extends AbstractStage {

    @Override
    protected void doProcess() {
        getState().getSpecifications().forEach(spec -> getState().getSpecIndex().index(spec));
    }

}
