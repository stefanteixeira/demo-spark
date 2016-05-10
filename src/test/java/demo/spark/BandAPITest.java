package demo.spark;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static spark.Spark.stop;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class BandAPITest {

    @BeforeClass
    public static void setUp() {
        BandsAPI.main(null);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4567;
    }
    
    @AfterClass
    public static void tearDown() {
        stop();
    }
    
    @Test
    public void createBand() {
        given().
            content(ContentType.URLENC).
            queryParam("name", "Molejo").
        when().
            post("/bands").
        then().
            assertThat().statusCode(201);
    }
    
    @Test
    public void getBands() {
        addBand("Wesley Safadão");
        addBand("Calypso");
        addBand("Exaltasamba");
        
        given().
            content(ContentType.URLENC).
        when().
            get("/bands").
        then().
            assertThat().
                statusCode(200).
                body(containsString("Wesley Safadão"),
                     containsString("Calypso"),
                     containsString("Exaltasamba"));
    }
    
    private void addBand(String name) {
        given().
            content(ContentType.URLENC).
            queryParam("name", name).
        when().
            post("/bands").
        then().
            assertThat().statusCode(201);
    }
}
