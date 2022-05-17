package XMLParsers.DomParser.SaxParser;

import model.Root;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class MySaxParser {

    public Root parse(){

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParserHandler handler = new SaxParserHandler();
        SAXParser parser;

        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            System.out.println("Open Sax parser error " + e);
            return null;
        }

        File file = new File("test.xml");
        try {
            parser.parse(file, handler);
        } catch (SAXException e) {
            System.out.println("Sax parser error " + e);
            return null;
        } catch (IOException e) {
            System.out.println("IO parsing error " + e);
            return null;
        }


        return handler.getRoot();
    }
}
