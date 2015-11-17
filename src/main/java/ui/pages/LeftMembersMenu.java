package ui.pages;

import org.openqa.selenium.support.PageFactory;
import ui.BasePageObject;

/**
 * User: RonaldButron
 * Date: 11/17/15
 */
public class LeftMembersMenu extends BasePageObject {

    @Override
    public void waitUntilPageObjectIsLoaded() {

    }

    public LeftMembersMenu(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }
}
