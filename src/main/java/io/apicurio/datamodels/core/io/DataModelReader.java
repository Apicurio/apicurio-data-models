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

package io.apicurio.datamodels.core.io;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.License;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.models.common.Tag;

/**
 * Base class for all data model readers.  Provides some common reading capabilities.
 * @author eric.wittmann@gmail.com
 */
public abstract class DataModelReader<T extends Document> {
    
    /**
     * Constructor.
     */
    public DataModelReader() {
    }

    /**
     * Reads the extension properties from the input and stores them in the extensible node.
     * @param json
     * @param node
     */
    protected void readExtensions(Object json, ExtensibleNode node) {
        JsonCompat.keys(json).forEach(key -> {
            if (key.startsWith(Constants.EXTENSION_PREFIX)) {
                Extension extension = node.createExtension();
                extension.name = key;
                extension.value = JsonCompat.consumePropertyObject(json, key);
                node.addExtension(key, extension);
            }
        });
    }

    /**
     * Reads all remaining properties.  Anything left is an "extra" (or unexpected) property.  These
     * are not extension properties - they are actually properties that SHOULD NOT have existed on
     * the node.  (all extension properties must start with "x-" and are consumed by "readExtensions".
     * @param jsData
     * @param model
     */
    protected void readExtraProperties(Object json, Node node) {
        JsonCompat.keys(json).forEach(key -> {
            Object value = JsonCompat.consumePropertyObject(json, key);
            node.addExtraProperty(key, value);
            LoggerCompat.warn("Found unexpected data model property: ", key);
        });
    }

    /**
     * Reads the root document.
     * @param json
     * @param node
     */
    public void readDocument(Object json, T node) {
        Object info = JsonCompat.consumeProperty(json, Constants.PROP_INFO);
        List<Object> tags = JsonCompat.consumePropertyArray(json, Constants.PROP_TAGS);
        Object externalDocs = JsonCompat.consumeProperty(json, Constants.PROP_EXTERNAL_DOCS);
        
        if (info != null) {
            node.info = node.createInfo();
            this.readInfo(info, node.info);
        }
        
        if (tags != null) {
            List<Tag> tagModels = new ArrayList<>();
            for (Object tag : tags) {
                Tag tagModel = node.createTag();
                this.readTag(tag, tagModel);
                tagModels.add(tagModel);
            }
            node.tags = tagModels;
        }
        
        if (externalDocs != null) {
            node.externalDocs = node.createExternalDocumentation();
            this.readExternalDocumentation(externalDocs, node.externalDocs);
        }
        this.readExtensions(json, node);
    }
    
    /**
     * Reads an info object into a data model instance (info node).
     * @param json
     * @param node
     */
    public void readInfo(Object json, Info node) {
        String title = JsonCompat.consumePropertyString(json, Constants.PROP_TITLE);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        String termsOfService = JsonCompat.consumePropertyString(json, Constants.PROP_TERMS_OF_SERVICE);
        Object contact = JsonCompat.consumeProperty(json, Constants.PROP_CONTACT);
        Object license = JsonCompat.consumeProperty(json, Constants.PROP_LICENSE);
        String version = JsonCompat.consumePropertyString(json, Constants.PROP_VERSION);
        
        node.title = title;
        node.description = description;
        node.termsOfService = termsOfService;
        node.version = version;
        
        if (contact != null) {
            node.contact = node.createContact();
            this.readContact(contact, node.contact);
        }
        
        if (license != null) {
            node.license = node.createLicense();
            this.readLicense(license, node.license);
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads a contact object info into the data model.
     * @param json
     * @param node
     */
    public void readContact(Object json, Contact node) {
        String name = JsonCompat.consumePropertyString(json, Constants.PROP_NAME);
        String url = JsonCompat.consumePropertyString(json, Constants.PROP_URL);
        String email = JsonCompat.consumePropertyString(json, Constants.PROP_EMAIL);

        node.name = name;
        node.url = url;
        node.email = email;

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads a license object info into the data model.
     * @param json
     * @param node
     */
    public void readLicense(Object json, License node) {
        String name = JsonCompat.consumePropertyString(json, Constants.PROP_NAME);
        String url = JsonCompat.consumePropertyString(json, Constants.PROP_URL);

        node.name = name;
        node.url = url;

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads an tag object into a data model instance (tag node).
     * @param json
     * @param node
     */
    public void readTag(Object json, Tag node) {
        String name = JsonCompat.consumePropertyString(json, Constants.PROP_NAME);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object externalDocs = JsonCompat.consumeProperty(json, Constants.PROP_EXTERNAL_DOCS);
        
        node.name = name;
        node.description = description;
        
        if (externalDocs != null) {
            ExternalDocumentation externalDocsModel = node.createExternalDocumentation();
            this.readExternalDocumentation(externalDocs, externalDocsModel);
            node.externalDocs = externalDocsModel;
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads a server model.
     * @param json
     * @param node
     */
    public void readServer(Object json, Server node) {
        String url = JsonCompat.consumePropertyString(json, Constants.PROP_URL);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object variables = JsonCompat.consumeProperty(json, Constants.PROP_VARIABLES);
        
        node.url = url;
        node.description = description;
        
        if (variables != null) {
            JsonCompat.keys(variables).forEach(key -> {
                Object serverVariable = JsonCompat.consumeProperty(variables, key);
                ServerVariable serverVariableModel = node.createServerVariable(key);
                this.readServerVariable(serverVariable, serverVariableModel);
                node.addServerVariable(key, serverVariableModel);
            });
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads a server variable into the data model.
     * @param json
     * @param node
     */
    public void readServerVariable(Object json, ServerVariable node) {
        List<String> enum_ = JsonCompat.consumePropertyStringArray(json, Constants.PROP_ENUM);
        String default_ = JsonCompat.consumePropertyString(json, Constants.PROP_DEFAULT);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        
        node.enum_ = enum_;
        node.default_ = default_;
        node.description = description;

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads a security requirement.
     * @param json
     * @param node
     */
    public void readSecurityRequirement(Object json, SecurityRequirement node) {
        JsonCompat.keys(json).forEach(key -> {
            List<String> scopes = JsonCompat.consumePropertyStringArray(json, key);
            node.addSecurityRequirementItem(key, scopes);
        });
    }
    
    /**
     * Reads a security scheme.
     * @param json
     * @param node
     */
    public void readSecurityScheme(Object json, SecurityScheme node) {
        String type = JsonCompat.consumePropertyString(json, Constants.PROP_TYPE);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        String name = JsonCompat.consumePropertyString(json, Constants.PROP_NAME);
        String in = JsonCompat.consumePropertyString(json, Constants.PROP_IN);
        
        node.type = type;
        node.description = description;
        node.name = name;
        node.in = in;
        
        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }
    
    /**
     * Reads an external documentation into the data model.
     * @param json
     * @param node
     */
    public void readExternalDocumentation(Object json, ExternalDocumentation node) {
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        String url = JsonCompat.consumePropertyString(json, Constants.PROP_URL);

        node.description = description;
        node.url = url;

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads a schema.
     * @param json
     * @param node
     */
    public void readSchema(Object json, Schema node) {
        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        node.$ref = $ref;

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

    /**
     * Reads a single parameter.
     * @param json
     * @param node
     */
    public void readParameter(Object json, Parameter node) {
        String $ref = JsonCompat.consumePropertyString(json, Constants.PROP_$REF);
        String name = JsonCompat.consumePropertyString(json, Constants.PROP_NAME);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object schema = JsonCompat.consumeProperty(json, Constants.PROP_SCHEMA);

        node.$ref = $ref;
        node.name = name;
        node.description = description;
        
        if (schema != null) {
            node.schema = node.createSchema();
            this.readSchema(schema, node.schema);
        }

        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }
    
    /**
     * Reads a single operation.
     * @param json
     * @param node
     */
    public void readOperation(Object json, Operation node) {
        String operationId = JsonCompat.consumePropertyString(json, Constants.PROP_OPERATION_ID);
        String summary = JsonCompat.consumePropertyString(json, Constants.PROP_SUMMARY);
        String description = JsonCompat.consumePropertyString(json, Constants.PROP_DESCRIPTION);
        Object externalDocs = JsonCompat.consumeProperty(json, Constants.PROP_EXTERNAL_DOCS);
        
        node.operationId = operationId;
        node.summary = summary;
        node.description = description;

        if (externalDocs != null) {
            node.externalDocs = node.createExternalDocumentation();
            this.readExternalDocumentation(externalDocs, node.externalDocs);
        }
        
        this.readExtensions(json, node);
        this.readExtraProperties(json, node);
    }

}
