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

    public void createNewSprint(String sprintName) {

        driverWait.until(ExpectedConditions.visibilityOf(newSprintLink));
        nameSprint = sprintName;
        addNewSPrint();
        setNameSprint(sprintName);

    }

    public void addNewSPrint(){

        newSprintLink.click();
        driverWait.until(ExpectedConditions.visibilityOf(saveSprintButton));
    }

    public void setNameSprint(String nameSprint){

        inserSprintName.clear();
        inserSprintName.sendKeys(nameSprint);
    }

    public void setCapacityInHours(String hours){

        insertCapacityHours.clear();
        insertCapacityHours.sendKeys(hours);

    }

    public void setStartDate(String startDate){

        insertSprintStartDate.clear();
        insertSprintStartDate.sendKeys(startDate);

    }

    public void setFinishDate(String finishDate){

        insertSprintEndDate.clear();
        insertSprintEndDate.sendKeys(finishDate);

    }

    public void saveNewSprint(){

        saveSprintButton.click();


    }

    public Boolean sprintIsDisplayed(String sprintName){

       return UIMethods.isElementPresent(By.xpath(buildXpathSprintHeader(sprintName)));
    }

    public SprintBoard selectSprint(String sprintName){

        WebElement sprint = driver.findElement(By.xpath(buildXpathSprintHeader(sprintName)));
        sprint.click();

        return new SprintBoard();
    }


    public void dragAndDropTask(String taskName, String sprintName){

        WebElement selectedTask = driver.findElement(By.xpath("//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]"));
        WebElement target = driver.findElement(By.xpath(buildXpathSprintHeader(sprintName)));
        Actions builder= new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(selectedTask)
                                    .moveToElement(target)
                                    .release(target)
                                    .build();
        dragAndDrop.perform();
       // driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]")));
         UIMethods.waitElementIsNotPresent(5, By.xpath("//span[@class='board-task-title' and contains(text(), '"+ taskName +"')]"));
    }

    public Boolean isDisplayedTaskInSprint(String sprintName, String taskName, String taskBoard){


        return  UIMethods.waitElementIsPresent(10, By.xpath("//div[@class='sprint-task sprint-droppable "+ taskBoard +"']/div/div[@class='task-title' and contains(text(), '"+ taskName +"')]"));
    }

    public String buildXpathSprintHeader(String sprintName){

        return "//div[@class='dashboard-sprint-header']/div[contains(text(), '"+ sprintName +"')]";
    }

    public void createSuccessfullySprint(String sprintName, String startDate, String endDate, String capacityHours){

          createNewSprint(sprintName);
          setCapacityInHours(capacityHours);
          setStartDate(startDate);
          setFinishDate(endDate);
          saveNewSprint();

    }
}
