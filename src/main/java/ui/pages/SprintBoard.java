package ui.pages;

import framework.UIMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    public void selectDropDownButton(String taskName) {

        WebElement selectbutton = driver.findElement(By.xpath("//div[@class='task-title' and contains(text(), '"+ taskName +"')]/../following-sibling::div/div[@class='dropdown']/div"));
        //driverWait.until(ExpectedConditions.visibilityOf(selectbutton));
        selectbutton.click();



    }

    public void selectOptionMenu(String taskName, String taskOption, String status){


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

        //driverWait.until(ExpectedConditions.elementToBeClickable(closeEditTaskButton));
        WebElement setHoursTask = driver.findElement(By.xpath("//span[contains(text(), '"+ estimatedOrActualHours +"')]/following-sibling::span"));
        setHoursTask.click();
        hoursValueInput.clear();
        hoursValueInput.sendKeys(hours);
        saveHoursValueButton.click();
        closeEditTaskButton.click();
        WebElement hoursDisplayed = driver.findElement(By.xpath("//div[@class='task-time']/span[contains(text(), '" + hours + "')]"));
        driverWait.until(ExpectedConditions.visibilityOf(hoursDisplayed));



    }

    public void setHoursStartedTask(String estimatedOrActualHours, String hours) {


        WebElement setHoursTask = driver.findElement(By.xpath("//span[contains(text(), '"+ estimatedOrActualHours +"')]/following-sibling::span"));
        System.out.print("//span[contains(text(), '"+ estimatedOrActualHours +"')]/following-sibling::span");
        setHoursTask.click();
        hoursValueInput.clear();
        hoursValueInput.sendKeys(hours);
        saveHoursValueButton.click();
        closeEditTaskButton.click();




    }


    public Boolean isHourSetDisplayed(String hours, String nameTask) {

         return UIMethods.waitElementIsPresent(10, By.xpath("//div[@class='task-time']/span[contains(text(), '"+ hours +"')]/../../following-sibling::div/div[@class='task-title' and contains(text(), '"+ nameTask +"')]"));

    }
}
