package productsTests;
import baseTestCase.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

    public class AddItemInTheCart extends TestUtil {
        // Константа за базовия идентификатор на продуктите, използвана за динамично намиране на елементи
        private final static String BASE_PRODUCT_ID = "add-to-cart-sauce-labs-";

        @Test(dataProvider = "shoppingItems")
        public void addProductToTheCart(String item){
            //въвеждане на потребителско име и въвеждане на стандартен потребител
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

// Намиране на продукт, който ще се добави като се използва идентификатор базиран на продукта
            WebElement itemToBeAdded = driver.findElement(By.id(BASE_PRODUCT_ID + item));
            itemToBeAdded.click();

            WebElement shoppingCartBadge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
//различен локатор

            // Проверка дали количеството на продуктите в количката е 1 тъй като се добавя един елемент
            Assert.assertEquals(shoppingCartBadge.getText(), "1", "Since we`ve added one element");
        }
//добавените продукти
        @DataProvider(name = "shoppingItems")
        public Object[] getShoppingItems(){
            return new Object[] {
                    "bike-light",
                    "backpack",
                    "fleece-jacket"
            };
        }
    }

