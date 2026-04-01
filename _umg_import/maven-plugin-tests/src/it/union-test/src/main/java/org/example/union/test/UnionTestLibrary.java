package org.example.union.test;

import org.example.union.util.JsonUtil;
import org.example.union.v10.Utm10Document;
import org.example.union.v10.Utm10DocumentImpl;
import org.example.union.v10.io.Utm10ModelReader;
import org.example.union.v10.io.Utm10ModelWriter;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class UnionTestLibrary {

    public static Utm10Document readDocument(String content) {
        ObjectNode json = (ObjectNode) JsonUtil.parseJSON(content);
        Utm10ModelReader reader = new Utm10ModelReader();
        Utm10Document document = new Utm10DocumentImpl();
        reader.readDocument(json, document);
        return document;
    }

    public static String writeDocument(Utm10Document document) {
        ObjectNode object = JsonUtil.objectNode();
        Utm10ModelWriter writer = new Utm10ModelWriter();
        writer.writeDocument(document, object);
        return JsonUtil.stringify(object);
    }

}
