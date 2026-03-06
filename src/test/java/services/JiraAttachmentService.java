package services;

import io.restassured.response.Response;
import utils.BaseTest;
import utils.ConfigReader;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraAttachmentService extends BaseTest {

    public Response uploadAttachment(String issueKey, File file) {

        return given()
                .header("X-Atlassian-Token", "no-check")
                .auth()
                .preemptive()
                .basic(ConfigReader.get("email"), ConfigReader.get("apiToken"))
                .multiPart("file", file)
        .when()
                .post("/rest/api/3/issue/" + issueKey + "/attachments");
    }
}