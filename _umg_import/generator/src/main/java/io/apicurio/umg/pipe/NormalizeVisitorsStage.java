package io.apicurio.umg.pipe;

public class NormalizeVisitorsStage extends AbstractStage {

    @Override
    protected void doProcess() {
        getState().getModelIndex().findVisitors("").stream().forEach(visitor -> {

        });
    }

}
