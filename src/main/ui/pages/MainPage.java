package ui.pages;

import commons.DomainAppMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

import java.util.concurrent.TimeUnit;

/**
 * User: RonaldButron
 * Date: 11/11/15
 */
public class MainPage extends BasePageObject{

    @FindBy(id = "project-add-link")
    @CacheLookup
    WebElement newProjectLink;

    @FindBy(css = "span.logout-button")
    @CacheLookup
    WebElement logOutPage;

    @FindBy(linkText = "Welcome Project")
    @CacheLookup
    WebElement welcomeProjectLink;

    @FindBy(xpath = "//button[@type='button' and @content-type='sprints']")
    @CacheLookup
    WebElement sprintsButton;

    @FindBy(name = "name")
    @CacheLookup
    WebElement insertPrjName;

    @FindBy(id = "project-start-date")
    @CacheLookup
    WebElement insertStartDatePrj;

    @FindBy(id = "project-end-date")
    @CacheLookup
    WebElement insertEndDatePrj;

    @FindBy(id = "archive")
    @CacheLookup
    WebElement selectArchiveProject;

    @FindBy(xpath = "//button[@type='button' and contains(text(), 'Save')]")
    @CacheLookup
    WebElement savePrjButton;

    @FindBy(xpath = "//div[@id='sprints-add-link']/span")
    @CacheLookup
    WebElement newSprintLink;

    @FindBy(xpath = "//div[@class='board-header']/div/span[contains(text(), 'Bugs')]")
    @CacheLookup
    WebElement ideaBoardDisplayed;

    @Override
    public void waitUntilPageObjectIsLoaded() {
      driverWait.until(ExpectedConditions.visibilityOf(ideaBoardDisplayed));
    }

    public MainPage(){
       PageFactory.initElements(driver, this);
       waitUntilPageObjectIsLoaded();
    }

    //New Project Methods

    public MainPage clickNewProject(){

        newProjectLink.click();
        return this;
    }

    public MainPage createNewProject(String projectName){

        return successfullyProjectCreated(projectName);
    }

    public void newProject(String projectName){
        clickNewProject();
        driverWait.until(ExpectedConditions.visibilityOf(savePrjButton));
        setProjectName(projectName);

    }
    public void setProjectName(String projectName) {
        insertPrjName.clear();
        insertPrjName.sendKeys(projectName);
    }
    public void setFinishDate(String finishDate) {
        insertStartDatePrj.clear();
        insertStartDatePrj.sendKeys(finishDate);
    }
    public void setStartDate(String startDate) {
        insertEndDatePrj.clear();
        insertEndDatePrj.sendKeys(startDate);
    }

    public void saveProjectButton(String nameProject){

        savePrjButton.click();

        WebElement prj = driver.findElement(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '"+ nameProject +"')]"));
        driverWait.until(ExpectedConditions.visibilityOf(prj));
        // Editar @FIndBy Created
        WebElement newSprint = driver.findElement(By.xpath("//div[@id='sprints-add-link']/span"));
        driverWait.until(ExpectedConditions.visibilityOf(newSprint));

    }
    private MainPage successfullyProjectCreated(String projectName) {

        newProject(projectName);

        return this;
    }

    // LogOut Main Page

    public LoginPage logOut(){

        logOutPage.click();
        return new LoginPage();
    }

    // Verification New Project

    public Boolean prjCreatedDisplayed(String namePrj){

        WebElement newPrj = driver.findElement(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '"+ namePrj +"')]"));
        return  newPrj.isDisplayed();
    }

    public Boolean prjIsNotDisplayed(String  namePrj){
        // Insert Waits ?????
        return DomainAppMethods.isElementPresent(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '"+ namePrj +"')]"), driver);
    }

    //Edit Project
    public void selectPrjDropDownButton(String namePrj){

        WebElement editPrj = driver.findElement(By.xpath("//a[contains(text(), '"+ namePrj +"')]/../div/div/span"));
        driverWait.until(ExpectedConditions.elementToBeClickable(editPrj));
        editPrj.click();
    }

    public void selectProject(String namePrj){

        WebElement selectPrj = driver.findElement(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '" + namePrj + "')]"));
        selectPrj.click();
    }

    public void selectEditPrjButton(String nameProject){

        WebElement editPrj = driver.findElement(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '"+ nameProject +"')]/../div/div/ul/li/a[@class='project-settings-link']"));
        editPrj.click();
    }

    // Archive Project

    public void selectArchiveProject(){

        selectArchiveProject.click();
    }

    //Delete Project Methods

    private void successfullyDeletePrj(String nameProject){

        WebElement prj = driver.findElement(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '"+ nameProject +"')]"));
        driverWait.until(ExpectedConditions.visibilityOf(prj));
        // Editar @FIndBy Created
        WebElement newSprint = driver.findElement(By.xpath("//div[@id='sprints-add-link']/span"));
        driverWait.until(ExpectedConditions.visibilityOf(newSprint));

        selectPrjDropDownButton(nameProject);
        selectEditPrjButton(nameProject);
        selectArchiveProject();
        saveEditPrj();

    }

    public void saveEditPrj(){

        WebElement saveEdit = driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']/button"));
        saveEdit.click();
        // Editar @FIndBy Created
        WebElement newSprint = driver.findElement(By.xpath("//div[@id='sprints-add-link']/span"));
        driverWait.until(ExpectedConditions.visibilityOf(newSprint));
        //driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='sprints-add-link']/span")));
    }

    public void deletePrj(String nameProject){

         successfullyDeletePrj(nameProject);
    }

    public Boolean welcomeProjectIsDisplayed(){

        return  welcomeProjectLink.isDisplayed();
    }
}
