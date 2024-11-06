package baseTestCase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
public class TestUtil {

    public WebDriver driver;
    private String url, browser;
    private int implicitWait;

    // Метод който настройва драйвъра и зарежда УРЛ преди всеки тест
    @BeforeMethod
    public void setupDriverAndOpenTestUrl(){
        readConfig("src/test/resources/config.properties");
        setUpDriver();
        driver.get(url);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();  //затваряне на драйвера след всеки тест
    }

    private void readConfig(String configPath){
        try{
            FileInputStream fileInputStream = new FileInputStream(configPath);
            Properties properties = new Properties();
            properties.load(fileInputStream);

// Прочита урл и типа на браузъра от конфигурацията
            url = properties.getProperty("testUrl");
            browser = properties.getProperty("browser");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        //// Прочитане на implicitWait от конфигурационния файл
//        implicitWait = Integer.parseInt(properties.getProperty("implicitWait"));
//    } catch (IOException e) {
//        System.out.println(e.getMessage());
//    }
    }

    private void setUpDriver(){
        switch (browser){
            case "firefox":
                driver = setupFirefoxDriver();
                break;
            case "chrome":
                driver = setupChromeDriver();
             break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private WebDriver setupFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
// Инсталира подходящата версия на драйвъра за Chrome
    private WebDriver setupChromeDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

}
