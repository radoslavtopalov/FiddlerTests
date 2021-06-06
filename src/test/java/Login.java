import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class Login{

    @When("Login with Username: {string} and Password: {string}")
    public void login_with_username_and_password(String username, String password) {
        DefaultBaseTest.driver.findElement(By.xpath("//div[@class=\"amplify-component\"]//button//span[contains(text(),'Sign')]"))
                .click();
        DefaultBaseTest.driver.findElement(By.xpath("//div[@class=\"amplify-container ng-star-inserted\"]//input[@id=\"usernameField\"]"))
                .sendKeys(username);
        DefaultBaseTest.driver.findElement(By.xpath("//div[@class=\"amplify-container ng-star-inserted\"]//input[@id=\"passwordField\"]"))
                .sendKeys(password);
        DefaultBaseTest.driver.findElement(By.xpath("//div[@class=\"amplify-container ng-star-inserted\"]//button[@class=\"amplify-form-button sign-in-button\"]"))
                .click();
    }
}
