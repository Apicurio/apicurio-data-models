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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.asyncapi.v2.models.Aai20ExternalDocumentation;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Tag;
import io.apicurio.datamodels.asyncapi.v2.visitors.IAai20Visitor;
import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.INamed;
import io.apicurio.datamodels.core.models.common.Tag;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class AaiUnknownTrait extends ExtensibleNode implements INamed, IAaiTrait {
    
    public String _name;
    
    public String summary;
    public String description;
    public List<Tag> tags;
    public ExternalDocumentation externalDocs;
    public Map<String, AaiProtocolInfo> protocolInfo;

    /**
     * Constructor.
     */
    public AaiUnknownTrait(String name) {
        this._name = name;
    }

    public AaiUnknownTrait(Node parent, String name) {
        this(name);
        if (parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#getName()
     */
    @Override
    public String getName() {
        return this._name;
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.INamed#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {
        this._name = newName;
    }

    /**
     * @see io.apicurio.datamodels.core.models.Node#accept(io.apicurio.datamodels.core.visitors.IVisitor)
     */
    @Override
    public void accept(IVisitor visitor) {
        IAai20Visitor v = (IAai20Visitor) visitor;
        v.visitUnknownTrait(this);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.models.IAaiTrait#isMessageTrait()
     */
    @Override
    public boolean isMessageTrait() {
        return false;
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.models.IAaiTrait#isOperationTrait()
     */
    @Override
    public boolean isOperationTrait() {
        return false;
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.models.IAaiTrait#isUnknownTrait()
     */
    @Override
    public boolean isUnknownTrait() {
        return true;
    }

    public Aai20ExternalDocumentation createExternalDocumentation() {
        return new Aai20ExternalDocumentation(this);
    }
    
    public Aai20Tag createTag() {
        return new Aai20Tag(this);
    }

    public List<AaiProtocolInfo> getProtocolInfoList() {
        return JsonCompat.mapToList(protocolInfo);
    }

    public void addTag(AaiTag tag) {
        if (tags == null) {
            tags = new LinkedList<>();
        }
        tags.add(tag);
    }

    public void addProtocolInfo(AaiProtocolInfo item) {
        if (protocolInfo == null) {
            protocolInfo = new LinkedHashMap<>();
        }
        protocolInfo.put(item.getName(), item);
    }
}
