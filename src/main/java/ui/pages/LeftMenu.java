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

    @FindBy(xpath = "//button[@type='button' and @content-type='sprints']")
    @CacheLookup
    WebElement sprintsButton;

    @FindBy(xpath = "//button[@type='button' and @content-type='releases']")
    @CacheLookup
    WebElement releasesButton;

    @FindBy(xpath = "//button[@type='button' and @content-type='members']")
    @CacheLookup
    WebElement membersButton;

    @FindBy(xpath = "//div[@id='sprints-add-link']/span")
    @CacheLookup
    WebElement newSprintLink;

    @FindBy(xpath = "//div[@id='release-add-link']/span")
    @CacheLookup
    WebElement newReleaseLink;

    @FindBy(xpath = "//div[@id='member-add-link']/span")
    @CacheLookup
    WebElement newMemberLink;


    public LeftMenu(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
       driverWait.until(ExpectedConditions.visibilityOf(newSprintLink));
    }

    /**
     * This method select the button Sprint
     * @return a new page sprint
     */
    public LeftSprintMenu selectSprintPage(){

        sprintsButton.click();
        return new LeftSprintMenu();
    }

    /**
     * This method select the button Release
     * @return a new page Release
     */
    public LeftReleasesMenu selectReleasesPage(){

        releasesButton.click();
        driverWait.until(ExpectedConditions.visibilityOf(newReleaseLink));
        return new LeftReleasesMenu();
    }

    /**
     * This method select the button Members
     * @return a new page Members
     */
    public LeftMembersMenu selectMembersMenu(){

        membersButton.click();
        driverWait.until(ExpectedConditions.visibilityOf(newMemberLink));
        return new LeftMembersMenu();
    }


}
