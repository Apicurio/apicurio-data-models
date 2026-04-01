package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xChannel;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi31ChannelsImpl extends NodeImpl implements AsyncApi31Channels {

	private Map<String, AsyncApi3xChannel> _items = new LinkedHashMap<>();

	@Override
	public AsyncApi3xChannel getItem(String name) {
		return this._items.get(name);
	}

	@Override
	public List<AsyncApi3xChannel> getItems() {
		List<AsyncApi3xChannel> rval = new ArrayList<>();
		rval.addAll(this._items.values());
		return rval;
	}

	@Override
	public List<String> getItemNames() {
		List<String> rval = new ArrayList<>();
		rval.addAll(this._items.keySet());
		return rval;
	}

	@Override
	public void addItem(String name, AsyncApi3xChannel item) {
		this._items.put(name, item);
		if (item != null) {
			((NodeImpl) item)._setParentPropertyName(null);
			((NodeImpl) item)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) item)._setMapPropertyName(name);
		}
	}

	@Override
	public void insertItem(String name, AsyncApi3xChannel item, int atIndex) {
		this._items = DataModelUtil.insertMapEntry(this._items, name, item, atIndex);
		if (item != null) {
			((NodeImpl) item)._setParentPropertyName(null);
			((NodeImpl) item)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) item)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi3xChannel removeItem(String name) {
		return this._items.remove(name);
	}

	@Override
	public void clearItems() {
		this._items.clear();
	}

	@Override
	public AsyncApi31Channel createChannel() {
		AsyncApi31ChannelImpl node = new AsyncApi31ChannelImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public void accept(Visitor visitor) {
		AsyncApi31Visitor viz = (AsyncApi31Visitor) visitor;
		viz.visitChannels(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi31ChannelsImpl();
	}
}