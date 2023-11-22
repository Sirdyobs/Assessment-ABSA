package Marshall;

import Webdriver.Actions;

import java.lang.reflect.InvocationTargetException;

public class TestMarshal{

    public boolean test(String testStep,String testDesc,String action,
                        String page_name,String property,String io) throws InterruptedException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        boolean test_passed = false;
        Actions actions = new Actions();
        switch (action) {
            case "navigate":
                actions.navigate();
                test_passed = true;
                break;
            case "validate":
                actions.validate(page_name,property,io);
                test_passed = true;
                break;
            case "validate_data":
                actions.validate_data(io);
                test_passed = true;
                break;
            case "click":
                actions.element_click(page_name,property);
                test_passed = true;
                break;
            case "Add":
                actions.addUserList(io);
                test_passed = true;
                break;

            case "endTest":
        }
        return test_passed;
    }



}
