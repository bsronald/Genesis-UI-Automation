package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.java.After;
import cucumber.api.java.en.When;
import ui.pages.TopMenu;
import org.testng.Assert;
import ui.pages.MainPages;

import java.util.Map;

/**
 * User: RonaldButron
 * Date: 11/13/15
 */
public class Projects {
    MainPages newMainPage = new MainPages();
    TopMenu topMenu;
    String nameProj;
    String startDate;
    String endDate;
    @And("^I have a Project \"(.*?)\"$")
    public void createAProject(String projectName){

        topMenu = newMainPage.getTopMenu();
        topMenu.createNewProject(projectName);
        nameProj = projectName;

    }

    @And("^the Start Date is \"(.*?)\"$")
    public void projectStarDate(String startDate){
         topMenu.setStartDate(startDate);


    }

    @And("^the End Date is \"(.*?)\"$")
    public void projectFinishDate(String finishDate){
        topMenu.setFinishDate(finishDate);

    }

    @Then("^the project \"(.*?)\" should be displayed in project board$")
    public void projectDisplayed(String nameProject){

       Assert.assertTrue(topMenu.prjCreatedIsDisplayed(nameProject), "Project Displayed");

    }

    @When("^I delete the project \"(.*?)\"$")
    public void archivePrj(String namePrj){

        topMenu.deletePrj(namePrj);

    }

    @Then("^the project \"(.*?)\" should not be displayed in the projects dashboard$")
    public void projectIsNotDisplayed(String namePrj){

        Assert.assertFalse(topMenu.prjIsNotDisplayed(namePrj), "Project Deleted");


    }

    @When("^I save the project \"(.*?)\"$")
    public void saveProject(String namePrj){

        topMenu.saveProjectButton(namePrj);
    }


    @Given("^I have the following Project$")
    public void theFollowingProject(DataTable table){

        for (Map <String, String> map : table.asMaps(String.class, String.class)){
             nameProj = map.get("name");
             startDate = map.get("start date");
             endDate = map.get("end date");
             System.out.print("Project name: " + nameProj + "\n" + "Start date: "  + startDate + "\n" + "End date: " + endDate );
        }

        topMenu = newMainPage.getTopMenu();
        topMenu.createSuccessfullyProject(nameProj, startDate, endDate);


    }

    @After(value = "@Projects", order = 999)
    public void deleteProject(){

        topMenu.deletePrj(nameProj);
    }






}
