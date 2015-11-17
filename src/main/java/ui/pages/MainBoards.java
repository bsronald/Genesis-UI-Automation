package ui.pages;

import commons.DomainAppConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ui.BasePageObject;
import ui.pages.MainPage;

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
     *
     * @param nameTask
     * @param nameBoard
     * @return
     */

    public MainBoards createTask(String nameTask, String nameBoard){

        return  createTaskSuccessfully(nameTask, nameBoard);
    }

    /**
     *
     * @param nameTask
     * @param nameBoard
     * @return
     */
    private MainBoards createTaskSuccessfully(String nameTask, String nameBoard){

        selectAddTask(nameBoard);
        setName(nameTask, nameBoard);
        saveTask(nameBoard, nameTask);
        return this;
    }

    /**
     *
     * @param nameBoard
     */
    public void selectAddTask(String nameBoard) {

        WebElement taskName = driver.findElement(By.xpath("//div[@class='board-header']/div/span[contains(text(), '" + nameBoard + "')]/../../../div[@class='task-add-container']/span"));
        taskName.click();
    }

    /**
     *
     * @param nameBoard
     * @param nameTask
     */
    public void saveTask(String nameBoard, String nameTask){

        WebElement taskName = driver.findElement(By.xpath("//div[@class='board-header']/div/span[contains(text(), '"+ nameBoard +"')]/../../../div[@class='task-add-container']/span/form/button[contains(text(),'Save')]"));
        taskName.click();
        WebElement task = driver.findElement(By.xpath("//span[@class='board-task-title' and contains(text(), '"+ nameTask +"')]"));
        driverWait.until(ExpectedConditions.visibilityOf(task));

    }

    /**
     *
     * @param nameTask
     * @param nameBoard
     */
    public void setName(String nameTask, String nameBoard){

        WebElement taskName = driver.findElement(By.xpath("//div[@class='board-header']/div/span[contains(text(), '"+ nameBoard +"')]/../../../div[@class='task-add-container']/span/form/input"));
        taskName.clear();
        taskName.sendKeys(nameTask);
    }

    /**
     *
     * @param nameBoard
     */
    public void taskNoSaved(String nameBoard){
        WebElement taskName = driver.findElement(By.xpath("//div[@class='board-header']/div/span[contains(text(), '"+ nameBoard +"')]/../../../div[@class='task-add-container']/span/form/button[contains(text(),'Cancel')]"));
        taskName.click();
    }

    /**
     *
     * @param taskName
     */
    public void clickOverTask(String taskName){

        WebElement clickTask = driver.findElement(By.xpath("//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]"));
         clickTask.click();
         driverWait.until(ExpectedConditions.visibilityOf(archiveButton));
    }

    //Methods add a description to a task

    /**
     *
     * @param description
     */
    public void setDescriptionTask(String description) {

        clickDescriptionTask();
        insertTaskDescription(description);
        saveTaskDescription();
    }

    /**
     *
     */
    private void saveTaskDescription() {

        descriptionSave.click();
        driverWait.until(ExpectedConditions.visibilityOf(archiveButton));
    }

    /**
     *
     * @param description
     */

    public void insertTaskDescription(String description) {

        descriptionType.clear();
        descriptionType.sendKeys(description);
    }

    /**
     *
     */
    public void clickDescriptionTask() {

        descriptionInsert.click();
        driverWait.until(ExpectedConditions.visibilityOf(descriptionSave));
    }

    /**
     *
     * @param comment
     */
    public void setCommentTask(String comment) {

        clickCommentTask();
        typeCommentTask(comment);
        saveCommentTask();
    }

    // Comment methods

    /**
     *
     */
    public void saveCommentTask() {

        commentSave.click();
        driverWait.until(ExpectedConditions.visibilityOf(archiveButton));
    }

    /**
     *
     * @param comment
     */
    public void typeCommentTask(String comment) {

        commentType.clear();
        commentType.sendKeys(comment);
    }

    /**
     *
     */
    public void clickCommentTask() {

        commnentInsert.click();
        driverWait.until(ExpectedConditions.visibilityOf(commentSave));

    }

    //Close Window Edit task
    /**
     *
     */
    public void closeEditTask(){

        closeEditTaskButton.click();
    }

    /**
     *
     * @param taskName
     * @return
     */
    public String taskDisplayed(String taskName) {
        //Review
        WebElement taskDisp = driver.findElement(By.xpath("//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]"));
        return taskDisp.getText();
    }

    /**
     *
     * @param taskName
     * @param boardName
     */
    public void dragAndDropTask(String taskName, String boardName) {

        WebElement selectedTask = driver.findElement(By.xpath("//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]"));
        WebElement target = driver.findElement(By.xpath("//div[@class='board-header']/div/span[contains(text(), '"+ boardName+"')]/../../../ul"));
        Actions builder= new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(selectedTask)
                                    .moveToElement(target)
                                    .release(target)
                                    .build();
        dragAndDrop.perform();

    }
}
