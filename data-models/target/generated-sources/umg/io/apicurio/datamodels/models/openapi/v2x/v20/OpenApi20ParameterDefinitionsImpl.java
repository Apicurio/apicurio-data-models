package io.apicurio.datamodels.models.openapi.v2x.v20;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenApi20ParameterDefinitionsImpl extends NodeImpl implements OpenApi20ParameterDefinitions {

	private Map<String, OpenApiParameter> _items = new LinkedHashMap<>();

	@Override
	public OpenApiParameter getItem(String name) {
		return this._items.get(name);
	}

	@Override
	public List<OpenApiParameter> getItems() {
		List<OpenApiParameter> rval = new ArrayList<>();
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
	public void addItem(String name, OpenApiParameter item) {
		this._items.put(name, item);
		if (item != null) {
			((NodeImpl) item)._setParentPropertyName(null);
			((NodeImpl) item)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) item)._setMapPropertyName(name);
		}
	}

	@Override
	public void insertItem(String name, OpenApiParameter item, int atIndex) {
		this._items = DataModelUtil.insertMapEntry(this._items, name, item, atIndex);
		if (item != null) {
			((NodeImpl) item)._setParentPropertyName(null);
			((NodeImpl) item)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) item)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApiParameter removeItem(String name) {
		return this._items.remove(name);
	}

	@Override
	public void clearItems() {
		this._items.clear();
	}

	@Override
	public OpenApi20Parameter createParameter() {
		OpenApi20ParameterImpl node = new OpenApi20ParameterImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public void accept(Visitor visitor) {
		OpenApi20Visitor viz = (OpenApi20Visitor) visitor;
		viz.visitParameterDefinitions(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi20ParameterDefinitionsImpl();
	}
}