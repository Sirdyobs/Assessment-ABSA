package Webdriver;

import Base.BasePage;
import Data.DataProvder;
import Helper.Utility;
import Page.WebTable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Actions extends BasePage {

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


    public void addUserList(String fileName) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        WebTable table = new WebTable();
        DataProvder data = new DataProvder();
        Utility util = new Utility();
        table.addUser(data.userList(fileName), util.getRandomNumber());
    }

    public void validate_data(String fileName) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        WebTable table = new WebTable();
        DataProvder data = new DataProvder();
        table.validate(data.userList(fileName));
    }

    public void switchToNewTab(){
        Set<String> handles = driver.getWindowHandles();
        for(String actual: handles){
            if(!actual.equalsIgnoreCase(currentWindowHandle)) {
                driver.switchTo().window(actual);
            }
        }
    }

    public void selectFromDropdownUsingValue(String selector,String selectorValue,String valueToSelect){
        //webDriver.switchTo().frame(webDriver.findElement(By.tagName("om-personal-loans-calculator")));

        String input[] = valueToSelect.split(":");
        Select webElement = null;
        switch (selector) {
            case "id":
                webElement = new Select(driver.findElement(By.id(selectorValue)));
                break;
            case "css":
                webElement = new Select(driver.findElement(By.cssSelector(selectorValue)));
                break;
            case "xpath":
                webElement = new Select(driver.findElement(By.xpath(selectorValue)));
                break;
            case "tagName":
                webElement = new Select(driver.findElement(By.tagName(selectorValue)));
                break;
        }
        webElement.selectByValue("R50000");
    }

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

    public void webDriverWaitToBeClickable(String selector,String selectorValue){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        switch (selector) {
            case "id":
                wait.until(ExpectedConditions.elementToBeClickable(By.id(selectorValue)));
                break;
            case "css":
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selectorValue)));
                break;
            case "xpath":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selectorValue)));
                break;
            case "tagName":
                wait.until(ExpectedConditions.elementToBeClickable(By.tagName(selectorValue)));
                break;
        }
    }

    public void selectDropdownUsingOptions(String dropDownSelector, String dropDownSelectorValue,
                                           String optionsSelector,String optionsSelectorValue,String valueToSelect) throws InterruptedException {
        WebElement dropdownElement = null;
        Thread.sleep(3000);
        switch (dropDownSelector) {
            case "id":
                webDriverWaitToBeClickable(dropDownSelector,dropDownSelectorValue);
                driver.findElement(By.id(dropDownSelectorValue)).click();
                break;
            case "css":
                webDriverWaitToBeClickable(dropDownSelector,dropDownSelectorValue);
                //webDriver.findElement(By.cssSelector(dropDownSelectorValue)).click();
                break;
            case "xpath":
                webDriverWaitToBeClickable(dropDownSelector,dropDownSelectorValue);
                driver.findElement(By.xpath(dropDownSelectorValue)).click();
                break;
            case "tagName":
                webDriverWaitToBeClickable(dropDownSelector,dropDownSelectorValue);
                driver.findElement(By.tagName(dropDownSelectorValue)).click();
                break;
        }
        List<WebElement> optionsElement = null;

        switch (optionsSelector) {
            case "id":
                optionsElement = driver.findElements(By.id(optionsSelectorValue));
                break;
            case "css":
                optionsElement = driver.findElements(By.cssSelector(optionsSelectorValue));
                break;
            case "xpath":
                optionsElement = driver.findElements(By.xpath(optionsSelectorValue));
                break;
            case "tagName":
                optionsElement = driver.findElements(By.tagName(optionsSelectorValue));
                break;
        }

        for(int i = 0; i <= optionsElement.size() - 1; i++) {
            if (optionsElement.get(i).getAttribute("text").equalsIgnoreCase(valueToSelect.split(":")[1])){
                JavascriptExecutor executer = (JavascriptExecutor)driver;
                executer.executeScript("arguments[0].click();",optionsElement.get(i));
                driver.navigate().refresh();
                executer.executeScript("arguments[0].click();",optionsElement.get(i));
            }
        }
    }


}
