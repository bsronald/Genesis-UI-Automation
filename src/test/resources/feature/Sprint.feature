@Sprint
Feature: Manage tasks during a sprint

  Background:
    Given I log in successfully as "ronaldbutron@gmail.com" with password "Control123"
      And I have the following Project
        | name        | start date | end date   |
        | Genesis     | 11/23/2015 | 11/30/2015 |

  @Projects
  Scenario: Accept task , using drag and drop
    Given I have selected the "sprint" button
    And I have the following task
      | task name | board name      | description     | comment        |
      | Test Plan | Feature Backlog | details scenes  | estimated time |
    And I create the following Sprint
      | sprint name | start date | end date   | capacity hours  |
      | Planning    | 11/25/2015 | 12/10/2015 | 20              |
    And I drag and drop the task "Test Plan" to the sprint "Planning"
    And I select the sprint "Planning"
    When the task has the following work flow using drag and drop
      | task name  | target       | current     |
      | Test Plan  | In Progress  | Not Started |
      | Test Plan  | Testing      | In Progress |
      | Test Plan  | Done         | Testing     |
    Then the task "Test Plan" should be displayed in the board "Not Started"

  @Projects
  Scenario: Assign a task to a member
    Given I have selected the "sprint" button
    And I have the following task
      | task name | board name      | description    | comment        |
      | Test Plan | Feature Backlog | details scenes | estimated time |
    And I create the following Sprint
      | sprint name | start date | end date   | capacity hours  |
      | Planning    | 11/24/2015 | 12/10/2015 | 30              |
    And I drag and drop the task "Test Plan" to the sprint "Planning"
    And I select the sprint "Planning"
    When the task "Test Plan" has assigned to the member "Ronald"
    Then the task "Test Plan" should display the name "Ronald" of the member

  @Projects
  Scenario: Estimated time of task
    Given I have selected the "sprint" button
    And I have the following task
      | task name | board name      | description    | comment        |
      | Test Plan | Feature Backlog | details scenes | estimated time |
    And I create the following Sprint
      | sprint name | start date | end date   | capacity hours  |
      | Planning    | 11/30/2015 | 12/10/2015 | 30              |
    And I drag and drop the task "Test Plan" to the sprint "Planning"
    And I select the sprint "Planning"
    When I set the "Estimated hours" is "10.0" in the task "Test Plan"
    Then the Estimated hours "10.0" is displayed in the task "Test Plan"

  @Projects
  Scenario: Accept task, using task buttons
    Given I have selected the "sprint" button
    And I have the following task
      | task name | board name      | description    | comment        |
      | Test Plan | Feature Backlog | details scenes | estimated time |
    And I create the following Sprint
      | sprint name | start date | end date   | capacity hours  |
      | Planning    | 11/30/2015 | 12/10/2015 | 30              |
    And I drag and drop the task "Test Plan" to the sprint "Planning"
    And I select the sprint "Planning"
    And the task has the following work flow using buttons
      | task option | task name  |
      | Start task  | Test Plan  |
      | Deliver     | Test Plan  |
    When I "Accept" the task "Test Plan"
    Then the task "Test Plan" should be displayed in the board "Done"











