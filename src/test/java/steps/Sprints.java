package steps;

import commons.DomainAppConstants;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import ui.pages.*;

import java.util.Map;

/**
 * User: Ronald Butron
 * Date: 11/17/15
 */
public class Sprints {
    MainPages newMainPage = new MainPages();
    LeftMenu leftMenu = newMainPage.leftMenu();
    SprintBoard sprintBoard;
    LeftSprintMenu sprintMenu;
    String sprintName;
    String startDate;
    String endDate;
    String capacityHours;
    String taskName;

    @Given("^I have selected the \"(.*?)\" button$")
    public void selectLeftToolBarButton(String buttonSelected){

        sprintMenu = leftMenu.selectSprintPage();
    }

    @When("^I create the following sprint \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\"$")
    public void createNewSprint(String sprintName, String capacityHours, String startDate, String endDate){

        sprintMenu = leftMenu.selectSprintPage();
        sprintMenu.createSuccessfullySprint(sprintName, startDate, endDate, capacityHours);
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
        this.taskName = taskName;
        sprintMenu.dragAndDropTask(taskName, sprintName);
    }

    @Then("^the task \"(.*?)\" should be displayed inside the sprint \"(.*?)\" in the board \"(.*?)\"$")
    public void isDisplayedTheTaskInsideSprint(String taskName, String sprintName, String board ){

        Assert.assertTrue(sprintBoard.isDisplayedTaskInSprint(sprintName, taskName, board));
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

    @And("^I select the sprint \"(.*?)\"$")
    public void selectTheSprint(String sprintName){

       sprintBoard= sprintMenu.selectSprint(sprintName);
    }

    @When("^I select the option \"(.*?)\" of task \"(.*?)\" from the \"(.*?)\" board$")
    public void selectOptionMenu(String taskOption, String taskName, String currentBoard){

        sprintBoard.selectDropDownButton(taskName, currentBoard);
        sprintBoard.selectOptionMenu(taskName, taskOption);

    }

    @When("^I set the \"(.*?)\" is \"(.*?)\" in the task \"(.*?)\"$")
    public void setHoursTask(String selectActualOrEstimatedHours, String hours, String nameTask ){

            if(selectActualOrEstimatedHours.equalsIgnoreCase(DomainAppConstants.ESTIMATED_HOURS)){

                sprintBoard.selectTask(nameTask);
                sprintBoard.setHoursNotStartedTask(selectActualOrEstimatedHours, hours);
            } else {
                sprintBoard.selectTask(nameTask);
                sprintBoard.setHoursStartedTask(selectActualOrEstimatedHours, hours);
            }
    }

    @Then("^the Estimated hours \"(.*?)\" is displayed in the task \"(.*?)\"$")
    public void isDisplayedTheHoursSet(String hours, String nameTask){

       Assert.assertTrue(sprintBoard.isHourSetDisplayed(hours, nameTask));
    }

    @When("^the task has the following work flow using buttons$")
    public void taskWorkFlow(DataTable table){

        for (Map<String, String> map : table.asMaps(String.class, String.class)){

            String taskOption = map.get("task option");
            String taskName = map.get("task name");
            String currentBoard = map.get("current board");

            sprintBoard.selectDropDownButton(taskName, currentBoard);
            sprintBoard.selectOptionMenu(taskName, taskOption);
        }
    }

    @Then("^the task \"(.*?)\" should be displayed in the board \"(.*?)\"$")
    public void isDisplayedTheTask(String nameTask, String boardName ){

        Assert.assertTrue(sprintBoard.isDisplayedTheTask(nameTask, boardName));

    }

    @When("^the task \"(.*?)\" has assigned to the member \"(.*?)\"$")
    public void assignTaskToAMember(String nameTask, String nameMember){

        sprintBoard.assignTaskToAMemeber(nameTask, nameMember);
    }

    @Then("^the task \"(.*?)\" should display the name of the member \"(.*?)\" in the task$")
    public void isDisplayedTheNameOfTheMember(String nameTask,String memberName){

        Assert.assertTrue(sprintBoard.isMemberDisplayedInTheTask(nameTask, memberName));
    }

    @When("^the task has the following work flow using drag and drop$")
    public void taskWorkFlowDragAndDrop(DataTable table){

        for(Map<String, String> map : table.asMaps(String.class, String.class)){
            String target = map.get("target");
            String nameTask = map.get("task name");
            String current = map.get("current");
            sprintBoard.dragAndDropTask(nameTask, target, current);
        }


    }

    @Given("^I move the task \"(.*?)\" to the board \"(.*?)\"$")
    public void taskDragAndDrop(String nameTask, String boardTarget){

        if (!boardTarget.equalsIgnoreCase(DomainAppConstants.NOT_STARTED_BOARD)){
            String current = DomainAppConstants.NOT_STARTED_BOARD;
            sprintBoard.dragAndDropTask(nameTask, boardTarget, current);
        }

    }



}
