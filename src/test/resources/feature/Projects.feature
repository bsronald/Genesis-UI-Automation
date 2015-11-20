@Project
Feature: Projects
  Background:
    Given I navigate to Login Page
    And I log in successfully as "ronaldbutron@gmail.com" with password "Control123"

  @Projects
  @LogOut
  Scenario Outline: Create Project
    Given I have a Project "<project name>"
      And the Start Date is "11/14/2015"
      And the End Date is "11/25/2015"
    When I save the "<project name>" project
    Then the "<project name>" project should be displayed in project board

  Examples:
    | project name |
    | Genesis      |

  @LogOut
  Scenario Outline: Archive a Project
    Given I have a Project "<project name>"
    And the Start Date is "11/14/2015"
    And the End Date is "11/25/2015"
    And I save the "<project name>" project
    When I delete the "<project name>" project
    Then the "<project name>" project should not be displayed in the projects dashboard

  Examples:
    | project name |
    | Genesis     |

