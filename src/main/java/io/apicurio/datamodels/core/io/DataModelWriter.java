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
import io.apicurio.datamodels.core.models.common.IParameterDefinition;
import io.apicurio.datamodels.core.models.common.ISchemaDefinition;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.License;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
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
     */
    protected Object lookupParentJson(Node node) {
        return this.lookup(node.parent().modelId());
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitDocument(io.apicurio.datamodels.core.models.Document)
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
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExtension(io.apicurio.datamodels.core.models.Extension)
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
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitValidationProblem(io.apicurio.datamodels.core.models.ValidationProblem)
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
        
        this.addSecuritySchemeToParent(parent, json, node);

        this.updateIndex(node, json);
    }
    protected void writeSecurityScheme(Object json, SecurityScheme node) {
        JsonCompat.setPropertyString(json, Constants.PROP_TYPE, node.type);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyString(json, Constants.PROP_NAME, node.name);
        JsonCompat.setPropertyString(json, Constants.PROP_IN, node.in);
    }
    protected void addSecuritySchemeToParent(Object parent, Object json, SecurityScheme node) {
        JsonCompat.setProperty(parent, node.getSchemeName(), json);
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
        
        addSchemaDefinitionToParent(parent, json, node);

        this.updateIndex(schema, json);
    }
    protected void addSchemaDefinitionToParent(Object parent, Object json, ISchemaDefinition node) {
        JsonCompat.setProperty(parent, node.getName(), json);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitParameterDefinition(io.apicurio.datamodels.core.models.common.IParameterDefinition)
     */
    @Override
    public void visitParameterDefinition(IParameterDefinition node) {
        Parameter pdef = (Parameter) node;
        
        Object parent = this.lookupParentJson(pdef);
        Object json = JsonCompat.objectNode();
        this.writeParameter(json, pdef);
        this.writeExtraProperties(json, pdef);

        addParameterDefinitionToParent(parent, json, node);

        this.updateIndex(pdef, json);        
    }
    protected void addParameterDefinitionToParent(Object parent, Object json, IParameterDefinition node) {
        JsonCompat.setProperty(parent, node.getName(), json);
    }

}
