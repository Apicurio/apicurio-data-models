package io.apicurio.datamodels.core.util;

import org.junit.Assert;
import org.junit.Test;


public class ValidatorsTest {

    @Test
    public void testRFC3986URI() {

        Assert.assertThrows("URIs should not be null", IllegalArgumentException.class, () -> {
            Validators.isValidRFC3986URI(null);
        } );

        Assert.assertTrue(Validators.isValidRFC3986URI("http://a/b/c/d;p?q"));

        // 5.4.1.  Normal Examples https://tools.ietf.org/html/rfc3986#section-5.4.1

        Assert.assertTrue(Validators.isValidRFC3986URI("g:h"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("./g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g/"));
        Assert.assertTrue(Validators.isValidRFC3986URI("/g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("//g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("?y"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g?y"));
        Assert.assertTrue(Validators.isValidRFC3986URI("#s"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g#s"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g?y#s"));
        Assert.assertTrue(Validators.isValidRFC3986URI(";x"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g;x"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g;x?y#s"));
        Assert.assertTrue(Validators.isValidRFC3986URI(""));
        Assert.assertTrue(Validators.isValidRFC3986URI("."));
        Assert.assertTrue(Validators.isValidRFC3986URI("./"));
        Assert.assertTrue(Validators.isValidRFC3986URI(".."));
        Assert.assertTrue(Validators.isValidRFC3986URI("../"));
        Assert.assertTrue(Validators.isValidRFC3986URI("../g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("../.."));
        Assert.assertTrue(Validators.isValidRFC3986URI("../../"));
        Assert.assertTrue(Validators.isValidRFC3986URI("../../g"));

        // 5.4.2.  Abnormal Examples https://tools.ietf.org/html/rfc3986#section-5.4.2

        Assert.assertTrue(Validators.isValidRFC3986URI("../../../g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("../../../../g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("/./g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("/../g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g."));
        Assert.assertTrue(Validators.isValidRFC3986URI(".g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g.."));
        Assert.assertTrue(Validators.isValidRFC3986URI("..g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("./../g"));
        Assert.assertTrue(Validators.isValidRFC3986URI("./g/."));
        Assert.assertTrue(Validators.isValidRFC3986URI("g/./h"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g/../h"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g;x=1/./y"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g;x=1/../y"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g?y/./x"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g?y/../x"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g#s/./x"));
        Assert.assertTrue(Validators.isValidRFC3986URI("g#s/../x"));
        Assert.assertTrue(Validators.isValidRFC3986URI("http:g"));

    }

}