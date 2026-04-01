package io.apicurio.datamodels.models.asyncapi.v2x.v26;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.visitors.AsyncApi26Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi26ParametersImpl extends NodeImpl implements AsyncApi26Parameters {

	private Map<String, AsyncApiParameter> _items = new LinkedHashMap<>();

	@Override
	public AsyncApiParameter getItem(String name) {
		return this._items.get(name);
	}

	@Override
	public List<AsyncApiParameter> getItems() {
		List<AsyncApiParameter> rval = new ArrayList<>();
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
	public void addItem(String name, AsyncApiParameter item) {
		this._items.put(name, item);
		if (item != null) {
			((NodeImpl) item)._setParentPropertyName(null);
			((NodeImpl) item)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) item)._setMapPropertyName(name);
		}
	}

	@Override
	public void insertItem(String name, AsyncApiParameter item, int atIndex) {
		this._items = DataModelUtil.insertMapEntry(this._items, name, item, atIndex);
		if (item != null) {
			((NodeImpl) item)._setParentPropertyName(null);
			((NodeImpl) item)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) item)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApiParameter removeItem(String name) {
		return this._items.remove(name);
	}

	@Override
	public void clearItems() {
		this._items.clear();
	}

	@Override
	public AsyncApi26Parameter createParameter() {
		AsyncApi26ParameterImpl node = new AsyncApi26ParameterImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public void accept(Visitor visitor) {
		AsyncApi26Visitor viz = (AsyncApi26Visitor) visitor;
		viz.visitParameters(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi26ParametersImpl();
	}
}