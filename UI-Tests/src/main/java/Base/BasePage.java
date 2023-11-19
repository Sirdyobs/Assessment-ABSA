package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public WebDriver driver;
    public Properties props;


    /**
     * This method loads properties from resources file.
     * @return
     */

    public Properties init_props(){
        try {
            props = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\src\\main\\java\\Config\\config.properties");
            props.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return props;
    }


    /**
     * This method initialises the webdriver specified in the properties file
     */

    public WebDriver init_driver(Properties prop){
        String browser = prop.getProperty("browser");
        String headless = prop.getProperty("headless");
        try{
            if(browser.equalsIgnoreCase("chrome")){
                WebDriverManager.chromedriver().setup();
                if (headless.equalsIgnoreCase("yes")){
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                } else{
                    driver = new ChromeDriver();
                }
            } else if (browser.equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                if (headless.equalsIgnoreCase("yes")){
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--headless");
                    driver = new FirefoxDriver(options);
                } else {
                    driver = new FirefoxDriver();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        driver.manage().window().fullscreen();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
        return driver;
    }

    /**
     * This method should quit the browser
     */

    public void quit_browser(){
        try{
            driver.quit();
        } catch (Exception e){
            System.out.println("Exception occured while quiting the browser");
            e.printStackTrace();
        }
    }
    /**
     * This method should close the browser
     */

    public void close_browser(){
        try{
            driver.close();
        } catch (Exception e){
            System.out.println("Exception occured while closing the browser");
            e.printStackTrace();
        }

    }

}
