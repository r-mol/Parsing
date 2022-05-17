package JsonParser;

import model.Person;
import model.Root;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonSimpleParser {
    private static final String TAG_NAME_MAIN = "name";
    private static final String TAG_PEOPLE = "people";
    private static final String TAG_NAME = "name";
    private static final String TAG_AGE = "age";

    public Root parse(){

        Root root = new Root();

        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader("test.json")){

            JSONObject rootJsonObject= (JSONObject) parser.parse(reader);

            String mainName = (String) rootJsonObject.get(TAG_NAME_MAIN);

            JSONArray peopleJsonArray = (JSONArray) rootJsonObject.get(TAG_PEOPLE);

            List<Person> peopleList = new ArrayList<>();
            for(Object it: peopleJsonArray){
                JSONObject peopleJsonObject = (JSONObject) it;

                String name = (String)peopleJsonObject.get(TAG_NAME);
                long age = (long)peopleJsonObject.get(TAG_AGE);

                Person person = new Person(name,(int)age);
                peopleList.add(person);
            }

            root.setName(mainName);
            root.setPeople(peopleList);

        }catch(Exception e){
            System.out.println("Parsing error: " + e);
        }

        return root;
    }
}
