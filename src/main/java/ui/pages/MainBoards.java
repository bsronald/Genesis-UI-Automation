package ui.pages;

import framework.UIMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * User: RonaldButron
 * Date: 11/14/15
 */
public class MainBoards extends BasePageObject {

    @FindBy(css = "span.board-title-editable")
    @CacheLookup
    WebElement ideaBoardDisplayed;

     @FindBy(xpath = "//button[@class='btn task-archive-link' and contains(text(), 'Archive...')]")
     @CacheLookup
     WebElement archiveButton;

    @FindBy(xpath = "//span[@id='task-description-editable']/p")
    @CacheLookup
    WebElement descriptionInsert;

    @FindBy(id = "description_text")
    @CacheLookup
    WebElement descriptionType;

    @FindBy(xpath = "//div[@id='description_editor']/div/input")
    @CacheLookup
    WebElement descriptionSave;

    @FindBy(id = "task-comment-editable")
    @CacheLookup
    WebElement commnentInsert;

    @FindBy(name = "inplace_value")
    @CacheLookup
    WebElement commentType;

    @FindBy(xpath = "//div[@id='task-comment-editable']/form/button[contains(text(), 'Save')]")
    @CacheLookup
    WebElement commentSave;

    @FindBy(xpath = "//button[@class='close' and contains(text(), x)]")
    @CacheLookup
    WebElement closeEditTaskButton;



    @Override
    public void waitUntilPageObjectIsLoaded() {

        driverWait.until(ExpectedConditions.visibilityOf(ideaBoardDisplayed));
    }

    public MainBoards(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    /**
     * Create a new task
     * @param nameTask task name
     * @param nameBoard board name where will be create the task
     * @return the same page
     */

    public MainBoards createTask(String nameTask, String nameBoard){

        return  createTaskSuccessfully(nameTask, nameBoard);
    }

    /**
     * Create a new task
     * @param nameTask task name
     * @param nameBoard board name where will be create the task
     * @return the same page
     */
    private MainBoards createTaskSuccessfully(String nameTask, String nameBoard){

        selectAddTask(nameBoard);
        setName(nameTask, nameBoard);
        saveTask(nameBoard, nameTask);
        return this;
    }

    /**
     * Select add a new task
     * @param nameBoard boar name where is going to be created the task
     */
    public void selectAddTask(String nameBoard) {

        WebElement taskName = driver.findElement(By.xpath(buildXpathForBoard(nameBoard) + "div[@class='task-add-container']/span"));
        taskName.click();
    }

    /**
     * Click the button save task
     * @param nameBoard name board
     * @param nameTask name task
     */
    public void saveTask(String nameBoard, String nameTask){

        WebElement taskName = driver.findElement(By.xpath(buildXpathForBoard(nameBoard) + "div[@class='task-add-container']/span/form/button[contains(text(),'Save')]"));
        taskName.click();
        WebElement task = driver.findElement(By.xpath(buildXpathForTask(nameTask)));
        driverWait.until(ExpectedConditions.visibilityOf(task));

    }

    /**
     * Set the name of the task
     * @param nameTask task name
     * @param nameBoard board name
     */
    public void setName(String nameTask, String nameBoard){

        WebElement taskName = driver.findElement(By.xpath(buildXpathForBoard(nameBoard) + "div[@class='task-add-container']/span/form/input"));
        taskName.clear();
        taskName.sendKeys(nameTask);
    }

    /**
     * select cancel button no save task
     * @param nameBoard  board name
     */
    public void taskNoSaved(String nameBoard){

        WebElement taskName = driver.findElement(By.xpath(buildXpathForBoard(nameBoard)+ "div[@class='task-add-container']/span/form/button[contains(text(),'Cancel')]"));
        taskName.click();
    }

    /**
     * select a task
     * @param taskName task name to select
     */
    public void clickOverTask(String taskName){

         WebElement clickTask = driver.findElement(By.xpath(buildXpathForTask(taskName)));
         clickTask.click();
         driverWait.until(ExpectedConditions.visibilityOf(archiveButton));
    }


    /**
     * Insert description to a task all the work flow
     * @param description to insert
     */
    public void setDescriptionTask(String description) {

        clickDescriptionTask();
        insertTaskDescription(description);
        saveTaskDescription();
    }

    /**
     * Save the description insert in a task
     */
    private void saveTaskDescription() {

        descriptionSave.click();
        driverWait.until(ExpectedConditions.visibilityOf(archiveButton));
    }

    /**
     * Set a description
     * @param description to insert
     */

    public void insertTaskDescription(String description) {

        descriptionType.clear();
        descriptionType.sendKeys(description);
    }

    /**
     * Click to insert a description
     */
    public void clickDescriptionTask() {

        descriptionInsert.click();
        driverWait.until(ExpectedConditions.visibilityOf(descriptionSave));
    }

    /**
     * Work flow to insert a comment in a task
     * @param comment
     */
    public void setCommentTask(String comment) {

        clickCommentTask();
        typeCommentTask(comment);
        saveCommentTask();
    }

    /**
     *  This method save the comment inserted
     */
    public void saveCommentTask() {

        commentSave.click();
        driverWait.until(ExpectedConditions.visibilityOf(archiveButton));
    }

    /**
     * This method type the comment
     * @param comment
     */
    public void typeCommentTask(String comment) {

        commentType.clear();
        commentType.sendKeys(comment);
    }

    /**
     * This method make a click to inser a comment
     */
    public void clickCommentTask() {

        commnentInsert.click();
        driverWait.until(ExpectedConditions.visibilityOf(commentSave));

    }

    /**
     * This method close the form of edit task
     */
    public void closeEditTask(){

        closeEditTaskButton.click();
    }

    /**
     * This method verify if a task is displayed inside a board
     * @param taskName  task name to verify
     * @return true if the task is displayed false if not
     */
    public Boolean isTaskDisplayedInBoard(String taskName, String dashBoard) {

        return UIMethods.waitElementIsPresent(10, By.xpath(buildXpathTaskInABoard(dashBoard, taskName)));
    }

    /**
     * This method move the task from a current board to a target board
     * @param taskName task name
     * @param boardNameTarget target board
     * @param boardNameCurrent current board
     */
    public void dragAndDropTask(String taskName, String boardNameCurrent, String boardNameTarget) {

        WebElement selectedTask = driver.findElement(By.xpath(buildXpathTaskInABoard(boardNameCurrent, taskName)));
        WebElement target = driver.findElement(By.xpath(buildXpathForBoard(boardNameTarget)+"ul"));
        Actions builder= new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(selectedTask)
                                    .moveToElement(target)
                                    .release(target)
                                    .build();
        dragAndDrop.perform();

    }

    /**
     * This method build a xpath
     * @param boarName  board name
     * @param taskName  task name
     * @return a String with xpath build
     */
    public String buildXpathTaskInABoard(String boarName, String taskName){

           return "//span[@class='board-title-editable' and contains(text(), '"+ boarName +"')]/../../following-sibling::ul/li/span[@class='board-task-title' and contains(text(), '" + taskName + "')]";
    }

    /**
     * This method build a xpath
     * @param taskName task name
     * @return a String with xpath build
     */
    public String buildXpathForTask(String taskName){

        return "//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]";
    }

    /**
     * This method build a xpath
     * @param boardName board name
     * @return a String with xpath build
     */
    public String buildXpathForBoard(String boardName){

        return "//div[@class='board-header']/div/span[contains(text(), '"+ boardName +"')]/../../../";
    }

    /**
     * This method create a task with description and comments
     * @param taskName task name
     * @param boardName board to create task
     * @param taskDescription description
     * @param taskComment comment
     */
    public void createTheFollowingTask(String taskName, String boardName, String taskDescription, String taskComment){

        selectAddTask(boardName);
        setName(taskName, boardName);
        saveTask(boardName, taskName);
        clickOverTask(taskName);
        setDescriptionTask(taskDescription);
        setCommentTask(taskComment);
        closeEditTask();
    }


}
