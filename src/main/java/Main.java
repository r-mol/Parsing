import JsonParser.JsonSimpleParser;
import model.Root;

public class Main {

    public static void main(String[] args) {
        //DomParser parser = new DomParser();
        //MySaxParser parser = new MySaxParser();
        JsonSimpleParser parser = new JsonSimpleParser();
        Root root = parser.parse();

        System.out.println("Root: " + root.toString());
        root.getPeople().stream().filter(people -> people.getAge() == 20).forEach(people -> System.out.println("People " + people));
    }

}
