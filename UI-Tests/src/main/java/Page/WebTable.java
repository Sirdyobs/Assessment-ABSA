package Page;

import Base.BasePage;
import Data.FileReader;
import Helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class WebTable extends BasePage {

    public WebTable(){
        super();
    }
    public String users_list_table_xpath(){
        return "//table";
    }

    public String usernameList(){
        return "//table/tbody/tr/td[3]";
    }
    public String btn_add_user_xpath(){
        return "//button[text()=' Add User']";
    }

    public String popup_add_user_form_xpath(){
        return "//div[@class='modal ng-scope']";
    }

    public String txtFirstName_name(){
        return "FirstName";
    }

    public String txtLastName_name(){
        return "LastName";
    }

    public String txtUserName_name(){
        return "UserName";
    }

    public String txtPassword_name(){
        return "Password";
    }

    public String chkCompanyAAA_xpath(){
        return "//label[contains(., 'AAA')]/input";
    }

    public String chkCompanyBBB_xpath() {
        return "//label[contains(., 'BBB')]/input";
    }

    public String cmbRole_name() {
        return "RoleId";
    }

    public String txtEmail_name() {
        return "Email";
    }

    public String txtCellphone_name() {
        return "Mobilephone";
    }

    public String btnSave_xpath() {
        return "//button[text()='Save']";
    }

    public String btnClose_xpath() {
        return "//button[text()='Close']";
    }

    public String table_row_elements(String key) {
        return "//table/tbody/tr[td//text()='"+key+"']/td";
    }
    By btnAdd = By.xpath(this.btn_add_user_xpath());
    By fname = By.name(this.txtFirstName_name());
    By lname = By.name(this.txtLastName_name());
    By uname = By.name(this.txtUserName_name());
    By pword = By.name(this.txtPassword_name());
    By customerA = By.xpath(this.chkCompanyAAA_xpath());
    By customerB = By.xpath(this.chkCompanyBBB_xpath());
    By role = By.name(this.cmbRole_name());
    By email = By.name(this.txtEmail_name());
    By cell = By.name(this.txtCellphone_name());
    By userform = By.xpath(this.popup_add_user_form_xpath());
    By btnSave = By.xpath(this.btnSave_xpath());
    By btnClose = By.xpath(this.btnClose_xpath());
    By userNameList = By.xpath(this.usernameList());
    FileReader fileReader = new FileReader();

    public boolean addUser(Object[][] userData,int key){
        Utility util = new Utility();
        for(int i=0;i<=userData.length-1; i++){
            List<WebElement> usernameList = driver.findElements(userNameList);
            for(int j=0;j<=userData[i].length; j++){
                try{
                    driver.findElement(fname).clear();
                    driver.findElement(fname).sendKeys(userData[i][j].toString());
                    j++;
                    driver.findElement(lname).clear();
                    driver.findElement(lname).sendKeys(userData[i][j].toString());
                    j++;
                    if(!usernameList.contains(userData[i][j].toString()+key)) {
                        driver.findElement(uname).clear();
                        driver.findElement(uname).sendKeys(userData[i][j].toString()+key);
                    } else {
                        driver.findElement(uname).clear();
                        driver.findElement(uname).sendKeys(userData[i][j].toString()+util.getRandomNumber());
                    }
                    j++;
                    driver.findElement(pword).clear();
                    driver.findElement(pword).sendKeys(userData[i][j].toString());
                    j++;
                    if (userData[i][j].toString().equalsIgnoreCase("Company AAA")){
                        driver.findElement(customerA).click();
                    } else {
                        driver.findElement(customerB).click();
                    }
                    j++;
                    Select role_values = new Select(driver.findElement(By.name(this.cmbRole_name())));
                    role_values.selectByVisibleText(userData[i][j].toString());
                    j++;
                    driver.findElement(email).clear();
                    driver.findElement(email).sendKeys(userData[i][j].toString());
                    j++;
                    driver.findElement(cell).clear();
                    driver.findElement(cell).sendKeys(userData[i][j].toString());
                    j++;
                    driver.findElement(btnSave).click();
                    if(i < userData.length-1)
                        driver.findElement(btnAdd).click();
                } catch(Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validate(Object[][] userData){
        Utility util = new Utility();
        for(int i=0;i<=userData.length-1; i++){
            for(int j=0;j<=userData[i].length; j++){
                By table_row = By.xpath(this.table_row_elements(userData[i][7].toString()));
                List<WebElement> user_details= driver.findElements(table_row);
                List<Object> row_values = Arrays.asList(Arrays.asList(userData).get(i));

                for(WebElement element: user_details){
                    if (!element.getText().contains("User"))
                        if (!row_values.contains(element.getText())){
                            System.out.println("Test failed, value: " + element.getText());
                            return false;
                        }
                }
            }
        }
        return true;
    }





}
