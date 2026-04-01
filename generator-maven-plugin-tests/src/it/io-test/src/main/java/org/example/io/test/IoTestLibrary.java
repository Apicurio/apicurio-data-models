package org.example.io.test;

import org.example.io.util.JsonUtil;
import org.example.io.v10.Iot10Document;
import org.example.io.v10.Iot10DocumentImpl;
import org.example.io.v10.io.Iot10ModelReader;
import org.example.io.v10.io.Iot10ModelWriter;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class IoTestLibrary {

    public static Iot10Document readDocument(String content) {
        ObjectNode json = (ObjectNode) JsonUtil.parseJSON(content);
        Iot10ModelReader reader = new Iot10ModelReader();
        Iot10Document document = new Iot10DocumentImpl();
        reader.readDocument(json, document);
        return document;
    }

    public static String writeDocument(Iot10Document document) {
        ObjectNode object = JsonUtil.objectNode();
        Iot10ModelWriter writer = new Iot10ModelWriter();
        writer.writeDocument(document, object);
        return JsonUtil.stringify(object);
    }

}
