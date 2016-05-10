package demo.spark.util;

import java.io.InputStreamReader;

import spark.ResponseTransformer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonUtil {
    
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }

    public static JsonElement parseFromFile(String filename) {
        JsonParser parser = new JsonParser();
        try {
            JsonElement json = parser.parse(new InputStreamReader(JsonUtil.class.getClassLoader().
                    getResourceAsStream(filename)));
            return json;
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return new JsonObject();
    }
    
}
