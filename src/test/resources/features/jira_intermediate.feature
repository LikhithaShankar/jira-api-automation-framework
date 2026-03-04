Feature: Jira Intermediate API Automation

  Background:
    Given User sets Jira base URI
    And User creates a new Jira issue
    Then Status code should be 201

  Scenario: Verify user cannot create issue without mandatory fields
    When User creates issue without mandatory fields
    Then Status code should be 400

  Scenario: Verify user cannot fetch issue with invalid key
    When User fetches issue with invalid key "INVALID-1"
    Then Status code should be 404

  Scenario: Verify user can assign issue to another user
    When User assigns issue to another user
    Then Status code should be 204

  Scenario: Verify user can transition issue status
    When User transitions issue status
    Then Status code should be 204
    And Issue status should be "Done"

  Scenario: Verify user can delete issue
    When User deletes the created issue
    Then Status code should be 204

#   Scenario: Verify user cannot update issue without permission
#     When User updates issue without permission
#     Then Status code should be 403

