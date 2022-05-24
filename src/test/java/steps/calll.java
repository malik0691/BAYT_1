package steps;

//import Helper.CellValues;
//import Helper.DataRead;
import Helper.Xls_Reader;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class calll {

    public static void main(String[] args) throws Exception {
//        String path = System.getProperty("user.dir");
//
//        String rel = path + "\\src\\test\\java\\TestData\\userInput1.xlsx";
//
//        Xls_Reader y = new Xls_Reader(rel);
//
//        String CreatedPlacementID= y.getCellData1("FixedData", "placement", 2);
//
//        System.out.println(CreatedPlacementID);


//        Xls_Reader x = new Xls_Reader("Z:\\Projects\\Cucumber_framework\\src\\test\\java\\TestData\\Test.xlsx");

        String Path= System.getProperty("user.dir");
//        String Rel= Path+ "\\src\\test\\java\\TestData\\userInput1.xlsx";
        String Rel= Path+ "/src/test/java/TestData/userInputSS.xlsx";
        Xls_Reader x= new Xls_Reader(Rel);

        String CreatedPlacementID= x.getCellData1("LoginAndSignup", "FirstName", 2);


//        String splitVal[] = CreatedPlacementID.split("\\.");
//        CreatedPlacementID=splitVal[0];

//        String codeVal = x.getCellData1("PlayableChargesData", "placement_PayableCharge", 2);
        System.out.println(CreatedPlacementID.toString());
    }
}