package Page;

public class WebTable {

    public String users_list_table_xpath(){
        return "//table";
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

    public String chkCompanyBBB_xpath(){
        return "//label[contains(., 'BBB')]/input";
    }

    public String cmbRole_name(){
        return "RoleId";
    }

    public String txtEmail_name(){
        return "Email";
    }

    public String txtCellphone_name(){
        return "Mobilephone";
    }

    public String btnSave_xpath(){
        return "//button[text()='Save']";
    }

    public String btnClose_xpath(){
        return "//button[text()='Close']";
    }




}
