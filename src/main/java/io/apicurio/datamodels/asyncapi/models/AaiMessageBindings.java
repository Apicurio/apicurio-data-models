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
 * @author eric.wittmann@gmail.com
 */
public abstract class AaiMessageBindings extends Node implements IReferenceNode {

//    public AaiHTTPMessageBinding http;
//    public AaiWebSocketsMessageBinding ws;
//    public AaiKafkaMessageBinding kafka;
//    public AaiAMQPMessageBinding amqp;
//    public AaiAMQP10MessageBinding amqp1;
//    public AaiMQTTMessageBinding mqtt;
//    public AaiMQTT5MessageBinding mqtt5;
//    public AaiNATMessageBinding nats;
//    public AaiJMSMessageBinding jms;
//    public AaiSNSMessageBinding sns;
//    public AaiSQSMessageBinding sqs;
//    public AaiSTOMPMessageBinding stomp;
//    public AaiRedisMessageBinding redis;

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
    public AaiMessageBindings() {
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiMessageBindings(Node parent) {
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
        v.visitMessageBindings(this);
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
