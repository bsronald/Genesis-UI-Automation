package ui.pages;

import commons.DomainAppConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    public MainBoards createTask(String nameTask, String nameBoard){

        return  createTaskSuccessfully(nameTask, nameBoard);
    }

    //Create task methods
    private MainBoards createTaskSuccessfully(String nameTask, String nameBoard){

        selectAddTask(nameBoard);
        setName(nameTask, nameBoard);
        saveTask(nameBoard, nameTask);
        return this;
    }

    public void selectAddTask(String nameBoard) {

        WebElement taskName = driver.findElement(By.xpath("//div[@class='board-header']/div/span[contains(text(), '" + nameBoard + "')]/../../../div[@class='task-add-container']/span"));
        taskName.click();
    }

    public void saveTask(String nameBoard, String nameTask){

        WebElement taskName = driver.findElement(By.xpath("//div[@class='board-header']/div/span[contains(text(), '"+ nameBoard +"')]/../../../div[@class='task-add-container']/span/form/button[contains(text(),'Save')]"));
        taskName.click();
        WebElement task = driver.findElement(By.xpath("//span[@class='board-task-title' and contains(text(), '"+ nameTask +"')]"));
        driverWait.until(ExpectedConditions.visibilityOf(task));

    }


    public void setName(String nameTask, String nameBoard){

        WebElement taskName = driver.findElement(By.xpath("//div[@class='board-header']/div/span[contains(text(), '"+ nameBoard +"')]/../../../div[@class='task-add-container']/span/form/input"));
        taskName.clear();
        taskName.sendKeys(nameTask);
    }

    public void taskNoSaved(String nameBoard){
        WebElement taskName = driver.findElement(By.xpath("//div[@class='board-header']/div/span[contains(text(), '"+ nameBoard +"')]/../../../div[@class='task-add-container']/span/form/button[contains(text(),'Cancel')]"));
        taskName.click();
    }


    public void clickOverTask(String taskName){

        WebElement clickTask = driver.findElement(By.xpath("//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]"));
         clickTask.click();
         driverWait.until(ExpectedConditions.visibilityOf(archiveButton));
    }

    //Methods add a description to a task
    public void setDescription(String description) {

        clickDescriptionTask();
        insertTaskDescription(description);
        saveTaskDescription();
    }

    private void saveTaskDescription() {

        descriptionSave.click();
        driverWait.until(ExpectedConditions.visibilityOf(archiveButton));
    }

    public void insertTaskDescription(String description) {

        descriptionType.clear();
        descriptionType.sendKeys(description);
    }

    public void clickDescriptionTask() {

        descriptionInsert.click();
        driverWait.until(ExpectedConditions.visibilityOf(descriptionSave));
    }

    public void setComment(String comment) {

        clickComment();
        typeComment(comment);
        saveComment();
    }

    // Comment methods
    public void saveComment() {

        commentSave.click();
        driverWait.until(ExpectedConditions.visibilityOf(archiveButton));
    }

    public void typeComment(String comment) {

        commentType.clear();
        commentType.sendKeys(comment);
    }

    public void clickComment() {

        commnentInsert.click();
        driverWait.until(ExpectedConditions.visibilityOf(commentSave));

    }

    //Close Window Edit task
    public void closeEditTask(){

        closeEditTaskButton.click();
    }

    //Verify task exists
    public String taskDisplayed(String taskName) {
        WebElement taskDisp = driver.findElement(By.xpath("//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]"));
        return taskDisp.getText();
    }
}