package org.example.unionmap.test;

import org.example.unionmap.util.JsonUtil;
import org.example.unionmap.v10.Umtm10Document;
import org.example.unionmap.v10.Umtm10DocumentImpl;
import org.example.unionmap.v10.io.Umtm10ModelReader;
import org.example.unionmap.v10.io.Umtm10ModelWriter;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class UnionMapTestLibrary {

    public static Umtm10Document readDocument(String content) {
        ObjectNode json = (ObjectNode) JsonUtil.parseJSON(content);
        Umtm10ModelReader reader = new Umtm10ModelReader();
        Umtm10Document document = new Umtm10DocumentImpl();
        reader.readDocument(json, document);
        return document;
    }

    public static String writeDocument(Umtm10Document document) {
        ObjectNode object = JsonUtil.objectNode();
        Umtm10ModelWriter writer = new Umtm10ModelWriter();
        writer.writeDocument(document, object);
        return JsonUtil.stringify(object);
    }

}
