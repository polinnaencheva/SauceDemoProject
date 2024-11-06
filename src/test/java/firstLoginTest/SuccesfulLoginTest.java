package firstLoginTest;

import baseTestCase.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class SuccesfulLoginTest extends TestUtil {

    @Test(dataProvider = "userList")
    public void successfulLogin(String username){
        //полето за потребителско име, кликваме и въвеждане на даденото име
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.name("login-button"));
        loginBtn.click();

        //Explicit Wait: Изчаква заглавието на страницата да се зареди за да се потвърди успешен логин
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

      // Намира елемента представляващ заглавието на продуктовата страница
        WebElement productsPageTitle = driver.findElement(By.className("title"));
        wait.until(ExpectedConditions.visibilityOf(productsPageTitle));/// Чакане до появата на заглавието
        Assert.assertTrue(productsPageTitle.isDisplayed(), "Login was not successful");
    }

    @DataProvider(name = "userList")
    public Object[] getUsers(){
        return new Object[] {
                "standard_user",
                "locked_out_user",
                "problem_user",
                "performance_glitch_user"
        };
    }

   }

