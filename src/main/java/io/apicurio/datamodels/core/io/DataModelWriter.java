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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.ISchemaDefinition;
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
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Base class for all data model writers.
 * @author eric.wittmann@gmail.com
 */
public class DataModelWriter implements IVisitor {

    private Object _result;
    private Map<Integer, Object> _modelIdToJS;
    
    /**
     * Constructor.
     */
    public DataModelWriter() {
        this.reset();
    }
    
    /**
     * Resets the visitor.
     */
    private void reset() {
        this._modelIdToJS = new HashMap<>();
    }
    
    /**
     * Gets the result of the writing.
     */
    public Object getResult() {
        return JsonCompat.removeNullProperties(this._result);
    }

    protected void updateIndex(Node node, Object json) {
        this._modelIdToJS.put(node.modelId(), json);
        // Note: the first object created by the visitor is the result (we always traverse top-down).
        if (this._result == null) {
            this._result = json;
        }
    }
    
    protected void writeExtraProperties(Object json, Node node) {
        node.getExtraPropertyNames().forEach(pname -> {
            Object value = node.getExtraProperty(pname);
            JsonCompat.setProperty(json, pname, value);
        });
    }

    protected Object lookup(int modelId) {
        Object rval = this._modelIdToJS.get(modelId);
        // If not found, return a throwaway object (this would happen when doing a partial
        // read of a subsection of a document).
        if (rval == null) {
            return JsonCompat.objectNode();
        }
        return rval;
    }

    /**
     * Lookup a JS object using the model ID of the node's parent.
     * @param node
     * @return {any}
     */
    protected Object lookupParentJson(Node node) {
        return this.lookup(node.parent().modelId());
    }

    /**
     * @see io.apicurio.asyncapi.core.visitors.IAaiNodeVisitor#visitDocument(io.apicurio.datamodels.asyncapi.models.AaiDocument)
     */
    @Override
    public void visitDocument(Document node) {
        Object root = JsonCompat.objectNode();
        writeDocument(node, root);
        this.updateIndex(node, root);
    }
    
    /**
     * Writes the document node info into the given json object.
     * @param node
     * @param json
     */
    protected void writeDocument(Document node, Object json) {
        // Subclasses should implement this.
    }

    /**
     * @see io.apicurio.asyncapi.core.visitors.IAaiNodeVisitor#visitExtension(io.apicurio.asyncapi.core.models.AaiExtension)
     */
    @Override
    public void visitExtension(Extension node) {
        Object parent = this.lookupParentJson(node);
        JsonCompat.setProperty(parent, node.name, node.value);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitInfo(io.apicurio.datamodels.core.models.common.Info)
     */
    @Override
    public void visitInfo(Info node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_TITLE, node.title);
        JsonCompat.setPropertyString(json, Constants.PROP_VERSION, node.version);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyString(json, Constants.PROP_TERMS_OF_SERVICE, node.termsOfService);
        JsonCompat.setPropertyNull(json, Constants.PROP_CONTACT);
        JsonCompat.setPropertyNull(json, Constants.PROP_LICENSE);
        writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_INFO, json);

        this.updateIndex(node, json);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitContact(io.apicurio.datamodels.core.models.common.Contact)
     */
    @Override
    public void visitContact(Contact node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_NAME, node.name);
        JsonCompat.setPropertyString(json, Constants.PROP_URL, node.url);
        JsonCompat.setPropertyString(json, Constants.PROP_EMAIL, node.email);
        writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_CONTACT, json);

        this.updateIndex(node, json);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitLicense(io.apicurio.datamodels.core.models.common.License)
     */
    @Override
    public void visitLicense(License node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_NAME, node.name);
        JsonCompat.setPropertyString(json, Constants.PROP_URL, node.url);
        writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_LICENSE, json);

        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitTag(io.apicurio.datamodels.core.models.common.Tag)
     */
    @Override
    public void visitTag(Tag node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_NAME, node.name);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS);
        writeExtraProperties(json, node);
        
        JsonCompat.appendToArrayProperty(parent, Constants.PROP_TAGS, json);

        this.updateIndex(node, json);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    @Override
    public void visitSecurityRequirement(SecurityRequirement node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        node.getSecurityRequirementNames().forEach(name -> {
            List<String> scopes = node.getScopes(name);
            JsonCompat.setPropertyStringArray(json, name, scopes);
        });
        JsonCompat.appendToArrayProperty(parent, Constants.PROP_SECURITY, json);
        this.updateIndex(node, json);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void visitServer(Server node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeServer(json, node);
        writeExtraProperties(json, node);

        JsonCompat.appendToArrayProperty(parent, Constants.PROP_SERVERS, json);
        
        this.updateIndex(node, json);
    }
    protected void writeServer(Object json, Server node) {
        JsonCompat.setPropertyString(json, Constants.PROP_URL, node.url);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_VARIABLES);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitServerVariable(io.apicurio.datamodels.core.models.common.ServerVariable)
     */
    @Override
    public void visitServerVariable(ServerVariable node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeServerVariable(json, node);
        writeExtraProperties(json, node);
        
        // Set the variable as a property on the parent's "variables" child object
        Object variables = JsonCompat.getProperty(parent, Constants.PROP_VARIABLES);
        if (variables == null) {
            variables = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_VARIABLES, variables);
        }
        JsonCompat.setProperty(variables, node.getName(), json);

        this.updateIndex(node, json);
    }
    protected void writeServerVariable(Object json, ServerVariable node) {
        JsonCompat.setPropertyStringArray(json, Constants.PROP_ENUM, node.enum_);
        JsonCompat.setPropertyString(json, Constants.PROP_DEFAULT, node.default_);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_VARIABLES);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExternalDocumentation(io.apicurio.datamodels.core.models.common.ExternalDocumentation)
     */
    @Override
    public void visitExternalDocumentation(ExternalDocumentation node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyString(json, Constants.PROP_URL, node.url);
        writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_EXTERNAL_DOCS, json);

        this.updateIndex(node, json);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeOperation(json, node);
        writeExtraProperties(json, node);
        
        JsonCompat.setProperty(parent, node.getType(), json);

        this.updateIndex(node, json);
    }
    protected void writeOperation(Object json, Operation node) {
        JsonCompat.setPropertyString(json, Constants.PROP_OPERATION_ID, node.operationId);
        JsonCompat.setPropertyString(json, Constants.PROP_SUMMARY, node.summary);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitParameter(io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeParameter(json, node);
        writeExtraProperties(json, node);
        
        JsonCompat.appendToArrayProperty(parent, Constants.PROP_PARAMETERS, json);
        
        this.updateIndex(node, json);
    }
    protected void writeParameter(Object json, Parameter node) {
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_NAME, node.name);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_SCHEMA);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSchema(io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    public void visitSchema(Schema node) {
        this.doVisitSchema(node, Constants.PROP_SCHEMA, false);
    }
    protected void doVisitSchema(Schema node, String parentPropertyName, boolean isCollection) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeSchema(json, node);
        writeExtraProperties(json, node);
        
        if (isCollection) {
            JsonCompat.appendToArrayProperty(parent, parentPropertyName, json);
        } else {
            JsonCompat.setProperty(parent, parentPropertyName, json);
        }

        this.updateIndex(node, json);
    }
    protected void writeSchema(Object json, Schema node) {
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
    }

    /**
     * @see io.apicurio.asyncapi.core.visitors.IAaiNodeVisitor#visitValidationProblem(io.apicurio.asyncapi.core.validation.AaiValidationProblem)
     */
    @Override
    public void visitValidationProblem(ValidationProblem problem) {
        // Validation problems are not written out, obviously.
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeSecurityScheme(json, node);
        writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, node.getSchemeName(), json);

        this.updateIndex(node, json);
    }
    protected void writeSecurityScheme(Object json, SecurityScheme node) {
        JsonCompat.setPropertyString(json, Constants.PROP_TYPE, node.type);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyString(json, Constants.PROP_NAME, node.name);
        JsonCompat.setPropertyString(json, Constants.PROP_IN, node.in);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSchemaDefinition(io.apicurio.datamodels.core.models.common.ISchemaDefinition)
     */
    @Override
    public void visitSchemaDefinition(ISchemaDefinition node) {
        Schema schema = (Schema) node;
        Object parent = this.lookupParentJson(schema);
        Object json = JsonCompat.objectNode();
        writeSchema(json, schema);
        writeExtraProperties(json, schema);

        JsonCompat.setProperty(parent, node.getName(), json);

        this.updateIndex(schema, json);
    }

}
