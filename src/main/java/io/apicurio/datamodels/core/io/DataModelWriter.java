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
import java.util.Map;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public abstract class DataModelWriter implements IVisitor {

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
        return this._result;
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
    protected abstract void writeDocument(Document node, Object json);

    /**
     * @see io.apicurio.asyncapi.core.visitors.IAaiNodeVisitor#visitExtension(io.apicurio.asyncapi.core.models.AaiExtension)
     */
    @Override
    public void visitExtension(Extension node) {
        Object parent = this.lookupParentJson(node);
        JsonCompat.setProperty(parent, node.name, node.value);
    }

    /**
     * @see io.apicurio.asyncapi.core.visitors.IAaiNodeVisitor#visitValidationProblem(io.apicurio.asyncapi.core.validation.AaiValidationProblem)
     */
    @Override
    public void visitValidationProblem(ValidationProblem problem) {
        // Validation problems are not written out, obviously.
    }

}
