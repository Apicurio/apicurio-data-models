package io.apicurio.datamodels.models.asyncapi.v2x.v22;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.visitors.AsyncApi22Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi22SecurityRequirementImpl extends NodeImpl implements AsyncApi22SecurityRequirement {

	private Map<String, List<String>> _items = new LinkedHashMap<>();

	@Override
	public List<String> getItem(String name) {
		return this._items.get(name);
	}

	@Override
	public List<List<String>> getItems() {
		List<List<String>> rval = new ArrayList<>();
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
	public void addItem(String name, List<String> item) {
		this._items.put(name, item);
	}

	@Override
	public void insertItem(String name, List<String> item, int atIndex) {
		this._items = DataModelUtil.insertMapEntry(this._items, name, item, atIndex);
	}

	@Override
	public List<String> removeItem(String name) {
		return this._items.remove(name);
	}

	@Override
	public void clearItems() {
		this._items.clear();
	}

	@Override
	public void accept(Visitor visitor) {
		AsyncApi22Visitor viz = (AsyncApi22Visitor) visitor;
		viz.visitSecurityRequirement(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi22SecurityRequirementImpl();
	}
}