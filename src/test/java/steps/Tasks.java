package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.java.After;
import cucumber.api.java.en.When;
import ui.pages.MainBoards;
import org.testng.Assert;
import ui.pages.MainPages;

import java.util.Map;

/**
 * User: RonaldButron
 * Date: 11/14/15
 */
public class Tasks {
    MainPages newMainPage = new MainPages();
    MainBoards task = newMainPage.getMainBoards();
    String taskName;
    String boardName;
    String taskDescription;
    String taskComment;

    @Given("^I have a new task \"(.*?)\" in the \"(.*?)\" dashboard$")
    public void createTask(String nameTask, String nameBoard){

        task.createTask(nameTask, nameBoard);
    }

    @And("^I insert a description \"(.*?)\" into the \"(.*?)\" task$")
    public void descriptionTask(String description, String taskName){

         task.clickOverTask(taskName);
         task.setDescriptionTask(description);
    }

    @And("^I insert a comment \"(.*?)\" into the \"(.*?)\" task$")
    public void commentTask(String comment, String taskName){
         task.setCommentTask(comment);

    }

    @When("^I save the task$")
    public void saveTask(){

        task.closeEditTask();

    }

    @Then("^The \"(.*?)\" task should be displayed$")
    public void taskDisplayed(String taskName){

       Assert.assertEquals(task.taskDisplayed(taskName), taskName, "The object is displayed" );

    }

    @When("^I drag and drop the \"(.*?)\" task to \"(.*?)\" dashboard$")
    public void dragAndDrop(String taskName, String boardName){

        task.dragAndDropTask(taskName, boardName);

    }

    @Given("^I have the following task$")
    public void hasTheFollowingTask(DataTable table){

        for(Map<String, String> map : table.asMaps(String.class, String.class)){

            taskName = map.get("task name");
            boardName = map.get("board name");
            taskDescription = map.get("description");
            taskComment = map.get("comment");
            task.createTheFollowingTask(taskName, boardName, taskDescription, taskComment);
        }


    }











}
