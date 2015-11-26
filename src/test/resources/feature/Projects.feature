@Project
Feature: Projects
  Background:
    Given I log in successfully as "ronaldbutron@gmail.com" with password "Control123"

  @Projects
  Scenario Outline: Create Project
    Given I have a Project "<project name>"
      And the Start Date is "11/14/2015"
      And the End Date is "11/25/2015"
    When I save the project "<project name>"
    Then the project "<project name>" should be displayed in project board

  Examples:
    | project name |
    | Genesis      |


  Scenario Outline: Delete a Project
    Given I have a Project "<project name>"
    And the Start Date is "11/14/2015"
    And the End Date is "11/25/2015"
    And I save the project "<project name>"
    When I delete the project "<project name>"
    Then the project "<project name>" should not be displayed in the projects dashboard

  Examples:
    | project name |
    | Genesis     |

