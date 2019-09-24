package io.apicurio.datamodels.asyncapi.models;

import java.util.LinkedList;

import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiMessageTrait extends AaiMessageBase implements IAaiTrait {

    /**
     * Constructor.
     */
    public AaiMessageTrait(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiMessageTrait(Node parent) {
        super(parent);
    }

    /**
     * Constructor.
     * @param parent
     * @param name
     */
    public AaiMessageTrait(Node parent, String name) {
        super(parent, name);
    }
    
    /**
     * @see io.apicurio.datamodels.asyncapi.models.IAaiTrait#getTraitType()
     */
    @Override
    public AaiTraitType getTraitType() {
        return AaiTraitType.message;
    }

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAaiVisitor v = (IAaiVisitor) visitor;
        v.visitMessageTrait(this);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.models.AaiMessageBase#addTag(io.apicurio.datamodels.asyncapi.models.AaiTag)
     */
    @Override
    public void addTag(AaiTag tag) {
        if(tags == null)
            tags = new LinkedList<>();
        tags.add(tag);
    }

}
