package TestSuites;

import Data.DataProvder;
import Marshall.TestMarshal;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;

public class AddUser {

    TestMarshal marshal = new TestMarshal();

    @Test(dataProvider = "addUsers", dataProviderClass = DataProvder.class)
    public void addUser(String TestStep, String TestDescription,
                        String Action, String Page, String Property,String IO) throws InterruptedException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        marshal.test(TestStep, TestDescription, Action,Page,Property,IO);
    }





}
