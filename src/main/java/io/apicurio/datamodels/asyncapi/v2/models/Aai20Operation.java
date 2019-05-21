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

package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiProtocolInfo;
import io.apicurio.datamodels.asyncapi.models.AaiTag;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Operation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import static io.apicurio.datamodels.asyncapi.v2.models.Aai20Document.mapToList;

/**
 * @author eric.wittmann@gmail.com
 */
public class Aai20Operation extends AaiOperation {


    public Aai20Operation(Node parent) {
        super(parent);
    }

    public Aai20Operation(Node parent, String opType) {
        super(parent, opType);
    }

    @Override
    public List<AaiProtocolInfo> getProtocolInfoList() {
        return mapToList(this.protocolInfo);
    }

    @Override
    public void addTag(AaiTag tag) {
        if(tags == null)
            tags = new LinkedList<>();
        tags.add(tag);
    }

    @Override
    public void addProtocolInfo(AaiProtocolInfo item) {
        if(protocolInfo == null)
            protocolInfo = new LinkedHashMap<>();
        protocolInfo.put(item.getName(), item);
    }

    /**
     * Creates a Tag node.
     */
    public Aai20Tag createTag() {
        Aai20Tag tag = new Aai20Tag(this);
        return tag;
    }

    /**
     * Adds a tag.
     *
     * @param name
     * @param description
     */
    public Aai20Tag addTag(String name, String description) {
        Aai20Tag tag = this.createTag();
        tag.name = name;
        tag.description = description;
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tag);
        return tag;
    }

    /**
     * @see Operation#createExternalDocumentation()
     */
    @Override
    public Aai20ExternalDocumentation createExternalDocumentation() {
        Aai20ExternalDocumentation externalDocs = new Aai20ExternalDocumentation(this);
        return externalDocs;
    }

}
