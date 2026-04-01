package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xItems;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenApi20ParameterImpl extends NodeImpl implements OpenApi20Parameter {

	private String $ref;
	private String description;
	private String type;
	private String format;
	private JsonNode _default;
	private Number maximum;
	private Boolean exclusiveMaximum;
	private Number minimum;
	private Boolean exclusiveMinimum;
	private Integer maxLength;
	private Integer minLength;
	private String pattern;
	private Integer maxItems;
	private Integer minItems;
	private Boolean uniqueItems;
	private List<JsonNode> _enum;
	private Number multipleOf;
	private String name;
	private String in;
	private Boolean required;
	private OpenApiSchema schema;
	private OpenApi2xItems items;
	private Boolean allowEmptyValue;
	private String collectionFormat;
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String value) {
		this.description = value;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String value) {
		this.type = value;
	}

	@Override
	public String getFormat() {
		return format;
	}

	@Override
	public void setFormat(String value) {
		this.format = value;
	}

	@Override
	public JsonNode getDefault() {
		return _default;
	}

	@Override
	public void setDefault(JsonNode value) {
		this._default = value;
	}

	@Override
	public Number getMaximum() {
		return maximum;
	}

	@Override
	public void setMaximum(Number value) {
		this.maximum = value;
	}

	@Override
	public Boolean isExclusiveMaximum() {
		return exclusiveMaximum;
	}

	@Override
	public void setExclusiveMaximum(Boolean value) {
		this.exclusiveMaximum = value;
	}

	@Override
	public Number getMinimum() {
		return minimum;
	}

	@Override
	public void setMinimum(Number value) {
		this.minimum = value;
	}

	@Override
	public Boolean isExclusiveMinimum() {
		return exclusiveMinimum;
	}

	@Override
	public void setExclusiveMinimum(Boolean value) {
		this.exclusiveMinimum = value;
	}

	@Override
	public Integer getMaxLength() {
		return maxLength;
	}

	@Override
	public void setMaxLength(Integer value) {
		this.maxLength = value;
	}

	@Override
	public Integer getMinLength() {
		return minLength;
	}

	@Override
	public void setMinLength(Integer value) {
		this.minLength = value;
	}

	@Override
	public String getPattern() {
		return pattern;
	}

	@Override
	public void setPattern(String value) {
		this.pattern = value;
	}

	@Override
	public Integer getMaxItems() {
		return maxItems;
	}

	@Override
	public void setMaxItems(Integer value) {
		this.maxItems = value;
	}

	@Override
	public Integer getMinItems() {
		return minItems;
	}

	@Override
	public void setMinItems(Integer value) {
		this.minItems = value;
	}

	@Override
	public Boolean isUniqueItems() {
		return uniqueItems;
	}

	@Override
	public void setUniqueItems(Boolean value) {
		this.uniqueItems = value;
	}

	@Override
	public List<JsonNode> getEnum() {
		return _enum;
	}

	@Override
	public void setEnum(List<JsonNode> value) {
		this._enum = value;
	}

	@Override
	public Number getMultipleOf() {
		return multipleOf;
	}

	@Override
	public void setMultipleOf(Number value) {
		this.multipleOf = value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String value) {
		this.name = value;
	}

	@Override
	public String getIn() {
		return in;
	}

	@Override
	public void setIn(String value) {
		this.in = value;
	}

	@Override
	public Boolean isRequired() {
		return required;
	}

	@Override
	public void setRequired(Boolean value) {
		this.required = value;
	}

	@Override
	public OpenApiSchema getSchema() {
		return schema;
	}

	@Override
	public void setSchema(OpenApiSchema value) {
		this.schema = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("schema");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20Schema createSchema() {
		OpenApi20SchemaImpl node = new OpenApi20SchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApi2xItems getItems() {
		return items;
	}

	@Override
	public void setItems(OpenApi2xItems value) {
		this.items = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("items");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20Items createItems() {
		OpenApi20ItemsImpl node = new OpenApi20ItemsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Boolean isAllowEmptyValue() {
		return allowEmptyValue;
	}

	@Override
	public void setAllowEmptyValue(Boolean value) {
		this.allowEmptyValue = value;
	}

	@Override
	public String getCollectionFormat() {
		return collectionFormat;
	}

	@Override
	public void setCollectionFormat(String value) {
		this.collectionFormat = value;
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
		OpenApi20Visitor viz = (OpenApi20Visitor) visitor;
		viz.visitParameter(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi20ParameterImpl();
	}
}