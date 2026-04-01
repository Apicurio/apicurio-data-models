package io.apicurio.datamodels.models.asyncapi.v2x.v20;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiBinding;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.visitors.AsyncApi20Visitor;
import io.apicurio.datamodels.models.visitors.Visitor;

public class AsyncApi20ServerBindingsImpl extends NodeImpl implements AsyncApi20ServerBindings {

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
	public AsyncApi20Binding createBinding() {
		AsyncApi20BindingImpl node = new AsyncApi20BindingImpl();
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
	public void accept(Visitor visitor) {
		AsyncApi20Visitor viz = (AsyncApi20Visitor) visitor;
		viz.visitServerBindings(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi20ServerBindingsImpl();
	}
}