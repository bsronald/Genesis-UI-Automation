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
public class TopMenu extends BasePageObject{

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

    @FindBy(xpath = "//div[@class='ui-dialog-buttonset']/button")
    @CacheLookup
    WebElement saveEditButton;

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

    public TopMenu(){
       PageFactory.initElements(driver, this);
       waitUntilPageObjectIsLoaded();
    }

    /**
     *  This method click over new Project link
     * @return the same page
     */
    public TopMenu clickNewProject(){

        newProjectLink.click();
        return this;
    }

    /**
     *  This method create a new Project
     * @param projectName project name
     * @return the same page
     */
    public TopMenu createNewProject(String projectName){

        return successfullyProjectCreated(projectName);
    }

    /**
     * this method create a new project set the name
     * @param projectName project name
     */
    public void newProject(String projectName){

        clickNewProject();
        driverWait.until(ExpectedConditions.visibilityOf(savePrjButton));
        setProjectName(projectName);

    }

    /**
     * This method set the name of the project
     * @param projectName project name
     */
    public void setProjectName(String projectName) {
        insertPrjName.clear();
        insertPrjName.sendKeys(projectName);
    }

    /**
     * This method set the end date of the project
     * @param finishDate finish date
     */
    public void setFinishDate(String finishDate) {
        insertEndDatePrj.clear();
        insertEndDatePrj.sendKeys(finishDate);
    }

    /**
     * This method set the start date of the project
     * @param startDate start date
     */
    public void setStartDate(String startDate) {

        insertStartDatePrj.clear();
        insertStartDatePrj.sendKeys(startDate);
    }

    /**
     * This method click over the save project button
     * @param nameProject name project
     */
    public void saveProjectButton(String nameProject){

        savePrjButton.click();
        WebElement prj = driver.findElement(By.xpath(buildXpathForProject(nameProject)));
        driverWait.until(ExpectedConditions.visibilityOf(prj));
        driverWait.until(ExpectedConditions.visibilityOf(newSprintLink));
    }

    /**
     *  This method create a project successfully
     * @param projectName project name
     * @return the same page
     */
    private TopMenu successfullyProjectCreated(String projectName) {

        newProject(projectName);

        return this;
    }

    /**
     * This method log out of the web page
     * @return Login page
     */
    public LoginPage logOut(){

        driverWait.until(ExpectedConditions.elementToBeClickable(logOutPage));
        logOutPage.click();
        return new LoginPage();
    }

    /**
     * Verify if the project created is displayed
     * @param namePrj project name
     * @return true if the project is present and false if not
     */
    public Boolean prjCreatedIsDisplayed(String namePrj){

        return UIMethods.waitElementIsPresent(10, By.xpath(buildXpathForProject(namePrj)));

    }

    /**
     * This method verify if the project is not displayed
     * @param namePrj name project
     * @return return false
     */
    public Boolean prjIsNotDisplayed(String  namePrj){

        return UIMethods.waitElementIsNotPresent(5, By.xpath(buildXpathForProject(namePrj)));
    }

    /**
     * click over the project drop down button
     * @param namePrj name project
     */
    public void selectPrjDropDownButton(String namePrj){

        WebElement editPrj = driver.findElement(By.xpath("//a[contains(text(), '"+ namePrj +"')]/../div/div/span"));
        driverWait.until(ExpectedConditions.elementToBeClickable(editPrj));
        editPrj.click();
    }

    /**
     * Click over a specific project
     * @param namePrj name project
     */
    public void selectProject(String namePrj){

        WebElement selectPrj = driver.findElement(By.xpath(buildXpathForProject(namePrj)));
        selectPrj.click();
    }

    /**
     * click over a option of the drop down menu
     * @param nameProject
     */
    public void selectEditPrjButton(String nameProject){

        WebElement editPrj = driver.findElement(By.xpath(buildXpathForProject(nameProject) + "/../div/div/ul/li/a[@class='project-settings-link']"));
        editPrj.click();
    }

    /**
     * click over the archive checkbox
     */
    public void selectArchiveProject(){

        selectArchiveProject.click();
    }

    /**
     *  Delete a project successfully
     * @param nameProject
     */
    private void successfullyDeletePrj(String nameProject){

        selectPrjDropDownButton(nameProject);
        selectEditPrjButton(nameProject);
        selectArchiveProject();
        saveEditPrj(nameProject);

    }

    /**
     * Click over the save edited project button
     */
    public void saveEditPrj(String nameProject){

        driverWait.until(ExpectedConditions.elementToBeClickable(saveEditButton));
        saveEditButton.click();
        UIMethods.waitElementIsNotPresent(10, By.xpath(buildXpathForProject(nameProject)));
    }

    /**
     *  Delete project method
     * @param nameProject
     */
    public void deletePrj(String nameProject){

         successfullyDeletePrj(nameProject);
    }

    /**
     * verify welcome project displayed
     * @return
     */
    public Boolean welcomeProjectIsDisplayed(){

        return  welcomeProjectLink.isDisplayed();
    }

    /**
     * Build the a string path for a projects
     * @param nameProject name project
     * @return a string of the path builded
     */
    public String buildXpathForProject(String nameProject){

        return  "//ul[@id='projects-list']/li/a[contains(text(), '" + nameProject + "')]";
    }

    /**
     * Create successfully a project
     * @param nameProject name project
     * @param startDate star date
     * @param endDate end date
     */
    public void createSuccessfullyProject(String nameProject, String startDate, String endDate){

            clickNewProject();
            setProjectName(nameProject);
            setStartDate(startDate);
            setFinishDate(endDate);
            saveProjectButton(nameProject);
    }
}
