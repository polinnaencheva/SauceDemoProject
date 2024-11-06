package firstLoginTest;

import baseTestCase.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UnSuccessfulLoginTest extends TestUtil {
    //метод за неуспешен логин

    @Test(dataProvider = "wrongUsers")
    public void unSuccessfulLogin(String username, String password){
        //driver.get("https://www.saucedemo.com/");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.name("login-button"));
        loginBtn.click();

        WebElement errorMsg = driver.findElement(By.xpath(".//*[text()='Epic sadface: Username and password do not match any user in this service']"));
        Assert.assertTrue(errorMsg.isDisplayed());
    }

// чете данните от CSV файл и ги подава на теста
    @DataProvider(name = "wrongUsers")
    public Object[][] readWrongUsers(){
        try {
            /// Създава CSVReader, който чете файла с невалидни потребители "wrongUsers.csv"
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongUsers.csv"));

            // Чете всички редове от CSV файла и ги запазва като списък от масиви
            List<String[]> csvData = csvReader.readAll();

            // Създаваме двумерен масив, броят на редовете е като броя на записите, колоните са 2 за име и парола
            Object[][] csvResult = new Object[csvData.size()][2];

            for (int i = 0; i < csvData.size(); i++){
                csvResult[i] = csvData.get(i);
            }
            return csvResult;
        }catch (IOException e){
            //грешка с четенето на файла
            System.out.println(e.getMessage());
            return null;
        }catch (CsvException e){
            //грешка с обработването на данните
            System.out.println(e.getMessage());
            return null;
        }
    }
}

