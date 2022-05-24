package Helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CellData {
    public static void main (String [] args) throws IOException{
////Path of the excel file
//        FileInputStream fs = new FileInputStream("D:\\DemoFile.xlsx");
////Creating a workbook
//        XSSFWorkbook workbook = new XSSFWorkbook(fs);
//        XSSFSheet sheet = workbook.getSheetAt(0);
//        Row row = sheet.getRow(0);
//        Cell cell = row.getCell(0);
//        System.out.println(sheet.getRow(0).getCell(0));
//        Row row1 = sheet.getRow(1);
//        Cell cell1 = row1.getCell(1);
//        System.out.println(sheet.getRow(0).getCell(1));
//        Row row2 = sheet.getRow(1);
//        Cell cell2 = row2.getCell(1);
//        System.out.println(sheet.getRow(1).getCell(0));
//        Row row3 = sheet.getRow(1);
//        Cell cell3 = row3.getCell(1);
//        System.out.println(sheet.getRow(1).getCell(1));
////String cellval = cell.getStringCellValue();
////System.out.println(cellval);


        String path = System.getProperty("user.dir");
        String rel = path + "//src//test//java//TestData//userInputSS.xlsx";

        FileInputStream inputStream = new FileInputStream(rel);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFCell firstColumnCell = null;
        int firstColumnRowCount = 0;
        for (int i = 0; i <= rowCount; i++) {
            try {
                XSSFRow row = sheet.getRow(i);
                firstColumnCell = row.getCell(0);
            }
            catch (NullPointerException nullPointerException) {
                System.out.println("Cell is null at index: " + i);
            }
            if (firstColumnCell != null) {
                if (firstColumnCell.getStringCellValue().length() > 0) {
                    firstColumnRowCount = i;
                }
            }
        }
        for (int j = 0; j <= firstColumnRowCount; j++) {
            XSSFRow row = sheet.getRow(j);
            XSSFCell cell = row.getCell(0);
            System.out.println(cell.getStringCellValue());
        }
        workbook.close();
    }
}
