package ui.pages;


import commons.DomainAppConstants;
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
public class LoginPage extends BasePageObject{



    @FindBy(id = "id_email")
    @CacheLookup
    WebElement userNameInput;

    @FindBy(id = "id_password")
    @CacheLookup
    WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    @CacheLookup
    WebElement submitButton;


    @Override
    public void waitUntilPageObjectIsLoaded() {

        System.out.print("Waiting for submit button\n");
        driverWait.until(ExpectedConditions.visibilityOf(submitButton));
    }

    public LoginPage(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    /**
     * This method insert the user name
     * @param userName user name to set
     * @return the same page
     */

    public LoginPage setUserName(String userName){
        userNameInput.clear();
        userNameInput.sendKeys(userName);
        return this;
    }

    /**
     * This method insert the password of the user
     * @param userPassword user password
     * @return the same page
     */

    public LoginPage setPassword(String userPassword){
        passwordInput.clear();
        passwordInput.sendKeys(userPassword);
        return  this;
    }

    /**
     * This method login to the page
     * @param userName user name
     * @param userPassword  user password
     *
     */
    private void login(String userName, String userPassword){
        setUserName(userName);
        setPassword(userPassword);
    }

    /**
     * This method submit the login but fail
     * @return the same page
     */

    public LoginPage loginPageFailed(){
        submitButton.click();
        return this;
    }

    /**
     * This method login successfully to the web page
     * @param userName user name
     * @param userPassword user password
     * @return the main page
     */

    public LoginPage loginPageUnsuccessfully(String userName, String userPassword){
        login(userName, userPassword);
        return loginPageFailed();
    }

    /**
     * This method login successfully to the web page
     * @param userName user name
     * @param userPassword user password
     * @return the main page
     */
    public MainPages LoginSuccessfully(String userName, String userPassword){

        return  loginPageSuccessfully(userName, userPassword);
    }

    /**
     * This method login to the main page
     * @return a new main page
     */
    public MainPages loginPageSuccessfully(String userName, String userPassword){

        login(userName, userPassword);
        submitButton.click();
        return  new MainPages();


    }

    /**
     * This method verify if the submit button is displayed
     * @return  true if is displayed and false if not
     */

    public Boolean submitButtonDisplayed(){

        return  submitButton.isDisplayed();
    }

    /**
     * Verify if the message error is displayed
     * @return true if the message is displayed and false if not is displayed
     */

    public Boolean userMessageError(){

        return UIMethods.waitElementIsPresent(5, By.xpath("//form[contains(text(),'" + DomainAppConstants.INVALID_USER_ERROR + "')]"));
    }
}
