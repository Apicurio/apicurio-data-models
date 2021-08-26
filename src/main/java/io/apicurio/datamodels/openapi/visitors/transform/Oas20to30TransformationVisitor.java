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

package io.apicurio.datamodels.openapi.visitors.transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.IReferenceNode;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.models.common.Contact;
import io.apicurio.datamodels.core.models.common.ExternalDocumentation;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.models.common.IExternalDocumentationParent;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.ISecurityRequirementParent;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.License;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Tag;
import io.apicurio.datamodels.core.util.DocumentUtil;
import io.apicurio.datamodels.core.util.NodePathUtil;
import io.apicurio.datamodels.core.util.NodeUtil;
import io.apicurio.datamodels.core.util.ReferenceUtil;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.visitors.ConsumesProducesFinder;
import io.apicurio.datamodels.core.visitors.OperationFinder;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
import io.apicurio.datamodels.openapi.models.IOasParameterParent;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasPaths;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasResponses;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.models.OasXML;
import io.apicurio.datamodels.openapi.v2.models.Oas20Definitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Headers;
import io.apicurio.datamodels.openapi.v2.models.Oas20Items;
import io.apicurio.datamodels.openapi.v2.models.Oas20Operation;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Response;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Schema;
import io.apicurio.datamodels.openapi.v2.models.Oas20Schema.Oas20PropertySchema;
import io.apicurio.datamodels.openapi.v2.models.Oas20SchemaDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20Scopes;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityRequirement;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityScheme;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;
import io.apicurio.datamodels.openapi.v3.models.Oas30Components;
import io.apicurio.datamodels.openapi.v3.models.Oas30Contact;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30ExternalDocumentation;
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;
import io.apicurio.datamodels.openapi.v3.models.Oas30Info;
import io.apicurio.datamodels.openapi.v3.models.Oas30License;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30ParameterDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30PathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30Paths;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Response;
import io.apicurio.datamodels.openapi.v3.models.Oas30ResponseDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Responses;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AdditionalPropertiesSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AllOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30ItemsSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30PropertySchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30SchemaDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityRequirement;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityScheme;
import io.apicurio.datamodels.openapi.v3.models.Oas30Server;
import io.apicurio.datamodels.openapi.v3.models.Oas30ServerVariable;
import io.apicurio.datamodels.openapi.v3.models.Oas30XML;

/**
 * A visitor used to transform an OpenAPI 2.0 document into an OpenAPI 3.0.x document.
 * @author eric.wittmann@gmail.com
 */
public class Oas20to30TransformationVisitor implements IOas20Visitor {

    private Oas30Document doc30;

    private Map<String, Node> _nodeMap = new HashMap<>();
    private List<Oas30Response> _postProcessResponses = new ArrayList<>();
    private boolean _postProcessingComplete = false;

    /**
     * Retrieves the final result.
     */
    public Oas30Document getResult() {
        if (!this._postProcessingComplete) {
            this.postProcess();
        }
        return this.doc30;
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitDocument(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void visitDocument(Document node) {
        this.doc30 = (Oas30Document) DocumentUtil.createDocument(DocumentType.openapi3);

        Oas20Document doc20 = (Oas20Document) node;
        if (!NodeCompat.isNullOrUndefined(doc20.host)) {
            String basePath = doc20.basePath;
            if (NodeCompat.isNullOrUndefined(basePath)) {
                basePath = "";
            }
            List<String> schemes = doc20.schemes;
            if (NodeCompat.isNullOrUndefined(schemes) || schemes.size() == 0) {
                schemes = NodeCompat.asList("http");
            }

            Oas30Server server30 = (Oas30Server) this.doc30.createServer();
            this.doc30.servers = new ArrayList<>();
            this.doc30.servers.add(server30);
            if (schemes.size() == 1) {
                server30.url = schemes.get(0) + "://" + doc20.host + basePath;
            } else {
                server30.url = "{scheme}://" + doc20.host + basePath;
                Oas30ServerVariable var30 = (Oas30ServerVariable) server30.createServerVariable("scheme");
                server30.addServerVariable("scheme", var30);
                var30.default_ = schemes.get(0);
                var30.enum_ = NodeCompat.copyList(schemes);
                var30.description = "The supported protocol schemes.";
            }
        }

        this.mapNode(doc20, this.doc30);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitInfo(io.apicurio.datamodels.core.models.common.Info)
     */
    @Override
    public void visitInfo(Info node) {
        this.doc30.info = this.doc30.createInfo();
        this.doc30.info.title = node.title;

        this.doc30.info.description = node.description;
        this.doc30.info.termsOfService = node.termsOfService;
        this.doc30.info.version = node.version;

        this.mapNode(node, this.doc30.info);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitContact(io.apicurio.datamodels.core.models.common.Contact)
     */
    @Override
    public void visitContact(Contact node) {
        Oas30Info info30 = (Oas30Info) this.lookup(node.parent());
        Oas30Contact contact30 = (Oas30Contact) info30.createContact();
        info30.contact = contact30;
        contact30.name = node.name;
        contact30.url = node.url;
        contact30.email = node.email;

        this.mapNode(node, contact30);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitLicense(io.apicurio.datamodels.core.models.common.License)
     */
    @Override
    public void visitLicense(License node) {
        Oas30Info info30 = (Oas30Info) this.lookup(node.parent());
        Oas30License license30 = (Oas30License) info30.createLicense();
        info30.license = license30;
        license30.name = node.name;
        license30.url = node.url;

        this.mapNode(node, license30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPaths(io.apicurio.datamodels.openapi.models.OasPaths)
     */
    @Override
    public void visitPaths(OasPaths node) {
        this.doc30.paths = this.doc30.createPaths();

        this.mapNode(node, this.doc30.paths);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void visitPathItem(OasPathItem node) {
        Oas30Paths paths30 = (Oas30Paths) this.lookup(node.parent());
        Oas30PathItem pathItem30 = (Oas30PathItem) paths30.createPathItem(node.getPath());
        paths30.addPathItem(node.getPath(), pathItem30);

        pathItem30.$ref = this.updateRef(node.$ref);

        this.mapNode(node, pathItem30);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        Oas20Operation op = (Oas20Operation) node;
        Oas30PathItem pathItem30 = (Oas30PathItem) this.lookup(node.parent());
        Oas30Operation operation30 = (Oas30Operation) pathItem30.createOperation(node.getType());
        pathItem30.setOperation(operation30);

        operation30.tags = op.tags;
        operation30.summary = op.summary;
        operation30.description = op.description;
        operation30.operationId = op.operationId;
        operation30.deprecated = op.deprecated;

        if (!NodeCompat.isNullOrUndefined(op.schemes) &&
                op.schemes.size() > 0 &&
                !NodeCompat.isNullOrUndefined(this.doc30.servers) &&
                this.doc30.servers.size() > 0)
        {
            Oas30Server server30 = (Oas30Server) operation30.createServer();
            operation30.servers = new ArrayList<>();
            operation30.servers.add(server30);

            server30.url = this.doc30.servers.get(0).url;
            if (op.schemes.size() == 1) {
                server30.url = server30.url.replace("{scheme}", op.schemes.get(0));
                server30.removeServerVariable("scheme");
            } else {
                server30.url = "{scheme}" + server30.url.substring(server30.url.indexOf("://"));
                Oas30ServerVariable var30 = (Oas30ServerVariable) server30.createServerVariable("scheme");
                server30.addServerVariable("scheme", var30);
                var30.description = "The supported protocol schemes.";
                var30.default_ = op.schemes.get(0);
                var30.enum_ = NodeCompat.copyList(op.schemes);
            }
        }

        // Note: consumes/produces will be handled elsewhere (when Request Body and Response models are created)

        this.mapNode(op, operation30);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitParameter(io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        Oas20Parameter param20 = (Oas20Parameter) node;
        if (NodeCompat.equals(param20.in, "body")) {
            Oas30Operation operation30 = (Oas30Operation) this.lookup(this.findParentOperation(param20));
            if (!NodeCompat.isNullOrUndefined(operation30)) {
                Oas30RequestBody body30 = operation30.createRequestBody();
                operation30.requestBody = body30;

                body30.description = param20.description;
                body30.required = param20.required;

                if (!NodeCompat.isNullOrUndefined(param20.schema)) {
                    List<String> consumes = this.findConsumes(param20);
                    Oas20Schema schema = (Oas20Schema) param20.schema;
                    consumes.forEach( ct -> {
                        Oas30MediaType mediaType30 = body30.createMediaType(ct);
                        body30.addMediaType(ct, mediaType30);

                        Oas30Schema schema30 = mediaType30.createSchema();
                        mediaType30.schema = this.toSchema(schema, schema30, true);

                        this.mapNode(schema, schema30);
                    });
                }
            }
        } else if (NodeCompat.equals(param20.in, "formData")) {
            Oas30Operation operation30 = (Oas30Operation) this.lookup(this.findParentOperation(param20));
            if (!NodeCompat.isNullOrUndefined(operation30)) {
                List<String> consumes = this.findConsumes(param20);
                if (!this.hasFormDataMimeType(consumes)) {
                    consumes = NodeCompat.asList("application/x-www-form-urlencoded");
                }
                consumes.forEach(ct -> {
                    if (this.isFormDataMimeType(ct)) {
                        Oas30RequestBody body30 = operation30.requestBody;
                        if (NodeCompat.isNullOrUndefined(body30)) {
                            body30 = operation30.createRequestBody();
                            operation30.requestBody = body30;
                            body30.required = true;
                        }
                        Oas30MediaType mediaType30 = body30.getMediaType(ct);
                        if (NodeCompat.isNullOrUndefined(mediaType30)) {
                            mediaType30 = body30.createMediaType(ct);
                            body30.addMediaType(ct, mediaType30);
                        }
                        Oas30Schema schema30 = mediaType30.schema;
                        if (NodeCompat.isNullOrUndefined(schema30)) {
                            schema30 = mediaType30.createSchema();
                            mediaType30.schema = schema30;
                            schema30.type = "object";
                        }

                        Oas30PropertySchema property30 = (Oas30PropertySchema) schema30.createPropertySchema(param20.name);
                        schema30.addProperty(param20.name, property30);
                        property30.description = param20.description;
                        this.toSchema(param20, property30, false);

                        this.mapNode(param20, schema30);
                    }
                });
            }
        } else {
            if (this.isRef(param20)) {
                Oas20ParameterDefinition paramDef = (Oas20ParameterDefinition) ReferenceUtil.resolveRef(param20.$ref, param20);

                // Handle the case where there is a parameter $ref to a "body" param.  All body params become
                // Request Bodies.  So a reference to a "body" param should become a reference to a request body.
                if (!NodeCompat.isNullOrUndefined(paramDef) && NodeCompat.equals(paramDef.in, "body")) {
                    Oas30Operation parent30 = (Oas30Operation) this.lookup(this.findParentOperation(param20));
                    if (!NodeCompat.isNullOrUndefined(parent30)) {
                        Oas30RequestBody body30 = parent30.createRequestBody();
                        parent30.requestBody = body30;

                        body30.$ref = "#/components/requestBodies/" + paramDef.getName();

                        this.mapNode(param20, body30);
                        return;
                    }
                }

                // Handle the case where the parameter is a $ref to a formData param.  In this case we want to
                // treat the param as though it is inlined (which will result in a requestBody model).
                if (!NodeCompat.isNullOrUndefined(paramDef) && NodeCompat.equals(paramDef.in, "formData")) {
                    // Inline the parameter definition and then re-visit it.
                    NodeUtil.readNode(NodeUtil.writeNode(paramDef), param20);
                    param20.$ref = null;
                    this.visitParameter(param20);
                    return;
                }
            }

            // Now we have normal handling of a parameter, examples include path params, query params, header params, etc.
            IOasParameterParent parent30 = (IOasParameterParent) this.lookup(param20.parent());
            Oas30Parameter param30 = (Oas30Parameter) parent30.createParameter();
            parent30.addParameter(param30);
            this.transformParam(param20, param30);
            this.mapNode(param20, param30);
        }
    }

    private Oas30Parameter transformParam(Oas20Parameter node, Oas30Parameter param30) {
        param30.$ref = this.updateRef(node.$ref);
        if (!NodeCompat.isNullOrUndefined(param30.$ref)) {
            return param30;
        }
        param30.name = node.name;
        param30.in = node.in;
        param30.description = node.description;
        param30.required = node.required;
        param30.allowEmptyValue = node.allowEmptyValue;
        param30.schema = this.toSchema(node, (Oas30Schema) param30.createSchema(), false);
        this.collectionFormatToStyleAndExplode(node, param30);
        return param30;
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitParameterDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitParameterDefinition(IDefinition node) {
        Oas20ParameterDefinition pd20 = (Oas20ParameterDefinition) node;
        if (NodeCompat.equals(pd20.in, "body")) {
            Oas30Components parent30 = this.getOrCreateComponents();
            Oas30RequestBodyDefinition bodyDef30 = parent30.createRequestBodyDefinition(pd20.getName());
            parent30.addRequestBodyDefinition(pd20.getName(), bodyDef30);

            bodyDef30.description = pd20.description;
            bodyDef30.required = pd20.required;
            if (!NodeCompat.isNullOrUndefined(pd20.schema)) {
                List<String> consumes = this.findConsumes(pd20);
                Oas20Schema schema = (Oas20Schema) pd20.schema;
                consumes.forEach( ct -> {
                    Oas30MediaType mediaType30 = bodyDef30.createMediaType(ct);
                    bodyDef30.addMediaType(ct, mediaType30);

                    Oas30Schema schema30 = mediaType30.createSchema();
                    mediaType30.schema = this.toSchema(schema, schema30, true);

                    this.mapNode(schema, schema30);
                });
            }
        } else if (NodeCompat.equals(pd20.in, "formData")) {
            // Exclude any re-usable formData parameters - they are currently being inlined elsewhere.  I'm not sure
            // what we would do with them anyway.
        } else {
            Oas30Components components30 = this.getOrCreateComponents();
            Oas30ParameterDefinition paramDef30 = components30.createParameterDefinition(pd20.getName());
            components30.addParameterDefinition(pd20.getName(), paramDef30);
            this.transformParam(pd20, paramDef30);
            this.mapNode(pd20, paramDef30);
        }
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExternalDocumentation(io.apicurio.datamodels.core.models.common.ExternalDocumentation)
     */
    @Override
    public void visitExternalDocumentation(ExternalDocumentation node) {
        IExternalDocumentationParent parent30 = (IExternalDocumentationParent) this.lookup(node.parent());
        Oas30ExternalDocumentation externalDocs30 = (Oas30ExternalDocumentation) parent30.createExternalDocumentation();
        parent30.setExternalDocumentation(externalDocs30);

        externalDocs30.description = node.description;
        externalDocs30.url = node.url;

        this.mapNode(node, externalDocs30);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityRequirement(io.apicurio.datamodels.core.models.common.SecurityRequirement)
     */
    @Override
    public void visitSecurityRequirement(SecurityRequirement node) {
        Oas20SecurityRequirement req = (Oas20SecurityRequirement) node;

        ISecurityRequirementParent parent30 = (ISecurityRequirementParent) this.lookup(req.parent());
        Oas30SecurityRequirement securityRequirement30 = (Oas30SecurityRequirement) parent30.createSecurityRequirement();
        parent30.addSecurityRequirement(securityRequirement30);

        req.getSecurityRequirementNames().forEach( name -> {
            securityRequirement30.addSecurityRequirementItem(name, req.getScopes(name));
        });

        this.mapNode(req, securityRequirement30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponses(io.apicurio.datamodels.openapi.models.OasResponses)
     */
    @Override
    public void visitResponses(OasResponses node) {
        Oas30Operation parent30 = (Oas30Operation) this.lookup(node.parent());
        Oas30Responses responses30 = (Oas30Responses) parent30.createResponses();
        parent30.responses = responses30;

        this.mapNode(node, responses30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponse(io.apicurio.datamodels.openapi.models.OasResponse)
     */
    @Override
    public void visitResponse(OasResponse node) {
        Oas30Responses parent30 = (Oas30Responses) this.lookup(node.parent());
        Oas30Response response30 = (Oas30Response) parent30.createResponse(node.getStatusCode());
        parent30.addResponse(node.getStatusCode(), response30);

        response30.$ref = this.updateRef(node.$ref);
        this.transformResponse((Oas20Response) node, response30);

        this.mapNode(node, response30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitResponseDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitResponseDefinition(IDefinition node) {
        Oas30Components parent30 = this.getOrCreateComponents();
        Oas30ResponseDefinition responseDef30 = parent30.createResponseDefinition(node.getName());
        parent30.addResponseDefinition(node.getName(), responseDef30);

        this.transformResponse((Oas20Response) node, responseDef30);

        this.mapNode((Oas20Response) node, responseDef30);
    }

    private void transformResponse(Oas20Response node, Oas30Response response30) {
        response30.description = node.description;

        if (!NodeCompat.isNullOrUndefined(node.schema)) {
            List<String> produces = this.findProduces(node);
            Oas20Schema schema = node.schema;
            produces.forEach( ct -> {
                Oas30MediaType mediaType30 = response30.createMediaType(ct);
                response30.addMediaType(ct, mediaType30);

                Oas30Schema schema30 = mediaType30.createSchema();
                mediaType30.schema = this.toSchema(schema, schema30, true);

                if (!NodeCompat.isNullOrUndefined(node.examples)) {
                    Object ctexample = node.examples.getExample(ct);
                    if (!NodeCompat.isNullOrUndefined(ctexample)) {
                        mediaType30.example = ctexample;
                    }
                }

                this.mapNode(schema, schema30);
            });
            // mark the Response as needing Content post-processing
            if (produces.size() > 1) {
                this._postProcessResponses.add(response30);
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSchema(io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    public void visitSchema(Schema node) {
        // In 2.0, Schemas can only be located on responses and parameters.  In both cases, we
        // handle processing and indexing the schema in their respective visit methods - so we
        // can skip doing that here.
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitHeaders(io.apicurio.datamodels.openapi.v2.models.Oas20Headers)
     */
    @Override
    public void visitHeaders(Oas20Headers node) {
        Oas30Response parent30 = (Oas30Response) this.lookup(node.parent());
        // No processing to do here, because 3.0 doesn't have a "headers" node.  So instead
        // we'll map the headers node to the 3.0 response node, because that will be the
        // effective parent for any 3.0 Header nodes.
        this.mapNode(node, parent30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitHeader(io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    public void visitHeader(OasHeader node) {
        Oas30Response parent30 = (Oas30Response) this.lookup(node.parent());
        Oas30Header header30 = parent30.createHeader(node.getName());
        parent30.addHeader(node.getName(), header30);

        header30.description = node.description;
        header30.schema = this.toSchema(node, header30.createSchema(), false);

        this.mapNode(node, header30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitExample(io.apicurio.datamodels.core.models.common.IExample)
     */
    @Override
    public void visitExample(IExample node) {
        // Examples are processed as part of "transformResponse"
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitItems(io.apicurio.datamodels.openapi.v2.models.Oas20Items)
     */
    @Override
    public void visitItems(Oas20Items node) {
        Oas30Schema parent30 = this.findItemsParent(node);
        Oas30ItemsSchema items30 = (Oas30ItemsSchema) parent30.createItemsSchema();
        parent30.items = items30;

        this.toSchema(node, items30, false);

        this.mapNode(node, items30);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitTag(io.apicurio.datamodels.core.models.common.Tag)
     */
    @Override
    public void visitTag(Tag node) {
        Oas30Document parent30 = this.doc30;
        Tag tag30 = parent30.addTag(node.name, node.description);
        this.mapNode(node, tag30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitSecurityDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions)
     */
    @Override
    public void visitSecurityDefinitions(Oas20SecurityDefinitions node) {
        // OpenAPI 3 has no "Security Definitions" wrapper entity.
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        Oas20SecurityScheme scheme = (Oas20SecurityScheme) node;
        Oas30Components parent30 = this.getOrCreateComponents();
        Oas30SecurityScheme scheme30 = parent30.createSecurityScheme(scheme.getName());
        parent30.addSecurityScheme(scheme.getName(), scheme30);

        scheme30.type = scheme.type;
        scheme30.description = scheme.description;
        scheme30.name = scheme.name;
        scheme30.in = scheme.in;

        if (NodeCompat.equals(scheme.type, "oauth2")) {
            if (NodeCompat.equals(scheme.flow, "implicit")) {
                scheme30.flows = scheme30.createOAuthFlows();
                scheme30.flows.implicit = scheme30.flows.createImplicitOAuthFlow();
                scheme30.flows.implicit.authorizationUrl = scheme.authorizationUrl;
                if (!NodeCompat.isNullOrUndefined(scheme.scopes)) {
                    scheme.scopes.getScopeNames().forEach( scopeName -> {
                        scheme30.flows.implicit.addScope(scopeName, scheme.scopes.getScopeDescription(scopeName));
                    });
                }
            }
            if (NodeCompat.equals(scheme.flow, "accessCode")) {
                scheme30.flows = scheme30.createOAuthFlows();
                scheme30.flows.authorizationCode = scheme30.flows.createAuthorizationCodeOAuthFlow();
                scheme30.flows.authorizationCode.authorizationUrl = scheme.authorizationUrl;
                scheme30.flows.authorizationCode.tokenUrl = scheme.tokenUrl;
                if (!NodeCompat.isNullOrUndefined(scheme.scopes)) {
                    scheme.scopes.getScopeNames().forEach( scopeName -> {
                        scheme30.flows.authorizationCode.addScope(scopeName, scheme.scopes.getScopeDescription(scopeName));
                    });
                }
            }
            if (NodeCompat.equals(scheme.flow, "password")) {
                scheme30.flows = scheme30.createOAuthFlows();
                scheme30.flows.password = scheme30.flows.createPasswordOAuthFlow();
                scheme30.flows.password.tokenUrl = scheme.tokenUrl;
                if (!NodeCompat.isNullOrUndefined(scheme.scopes)) {
                    scheme.scopes.getScopeNames().forEach( scopeName -> {
                        scheme30.flows.password.addScope(scopeName, scheme.scopes.getScopeDescription(scopeName));
                    });
                }
            }
            if (NodeCompat.equals(scheme.flow, "application")) {
                scheme30.flows = scheme30.createOAuthFlows();
                scheme30.flows.clientCredentials = scheme30.flows.createClientCredentialsOAuthFlow();
                scheme30.flows.clientCredentials.tokenUrl = scheme.tokenUrl;
                if (!NodeCompat.isNullOrUndefined(scheme.scopes)) {
                    scheme.scopes.getScopeNames().forEach( scopeName -> {
                        scheme30.flows.clientCredentials.addScope(scopeName, scheme.scopes.getScopeDescription(scopeName));
                    });
                }
            }
        }

        this.mapNode(scheme, scheme30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitScopes(io.apicurio.datamodels.openapi.v2.models.Oas20Scopes)
     */
    @Override
    public void visitScopes(Oas20Scopes node) {
        // Note: scopes are handled during the processing of the security scheme.  See `visitSecurityScheme` for details.
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitXML(io.apicurio.datamodels.openapi.models.OasXML)
     */
    @Override
    public void visitXML(OasXML node) {
        Oas30Schema parent30 = (Oas30Schema) this.lookup(node.parent());
        Oas30XML xml30 = (Oas30XML) parent30.createXML();
        parent30.xml = xml30;

        xml30.name = node.name;
        xml30.namespace = node.namespace;
        xml30.prefix = node.prefix;
        xml30.attribute = node.attribute;
        xml30.wrapped = node.wrapped;

        this.mapNode(node, xml30);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitSchemaDefinition(io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    public void visitSchemaDefinition(IDefinition node) {
        Oas20SchemaDefinition sd20 = (Oas20SchemaDefinition) node;

        Oas30Components parent30 = this.getOrCreateComponents();
        Oas30SchemaDefinition schemaDef30 = parent30.createSchemaDefinition(sd20.getName());
        parent30.addSchemaDefinition(sd20.getName(), schemaDef30);

        this.toSchema(sd20, schemaDef30, true);

        this.mapNode(sd20, schemaDef30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitPropertySchema(io.apicurio.datamodels.core.models.common.IPropertySchema)
     */
    @Override
    public void visitPropertySchema(IPropertySchema node) {
        Oas20PropertySchema ps20 = (Oas20PropertySchema) node;

        Oas30Schema parent30 = (Oas30Schema) this.lookup(ps20.parent());
        Oas30PropertySchema property30 = (Oas30PropertySchema) parent30.createPropertySchema(ps20.getPropertyName());
        parent30.addProperty(ps20.getPropertyName(), property30);

        this.toSchema(ps20, property30, true);

        this.mapNode(ps20, property30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitAdditionalPropertiesSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAdditionalPropertiesSchema(OasSchema node) {
        Oas30Schema parent30 = (Oas30Schema) this.lookup(node.parent());
        Oas30AdditionalPropertiesSchema additionalProps30 = (Oas30AdditionalPropertiesSchema) parent30.createAdditionalPropertiesSchema();
        parent30.additionalProperties = additionalProps30;

        this.toSchema(node, additionalProps30, true);

        this.mapNode(node, additionalProps30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitAllOfSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitAllOfSchema(OasSchema node) {
        Oas30Schema parent30 =  (Oas30Schema) this.lookup(node.parent());
        Oas30AllOfSchema allOf30 = (Oas30AllOfSchema) parent30.createAllOfSchema();
        if (NodeCompat.isNullOrUndefined(parent30.allOf)) {
            parent30.allOf = new ArrayList<>();
        }
        parent30.allOf.add(allOf30);

        this.toSchema(node, allOf30, true);

        this.mapNode(node, allOf30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitItemsSchema(io.apicurio.datamodels.openapi.models.OasSchema)
     */
    @Override
    public void visitItemsSchema(OasSchema node) {
        Oas30Schema parent30 = (Oas30Schema) this.lookup(node.parent());
        Oas30ItemsSchema items30 = (Oas30ItemsSchema) parent30.createItemsSchema();
        if (!NodeCompat.isNullOrUndefined(parent30.items) && NodeCompat.isNode(parent30.items)) {
            List<Oas30ItemsSchema> items = new ArrayList<>();
            items.add(items30);
            parent30.items = items;
        } else {
            parent30.items = items30;
        }

        this.toSchema(node, items30, true);

        this.mapNode(node, items30);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20Definitions)
     */
    @Override
    public void visitDefinitions(Oas20Definitions node) {
        // Note: there is no "definitions" entity in 3.0, so nothing to do here.
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitParameterDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions)
     */
    @Override
    public void visitParameterDefinitions(Oas20ParameterDefinitions node) {
        // Note: there is no "parameters definitions" entity in 3.0, so nothing to do here.
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitResponseDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions)
     */
    @Override
    public void visitResponseDefinitions(Oas20ResponseDefinitions node) {
        // Note: there is no "responses definitions" entity in 3.0, so nothing to do here.
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitExtension(io.apicurio.datamodels.core.models.Extension)
     */
    @Override
    public void visitExtension(Extension node) {
        ExtensibleNode parent30 = (ExtensibleNode) this.lookup(node.parent());
        Extension ext30 = parent30.createExtension();
        ext30.name = node.name;
        ext30.value = node.value;
        parent30.addExtension(node.name, ext30);
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitValidationProblem(io.apicurio.datamodels.core.models.ValidationProblem)
     */
    @Override
    public void visitValidationProblem(ValidationProblem problem) {
        // Note: nothing to do for a validation problem
    }

    private void mapNode(Node from, Node to) {
        NodePath nodePath = NodePathUtil.createNodePath(from);
        String mapIndex = nodePath.toString();
        this._nodeMap.put(mapIndex, to);
    }

    private Node lookup(Node node) {
        NodePath nodePath = NodePathUtil.createNodePath(node);
        String mapIndex = nodePath.toString();
        return this._nodeMap.get(mapIndex);
    }

    private Oas30Components getOrCreateComponents() {
        if (NodeCompat.isNullOrUndefined(this.doc30.components)) {
            this.doc30.components = this.doc30.createComponents();
        }
        return this.doc30.components;
    }

    // from : Oas20ParameterBase | Oas20Header | Oas20Items | Oas20Schema | Oas20SchemaDefinition
    @SuppressWarnings("unchecked")
    private Oas30Schema toSchema(Node from, Oas30Schema schema30, boolean isSchema) {
        String type = (String) NodeCompat.getProperty(from, Constants.PROP_TYPE);
        String format = (String) NodeCompat.getProperty(from, Constants.PROP_FORMAT);
        Object items = NodeCompat.getProperty(from, Constants.PROP_ITEMS);
        Object default_ = NodeCompat.getProperty(from, Constants.PROP_DEFAULT);
        Number maximum = (Number) NodeCompat.getProperty(from, Constants.PROP_MAXIMUM);
        Boolean exclusiveMaximum = (Boolean) NodeCompat.getProperty(from, Constants.PROP_EXCLUSIVE_MAXIMUM);
        Number minimum = (Number) NodeCompat.getProperty(from, Constants.PROP_MINIMUM);
        Boolean exclusiveMinimum = (Boolean) NodeCompat.getProperty(from, Constants.PROP_EXCLUSIVE_MINIMUM);
        Number maxLength = (Number) NodeCompat.getProperty(from, Constants.PROP_MAX_LENGTH);
        Number minLength = (Number) NodeCompat.getProperty(from, Constants.PROP_MIN_LENGTH);
        String pattern = (String) NodeCompat.getProperty(from, Constants.PROP_PATTERN);
        Number maxItems = (Number) NodeCompat.getProperty(from, Constants.PROP_MAX_ITEMS);
        Number minItems = (Number) NodeCompat.getProperty(from, Constants.PROP_MIN_ITEMS);
        Boolean uniqueItems = (Boolean) NodeCompat.getProperty(from, Constants.PROP_UNIQUE_ITEMS);
        List<String> enum_ = (List<String>) NodeCompat.getProperty(from, Constants.PROP_ENUM);
        Number multipleOf = (Number) NodeCompat.getProperty(from, Constants.PROP_MULTIPLE_OF);

        schema30.type = type;
        schema30.format = format;
        if (NodeCompat.equals(type, "file")) {
            schema30.type = "string";
            schema30.format = "binary";
        }
        if (NodeCompat.isNode(items)) {
            // This is done so that we can lookup the appropriate parent for an "Items" object later
            // on in the visit.  This is a special case because we're introducing a new Oas30Schema
            // entity in between e.g. a Response and the ItemsSchema - whereas in 2.0 the ItemsSchema
            // is a direct child of Response and Parameter.  So when visiting a Items, we cannot lookup
            // the new 3.0 Schema using the Items' parent (because the parent maps to something else -
            // the grandparent, in fact).  THIS IS ONLY A PROBLEM FOR "ITEMS" ON PARAM AND RESPONSE.
            ((Node) items).setAttribute("_transformation_items-parent", schema30);
        } else if (NodeCompat.isList(items)) {
            // TODO handle the case where "items" is a list of items!!
        }
        // Note: Not sure what to do with the "collectionFormat" of a schema.  Dropping it for now.
        //schema30.collectionFormat = collectionFormat;
        schema30.default_ = default_;
        schema30.maximum = maximum;
        schema30.exclusiveMaximum = exclusiveMaximum;
        schema30.minimum = minimum;
        schema30.exclusiveMinimum = exclusiveMinimum;
        schema30.maxLength = maxLength;
        schema30.minLength = minLength;
        schema30.pattern = pattern;
        schema30.maxItems = maxItems;
        schema30.minItems = minItems;
        schema30.uniqueItems = uniqueItems;
        schema30.enum_ = enum_;
        schema30.multipleOf = multipleOf;

        if (isSchema) {
            Oas20Schema schema20 = (Oas20Schema) from;
            schema30.$ref = this.updateRef(schema20.$ref);

            if (schema20.hasAdditionalPropertiesBoolean()) {
                schema30.additionalProperties = schema20.additionalProperties;
            }
            schema30.readOnly = schema20.readOnly;
            schema30.example = schema20.example;
            schema30.title = schema20.title;
            schema30.description = schema20.description;
            schema30.maxProperties = schema20.maxProperties;
            schema30.minProperties = schema20.minProperties;
            schema30.required = schema20.required;

            if (!NodeCompat.isNullOrUndefined(schema20.discriminator)) {
                schema30.discriminator = schema30.createDiscriminator();
                schema30.discriminator.propertyName = schema20.discriminator;
            }
        }

        return schema30;
    }

    private Oas30Schema findItemsParent(Oas20Items node) {
        Oas30Schema itemsParent = (Oas30Schema) node.getAttribute("_transformation_items-parent");
        if (NodeCompat.isNullOrUndefined(itemsParent)) {
            itemsParent = (Oas30Schema) this.lookup(node.parent());
        }
        return itemsParent;
    }

    private Oas20Operation findParentOperation(Parameter node) {
        OperationFinder finder = new OperationFinder();
        VisitorUtil.visitTree(node, finder, TraverserDirection.up);
        return (Oas20Operation) finder.found;
    }

    private List<String> findProduces(Node node) {
        ConsumesProducesFinder finder = new ConsumesProducesFinder();
        VisitorUtil.visitTree(node, finder, TraverserDirection.up);
        List<String> produces = finder.produces;
        if (NodeCompat.isNullOrUndefined(produces) || produces.size() == 0) {
            produces = new ArrayList<>();
            produces.add("*/*");
        }
        return produces;
    }

    private List<String> findConsumes(Node node) {
        ConsumesProducesFinder finder = new ConsumesProducesFinder();
        VisitorUtil.visitTree(node, finder, TraverserDirection.up);
        List<String> consumes = finder.consumes;
        if (NodeCompat.isNullOrUndefined(consumes) || consumes.size() == 0) {
            consumes = new ArrayList<>();
            consumes.add("*/*");
        }
        return consumes;
    }

    private void collectionFormatToStyleAndExplode(Oas20Parameter node, Oas30Parameter param30) {
        if (NodeCompat.equals(node.type, "array") && NodeCompat.equals(node.collectionFormat, "multi") && (NodeCompat.equals(node.in, "query") || NodeCompat.equals(node.in, "cookie"))) {
            param30.style = "form";
            param30.explode = true;
            return;
        }
        if (NodeCompat.equals(node.type, "array") && NodeCompat.equals(node.collectionFormat, "csv") && (NodeCompat.equals(node.in, "query") || NodeCompat.equals(node.in, "cookie"))) {
            param30.style = "form";
            param30.explode = false;
            return;
        }
        if (NodeCompat.equals(node.type, "array") && NodeCompat.equals(node.collectionFormat, "csv") && (NodeCompat.equals(node.in, "path") || NodeCompat.equals(node.in, "header"))) {
            param30.style = "simple";
            return;
        }
        if (NodeCompat.equals(node.type, "array") && NodeCompat.equals(node.collectionFormat, "ssv") && NodeCompat.equals(node.in, "query")) {
            param30.style = "spaceDelimited";
            return;
        }
        if (NodeCompat.equals(node.type, "array") && NodeCompat.equals(node.collectionFormat, "pipes") && NodeCompat.equals(node.in, "query")) {
            param30.style = "pipeDelimited";
            return;
        }
    }

    private boolean isFormDataMimeType(String mimetype) {
        return !NodeCompat.isNullOrUndefined(mimetype) && (NodeCompat.equals(mimetype, "multipart/form-data") || NodeCompat.equals(mimetype, "application/x-www-form-urlencoded"));
    }

    private boolean hasFormDataMimeType(List<String> mimetypes) {
        if (!NodeCompat.isNullOrUndefined(mimetypes)) {
            for (String mt : mimetypes) {
                if (this.isFormDataMimeType(mt)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRef(IReferenceNode node) {
        String $ref = (String) NodeCompat.getProperty(node, Constants.PROP_$REF);
        return !NodeCompat.isNullOrUndefined($ref) && $ref.length() > 0;
    }

    private String updateRef(String $ref) {
        if (NodeCompat.isNullOrUndefined($ref) || $ref.length() == 0) {
            return $ref;
        }
        String[] split = $ref.split("/");
        if (NodeCompat.equals(split[0], "#")) {
            if (NodeCompat.equals(split[1], "definitions")) {
                return $ref.replace("#/definitions/", "#/components/schemas/");
            } else if (NodeCompat.equals(split[1], "parameters")) {
                return $ref.replace("#/parameters/", "#/components/parameters/");
            } else if (NodeCompat.equals(split[1], "responses")) {
                return $ref.replace("#/responses/", "#/components/responses/");
            }
        }
        return $ref;
    }

    /**
     * Called when visiting is complete.  Any additional processing of the result can
     * be done here.
     */
    private void postProcess() {
        // Post process all of the responses that require it.  Responses may require post-processing
        // when a response has multiple @Produces content types, which results in multiple MimeType
        // entities in the 3.0 Response 'content'.  When this happens, only one of the mime types
        // will contain the visited (and thus transformed) data model.  So we must post-process them
        // to "clone" that info to the other mime types.  Otherwise we'll have a full mime type
        // definition for only ONE of the mime types, and partial definitions for the rest.
        this._postProcessResponses.forEach( response -> {
            // First, figure out which of the media types is the largest (has the most content)
            int largest = 0;
            Oas30MediaType srcMt = null;
            for (Oas30MediaType mt : response.getMediaTypes()) {
                int size = JsonCompat.stringify(NodeUtil.writeNode(mt.schema)).length();
                if (size > largest) {
                    largest = size;
                    srcMt = mt;
                }
            }
            // Now clone the contents of the largest media type into all the others.
            for (Oas30MediaType mt : response.getMediaTypes()) {
                if (srcMt != mt) {
                    Object src = NodeUtil.writeNode(srcMt.schema);
                    NodeUtil.readNode(src, mt.schema);
                }
            }
        });
    }

}
