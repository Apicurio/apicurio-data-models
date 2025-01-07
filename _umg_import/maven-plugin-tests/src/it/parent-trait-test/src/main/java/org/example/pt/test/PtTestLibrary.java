package org.example.pt.test;

import org.example.pt.util.JsonUtil;
import org.example.pt.v10.Pt10Document;
import org.example.pt.v10.Pt10DocumentImpl;
import org.example.pt.v10.io.Pt10ModelReader;
import org.example.pt.v10.io.Pt10ModelWriter;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class PtTestLibrary {

    public static Pt10Document readDocument(String content) {
        ObjectNode json = (ObjectNode) JsonUtil.parseJSON(content);
        Pt10ModelReader reader = new Pt10ModelReader();
        Pt10Document document = new Pt10DocumentImpl();
        reader.readDocument(json, document);
        return document;
    }

    public static String writeDocument(Pt10Document document) {
        ObjectNode object = JsonUtil.objectNode();
        Pt10ModelWriter writer = new Pt10ModelWriter();
        writer.writeDocument(document, object);
        return JsonUtil.stringify(object);
    }

}
