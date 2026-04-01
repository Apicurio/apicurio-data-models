package io.apicurio.datamodels.models.openapi.v2x.v20;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenApi20ResponseDefinitionsImpl extends NodeImpl implements OpenApi20ResponseDefinitions {

	private Map<String, OpenApiResponse> _items = new LinkedHashMap<>();

	@Override
	public OpenApiResponse getItem(String name) {
		return this._items.get(name);
	}

	@Override
	public List<OpenApiResponse> getItems() {
		List<OpenApiResponse> rval = new ArrayList<>();
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
	public void addItem(String name, OpenApiResponse item) {
		this._items.put(name, item);
		if (item != null) {
			((NodeImpl) item)._setParentPropertyName(null);
			((NodeImpl) item)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) item)._setMapPropertyName(name);
		}
	}

	@Override
	public void insertItem(String name, OpenApiResponse item, int atIndex) {
		this._items = DataModelUtil.insertMapEntry(this._items, name, item, atIndex);
		if (item != null) {
			((NodeImpl) item)._setParentPropertyName(null);
			((NodeImpl) item)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) item)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApiResponse removeItem(String name) {
		return this._items.remove(name);
	}

	@Override
	public void clearItems() {
		this._items.clear();
	}

	@Override
	public OpenApi20Response createResponse() {
		OpenApi20ResponseImpl node = new OpenApi20ResponseImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public void accept(Visitor visitor) {
		OpenApi20Visitor viz = (OpenApi20Visitor) visitor;
		viz.visitResponseDefinitions(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi20ResponseDefinitionsImpl();
	}
}