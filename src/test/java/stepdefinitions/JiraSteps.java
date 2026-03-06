package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pojo.*;
import services.JiraAttachmentService;
import services.JiraCommentService;
import services.JiraIssueService;
import services.JiraProjectService;
import utils.BaseTest;
import utils.ConfigReader;
import utils.JsonUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.MatcherAssert.assertThat;
//import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JiraSteps extends BaseTest {

    Response response;
    JiraIssueService issueService = new JiraIssueService();
    JiraProjectService projectService = new JiraProjectService();
    JiraCommentService commentService = new JiraCommentService();
    JiraAttachmentService attachmentService = new JiraAttachmentService();

    Set<String> createdIssueKeys = new HashSet<>();
    boolean allStatus201 = true;
    
    @Given("User sets Jira base URI")
    public void set_base_uri() {
        System.out.println("Base URI is set: " + ConfigReader.get("baseURI"));
    }

    @Given("User creates a new Jira issue")
public void create_issue() {

    CreateIssueRequest requestBody =
            JsonUtils.readJson("createIssue.json", CreateIssueRequest.class);

    response = issueService.createIssue(requestBody);

    response.then()
            .statusCode(201)
            .body("key", notNullValue());

    issueKey = response.jsonPath().getString("key");
    System.out.println("Created Issue Key: " + issueKey);
}
   
    @When("User fetches Jira issue by key")
    public void fetch_issue() {

        response = issueService.getIssue(issueKey);

        response.then()
                .body("fields.summary", notNullValue())
                .body("fields.status.name", notNullValue())
                .body("fields.reporter.displayName", notNullValue());
    }

   
   @When("User adds comment to issue")
public void add_comment() {

    AddCommentRequest requestBody =
            JsonUtils.readJson("addComment.json", AddCommentRequest.class);

    response = commentService.addComment(issueKey, requestBody);

    response.then()
            .statusCode(201)
            .body("id", notNullValue());
}



  @When("User updates issue summary")
public void update_summary() {

    UpdateSummaryRequest requestBody =
            JsonUtils.readJson("updateSummary.json", UpdateSummaryRequest.class);

        response = issueService.updateSummary(issueKey, requestBody);

    response.then().statusCode(204);

   Response verifyResponse = issueService.getIssue(issueKey);

        verifyResponse.then()
                .statusCode(200)
                .body("fields.summary",
                        equalTo(requestBody.getFields().getSummary()));

}

 
    @When("User fetches Jira projects")
    public void fetch_projects() {

        response = projectService.getAllProjects();

        response.then()
                .body("size()", greaterThan(0))
                .body("[0].key", notNullValue())
                .body("[0].name", notNullValue());
    }

   
    @Then("Status code should be {int}")
    public void validate_status(int expectedStatus) {

        int actualStatus = response.getStatusCode();
        System.out.println("Actual Status Code: " + actualStatus);

        assertEquals(actualStatus, expectedStatus);
    }

     @When("User creates issue without mandatory fields")
    public void create_issue_without_mandatory_fields() {

        CreateIssueRequest requestBody =
                JsonUtils.readJson("createInvalidIssue.json", CreateIssueRequest.class);

        response = issueService.createInvalidIssue(requestBody);

        response.then()
                .statusCode(400)
                .body("errors", notNullValue());
    }

    @When("User fetches issue with invalid key {string}")
public void fetch_issue_with_invalid_key(String invalidKey) {

        response = issueService.getIssue(invalidKey);
        
        response.then()
                .statusCode(404)
                .body("errorMessages", notNullValue());
}

@When("User assigns issue to another user")
public void user_assigns_issue_to_another_user() {

    AssignIssueRequest requestBody =
            JsonUtils.readJson("assignIssue.json", AssignIssueRequest.class);

        response = issueService.assignIssue(issueKey, requestBody);

    response.then().statusCode(204);
}

@When("User transitions issue status")
public void user_transitions_issue_status() {

    TransitionRequest requestBody =
            JsonUtils.readJson("transitionIssue.json", TransitionRequest.class);

        response = issueService.transitionIssue(issueKey, requestBody);

        response.then()
            .statusCode(204);
}



@Then("Issue status should be {string}")
public void issue_status_should_be(String expectedStatus) {

    Response fetchResponse = issueService.getIssue(issueKey);
    fetchResponse.then().statusCode(200);

    String actualStatus =
            fetchResponse.jsonPath().getString("fields.status.name");

    System.out.println("Expected Status: " + expectedStatus);
    System.out.println("Actual Status: " + actualStatus);

    assertEquals(actualStatus, expectedStatus);
}

@When("User deletes the created issue")
public void delete_created_issue() {

    response = issueService.deleteIssue(issueKey);
    response.then().statusCode(204);
}

//advanced scenarios
@When("User creates multiple Jira issues from dataset")
public void create_multiple_jira_issues() {

    CreateIssueRequest[] requests =
            JsonUtils.readJson("createMultipleIssues.json", CreateIssueRequest[].class);

    for (CreateIssueRequest requestBody : requests) {

        Response response = issueService.createIssue(requestBody);

        int statusCode = response.getStatusCode();

        if (statusCode != 201) {
            allStatus201 = false;
        }

        String key = response.jsonPath().getString("key");
        System.out.println("Created Issue Key: " + key);

        createdIssueKeys.add(key);
}}

@Then("Status code should be 201 for each dataset")
public void status_code_should_be_201_for_each_dataset() {
      assertTrue("One or more requests did not return 201", allStatus201);
}

@Then("Unique issue keys should be generated")
public void validate_unique_issue_keys() {

    CreateIssueRequest[] requests =
            JsonUtils.readJson("createMultipleIssues.json", CreateIssueRequest[].class);

    int expectedCount = requests.length;
    int actualCount = createdIssueKeys.size();

    System.out.println("Expected Count: " + expectedCount);
    System.out.println("Unique Keys Count: " + actualCount);

    assertEquals(expectedCount, actualCount);
}


@When("User performs complete Jira workflow")
public void user_performs_complete_workflow() {

    // Create Issue(call the methods which already written)
       create_issue();

    // Add Comment
       add_comment();

    // Transition Issue
    user_transitions_issue_status();
   
}

@When("User uploads attachment to created issue")
public void user_uploads_attachment_to_created_issue() {

    File file = new File(
            getClass()
                .getClassLoader()
                .getResource("testdata/sample.txt")
                .getFile()
    );

        response = attachmentService.uploadAttachment(issueKey, file);

}

@Then("Response should contain attachment filename")
public void response_should_contain_attachment_filename() {

    response.then()
            .statusCode(200)
            .body("[0].filename", equalTo("sample.txt"))
            .body("[0].size", greaterThan(0));

}

@Then("Response should match JSON schema {string}")
public void response_should_match_json_schema(String schemaFile) {

    response.then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("schemas/" + schemaFile));
}


@Then("Response time should be less than {long} ms")
public void response_time_should_be_less_than_ms(long expectedTime) {

    long actualTime = response.timeIn(TimeUnit.MILLISECONDS);

    System.out.println("Actual Response Time: " + actualTime + " ms");

    assertThat(actualTime, lessThan(expectedTime));
}
}
