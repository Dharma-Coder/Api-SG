package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class TestDataExtractor {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    public static int rowNumber;

    public static Object[][] ExcelData(String workbookName, String sheetName) {
        Object[][] x = new Object[0][];
        try {
            DataFormatter formatter = new DataFormatter();
            workbook = new XSSFWorkbook(workbookName);
            sheet = workbook.getSheet(sheetName);

            rowNumber = sheet.getPhysicalNumberOfRows();
            int columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();
            x = new Object[rowNumber - 1][columnNumber];
            for (int i = 1; i < rowNumber; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < columnNumber; j++) {
                    Cell cell = row.getCell(j);
                    x[i - 1][j] = formatter.formatCellValue(cell);
                }
            }

            workbook.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return x;
    }
}
