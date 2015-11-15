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
        driverWait.until(ExpectedConditions.visibilityOf(submitButton));
    }

    public LoginPage(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    public LoginPage setUserName(String userName){
        userNameInput.clear();
        userNameInput.sendKeys(userName);
        return this;
    }

    public LoginPage setPassword(String userPassword){
        passwordInput.clear();
        passwordInput.sendKeys(userPassword);
        return  this;
    }

    private void login(String userName, String userPassword){
        setUserName(userName);
        setPassword(userPassword);
    }

    public LoginPage loginPageFailed(){
        submitButton.click();
        return this;
    }

    public LoginPage loginPageUnsuccessfully(String userName, String userPassword){
        login(userName, userPassword);
        return loginPageFailed();
    }

    public MainPage LoginSuccesfully(String userName, String userPassword){
        login(userName, userPassword);
        return  loginPageSuccessfully();
    }

    public MainPage loginPageSuccessfully(){
        submitButton.click();
        return new MainPage();
    }

    public Boolean submitButtonDisplayed(){

        return  submitButton.isDisplayed();
    }

    public Boolean userMessageError(){

        WebElement loginError = driver.findElement(By.xpath("//form[contains(text(),'" + DomainAppConstants.INVALID_USER_ERROR + "')]"));

        return loginError.isDisplayed();
    }
}
