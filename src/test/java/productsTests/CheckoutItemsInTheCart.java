package productsTests;

import baseTestCase.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutItemsInTheCart extends TestUtil {
    private final static String BASE_PRODUCT_ID = "add-to-cart-sauce-labs-";

    @Test(dataProvider = "shoppingItems")
    public void checkoutWithItemsInTheCart(String item) {
        // Логване в системата
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.name("login-button"));
        loginBtn.click();

        // Добавяне на продукт в количката
        WebElement itemToBeAdded = driver.findElement(By.id(BASE_PRODUCT_ID + item));
        itemToBeAdded.click();

        // Навигация до количката
        WebElement shoppingCartLink = driver.findElement(By.cssSelector(".shopping_cart_link"));
        shoppingCartLink.click();

        // Клик на бутона за преминаване към чекаут
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        // Въвеждане на потребителска информация
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        firstNameInput.sendKeys("John");

        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        lastNameInput.sendKeys("Doe");

        WebElement postalCodeInput = driver.findElement(By.id("postal-code"));
        postalCodeInput.sendKeys("12345");

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        // Завършване на покупката
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        // Проверка, че покупката е успешна
        WebElement confirmationMessage = driver.findElement(By.className("complete-header"));
        Assert.assertTrue(confirmationMessage.isDisplayed(), "Checkout process was not completed successfully.");
        Assert.assertEquals(confirmationMessage.getText(), "THANK YOU FOR YOUR ORDER", "Order confirmation message does not match.");
    }

    @DataProvider(name = "shoppingItems")
    public Object[] getShoppingItems() {
        return new Object[]{
                "bike-light",
                "backpack",
                "fleece-jacket"
        };
    }
}
