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

package io.apicurio.datamodels.compat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * A compatibility layer containing methods used when performing validation.  Any code that must
 * be different in Java vs. TS/JS *and* related to validation should go in here.  There is a 
 * ValidationCompat.ts file that contains the TS/JS specific implementation of this class.
 * @author eric.wittmann@gmail.com
 */
public class ValidationCompat {

    public static ValidationRule instantiate(ValidationRuleMetaData ruleInfo) {
        try {
            Constructor<?> constructor = ruleInfo.ruleClass.getConstructor(ValidationRuleMetaData.class);
            return (ValidationRule) constructor.newInstance(ruleInfo);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException("Failed to instantiate rule: " + ruleInfo.name, e);
        }
    }
    
}
