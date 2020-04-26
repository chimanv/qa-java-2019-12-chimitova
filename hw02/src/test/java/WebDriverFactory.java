import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    public static WebDriver create(String webDriverName){
        Browser browser = getBrowser(webDriverName);
        return browser.getDriver();
    }

    public static WebDriver create(String webDriverName, Capabilities options) {
        Browser browser = getBrowser(webDriverName);
        return browser.getDriver(options);
    }

    private static Browser getBrowser(String webDriverName) {
        return Browser.valueOf(webDriverName.toUpperCase());
    }
}
