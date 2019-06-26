package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiHeaderItem;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;


/**
 * Represents `Schema Object | Reference Object` type.
 *
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20HeaderItem extends AaiHeaderItem {


    public Aai20HeaderItem(Node parent) {
        super(parent);
    }

    public Aai20HeaderItem(Node parent, String name) {
        super(parent, name);
    }

    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitHeaderItem(this);
    }
}
