package demo.spark;

import static demo.spark.util.JsonUtil.parseFromFile;
import static demo.spark.util.JsonUtil.toJson;
import static spark.Spark.get;

import com.google.gson.JsonElement;

public class SimulatorExample {
    
    public static void main(String[] args) {
        
        get("/myendpoint/user/*", (req, res) -> toJson(parseFromFile("jsonResponse.txt")));
        
        get("*/api/token/*", (req, res) -> {
            String token = req.splat()[1];
            return getJsonWithValueFromRequest(token);
        });
    }
    
    private static String getJsonWithValueFromRequest(String value) {
        JsonElement json = parseFromFile("jsonResponse2.txt");
        json.getAsJsonObject().get("user").getAsJsonObject().addProperty("token", value);
        return toJson(json);
    }
}
