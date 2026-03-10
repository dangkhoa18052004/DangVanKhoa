package dtm;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    // Locator
    By usernameInput = By.id("user-name");
    By passwordInput = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.cssSelector("h3[data-test='error']");
    By inventoryContainer = By.id("inventory_container");

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
    }

    public void login(String username, String password) {
        driver.findElement(usernameInput).clear();
        driver.findElement(passwordInput).clear();

        if (username != null && !username.isEmpty()) {
            driver.findElement(usernameInput).sendKeys(username);
        }

        if (password != null && !password.isEmpty()) {
            driver.findElement(passwordInput).sendKeys(password);
        }

        driver.findElement(loginButton).click();
    }

    @Test(description = "Dang nhap thanh cong voi tai khoan hop le")
    public void testLoginSuccess() {
        login("standard_user", "secret_sauce");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("inventory.html"),
                ExpectedConditions.visibilityOfElementLocated(inventoryContainer)));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("inventory.html"),
                "Dang nhap thanh cong nhung khong chuyen sang trang inventory!");
    }

    @Test(description = "Dang nhap sai mat khau")
    public void testLoginWrongPassword() {
        login("standard_user", "wrong_password");

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage));

        String actualError = error.getText();
        Assert.assertTrue(
                actualError.contains("Username and password do not match any user in this service"),
                "Thong bao loi sai mat khau khong dung! Thuc te: " + actualError);
    }

    @Test(description = "Bo trong username")
    public void testLoginEmptyUsername() {
        login("", "secret_sauce");

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage));

        String actualError = error.getText();
        Assert.assertEquals(
                actualError,
                "Epic sadface: Username is required",
                "Thong bao khi bo trong username khong dung!");
    }

    @Test(description = "Bo trong password")
    public void testLoginEmptyPassword() {
        login("standard_user", "");

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage));

        String actualError = error.getText();
        Assert.assertEquals(
                actualError,
                "Epic sadface: Password is required",
                "Thong bao khi bo trong password khong dung!");
    }

    @Test(description = "Dang nhap voi tai khoan bi khoa")
    public void testLoginLockedUser() {
        login("locked_out_user", "secret_sauce");

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage));

        String actualError = error.getText();
        Assert.assertTrue(
                actualError.contains("Sorry, this user has been locked out"),
                "Thong bao tai khoan bi khoa khong dung! Thuc te: " + actualError);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        if (driver != null) {
            driver.quit();
        }
    }
}