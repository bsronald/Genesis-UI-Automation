@Sprint
Feature: Manage tasks during a sprint

  Background:
    Given I navigate to Login Page
      And I log in successfully as "ronaldbutron@gmail.com" with password "Control123"
      And I have the following Project
        | name        | start date | end date   |
        | Genesis     | 11/23/2015 | 11/30/2015 |


  Scenario: Reject a task
    Given I have selected the "sprint" button
      And I create the following Sprint
        | sprint name | start date | end date   | capacity hours  |
        | Planning    | 11/30/2015 | 12/10/2015 | 30              |
      And I have a new task "Test Plan" in the "Feature Backlog" dashboard
      And I drag and drop the task "Test Plan" to the sprint "Planning"
    When I start the task "Test Plan"
      And I "deliver" the task "Test Plan"
      And I "reject" the task "Test Plan"
    Then the task "Test Plan" should be displayed in the Board "In Progress"

  Scenario: Accept a task
    Given I have selected the "sprint" button
    And I create the following Sprint
      | sprint name | start date | end date   | capacity hours  |
      | Planning    | 11/30/2015 | 12/10/2015 | 30              |
    And I have a new task "Test Plan" in the "Feature Backlog" dashboard
    And I drag and drop the task "Test Plan" to the sprint "Planning"
    When I start the task "Test Plan"
    And I "deliver" the task "Test Plan"
    And I "reject" the task "Test Plan"
    Then the task "Test Plan" should be displayed in the Board "Done"
