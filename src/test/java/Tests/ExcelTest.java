package Tests;

import org.testng.annotations.Test;
import utils.ExcelReader;

import java.util.List;

public class ExcelTest {

    @Test
    public void readExcel(){

        List<List<String>> data =
                ExcelReader.readSheet(
                        "Login_Data");

        for(List<String> row : data){

            System.out.println(row);
        }
    }
}