package steps;

import common.CommonMethods;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.After;
import framework.DriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ui.pages.LoginPage;
import ui.PageTransporter;
import org.testng.Assert;
import ui.pages.MainPages;


/**
 * Created by Ronald Butron on 11/9/2015.
 */
public class Login {

    public LoginPage loginPage;
    public MainPages mainPages;


    @Given("^I navigate to Login Page$")
    public void navigateLoginPage(){

    }

    @When("^I log in successfully as \"(.*?)\" with password \"(.*?)\"$")
    public void loginSuccessfullyAs(String userName, String userPassword){
        PageTransporter.getInstance();

        if(CommonMethods.isItInTheLoginPage()){

             loginPage = new LoginPage();
             mainPages = loginPage.LoginSuccessfully(userName, userPassword);

        } else {

             mainPages = new MainPages();
        }


    }

    @Then("^The main page is displayed$")
    public void mainPage(){

        Assert.assertTrue(mainPages.getTopMenu().welcomeProjectIsDisplayed(), "Welcome Project is Displayed");
    }

    @When("^I log in unsuccessfully as \"(.*?)\" with password \"(.*?)\"$")
    public  void loginUnsuccessfullyAs(String userName, String userPassword){
        PageTransporter.getInstance();
        if(CommonMethods.isItInTheLoginPage()){

            loginPage = new LoginPage();
            loginPage.loginPageUnsuccessfully(userName, userPassword);
        } else {

            mainPages = new MainPages();
            mainPages.getTopMenu().logOut();
            loginPage.loginPageUnsuccessfully(userName, userPassword);
        }
    }

    @Then("^An error message is Displayed$")
    public void loginFailed(){
         Assert.assertTrue(loginPage.userMessageError(), "User Login Failed");
    }

    @After(value="@LogOut", order = 998)
    public void LogOut(){
        mainPages.getTopMenu().logOut();
    }

    @After(value = "@Refresh")
    public void refresh(){
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        driver.navigate().refresh();
    }





}
