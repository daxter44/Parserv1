package com.example.marci.parserv1;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RSSReader {

    public static ArrayList<RSSItem> startReader(URL url) throws SAXException, IOException {

        try {
            // Musimy dostać się do dwóch rzeczy
            // # Parsera XML i kodu kanału
            // # Naszego czytnika, który odpowiednio umieści dane w RSSItem
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader(); // Mamy nasz parser XML!
            RSSParser rssParser = new RSSParser(); // A tutaj parser dla RSSItem
            InputSource inputSource = new InputSource(url.openStream());

            reader.setContentHandler(rssParser);
            reader.parse(inputSource);

            // Zwracamy dane
            return rssParser.getResult();
        } catch (Exception e) {
            // Pokazujemy błąd w LogCacie
            e.printStackTrace();
            throw new SAXException();
        }

    }

}