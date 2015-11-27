@Sprint
Feature: Manage tasks during a sprint

  Background:
    Given I log in successfully as "ronaldbutron@gmail.com" with password "Control123"
      And I have the following Project
        | name        | start date | end date   |
        | Genesis     | 11/23/2015 | 12/30/2015 |
      And I have selected the "sprint" button
      And I have the following task
        | task name | board name      | description     | comment        |
        | Test Plan | Feature Backlog | details scenes  | estimated time |
      And I create the following Sprint
        | sprint name | start date | end date   | capacity hours  |
        | Planning    | 11/23/2015 | 12/10/2015 | 200             |
      And I drag and drop the task "Test Plan" to the sprint "Planning"
      And I select the sprint "Planning"

  @Projects
  Scenario Outline: Accept task , using drag and drop
    When I move the task "<task name>" to the board "<target>"
    Then the task "<task name>" should be displayed in the board "<target>"
    Examples:
      | task name  | target       |
      | Test Plan  | In Progress  |
      | Test Plan  | Testing      |
      | Test Plan  | Done         |

  @Projects
  Scenario: Assign a task to a member
    When the task "Test Plan" has assigned to the member "Ronald"
    Then the task "Test Plan" should display the name of the member "Ronald" in the task

  @Projects
  Scenario: Estimated time of task
    When I set the "Estimated hours" is "10.0" in the task "Test Plan"
    Then the Estimated hours "10.0" is displayed in the task "Test Plan"

  @Projects
  Scenario Outline: Accept task, using task buttons
    Given I move the task "<task name>" to the board "<current board>"
    When I select the option "<task option>" of task "<task name>" from the "<current board>" board
    Then the task "<task name>" should be displayed in the board "<target board>"
   Examples:
     | task option | task name  | target board  | current board |
     | Deliver     | Test Plan  | Testing       | In Progress   |
     | Accept      | Test Plan  | Done          | Testing       |











