/*
 * Copyright 2020 JBoss Inc
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

package io.apicurio.datamodels.core.validation.rules.invalid.format;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author eric.wittmann@gmail.com
 */
public class AaiInvalidApiIdRuleTest {

    /**
     * Test method for {@link io.apicurio.datamodels.core.validation.rules.invalid.format.AaiInvalidApiIdRule#isValidId(java.lang.String)}.
     */
    @Test
    public void testIsValidId() {
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("http://a/b/c/d;p?q"));

        // 5.4.1.  Normal Examples https://tools.ietf.org/html/rfc3986#section-5.4.1

        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g:h"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("./g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g/"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("/g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("//g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("?y"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g?y"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("#s"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g#s"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g?y#s"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId(";x"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g;x"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g;x?y#s"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId(""));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("."));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("./"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId(".."));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("../"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("../g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("../.."));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("../../"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("../../g"));

        // 5.4.2.  Abnormal Examples https://tools.ietf.org/html/rfc3986#section-5.4.2

        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("../../../g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("../../../../g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("/./g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("/../g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g."));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId(".g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g.."));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("..g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("./../g"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("./g/."));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g/./h"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g/../h"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g;x=1/./y"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g;x=1/../y"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g?y/./x"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g?y/../x"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g#s/./x"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("g#s/../x"));
        Assert.assertTrue(AaiInvalidApiIdRule.isValidId("http:g"));

    }

}
