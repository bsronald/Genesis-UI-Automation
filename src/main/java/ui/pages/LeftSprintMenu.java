package ui.pages;

import framework.UIMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * User: RonaldButron
 * Date: 11/17/15
 */
public class LeftSprintMenu extends BasePageObject {

    String nameSprint;

    @FindBy(xpath = "//div[@id='sprints-add-link']/span")
    @CacheLookup
    WebElement newSprintLink;

    @FindBy(xpath = "//button[@class='btn btn-primary' and contains(text(), 'Save')]")
    @CacheLookup
    WebElement saveSprintButton;

    @FindBy(id = "id_name")
    @CacheLookup
    WebElement inserSprintName;

    @FindBy(id = "id_start_date")
    @CacheLookup
    WebElement insertSprintStartDate;

    @FindBy(id = "id_end_date")
    @CacheLookup
    WebElement insertSprintEndDate;

    @FindBy(id = "id_total_possible_hours")
    @CacheLookup
    WebElement insertCapacityHours;

    @FindBy(id = "id_archive")
    @CacheLookup
    WebElement selectArchiveSprint;

    @Override
    public void waitUntilPageObjectIsLoaded() {

        driverWait.until(ExpectedConditions.visibilityOf(newSprintLink));
    }


    public LeftSprintMenu(){

        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    /**
     * This method create a new Sprint inside a project
     * @param sprintName is the name of the sprint
     */
    public void createNewSprint(String sprintName) {

        driverWait.until(ExpectedConditions.visibilityOf(newSprintLink));
        nameSprint = sprintName;
        addNewSPrint();
        setNameSprint(sprintName);

    }

    /**
     * This method make a click in the new sprint link
     */
    public void addNewSPrint(){

        newSprintLink.click();
        driverWait.until(ExpectedConditions.visibilityOf(saveSprintButton));
    }

    /**
     * This method insert the name of the sprint
     * @param nameSprint is the sprint name
     */
    public void setNameSprint(String nameSprint){

        inserSprintName.clear();
        inserSprintName.sendKeys(nameSprint);
    }

    /**
     * This method set the hours of the sprint
     * @param hours
     */
    public void setCapacityInHours(String hours){

        insertCapacityHours.clear();
        insertCapacityHours.sendKeys(hours);

    }

    /**
     * This method set the star date of the sprint
     * @param startDate
     */
    public void setStartDate(String startDate){

        insertSprintStartDate.clear();
        insertSprintStartDate.sendKeys(startDate);

    }

    /**
     *  This method set the finish date of the sprint
     * @param finishDate
     */

    public void setFinishDate(String finishDate){

        insertSprintEndDate.clear();
        insertSprintEndDate.sendKeys(finishDate);

    }

    /**
     * This method make a click over the save sprint button
     */
    public void saveNewSprint(){

        saveSprintButton.click();


    }

    /**
     * This method verify if a sprint is displayed
     * @param sprintName the name of the sprint to validate
     * @return True if the sprint is displayed and false if is not displayed
     */
    public Boolean sprintIsDisplayed(String sprintName){

       return UIMethods.isElementPresent(By.xpath(buildXpathSprintHeader(sprintName)));
    }

    /**
     * This method select a sprint
     * @param sprintName to be selected
     * @return a new Page Sprint Board
     */
    public SprintBoard selectSprint(String sprintName){

        WebElement sprint = driver.findElement(By.xpath(buildXpathSprintHeader(sprintName)));
        sprint.click();

        return new SprintBoard();
    }

    /**
     * Drag and Drop a task to a specific sprint
     * @param taskName to find
     * @param sprintName the target to drag and drop
     */
    public void dragAndDropTask(String taskName, String sprintName){

        WebElement selectedTask = driver.findElement(By.xpath("//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]"));
        WebElement target = driver.findElement(By.xpath(buildXpathSprintHeader(sprintName)));
        Actions builder= new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(selectedTask)
                                    .moveToElement(target)
                                    .release(target)
                                    .build();
        dragAndDrop.perform();
        UIMethods.waitElementIsNotPresent(5, By.xpath("//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]"));
    }

    /**
     * Verify is task is displayed insisde a sprint
     * @param sprintName sprint name
     * @param taskName  task name
     * @param taskBoard  board
     * @return true is task is displayed inside the board and false if not.
     */
    public Boolean isDisplayedTaskInSprint(String sprintName, String taskName, String taskBoard){


        return  UIMethods.waitElementIsPresent(10, By.xpath("//div[@class='sprint-task sprint-droppable "+ taskBoard +"']/div/div[@class='task-title' and contains(text(), '"+ taskName +"')]"));
    }

    /**
     *  Build a string path to find a specific sprint
     * @param sprintName  sprint name
     * @return the path builded
     */
    public String buildXpathSprintHeader(String sprintName){

        return "//div[@class='dashboard-sprint-header']/div[contains(text(), '"+ sprintName +"')]";
    }

    /**
     * This method create a new Sprint
     * @param sprintName sprint name
     * @param startDate  start date
     * @param endDate    finish date
     * @param capacityHours hours of the sprint
     */
    public void createSuccessfullySprint(String sprintName, String startDate, String endDate, String capacityHours){

          createNewSprint(sprintName);
          setCapacityInHours(capacityHours);
          setStartDate(startDate);
          setFinishDate(endDate);
          saveNewSprint();

    }
}
