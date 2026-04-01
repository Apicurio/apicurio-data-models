package io.apicurio.datamodels.models.asyncapi.v2x.v24;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServer;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.visitors.AsyncApi24Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi24ServersImpl extends NodeImpl implements AsyncApi24Servers {

	private Map<String, AsyncApiServer> _items = new LinkedHashMap<>();

	@Override
	public AsyncApiServer getItem(String name) {
		return this._items.get(name);
	}

	@Override
	public List<AsyncApiServer> getItems() {
		List<AsyncApiServer> rval = new ArrayList<>();
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
	public void addItem(String name, AsyncApiServer item) {
		this._items.put(name, item);
		if (item != null) {
			((NodeImpl) item)._setParentPropertyName(null);
			((NodeImpl) item)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) item)._setMapPropertyName(name);
		}
	}

	@Override
	public void insertItem(String name, AsyncApiServer item, int atIndex) {
		this._items = DataModelUtil.insertMapEntry(this._items, name, item, atIndex);
		if (item != null) {
			((NodeImpl) item)._setParentPropertyName(null);
			((NodeImpl) item)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) item)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApiServer removeItem(String name) {
		return this._items.remove(name);
	}

	@Override
	public void clearItems() {
		this._items.clear();
	}

	@Override
	public AsyncApi24Server createServer() {
		AsyncApi24ServerImpl node = new AsyncApi24ServerImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public void accept(Visitor visitor) {
		AsyncApi24Visitor viz = (AsyncApi24Visitor) visitor;
		viz.visitServers(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi24ServersImpl();
	}
}