package io.apicurio.datamodels.models.asyncapi.v2x.v25;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.visitors.AsyncApi25Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.List;

public class AsyncApi25OneOfMessagesImpl extends NodeImpl implements AsyncApi25OneOfMessages {

	private List<AsyncApiMessage> oneOf;

	@Override
	public AsyncApi25Message createMessage() {
		AsyncApi25MessageImpl node = new AsyncApi25MessageImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApiMessage> getOneOf() {
		return oneOf;
	}

	@Override
	public void addOneOf(AsyncApiMessage value) {
		if (this.oneOf == null) {
			this.oneOf = new ArrayList<>();
		}
		this.oneOf.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("oneOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearOneOf() {
		if (this.oneOf != null) {
			this.oneOf.clear();
		}
	}

	@Override
	public void removeOneOf(AsyncApiMessage value) {
		if (this.oneOf != null) {
			this.oneOf.remove(value);
		}
	}

	@Override
	public void insertOneOf(AsyncApiMessage value, int atIndex) {
		if (this.oneOf == null) {
			this.oneOf = new ArrayList<>();
			this.oneOf.add(value);
		} else {
			this.oneOf = DataModelUtil.insertListEntry(this.oneOf, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("oneOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void accept(Visitor visitor) {
		AsyncApi25Visitor viz = (AsyncApi25Visitor) visitor;
		viz.visitOneOfMessages(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi25OneOfMessagesImpl();
	}
}