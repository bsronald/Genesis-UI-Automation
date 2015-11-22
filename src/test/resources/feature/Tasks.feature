@Tasks
Feature: Manage Tasks
  The user is able to create and archive tasks in the different dashboards of the project.

  Background:
    Given  I navigate to Login Page
      And I log in successfully as "ronaldbutron@gmail.com" with password "Control123"
      And I have a Project "Genesis"
      And the Start Date is "11/14/2015"
      And the End Date is "11/25/2015"
      And I save the "Genesis" project

  @Projects
  @LogOut
  Scenario Outline: Create New Task
    Given I have a new task "<task name>" in the "<dashboard name>" dashboard
      And I insert a description "<description>" into the "<task name>" task
      And I insert a comment "<comment>" into the "<task name>" task
    When I save the task
    Then The "<task name>" task should be displayed

  Examples:
    | task name	        | description 	              | comment 	          | dashboard name        	|
    | Write Scenarios	| Details the scenes	      | estimated time	      | Bugs                   	|

  @Projects
  @LogOut
  Scenario Outline: Move a task to another board.
    Given I have a new task "<task name>" in the "<dashboard name>" dashboard
    When I drag and drop the "<task name>" task to "<target>" dashboard
    Then The "<task name>" task should be displayed

  Examples:
    | task name	            | dashboard name           	| target	        |
    | Write Scenarios	    | Idea                   	| Bugs		        |

  @Projects
  @LogOut
  Scenario: Create a Sprint
    Given I have selected the "sprint" button
    When I create a new sprint "Planning"
      And the sprint start date is "11/18/2015"
      And the sprint end date is "11/25/2015"
      And the capacity in hours is "20"
      And the sprint is saved
    Then The sprint "Planning" should be displayed in the Left board

  @Projects
  @LogOut
  Scenario: Add task to a sprint
    Given I have selected the "sprint" button
      And I create a new sprint "Planning"
      And the sprint start date is "11/18/2015"
      And the sprint end date is "11/25/2015"
      And the capacity in hours is "20"
      And the sprint is saved
    When I have a new task "Test Plan" in the "Feature Backlog" dashboard
      And I drag and drop the task "Test Plan" to the sprint "Planning"
    Then the task "Test Plan" should be displayed inside the sprint "Planning" in the board "notstarted"

