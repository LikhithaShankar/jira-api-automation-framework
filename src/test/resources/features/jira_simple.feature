Feature: Jira API Automation

  Background:
    Given User sets Jira base URI
    And User creates a new Jira issue
    Then Status code should be 201

  Scenario: Get Jira Issue by Key
    When User fetches Jira issue by key
    Then Status code should be 200

  Scenario: Add Comment to Issue
    When User adds comment to issue
    Then Status code should be 201

  Scenario: Update Issue Summary
    When User updates issue summary
    Then Status code should be 204

  Scenario: Get All Projects
    When User fetches Jira projects
    Then Status code should be 200