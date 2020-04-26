import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MenuCategories {
    private WebDriver driver;

    private By allCategories = By.xpath("//button[@class='_35SYuInI1T _2njYUPL9xd'][.='Все категории']");

    private By electronics = By.xpath("//button[@class='_35SYuInI1T _2BRGNp7I5O'][.='Электроника']");

    private By mobilePhone = By.xpath("//div[.='Мобильные телефоны']");

    public MenuCategories(WebDriver driver) {
        this.driver = driver;
    }

    public void openSectionMobilePhone() {
        clickOnElement(allCategories);
        hoverToElectronics();
        clickOnElement(mobilePhone);
    }

    private void clickOnElement(By categories) {
        driver.findElement(categories)
                .click();
    }

    private void hoverToElectronics() {
        Actions builder = new Actions(driver);
        WebElement section = driver.findElement(electronics);
        Actions hoverOverWebElement = builder.moveToElement(section);
        hoverOverWebElement.perform();
    }
}
