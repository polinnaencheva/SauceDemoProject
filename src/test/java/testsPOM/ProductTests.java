package testsPOM;

import baseTestCase.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pagesPOM.LoginPage;
import pagesPOM.ProductPage;

public class ProductTests extends TestUtil {
    @Test
    public void addItemToTheCart(){
        // Създаване на обект от LoginPage за да може да влезем в системата
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        productPage.addItemToTheCart("bike-light");
        // Проверяваме дали продуктът е добавен успешно към количката като проверяваме броя на продуктите в количката
        Assert.assertEquals(productPage.getItemsInTheCart(), 1, "Since we`ve added just one item");
    }

    @Test
    public void addRemoveItemsToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        // Създаване на обект за SoftAssert който позволява изпълнение на няколко асърта без да спира теста
        SoftAssert softAssert = new SoftAssert();

        // Добавяме първия продукт в количката
        productPage.addItemToTheCart("bike-light");
        // Проверяваме дали е добавен успешно
        softAssert.assertEquals(productPage.getItemsInTheCart(), 1, "Since we`ve added just one item");

        productPage.addItemToTheCart("backpack");
        softAssert.assertEquals(productPage.getItemsInTheCart(), 2, "Since we`ve added one more item");

        productPage.removeItemFromTheCart("bike-light");
        softAssert.assertEquals(productPage.getItemsInTheCart(), 1, "Since we`ve removed one item");
//проверяваме всички асърти
        softAssert.assertAll();
    }
}