package org.example.multi.test;

import org.example.multi.Document;
import org.example.multi.frst.v10.Frst10Document;
import org.example.multi.frst.v10.Frst10DocumentImpl;
import org.example.multi.frst.v10.io.Frst10ModelReader;
import org.example.multi.frst.v10.io.Frst10ModelWriter;
import org.example.multi.frst.v20.Frst20Document;
import org.example.multi.frst.v20.Frst20DocumentImpl;
import org.example.multi.frst.v20.io.Frst20ModelReader;
import org.example.multi.frst.v20.io.Frst20ModelWriter;
import org.example.multi.scnd.v10.Scnd10Document;
import org.example.multi.scnd.v10.Scnd10DocumentImpl;
import org.example.multi.scnd.v10.io.Scnd10ModelReader;
import org.example.multi.scnd.v10.io.Scnd10ModelWriter;
import org.example.multi.scnd.v20.Scnd20Document;
import org.example.multi.scnd.v20.Scnd20DocumentImpl;
import org.example.multi.scnd.v20.io.Scnd20ModelReader;
import org.example.multi.scnd.v20.io.Scnd20ModelWriter;
import org.example.multi.util.JsonUtil;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class MultiLevelSpecTestLibrary {

    public static Document readDocument(ObjectNode json) {
        String spec = json.get("spec").textValue();
        if ("first".equals(spec)) {
            return readFirstDocument(json);
        } else {
            return readSecondDocument(json);
        }
    }

    private static Document readFirstDocument(ObjectNode json) {
        String version = json.get("version").textValue();
        if ("1.0".equals(version)) {
            Frst10ModelReader reader = new Frst10ModelReader();
            Frst10Document document = new Frst10DocumentImpl();
            reader.readDocument(json, document);
            document.setSpec("first");
            document.setVersion(version);
            return document;
        } else {
            Frst20ModelReader reader = new Frst20ModelReader();
            Frst20Document document = new Frst20DocumentImpl();
            reader.readDocument(json, document);
            document.setSpec("first");
            document.setVersion(version);
            return document;
        }
    }

    private static Document readSecondDocument(ObjectNode json) {
        String version = json.get("version").textValue();
        if ("1.0".equals(version)) {
            Scnd10ModelReader reader = new Scnd10ModelReader();
            Scnd10Document document = new Scnd10DocumentImpl();
            reader.readDocument(json, document);
            document.setSpec("second");
            document.setVersion(version);
            return document;
        } else {
            Scnd20ModelReader reader = new Scnd20ModelReader();
            Scnd20Document document = new Scnd20DocumentImpl();
            reader.readDocument(json, document);
            document.setSpec("second");
            document.setVersion(version);
            return document;
        }
    }

    public static ObjectNode writeDocument(Document document) {
        ObjectNode object = JsonUtil.objectNode();
        if ("first".equals(document.getSpec())) {
            if ("1.0".equals(document.getVersion())) {
                Frst10ModelWriter writer = new Frst10ModelWriter();
                writer.writeDocument((Frst10Document) document, object);
            } else {
                Frst20ModelWriter writer = new Frst20ModelWriter();
                writer.writeDocument((Frst20Document) document, object);
            }
        } else {
            if ("1.0".equals(document.getVersion())) {
                Scnd10ModelWriter writer = new Scnd10ModelWriter();
                writer.writeDocument((Scnd10Document) document, object);
            } else {
                Scnd20ModelWriter writer = new Scnd20ModelWriter();
                writer.writeDocument((Scnd20Document) document, object);
            }
        }
        return object;
    }

}
