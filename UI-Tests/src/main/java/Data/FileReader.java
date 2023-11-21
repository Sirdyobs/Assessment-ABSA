package Data;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {
    @DataProvider(name = "dp")
    public Object[][] getTestData(String filename) {
        XSSFWorkbook workbook = null;
        Sheet sheet;
        String testDataPath = System.getProperty("user.dir")+"\\src\\test\\resources\\"+filename;
        FileInputStream file = null;
        try {
            file = new FileInputStream(testDataPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook = new XSSFWorkbook(file);
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheet("Sheet1");
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int row = 0; row < sheet.getLastRowNum(); row++) {
            for (int col = 0; col < sheet.getRow(row).getLastCellNum(); col++) {
                if (sheet.getRow(row + 1).getCell(col) != null)
                    if(row + 1 == 2){
                        System.out.print(9);
                    }
                //data[row][col] = sheet.getRow(row + 1).getCell(col).toString();
                data[row][col] = CellUtil.getCell(sheet.getRow(row + 1),col).toString();
            }
        }
        return data;
    }
}
