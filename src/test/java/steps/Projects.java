package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.java.After;
import cucumber.api.java.en.When;
import ui.pages.MainPage;
import org.testng.Assert;

import java.util.Map;

/**
 * User: RonaldButron
 * Date: 11/13/15
 */
public class Projects {

    MainPage mainPage = new MainPage();
    String nameProj;
    String startDate;
    String endDate;
    @And("^I have a Project \"(.*?)\"$")
    public void createAProject(String projectName){
        mainPage.createNewProject(projectName);
        nameProj = projectName;




    }

    @And("^the Start Date is \"(.*?)\"$")
    public void projectStarDate(String startDate){
         mainPage.setStartDate(startDate);


    }

    @And("^the End Date is \"(.*?)\"$")
    public void projectFinishDate(String finishDate){
        mainPage.setFinishDate(finishDate);

    }

    @Then("^the project \"(.*?)\" should be displayed in project board$")
    public void projectDisplayed(String nameProject){

       Assert.assertTrue(mainPage.prjCreatedIsDisplayed(nameProject), "Project Displayed");

    }

    @When("^I delete the project \"(.*?)\"$")
    public void archivePrj(String namePrj){

        mainPage.deletePrj(namePrj);

    }

    @Then("^the project \"(.*?)\" should not be displayed in the projects dashboard$")
    public void projectIsNotDisplayed(String namePrj){

        Assert.assertFalse(mainPage.prjIsNotDisplayed(namePrj), "Project Deleted");

    }

    @When("^I save the project \"(.*?)\"$")
    public void saveProject(String namePrj){

        mainPage.saveProjectButton(namePrj);
    }


    @Given("^I have the following Project$")
    public void theFollowingProject(DataTable table){

        for (Map <String, String> map : table.asMaps(String.class, String.class)){
             nameProj = map.get("name");
             startDate = map.get("start date");
             endDate = map.get("end date");
             System.out.print("Project name: " + nameProj + "\n" + "Start date: "  + startDate + "\n" + "End date: " + endDate );
        }

        mainPage.createSuccessfullyProject(nameProj, startDate, endDate);


    }

    @After(value = "@Projects", order = 999)
    public void deleteProject(){

        mainPage.deletePrj(nameProj);
    }






}
