package io.apicurio.datamodels.asyncapi.models;

import java.util.LinkedList;
import java.util.List;

import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiMessage extends AaiMessageBase {

    /**
     * The message may be "composite".
     * In this case, nested messages are stored here.
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-operationobject-a-operation-object">AsyncAPI 2.0.0 spec</a>
     */
    public List<AaiMessage> oneOf;
    public boolean _isOneOfMessage = false;

    /**
     * Objects of `any` type are deserialized as follows:
     * In TypeScript, this field contains the raw JS object, in case of Java
     * it is a {@link com.fasterxml.jackson.databind.node.ObjectNode}.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#messageObject">AsyncAPI 2.0.0 spec</a>
     */
    public Object payload;
    public List<AaiMessageTrait> traits;

    /**
     * Constructor.
     * @param name
     */
    public AaiMessage(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiMessage(Node parent) {
        super(parent);
    }

    /**
     * Constructor.
     * @param parent
     * @param name
     */
    public AaiMessage(Node parent, String name) {
        super(parent, name);
    }

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAaiVisitor v = (IAaiVisitor) visitor;
        v.visitMessage(this);
    }

    /**
     * Add a oneOf message.
     * @param item
     */
    public void addOneOfMessage(AaiMessage item) {
        if (oneOf == null)
            oneOf = new LinkedList<>();
        oneOf.add(item);
    }

    /**
     * Delete a oneOf message.
     * @param item
     */
    public void deleteOneOfMessage(AaiMessage item) {
        if (oneOf == null)
            oneOf = new LinkedList<>();
        oneOf.remove(item);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.models.AaiMessageBase#addTag(io.apicurio.datamodels.asyncapi.models.AaiTag)
     */
    @Override
    public void addTag(AaiTag tag) {
        if (tags == null)
            tags = new LinkedList<>();
        tags.add(tag);
    }

    /**
     * @param traitModel
     */
    public void addTrait(AaiMessageTrait traitModel) {
        if (traits == null) {
            traits = new LinkedList<>();
        }
        traits.add(traitModel);
        
    }
}
