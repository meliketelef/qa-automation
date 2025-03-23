package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()[normalize-space()='Company']]")
    private WebElement companyMenu;

    @FindBy(xpath = "//a[contains(@class, 'dropdown-sub') and contains(text(), 'Careers')]")
    private WebElement careersLink;

    public void goToHomepage() {
        driver.navigate().to("https://useinsider.com/");
    }

    public boolean isHomePageOpened() {
        return driver.getCurrentUrl().equals("https://useinsider.com/");
    }

    public void clickCompanyMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(companyMenu)).click();
    }

    public void clickCareersLink() {
        wait.until(ExpectedConditions.elementToBeClickable(careersLink)).click();
    }
}
