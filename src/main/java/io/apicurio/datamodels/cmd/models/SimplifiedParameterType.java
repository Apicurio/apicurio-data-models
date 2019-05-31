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

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v2.models.Oas20Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;

/**
 * @author eric.wittmann@gmail.com
 */
public class SimplifiedParameterType extends SimplifiedType {

    public static SimplifiedParameterType fromParameter(Parameter param) {
        SimplifiedParameterType rval = new SimplifiedParameterType();
        SimplifiedType st;
        if (param.ownerDocument().getDocumentType() == DocumentType.openapi2) {
            Oas20Parameter param20 = (Oas20Parameter) param;
            if (NodeCompat.equals(param20.in, "body")) {
                st = SimplifiedType.fromSchema((OasSchema) param20.schema);
            } else {
                st = SimplifiedType.fromParameter((Oas20Parameter) param);
            }
            rval.required = param20.required;
        } else {
            Oas30Parameter param30 = (Oas30Parameter) param;
            st = SimplifiedType.fromSchema((OasSchema) param30.schema);
            rval.required = param30.required;
        }

        rval.type = st.type;
        rval.enum_ = st.enum_;
        rval.of = st.of;
        rval.as = st.as;

        return rval;
    }

    public boolean required;

}
