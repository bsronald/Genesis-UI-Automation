package ui.pages;

import org.openqa.selenium.support.PageFactory;
import ui.BasePageObject;

/**
 * User: RonaldButron
 * Date: 11/17/15
 */
public class LeftReleasesMenu extends BasePageObject {


    @Override
    public void waitUntilPageObjectIsLoaded() {

    }

    public LeftReleasesMenu(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }
}
