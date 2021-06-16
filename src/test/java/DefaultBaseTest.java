import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class DefaultBaseTest {

    public static WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-extensions");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("Website is opened")
    public void website_is_opened() {
        driver.get("https://dashboard.getfiddler.com");
        driver.findElement(By.xpath("//div[@id=\"onetrust-banner-sdk\"]//button[@id=\"onetrust-accept-btn-handler\"]")).click();
    }
}
