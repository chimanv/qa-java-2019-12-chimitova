import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;

public class WebDriverFactory {

    public static WebDriver create(String webDriverName) {
        WebDriver driver = null;

        if (isFirefox(webDriverName)) {
            driver = new FirefoxDriver();
        } else if (isChrome(webDriverName)) {
            driver = new ChromeDriver();
        } else {
            System.out.println(webDriverName + " unsupported");
        }
        return driver;
    }

    public static WebDriver create(String webDriverName, Object options) {
        WebDriver driver = null;

        if (isFirefox(webDriverName)) {
            driver = new FirefoxDriver((FirefoxOptions) options);
        } else if (isChrome(webDriverName)) {
            driver = new ChromeDriver((ChromeOptions) options);
        } else {
            System.out.println(webDriverName + " unsupported");
        }
        return driver;
    }

    private static boolean isFirefox(String webDriverName) {
        return BrowserType.FIREFOX.equals(webDriverName);
    }

    private static boolean isChrome(String webDriverName) {
        return BrowserType.GOOGLECHROME.equals(webDriverName) || BrowserType.CHROME.equals(webDriverName);
    }
}
