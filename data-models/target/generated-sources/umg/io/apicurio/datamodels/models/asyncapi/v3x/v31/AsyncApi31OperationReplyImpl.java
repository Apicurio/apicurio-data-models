package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReference;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi31OperationReplyImpl extends NodeImpl implements AsyncApi31OperationReply {

	private String $ref;
	private AsyncApiOperationReplyAddress address;
	private AsyncApiReference channel;
	private List<AsyncApiReference> messages;
	private Map<String, JsonNode> extensions;

	@Override
	public String get$ref() {
		return $ref;
	}

	@Override
	public void set$ref(String value) {
		this.$ref = value;
	}

	@Override
	public AsyncApiOperationReplyAddress getAddress() {
		return address;
	}

	@Override
	public void setAddress(AsyncApiOperationReplyAddress value) {
		this.address = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("address");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi31OperationReplyAddress createOperationReplyAddress() {
		AsyncApi31OperationReplyAddressImpl node = new AsyncApi31OperationReplyAddressImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApiReference getChannel() {
		return channel;
	}

	@Override
	public void setChannel(AsyncApiReference value) {
		this.channel = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("channel");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi31Reference createReference() {
		AsyncApi31ReferenceImpl node = new AsyncApi31ReferenceImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApiReference> getMessages() {
		return messages;
	}

	@Override
	public void addMessage(AsyncApiReference value) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		this.messages.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messages");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearMessages() {
		if (this.messages != null) {
			this.messages.clear();
		}
	}

	@Override
	public void removeMessage(AsyncApiReference value) {
		if (this.messages != null) {
			this.messages.remove(value);
		}
	}

	@Override
	public void insertMessage(AsyncApiReference value, int atIndex) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
			this.messages.add(value);
		} else {
			this.messages = DataModelUtil.insertListEntry(this.messages, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messages");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public Map<String, JsonNode> getExtensions() {
		return extensions;
	}

	@Override
	public void addExtension(String name, JsonNode value) {
		if (this.extensions == null) {
			this.extensions = new LinkedHashMap<>();
		}
		this.extensions.put(name, value);
	}

	@Override
	public void clearExtensions() {
		if (this.extensions != null) {
			this.extensions.clear();
		}
	}

	@Override
	public void removeExtension(String name) {
		if (this.extensions != null) {
			this.extensions.remove(name);
		}
	}

	@Override
	public void insertExtension(String name, JsonNode value, int atIndex) {
		if (this.extensions == null) {
			this.extensions = new LinkedHashMap<>();
			this.extensions.put(name, value);
		} else {
			this.extensions = DataModelUtil.insertMapEntry(this.extensions, name, value, atIndex);
		}
	}

	@Override
	public void accept(Visitor visitor) {
		AsyncApi31Visitor viz = (AsyncApi31Visitor) visitor;
		viz.visitOperationReply(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi31OperationReplyImpl();
	}
}