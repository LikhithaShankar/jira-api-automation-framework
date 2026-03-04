Feature: Jira Advanced API Automation

  Background:
    Given User sets Jira base URI

@data-driven
  Scenario: Verify user can create multiple Jira issues using data-driven testing
    When User creates multiple Jira issues from dataset
    Then Status code should be 201 for each dataset
    And Unique issue keys should be generated

@workflow
  Scenario: Verify create issue → add comment → transition → close workflow
    When User performs complete Jira workflow
    Then Issue status should be "Done"


@Attachment
  Scenario: Verify user can upload attachment to Jira issue
    When User creates a new Jira issue
    Then Status code should be 201
    And User uploads attachment to created issue
    Then Status code should be 200
    And Response should contain attachment filename

  @schema
  Scenario: Verify Jira issue response matches expected JSON schema
    When User creates a new Jira issue
    Then Status code should be 201
    And Response should match JSON schema "createIssueSchema.json"

  @performance
  Scenario: Verify API response time is within threshold
    When User creates a new Jira issue
    Then Status code should be 201
    And Response time should be less than 3000 ms