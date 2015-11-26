@Sprint
Feature: Manage tasks during a sprint

  Background:
    Given I log in successfully as "ronaldbutron@gmail.com" with password "Control123"
      And I have the following Project
        | name        | start date | end date   |
        | Genesis     | 11/23/2015 | 11/30/2015 |

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





