package dtm;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(groups = { "smoke", "regression" })
    public void testAddToCart() {
        loginStandardUser();
        addBackpackToCart();

        String badge = driver().findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(badge, "1");
    }

    @Test(groups = { "regression" })
    public void testOpenCart() {
        loginStandardUser();
        addBackpackToCart();
        openCart();

        Assert.assertTrue(driver().getCurrentUrl().contains("cart.html"));
    }
}