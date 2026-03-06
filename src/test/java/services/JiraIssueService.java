package services;

import io.restassured.response.Response;
import pojo.*;
import utils.BaseTest;

import static io.restassured.RestAssured.given;

public class JiraIssueService extends BaseTest {

    public Response createIssue(CreateIssueRequest requestBody) {

        return given()
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .post("/rest/api/3/issue");
    }

    public Response getIssue(String issueKey) {

        return given()
                .spec(requestSpec)
        .when()
                .get("/rest/api/3/issue/" + issueKey);
    }

    public Response updateSummary(String issueKey, UpdateSummaryRequest requestBody) {

        return given()
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .put("/rest/api/3/issue/" + issueKey);
    }

    public Response assignIssue(String issueKey, AssignIssueRequest requestBody) {

        return given()
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .put("/rest/api/3/issue/" + issueKey + "/assignee");
    }

    public Response transitionIssue(String issueKey, TransitionRequest requestBody) {

        return given()
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .post("/rest/api/3/issue/" + issueKey + "/transitions");
    }

    public Response deleteIssue(String issueKey) {

        return given()
                .spec(requestSpec)
        .when()
                .delete("/rest/api/3/issue/" + issueKey);
    }

    public Response createInvalidIssue(CreateIssueRequest requestBody) {

        return given()
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .post("/rest/api/3/issue");
    }
}