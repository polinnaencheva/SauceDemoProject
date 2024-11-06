package pagesPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/// Класът ProductPage представлява страницата с продукти и следва Page Object Model подхода.
public class ProductPage extends BasePage{
    private final static String BASE_PRODUCT_ID = "add-to-cart-sauce-labs-";
    private final static String BASE_REMOVE_PRODUCT_ID = "remove-sauce-labs-";

     // Локатор за заглавието на страницата с продукти
    @FindBy(xpath = "//span[text()='Products']")
    WebElement productsPageTitle;

// за бутон навигация
    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenuBtn;

//иконката на количката с брой продукти
    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCartBadge;


    // Конструктор който инициализира WebElements с PageFactory
    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }


    //  дали сме на страницата с продукти като проверяваме наличието на основни елементи
    @Override
    public boolean isAt() {
        return productsPageTitle.isDisplayed() && burgerMenuBtn.isDisplayed();
    }

    public void addItemToTheCart(String itemName){
        WebElement itemTobeAdded = driver.findElement(By.id(BASE_PRODUCT_ID + itemName));
        itemTobeAdded.click();
    }

    public void removeItemFromTheCart(String itemName){
        WebElement itemTobeRemoved = driver.findElement(By.id(BASE_REMOVE_PRODUCT_ID + itemName));
        itemTobeRemoved.click();
    }
//връща броя на продуктите в количката
    public int getItemsInTheCart(){
        return Integer.parseInt(shoppingCartBadge.getText());
    }
}