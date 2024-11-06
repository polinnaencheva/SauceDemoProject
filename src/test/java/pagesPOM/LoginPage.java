package pagesPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    //Локатори
    @FindBy(id = "user-name")
    WebElement userNameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    //Конструктор
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //проверява дали сме на страницата за логване като се уверява, че бутонът за логване е видим
    @Override
    public boolean isAt() {
        return loginBtn.isDisplayed();
    }

    public ProductPage login(String username, String password){
          // Кликва на полето за потребителско име, изчистване се предишния текст и въвеждаме наново потребителско име
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();

        return new ProductPage(driver);
    }
}