import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class TestArrangeActAssert {

    private final String Username = "TestUser1000";
    private final String Password = "Fidd123456!";
    private static WebDriver driver;

    @Test
    public void Verify_SubscriptionsPaneFields_AreDisplayedCorrectly(){
        //Arrange
        driver.get("https://dashboard.getfiddler.com");
        driver.findElement(By.xpath("//div[@id=\"onetrust-banner-sdk\"]//button[@id=\"onetrust-accept-btn-handler\"]")).click();

        //Act
        LoginWithUsernameAndPassword(Username, Password);

        //Assert
        AssertSubscriptionPaneFielsAreDisplayedCorrectly();
    }

    private void LoginWithUsernameAndPassword(String username, String password) {
        driver.findElement(By.xpath("//div[@class=\"amplify-component\"]//button//span[contains(text(),'Sign')]"))
                .click();
        driver.findElement(By.xpath("//div[@class=\"amplify-container ng-star-inserted\"]//input[@id=\"usernameField\"]"))
                .sendKeys(username);
        driver.findElement(By.xpath("//div[@class=\"amplify-container ng-star-inserted\"]//input[@id=\"passwordField\"]"))
                .sendKeys(password);
        driver.findElement(By.xpath("//div[@class=\"amplify-container ng-star-inserted\"]//button[@class=\"amplify-form-button sign-in-button\"]"))
                .click();
    }

    private void AssertSubscriptionPaneFielsAreDisplayedCorrectly() {
        String subscriptionsAssertMessage = " is expected to be displayed on Overview tab in Subscriptions pane.";

        WebElement title = driver.findElement(By.xpath("//fdl-card[@label=\"Subscriptions\"]//h4[contains(text(),'Subscriptions')]"));
        assertTrue(title.isDisplayed(), "Subscriptions title".concat(subscriptionsAssertMessage));
        assertThat(title.getText()).contains("Subscriptions");

        WebElement description = driver.findElement(By.xpath("//fdl-card[@label=\"Subscriptions\"]//p"));
        assertTrue(description.isDisplayed(), "Subscriptions description".concat(subscriptionsAssertMessage));
        assertThat(description.getText()).contains("Your recurring payments for subscription services.");

        String[] firstRowExpectedElements = {"Product", "Current Plan", "Total Seats", "Cost", ""};
        List<WebElement> firstRowElements = driver.findElements(By.xpath("//fdl-card[@label=\"Subscriptions\"]//table[@role=\"presentation\"]//th"));
        assertThat(firstRowElements).hasSize(firstRowExpectedElements.length);
        for (int i = 0; i < firstRowElements.stream().count(); i++){
            assertTrue(firstRowElements.get(i).isDisplayed(), firstRowExpectedElements[i].concat(subscriptionsAssertMessage));
            assertThat(firstRowElements.get(i).getText()).contains(firstRowExpectedElements[i]);
        }

        String[] secondRowExpectedElements = {"Fiddler Everywhere", "FREE", "1", "-", "Manage Subscription"};
        List<WebElement> secondRowElements = driver.findElements(By.xpath("//fdl-card[@label=\"Subscriptions\"]//td"));
        assertThat(secondRowElements).hasSize(secondRowExpectedElements.length);
        for (int i = 0; i < firstRowElements.stream().count() - 1; i++){
            assertTrue(secondRowElements.get(i).isDisplayed(), secondRowExpectedElements[i].concat(subscriptionsAssertMessage));
            assertThat(secondRowElements.get(i).getText()).contains(secondRowExpectedElements[i]);
        }
        WebElement fiddlerImage = driver.findElement(By.xpath("//fdl-card[@label=\"Subscriptions\"]//a"));
        assertTrue(fiddlerImage.isDisplayed(), "Fiddler image".concat(subscriptionsAssertMessage));
    }

    @BeforeTest
    private void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\DevelpmentProgramFiles\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-extensions");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    private void tearDown() {
        driver.quit();
    }
}
