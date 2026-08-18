package data;

import org.testng.annotations.DataProvider;

import utils.ExcelUtil;

public class TestDataProvider {
	
	 @DataProvider(name = "loginData")
	public static Object[][] loginData() {
        
        ExcelUtil excel = new ExcelUtil("Login");

        int rows = excel.getRowCount();
        int columns = excel.getColumnCount();

        Object[][] data = new Object[rows - 1][columns];

        // Start from 1 because row 0 contains headings
        for (int i = 1; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                data[i - 1][j] =
                        excel.getCellData(i, j);
            }
        }

        return data;
    }

}
