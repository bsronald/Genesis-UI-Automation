package ui.pages;

import common.CommonMethods;
import commons.DomainAppConstants;
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
 * Date: 11/24/15
 */
public class SprintBoard extends BasePageObject {

    @FindBy(xpath = "//div[@class='sprint-header-content']")
    @CacheLookup
    WebElement sprintBoardHeader;

    @FindBy(xpath = "//div[@class='dropdown open']/ul[@role='menu']/li/a")
    @CacheLookup
    WebElement taskDropDownMenuDisplayed;

    @FindBy(xpath = "//input[@name='inplace_value']")
    @CacheLookup
    WebElement hoursValueInput;

    @FindBy(xpath = "//button[@class='inplace_save']")
    @CacheLookup
    WebElement saveHoursValueButton;

    @FindBy(xpath = "//button[@class='btn task-archive-link' and contains(text(), 'Archive...')]")
    @CacheLookup
    WebElement archiveButton;

    @FindBy(xpath = "//button[@class='close']")
    @CacheLookup
    WebElement closeEditTaskButton;



    @Override
    public void waitUntilPageObjectIsLoaded() {
        driverWait.until(ExpectedConditions.visibilityOf(sprintBoardHeader));
    }

    public SprintBoard(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    /**
     * This method verify that if a task is displayed inside a sprint
     * @param sprintName sprint name
     * @param taskName task name
     * @param taskBoard board where id the task
     * @return true if is displayed false if not
     */
    public Boolean isDisplayedTaskInSprint(String sprintName, String taskName, String taskBoard){

        return  UIMethods.waitElementIsPresent(10, By.xpath("//div[@class='sprint-task sprint-droppable " + taskBoard + "']/div/div[@class='task-title' and contains(text(), '" + taskName + "')]"));
    }

    /**
     *  This method make a click over a drop down button of a task
     * @param taskName task name
     * @param boarName board name
     */
    public void selectDropDownButton(String taskName, String boarName) {

        String status = boardStatus(boarName);
        WebElement selectbutton = driver.findElement(By.xpath(buildXpathStatusBoard(status) + "[@class='task-title' and contains(text(), '" + taskName + "')]/../following-sibling::div/div[@class='dropdown']/div"));
        selectbutton.click();



    }

    /**
     * This method select a Option of a menu of the task
     * @param taskName task name
     * @param taskOption task option
     */
    public void selectOptionMenu(String taskName, String taskOption){

        String status = boardStatusAction(taskOption);
        WebElement selectOptionMenu = driver.findElement(By.xpath("//div[@class='dropdown open']/ul/li/a[contains(text(), '"+ taskOption +"')]"));
        driverWait.until(ExpectedConditions.elementToBeClickable(selectOptionMenu));
        selectOptionMenu.click();
        WebElement taskSprint = driver.findElement(By.xpath(buildXpathStatusBoard(status) + "[contains(text(), '"+ taskName +"')]"));
        driverWait.until(ExpectedConditions.visibilityOf(taskSprint));
    }

    /**
     * This method select a specific task
     * @param taskName task name
     */
    public void selectTask(String taskName) {

        WebElement selectTask = driver.findElement(By.xpath("//div[@class='task-title' and contains(text(), '" + taskName + "')]"));
        selectTask.click();

    }

    /**
     * This method set the Hours of the a task not started
     * @param estimatedOrActualHours  estimated Or ActualHours
     * @param hours set the hours
     */
    public void setHoursNotStartedTask(String estimatedOrActualHours, String hours) {

        WebElement setHoursTask = driver.findElement(By.xpath("//span[contains(text(), '"+ estimatedOrActualHours +"')]/following-sibling::span"));
        CommonMethods.elementHighlight(setHoursTask);
        setHoursTask.click();
        hoursValueInput.clear();
        hoursValueInput.sendKeys(hours);
        saveHoursValueButton.click();
        closeEditTaskButton.click();
        WebElement hoursDisplayed = driver.findElement(By.xpath("//div[@class='task-time']/span[contains(text(), '" + hours + "')]"));
        driverWait.until(ExpectedConditions.visibilityOf(hoursDisplayed));
    }

    /**
     * This method set the Hours of the a task  started
     * @param estimatedOrActualHours  estimated Or ActualHours
     * @param hours set the hours
     */
    public void setHoursStartedTask(String estimatedOrActualHours, String hours) {

        WebElement setHoursTaskStarted = driver.findElement(By.xpath("//span[contains(text(), '" + estimatedOrActualHours + "')]/following-sibling::span[@class='task-input-editable' and @field='actual-hours']"));
        CommonMethods.elementHighlight(setHoursTaskStarted);
        setHoursTaskStarted.click();
        hoursValueInput.clear();
        hoursValueInput.sendKeys(hours);
        saveHoursValueButton.click();
        closeEditTaskButton.click();
    }

    /**
     * This method verify if the hours set is displayed
     * @param hours hours to verify
     * @param nameTask task name
     * @return true if find the hours false if not
     */
    public Boolean isHourSetDisplayed(String hours, String nameTask) {

         return UIMethods.waitElementIsPresent(10, By.xpath("//div[@class='task-time']/span[contains(text(), '" + hours + "')]/../../following-sibling::div/div[@class='task-title' and contains(text(), '" + nameTask + "')]"));
    }

    /**
     * This method verify if the task is displayed
     * @param nameTask name task
     * @param boardName board name
     * @return true if find the task false if not
     */
    public Boolean isDisplayedTheTask(String nameTask, String boardName) {

        String statusBoard = boardStatus(boardName);
        return UIMethods.waitElementIsPresent(10, By.xpath(buildXpathStatusBoard(statusBoard) + "[contains(text(), '"+ nameTask +"')]"));
    }

    /**
     * This method assgin a task to a member
     * @param taskName task name
     * @param memberName member name
     */
    public void assignTaskToAMemeber(String taskName, String memberName) {

        clickIconMember(taskName);
        selectMemberName(memberName, taskName);
    }

    /**
     * This method select a member
     * @param memberName  member name
     * @param taskName task name
     */
    private void selectMemberName(String memberName, String taskName) {

        WebElement selectMember = driver.findElement(By.xpath(buildXpathManageTask(taskName) + "/div/ul/li/a[contains(text(), '" + memberName + "')]"));
        driverWait.until(ExpectedConditions.elementToBeClickable(selectMember));
        selectMember.click();
    }

    /**
     * click over a member icon
     * @param nameTask task name
     */
    private void clickIconMember(String nameTask) {

        WebElement memberIcon = driver.findElement(By.xpath(buildXpathManageTask(nameTask) + "/div/span/span[@class='icon-user']"));
        memberIcon.click();
    }

    /**
     * This method verify if the member is displayed in the task
     * @param taskName task name
     * @param memberName member name
     * @return verify if the member is displayed
     */
    public Boolean isMemberDisplayedInTheTask(String taskName, String memberName){

          return UIMethods.waitElementIsPresent(10, By.xpath(buildXpathManageTask(taskName) + "/div/span/span[contains(text(), '" + memberName + "')]"));
    }

    /**
     * This method drag and drop task to another board
     * @param nameTask
     * @param target
     * @param current
     */
    public void dragAndDropTask(String nameTask, String target, String current) {

        String currentBoard = boardStatus(current);
        String status = boardStatus(target);
        WebElement selectedTask = driver.findElement(By.xpath(buildXpathStatusBoard(currentBoard) + "[@class='task-title' and contains(text(), '"+ nameTask +"')]"));
        WebElement boardTarget = driver.findElement(By.xpath("//td[@class='sprint-task-content ui-sortable' and @status='"+ status +"']"));
        Actions builder= new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(selectedTask)
                                    .moveToElement(boardTarget)
                                    .release(boardTarget)
                                    .build();
        dragAndDrop.perform();
        if (!target.equalsIgnoreCase(DomainAppConstants.DONE_BOARD)){
            UIMethods.waitElementIsNotPresent(5, By.xpath(buildXpathStatusBoard(currentBoard) + "[contains(text(), '"+ nameTask +"')]"));
        }
    }

    /**
     * This method build a path for manage tasks
     * @param taskName task name
     * @return bu
     */
    public String buildXpathManageTask(String taskName){

        return "//div[@class='task-title' and contains(text(), '" + taskName + "')]/../preceding-sibling::div[@class='sprint-task-header']";
    }

    /**
     * This method build a path for the status board
     * @param statusBoard status board
     * @return a string with the path
     */
    public String buildXpathStatusBoard(String statusBoard){

        return "//td[@class='sprint-task-content ui-sortable' and @status='" + statusBoard + "']/div/div/div";
    }

    /**
     * This method return the status of the future board where is going to be the task
     * @param boardName board name
     * @return the status of the board
     */
    public String boardStatus(String boardName){

        String boardStatus = null;
        if (boardName.equalsIgnoreCase(DomainAppConstants.NOT_STARTED_BOARD)){

            boardStatus = "0";
        }

        if (boardName.equalsIgnoreCase(DomainAppConstants.IN_PROGRESS_BOARD)){

            boardStatus = "1";
        }

        if (boardName.equalsIgnoreCase(DomainAppConstants.TESTING_BOARD)){

            boardStatus = "2";
        }

        if (boardName.equalsIgnoreCase(DomainAppConstants.DONE_BOARD)){

            boardStatus = "3";
        }

        return boardStatus;
    }

    /**
     * This method return the status of the board or the identifier of each board
     * @param taskOption task option
     * @return the status of the board
     */
    public String boardStatusAction(String taskOption){

        String status="0";
        if (taskOption.equalsIgnoreCase(DomainAppConstants.START_TASK)){
            status = "1";
        }
        if (taskOption.equalsIgnoreCase(DomainAppConstants.DELIVER_TASK)){
            status = "2";
        }
        if (taskOption.equalsIgnoreCase(DomainAppConstants.REJECT_TASK)){
            status = "1";
        }
        if (taskOption.equalsIgnoreCase(DomainAppConstants.ACCEPT_TASK)){
            status = "3";
        }
        return status;
    }

}
