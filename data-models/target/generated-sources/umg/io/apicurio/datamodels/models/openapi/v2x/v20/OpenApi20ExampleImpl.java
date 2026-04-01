package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenApi20ExampleImpl extends NodeImpl implements OpenApi20Example {

	private Map<String, JsonNode> _items = new LinkedHashMap<>();

	@Override
	public JsonNode getItem(String name) {
		return this._items.get(name);
	}

	@Override
	public List<JsonNode> getItems() {
		List<JsonNode> rval = new ArrayList<>();
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
	public void addItem(String name, JsonNode item) {
		this._items.put(name, item);
	}

	@Override
	public void insertItem(String name, JsonNode item, int atIndex) {
		this._items = DataModelUtil.insertMapEntry(this._items, name, item, atIndex);
	}

	@Override
	public JsonNode removeItem(String name) {
		return this._items.remove(name);
	}

	@Override
	public void clearItems() {
		this._items.clear();
	}

	@Override
	public void accept(Visitor visitor) {
		OpenApi20Visitor viz = (OpenApi20Visitor) visitor;
		viz.visitExample(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi20ExampleImpl();
	}
}