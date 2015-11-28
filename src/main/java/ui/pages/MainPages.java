package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * User: RonaldButron
 * Date: 11/24/15
 */
public class MainPages extends BasePageObject {

    TopMenu topMenu = new TopMenu();
    MainBoards mainBoards = new MainBoards();
    LeftMenu leftMenu = new LeftMenu();


    @FindBy(xpath = "//div[@class='board-header']/div/span[contains(text(), 'Bugs')]")
    @CacheLookup
    WebElement ideaBoardDisplayed;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        driverWait.until(ExpectedConditions.visibilityOf(ideaBoardDisplayed));
    }

    public MainPages(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    /**
     * This method return a Top Menu page
     * @return a new page top Menu
     */

   public TopMenu getTopMenu(){

        return topMenu;
    }

    /**
     * This method return a Main Boar page
     * @return a new Main Boar Menu
     */
    public MainBoards getMainBoards(){

        return mainBoards;
    }

    /**
     * This method return a Left Menu page
     * @return a new page Left Menu
     */
    public LeftMenu leftMenu(){

        return leftMenu;
    }


}
