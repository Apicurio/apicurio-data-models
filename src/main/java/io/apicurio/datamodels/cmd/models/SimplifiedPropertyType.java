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

package io.apicurio.datamodels.cmd.models;

import java.util.List;

import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * @author eric.wittmann@gmail.com
 */
public class SimplifiedPropertyType extends SimplifiedType {

    @SuppressWarnings("unchecked")
    public static SimplifiedPropertyType fromPropertySchema(Schema schema) {
        SimplifiedPropertyType rval = new SimplifiedPropertyType();

        String propName = getPropertyName(schema);
        List<String> required = (List<String>) NodeUtil.getProperty(schema.parent(), "required");

        SimplifiedType st = SimplifiedType.fromSchema((OpenApiSchema) schema);
        rval.type = st.type;
        rval.enum_ = st.enum_;
        rval.of = st.of;
        rval.as = st.as;
        rval.required = false;
        if (NodeUtil.isDefined(required) && required.size() > 0 && required.indexOf(propName) != -1) {
            rval.required = true;
        }

        return rval;
    }

    private static String getPropertyName(Schema schema) {
        throw new UnsupportedOperationException("Getting a property name for a property schema not yet implemented!");
    }

    public Boolean required;

}
