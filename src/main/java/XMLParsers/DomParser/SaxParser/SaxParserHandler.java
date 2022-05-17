package XMLParsers.DomParser.SaxParser;

import model.Person;
import model.Root;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxParserHandler extends DefaultHandler {

    private static final String TAG_NAME_MAIN = "name";
    private static final String TAG_PEOPLE = "people";
    private static final String TAG_ELEMENT = "element";
    private static final String TAG_NAME = "name";
    private static final String TAG_AGE = "age";


    Root root = new Root();
    private final List<Person> peopleList = new ArrayList<>();
    private String currentTagName;
    private boolean isPeople = false;
    private boolean isElement = false;

    private String name = "";
    private int age = 0;

    public Root getRoot() {
        return root;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        currentTagName = qName;

        if (currentTagName.equals(TAG_PEOPLE)) {
            isPeople = true;
        } else if (currentTagName.equals(TAG_ELEMENT)) {
            isElement = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){

        if (qName.equals(TAG_PEOPLE)) {
            if(isPeople){
                root.setPeople(peopleList);
            }
            isPeople = false;
        } else if (qName.equals(TAG_ELEMENT)) {
            isElement = false;

            Person person = new Person(name, age);
            peopleList.add(person);
        }

        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if (currentTagName == null) {
            return;
        }

        if (!isPeople && currentTagName.equals(TAG_NAME_MAIN)) {
            root.setName(new String(ch, start, length));
        }

        if (isPeople && isElement) {
            if (currentTagName.equals(TAG_AGE)) {
                age = Integer.parseInt(new String(ch, start, length));
            } else if (currentTagName.equals(TAG_NAME)) {
                name = new String(ch, start, length);
            }
        }
    }
}
