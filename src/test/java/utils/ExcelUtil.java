package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	private Workbook workbook;
    private Sheet sheet;

    public ExcelUtil(String sheetName) {

        try {

            FileInputStream fis =
                    new FileInputStream("C:\\Users\\Orcon\\eclipse-workspace\\AECearth_automation\\src\\test\\resources\\TestData.xlsx");

            workbook =
                    new XSSFWorkbook(fis);

            sheet =
                    workbook.getSheet(sheetName);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read Excel file",
                    e
            );
        }
    }

    public int getRowCount() {

        return sheet.getPhysicalNumberOfRows();
    }

    public int getColumnCount() {

        return sheet
                .getRow(0)
                .getPhysicalNumberOfCells();
    }

    public String getCellData(
            int row,
            int column) {

        Cell cell =
                sheet
                .getRow(row)
                .getCell(column);

        DataFormatter formatter =
                new DataFormatter();

        return formatter.formatCellValue(cell);
    }

}
