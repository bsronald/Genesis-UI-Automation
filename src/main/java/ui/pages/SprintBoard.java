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

    public Boolean isDisplayedTaskInSprint(String sprintName, String taskName, String taskBoard){

        return  UIMethods.waitElementIsPresent(10, By.xpath("//div[@class='sprint-task sprint-droppable " + taskBoard + "']/div/div[@class='task-title' and contains(text(), '" + taskName + "')]"));
    }

    public void selectDropDownButton(String taskName, String boarName) {

        String status = boardStatus(boarName);
        WebElement selectbutton = driver.findElement(By.xpath("//td[@class='sprint-task-content ui-sortable' and @status='" + status + "']/div/div/div[@class='task-title' and contains(text(), '" + taskName + "')]/../following-sibling::div/div[@class='dropdown']/div"));
        selectbutton.click();



    }

    public void selectOptionMenu(String taskName, String taskOption){

        String status = boardStatusAction(taskOption);
        WebElement selectOptionMenu = driver.findElement(By.xpath("//div[@class='dropdown open']/ul/li/a[contains(text(), '"+ taskOption +"')]"));
        driverWait.until(ExpectedConditions.elementToBeClickable(selectOptionMenu));
        selectOptionMenu.click();
        WebElement taskSprint = driver.findElement(By.xpath("//td[@class='sprint-task-content ui-sortable' and @status='"+ status +"']/div/div/div[contains(text(), '"+ taskName +"')]"));
        driverWait.until(ExpectedConditions.visibilityOf(taskSprint));
    }

    public void selectTask(String taskName) {

        WebElement selectTask = driver.findElement(By.xpath("//div[@class='task-title' and contains(text(), '" + taskName + "')]"));
        selectTask.click();

    }


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

    public void setHoursStartedTask(String estimatedOrActualHours, String hours) {

        WebElement setHoursTaskStarted = driver.findElement(By.xpath("//span[contains(text(), '" + estimatedOrActualHours + "')]/following-sibling::span[@class='task-input-editable' and @field='actual-hours']"));
        CommonMethods.elementHighlight(setHoursTaskStarted);
        setHoursTaskStarted.click();
        hoursValueInput.clear();
        hoursValueInput.sendKeys(hours);
        saveHoursValueButton.click();
        closeEditTaskButton.click();
    }


    public Boolean isHourSetDisplayed(String hours, String nameTask) {

         return UIMethods.waitElementIsPresent(10, By.xpath("//div[@class='task-time']/span[contains(text(), '" + hours + "')]/../../following-sibling::div/div[@class='task-title' and contains(text(), '" + nameTask + "')]"));

    }

    public Boolean isDisplayedTheTask(String nameTask, String boardName) {

        String statusBoard = boardStatus(boardName);
        return UIMethods.waitElementIsPresent(10, By.xpath("//td[@class='sprint-task-content ui-sortable' and @status='"+ statusBoard +"']/div/div/div[contains(text(), '"+ nameTask +"')]"));
    }

    public void assignTaskToAMemeber(String taskName, String memberName) {

        clickIconMember(taskName);
        selectMemberName(memberName, taskName);
    }

    private void selectMemberName(String memberName, String taskName) {

        WebElement selectMember = driver.findElement(By.xpath("//div[@class='task-title' and contains(text(), '" + taskName + "')]/../preceding-sibling::div[@class='sprint-task-header']/div/ul/li/a[contains(text(), '" + memberName + "')]"));
        driverWait.until(ExpectedConditions.elementToBeClickable(selectMember));
        selectMember.click();
    }

    private void clickIconMember(String nameTask) {

        WebElement memberIcon = driver.findElement(By.xpath("//div[@class='task-title' and contains(text(), '" + nameTask + "')]/../preceding-sibling::div[@class='sprint-task-header']/div/span/span[@class='icon-user']"));
        memberIcon.click();
    }

    public Boolean isMemberDisplayedInTheTask(String taskName, String memberName){

          return UIMethods.waitElementIsPresent(10, By.xpath("//div[@class='task-title' and contains(text(), '" + taskName + "')]/../preceding-sibling::div[@class='sprint-task-header']/div/span/span[contains(text(), '" + memberName + "')]"));
    }

    public void dragAndDropTask(String nameTask, String target, String current) {

        String currentBoard = boardStatus(current);
        String status = boardStatus(target);
        WebElement selectedTask = driver.findElement(By.xpath("//td[@class='sprint-task-content ui-sortable' and @status='"+ currentBoard +"']/div/div/div[@class='task-title' and contains(text(), '"+ nameTask +"')]"));
        WebElement boardTarget = driver.findElement(By.xpath("//td[@class='sprint-task-content ui-sortable' and @status='"+ status +"']"));
        Actions builder= new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(selectedTask)
                                    .moveToElement(boardTarget)
                                    .release(boardTarget)
                                    .build();
        dragAndDrop.perform();
        if (!target.equalsIgnoreCase(DomainAppConstants.DONE_BOARD)){
            UIMethods.waitElementIsNotPresent(5, By.xpath("//td[@class='sprint-task-content ui-sortable' and @status='"+ currentBoard +"']/div/div/div[contains(text(), '"+ nameTask +"')]"));
        }
    }

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
