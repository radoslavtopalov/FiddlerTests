import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeneralHelper {

    public static boolean isClickable(WebElement webElement)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(DefaultBaseTest.driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
