package testsPOM;

import baseTestCase.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesPOM.LoginPage;
import pagesPOM.ProductPage;

public class LoginTests extends TestUtil {

    @Test
    public void successfulLogin(){
        //обект за взаимодействие с елементите
        LoginPage loginPage = new LoginPage(driver);
        // Логваме се със стандартен потребител и парола
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        // Проверяваме дали след успешен вход сме на правилната страница - страницата с продукти
        Assert.assertTrue(productPage.isAt());
    }

    @Test
    public void unSuccessfulLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_userd", "secret_sauce");

        Assert.assertTrue(loginPage.isAt());
    }
}