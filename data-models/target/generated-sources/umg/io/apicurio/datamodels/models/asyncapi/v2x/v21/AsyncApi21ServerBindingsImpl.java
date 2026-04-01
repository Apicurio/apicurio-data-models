package io.apicurio.datamodels.models.asyncapi.v2x.v21;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiBinding;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.visitors.AsyncApi21Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class AsyncApi21ServerBindingsImpl extends NodeImpl implements AsyncApi21ServerBindings {

	private String $ref;
	private AsyncApiBinding http;
	private AsyncApiBinding ws;
	private AsyncApiBinding kafka;
	private AsyncApiBinding amqp;
	private AsyncApiBinding amqp1;
	private AsyncApiBinding mqtt;
	private AsyncApiBinding mqtt5;
	private AsyncApiBinding nats;
	private AsyncApiBinding jms;
	private AsyncApiBinding sns;
	private AsyncApiBinding sqs;
	private AsyncApiBinding stomp;
	private AsyncApiBinding redis;
	private AsyncApi21Binding mercure;
	private AsyncApi21Binding ibmmq;
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
	public AsyncApiBinding getHttp() {
		return http;
	}

	@Override
	public void setHttp(AsyncApiBinding value) {
		this.http = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("http");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi21Binding createBinding() {
		AsyncApi21BindingImpl node = new AsyncApi21BindingImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApiBinding getWs() {
		return ws;
	}

	@Override
	public void setWs(AsyncApiBinding value) {
		this.ws = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("ws");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getKafka() {
		return kafka;
	}

	@Override
	public void setKafka(AsyncApiBinding value) {
		this.kafka = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("kafka");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getAmqp() {
		return amqp;
	}

	@Override
	public void setAmqp(AsyncApiBinding value) {
		this.amqp = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("amqp");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getAmqp1() {
		return amqp1;
	}

	@Override
	public void setAmqp1(AsyncApiBinding value) {
		this.amqp1 = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("amqp1");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getMqtt() {
		return mqtt;
	}

	@Override
	public void setMqtt(AsyncApiBinding value) {
		this.mqtt = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("mqtt");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getMqtt5() {
		return mqtt5;
	}

	@Override
	public void setMqtt5(AsyncApiBinding value) {
		this.mqtt5 = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("mqtt5");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getNats() {
		return nats;
	}

	@Override
	public void setNats(AsyncApiBinding value) {
		this.nats = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("nats");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getJms() {
		return jms;
	}

	@Override
	public void setJms(AsyncApiBinding value) {
		this.jms = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("jms");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getSns() {
		return sns;
	}

	@Override
	public void setSns(AsyncApiBinding value) {
		this.sns = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("sns");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getSqs() {
		return sqs;
	}

	@Override
	public void setSqs(AsyncApiBinding value) {
		this.sqs = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("sqs");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getStomp() {
		return stomp;
	}

	@Override
	public void setStomp(AsyncApiBinding value) {
		this.stomp = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("stomp");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiBinding getRedis() {
		return redis;
	}

	@Override
	public void setRedis(AsyncApiBinding value) {
		this.redis = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("redis");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi21Binding getMercure() {
		return mercure;
	}

	@Override
	public void setMercure(AsyncApi21Binding value) {
		this.mercure = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("mercure");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi21Binding getIbmmq() {
		return ibmmq;
	}

	@Override
	public void setIbmmq(AsyncApi21Binding value) {
		this.ibmmq = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("ibmmq");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
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
		AsyncApi21Visitor viz = (AsyncApi21Visitor) visitor;
		viz.visitServerBindings(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi21ServerBindingsImpl();
	}
}