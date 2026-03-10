package dtm;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(groups = { "smoke", "regression" })
    public void testOpenCheckoutStepOne() {
        loginStandardUser();
        addBackpackToCart();
        openCart();

        driver().findElement(By.id("checkout")).click();
        wait.until(driver -> driver.getCurrentUrl().contains("checkout-step-one.html"));

        Assert.assertTrue(driver().getCurrentUrl().contains("checkout-step-one.html"));
    }

    @Test(groups = { "regression" })
    public void testCompleteCheckout() {
        loginStandardUser();
        addBackpackToCart();
        openCart();

        driver().findElement(By.id("checkout")).click();
        wait.until(driver -> driver.getCurrentUrl().contains("checkout-step-one.html"));

        driver().findElement(By.id("first-name")).sendKeys("Dang");
        driver().findElement(By.id("last-name")).sendKeys("Khoa");
        driver().findElement(By.id("postal-code")).sendKeys("700000");
        driver().findElement(By.id("continue")).click();

        wait.until(driver -> driver.getCurrentUrl().contains("checkout-step-two.html"));
        driver().findElement(By.id("finish")).click();

        wait.until(driver -> driver.getCurrentUrl().contains("checkout-complete.html"));

        String actualText = driver().findElement(By.className("complete-header")).getText();
        Assert.assertEquals(actualText, "Thank you for your order!");
    }
}