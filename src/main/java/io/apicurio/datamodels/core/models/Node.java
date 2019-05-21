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

package io.apicurio.datamodels.core.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Base class for all node types in all data models.
 * @author eric.wittmann@gmail.com
 */
public abstract class Node implements IVisitable {
    
    private static int __modelIdCounter = 0;

    public Document _ownerDocument;
    public Node _parent;
    protected int _modelId = __modelIdCounter++;
    protected Map<String, Object> _attributes;

	/**
	 * Properties that are present in the source document,
	 * but are not defined in the specification for this node,
	 * so they can't be read directly into the data model fields.
	 */
	protected Map<String, Object> _extraProperties;
    protected Map<String, ValidationProblem> _validationProblems = new LinkedHashMap<>();

    /**
     * Gets the owner document.
     */
    public Document ownerDocument() {
        return this._ownerDocument;
    }
    
    /**
     * Returns true if this node is extensible.
     */
    public boolean isExtensible() {
        return false;
    }

    /**
     * Gets the parent.
     */
    public Node parent() {
        return this._parent;
    }

    /**
     * Gets the model's unique ID.
     */
    public int modelId() {
        return this._modelId;
    }

	/**
	 * @see io.apicurio.datamodels.core.models.IVisitable#accept(io.apicurio.datamodels.core.visitors.IVisitor)
	 */
	public abstract void accept(IVisitor visitor);
	
	/**
	 * Gets a single attribute by name.
	 * @param attributeName
	 */
	public Object getAttribute(String attributeName) {
	    if (this._attributes != null) {
	        return this._attributes.get(attributeName);
	    } else {
	        return null;
	    }
	}

	/**
	 * Sets a single named attribute value.
	 * @param attributeName
	 * @param attributeValue
	 */
	public void setAttribute(String attributeName, Object attributeValue) {
	    if (this._attributes == null) {
	        this._attributes = new HashMap<>();
	    }
	    this._attributes.put(attributeName, attributeValue);
	}
	
	/**
	 * Gets a collection of all the attribute names.
	 */
	public Collection<String> getAttributeNames() {
	    if (this._attributes != null) {
	        return this._attributes.keySet();
	    } else {
	        return Collections.emptyList();
	    }
	}
	
	/**
	 * Deletes all attributes in the node.
	 */
	public void clearAttributes() {
	    if (this._attributes != null) {
	        this._attributes.clear();
	    }
	}
	
	/**
	 * Gets a list of all the validation problem codes for this node (and this node only).  Returns
	 * an empty list if no problems are found for this node.
	 */
	public List<String> getValidationProblemCodes() {
	    return new ArrayList<>(this._validationProblems.keySet());
	}

	/**
	 * Gets a list of all the validation problems detected for this node.
	 */
	public List<ValidationProblem> getValidationProblems() {
	    return new ArrayList<>(this._validationProblems.values());
	}
	
	/**
	 * Gets a list of all validation problems detected for this node, filtered by only those
	 * problems that apply to the given property name.
	 * @param propertyName
	 */
	public List<ValidationProblem> getValidationProblemsFor(String propertyName) {
	    List<ValidationProblem> rval = new ArrayList<>();
	    for (ValidationProblem problem : this._validationProblems.values()) {
            if (problem.property.equals(propertyName)) {
                rval.add(problem);
            }
        }
	    return rval;
	}
	
	/**
	 * Adds a validation problem to the data model.  Typically this is only used by the validation
	 * layer when performing validation on the data model.
	 * @param errorCode
	 * @param nodePath
	 * @param property
	 * @param message
	 * @param severity
	 */
    public ValidationProblem addValidationProblem(String errorCode, NodePath nodePath, String property,
            String message, ValidationProblemSeverity severity) {
        ValidationProblem problem = new ValidationProblem(errorCode, nodePath, property, message, severity);
        this._validationProblems.put(errorCode, problem);
        return problem;
    }
	
    /**
     * Deletes all validation problems previously discovered for this node.  Typically called by
     * the validation layer to reset the data model prior to performing validation.
     */
	public void clearValidationProblems() {
	    this._validationProblems.clear();
	}

    /**
     * Adds an extra property to the data model.  This is called when the reader encounters a property
     * that is unexpected based on the expected schema.
     * @param key
     * @param value
     */
    public void addExtraProperty(String key, Object value) {
        if (this._extraProperties == null) {
            this._extraProperties = new LinkedHashMap<>();
        }
        this._extraProperties.put(key, value);
    }
    
    public Object removeExtraProperty(String name) {
        if (this._extraProperties != null && this._extraProperties.containsKey(name)) {
            return this._extraProperties.remove(name);
        }
        return null;
    }

    public boolean hasExtraProperties() {
        return this._extraProperties != null && this._extraProperties.size() > 0;
    }

    public List<String> getExtraPropertyNames() {
        if (this.hasExtraProperties()) {
            return new ArrayList<String>(this._extraProperties.keySet());
        }
        return Collections.emptyList();
    }

    public Object getExtraProperty(String name) {
        if (this.hasExtraProperties()) {
            return this._extraProperties.get(name);
        }
        return null;
    }

}
