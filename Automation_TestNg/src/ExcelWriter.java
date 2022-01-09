import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//This class handles all the excel related activities
public class ExcelWriter {

	// creating variables for fileinput stream, workbook and sheet
	FileInputStream fi;
	XSSFWorkbook wb;
	XSSFSheet sh;

	public ExcelWriter() throws IOException {

		// initializing file input stream and assigning the file
		fi = new FileInputStream("C:\\Users\\New\\Desktop\\Data.xlsx");
		// initializing workbook with workbook of file
		wb = new XSSFWorkbook(fi);
		// initializing sheet with sheet from the workbook
		sh = wb.getSheet("Sheet1");
	}

	// method the read value from excel sheet based on row and column location
	public String getvalue(int x, int y) throws Exception {
		String a = sh.getRow(x).getCell(y).getStringCellValue();
		return a;
	}

	// closing the workbook and file
	public void close() throws IOException {
		wb.close();
		fi.close();
	}
}
