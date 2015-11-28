@Login
Feature: Login
    Allows to log on successfully and unsuccessfully

  @LogOut
  Scenario: Login to the page successfully
    When I log in successfully as "ronaldbutron@gmail.com" with password "Control123"
    Then The main page is displayed


  Scenario Outline: Login to the page Unsuccessfully
      When I log in unsuccessfully as "<userName>" with password "<password>"
      Then An error message is Displayed

  Examples:
    | userName                | password      |
    | ronaldutron@gmail.com   | Control123    |










