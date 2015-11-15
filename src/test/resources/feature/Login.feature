@Login
Feature: Login
    Allows to log on successfully and unsuccessfully
  @smokeTest
  Scenario: Login to the page successfully
      Given I navigate to Login Page
      When I log in successfully as "ronaldbutron@gmail.com" with password "Control123"
      Then The main page is displayed

  Scenario Outline: Login to the page Unsuccessfully
      Given I navigate to Login Page
      When I log in unsuccessfully as "<userName>" with password "<password>"
      Then An error message is Displayed

  Examples:
    | userName                | password      |
    | ronaldutron@gmail.com   | Control123    |







