import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ActSteps {

    @And("Click on ‘Manage Subscription’")
    public void click_on_manage_subscription() {
        WebElement manageSubscriptionButton = DefaultBaseTest.driver.findElement(By.xpath("//fdl-card[@label=\"Subscriptions\"]//a"));
        manageSubscriptionButton.click();
    }

    @And("Click on the ‘ADD’ button")
    public void click_on_the_add_button() {
        WebElement addButton = DefaultBaseTest.driver.findElement(By.xpath("//div[@class=\"view-subscription\"]//a[@routerlink=\"/cards\"][contains(text(),'Add')]"));
        addButton.click();
    }

    @And("Click on ‘Upgrade to Pro now’ button")
    public void click_on_upgrade_to_pro_now_button() {
        WebElement upgradeToProNowButton = DefaultBaseTest.driver.findElement(By.xpath("//fdl-card[@ribbon=\"Recommended\"]//button[@title=\"UPGRADE TO PRO NOW\"]"));
        upgradeToProNowButton.click();
    }

    @And("Get subscription price per period on ‘Process Order’ page on ‘Subscription details’ step")
    public static double get_subscription_price_per_period_on_process_order_page_on_subscription_details_step() {
        String pricePerPeriod = DefaultBaseTest.driver.findElement(By.cssSelector("div.choose-plan.k-vbox > div.summary > div:nth-child(1) > span:nth-child(3)")).getText();
        double pricePerPeriodDouble = Double.parseDouble(pricePerPeriod.replace('$', ' '));

        return pricePerPeriodDouble;
    }
}
