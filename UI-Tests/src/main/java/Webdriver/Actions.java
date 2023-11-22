package Webdriver;

import Base.BasePage;
import Data.DataProvder;
import Helper.Utility;
import Page.WebTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;

public class Actions extends BasePage {
    /**
     * This class defines webdriver actions
     */
    public Actions() {
        super();
    }

    WebElement element = null;
    String currentWindowHandle;
    public void navigate(){
        try{
            init_driver(props);
            driver.get(props.getProperty("url"));
            driver.manage().window().maximize();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Handles all click events of the webdriver
     * @param page_name
     * @param method
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void element_click(String page_name, String method) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class<?> page = Class.forName("Page."+page_name);
        Method method_name = page.getMethod(method);
        Object objectOfPage = page.newInstance();
        try {
            switch (method.substring(method.lastIndexOf("_")+1)){
                case "id":
                    webDriverWaitToLoad(method.substring(method.lastIndexOf("_")+1),(String) method_name.invoke(objectOfPage));
                    driver.findElement(By.id((String) method_name.invoke(objectOfPage))).click();
                    break;
                case "css":
                    webDriverWaitToLoad(method.substring(method.lastIndexOf("_")+1),(String) method_name.invoke(objectOfPage));
                    driver.findElement(By.cssSelector((String) method_name.invoke(objectOfPage))).click();
                    break;
                case "xpath":
                    webDriverWaitToLoad(method.substring(method.lastIndexOf("_")+1),(String) method_name.invoke(objectOfPage));
                    driver.findElement(By.xpath((String) method_name.invoke(objectOfPage))).click();
                    break;
                case "name":
                    webDriverWaitToLoad(method.substring(method.lastIndexOf("_")+1),(String) method_name.invoke(objectOfPage));
                    driver.findElement(By.name((String) method_name.invoke(objectOfPage))).click();
                    break;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Handles validation of web driver
     * @param page_name
     * @param method
     * @param io
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public boolean validate(String page_name, String method, String io) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> page = Class.forName("Page."+page_name);
        Method method_name = page.getMethod(method);
        Object objectOfPage = page.newInstance();
        WebElement webElement = null;
        boolean valid = false;

        switch (method.substring(method.lastIndexOf("_")+1)){
            case "id":
                webDriverWaitToLoad(method.substring(method.lastIndexOf("_")+1),(String) method_name.invoke(objectOfPage));
                webElement = driver.findElement(By.id((String) method_name.invoke(objectOfPage)));
                break;
            case "css":
                webDriverWaitToLoad(method.substring(method.lastIndexOf("_")+1),(String) method_name.invoke(objectOfPage));
                webElement = driver.findElement(By.cssSelector((String) method_name.invoke(objectOfPage)));
                break;
            case "xpath":
                webDriverWaitToLoad(method.substring(method.lastIndexOf("_")+1),(String) method_name.invoke(objectOfPage));
                webElement = driver.findElement(By.xpath((String) method_name.invoke(objectOfPage)));
                break;
            case "tagName":
                webDriverWaitToLoad(method.substring(method.lastIndexOf("_")+1),(String) method_name.invoke(objectOfPage));
                webElement = driver.findElement(By.tagName((String) method_name.invoke(objectOfPage)));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + method.substring(method.lastIndexOf("_") + 1));
        }
        String[] validate_values = io.split(":");
        if (validate_values[0].equalsIgnoreCase("out"))
            if (webElement.getAttribute("innerText").equalsIgnoreCase(validate_values[1]))
                valid = true;

        return valid;
    }

    /**
     * Handles adding values from a file
     * @param fileName
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void addUserList(String fileName) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        WebTable table = new WebTable();
        DataProvder data = new DataProvder();
        Utility util = new Utility();
        table.addUser(data.userList(fileName), util.getRandomNumber());
    }

    /**
     * Handles validating data from a file
     * @param fileName
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void validate_data(String fileName) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        WebTable table = new WebTable();
        DataProvder data = new DataProvder();
        table.validate(data.userList(fileName));
    }


    /**
     * Hanldes web driver wait
     * @param selector
     * @param selectorValue
     */
    public void webDriverWaitToLoad(String selector,String selectorValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        switch (selector) {
            case "id":
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(selectorValue)));
                break;
            case "css":
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectorValue)));
                break;
            case "xpath":
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectorValue)));
                break;
            case "tagName":
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(selectorValue)));
                break;
        }
    }

}
