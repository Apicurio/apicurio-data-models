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

import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.openapi.models.OasSchema;

/**
 * @author eric.wittmann@gmail.com
 */
public class SimplifiedPropertyType extends SimplifiedType {

    @SuppressWarnings("unchecked")
    public static SimplifiedPropertyType fromPropertySchema(IPropertySchema schema) {
        OasSchema s = (OasSchema) schema;
        SimplifiedPropertyType rval = new SimplifiedPropertyType();
        
        String propName = schema.getPropertyName();
        List<String> required = (List<String>) NodeCompat.getProperty(s.parent(), Constants.PROP_REQUIRED);

        SimplifiedType st = SimplifiedType.fromSchema((OasSchema) schema);
        rval.type = st.type;
        rval.enum_ = st.enum_;
        rval.of = st.of;
        rval.as = st.as;
        rval.required = false;
        if (ModelUtils.isDefined(required) && required.size() > 0 && required.indexOf(propName) != -1) {
            rval.required = true;
        }

        return rval;
    }

    public Boolean required;

}
