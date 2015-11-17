package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * User: RonaldButron
 * Date: 11/17/15
 */
public class LeftMenu extends BasePageObject{

    LeftSprintMenu sprintsMenu = new LeftSprintMenu();
    LeftMembersMenu membersMenu = new LeftMembersMenu();
    LeftReleasesMenu releasesMenu = new LeftReleasesMenu();


    @FindBy(xpath = "//button[@type='button' and @content-type='sprints']")
    @CacheLookup
    WebElement sprintsButton;

    @FindBy(xpath = "//button[@type='button' and @content-type='releases']")
    @CacheLookup
    WebElement releasesButton;

    @FindBy(xpath = "//button[@type='button' and @content-type='members']")
    @CacheLookup
    WebElement membersButton;


    public LeftMenu(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
       driverWait.until(ExpectedConditions.visibilityOf(sprintsButton));
    }

    public LeftSprintMenu selectSprintPage(){

        sprintsButton.click();
        driverWait.until(ExpectedConditions.visibilityOf(sprintsButton));
        return new LeftSprintMenu();
    }

    public LeftReleasesMenu selectReleasesPage(){

        releasesButton.click();
        return new LeftReleasesMenu();
    }

    public  LeftMembersMenu selectMembersMenu(){

        membersButton.click();
        return new LeftMembersMenu();
    }


}
