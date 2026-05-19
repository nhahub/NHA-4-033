package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    private static final String FILE_PATH =
            "src/test/resources/testdata/SauceDemo-Data.xlsx";

    public static List<List<String>> readSheet(
            String sheetName){

        List<List<String>> data =
                new ArrayList<>();

        try{

            FileInputStream fis =
                    new FileInputStream(FILE_PATH);

            Workbook workbook =
                    new XSSFWorkbook(fis);

            Sheet sheet =
                    workbook.getSheet(sheetName);

            for(int i=1;
                i<=sheet.getLastRowNum();
                i++){

                Row row =
                        sheet.getRow(i);

                List<String> rowData =
                        new ArrayList<>();

                for(int j=0;
                    j<row.getLastCellNum();
                    j++){

                    Cell cell =
                            row.getCell(j);

                    rowData.add(
                            cell == null
                                    ? ""
                                    : cell.toString());
                }

                data.add(rowData);
            }

            workbook.close();
            fis.close();
        }

        catch(Exception e){

            e.printStackTrace();
        }

        return data;
    }
}