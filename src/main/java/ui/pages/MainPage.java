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

    /**
     *
     * @return
     */

    public MainPage clickNewProject(){

        newProjectLink.click();
        return this;
    }

    /**
     *
     * @param projectName
     * @return
     */
    public MainPage createNewProject(String projectName){

        return successfullyProjectCreated(projectName);
    }

    /**
     *
     * @param projectName
     */
    public void newProject(String projectName){
        clickNewProject();
        driverWait.until(ExpectedConditions.visibilityOf(savePrjButton));
        setProjectName(projectName);

    }

    /**
     *
     * @param projectName
     */
    public void setProjectName(String projectName) {
        insertPrjName.clear();
        insertPrjName.sendKeys(projectName);
    }

    /**
     *
     * @param finishDate
     */
    public void setFinishDate(String finishDate) {
        insertEndDatePrj.clear();
        insertEndDatePrj.sendKeys(finishDate);
    }

    /**
     *
     * @param startDate
     */
    public void setStartDate(String startDate) {

        insertStartDatePrj.clear();
        insertStartDatePrj.sendKeys(startDate);
    }

    /**
     *
     * @param nameProject
     */
    public void saveProjectButton(String nameProject){

        savePrjButton.click();

        WebElement prj = driver.findElement(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '"+ nameProject +"')]"));
        driverWait.until(ExpectedConditions.visibilityOf(prj));
        driverWait.until(ExpectedConditions.visibilityOf(newSprintLink));

    }

    /**
     *
     * @param projectName
     * @return
     */
    private MainPage successfullyProjectCreated(String projectName) {

        newProject(projectName);

        return this;
    }

    /**
     *
     * @return
     */
    public LoginPage logOut(){

        logOutPage.click();
        return new LoginPage();
    }

    /**
     *
     * @param namePrj
     * @return
     */
    public Boolean prjCreatedIsDisplayed(String namePrj){

        WebElement newPrj = driver.findElement(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '"+ namePrj +"')]"));
        return  newPrj.isDisplayed();
    }

    /**
     *
     * @param namePrj
     * @return
     */
    public Boolean prjIsNotDisplayed(String  namePrj){
       // Insert Waits ?????
       //return UIMethods.isElementPresent(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '" + namePrj + "')]"));
        return UIMethods.waitElementIsRemoved(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '" + namePrj + "')]"));
    }

    /**
     *
     * @param namePrj
     */
    public void selectPrjDropDownButton(String namePrj){

        WebElement editPrj = driver.findElement(By.xpath("//a[contains(text(), '"+ namePrj +"')]/../div/div/span"));
        driverWait.until(ExpectedConditions.elementToBeClickable(editPrj));
        editPrj.click();
    }

    /**
     *
     * @param namePrj
     */
    public void selectProject(String namePrj){

        WebElement selectPrj = driver.findElement(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '" + namePrj + "')]"));
        selectPrj.click();
    }

    /**
     *
     * @param nameProject
     */
    public void selectEditPrjButton(String nameProject){

        WebElement editPrj = driver.findElement(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '"+ nameProject +"')]/../div/div/ul/li/a[@class='project-settings-link']"));
        editPrj.click();
    }

    /**
     *
     */
    public void selectArchiveProject(){

        selectArchiveProject.click();
    }

    /**
     *
     * @param nameProject
     */
    private void successfullyDeletePrj(String nameProject){

        WebElement prj = driver.findElement(By.xpath("//ul[@id='projects-list']/li/a[contains(text(), '"+ nameProject +"')]"));
        driverWait.until(ExpectedConditions.visibilityOf(prj));
        driverWait.until(ExpectedConditions.visibilityOf(newSprintLink));
        selectPrjDropDownButton(nameProject);
        selectEditPrjButton(nameProject);
        selectArchiveProject();
        saveEditPrj();

    }

    /**
     *
     */
    public void saveEditPrj(){

        WebElement saveEdit = driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']/button"));
        driverWait.until(ExpectedConditions.elementToBeClickable(saveEdit));
        saveEdit.click();
        driverWait.until(ExpectedConditions.visibilityOf(newSprintLink));

    }

    /**
     *
     * @param nameProject
     */
    public void deletePrj(String nameProject){

         successfullyDeletePrj(nameProject);
    }

    /**
     *
     * @return
     */
    public Boolean welcomeProjectIsDisplayed(){

        return  welcomeProjectLink.isDisplayed();
    }
}