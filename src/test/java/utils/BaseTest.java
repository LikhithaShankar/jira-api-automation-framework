package utils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

    public static String issueKey;
    public static RequestSpecification requestSpec;

    public void setup() {

        RestAssured.baseURI = ConfigReader.get("baseURI");

        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .setAuth(RestAssured.preemptive()
                        .basic(ConfigReader.get("email"),
                               ConfigReader.get("apiToken")))
                .build();
    }
}