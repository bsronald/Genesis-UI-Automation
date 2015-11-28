@Tasks
Feature: Manage Tasks in the main board of the project
  The user is able to create and archive tasks in the different dashboards of the project.

  Background:
    Given  I navigate to Login Page
      And I log in successfully as "ronaldbutron@gmail.com" with password "Control123"
      And I have the following Project
      | name        | start date | end date   |
      | Genesis     | 11/23/2015 | 12/30/2015 |

  @Projects
  Scenario Outline: Create New Task
    Given I have a new task "<task name>" in the "<dashboard name>" dashboard
      And I insert a description "<description>" into the "<task name>" task
      And I insert a comment "<comment>" into the "<task name>" task
    When I save the task
    Then the task "<task name>" should be displayed in the dashboard "<dashboard name>" of the main board

  Examples:
    | task name	        | description 	              | comment 	          | dashboard name        	|
    | Write Scenarios	| Details the scenes	      | estimated time	      | Idea                   	|
    | Planning          | Plan the sprint             | what are the goals?   | Feature Backlog         |
    | Installer fail    | Not run the installer       | review Libs           | Bugs                    |
  @Projects
  Scenario Outline: Move a task to another board
    Given I have a new task "<task name>" in the "<dashboard name>" dashboard
    When I drag and drop the task "<task name>" from "<dashboard name>" dashboard to "<target>" dashboard
    Then the task "<task name>" should be displayed in the dashboard "<target>" of the main board

  Examples:
    | task name	                  | dashboard name       | target	      |
    | Write Scenarios	          | Idea                 | Bugs           |
    | Planning tasks              | Feature Backlog      | Idea           |
    | Element Y is not displayed  | Bugs                 | Feature Backlog|

  @Projects
  Scenario Outline: Create a Sprint
    When I create the following sprint "<sprint name>", "<capacity hours>", "<start date>", "<end date>"
    Then The sprint "<sprint name>" should be displayed in the Left board

   Examples:
      |sprint name                | capacity hours| start date | end date   |
      | Planning                  | 50            | 11/23/2015 | 11/30/2015 |
      | !@##$%%^&&**()_+=         | 20            | 11/30/2015 | 12/07/2015 |


  @Projects
  Scenario: Add task to a sprint
    Given I have selected the "sprint" button
      And I create the following Sprint
        | sprint name | start date | end date   | capacity hours  |
        | Planning    | 11/30/2015 | 12/10/2015 | 30              |
    When I have a new task "Test Plan" in the "Feature Backlog" dashboard
      And I drag and drop the task "Test Plan" to the sprint "Planning"
      And I select the sprint "Planning"
    Then the task "Test Plan" should be displayed inside the sprint "Planning" in the board "notstarted"
