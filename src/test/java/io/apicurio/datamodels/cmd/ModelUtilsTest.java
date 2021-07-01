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

package io.apicurio.datamodels.cmd;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.apicurio.datamodels.cmd.util.ModelUtils;

/**
 * @author eric.wittmann@gmail.com
 */
public class ModelUtilsTest {

    @Test
    public void testDetectPathParamNames() {
        List<String> names = ModelUtils.detectPathParamNames("/path/to/something");
        Assert.assertNotNull(names);
        Assert.assertTrue(names.isEmpty());
        
        names = ModelUtils.detectPathParamNames("/path/to/{somethingId}");
        Assert.assertNotNull(names);
        Assert.assertEquals(1, names.size());
        Assert.assertEquals("somethingId", names.get(0));
        
        names = ModelUtils.detectPathParamNames("/{ pathId }/to/{somethingId}");
        Assert.assertNotNull(names);
        Assert.assertEquals(2, names.size());
        Assert.assertEquals("pathId", names.get(0));
        Assert.assertEquals("somethingId", names.get(1));
    }

}
