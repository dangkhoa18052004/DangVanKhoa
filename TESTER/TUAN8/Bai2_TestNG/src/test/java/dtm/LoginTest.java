package dtm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = { "smoke", "regression" })
    public void testLoginSuccess() {
        login("standard_user", "secret_sauce");
        wait.until(driver -> driver.getCurrentUrl().contains("inventory.html"));

        Assert.assertTrue(driver().getCurrentUrl().contains("inventory.html"));
    }

    @Test(groups = { "regression" })
    public void testLoginWrongPassword() {
        login("standard_user", "wrong_password");

        WebElement error = wait.until(driver -> driver.findElement(By.cssSelector("h3[data-test='error']")));

        Assert.assertTrue(error.getText()
                .contains("Username and password do not match any user in this service"));
    }
}