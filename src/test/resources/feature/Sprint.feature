@Sprint
Feature: Manage tasks during a sprint

  Background:
    Given I navigate to Login Page
      And I log in successfully as "ronaldbutron@gmail.com" with password "Control123"
      And I have a Project "Genesis"
      And the Start Date is "11/14/2015"
      And the End Date is "11/25/2015"
      And I save the project "Genesis"


  Scenario: Reject a task
    Given I have selected the "sprint" button
      And I create a new sprint "Planning"
      And the sprint start date is "11/18/2015"
      And the sprint end date is "11/25/2015"
      And the capacity in hours is "20"
      And the sprint is saved
      And I have a new task "Test Plan" in the "Feature Backlog" dashboard
      And I drag and drop the task "Test Plan" to the sprint "Planning"
    When I start the task "Test Plan"
      And I "deliver" the task "Test Plan"
      And I "reject" the task "Test Plan"
    Then the task "Test Plan" should be displayed in the Board "In Progress"

  Scenario: Accept a task
    Given I have selected the "sprint" button
    And I create a new sprint "Planning"
    And the sprint start date is "11/18/2015"
    And the sprint end date is "11/25/2015"
    And the capacity in hours is "20"
    And the sprint is saved
    And I have a new task "Test Plan" in the "Feature Backlog" dashboard
    And I drag and drop the task "Test Plan" to the sprint "Planning"
    When I start the task "Test Plan"
    And I "deliver" the task "Test Plan"
    And I "reject" the task "Test Plan"
    Then the task "Test Plan" should be displayed in the Board "Done"
