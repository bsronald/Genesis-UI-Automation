package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.After;
import ui.pages.LoginPage;
import ui.PageTransporter;
import ui.pages.MainPage;
import org.testng.Assert;


/**
 * Created by Ronald Butron on 11/9/2015.
 */
public class Login {

    public LoginPage loginPage;
    public MainPage mainPage;

    @Given("^I navigate to Login Page$")
    public void navigateLoginPage(){

        loginPage = PageTransporter.getInstance().toLoginPage();

    }

    @When("^I log in successfully as \"(.*?)\" with password \"(.*?)\"$")
    public void loginSuccessfullyAs(String userName, String userPassword){

        mainPage = loginPage.LoginSuccesfully(userName, userPassword);
    }

    @Then("^The main page is displayed$")
    public void mainPage(){

        Assert.assertTrue(mainPage.welcomeProjectIsDisplayed(), "Welcome Project is Displayed");
    }

    @When("^I log in unsuccessfully as \"(.*?)\" with password \"(.*?)\"$")
    public  void loginUnsuccessfullyAs(String userName, String userPassword){

        loginPage.loginPageUnsuccessfully(userName, userPassword);
    }

    @Then("^An error message is Displayed$")
    public void loginFailed(){
         Assert.assertTrue(loginPage.userMessageError(), "User Login Failed");
    }

    @After(value="@smokeTest", order = 999)
    public void LogOut(){
        mainPage.logOut();
    }





}
