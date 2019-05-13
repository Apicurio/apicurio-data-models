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

package io.apicurio.datamodels.core.validation.rules.invalid.name;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.core.validation.rules.OperationFinder;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v2.models.Oas20Example;
import io.apicurio.datamodels.openapi.v2.models.Oas20Operation;

/**
 * Implements the Unmatched Example Type Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasUnmatchedExampleTypeRule extends OasInvalidPropertyNameRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasUnmatchedExampleTypeRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * Finds the parent operation for the given node.
     * @param example
     */
    private static Operation findParentOperation(Node node) {
        OperationFinder finder = new OperationFinder();
        VisitorUtil.visitTree(node, finder, TraverserDirection.up);
        return finder.found;
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitExample(io.apicurio.datamodels.openapi.v2.models.Oas20Example)
     */
    @Override
    public void visitExample(Oas20Example node) {
        Oas20Document doc = (Oas20Document) node.ownerDocument();
        List<String> produces = doc.produces;
        Oas20Operation operation = (Oas20Operation) findParentOperation(node);
        if (hasValue(operation.produces)) {
            produces = operation.produces;
        }
        if (!hasValue(produces)) {
            produces = new ArrayList<>();
        }

        List<String> ctypes = node.getExampleContentTypes();
        for (String ct : ctypes) {
            this.reportIfInvalid(produces.indexOf(ct) != -1, node, Constants.PROP_PRODUCES, map("contentType", ct));
        }
    }

}
