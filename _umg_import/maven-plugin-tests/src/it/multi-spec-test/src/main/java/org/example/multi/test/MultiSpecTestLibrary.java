package org.example.multi.test;

import org.example.multi.MltiDocument;
import org.example.multi.util.JsonUtil;
import org.example.multi.v10.Mlti10Document;
import org.example.multi.v10.Mlti10DocumentImpl;
import org.example.multi.v10.io.Mlti10ModelReader;
import org.example.multi.v10.io.Mlti10ModelWriter;
import org.example.multi.v20.Mlti20Document;
import org.example.multi.v20.Mlti20DocumentImpl;
import org.example.multi.v20.io.Mlti20ModelReader;
import org.example.multi.v20.io.Mlti20ModelWriter;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class MultiSpecTestLibrary {

    public static MltiDocument readDocument(ObjectNode json) {
        String version = json.get("version").textValue();
        if ("1.0".equals(version)) {
            Mlti10ModelReader reader = new Mlti10ModelReader();
            Mlti10Document document = new Mlti10DocumentImpl();
            reader.readDocument(json, document);
            document.setVersion(version);
            return document;
        } else {
            Mlti20ModelReader reader = new Mlti20ModelReader();
            Mlti20Document document = new Mlti20DocumentImpl();
            reader.readDocument(json, document);
            document.setVersion(version);
            return document;
        }
    }

    public static ObjectNode writeDocument(MltiDocument document) {
        ObjectNode object = JsonUtil.objectNode();
        if ("1.0".equals(document.getVersion())) {
            Mlti10ModelWriter writer = new Mlti10ModelWriter();
            writer.writeDocument((Mlti10Document) document, object);
        } else {
            Mlti20ModelWriter writer = new Mlti20ModelWriter();
            writer.writeDocument((Mlti20Document) document, object);
        }
        return object;
    }

}
