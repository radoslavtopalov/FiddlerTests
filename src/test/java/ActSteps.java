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
}
