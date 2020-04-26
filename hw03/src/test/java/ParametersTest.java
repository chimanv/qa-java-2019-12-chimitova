import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class ParametersTest {
    private static final Logger logger = Logger.getLogger(ParametersTest.class);

    private WebDriver driver;

    private MenuCategories filters;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        filters = new MenuCategories(driver);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        logger.info("Test start");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        openMarket();
        openSectionMobilePhone();
        logger.info("Открыт раздел 'Мобильные телефоны'");
    }

    private void openSectionMobilePhone() {
        filters.openSectionMobilePhone();
        Assertions.assertTrue(driver.getTitle().contains("Мобильные телефоны"),
                String.format("Open catalog with title: %s", driver.getTitle()));
    }

    private void openMarket() {
        String url = "https://market.yandex.ru/";
        driver.get(url);
        Assertions.assertEquals(url, driver.getCurrentUrl());
    }
}