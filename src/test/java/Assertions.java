import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Assertions{

    /**
     * Asserts Subscriptions pane fields are displayed correctly on Overview tab.
     */
    @Then("Verify Subscriptions pane fields are displayed correctly")
    public void verify_subscriptions_pane_fields_are_displayed_correctly() {
        String  subscriptionsAssertMessage = " is expected to be displayed on Overview tab in Subscriptions pane.";

        WebElement title = DefaultBaseTest.driver.findElement(By.xpath("//fdl-card[@label=\"Subscriptions\"]//h4[contains(text(),'Subscriptions')]"));
        assertTrue(title.isDisplayed(), "Subscriptions title".concat(subscriptionsAssertMessage));
        assertThat(title.getText()).contains("Subscriptions");

        WebElement description = DefaultBaseTest.driver.findElement(By.xpath("//fdl-card[@label=\"Subscriptions\"]//p"));
        assertTrue(description.isDisplayed(), "Subscriptions description".concat(subscriptionsAssertMessage));
        assertThat(description.getText()).contains("Your recurring payments for subscription services.");

        String[] firstRowExpectedElements = {"Product", "Current Plan", "Total Seats", "Cost", ""};
        List<WebElement> firstRowElements = DefaultBaseTest.driver.findElements(By.xpath("//fdl-card[@label=\"Subscriptions\"]//table[@role=\"presentation\"]//th"));
        assertThat(firstRowElements).hasSize(firstRowExpectedElements.length);
        for (int i = 0; i < firstRowElements.stream().count(); i++){
            assertTrue(firstRowElements.get(i).isDisplayed(), firstRowExpectedElements[i].concat(subscriptionsAssertMessage));
            assertThat(firstRowElements.get(i).getText()).contains(firstRowExpectedElements[i]);
        }

        String[] secondRowExpectedElements = {"Fiddler Everywhere", "FREE", "1", "-", "Manage Subscription"};
        List<WebElement> secondRowElements = DefaultBaseTest.driver.findElements(By.xpath("//fdl-card[@label=\"Subscriptions\"]//td"));
        assertThat(secondRowElements).hasSize(secondRowExpectedElements.length);
        for (int i = 0; i < firstRowElements.stream().count() - 1; i++){
            assertTrue(secondRowElements.get(i).isDisplayed(), secondRowExpectedElements[i].concat(subscriptionsAssertMessage));
            assertThat(secondRowElements.get(i).getText()).contains(secondRowExpectedElements[i]);
        }
        WebElement manageSubscription = DefaultBaseTest.driver.findElement(By.xpath("//fdl-card[@label=\"Subscriptions\"]//a"));
        assertTrue(GeneralHelper.isClickable(manageSubscription), manageSubscription.getText().concat(" is expected to be clickable."));
        WebElement fiddlerImage = DefaultBaseTest.driver.findElement(By.xpath("//fdl-card[@label=\"Subscriptions\"]//a"));
        assertTrue(fiddlerImage.isDisplayed(), "Fiddler image".concat(subscriptionsAssertMessage));
    }
}
