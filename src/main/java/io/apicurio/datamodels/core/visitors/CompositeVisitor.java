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

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.IVisitable;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.License;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.core.models.common.Tag;

/**
 * A simple visitor that delegates to a list of *other* visitors.  Basically converts
 * a list of visitors to a single visitor.
 * @author eric.wittmann@gmail.com
 */
public class CompositeVisitor implements IVisitor {
    
    private List<IVisitor> visitors = new ArrayList<>();

    /**
     * Constructor.
     * @param visitors
     */
    public CompositeVisitor(List<IVisitor> visitors) {
        this.visitors = visitors;
    }
    
    /**
     * Adds a visitor.
     * @param visitor
     */
    public void addVisitor(IVisitor visitor) {
        this.visitors.add(visitor);
    }
    
    /**
     * Adds multiple visitors.
     * @param visitors
     */
    public void addVisitors(List<? extends IVisitor> visitors) {
        this.visitors.addAll(visitors);
    }
    
    /**
     * Make the node accept all of the visitors.
     * @param node
     */
    protected void acceptAll(IVisitable node) {
        this.visitors.forEach(visitor -> {
            node.accept(visitor);
        });
    }

    /**
     * @see io.apicurio.asyncapi.core.visitors.IVisitor#visitDocument(io.apicurio.asyncapi.core.models.Document)
     */
    @Override
    public void visitDocument(Document node) {
        this.acceptAll(node);
    }

    /**
     * @see io.apicurio.asyncapi.core.visitors.IVisitor#visitExtension(io.apicurio.asyncapi.core.models.Extension)
     */
    @Override
    public void visitExtension(Extension node) {
        this.acceptAll(node);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitInfo(io.apicurio.datamodels.core.models.common.Info)
     */
    @Override
    public void visitInfo(Info node) {
        this.acceptAll(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitContact(io.apicurio.datamodels.core.models.common.Contact)
     */
    @Override
    public void visitContact(Contact node) {
        this.acceptAll(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitLicense(io.apicurio.datamodels.core.models.common.License)
     */
    @Override
    public void visitLicense(License node) {
        this.acceptAll(node);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitTag(io.apicurio.datamodels.core.models.common.Tag)
     */
    @Override
    public void visitTag(Tag node) {
        this.acceptAll(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    @Override
    public void visitSecurityRequirement(SecurityRequirement node) {
        this.acceptAll(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void visitServer(Server node) {
        this.acceptAll(node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitServerVariable(io.apicurio.datamodels.core.models.common.ServerVariable)
     */
    @Override
    public void visitServerVariable(ServerVariable node) {
        this.acceptAll(node);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExternalDocumentation(io.apicurio.datamodels.core.models.common.ExternalDocumentation)
     */
    @Override
    public void visitExternalDocumentation(ExternalDocumentation node) {
        this.acceptAll(node);
    }

    /**
     * @see io.apicurio.asyncapi.core.visitors.IVisitor#visitValidationProblem(io.apicurio.asyncapi.core.validation.ValidationProblem)
     */
    @Override
    public void visitValidationProblem(ValidationProblem problem) {
        this.acceptAll(problem);
    }

}
