import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Assertions{

    /**
     * Asserts Subscriptions pane fields are displayed correctly on Overview tab.
     */
    @Then("Verify Subscriptions pane fields are displayed correctly")
    public void verify_subscriptions_pane_fields_are_displayed_correctly() {
        String subscriptionsAssertMessage = " is expected to be displayed on Overview tab in Subscriptions pane.";

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
        WebElement fiddlerImage = DefaultBaseTest.driver.findElement(By.xpath("//fdl-card[@label=\"Subscriptions\"]//a"));
        assertTrue(fiddlerImage.isDisplayed(), "Fiddler image".concat(subscriptionsAssertMessage));
    }

    @Then("Verify subscription page is opened")
    public void verify_subscription_page_is_opened() {
        WebDriverWait wait = new WebDriverWait(DefaultBaseTest.driver, 5);

        boolean isURLCorrect = wait.until(ExpectedConditions.urlContains("/subscription"));
        assertTrue(isURLCorrect, "Subscription page URL is expected to ends with /subscription .");

        String subscriptionPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"view-subscription\"]//h2"))).getText();
        assertThat(subscriptionPageTitle).contains("Fiddler Everywhere Subscription");
    }

    @Then("Verify ‘Saved Cards’ tab is opened")
    public void verify_saved_cards_tab_is_opened() {
        WebDriverWait wait = new WebDriverWait(DefaultBaseTest.driver, 5);

        boolean isURLCorrect = wait.until(ExpectedConditions.urlContains("/cards"));
        assertTrue(isURLCorrect, "Save Cards tab URL is expected to ends with /subscription .");

        WebElement saveCardsButton = DefaultBaseTest.driver.findElement(By.xpath("//ul[@role=\"tablist\"]//a[@href=\"/cards\"]//ancestor::li"));
        Boolean isSaveCardsOpen = Boolean.parseBoolean(saveCardsButton.getAttribute("aria-selected"));
        assertTrue(isSaveCardsOpen, "Save Cards tab is expected to be open.");
    }

    @Then("Verify ‘Process Order’ page is opened and annual payment is selected by default")
    public void verify_page_is_opened_and_annual_payment_is_selected_by_default() {
        WebDriverWait wait = new WebDriverWait(DefaultBaseTest.driver, 5);

        String processOrderPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"k-hbox title-and-button\"]//h2"))).getText();
        assertThat(processOrderPageTitle).contains("Process Order");

        WebElement payAnnuallyButton = DefaultBaseTest.driver.findElement(
                By.xpath("//div[@class=\"choose-plan k-vbox\"]//div[contains(text(),'Pay Annually')]//ancestor::div[@class=\"period-radio-btn selected-plan\"]"));
        String payAnnuallyButtonAttribute = payAnnuallyButton.getAttribute("class");
        assertThat(payAnnuallyButtonAttribute).doesNotContain("unselected");
    }

    @Then("Upgrade with Monthly subscription with Seats: {int} and verify Total Charge is correct")
    public void upgrade_with_monthly_subscription_with_seats_and_verify_total_charge_is_correct(int seatsNumber) {
        WebElement payMonthlyButton = DefaultBaseTest.driver.findElement(
                By.xpath("//div[@class=\"choose-plan k-vbox\"]//div[contains(text(),'Pay Monthly')]//ancestor::div[@class=\"period-radio-btn unselected-plan\"]"));
        payMonthlyButton.click();

        WebElement seatsInputField = DefaultBaseTest.driver.findElement(By.xpath("//div[@class=\"choose-plan k-vbox\"]//input[@role=\"spinbutton\"]"));
        seatsInputField.clear();
        seatsInputField.sendKeys(String.valueOf(seatsNumber));

        double subscriptionPriceOnSubscriptionDetails = ActSteps.get_subscription_price_per_period_on_process_order_page_on_subscription_details_step();
        subscriptionPriceOnSubscriptionDetails = subscriptionPriceOnSubscriptionDetails * seatsNumber;

        DefaultBaseTest.driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

        String totalCharge = DefaultBaseTest.driver.findElement(By.cssSelector("div.k-vbox.payment-details > div.k-hbox >h3 > span > span")).getText();
        double totalChargeDouble = Double.parseDouble(totalCharge.replace('$', ' '));

        assertEquals(totalChargeDouble, subscriptionPriceOnSubscriptionDetails, "Total Charge is expected to be calculated correctly.");
    }

    @Then("Verify the card details form is displayed")
    public void verify_the_card_details_form_is_displayed() {
        WebElement paymentDetailsForm = DefaultBaseTest.driver.findElement(By.xpath("//div[@class=\"k-vbox details-container\"]"));
        assertTrue(paymentDetailsForm.isDisplayed(), "Payment Details Form is expected to be displayed.");
    }
}
