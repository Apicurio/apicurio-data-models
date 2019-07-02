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

package io.apicurio.datamodels.core.visitors;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.NodePath;
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

/**
 * Base class for all node path visitors.  Responsible for creating a node path
 * by visiting a 
 * @author eric.wittmann@gmail.com
 */
public class NodePathVisitor implements IVisitor {

    protected NodePath path = new NodePath();
    
    /**
     * Constructor.
     */
    public NodePathVisitor() {
    }

    /**
     * Gets the path that was created.
     */
    public NodePath getPath() {
        return this.path;
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitValidationProblem(io.apicurio.datamodels.core.models.ValidationProblem)
     */
    @Override
    public void visitValidationProblem(ValidationProblem problem) {
        // Do nothing for validation problems.
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void visitDocument(Document node) {
        // Nothing to do for root
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExtension(io.apicurio.datamodels.core.models.Extension)
     */
    @Override
    public void visitExtension(Extension node) {
        this.path.prependSegment(node.name, false);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitInfo(io.apicurio.datamodels.core.models.common.Info)
     */
    @Override
    public void visitInfo(Info node) {
        this.path.prependSegment(Constants.PROP_INFO, false);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitContact(io.apicurio.datamodels.core.models.common.Contact)
     */
    @Override
    public void visitContact(Contact node) {
        this.path.prependSegment(Constants.PROP_CONTACT, false);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitLicense(io.apicurio.datamodels.core.models.common.License)
     */
    @Override
    public void visitLicense(License node) {
        this.path.prependSegment(Constants.PROP_LICENSE, false);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitTag(io.apicurio.datamodels.core.models.common.Tag)
     */
    @Override
    public void visitTag(Tag node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_TAGS);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_TAGS, false);
        }
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    @Override
    public void visitSecurityRequirement(SecurityRequirement node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_SECURITY);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_SECURITY, false);
        }
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExternalDocumentation(io.apicurio.datamodels.core.models.common.ExternalDocumentation)
     */
    @Override
    public void visitExternalDocumentation(ExternalDocumentation node) {
        this.path.prependSegment(Constants.PROP_EXTERNAL_DOCS, false);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSchema(io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    public void visitSchema(Schema node) {
        this.path.prependSegment(Constants.PROP_SCHEMA, false);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitParameter(io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_PARAMETERS);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_PARAMETERS, false);
        }
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        // _type determines the field name
        this.path.prependSegment(node.getType(), false);
    }
    
//    /**
//     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
//     */
//    @Override
//    public void visitSecurityScheme(SecurityScheme node) {
//        this.path.prependSegment(node.getSchemeName(), true);
//    }

    /**
     * @see io.apicurio.datamodels.core.visitors.NodePathVisitor#visitSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        this.path.prependSegment(node.getSchemeName(), true);
        // TODO test fails
        //this.path.prependSegment(Constants.PROP_SECURITY_SCHEMES, false);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSchemaDefinition(io.apicurio.datamodels.core.models.common.ISchemaDefinition)
     */
    @Override
    public void visitSchemaDefinition(ISchemaDefinition node) {
        this.path.prependSegment(node.getName(), true);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitParameterDefinition(io.apicurio.datamodels.core.models.common.IParameterDefinition)
     */
    @Override
    public void visitParameterDefinition(IParameterDefinition node) {
        this.path.prependSegment(node.getName(), true);
    }

}
