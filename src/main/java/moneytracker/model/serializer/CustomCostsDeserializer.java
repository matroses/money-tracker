package moneytracker.model.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import moneytracker.model.Person;
import moneytracker.model.db.PeopleDB;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomCostsDeserializer implements JsonDeserializer<Person> {
        @Override
    public Person deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        // Get as string
        String personId = jsonElement.getAsString();

        // Check if it's a person
        if (!personId.startsWith("PERSON-INSTANCE-UUID--")) {
            return null;
        }

        // Remove prefix
        personId = personId.replace("PERSON-INSTANCE-UUID--", "");

        try {
            // Get person
            return PeopleDB.getInstance().getPerson(UUID.fromString(personId));
        } catch (Exception e) {
            return null;
        }
    }
}
