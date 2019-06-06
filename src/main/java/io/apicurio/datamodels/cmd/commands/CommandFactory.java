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

package io.apicurio.datamodels.cmd.commands;

import java.util.List;

import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.cmd.models.SimplifiedParameterType;
import io.apicurio.datamodels.cmd.models.SimplifiedPropertyType;
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.ISecurityRequirementParent;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.v2.models.Oas20Response;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;

/**
 * This factory is used to create an instance of {@link ICommand} given a "type" that was previously
 * serialized as part of the marshalling of a command to a JS object (and ultimately a JSON string).
 * The type of command is typically its short classname, but not always.
 * @author eric.wittmann@gmail.com
 */
public class CommandFactory {

    /**
     * Called to create a command of a given type.
     * @param cmdType
     */
    @SuppressWarnings("rawtypes")
    public static ICommand create(String cmdType) {
        switch (cmdType) {

            case "AggregateCommand": 
            { return new AggregateCommand(); }

            /** Add Commands **/
            
            case "AddSchemaDefinitionCommand_20": 
            { return new AddSchemaDefinitionCommand_20(); }
            case "AddSchemaDefinitionCommand_30":
            { return new AddSchemaDefinitionCommand_30(); }
            case "AddExampleCommand_30":
            { return new AddExampleCommand_30(); }
            case "AddPathItemCommand":
            case "AddPathItemCommand_20":
            case "AddPathItemCommand_30":
            { return new AddPathItemCommand(); }
            case "AddSecurityRequirementCommand":
            { return new AddSecurityRequirementCommand(); }
            
            /** Change Commands **/
            
            case "ChangePropertyCommand_20": 
            case "ChangePropertyCommand_30":
            case "ChangePropertyCommand":
            { return new ChangePropertyCommand(); }
            case "ChangeDescriptionCommand_20": 
            case "ChangeDescriptionCommand_30":
            case "ChangeDescriptionCommand":
            { return new ChangeDescriptionCommand(); }
            case "ChangeTitleCommand_20": 
            case "ChangeTitleCommand_30":
            case "ChangeTitleCommand":
            { return new ChangeTitleCommand(); }
            case "ChangeVersionCommand_20": 
            case "ChangeVersionCommand_30":
            case "ChangeVersionCommand":
            { return new ChangeVersionCommand(); }
            case "ChangeContactCommand_20": 
            case "ChangeContactCommand_30":
            case "ChangeContactCommand":
            { return new ChangeContactCommand(); }
            case "ChangeLicenseCommand_20": 
            case "ChangeLicenseCommand_30":
            case "ChangeLicenseCommand":
            { return new ChangeLicenseCommand(); }
            case "ChangeMediaTypeTypeCommand":
            { return new ChangeMediaTypeTypeCommand(); }
            case "ChangeParameterTypeCommand_20": 
            { return new ChangeParameterTypeCommand_20(); }
            case "ChangeParameterDefinitionTypeCommand_20": 
            { return new ChangeParameterDefinitionTypeCommand_20(); }
            case "ChangeParameterTypeCommand_30":
            { return new ChangeParameterTypeCommand_30(); }
            case "ChangeParameterDefinitionTypeCommand_30":
            { return new ChangeParameterDefinitionTypeCommand_30(); }
            case "ChangePropertyTypeCommand":
            case "ChangePropertyTypeCommand_20":
            case "ChangePropertyTypeCommand_30":
            { return new ChangePropertyTypeCommand(); }
            case "ChangeResponseTypeCommand":
            case "ChangeResponseTypeCommand_20":
            case "ChangeResponseDefinitionTypeCommand_20":
            { return new ChangeResponseTypeCommand(); }
            case "ChangeSecuritySchemeCommand_20":
            { return new ChangeSecuritySchemeCommand_20(); }
            case "ChangeSecuritySchemeCommand_30":
            { return new ChangeSecuritySchemeCommand_30(); }
            case "ChangeServerCommand":
            { return new ChangeServerCommand(); }

            
            /** Replace Commands **/

            case "ReplaceOperationCommand_20": 
            case "ReplaceOperationCommand_30":
            case "ReplaceOperationCommand":
            { return new ReplaceOperationCommand(); }
            
            /** Delete Commands **/
            case "DeleteContactCommand_20": 
            case "DeleteContactCommand_30":
            case "DeleteContactCommand":
            { return new DeleteContactCommand(); }
                        
        }
        return null;
    }
    
    public static final AggregateCommand createAggregateCommand(String name, Object info, List<ICommand> commands) {
        return new AggregateCommand(name, info, commands);
    }

    /* ***  Add Commands  *** */
    
    public static final AddExampleCommand_30 createAddExampleCommand(Oas30MediaType mediaType, Object example, 
            String exampleName, String exampleSummary, String exampleDescription) {
        return new AddExampleCommand_30(mediaType, example, exampleName, exampleSummary, exampleDescription);
    }

    public static final AddSchemaDefinitionCommand createAddSchemaDefinitionCommand(DocumentType docType, 
            String definitionName, Object from) {
        if (docType == DocumentType.openapi2) {
            return new AddSchemaDefinitionCommand_20(definitionName, from);
        }
        if (docType == DocumentType.openapi3) {
            return new AddSchemaDefinitionCommand_30(definitionName, from);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final AddPathItemCommand createAddPathItemCommand(String pathItemName, Object from) {
        return new AddPathItemCommand(pathItemName, from);
    }

    public static final AddSecurityRequirementCommand createAddSecurityRequirementCommand(
            ISecurityRequirementParent parent, SecurityRequirement requirement) {
        return new AddSecurityRequirementCommand(parent, requirement);
    }

    
    /* ***  Change Commands  *** */

    public static <T> ChangePropertyCommand<T> createChangePropertyCommand(Node node, String property, T newValue) {
        return new ChangePropertyCommand<T>(node, property, newValue);
    }

    public static final ChangeTitleCommand createChangeTitleCommand(String newTitle) {
        return new ChangeTitleCommand(newTitle);
    }
    
    public static final ChangeDescriptionCommand createChangeDescriptionCommand(String newDescription) {
        return new ChangeDescriptionCommand(newDescription);
    }

    public static final ChangeVersionCommand createChangeVersionCommand(String newVersion) {
        return new ChangeVersionCommand(newVersion);
    }
    
    public static final ChangeContactCommand createChangeContactCommand(String name, String email, String url) {
        return new ChangeContactCommand(name, email, url);
    }
    
    public static final ChangeMediaTypeTypeCommand createChangeMediaTypeTypeCommand(Oas30MediaType mediaType, 
            SimplifiedType newType) {
        return new ChangeMediaTypeTypeCommand(mediaType, newType);
    }

    public static final ChangeParameterTypeCommand createChangeParameterTypeCommand(DocumentType docType, 
            Parameter parameter, SimplifiedParameterType newType) {
        if (docType == DocumentType.openapi2) {
            return new ChangeParameterTypeCommand_20(parameter, newType);
        }
        if (docType == DocumentType.openapi3) {
            return new ChangeParameterTypeCommand_30(parameter, newType);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ChangeParameterTypeCommand createChangeParameterDefinitionTypeCommand(DocumentType docType, 
            Parameter parameter, SimplifiedParameterType newType) {
        if (docType == DocumentType.openapi2) {
            return new ChangeParameterDefinitionTypeCommand_20(parameter, newType);
        }
        if (docType == DocumentType.openapi3) {
            return new ChangeParameterDefinitionTypeCommand_30(parameter, newType);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ChangePropertyTypeCommand createChangePropertyTypeCommand(IOasPropertySchema property, 
            SimplifiedPropertyType newType) {
        return new ChangePropertyTypeCommand(property, newType);
    }
    
    public static final ChangeResponseTypeCommand createChangeResponseTypeCommand(Oas20Response response, 
            SimplifiedType newType) {
        return new ChangeResponseTypeCommand(response, newType);
    }

    public static final ChangeResponseTypeCommand createChangeResponseDefinitionTypeCommand(
            Oas20ResponseDefinition response, SimplifiedType newType) {
        return new ChangeResponseTypeCommand(response, newType);
    }
    
    public static final ChangeSecuritySchemeCommand createChangeSecuritySchemeCommand(DocumentType docType, 
            SecurityScheme scheme) {
        if (docType == DocumentType.openapi2) {
            return new ChangeSecuritySchemeCommand_20(scheme);
        } else if (docType == DocumentType.openapi3) {
            return new ChangeSecuritySchemeCommand_30(scheme);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ChangeServerCommand createChangeServerCommand(Server server) {
        return new ChangeServerCommand(server);
    }

    /* ***  Replace Commands  *** */

    public static final ReplaceOperationCommand createReplaceOperationCommand(OasOperation old, 
            OasOperation replacement) {
        return new ReplaceOperationCommand(old, replacement);
    }

    /* ***  Delete Commands  *** */
    
    public static final DeleteContactCommand createDeleteContactCommand(Info info) {
        return new DeleteContactCommand(info);
    }

}
