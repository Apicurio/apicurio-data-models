package io.apicurio.umg.pipe;

public class UnionTypeMapperStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getIndex().findClasses("").forEach(cm -> {
            cm.getFields().forEach((name, fm) -> {
                if(fm.getType().contains("|")) {
                    fm.setType("object");
                }
            });
        });
    }
}
