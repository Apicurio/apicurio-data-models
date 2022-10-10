package io.apicurio.umg.pipe;

public class NormalizeVisitorsStage extends AbstractStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findVisitors("").stream().forEach(visitor -> {

        });
    }

}
