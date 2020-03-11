import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Arrays;
import java.util.Collection;

public class ParametersTest {
    private static final Logger logger = Logger.getLogger(ParametersTest.class);

    private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{BrowserType.FIREFOX}, {BrowserType.GOOGLECHROME}});
    }

    private void getDriverByBrowserType(String browserType) {
        if (BrowserType.FIREFOX.equals(browserType)) {
            driver = new FirefoxDriver();
        } else if (BrowserType.GOOGLECHROME.equals(browserType)) {
            driver = new ChromeDriver();
        } else {
            System.out.println(browserType + " unsupported");
        }
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest(name = "Open otus from {0}")
    @MethodSource("data")
    public void test(String browserType) {
        logger.info("Test start on " + browserType);
        getDriverByBrowserType(browserType);
        String url = "https://otus.ru/";
        driver.get(url);

        Assertions.assertTrue(true, String.format("Переход по %s не осуществлен. Browser: %s", url, browserType));
    }
}