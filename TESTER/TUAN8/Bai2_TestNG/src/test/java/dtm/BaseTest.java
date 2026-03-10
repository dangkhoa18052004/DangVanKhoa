package dtm;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected String baseUrl;
    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    @Parameters({ "browser", "baseUrl" })
    public void setUp(@Optional("chrome") String browser,
            @Optional("https://www.saucedemo.com") String baseUrl) {
        this.baseUrl = baseUrl;
        DriverFactory.initDriver(browser);
        driver().get(baseUrl);
        wait = new WebDriverWait(driver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        DriverFactory.quitDriver();
    }

    protected WebDriver driver() {
        return DriverFactory.getDriver();
    }

    protected void login(String username, String password) {
        driver().findElement(By.id("user-name")).clear();
        driver().findElement(By.id("password")).clear();

        if (username != null && !username.isEmpty()) {
            driver().findElement(By.id("user-name")).sendKeys(username);
        }

        if (password != null && !password.isEmpty()) {
            driver().findElement(By.id("password")).sendKeys(password);
        }

        driver().findElement(By.id("login-button")).click();
    }

    protected void loginStandardUser() {
        login("standard_user", "secret_sauce");
        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }

    protected void addBackpackToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("add-to-cart-sauce-labs-backpack")));
        driver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    protected void openCart() {
        driver().findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }
}