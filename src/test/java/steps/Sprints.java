package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ui.pages.LeftMenu;
import ui.pages.LeftSprintMenu;

/**
 * User: RonaldButron
 * Date: 11/17/15
 */
public class Sprints {

    LeftMenu leftMenu = new LeftMenu();
    LeftSprintMenu sprintMenu;

   @When("^I create a new sprint \"(.*?)\"$")
    public void createNewSprint(){

       leftMenu.selectMembersMenu();

   }

    @Then("^The sprint \"(.*?)\" should be displayed in the Left board$")
    public void sprintIsDisplayed(){

    }

    @Given("^I have selected the \"(.*?)\" button$")
    public void selectLeftToolBarButton(String buttonSelected){


            leftMenu.selectMembersMenu();
            sprintMenu = leftMenu.selectSprintPage();



    }

    @And("^the sprint start date is \"(.*?)\"$")
    public void insertSprintStartDate(){
        leftMenu.selectMembersMenu();

    }

    @And("^the sprint end date is \"(.*?)\"$")
    public void insertSprintEndDate(){

    }

    @And("^the capacity in hours is \"(.*?)\"$")
    public void insertCapacityInHours(){

    }
}
