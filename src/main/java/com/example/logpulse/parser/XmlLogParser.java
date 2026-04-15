package com.example.logpulse.parser;

import com.example.logpulse.model.LogEvent;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.InputStream;
import java.util.*;

public class XmlLogParser {

    public static List<LogEvent> parse(InputStream inputStream) {
        List<LogEvent> list = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
            dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            dbf.setXIncludeAware(false);
            dbf.setExpandEntityReferences(false);

            Document doc = dbf.newDocumentBuilder().parse(inputStream);
            NodeList n = doc.getElementsByTagName("Event");

            for (int i = 0; i < n.getLength(); i++) {
                Element e = (Element) n.item(i);
                int id = Integer.parseInt(e.getElementsByTagName("EventID").item(0).getTextContent());
                String time = e.getElementsByTagName("TimeCreated").item(0)
                        .getAttributes().getNamedItem("SystemTime").getTextContent();
                String user = "Unknown", ip = "Unknown";
                NodeList d = e.getElementsByTagName("Data");
                for (int j = 0; j < d.getLength(); j++) {
                    Element x = (Element) d.item(j);
                    if (x.getAttribute("Name").equals("TargetUserName"))
                        user = x.getTextContent();
                    if (x.getAttribute("Name").equals("IpAddress"))
                        ip = x.getTextContent();
                }
                list.add(new LogEvent(id, time, user, ip));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
