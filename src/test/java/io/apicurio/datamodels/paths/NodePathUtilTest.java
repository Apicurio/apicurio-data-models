package io.apicurio.datamodels.paths;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NodePathUtilTest {

    @Test
    public void testDetectPathParamNames() {
        List<String> names = NodePathUtil.detectPathParamNames("/path/to/something");
        Assert.assertNotNull(names);
        Assert.assertTrue(names.isEmpty());

        names = NodePathUtil.detectPathParamNames("/path/to/{somethingId}");
        Assert.assertNotNull(names);
        Assert.assertEquals(1, names.size());
        Assert.assertEquals("somethingId", names.get(0));

        names = NodePathUtil.detectPathParamNames("/{ pathId }/to/{somethingId}");
        Assert.assertNotNull(names);
        Assert.assertEquals(2, names.size());
        Assert.assertEquals("pathId", names.get(0));
        Assert.assertEquals("somethingId", names.get(1));
    }

}
