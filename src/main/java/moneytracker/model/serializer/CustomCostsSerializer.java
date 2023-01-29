package moneytracker.model.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import moneytracker.model.Person;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class CustomCostsSerializer implements JsonSerializer<Person> {
    @Override
    public JsonElement serialize(Person person, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize("PERSON-INSTANCE-UUID--" + person.getId().toString());
    }
}
