package com.example.marci.parserv1;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class RSSParser extends DefaultHandler {

    private ArrayList<RSSItem> rssItems;
    private RSSItem parsingItem; // Aktualnie "przerabiana" wiadomość
    private StringBuilder stringBuilder; // Tutaj tworzone są tytuły, daty i tak dalej

    public RSSParser() {
        rssItems = new ArrayList<RSSItem>(); // Tworzymy tablicę składającą się z elementów RSSItem
        // Tutaj będzie znajdować się więcej niż jeden element
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        stringBuilder.append(ch, start, length); // To o czym mówiłem wyżej, odczytane znaki są łączone w całość
    }

    // Końcowa faza zapisu elementów
    @Override
    public void endElement(String uri, String localName, String qName) {

        if (parsingItem != null) {
            try {
                // To musimy zmienić dla dobra naszych funkcji
                if (qName.equals("content:encoded")) {
                    qName = "content";
                }

                // Nasza sztuczka, która zrobi za nas przypisywanie danych :>

                // Nazwa funkcji-settera w RSSItem, np setTitle
                String functionName = "set"
                        + qName.substring(0, 1).toUpperCase() // Pierwszy znak musi być wielki
                        + qName.substring(1);
                // Szukamy metody
                Method function = RSSItem.class.getMethod(functionName,
                        String.class);
                // I zaczynamy działanie na niej!
                function.invoke(parsingItem, stringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
                // Obsługa błędu :(
            }
        }

    }

    // Tworzymy kolejny element RSSItem
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {

        stringBuilder = new StringBuilder();

        if (qName.equals("item") && rssItems != null) {
            parsingItem = new RSSItem();
            rssItems.add(parsingItem); // Reszta danych za chwilę sama się dopisze
        }

    }

    public ArrayList<RSSItem> getResult(){
        return rssItems; // Zwracamy elementy po fakcie
    }

}