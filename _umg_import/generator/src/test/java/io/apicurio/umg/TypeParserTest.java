package io.apicurio.umg;

import io.apicurio.umg.models.concept.PropertyType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class TypeParserTest {

    @Test
    public void run() throws Exception {

        var t1 = PropertyType.parse("foo");
        var t2 = PropertyType.parse("foo|bar");
        var t3 = PropertyType.parse("foo|bar|baz");
        var t4 = PropertyType.parse("{foo|bar}");
        var t5 = PropertyType.parse("{{foo|bar}}");
        var t6 = PropertyType.parse("{[foo]|{bar}|baz}|quox");

        Assert.assertEquals(
                PropertyType.builder().simple(true).simpleType("foo").build(),
                PropertyType.parse("foo"));

        Assert.assertEquals(
                PropertyType.builder().union(true).nested(Set.of(
                        PropertyType.builder().simple(true).simpleType("foo").build(),
                        PropertyType.builder().simple(true).simpleType("bar").build()
                )).build(),
                PropertyType.parse("foo|bar"));

        Assert.assertEquals(
                PropertyType.builder().union(true).nested(Set.of(
                        PropertyType.builder().simple(true).simpleType("foo").build(),
                        PropertyType.builder().simple(true).simpleType("bar").build(),
                        PropertyType.builder().simple(true).simpleType("baz").build()
                )).build(),
                PropertyType.parse("foo|bar|baz"));

        Assert.assertEquals(
                PropertyType.builder().map(true).nested(Set.of(
                        PropertyType.builder().union(true).nested(Set.of(
                                PropertyType.builder().simple(true).simpleType("foo").build(),
                                PropertyType.builder().simple(true).simpleType("bar").build()
                        )).build()
                )).build(),
                PropertyType.parse("{foo|bar}"));

        Assert.assertEquals(
                PropertyType.builder().map(true).nested(Set.of(
                        PropertyType.builder().map(true).nested(Set.of(
                                PropertyType.builder().union(true).nested(Set.of(
                                        PropertyType.builder().simple(true).simpleType("foo").build(),
                                        PropertyType.builder().simple(true).simpleType("bar").build()
                                )).build()
                        )).build()
                )).build(),
                PropertyType.parse("{{foo|bar}}"));

        Assert.assertEquals(
                PropertyType.builder().union(true).nested(Set.of(
                        PropertyType.builder().map(true).nested(Set.of(
                                PropertyType.builder().union(true).nested(Set.of(
                                        PropertyType.builder().list(true).nested(Set.of(
                                                PropertyType.builder().simple(true).simpleType("foo").build()
                                        )).build(),
                                        PropertyType.builder().map(true).nested(Set.of(
                                                PropertyType.builder().simple(true).simpleType("bar").build()
                                        )).build(),
                                        PropertyType.builder().simple(true).simpleType("baz").build()
                                )).build()
                        )).build(),
                        PropertyType.builder().simple(true).simpleType("quox").build()
                )).build(),
                PropertyType.parse("{[foo]|{bar}|baz}|quox"));
    }
}
