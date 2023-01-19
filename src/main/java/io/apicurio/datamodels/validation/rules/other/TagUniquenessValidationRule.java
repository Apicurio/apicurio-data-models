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

package io.apicurio.datamodels.validation.rules.other;

import java.util.List;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Implements the Tag Name Uniqueness validation rule.
 * @author eric.wittmann@gmail.com
 */
public class TagUniquenessValidationRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public TagUniquenessValidationRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.AllNodeVisitor#visitTag(io.apicurio.datamodels.models.Tag)
     */
    @Override
    public void visitTag(Tag node) {
        List<Tag> tags = ((Document) node.root()).getTags();
        int tcount = 0;
        for (Tag tag : tags) {
            if (equals(tag.getName(), node.getName())) {
                tcount++;
            }
        }
        this.reportIf(tcount > 1, node, node.getName(), map("tagName", node.getName()));
    }

}
