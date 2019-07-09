/*
 * Copyright 2019 Red Hat
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

import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Tag;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiOperationBase extends Operation {

    public String $ref;
    public List<Tag> tags;
    public Map<String, AaiProtocolInfo> protocolInfo;
    
    /**
     * Constructor.
     */
    public AaiOperationBase(String opType) {
        super(opType);
    }

    public AaiOperationBase(Node parent, String opType) {
        super(opType);
        if(parent != null) {
            this._parent = parent;
            this._ownerDocument = parent.ownerDocument();
        }
    }

    public AaiOperationBase(Node parent) {
        this(parent, null);
    }


    public abstract List<AaiProtocolInfo> getProtocolInfoList();
    public abstract void addTag(AaiTag tag);
    public abstract void addProtocolInfo(AaiProtocolInfo value);
}
