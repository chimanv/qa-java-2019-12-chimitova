import io.github.bonigarcia.wdm.DriverManagerType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.lang.reflect.Constructor;


public enum Browser {
    CHROME(DriverManagerType.CHROME),
    FIREFOX(DriverManagerType.FIREFOX);

    private DriverManagerType driverManagerType;

    Browser(DriverManagerType driverManagerType) {
        this.driverManagerType = driverManagerType;
    }

    public WebDriver getDriver() {
        setupWebDriverManager();

        try {
            Class<?> driverClass = Class.forName(driverManagerType.browserClass());
            return (WebDriver) driverClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }

    public WebDriver getDriver(Capabilities options){
        setupWebDriverManager();

        try {
            Class<?> driverClass = Class.forName(driverManagerType.browserClass());
            Constructor<?> ctor = driverClass.getConstructor(Capabilities.class);
            return (WebDriver) ctor.newInstance(new Object[] { options });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setupWebDriverManager() {
        WebDriverManager webDriverManager = WebDriverManager.getInstance(driverManagerType); //5 Берем driverManagerType из поля 3
        webDriverManager.setup();
    }
}
