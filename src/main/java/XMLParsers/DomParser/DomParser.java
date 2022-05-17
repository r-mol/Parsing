package XMLParsers.DomParser;

import model.Person;
import model.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    private static final String TAG_NAME_MAIN = "name";
    private static final String TAG_PEOPLE = "people";
    private static final String TAG_ELEMENT = "element";
    private static final String TAG_NAME = "name";
    private static final String TAG_AGE = "age";

    public Root parse() {
        Root root = new Root();

        Document doc;
        try {
            doc = buildDocument();
        } catch (Exception e) {
            System.out.println("Open parsing error: " + e.toString());
            return null;
        }

        Node rootNode = doc.getFirstChild();
        NodeList rootChildren = rootNode.getChildNodes();

        String mainName = null;
        Node peopleNode = null;
        for (int i = 0; i < rootChildren.getLength(); i++) {

            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (rootChildren.item(i).getNodeName()) {
                case TAG_NAME_MAIN:
                    mainName = rootChildren.item(i).getTextContent();
                    break;
                case TAG_PEOPLE:
                    peopleNode = rootChildren.item(i);
                    break;
            }
        }

        if (peopleNode == null) {
            return null;
        }

        List<Person> peopleList = parsPeople(peopleNode);

        root.setName(mainName);
        root.setPeople(peopleList);


        return root;
    }

    private Document buildDocument() throws Exception {
        File file = new File("test.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }

    private List<Person> parsPeople(Node peopleNode) {
        List<Person> peopleList = new ArrayList<>();
        NodeList peopleChildren = peopleNode.getChildNodes();

        for (int i = 0; i < peopleChildren.getLength(); i++) {

            if (peopleChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (!peopleChildren.item(i).getNodeName().equals(TAG_ELEMENT)) {
                continue;
            }

            Person person = parsElement(peopleChildren.item(i));
            peopleList.add(person);
        }
        return peopleList;
    }

    private Person parsElement(Node elementNode) {
        String name = "";
        int age = 0;

        NodeList elementChildren = elementNode.getChildNodes();

        for (int j = 0; j < elementChildren.getLength(); j++) {
            if (elementChildren.item(j).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (elementChildren.item(j).getNodeName()) {
                case TAG_NAME:
                    name = elementChildren.item(j).getTextContent();
                    break;
                case TAG_AGE:
                    age = Integer.parseInt(elementChildren.item(j).getTextContent());
                    break;
            }
        }

        Person person = new Person(name, age);
        return person;
    }
}
