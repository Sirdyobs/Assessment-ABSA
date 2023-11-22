package UITestSuites;

import Base.BasePage;
import Data.DataProvder;
import Marshall.TestMarshal;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;

public class AddUser extends BasePage {
    /**
     * All tests UI
     */
    TestMarshal marshal = new TestMarshal();

    /**
     * Add User journey with all the validations
     * @param TestStep
     * @param TestDescription
     * @param Action
     * @param Page
     * @param Property
     * @param IO
     * @throws InterruptedException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    @Test(priority = 1,dataProvider = "addUsers", dataProviderClass = DataProvder.class)
    public void addUser(String TestStep, String TestDescription,
                        String Action, String Page, String Property,String IO) throws InterruptedException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        marshal.test(TestStep, TestDescription, Action,Page,Property,IO);
    }

    /**
     * close the browser
     */
    @AfterTest
    public void close_browser(){
        quit_browser();
    }



}
