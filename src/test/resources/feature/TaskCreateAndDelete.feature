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

  Scenario Outline: Create New Task
    Given I have a new task "<task name>" in the "<dashboard name>" dashboard
    And I insert a description "<description>" into the "<task name>" task
    And I insert a comment "<comment>" into the "<task name>" task
    When I save the task
    Then The "<task name>" task should be displayed

  Examples:
    | task name	        | description 	              | comment 	          | dashboard name        	|
    | Write Scenarios	| Details the scenes	      | estimated time	      | Idea                   	|
