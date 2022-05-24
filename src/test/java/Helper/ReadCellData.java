package Helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadCellData {
    public static Map<String, Map<String, String>> setMapData() throws IOException {

        String path = System.getProperty("user.dir");
        String rel = path + "//src//test//java//TestData//userInputSS.xlsx";

        FileInputStream fis = new FileInputStream(rel);

        Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();

        Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String, String>>();

        Map<String, String> dataMap = new HashMap<String, String>();

        //Looping over entire row
        for (int i = 0; i <= lastRow; i++) {

            Row row = sheet.getRow(i);

            //1st Cell as Value
            Cell valueCell = row.getCell(1);

            //0th Cell as Key
            Cell keyCell = row.getCell(0);

            if(valueCell.getCellTypeEnum() == CellType.NUMERIC||valueCell.getCellTypeEnum() == CellType.FORMULA) {


                String value = String.valueOf(valueCell.getNumericCellValue());
                String key = keyCell.getStringCellValue().trim();

                if (HSSFDateUtil.isCellDateFormatted(valueCell)) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = valueCell.getDateCellValue();
                    value = df.format(date);

                    //Putting key & value in dataMap
                    dataMap.put(key, value);

                    //Putting dataMap to excelFileMap
                    excelFileMap.put("DataSheet", dataMap);
                }
            }

            else {
                String value = valueCell.getStringCellValue().trim();
                String key = keyCell.getStringCellValue().trim();

                //Putting key & value in dataMap
                dataMap.put(key, value);

                //Putting dataMap to excelFileMap
                excelFileMap.put("DataSheet", dataMap);
            }

        }

        //Returning excelFileMap
        return excelFileMap;

    }

    //Method to retrieve value
    public static String getMapData(String key) throws IOException {

        Map<String, String> m = setMapData().get("DataSheet");
        String value = m.get(key);

        return value;

    }

}