package demo.spark;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BandsAPI {
    
    private static Map<String, Band> bands = new HashMap<String, Band>();
    
    public static void main(String[] args) {
        final Random random = new Random();
        
        post("/bands", (req, res) -> {
           String name = req.queryParams("name");
           Band band = new Band(name);
           
           int id = random.nextInt(Integer.MAX_VALUE);
           bands.put(String.valueOf(id), band);
           
           res.status(201);
           return id;
        });
        
        get("/bands", (req, res) -> {
            List<String> names = new ArrayList<String>();
            for(Band band : bands.values()) {
                names.add(band.getName());
            }
            
            return names.toString();            
        });
        
        get("/bands/:id", (req, res) -> {
            String id = req.params(":id");
            Band band = bands.get(id);
            
            if(band != null) {
                return "Name: " + band.getName();
            } else {
                res.status(404);
                return "Band not found";
            }
        });
        
        put("/bands/:id", (req, res) -> {
            String id = req.params(":id");
            Band band = bands.get(id);
            String name = req.queryParams("name");
            
            if(band != null && name != null) {
                band.setName(name);
                
                return "Band updated!";
            } else {
                res.status(404);
                return "Band not found";
            }
        });
        
        delete("/bands/:id", (req,res) -> {
            String id = req.params(":id");
            Band band = bands.remove(id);
            
            if(band != null) {
                return "Band deleted!";
            } else {
                res.status(404);
                return "Band not found";
            }
        });
    }
}
