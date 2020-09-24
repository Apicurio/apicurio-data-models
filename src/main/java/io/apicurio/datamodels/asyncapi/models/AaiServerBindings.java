/*
 * Copyright 2019 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.asyncapi.models;

import io.apicurio.datamodels.asyncapi.visitors.IAaiVisitor;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Models an AsyncAPI server bindings.
 * 
 * https://www.asyncapi.com/docs/specifications/2.0.0/#serverBindingsObject
 * 
 * @author eric.wittmann@gmail.com
 */
public abstract class AaiServerBindings extends Node implements IReferenceNode {

//    public AaiHTTPServerBinding http;
//    public AaiWebSocketsServerBinding ws;
//    public AaiKafkaServerBinding kafka;
//    public AaiAMQPServerBinding amqp;
//    public AaiAMQP10ServerBinding amqp1;
//    public AaiMQTTServerBinding mqtt;
//    public AaiMQTT5ServerBinding mqtt5;
//    public AaiNATServerBinding nats;
//    public AaiJMSServerBinding jms;
//    public AaiSNSServerBinding sns;
//    public AaiSQSServerBinding sqs;
//    public AaiSTOMPServerBinding stomp;
//    public AaiRedisServerBinding redis;

    public String $ref;
    public Object http;
    public Object ws;
    public Object kafka;
    public Object amqp;
    public Object amqp1;
    public Object mqtt;
    public Object mqtt5;
    public Object nats;
    public Object jms;
    public Object sns;
    public Object sqs;
    public Object stomp;
    public Object redis;

    /**
     * Constructor.
     */
    public AaiServerBindings() {
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiServerBindings(Node parent) {
        if (parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAaiVisitor v = (IAaiVisitor) visitor;
        v.visitServerBindings(this);
    }

    @Override
    public String getReference() {
        return $ref;
    }

    @Override
    public void setReference(String reference) {
        $ref = reference;
    }

}
