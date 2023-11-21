package Data;

import org.testng.annotations.DataProvider;

public class DataProvder {
    static FileReader fileReader = new FileReader();

    @DataProvider(name = "addUsers")
    public Object[][] addUsers() {
        Object testData[][] = fileReader.getTestData("AddUsers.xlsx");
        return testData;
    }

    @DataProvider(name = "useList")
    public Object[][] userList(String fileName) {
        Object testData[][] = fileReader.getTestData(fileName+".xlsx");
        return testData;
    }


}
