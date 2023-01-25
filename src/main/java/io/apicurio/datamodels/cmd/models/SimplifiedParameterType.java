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

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Parameter;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * @author eric.wittmann@gmail.com
 */
public class SimplifiedParameterType extends SimplifiedType {

    public static SimplifiedParameterType fromParameter(OpenApiParameter param) {
        SimplifiedParameterType rval = new SimplifiedParameterType();
        SimplifiedType st;
        if (param.root().modelType() == ModelType.OPENAPI20) {
            OpenApi20Parameter param20 = (OpenApi20Parameter) param;
            if (NodeUtil.equals(param20.getIn(), "body")) {
                st = SimplifiedType.fromSchema((OpenApiSchema) param20.getSchema());
            } else {
                st = SimplifiedType.fromParameter((OpenApi20Parameter) param);
            }
            rval.required = param20.isRequired();
        } else {
            st = SimplifiedType.fromSchema((OpenApiSchema) param.getSchema());
            rval.required = param.isRequired();
        }

        rval.type = st.type;
        rval.enum_ = st.enum_;
        rval.of = st.of;
        rval.as = st.as;

        return rval;
    }

    public Boolean required;

}
