package services;

import io.restassured.response.Response;
import utils.BaseTest;

import static io.restassured.RestAssured.given;

public class JiraProjectService extends BaseTest {

    public Response getAllProjects() {

        return given()
                .spec(requestSpec)
        .when()
                .get("/rest/api/3/project");
    }
}