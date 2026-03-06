package services;

import io.restassured.response.Response;
import pojo.AddCommentRequest;
import utils.BaseTest;

import static io.restassured.RestAssured.given;

public class JiraCommentService extends BaseTest {

    public Response addComment(String issueKey, AddCommentRequest requestBody) {

        return given()
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .post("/rest/api/3/issue/" + issueKey + "/comment");
    }
}