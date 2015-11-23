package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import ui.pages.LeftMenu;
import ui.pages.LeftSprintMenu;

import java.util.Map;

/**
 * User: Ronald Butron
 * Date: 11/17/15
 */
public class Sprints {

    LeftMenu leftMenu = new LeftMenu();
    LeftSprintMenu sprintMenu;
    String sprintName;
    String startDate;
    String endDate;
    String capacityHours;

    @Given("^I have selected the \"(.*?)\" button$")
    public void selectLeftToolBarButton(String buttonSelected){

        sprintMenu = leftMenu.selectSprintPage();
    }

    @When("^I create a new sprint \"(.*?)\"$")
    public void createNewSprint(String sprintName){

        sprintMenu.createNewSprint(sprintName);
    }

    @Then("^The sprint \"(.*?)\" should be displayed in the Left board$")
    public void sprintIsDisplayed(String sprintName){

        Assert.assertTrue(sprintMenu.sprintIsDisplayed(sprintName));
    }

    @And("^the sprint start date is \"(.*?)\"$")
    public void insertSprintStartDate(String startDate){

         sprintMenu.setStartDate(startDate);
    }

    @And("^the sprint end date is \"(.*?)\"$")
    public void insertSprintEndDate(String endDate){

        sprintMenu.setFinishDate(endDate);
    }

    @And("^the capacity in hours is \"(.*?)\"$")
    public void insertCapacityInHours(String capacityHours){

        sprintMenu.setCapacityInHours(capacityHours);
    }

    @And("^the sprint is saved$")
    public void saveSprint(){

        sprintMenu.saveNewSprint();
    }

    @And("^I drag and drop the task \"(.*?)\" to the sprint \"(.*?)\"$")
    public void dragAndDropTaskToSprint(String taskName, String sprintName){

        sprintMenu.dragAndDropTask(taskName, sprintName);
    }

    @Then("^the task \"(.*?)\" should be displayed inside the sprint \"(.*?)\" in the board \"(.*?)\"$")
    public void isDisplayedTheTaskInsideSprint(String taskName, String sprintName, String board ){

        Assert.assertTrue(sprintMenu.isDisplayedTaskInSprint(sprintName, taskName, board));
    }

    @Given("^I create the following Sprint$")
    public void createFollowingSprint(DataTable table){

        for (Map<String, String> map : table.asMaps(String.class, String.class)){
            sprintName = map.get("sprint name");
            startDate = map.get("start date");
            endDate = map.get("end date");
            capacityHours = map.get("capacity hours");
        }

        sprintMenu.createSuccessfullySprint(sprintName, startDate, endDate, capacityHours);

    }


}
