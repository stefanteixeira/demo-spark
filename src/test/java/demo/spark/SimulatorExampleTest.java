package demo.spark;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static spark.Spark.stop;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class SimulatorExampleTest {
    
    @BeforeClass
    public static void setUp() {
        SimulatorExample.main(null);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4567;
    }
    
    @AfterClass
    public static void tearDown() {
        stop();
    }
    
    @Test
    public void getWithJsonReplace() {
        given().
            content(ContentType.URLENC).
        when().
            get("/v2/api/token/TT489FE-99345BC").
        then().
            assertThat().
                statusCode(200).
                body(containsString("TT489FE-99345BC"));
    }
}
