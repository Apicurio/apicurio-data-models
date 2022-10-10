package io.apicurio.umg.pipe;

public class UnionTypeMapperStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getConceptIndex().findEntities("").forEach(cm -> {
            cm.getProperties().forEach((name, fm) -> {
                if(fm.getType().isUnion()) {
                    // TODO mapping of union types to language-specific types should be done in Java or Typescript specific generators
                    //fm.setType("object");
                }
            });
        });
    }
}
